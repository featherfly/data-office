
package cn.featherfly.data.core;

import java.util.Collection;

/**
 * 数据集.
 *
 * @param <R>
 *        数据记录
 * @author 钟冀
 */
public interface DataSet<R> {

    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();

    /**
     * <p>
     * 当前dataSet的索引
     * </p>
     *
     * @return 索引
     */
    int getIndex();

    /**
     * <p>
     * 获取全部数据记录
     * </p>
     *
     * @return 全部数据记录
     */
    Collection<R> getDataRecords();

    /**
     * <p>
     * 添加数据记录
     * </p>
     *
     * @param record 数据记录
     * @param <D> this.class
     * @return this
     */
    <D extends DataSet<R>> D addRecord(R record);

    /**
     * <p>
     * 批量添加数据记录
     * </p>
     *
     * @param records 数据记录数组
     * @param <D> this.class
     * @return this
     */
    <D extends DataSet<R>> D addRecord(@SuppressWarnings("unchecked") R... records);

    /**
     * <p>
     * 批量添加数据记录
     * </p>
     *
     * @param records 数据记录集合
     * @param <D> this.class
     * @return this
     */
    <D extends DataSet<R>> D addRecords(Collection<R> records);

    /**
     * <p>
     * 获取数据记录数量
     * </p>
     *
     * @return 数据记录数量
     */
    int getDataRecordsNumber();

    /**
     * <p>
     * 获取指定索引数据记录
     * </p>
     *
     * @param index
     *        索引
     * @return 数据记录
     */
    R getDataRecord(int index);
}
