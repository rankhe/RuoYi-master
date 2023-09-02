package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 产品批次
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductBatchInfo
{
    private Long id;
    // 批次号
    private Long  batchNo;

    // 创建时间
    private Date createTime;

    //所有者
    private Long ownerId;

    // 发布人
    private Long publishUser;

    //状态
    private Byte status;

    //报价人次
    private Integer quotation;

    // 更新时间
    private Date updateTime;

}
