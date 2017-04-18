
package cn.featherfly.data.office.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.featherfly.data.office.OfficeException;

/**
 * <p>
 * ExcelTemplateMapper
 * </p>
 * 
 * @param <R>
 *            要映射的具体类
 * @author 钟冀
 */
public class ExcelSheetTemplateMapper<R> extends ExcelObjectMapper<R> {
    
    /**
     * @param type
     *            Record Type Class
     * @param template
     *            Template Sheet
     */
    public ExcelSheetTemplateMapper(Class<R> type, Sheet template) {
        super(type);
        Row titleRow = template.getRow(0);
        if (titleRow == null) {
            throw new OfficeException("模板sheet第二行（标题描述）");
        }
        for (short i = 0; i < titleRow.getLastCellNum(); i++) {
            Cell cell = titleRow.getCell(i);
            getTitles().add(cell.getStringCellValue());
        }
        Row propertyNameRow = template.getRow(1);
        if (propertyNameRow == null) {
            throw new OfficeException("模板sheet第二行（对象属性描述）为空");
        }
        for (short i = 0; i < propertyNameRow.getLastCellNum(); i++) {
            Cell cell = propertyNameRow.getCell(i);
            getColumnPropertyNameMap().put(new Integer(i), cell.getStringCellValue());
        }
        
    }
}
