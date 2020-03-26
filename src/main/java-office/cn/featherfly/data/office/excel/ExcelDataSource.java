
package cn.featherfly.data.office.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.data.office.OfficeDataSource;

/**
 * <p>
 * Excel数据源.支持2003,2007
 * </p>
 *
 * @param <R> Record
 * @author 钟冀
 */
public class ExcelDataSource<R> implements OfficeDataSource<ExcelDataSet<R>, R> {

    private List<ExcelDataSet<R>> dataSets;

    private Workbook workbook;

    private ExcelDataMapper<R> mapper;

    private FormulaEvaluator evaluator;

    /**
     * @param workbook Workbook
     * @param mapper   ExcelDataMapper
     */
    public ExcelDataSource(Workbook workbook, ExcelDataMapper<R> mapper) {
        if (workbook == null) {
            throw new IllegalArgumentException("workbook 不能为空");
        }
        this.workbook = workbook;
        this.mapper = mapper;
        init(workbook, mapper);
    }

    /**
     * @param is     file input stream
     * @param mapper ExcelDataMapper
     * @throws IOException IOException
     */
    public ExcelDataSource(InputStream is, ExcelDataMapper<R> mapper) throws IOException {
        AssertIllegalArgument.isNotEmpty(is, "file");
        this.mapper = mapper;
        try {
            workbook = new XSSFWorkbook(is);
            init(workbook, mapper);
        } catch (POIXMLException e) {
            workbook = new HSSFWorkbook(is);
            init(workbook, mapper);
        }
    }

    /**
     * @param file   文件
     * @param mapper ExcelDataMapper
     * @throws IOException IOException
     */
    public ExcelDataSource(File file, ExcelDataMapper<R> mapper) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("file 为空或文件不存在");
        }
        this.mapper = mapper;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(file));
            init(workbook, mapper);
        } catch (POIXMLException e) {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            init(workbook, mapper);
        }
    }

    private void init(Workbook workbook, ExcelDataMapper<R> mapper) {
        int sheetNumber = workbook.getNumberOfSheets();
        dataSets = new ArrayList<>(sheetNumber);
        if (workbook instanceof XSSFWorkbook) {
            evaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
        } else if (workbook instanceof SXSSFWorkbook) {
            evaluator = new SXSSFFormulaEvaluator((SXSSFWorkbook) workbook);
        } else {
            evaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
        }
        for (int i = 0; i < sheetNumber; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            dataSets.add(new ExcelDataSet<>(sheet, evaluator, mapper));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExcelDataSet<R> getDataSet(int index) {
        return dataSets.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ExcelDataSet<R>> getDataSets() {
        return dataSets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDataSetsNumber() {
        return dataSets.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExcelDataSet<R> addDataSet() {
        Sheet sheet = workbook.createSheet();
        ExcelDataSet<R> dataSet = new ExcelDataSet<>(sheet, evaluator, mapper);
        dataSets.add(dataSet);
        return dataSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OutputStream outputStream) throws IOException {
        workbook.write(outputStream);
    }
}
