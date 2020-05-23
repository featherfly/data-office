
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
import cn.featherfly.conversion.string.ToStringConversion;
import cn.featherfly.conversion.string.ToStringConversionPolicys;
import cn.featherfly.conversion.string.ToStringTypeConversion;
import cn.featherfly.data.core.DataMapper;

/**
 * <p>
 * ExcelDataMapper
 * </p>
 *
 * @param <R> 要映射的具体类
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
    protected ToStringConversion conversion = new ToStringTypeConversion(
            ToStringConversionPolicys.getBasicConversionPolicy());

    /**
     * <p>
     * 设置值到Cell里
     * </p>
     *
     * @param value 值
     * @param cell  cell
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void setCellValue(Object value, Cell cell) {
        if (value != null) {
            cell.setCellValue(conversion.sourceToTarget(value, new GenericClass(value.getClass())));
        }
    }

    /**
     * <p>
     * 返回cell的值
     * </p>
     *
     * @param cell      cell
     * @param evaluator FormulaEvaluator
     * @return cell的值
     */
    protected Object getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) {
            return null;
        }

        Object value = null;

        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case BLANK:
                break;
            case ERROR:
                break;
            case FORMULA:
                CellValue cellValue = evaluator.evaluate(cell);
                switch (cellValue.getCellType()) {
                    case STRING:
                        value = cellValue.getStringValue();
                        break;
                    case NUMERIC:
                        value = cellValue.getNumberValue();
                        break;
                    case BOOLEAN:
                        value = cellValue.getBooleanValue();
                        break;
                    case BLANK:
                        break;
                    case ERROR:
                        break;
                    // CELL_TYPE_FORMULA will never happen
                    case FORMULA:
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
     *
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
