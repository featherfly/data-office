
package cn.featherfly.data.office.excel;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ExcelOpenApiModelMapper.
 *
 * @param <R> 要映射的具体类
 * @author zhongj
 */
public class ExcelOpenApiModelMapper<R> extends ExcelDataMapper<R> {

    private boolean insertTitleRow = true;

    /**
     */
    public ExcelOpenApiModelMapper() {
        this(true);
    }

    /**
     * @param insertTitleRow insertTitleRow
     */
    public ExcelOpenApiModelMapper(boolean insertTitleRow) {
        this.insertTitleRow = insertTitleRow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R mapRecord(Row row, int rowNum) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillData(Row row, R record, int rowNum) {
        if (record != null) {
            BeanDescriptor<?> beanDescriptor = BeanDescriptor.getBeanDescriptor(record.getClass());

            Collection<BeanProperty<?, ?>> beanProperties = getApiModelBeanProperties(beanDescriptor);
            int index = 0;
            if (insertTitleRow && rowNum == 0) {
                CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                // 第一行的时候再把标题添加进去
                for (BeanProperty<?, ?> beanProperty : beanProperties) {
                    Schema apiModelProperty = beanProperty.getAnnotation(Schema.class);
                    Cell cell = row.createCell(index);
                    cell.setCellStyle(cellStyle);
                    setCellValue(apiModelProperty.description(), cell);
                    index++;
                }
                row = row.getSheet().createRow(rowNum + 1);
                index = 0;
            }

            for (BeanProperty<?, ?> beanProperty : beanProperties) {
                Cell cell = row.createCell(index);
                Object object = beanProperty.getValue(record);
                setCellValue(object, cell);
                index++;
            }
        }
    }

    /**
     * 返回insertTitleRow
     *
     * @return insertTitleRow
     */
    public boolean isInsertTitleRow() {
        return insertTitleRow;
    }

    /**
     * 设置insertTitleRow
     *
     * @param insertTitleRow insertTitleRow
     */
    public void setInsertTitleRow(boolean insertTitleRow) {
        this.insertTitleRow = insertTitleRow;
    }

    private Collection<BeanProperty<?, ?>> getApiModelBeanProperties(BeanDescriptor<?> beanDescriptor) {
        return beanDescriptor.findBeanPropertys(new BeanPropertyAnnotationMatcher(Schema.class));
    }
}
