package com.ruoyi.system.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

// 报价历史
@Data
public class ProductQuotationInfo
{
    // 产品批次
    private Long batchId;

    // 产品ID
    private Long prodId;

    // 用户ID
    private Long userId;

    // 报价人昵称
    private String  nickName;

    // 意向购买数量
    private Integer count;

    // 价格
    private Long price;

    // 状态
    private Byte status;

    // 报价时间
    private Date createTime;
}
