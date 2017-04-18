
package cn.featherfly.data.office.excel;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.impl.SimpleDataRecord;

/**
 * <p>
 * ExcelDataMapper
 * </p>
 * 
 * @author 钟冀
 */
public class ExcelDataRecordMapper extends ExcelDataMapper<DataRecord> {

    /**
     */
    public ExcelDataRecordMapper() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataRecord mapRecord(Row row, int rowNum) {
        Workbook workbook = row.getSheet().getWorkbook();
        FormulaEvaluator evaluator = null;
        if (workbook instanceof XSSFWorkbook) {
            evaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
        } else if (workbook instanceof SXSSFWorkbook) {
            evaluator = new SXSSFFormulaEvaluator((SXSSFWorkbook) workbook);
        } else {
            evaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
        }
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
