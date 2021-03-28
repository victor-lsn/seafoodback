package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.Address;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    List<User> getAllUser();

    User getUserById(Integer id);

    List<User> getAllUserBySearch(@Param("username") String username, @Param("phone") String phone, @Param("role") String role);

    Integer updateStatus(@Param("userId") Integer userId, @Param("flag") Integer flag);

    Integer updateRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    UserInfoVo getUserInfo(@Param("userId") Integer userId);

    List<Address> getAllAddressByUserId(Integer userId);

    Integer addAddress(Address address);

    Integer deleteAddress(Integer id);

    List<User> getAllWaiter();
}
