
package cn.featherfly.data.office.excel;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.structure.HashChainMap;
import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.core.DataSet;

/**
 * <p>
 * ExcelDataSourceReadTest
 * </p>
 *
 * @author 钟冀
 */
public class ExcelDataSourceReadTest {

//    @SuppressWarnings("resource")
//    private ExcelDataSourceReadTest(File file) throws IOException {
//        Workbook w;
//        try {
//            w = new XSSFWorkbook(new FileInputStream(file));
//        } catch (POIXMLException e) {
//            w = new HSSFWorkbook(new FileInputStream(file));
//        }
//        System.out.println(file.getName());
//        System.out.println(w.getNumberOfSheets());
//        System.out.println(w.getSheetAt(0).getLastRowNum());
//        System.out.println(w.getSheetAt(0).getRow(0) == null);
//        System.out.println(w.getSheetAt(0).getRow(0).getLastCellNum());
//    }

    private static void showReadDataRecord() throws IOException {
        System.out.println("**************************************************");
        System.out.println("showReadDataRecord");
        System.out.println("**************************************************");
        ExcelDataSource<DataRecord> source = new ExcelDataSource<DataRecord>(new File(ExcelDataSourceReadTest.class.getResource("1.xlsx").getPath()),
                new ExcelRecordMapper());
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
//                for (Object value : record.getValues()) {
//                    System.out.print("\tcell " + vIndex + " : " + value);
//                    vIndex++;
//                }
                System.out.println();
                rIndex++;
            }
            dsIndex++;
        }
    }
    
    private static void showReadObject() throws IOException {
        System.out.println("**************************************************");
        System.out.println("showReadObject");
        System.out.println("**************************************************");
        Map<Integer, String> columnPropertyNameMap = new HashChainMap<Integer, String>()
                .putChain(0, "name")
                .putChain(1, "age");
        ExcelDataSource<User> source = new ExcelDataSource<User>(
                new File(ExcelDataSourceReadTest.class.getResource("object.xlsx").getPath())
                , new ExcelObjectMapper<>(User.class, columnPropertyNameMap));
        int dsIndex = 0;
        for (DataSet<User> dataSet : source.getDataSets()) {
            int rIndex = 0;
            System.out.println("sheet " + dsIndex);
            for (User record : dataSet.getDataRecords()) {
                System.out.println(" row " + rIndex);
                for (Entry<Integer, String> entry : columnPropertyNameMap.entrySet()) {
                    System.out.print("\tcell " + entry.getKey() + " : " + BeanUtils.getProperty(record, entry.getValue()));                    
                }
                System.out.println();
                rIndex++;
            }
            dsIndex++;
        }
    }
    
    private static void showReadTemplate() throws IOException, InvalidFormatException {
        System.out.println("**************************************************");
        System.out.println("showReadTemplate");
        System.out.println("**************************************************");
        
        try (XSSFWorkbook t = new XSSFWorkbook(new File(ExcelDataSourceReadTest.class.getResource("sheet_template.xlsx").getPath()))) {
            ExcelSheetTemplateMapper<User> mapper = new ExcelSheetTemplateMapper<User>(User.class, t.getSheetAt(1));
            try (XSSFWorkbook workbook = new XSSFWorkbook(new File(ExcelDataSourceReadTest.class.getResource("sheet_template_data.xlsx").getPath()))) {
                ExcelDataSource<User> source = new ExcelDataSource<User>(
                        workbook
                        , mapper);
                int dsIndex = 0;
                for (DataSet<User> dataSet : source.getDataSets()) {
                    int rIndex = 0;
                    System.out.println("sheet " + dsIndex);
                    for (User record : dataSet.getDataRecords()) {
                        System.out.println(" row " + rIndex);
                        for (Entry<Integer, String> entry : mapper.getColumnPropertyNameMap().entrySet()) {
                            System.out.print("\tcell " + entry.getKey() + " : " + BeanUtils.getProperty(record, entry.getValue()));                    
                        }
                        System.out.println();
                        System.out.println("\tname : " + record.getName() +  "\tage : " + record.getAge());                    
                        rIndex++;
                    }
                    dsIndex++;
                }
            }
        }
    }

    /**
     * <p>
     * 方法的说明
     * </p>
     * @param args args
     * @throws IOException IOException
     */
    public static void main(String[] args) throws Exception {
        // new T(new File(T.class.getResource("1.xlsx").getPath()));
        // new T(new File(T.class.getResource("2.xlsx").getPath()));
        // new T(new File(T.class.getResource("3.xlsx").getPath()));
        // new T(new File(T.class.getResource("3.xls").getPath()));

        showReadDataRecord();
        
        showReadObject();
        
        showReadTemplate();
        
        
        // HSSFWorkbook wb = new HSSFWorkbook(null);
        // HSSFSheet sheet = wb.getSheetAt(0);
        // HSSFRow row = sheet.getRow(0);
        // HSSFCell cell = row.getCell(0);
        // List<Map<String, String>> cellParam = new ArrayList<Map<String,
        // String>>();
        // int cellIndex = 0;
        // int cellLength = cellIndex;
        // while (cellIndex >= 0) {
        // cell = row.getCell(cellIndex);
        // if (cell == null) {
        // break;
        // }
        // if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
        // cell.getRichStringCellValue().getString();
        // Map<String, String> param = buildConfig(cell
        // .getRichStringCellValue().getString());
        // cellParam.add(param);
        // cell.setCellValue(new HSSFRichTextString(param.get("head")));
        // } else {
        // throw new RuntimeException(
        // "excel template head type is wrong -- must be string");
        // }
        // cellLength = ++cellIndex;
        // }
        // for (Object data : datasource) {
        // row = sheet.createRow(sheet.getLastRowNum() + 1);
        // if (data instanceof Map) {
        // Map<?, ?> md = (Map<?, ?>) data;
        // for (int index = 0; index < cellLength; index++) {
        // Map<String, String> param = cellParam.get(index);
        // Object od = md.get(param.get("property"));
        // String cellValue = converter.convert(od);
        // row.createCell(index).setCellValue(new
        // HSSFRichTextString(cellValue));
        // }
        // } else if (data instanceof Object[]) {
        // Object[] md = (Object[]) data;
        // for (int index = 0; index < md.length; index++) {
        // Object od = md[index];
        // String cellValue = converter.convert(od);
        // row.createCell(index).setCellValue(new
        // HSSFRichTextString(cellValue));
        // }
        // } else {
        // for (int index = 0; index < cellLength; index++) {
        // Map<String, String> param = cellParam.get(index);
        // String propertyName = param.get("property");
        // BeanDescriptor<?> beanDescriptor = BeanDescriptor
        // .getBeanDescriptor(data.getClass());
        // BeanProperty beanProperty = beanDescriptor
        // .getChildBeanProperty(propertyName);
        // LOG.debug("propertyName: " + beanProperty.getName());
        // Dictionary dictionary = beanProperty.getAnnotation(Dictionary.class);
        // Object od = BeanUtils.getProperty(data, propertyName);
        // if (dictionary != null) {
        // Object dataValue = od;
        // od = "";
        // if (dataValue != null) {
        // DictionaryOption option = dictionaryTableService
        // .getOption(dictionary.table()
        // , dictionary.field()
        // , Integer.parseInt(
        // dataValue.toString()));
        // if (option != null) {
        // od = option.getOptionText();
        // }
        // }
        // }
        // DateFormat formate = beanProperty.getAnnotation(DateFormat.class);
        // Map<String, String> config = null;
        // if (formate != null) {
        // config = new HashMap<String, String>();
        // config.put("date.formate", formate.value());
        // }
        // putValuesToCell(od, row, index, converter, config);
        // }
        // }
        // }
    }
}
