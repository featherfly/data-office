package cn.featherfly.data.office.excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "", value = "statistics.OrderGoodsSummary")
public class OrderGoodsSummary {

    private String name = null;

    private Integer num = null;

    private Float price = null;

    private String spec = null;

    private String flavor = null;

    /**
     * get 名称
     *
     * @return 名称
     **/
    @ApiModelProperty(value = "名称")
    public String getName() {
        return name;
    }

    /**
     * set 名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get 数量
     *
     * @return 数量
     **/
    @ApiModelProperty(value = "数量")

    public Integer getNum() {
        return num;
    }

    /**
     * set 数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * get 金额
     *
     * @return 金额
     **/
    @ApiModelProperty(value = "金额")

    public Float getPrice() {
        return price;
    }

    /**
     * set 金额
     *
     * @param price 金额
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * get 规格
     *
     * @return 规格
     **/
    @ApiModelProperty(value = "规格")

    public String getSpec() {
        return spec;
    }

    /**
     * set 规格
     *
     * @param spec 规格
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * get 口味
     *
     * @return 口味
     **/
    @ApiModelProperty(value = "口味")

    public String getFlavor() {
        return flavor;
    }

    /**
     * set 口味
     *
     * @param flavor 口味
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderGoodsSummary {\n");

        sb.append("  name: ").append(name).append("\n");
        sb.append("  num: ").append(num).append("\n");
        sb.append("  price: ").append(price).append("\n");
        sb.append("  spec: ").append(spec).append("\n");
        sb.append("  flavor: ").append(flavor).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
