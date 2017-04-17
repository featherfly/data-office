
package cn.featherfly.data.core;

import java.util.Collection;

/**
 * <p>
 * 数据源
 * </p>
 * @param <D> DataSet
 * @param <R> Record
 * @author 钟冀
 */
public interface DataSource<D extends DataSet<R>, R> {
    /**
     * <p>
     * 获取指定索引的数据集
     * </p>
     * 
     * @param index
     *            索引
     * @return 指定索引的数据集
     */
    D getDataSet(int index);

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
