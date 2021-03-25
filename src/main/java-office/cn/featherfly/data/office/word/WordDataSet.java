
package cn.featherfly.data.office.word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.data.core.DataSet;

/**
 * <p>
 * Word数据集
 * </p>
 *
 * @param <R> 数据记录
 * @author 钟冀
 */
public class WordDataSet<R> implements DataSet<R> {

    private List<R> records = new ArrayList<>();

    private int index = -1;

    private XWPFTable table;

    private WordDataMapper<R> mapper;

    /**
     * @param table  XWPFTable
     * @param index  index
     * @param mapper ExcelDataMapper
     */
    public WordDataSet(XWPFTable table, int index, WordDataMapper<R> mapper) {
        this.index = index;
        this.table = table;
        this.mapper = mapper;
        int rowNum = 1;
        for (XWPFTableRow row : table.getRows()) {
            records.add(mapper.mapRecord(row, rowNum));
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

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <D extends DataSet<R>> D addRecord(R record) {
        int rowNum = table.getNumberOfRows();
        rowNum++;
        XWPFTableRow row = table.createRow();
        mapper.fillData(row, record, rowNum);
        records.add(record);
        return (D) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <D extends DataSet<R>> D addRecord(R... records) {
        if (Lang.isNotEmpty(records)) {
            for (R record : records) {
                addRecord(record);
            }
        }
        return (D) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <D extends DataSet<R>> D addRecords(Collection<R> records) {
        if (Lang.isNotEmpty(records)) {
            for (R record : records) {
                addRecord(record);
            }
        }
        return (D) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex() {
        return index;
    }
}
