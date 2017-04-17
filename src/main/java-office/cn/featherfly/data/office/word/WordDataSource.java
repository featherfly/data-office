
package cn.featherfly.data.office.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import cn.featherfly.data.core.DataSource;

/**
 * <p>
 * Word数据源. 只支持2007以及以后的格式，不支持2003.
 * </p>
 * 
 * @param <R>
 *            要映射的具体类
 *
 * @author 钟冀
 */
public class WordDataSource<R> implements DataSource<WordDataSet<R>, R> {

    private List<WordDataSet<R>> dataSets;

    // public XWPFDocument document;

    /**
     * @param document
     *            XWPFDocument
     * @param mapper
     *            ExcelDataMapper
     */
    public WordDataSource(XWPFDocument document, WordDataMapper<R> mapper) {
        if (document == null) {
            throw new RuntimeException("document 不能为空");
        }
        dataSets = new ArrayList<WordDataSet<R>>(document.getTables().size());
        for (XWPFTable table : document.getTables()) {
            dataSets.add(new WordDataSet<R>(table, mapper));
        }
        // this.document = document;
    }

    /**
     * @param is
     *            输入流
     * @param mapper
     *            ExcelDataMapper
     * @throws IOException
     *             IOException
     */
    public WordDataSource(InputStream is, WordDataMapper<R> mapper) throws IOException {
        this(new XWPFDocument(is), mapper);
        is.close();
    }

    /**
     * @param file
     *            文件
     * @param mapper
     *            ExcelDataMapper
     * @throws IOException
     *             IOException
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

}
