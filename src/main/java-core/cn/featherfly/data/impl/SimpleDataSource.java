
package cn.featherfly.data.impl;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.commons.collections4.map.ListOrderedMap;

import cn.featherfly.data.core.DataSet;
import cn.featherfly.data.core.DataSource;

/**
 * 简单数据源.
 *
 * @author 钟冀
 * @param <D> DataSet
 * @param <R> Record
 */
public class SimpleDataSource<D extends DataSet<R>, R> implements DataSource<D, R> {

    private final ListOrderedMap<String, D> dataSets = new ListOrderedMap<>();

    /**
     * Instantiates a new simple data source.
     */
    public SimpleDataSource() {
    }

    /**
     * Instantiates a new simple data source.
     *
     * @param dataSets 数据集集合
     */
    public SimpleDataSource(LinkedHashMap<String, D> dataSets) {
        this.dataSets.putAll(dataSets);
    }

    /**
     * 添加数据集.
     *
     * @param record
     *        数据集
     */
    public void addDataSet(D record) {
        // TODO 这里设置record index
        dataSets.put(record.getName(), record);
    }

    /**
     * <p>
     * 添加数据集（复数）
     * </p>
     * .
     *
     * @param dataSets 数据集可变参数
     */
    public void addDataSets(@SuppressWarnings("unchecked") D... dataSets) {
        if (dataSets == null) {
            return;
        }
        for (D record : dataSets) {
            addDataSet(record);
        }
    }

    /**
     * 添加数据集（复数）.
     *
     * @param dataSets 数据集集合
     */
    public void addDataSets(Collection<D> dataSets) {
        if (dataSets == null) {
            return;
        }
        for (D record : dataSets) {
            addDataSet(record);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getDataSet(int index) {
        return dataSets.getValue(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getDataSet(String name) {
        return dataSets.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<D> getDataSets() {
        return dataSets.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDataSetsNumber() {
        return dataSets.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D addDataSet(String name) {
        throw new UnsupportedOperationException();
    }
}
