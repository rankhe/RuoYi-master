package com.ruoyi.web.controller.product.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProdInfoExcelVO1 implements Serializable
{
    /** $column.columnComment */
    private Long id;

    /** 产品型号 */
    @Excel(name = "型号")
    private String name;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brandName;

    /** 产品分类 */
    @Excel(name = "产品分类")
    private Long categoryName;

    /** 批次ID */
    @Excel(name = "批次ID")
    private String batchNo;

    /** 细分类别1数量 */
    @Excel(name = "子类1数量")
    private Long t1Cnt;

    /** 细分类别2数量 */
    @Excel(name = "子类2数量")
    private Long t2Cnt;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 细分类别1 */
    @Excel(name = "子类1")
    private String subtype1;

    /** 细分类别2 */
    @Excel(name = "子类2")
    private String subtype2;

}
