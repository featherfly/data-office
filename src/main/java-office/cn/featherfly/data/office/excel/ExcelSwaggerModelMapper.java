
package cn.featherfly.data.office.excel;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.policy.AllowPolicy;
import cn.featherfly.common.policy.WhiteListPolicy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ExcelSwaggerModelMapper.
 *
 * @author 钟冀
 * @param <R> 要映射的具体类
 */
public class ExcelSwaggerModelMapper<R> extends ExcelDataMapper<R> {

    private boolean insertTitleRow = true;

    private AllowPolicy<String> allowPolicy;

    /**
     * Instantiates a new excel swagger model mapper.
     */
    public ExcelSwaggerModelMapper() {
        this(true, k -> true);
    }

    /**
     * Instantiates a new excel swagger model mapper.
     *
     * @param allowPolicy the allow policy
     */
    public ExcelSwaggerModelMapper(AllowPolicy<String> allowPolicy) {
        this(true, allowPolicy);
    }

    /**
     * Instantiates a new excel swagger model mapper.
     *
     * @param insertTitleRow insertTitleRow
     * @param allowPolicy the allow policy
     */
    public ExcelSwaggerModelMapper(boolean insertTitleRow, AllowPolicy<String> allowPolicy) {
        this.insertTitleRow = insertTitleRow;
        this.allowPolicy = allowPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R mapRecord(Row row, int rowNum) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillData(Row row, R record, int rowNum) {
        if (record != null) {
            @SuppressWarnings("unchecked")
            BeanDescriptor<R> beanDescriptor = BeanDescriptor.getBeanDescriptor((Class<R>) record.getClass());
            Collection<BeanProperty<?, ?>> beanProperties = getApiModelBeanProperties(beanDescriptor);
            int index = 0;
            if (insertTitleRow && rowNum == 0) {
                CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                // 第一行的时候再把标题添加进去
                if (allowPolicy instanceof WhiteListPolicy) {
                    WhiteListPolicy<String, ?> white = (WhiteListPolicy<String, ?>) allowPolicy;
                    for (String pn : white.getWhiteList()) {
                        index = setCellTitle(getCellTitle(pn, beanDescriptor), row, index, cellStyle);
                    }
                } else {
                    for (BeanProperty<?, ?> beanProperty : beanProperties) {
                        if (allowPolicy.isAllow(beanProperty.getName())) {
                            index = setCellTitle(beanProperty.getAnnotation(ApiModelProperty.class).value(), row, index,
                                cellStyle);
                        }
                    }
                }
                row = row.getSheet().createRow(rowNum + 1);
                index = 0;
            }

            if (allowPolicy instanceof WhiteListPolicy) {
                WhiteListPolicy<String, ?> white = (WhiteListPolicy<String, ?>) allowPolicy;
                for (String pn : white.getWhiteList()) {
                    index = setCellValue(row, index, beanDescriptor.getProperty(record, pn));
                }
            } else {
                for (BeanProperty<?, ?> beanProperty : beanProperties) {
                    if (allowPolicy.isAllow(beanProperty.getName())) {
                        index = setCellValue(row, index, beanProperty.getValue(record));
                    }
                }
            }
        }
    }

    //    private void createTitleRow(Row row, int rowNum, Collection<BeanProperty<?, ?>> beanProperties) {
    //        int index = 0;
    //        if (insertTitleRow && rowNum == 0) {
    //            CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
    //            cellStyle.setAlignment(HorizontalAlignment.CENTER);
    //            // 第一行的时候再把标题添加进去
    //            for (BeanProperty<?, ?> beanProperty : beanProperties) {
    //                if (allowPolicy.isAllow(beanProperty.getName())) {
    //                    ApiModelProperty apiModelProperty = beanProperty.getAnnotation(ApiModelProperty.class);
    //                    Cell cell = row.createCell(index);
    //                    cell.setCellStyle(cellStyle);
    //                    setCellValue(apiModelProperty.value(), cell);
    //                    index++;
    //                }
    //            }
    //            row = row.getSheet().createRow(rowNum + 1);
    //        }
    //    }

    private int setCellValue(Row row, int index, Object value) {
        Cell cell = row.createCell(index);
        setCellValue(value, cell);
        index++;
        return index;
    }

    private String getCellTitle(String name, BeanDescriptor<?> beanDescriptor) {
        if (name.contains(Chars.DOT)) {
            BeanProperty<?,
                ?> beanProperty = beanDescriptor.getBeanProperty(name.substring(0, name.indexOf(Chars.DOT_CHAR)));
            String title = beanProperty.getAnnotation(ApiModelProperty.class).value();
            BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
            if (Lang.isEmpty(title)) {
                title = bd.getAnnotation(ApiModel.class).description();
            }
            return title + getCellTitle(name.substring(name.indexOf(Chars.DOT_CHAR) + 1), bd);
        } else {
            return beanDescriptor.getBeanProperty(name).getAnnotation(ApiModelProperty.class).value();
        }
    }

    private int setCellTitle(String title, Row row, int index, CellStyle cellStyle) {
        Cell cell = row.createCell(index);
        cell.setCellStyle(cellStyle);
        setCellValue(title, cell);
        index++;
        return index;
    }

    /**
     * 返回insertTitleRow.
     *
     * @return insertTitleRow
     */
    public boolean isInsertTitleRow() {
        return insertTitleRow;
    }

    /**
     * 设置insertTitleRow.
     *
     * @param insertTitleRow insertTitleRow
     */
    public void setInsertTitleRow(boolean insertTitleRow) {
        this.insertTitleRow = insertTitleRow;
    }

    private Collection<BeanProperty<?, ?>> getApiModelBeanProperties(BeanDescriptor<?> beanDescriptor) {
        return beanDescriptor.findBeanPropertys(new BeanPropertyAnnotationMatcher(ApiModelProperty.class));
    }
}
