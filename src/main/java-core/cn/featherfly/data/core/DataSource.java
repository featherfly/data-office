/*
 *
 */

package cn.featherfly.data.core;

import java.util.Collection;

/**
 * 数据源.
 *
 * @author 钟冀
 * @param <D> DataSet
 * @param <R> Record
 */
public interface DataSource<D extends DataSet<R>, R> {

    /**
     * 获取指定名称的数据集.
     *
     * @param name name
     * @return 指定名称的数据集
     */
    D getDataSet(String name);

    /**
     * 获取指定索引的数据集.
     *
     * @param index 索引
     * @return 指定索引的数据集
     */
    D getDataSet(int index);

    /**
     * 添加数据集.
     *
     * @return 新添加的数据集
     */
    default D addDataSet() {
        return addDataSet(null);
    }

    /**
     * 添加数据集.
     *
     * @param name the name
     * @return 新添加的数据集
     */
    D addDataSet(String name);

    /**
     * 获取全部数据集.
     *
     * @return 全部数据集
     */
    Collection<D> getDataSets();

    /**
     * 获取数据集数量.
     *
     * @return 数据集数量
     */
    int getDataSetsNumber();
}
