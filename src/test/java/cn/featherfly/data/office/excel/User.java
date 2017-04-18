
package cn.featherfly.data.office.excel;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * User
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public class User {

    private String name;
    
    private Integer age;

    /**
     * 返回name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * @param name name
     */
    @ApiModelProperty(value = "姓名", position = 0)
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回age
     * @return age
     */
    @ApiModelProperty(value = "年龄" , position = 1)
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age
     * @param age age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    
    
}
