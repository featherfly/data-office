
package cn.featherfly.data.impl;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.map.ListOrderedMap;

import cn.featherfly.data.core.DataRecord;

/**
 * <p>
 * 简单数据记录
 * </p>
 *
 * @author 钟冀
 */
public class SimpleDataRecord implements DataRecord {

    private ListOrderedMap<String, Object> values = new ListOrderedMap<>();

    /**
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
     * @param key   key
     * @param value value
     */
    public void add(String key, Object value) {
        values.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E> E get(int index) {
        return (E) values.getValue(index);
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
        return (Collection<E>) values.valueList();
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
    @Override
    public Collection<String> getKeys() {
        return values.keySet();
    }

}
