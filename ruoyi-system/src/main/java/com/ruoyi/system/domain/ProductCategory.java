package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory
{
    private Long id;

    private String name;

    private Integer index;

    private Byte status;

    private Date updateTime;
}
