
package cn.featherfly.data.office.excel;

import org.apache.poi.ss.usermodel.Row;

import cn.featherfly.data.core.DataMapper;

/**
 * <p>
 * ExcelDataMapper
 * </p>
 * 
 * @param <R> 要映射的具体类
 * 
 * @author 钟冀
 */
public interface ExcelDataMapper<R> extends DataMapper<R, Row>{
	

}
