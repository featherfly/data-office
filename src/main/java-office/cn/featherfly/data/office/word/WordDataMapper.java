
package cn.featherfly.data.office.word;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import cn.featherfly.common.lang.reflect.ClassType;
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
public abstract class WordDataMapper<R> implements DataMapper<R, XWPFTableRow> {

    private ToStringConversion conversion = new ToStringTypeConversion(
            ToStringConversionPolicys.getBasicConversionPolicy());

    /**
     * <p>
     * 设置Cell值
     * </p>
     *
     * @param value value
     * @param cell  cell
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void setCellValue(Object value, XWPFTableCell cell) {
        if (value != null) {
            cell.setText(conversion.sourceToTarget(value, new ClassType(value.getClass())));
        }
    }

}
