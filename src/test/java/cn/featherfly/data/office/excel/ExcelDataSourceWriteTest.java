
package cn.featherfly.data.office.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.featherfly.common.structure.HashChainMap;
import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.impl.SimpleDataRecord;

/**
 * <p>
 * ExcelDataSourceWriteTest
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
        writeDataRecord();
        writeObject();
        writeSwaggerModel();
    }
    
    private static void writeObject() throws IOException {
        System.out.println("**************************************************");
        System.out.println("writeObject");
        System.out.println("**************************************************");
        
        ExcelDataSource<User> source = new ExcelDataSource<User>(
                new XSSFWorkbook(),
                new ExcelObjectMapper<User>(User.class, new HashChainMap<Integer, String>()
                        .putChain(0, "name")
                        .putChain(1, "age")));
        User user = new User();
        user.setName("羽飞2");
        user.setAge(22);
        source.addDataSet().addRecord(user).addRecord(user);
        source.save(new FileOutputStream(new File("write.object.xlsx")));
    }
    
    private static void writeSwaggerModel() throws IOException {
        System.out.println("**************************************************");
        System.out.println("writeSwaggerModel");
        System.out.println("**************************************************");
        
        ExcelDataSource<User> source = new ExcelDataSource<User>(
                new XSSFWorkbook(),
                new ExcelSwaggerModelMapper<User>());
        User user = new User();
        user.setName("羽飞");
        user.setAge(20);
        User user2 = new User();
        user2.setName("yufei");
        user2.setAge(18);
        source.addDataSet().addRecord(user).addRecord(user2);
        source.save(new FileOutputStream(new File("write.swagger.xlsx")));
    }
    
    private static void writeDataRecord() throws IOException {
        System.out.println("**************************************************");
        System.out.println("writeDataRecord");
        System.out.println("**************************************************");
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
        source.save(new FileOutputStream(new File("write.2.xlsx")));
    }
}
