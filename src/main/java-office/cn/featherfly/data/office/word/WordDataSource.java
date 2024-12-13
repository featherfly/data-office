
package cn.featherfly.data.office.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import cn.featherfly.data.office.OfficeDataSource;

/**
 * Word数据源. 只支持2007以及以后的格式，不支持2003.
 *
 * @param <R>
 *        要映射的具体类
 * @author 钟冀
 */
public class WordDataSource<R> implements OfficeDataSource<WordDataSet<R>, R> {

    private List<WordDataSet<R>> dataSets;

    private XWPFDocument document;

    private WordDataMapper<R> mapper;

    /**
     * @param document
     *        XWPFDocument
     * @param mapper
     *        ExcelDataMapper
     */
    public WordDataSource(XWPFDocument document, WordDataMapper<R> mapper) {
        if (document == null) {
            throw new RuntimeException("document 不能为空");
        }
        this.document = document;
        this.mapper = mapper;
        dataSets = new ArrayList<>(document.getTables().size());
        for (XWPFTable table : document.getTables()) {
            dataSets.add(new WordDataSet<>(table, dataSets.size(), mapper));
        }
        // this.document = document;
    }

    /**
     * @param is
     *        输入流
     * @param mapper
     *        ExcelDataMapper
     * @throws IOException
     *         IOException
     */
    public WordDataSource(InputStream is, WordDataMapper<R> mapper) throws IOException {
        this(new XWPFDocument(is), mapper);
        is.close();
    }

    /**
     * @param file
     *        文件
     * @param mapper
     *        ExcelDataMapper
     * @throws IOException
     *         IOException
     */
    public WordDataSource(File file, WordDataMapper<R> mapper) throws IOException {
        this(new FileInputStream(file), mapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordDataSet<R> getDataSet(int index) {
        return dataSets.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordDataSet<R> getDataSet(String name) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<WordDataSet<R>> getDataSets() {
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
    public WordDataSet<R> addDataSet(String name) {
        XWPFTable table = document.createTable();
        WordDataSet<R> wordDataSet = new WordDataSet<>(table, dataSets.size() + 1, mapper);
        dataSets.add(wordDataSet);
        return wordDataSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OutputStream outputStream) throws IOException {
        document.write(outputStream);
    }

}
