package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品信息对象 prod_info
 * 
 * @author ruoyi
 * @date 2023-09-01
 */
public class ProdInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 产品分类id */
    @Excel(name = "产品分类id")
    private Long categoryId;

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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setBatchId(String batchId) 
    {
        this.batchId = batchId;
    }

    public String getBatchId() 
    {
        return batchId;
    }
    public void setT1Cnt(Long t1Cnt) 
    {
        this.t1Cnt = t1Cnt;
    }

    public Long getT1Cnt() 
    {
        return t1Cnt;
    }
    public void setT2Cnt(Long t2Cnt) 
    {
        this.t2Cnt = t2Cnt;
    }

    public Long getT2Cnt() 
    {
        return t2Cnt;
    }
    public void setT3Cnt(Long t3Cnt) 
    {
        this.t3Cnt = t3Cnt;
    }

    public Long getT3Cnt() 
    {
        return t3Cnt;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setSubtype1(String subtype1) 
    {
        this.subtype1 = subtype1;
    }

    public String getSubtype1() 
    {
        return subtype1;
    }
    public void setSubtype2(String subtype2) 
    {
        this.subtype2 = subtype2;
    }

    public String getSubtype2() 
    {
        return subtype2;
    }
    public void setIdx(Long idx) 
    {
        this.idx = idx;
    }

    public Long getIdx() 
    {
        return idx;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("categoryId", getCategoryId())
            .append("batchId", getBatchId())
            .append("t1Cnt", getT1Cnt())
            .append("t2Cnt", getT2Cnt())
            .append("t3Cnt", getT3Cnt())
            .append("count", getCount())
            .append("subtype1", getSubtype1())
            .append("subtype2", getSubtype2())
            .append("idx", getIdx())
            .append("status", getStatus())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
