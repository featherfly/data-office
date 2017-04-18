
package cn.featherfly.data.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.featherfly.data.core.DataSet;

/**
 * <p>
 * 简单数据记录
 * </p>
 * 
 * @param <R>
 *            数据记录
 * @author 钟冀
 */
public class SimpleDataSet<R> implements DataSet<R> {

    private List<R> records = new ArrayList<>();
    
    private int index = -1;

    /**
     */
    public SimpleDataSet() {
    }

    /**
     * @param records
     *            记录集合
     */
    public SimpleDataSet(Collection<R> records) {
        this.records.addAll(records);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <D extends DataSet<R>> D addRecord(R record) {
        records.add(record);
        return (D) this;
    }

    /**
     * <p>
     * 添加记录（复数）
     * </p>
     * 
     * @param records
     *            记录 可变参数
     */
    public void addRecords(@SuppressWarnings("unchecked") R... records) {
        if (records != null) {
            for (R record : records) {
                addRecord(record);
            }
        }
    }

    /**
     * <p>
     * 添加记录（复数）
     * </p>
     * 
     * @param records
     *            记录集合
     */
    public void addRecords(Collection<R> records) {
        if (records != null) {
            this.records.addAll(records);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<R> getDataRecords() {
        return records;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R getDataRecord(int index) {
        return records.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDataRecordsNumber() {
        return records.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex() {
        return index;
    }
}
