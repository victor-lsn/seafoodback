package com.victor.seafoodback.entity;

import com.victor.seafoodback.vo.OrderSeafood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Integer userId;
    private String userAddr;
    private Date createDate;
    private Double cost;
    private Double pay;
    private String realName;
    private String serialNumber;
    private Integer status;
    private List<OrderSeafood> children;
}
