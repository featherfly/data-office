
package cn.featherfly.data.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.featherfly.data.core.DataSet;
import cn.featherfly.data.core.DataSource;

/**
 * <p>
 * 简单数据源.
 * </p>
 *
 * @param <D>
 *            DataSet
 * @param <R>
 *            Record
 * @author 钟冀
 */
public class SimpleDataSource<D extends DataSet<R>, R> implements DataSource<D, R> {

    private List<D> dataSets = new ArrayList<>();

    /**
     */
    public SimpleDataSource() {
    }

    /**
     * @param dataSets
     *            数据集集合
     */
    public SimpleDataSource(Collection<D> dataSets) {
        this.dataSets.addAll(dataSets);
    }

    /**
     * <p>
     * 添加数据集
     * </p>
     * 
     * @param record
     *            数据集
     */
    public void addDataSet(D record) {
        dataSets.add(record);
    }

    /**
     * <p>
     * 添加数据集（复数）
     * </p>
     * 
     * @param dataSets
     *            数据集可变参数
     */
    public void addDataSets(@SuppressWarnings("unchecked") D... dataSets) {
        if (dataSets != null) {
            for (D record : dataSets) {
                addDataSet(record);
            }
        }
    }

    /**
     * <p>
     * 添加数据集（复数）
     * </p>
     * 
     * @param dataSets
     *            数据集集合
     */
    public void addDataSets(Collection<D> dataSets) {
        if (dataSets != null) {
            this.dataSets.addAll(dataSets);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D getDataSet(int index) {
        return dataSets.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<D> getDataSets() {
        return dataSets;
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
    public D addDataSet() {
        throw new UnsupportedOperationException();
    }
}
