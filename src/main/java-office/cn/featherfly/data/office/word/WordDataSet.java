
package cn.featherfly.data.office.word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import cn.featherfly.data.core.DataSet;

/**
 * <p>
 * Word数据集
 * </p>
 * @param <R>
 *            数据记录
 * @author 钟冀
 */
public class WordDataSet<R> implements DataSet<R> {

    private List<R> records = new ArrayList<>();

    // private XWPFTable table;

    /**
     * @param table
     *            XWPFTable
     * @param mapper
     *            ExcelDataMapper
     */
    public WordDataSet(XWPFTable table, WordDataMapper<R> mapper) {
        // this.table = table;
        int rowNum = 1;
        for (XWPFTableRow row : table.getRows()) {
            records.add(mapper.mapRow(row, rowNum));
            rowNum++;
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
}
