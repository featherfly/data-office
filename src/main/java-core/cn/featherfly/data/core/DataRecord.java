
package cn.featherfly.data.core;

import java.util.Collection;

/**
 * 数据记录.
 *
 * @author 钟冀
 */
public interface DataRecord {
    /**
     * 获取指定索引数据.
     *
     * @param index
     *        索引
     * @param <E>
     *        数据记录中的数据类型
     * @return 指定索引对象
     */
    <E> E get(int index);

    /**
     * 获取指定key数据.
     *
     * @param key
     *        key
     * @param <E> 数据记录中的数据类型
     * @return 指定key对象
     */
    <E> E get(String key);

    /**
     * 获取全部数据.
     *
     * @param <E> 数据记录中的数据类型
     * @return 全部数据
     */
    <E> Collection<E> getValues();

    /**
     * 获取全部key.
     *
     * @return 全部key
     */
    Collection<String> getKeys();

    /**
     * 获取数据数量.
     *
     * @return 数据数量
     */
    int getValuesNumber();
}
