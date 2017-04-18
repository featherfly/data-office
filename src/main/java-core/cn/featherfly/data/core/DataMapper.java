package cn.featherfly.data.core;

/**
 * <p>
 * 记录行映射接口
 * </p>
 * @param <R> 要映射的具体类
 * @param <D> 数据来源
 * @author 钟冀
 */
public interface DataMapper<R, D> {
    /**
     * <p>
     * 映射记录到指定的对象
     * </p>
     * @param datasource 数据来源
     * @param rowNum 行数
     * @return 记录映射的对象
     */
    R mapRecord(D datasource, int rowNum);
    /**
     * <p>
     * 把数据填充到指定目标内
     * </p>
     * @param datasource 数据来源
     * @param record 数据记录
     * @param rowNum 行数
     */
    void fillData(D datasource, R record, int rowNum);
}