package com.wyzhu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyzhu.model.system.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wyzhu
 * @since 2023-03-29
 */
public interface SysUserService extends IService<SysUser> {

    void updateStatus(Long id, Integer status);
}
