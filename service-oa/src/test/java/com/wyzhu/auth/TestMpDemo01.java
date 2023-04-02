package com.wyzhu.auth;

import com.wyzhu.auth.mapper.SysRoleMapper;
import com.wyzhu.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Date:2023/3/26 0026
 * Author:wyzhu
 * Description:
 */
@SpringBootTest
public class TestMpDemo01 {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Test
    public void getAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        System.out.println(sysRoles);
    }
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");

        int result = sysRoleMapper.insert(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole); //id自动回填
    }
}

