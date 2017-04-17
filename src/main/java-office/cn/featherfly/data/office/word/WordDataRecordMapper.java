
package cn.featherfly.data.office.word;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.impl.SimpleDataRecord;

/**
 * <p>
 * ExcelDataMapper
 * </p>
 * 
 * @author 钟冀
 */
public class WordDataRecordMapper implements WordDataMapper<DataRecord> {

    /**
     */
    public WordDataRecordMapper() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataRecord mapRow(XWPFTableRow row, int rowNum) {
        SimpleDataRecord record = new SimpleDataRecord();
        for (int i = 0; i < row.getTableCells().size(); i++) {
            XWPFTableCell cell = row.getTableCells().get(i);            
            record.add(i + "", cell.getText());
        }
        return record;
    }
}
