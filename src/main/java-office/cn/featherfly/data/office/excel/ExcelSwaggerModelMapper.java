
package cn.featherfly.data.office.excel;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import cn.featherfly.common.policy.AllowPolicy;
import io.swagger.annotations.ApiModelProperty;

/**
 * ExcelSwaggerModelMapper.
 *
 * @param <R> 要映射的具体类
 * @author 钟冀
 */
public class ExcelSwaggerModelMapper<R> extends ExcelDataMapper<R> {

    private boolean insertTitleRow = true;

    private AllowPolicy<String> allowPolicy;

    public ExcelSwaggerModelMapper() {
        this(true, k -> true);
    }

    public ExcelSwaggerModelMapper(AllowPolicy<String> allowPolicy) {
        this(true, allowPolicy);
    }

    /**
     * @param insertTitleRow insertTitleRow
     */
    public ExcelSwaggerModelMapper(boolean insertTitleRow, AllowPolicy<String> allowPolicy) {
        this.insertTitleRow = insertTitleRow;
        this.allowPolicy = allowPolicy;
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
            createTitleRow(row, rowNum, beanProperties);

            int index = 0;
            for (BeanProperty<?, ?> beanProperty : beanProperties) {
                if (allowPolicy.isAllow(beanProperty.getName())) {
                    Cell cell = row.createCell(index);
                    Object object = beanProperty.getValue(record);
                    setCellValue(object, cell);
                    index++;
                }
            }
        }
    }

    private void createTitleRow(Row row, int rowNum, Collection<BeanProperty<?, ?>> beanProperties) {
        int index = 0;
        if (insertTitleRow && rowNum == 0) {
            CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            // 第一行的时候再把标题添加进去
            for (BeanProperty<?, ?> beanProperty : beanProperties) {
                if (allowPolicy.isAllow(beanProperty.getName())) {
                    ApiModelProperty apiModelProperty = beanProperty.getAnnotation(ApiModelProperty.class);
                    Cell cell = row.createCell(index);
                    cell.setCellStyle(cellStyle);
                    setCellValue(apiModelProperty.value(), cell);
                    index++;
                }
            }
            row = row.getSheet().createRow(rowNum + 1);
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
        return beanDescriptor.findBeanPropertys(new BeanPropertyAnnotationMatcher(ApiModelProperty.class));
    }
}
