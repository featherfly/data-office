
package cn.featherfly.data.office.word;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

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
public abstract class WordDataMapper<R> implements DataMapper<R, XWPFTableRow> {

    @SuppressWarnings("rawtypes")
    private Conversion conversion = new TypeConversion(ConversionPolicysJdk8.getBasicConversionPolicy());

    /**
     * <p>
     * 设置Cell值
     * </p>
     * 
     * @param value
     *            value
     * @param cell
     *            cell
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void setCellValue(Object value, XWPFTableCell cell) {
        if (value != null) {
            cell.setText(conversion.toString(value, new GenericClass(value.getClass())));
        }
    }

}
