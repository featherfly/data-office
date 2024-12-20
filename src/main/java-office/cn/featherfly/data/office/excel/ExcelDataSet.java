
package cn.featherfly.data.office.excel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.data.core.DataSet;

/**
 * Excel数据集.
 *
 * @param <R> 数据记录
 * @author 钟冀
 */
public class ExcelDataSet<R> implements DataSet<R> {

    private final List<R> records = new ArrayList<>();

    private final int index;

    private final String name;

    private final Sheet sheet;

    private final ExcelDataMapper<R> mapper;

    /**
     * @param sheet Sheet
     * @param evaluator FormulaEvaluator
     * @param mapper ExcelDataMapper
     */
    public ExcelDataSet(Sheet sheet, FormulaEvaluator evaluator, ExcelDataMapper<R> mapper) {
        // this.sheet = sheet;
        if (sheet == null) {
            throw new IllegalArgumentException("sheet 不能为空");
        }
        if (evaluator == null) {
            throw new IllegalArgumentException("evaluator 不能为空");
        }
        index = sheet.getWorkbook().getSheetIndex(sheet);
        name = sheet.getSheetName();
        this.sheet = sheet;
        this.mapper = mapper;
        init(sheet, evaluator, mapper);
    }

    private void init(Sheet sheet, FormulaEvaluator evaluator, ExcelDataMapper<R> mapper) {
        int rowNum = sheet.getLastRowNum() + 1;
        for (int rIndex = 0; rIndex < rowNum; rIndex++) {
            Row row = sheet.getRow(rIndex);
            if (row != null) {
                records.add(mapper.mapRecord(row, rowNum));
            }
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
        Row row = null;
        if (sheet.getLastRowNum() == -1) {
            row = sheet.createRow(sheet.getLastRowNum() + 1);
        } else {
            row = sheet.getRow(sheet.getLastRowNum());
            if (row == null) {
                row = sheet.createRow(sheet.getLastRowNum());
            } else {
                row = sheet.createRow(sheet.getLastRowNum() + 1);
            }
        }
        mapper.fillData(row, record, row.getRowNum());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
}
