package com.wyzhu.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyzhu.auth.mapper.SysRoleMapper;
import com.wyzhu.auth.service.SysRoleService;
import com.wyzhu.auth.service.SysUserRoleService;
import com.wyzhu.model.system.SysRole;
import com.wyzhu.model.system.SysUserRole;
import com.wyzhu.vo.system.AssginRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Date:2023/3/27 0027
 * Author:wyzhu
 * Description:
 */

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysUserRoleService sysUserRoleService;
    @Override
    public Map<String, Object> findRoleByAdminId(Long userId) {
        //1 查询所有角色，返回List集合
        List<SysRole> sysRoles = baseMapper.selectList(null);
        //2 根据userid查询角色用户关系表，查询userid对应所有角色id
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(wrapper);

        // Stream流 + 方法引用
        List<Long> existRoleIdList = existUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());

        //3 根据查询所有角色id，找到对应角色信息
        List<SysRole> assginRoleList = new ArrayList<>();
        for (SysRole role : sysRoles) {
            //已分配
            if(existRoleIdList.contains(role.getId())) {
                assginRoleList.add(role);
            }
        }
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assginRoleList", assginRoleList);
        roleMap.put("allRolesList", sysRoles);
        return roleMap;

    }

    // 为用户分配角色
    @Transactional
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // 把用户之前分配角色数据删除
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,assginRoleVo.getUserId());
        sysUserRoleService.remove(wrapper);

        // 重新分配
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for(Long roleId : roleIdList){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleService.save(sysUserRole);
        }
    }
}
