
package cn.featherfly.data.office;

import java.io.IOException;
import java.io.OutputStream;

import cn.featherfly.data.core.DataSet;
import cn.featherfly.data.core.DataSource;

/**
 * <p>
 * OfficeDataSource
 * </p>
 * 
 * @param <D>
 *            DataSet
 * @param <R>
 *            Record
 * @author 钟冀
 */
public interface OfficeDataSource<D extends DataSet<R>, R> extends DataSource<D, R>{
    /**
     * <p>
     * 保存当前状态
     * </p>
     * 
     * @param outputStream
     *            OutputStream
     * @throws IOException IOException
     */
    void save(OutputStream outputStream) throws IOException;
}
