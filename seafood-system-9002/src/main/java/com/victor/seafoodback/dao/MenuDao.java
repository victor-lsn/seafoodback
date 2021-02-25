package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuDao {

    List<Menu> getAllMenu();

    Integer addMenu(Menu menu);
}
