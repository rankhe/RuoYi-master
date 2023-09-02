package com.ruoyi.web.controller.product.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProdInfoVO implements Serializable
{
    /** $column.columnComment */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 产品分类id */
    @Excel(name = "产品分类id")
    private Long categoryId;

    /** 产品名称 */
    @Excel(name = "产品类别")
    private String prodCategory;

    /** 批次ID */
    @Excel(name = "批次ID")
    private String batchId;

    /** 细分类别1数量 */
    @Excel(name = "细分类别1数量")
    private Long t1Cnt;

    /** 细分类别2数量 */
    @Excel(name = "细分类别2数量")
    private Long t2Cnt;

    /** 细分类别3数量 */
    @Excel(name = "细分类别3数量")
    private Long t3Cnt;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 细分类别1 */
    @Excel(name = "细分类别1")
    private String subtype1;

    /** 细分类别2 */
    @Excel(name = "细分类别2")
    private String subtype2;

    /** 展示顺序 */
    @Excel(name = "展示顺序")
    private Long idx;

    /** 状态1 正常 */
    @Excel(name = "状态1 正常")
    private Long status;
}