package com.isabella.lee.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isabella.lee.user.mapper.SysUserMapper;
import com.isabella.lee.user.pojo.SysUser;
import com.isabella.lee.result.ResultCode;
import com.isabella.lee.result.ServiceResult;
import com.isabella.lee.user.service.SysUserService;

import com.isabella.lee.uuid.SnowflakeIdWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private final static Logger logger = LogManager.getLogger(SysUserServiceImpl.class);

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public ServiceResult<Integer> save(SysUser sysUser) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try{
            SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);
            String id = Long.toString(snowflakeIdWorker.nextId());
            sysUser.setId(id);
            int c = sysUserMapper.save(sysUser);
            result.setData(c);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setCode(ResultCode.SQL_SAVE_ERROR.getCode());
            result.setMessage(ResultCode.SQL_SAVE_ERROR.getMessage());
        }
            return result;
    }

    @Override
    public ServiceResult<SysUser> getById(String id) {
        ServiceResult<SysUser> result = new ServiceResult<SysUser>();
        try {
            SysUser sysUser = sysUserMapper.getById(id);
            result.setData(sysUser);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setCode(ResultCode.SQL_SELECT_ERROR.getCode());
            result.setMessage(ResultCode.SQL_SELECT_ERROR.getMessage());
        }
        return result;
    }

    @Override
    public ServiceResult<PageInfo<SysUser>> page(SysUser sysUser, int pageNum, int pageSize) {
        ServiceResult<PageInfo<SysUser>> result = new ServiceResult<PageInfo<SysUser>>();
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<SysUser> list = sysUserMapper.page(sysUser);
            PageInfo<SysUser> page = new PageInfo<SysUser>(list);
            result.setData(page);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setCode(ResultCode.SQL_SELECT_ERROR.getCode());
            result.setMessage(ResultCode.SQL_SELECT_ERROR.getMessage());
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> update(SysUser sysUser) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            int c = sysUserMapper.update(sysUser);
            if(c > 0){
                result.setData(c);
            }else {
                result.setData(c);
                result.setCode(ResultCode.SQL_UPDATE_NONE.getCode());
                result.setMessage(ResultCode.SQL_UPDATE_NONE.getMessage());
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
               result.setSuccess(false);
            result.setCode(ResultCode.SQL_UPDATE_ERROR.getCode());
            result.setMessage(ResultCode.SQL_UPDATE_ERROR.getMessage());
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> delete(String id) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            int c = sysUserMapper.delete(id);
            if (c > 0){
                result.setData(c);
            }else {
                result.setData(c);
                result.setCode(ResultCode.SQL_DELETE_NONE.getCode());
                result.setMessage(ResultCode.SQL_DELETE_NONE.getMessage());
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setCode(ResultCode.SQL_DELETE_ERROR.getCode());
            result.setMessage(ResultCode.SQL_DELETE_ERROR.getMessage());
        }
        return result;
    }
}