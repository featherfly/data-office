
package cn.featherfly.data.impl;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import cn.featherfly.data.core.DataRecord;

/**
 * <p>
 * 简单数据记录
 * </p>
 *
 * @author 钟冀
 */
public class SimpleDataRecord implements DataRecord {

    private ListOrderedMap values = new ListOrderedMap();

    /**
     * @param values
     */
    public SimpleDataRecord() {
    }

    /**
     * @param values values
     */
    public SimpleDataRecord(Map<String, Object> values) {
        this.values.putAll(values);
    }

    /**
     * <p>
     * 添加记录
     * </p>
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public void add(String key, Object value) {
        this.values.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E> E get(int index) {
        return (E) values.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E> E get(String key) {
        return (E) values.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E> Collection<E> getValues() {
        return values.valueList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValuesNumber() {
        return values.valueList().size();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<String> getKeys() {
        return values.keySet();
    }

}
