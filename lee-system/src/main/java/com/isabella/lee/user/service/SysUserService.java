package com.isabella.lee.user.service;

import com.github.pagehelper.PageInfo;
import com.isabella.lee.result.ServiceResult;
import com.isabella.lee.user.pojo.SysUser;

public interface SysUserService {

    /** 新建*/
    ServiceResult<Integer> save(SysUser sysUser);

    /** 根据ID查询*/
    ServiceResult<SysUser> getById(String id);

    /** 分页查询*/
    ServiceResult<PageInfo<SysUser>> page(SysUser sysUser, int pageNum, int pageSize);

    /** 更新*/
    ServiceResult<Integer> update(SysUser sysUser);

    /** 逻辑删除*/
    ServiceResult<Integer> delete(String id);

}