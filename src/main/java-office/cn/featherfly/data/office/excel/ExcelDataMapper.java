
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.reflect.GenericClass;
import cn.featherfly.conversion.core.Conversion;
import cn.featherfly.conversion.core.ConversionPolicysJdk8;
import cn.featherfly.conversion.core.TypeConversion;
import cn.featherfly.data.core.DataMapper;

/**
 * <p>
 * ExcelDataMapper
 * </p>
 * 
 * @param <R>
 *            要映射的具体类
 * 
 * @author 钟冀
 */
public abstract class ExcelDataMapper<R> implements DataMapper<R, Row> {
    
    /**
     * logger
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * conversion
     */
    @SuppressWarnings("rawtypes")
    protected Conversion conversion = new TypeConversion(ConversionPolicysJdk8.getBasicConversionPolicy());

    /**
     * <p>
     * 设置值到Cell里
     * </p>
     * 
     * @param value
     *            值
     * @param cell
     *            cell
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void setCellValue(Object value, Cell cell) {
        if (value != null) {
            cell.setCellValue(conversion.toString(value, new GenericClass(value.getClass())));
        }
    }
    
    /**
     * <p>
     * 返回cell的值
     * </p>
     * 
     * @param cell
     *            cell
     * @param evaluator
     *            FormulaEvaluator
     * @return cell的值
     */
    @SuppressWarnings("deprecation")
    protected Object getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) {
            return null;
        }

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
        return value;
    }
    
    /**
     * <p>
     * 获取FormulaEvaluator
     * </p>
     * @param workbook workbook
     * @return FormulaEvaluator
     */
    protected FormulaEvaluator getFormulaEvaluator(Workbook workbook) {
        FormulaEvaluator evaluator = null;
        if (workbook instanceof XSSFWorkbook) {
            evaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
        } else if (workbook instanceof SXSSFWorkbook) {
            evaluator = new SXSSFFormulaEvaluator((SXSSFWorkbook) workbook);
        } else {
            evaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
        }
        return evaluator;
    }
}
