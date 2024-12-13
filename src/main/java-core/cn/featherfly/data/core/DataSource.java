
package cn.featherfly.data.core;

import java.util.Collection;

/**
 * 数据源.
 *
 * @param <D>
 *        DataSet
 * @param <R>
 *        Record
 * @author 钟冀
 */
public interface DataSource<D extends DataSet<R>, R> {
    /**
     * 获取指定名称的数据集
     *
     * @param name name
     * @return 指定名称的数据集
     */
    D getDataSet(String name);

    /**
     * <p>
     * 获取指定索引的数据集
     * </p>
     *
     * @param index
     *        索引
     * @return 指定索引的数据集
     */
    D getDataSet(int index);

    /**
     * 添加数据集.
     *
     * @param name the name
     * @return 新添加的数据集
     */
    D addDataSet(String name);

    /**
     * <p>
     * 获取全部数据集
     * </p>
     *
     * @return 全部数据集
     */
    Collection<D> getDataSets();

    /**
     * <p>
     * 获取数据集数量
     * </p>
     *
     * @return 数据集数量
     */
    int getDataSetsNumber();
}
