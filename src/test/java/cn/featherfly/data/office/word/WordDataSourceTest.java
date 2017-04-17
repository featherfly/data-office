
package cn.featherfly.data.office.word;

import java.io.IOException;
import java.io.InputStream;

import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.core.DataSet;
import cn.featherfly.data.core.DataSource;
import cn.featherfly.data.office.word.WordDataSource;

/**
 * <p>
 * T 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class WordDataSourceTest {
    public static void main(String[] args) throws IOException {
        InputStream is = WordDataSourceTest.class.getResourceAsStream("Word.docx");
        WordDataSource<DataRecord> source = new WordDataSource<DataRecord>(is, new WordDataRecordMapper());
        int dsIndex = 0;
        for (DataSet<DataRecord> dataSet : source.getDataSets()) {
            int rIndex = 0;
            System.out.println("sheet " + dsIndex);
            for (DataRecord record : dataSet.getDataRecords()) {
                int vIndex = 0;
                System.out.println(" row " + rIndex);
                
                for (String key : record.getKeys()) {
                    System.out.print("\tcell " + vIndex + " : " + record.get(key) + " -> key : " + key);
                    vIndex++;
                }
                System.out.println();
                rIndex++;
            }
            dsIndex++;
        }
//        for (DataSet<DataRecord> dataSet : source.getDataSets()) {
//            int rIndex = 0;
//            for (DataRecord record : dataSet.getDataRecords()) {
//                int vIndex = 0;
//                for (Object value : record.getValues()) {
//                    System.out.println(dsIndex + " " + rIndex + " " + vIndex + " : " + value);
//                }
//                rIndex++;
//            }
//            dsIndex++;
//        }
    }
}
