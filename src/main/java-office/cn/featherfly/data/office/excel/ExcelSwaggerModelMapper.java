
package cn.featherfly.data.office.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;

/**
 * <p>
 * ExcelSwaggerModelMapper
 * </p>
 * 
 * @author 钟冀
 */
public class ExcelSwaggerModelMapper extends ExcelDataMapper<Object> {

    /**
     */
    public ExcelSwaggerModelMapper() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object mapRecord(Row row, int rowNum) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillData(Row row, Object record, int rowNum) {
        if (record != null) {
            List<List<Object>> recoreds = new ArrayList<>();            
            BeanDescriptor<?> beanDescriptor = BeanDescriptor.getBeanDescriptor(record.getClass());
            List<Object> recored = new ArrayList<>();
            for (BeanProperty<?> beanProperty : beanDescriptor.getBeanProperties()) {
                Object object = beanProperty.getValue(record);
                recored.add(object);
            }
            recoreds.add(recored);
            fillData(recoreds, row);
        }
    }
    
    private void fillData(List<?> rowValues, Row row) {
        for (int i = 0; i < rowValues.size(); i++) {
            Object value = rowValues.get(i);
            Cell cell = row.createCell(i);
            setCellValue(value, cell);
        }
    }
}
