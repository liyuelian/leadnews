package com.li.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.common.enums.AppHttpCodeEnum;
import com.li.model.user.dtos.LoginDto;
import com.li.model.user.pojos.ApUser;
import com.li.user.mapper.ApUserMapper;
import com.li.user.service.ApUserService;
import com.li.utils.common.AppJwtUtil;
import com.li.utils.common.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @Author: liyuelian
 * @Date: 2024/7/3 18:40
 * @Description: 用户登录实现类
 **/

@Service
@Transactional
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Override
    public ResponseResult login(LoginDto dto) {
        HashMap<String, Object> dataMap = new HashMap<>(2);
        //(1)用户登录
        if (StringUtils.isNotBlank(dto.getPassword()) && StringUtils.isNotBlank(dto.getPhone())) {

            //非空判断
            ApUser dbUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, dto.getPhone()));
            if (dbUser == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST, "用户信息不存在！");
            }

            //对比密码
            String encodePwd = MD5Utils.encodeWithSalt(dto.getPassword(), dbUser.getSalt());


            if (!dbUser.getPassword().equals(encodePwd)) {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            //生成 jwt-token，user展示数据
            String token = AppJwtUtil.getToken(dbUser.getId().longValue());

            dataMap.put("token", token);
            dbUser.setPassword("");//这里应该创建一个用来展示VO类
            dbUser.setSalt("");//这里应该创建一个用来展示的VO类
            dataMap.put("user", dbUser);

            //返回数据
            return ResponseResult.okResult(dataMap);
        } else {
            //(2)游客登录
            dataMap.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(dataMap);
        }
    }
}
