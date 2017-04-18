
package cn.featherfly.data.office.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.impl.SimpleDataRecord;

/**
 * <p>
 * T 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class ExcelDataSourceWriteTest {

    /**
     * <p>
     * 方法的说明
     * </p>
     * @param args args
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        ExcelDataSource<DataRecord> source = new ExcelDataSource<DataRecord>(
                new File(ExcelDataSourceWriteTest.class.getResource("2.xlsx").getPath()),
                new ExcelDataRecordMapper());
//        ExcelDataSource<DataRecord> source = new ExcelDataSource<DataRecord>(
//                new XSSFWorkbook(),
//                new ExcelDataRecordMapper());
        SimpleDataRecord record = new SimpleDataRecord();
        record.add("name", "羽飞");
        record.add("age", 18);
        source.getDataSet(0).addRecord(record).addRecord(record);
        source.getDataSet(1).addRecord(record).addRecord(record);
        source.addDataSet()
            .addRecord(record);
//        source.addDataSet()
//            .addRecord(record);
        source.save(new FileOutputStream(new File("2.write.xlsx")));
        
        
        ExcelDataSource<User> source2 = new ExcelDataSource<User>(
                new XSSFWorkbook(),
                new ExcelSwaggerModelMapper<User>());
        User user = new User();
        user.setName("羽飞");
        user.setAge(20);
        source2.addDataSet().addRecord(user).addRecord(user);
//        source.addDataSet()
//            .addRecord(record);
        source2.save(new FileOutputStream(new File("swagger.write.xlsx")));
    }
}
