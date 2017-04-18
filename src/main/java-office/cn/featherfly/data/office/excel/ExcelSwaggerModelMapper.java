
package cn.featherfly.data.office.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ExcelSwaggerModelMapper
 * </p>
 * 
 * @author 钟冀
 */
public class ExcelSwaggerModelMapper<R> extends ExcelDataMapper<R> {
    
    private boolean insertTitleRow = true;

    /**
     */
    public ExcelSwaggerModelMapper() {
    }
    
    /**
     * 
     * @param insertTitleRow insertTitleRow
     */
    public ExcelSwaggerModelMapper(boolean insertTitleRow) {
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
            if (insertTitleRow && rowNum == 0) {
                // 第一行的时候再把标题添加进去
                List<Object> titles = new ArrayList<>();
                for (BeanProperty<?> beanProperty : beanDescriptor.getBeanProperties()) {
                    ApiModelProperty apiModelProperty = (ApiModelProperty) beanProperty
                            .getAnnotation(ApiModelProperty.class);
                    titles.add(apiModelProperty.value());
                }
                fillData(titles, row);
                row = row.getSheet().createRow(rowNum + 1); 
            }

            List<Object> recored = new ArrayList<>();
            for (BeanProperty<?> beanProperty : beanDescriptor.getBeanProperties()) {
                Object object = beanProperty.getValue(record);
                recored.add(object);
            }

            fillData(recored, row);
        }
    }

    private void fillData(List<?> rowValues, Row row) {
        for (int i = 0; i < rowValues.size(); i++) {
            Object value = rowValues.get(i);
            Cell cell = row.createCell(i);
            setCellValue(value, cell);
        }
    }
    
    /**
     * 返回insertTitleRow
     * @return insertTitleRow
     */
    public boolean isInsertTitleRow() {
        return insertTitleRow;
    }

    /**
     * 设置insertTitleRow
     * @param insertTitleRow insertTitleRow
     */
    public void setInsertTitleRow(boolean insertTitleRow) {
        this.insertTitleRow = insertTitleRow;
    }
}
