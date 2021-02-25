package com.victor.seafoodback.vo;

import com.victor.seafoodback.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private String name;
    private String phone;
    private Integer flag;
    private String roleName;
    private List<Address> addrs;
}
