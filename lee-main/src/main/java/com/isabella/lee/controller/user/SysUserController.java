package com.isabella.lee.controller.user;


import com.github.pagehelper.PageInfo;
import com.isabella.lee.result.ServiceResult;
import com.isabella.lee.result.WebResult;
import com.isabella.lee.user.pojo.SysUser;
import com.isabella.lee.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    SysUserService sysUserService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public WebResult<Integer> save(@RequestBody SysUser sysUser){
        WebResult<Integer> webResult = new WebResult<Integer>();
        ServiceResult<Integer> serviceResult = sysUserService.save(sysUser);
        BeanUtils.copyProperties(serviceResult,webResult);
        return webResult;
    }

    @RequestMapping(value = "/getOne",method = RequestMethod.GET)
    public WebResult<SysUser> getOne(String id){
        WebResult<SysUser> webResult = new WebResult<SysUser>();
        ServiceResult<SysUser> serviceResult = sysUserService.getById(id);
        BeanUtils.copyProperties(serviceResult,webResult);
        return webResult;
    }

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public WebResult<PageInfo<SysUser>> page(SysUser sysUser,int pageNum,int pageSize){
        WebResult<PageInfo<SysUser>> webResult = new WebResult<>();
        ServiceResult<PageInfo<SysUser>> serviceResult = sysUserService.page(sysUser,pageNum,pageSize);
        BeanUtils.copyProperties(serviceResult,webResult);
        return webResult;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public WebResult<Integer> update(@RequestParam SysUser sysUser){
        WebResult<Integer> webResult = new WebResult<Integer>();
        ServiceResult<Integer> serviceResult = sysUserService.update(sysUser);
        BeanUtils.copyProperties(serviceResult,webResult);
        return webResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public WebResult<Integer> delete(String id){
        WebResult<Integer> webResult = new WebResult<Integer>();
        ServiceResult<Integer> serviceResult = sysUserService.delete(id);
        BeanUtils.copyProperties(serviceResult,webResult);
        return webResult;
    }

}