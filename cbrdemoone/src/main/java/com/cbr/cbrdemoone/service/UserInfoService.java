package com.cbr.cbrdemoone.service;

import com.cbr.cbrdemoone.mode.UserInfo;

/**
 * Created by Lenovo on 2019/7/19.
 */
public interface UserInfoService {

    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
}
