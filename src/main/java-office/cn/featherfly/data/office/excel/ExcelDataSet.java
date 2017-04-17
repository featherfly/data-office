
package cn.featherfly.data.office.excel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.featherfly.data.core.DataSet;

/**
 * <p>
 * Excel数据集
 * </p>
 * 
 * @param <R>
 *            数据记录
 *
 * @author 钟冀
 */
public class ExcelDataSet<R> implements DataSet<R> {

    private List<R> records = new ArrayList<>();

    /**
     * @param sheet
     *            Sheet
     * @param evaluator
     *            FormulaEvaluator
     * @param mapper
     *            ExcelDataMapper
     */
    public ExcelDataSet(Sheet sheet, FormulaEvaluator evaluator, ExcelDataMapper<R> mapper) {
        // this.sheet = sheet;
        if (sheet == null) {
            throw new IllegalArgumentException("sheet 不能为空");
        }
        if (evaluator == null) {
            throw new IllegalArgumentException("evaluator 不能为空");
        }
        init(sheet, evaluator, mapper);
    }

    private void init(Sheet sheet, FormulaEvaluator evaluator, ExcelDataMapper<R> mapper) {
        int rowNum = sheet.getLastRowNum() + 1;
        for (int rIndex = 0; rIndex < rowNum; rIndex++) {
            Row row = sheet.getRow(rIndex);
            if (row != null) {
                records.add(mapper.mapRow(row, rowNum));
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
}
