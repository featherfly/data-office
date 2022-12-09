
package cn.featherfly.data.office.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.NumberUtils;
import cn.featherfly.common.lang.reflect.ClassType;

/**
 * <p>
 * ExcelSwaggerModelMapper
 * </p>
 *
 * @param <R> 要映射的具体类
 * @author 钟冀
 */
public class ExcelObjectMapper<R> extends ExcelDataMapper<R> {

    private Map<Integer, String> columnPropertyNameMap;

    private List<String> titles;

    private Class<R> type;

    /**
     * @param type Record Type Class
     */
    public ExcelObjectMapper(Class<R> type) {
        this(type, new HashMap<>(), new ArrayList<>());
    }

    /**
     * @param type                  Record Type Class
     * @param columnPropertyNameMap columnPropertyNameMap
     */
    public ExcelObjectMapper(Class<R> type, Map<Integer, String> columnPropertyNameMap) {
        this(type, columnPropertyNameMap, new ArrayList<>());
    }

    /**
     * @param type                  Record Type Class
     * @param columnPropertyNameMap columnPropertyNameMap
     * @param titles                titles
     */
    public ExcelObjectMapper(Class<R> type, Map<Integer, String> columnPropertyNameMap, List<String> titles) {
        this.type = type;
        this.columnPropertyNameMap = columnPropertyNameMap;
        this.titles = titles;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public R mapRecord(Row row, int rowNum) {
        R record = ClassUtils.newInstance(type);
        if (row != null) {
            FormulaEvaluator evaluator = getFormulaEvaluator(row.getSheet().getWorkbook());
            BeanDescriptor<R> bd = BeanDescriptor.getBeanDescriptor(type);
            for (Entry<Integer, String> columnPropertyName : columnPropertyNameMap.entrySet()) {
                Cell cell = row.getCell(columnPropertyName.getKey());
                if (cell != null) {
                    Object value = getCellValue(cell, evaluator);
                    Class<?> propertyType = bd.getBeanProperty(columnPropertyName.getValue()).getType();
                    if (value.getClass() != propertyType) {
                        if (value instanceof Number) {
                            value = NumberUtils.convert((Number) value, (Class<Number>) propertyType);
                        } else {
                            value = conversion.targetToSource(value.toString(), new ClassType<>(propertyType));
                        }
                    }
                    BeanUtils.setProperty(record, columnPropertyName.getValue(), value);
                } else {
                    logger.debug("cell of column[{}] is null", columnPropertyName.getKey());
                }
            }
        }
        return record;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillData(Row row, R record, int rowNum) {
        if (Lang.isNotEmpty(titles) && rowNum == 0) {
            for (int i = 0; i < titles.size(); i++) {
                String title = titles.get(i);
                Cell cell = row.createCell(i);
                setCellValue(title, cell);
            }
            row = row.getSheet().createRow(rowNum + 1);
        }

        if (record != null) {
            for (Entry<Integer, String> columnPropertyName : columnPropertyNameMap.entrySet()) {
                Object value = BeanUtils.getProperty(record, columnPropertyName.getValue());
                Cell cell = row.createCell(columnPropertyName.getKey());
                setCellValue(value, cell);
            }
        }
    }

    /**
     * 返回columnPropertyNameMap
     *
     * @return columnPropertyNameMap
     */
    public Map<Integer, String> getColumnPropertyNameMap() {
        return columnPropertyNameMap;
    }

    /**
     * 设置columnPropertyNameMap
     *
     * @param columnPropertyNameMap columnPropertyNameMap
     */
    public void setColumnPropertyNameMap(Map<Integer, String> columnPropertyNameMap) {
        this.columnPropertyNameMap = columnPropertyNameMap;
    }

    /**
     * 返回type
     *
     * @return type
     */
    public Class<R> getType() {
        return type;
    }

    /**
     * 返回titles
     *
     * @return titles
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     * 设置titles
     *
     * @param titles titles
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
