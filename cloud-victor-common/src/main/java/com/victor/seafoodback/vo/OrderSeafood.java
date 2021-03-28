package com.victor.seafoodback.vo;

import com.victor.seafoodback.entity.Seafood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSeafood {
    private Integer id;
    private Integer orderId;
    private Seafood seafood;
    private Integer count;
}
