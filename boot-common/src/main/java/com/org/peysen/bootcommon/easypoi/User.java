package com.org.peysen.bootcommon.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Peysen
 * @Date 2020/11/22 15:42
 * @Desc TODO
 */
@Data
@ExcelTarget("users")
public class User implements Serializable {
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "生日", format = "yyyy-MM-dd HH:mm:ss")
    private Date bir;
    @Excel(name = "状态", replace = {"激活_1", "锁定_0"})
    private Integer status;
    @ExcelIgnore()
    private String desc;
}
