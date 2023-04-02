package com.wyzhu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyzhu.model.system.SysRole;
import com.wyzhu.vo.system.AssginRoleVo;

import java.util.Map;


/**
 * Date:2023/3/27 0027
 * Author:wyzhu
 * Description:
 */
public interface SysRoleService extends IService<SysRole> {
    Map<String, Object> findRoleByAdminId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
