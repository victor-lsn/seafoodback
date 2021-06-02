package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BasicDao {

    int register(User user);

    int getUserByPhone(String phone);

    User login(User user);

    Integer getUserStatus(User user);
}
