
package cn.featherfly.data.core;

import java.util.Collection;

/**
 * <p>
 * 数据记录
 * </p>
 * 
 * @author 钟冀
 */
public interface DataRecord {
    /**
     * <p>
     * 获取指定索引数据
     * </p>
     * 
     * @param index
     *            索引
     * @param <E>
     *            数据记录中的数据类型
     * @return 指定索引对象
     */
    <E> E get(int index);

    /**
     * <p>
     * 获取指定key数据
     * </p>
     * 
     * @param key
     *            key
     * @param <E> 数据记录中的数据类型
     * @return 指定key对象
     */
    <E> E get(String key);

    /**
     * <p>
     * 获取全部数据
     * </p>
     * 
     * @param <E> 数据记录中的数据类型
     * 
     * @return 全部数据
     */
    <E> Collection<E> getValues();
    
    /**
     * <p>
     * 获取全部key
     * </p>
     * 
     * @return 全部key
     */
    Collection<String> getKeys();

    /**
     * <p>
     * 获取数据数量
     * </p>
     * 
     * @return 数据数量
     */
    int getValuesNumber();
}
