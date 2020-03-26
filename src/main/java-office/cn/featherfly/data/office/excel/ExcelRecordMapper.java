
package cn.featherfly.data.office.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.impl.SimpleDataRecord;

/**
 * <p>
 * ExcelRecordMapper
 * </p>
 *
 * @author 钟冀
 */
public class ExcelRecordMapper extends ExcelDataMapper<DataRecord> {

    /**
     */
    public ExcelRecordMapper() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataRecord mapRecord(Row row, int rowNum) {
        Workbook workbook = row.getSheet().getWorkbook();
        FormulaEvaluator evaluator = getFormulaEvaluator(workbook);
        return craeteRecord(row, evaluator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillData(Row row, DataRecord record, int rowNum) {
        for (int i = 0; i < record.getValuesNumber(); i++) {
            Object value = record.get(i);
            setCellValue(value, row.createCell(i));
        }
    }

    private DataRecord craeteRecord(Row row, FormulaEvaluator evaluator) {
        SimpleDataRecord record = null;
        if (row != null) {
            record = new SimpleDataRecord();
            for (int cIndex = 0; cIndex < row.getLastCellNum(); cIndex++) {
                Cell cell = row.getCell(cIndex);
                Object value = getCellValue(cell, evaluator);
                record.add(cIndex + "", value);
            }
        }
        return record;
    }
}
