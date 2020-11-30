package com.isabella.lee.user.mapper;

import com.isabella.lee.user.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    int save(@Param("sysUser") SysUser sysUser);

    SysUser getById(String id);

    List<SysUser> page(@Param("sysUser") SysUser sysUser);

    int update(@Param("sysUser") SysUser sysUser);

    int delete(String id);

}