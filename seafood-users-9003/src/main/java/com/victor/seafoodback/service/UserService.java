package com.victor.seafoodback.service;

import com.victor.seafoodback.entity.Address;
import com.victor.seafoodback.entity.CommonResult;
import com.victor.seafoodback.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.lang.model.type.IntersectionType;
import java.util.List;

public interface UserService {

    CommonResult getAllUser();

    CommonResult getUserForPage(Integer pageNo, Integer pageSize, String username, String phone, String role);

    CommonResult updateStatus(Integer userId, Integer flag);

    CommonResult updateRole(Integer userId, Integer roleId);

    CommonResult getUserInfo(Integer userId);

    CommonResult getAllAddress(Integer userId);

    CommonResult addAddress(Address address);

    CommonResult deleteAddress(Integer id);

    CommonResult getAllWaiter();
}
