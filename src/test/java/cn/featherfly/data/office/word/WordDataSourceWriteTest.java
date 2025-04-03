
package cn.featherfly.data.office.word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.impl.SimpleDataRecord;

/**
 * <p>
 * T 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class WordDataSourceWriteTest {
    public static void main(String[] args) throws IOException {
        WordDataSource<DataRecord> source = new WordDataSource<>(
            new XWPFDocument(), new WordDataRecordMapper());
        SimpleDataRecord record = new SimpleDataRecord();
        record.add("name", "yufei");
        record.add("age", 18);

        SimpleDataRecord record2 = new SimpleDataRecord();
        record2.add("name", "yufei222");
        record2.add("age", 22);
        record2.add("sex", "male");

        source.addDataSet("table1").addRecord(record).addRecord(record2);

        source.save(new FileOutputStream(new File("write.recored.docx")));
    }
}
