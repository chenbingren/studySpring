package com.cbr.cbrdemoone.service.impl;

import com.cbr.cbrdemoone.mode.UserInfo;
import com.cbr.cbrdemoone.reporitory.UserRepository;
import com.cbr.cbrdemoone.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lenovo on 2019/7/19.
 */
@Service
//@Service  要加行这个，controller里面才能用@Autowired注入服务
public class UserInfoServiceImpl implements UserInfoService{

    //这里用 @Resource 不用 @Autowired
    @Resource
    private UserRepository userRepository;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userRepository.findByUsername(username);
    }

}
