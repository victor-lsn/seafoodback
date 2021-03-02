package com.victor.seafoodback.dao;

import com.victor.seafoodback.entity.Menu;
import com.victor.seafoodback.entity.Role;
import com.victor.seafoodback.entity.User;
import com.victor.seafoodback.vo.RolePermissionVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDao {

    List<Role> getAllRole();

    Integer addRole(Role role);

    Integer deleteRoleById(Integer id);

    List<User> selectUserByRoleId(Integer roleId);

    Role getRoleById(Integer roleId);

    Integer deleteRolePermissionByRoleId(Integer roleId);

    Integer addRolePermission(List<RolePermissionVo> list);

    Menu getMenuById(Integer id);
}
