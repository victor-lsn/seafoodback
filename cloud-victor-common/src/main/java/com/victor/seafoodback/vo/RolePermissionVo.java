package com.victor.seafoodback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionVo {
    private Integer roleId;
    private Integer permissionId;
}
