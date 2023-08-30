package com.ruoyi.system.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ProductInfo
{
    // 商品Id
    private Long prodId;
    // 分类ID
    private Long  categoryId;

    private Long batchId;

    private String  name;

    private Integer t1Cnt;

    private Integer t2Cnt;

    private Long price;

    private Date createTime;
}
