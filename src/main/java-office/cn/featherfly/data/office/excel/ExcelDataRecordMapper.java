
package cn.featherfly.data.office.excel;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
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
public class ExcelDataRecordMapper implements ExcelDataMapper<DataRecord> {

    /**
     */
    public ExcelDataRecordMapper() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataRecord mapRow(Row row, int rowNum) {
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

    private DataRecord craeteRecord(Row row, FormulaEvaluator evaluator) {
        SimpleDataRecord record = null;
        if (row != null) {
            record = new SimpleDataRecord();
            for (int cIndex = 0; cIndex < row.getLastCellNum(); cIndex++) {
                Cell cell = row.getCell(cIndex);
                if (cell == null) {
                    record.add(cIndex + "", null);
                } else {
                    Object value = null;
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                value = cell.getDateCellValue();
                            } else {
                                value = cell.getNumericCellValue();
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            break;
                        case Cell.CELL_TYPE_ERROR:
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            CellValue cellValue = evaluator.evaluate(cell);
                            switch (cellValue.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    value = cellValue.getStringValue();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    value = cellValue.getNumberValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    value = cellValue.getBooleanValue();
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                                    break;
                                // CELL_TYPE_FORMULA will never happen
                                case Cell.CELL_TYPE_FORMULA:
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    record.add(cIndex + "", value);
                }
            }
        }
        return record;
    }
}
