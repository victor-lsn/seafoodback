package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.Menu;
import javafx.scene.control.MenuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuDao {

    List<Menu> getAllMenu();

    Integer addMenu(Menu menu);

    List<Menu> getMenuByRoleId(@Param("roleId")Integer roleId);

    Menu getMenuById(@Param("permissionId")Integer permissionId);

    Integer updateMenuById(Menu menu);
}
