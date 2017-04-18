
package cn.featherfly.data.office.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.featherfly.data.core.DataRecord;
import cn.featherfly.data.core.DataSet;
import cn.featherfly.data.core.DataSource;
import cn.featherfly.data.office.excel.ExcelDataSource;

/**
 * <p>
 * T 类的说明放这里
 * </p>
 *
 * @author 钟冀
 */
public class ExcelDataSourceReadTest {

    private ExcelDataSourceReadTest(File file) throws IOException {
        Workbook w;
        try {
            w = new XSSFWorkbook(new FileInputStream(file));
        } catch (POIXMLException e) {
            w = new HSSFWorkbook(new FileInputStream(file));
        }
        System.out.println(file.getName());
        System.out.println(w.getNumberOfSheets());
        System.out.println(w.getSheetAt(0).getLastRowNum());
        System.out.println(w.getSheetAt(0).getRow(0) == null);
        System.out.println(w.getSheetAt(0).getRow(0).getLastCellNum());
    }

    private void t() {
        XSSFWorkbook w = new XSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        wb.getNumberOfSheets();
        wb.getSheetAt(1);
    }

    /**
     * <p>
     * 方法的说明
     * </p>
     * @param args args
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        // new T(new File(T.class.getResource("1.xlsx").getPath()));
        // new T(new File(T.class.getResource("2.xlsx").getPath()));
        // new T(new File(T.class.getResource("3.xlsx").getPath()));
        // new T(new File(T.class.getResource("3.xls").getPath()));

        ExcelDataSource<DataRecord> source = new ExcelDataSource<DataRecord>(new File(ExcelDataSourceReadTest.class.getResource("1.xlsx").getPath()),
                new ExcelDataRecordMapper());
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
