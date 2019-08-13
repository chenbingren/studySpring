package com.cbr.cbrdemoone.web;

import com.cbr.cbrdemoone.mode.UserInfo;
import com.cbr.cbrdemoone.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	private UserInfoService userInfoService;
	
    //@RequestMapping("/getUser")
    @GetMapping({"/getUser/{myname}"})
    public UserInfo getUser(@PathVariable("myname")  String name) {
        UserInfo user=userInfoService.findByUsername(name);
    	if(user != null){
            System.out.println("进入后台-----"+user.toString());
        }else{
            log.info("数据为空，请核查用户名是否有误!");
        }

        return user;
    }

    //与上面接收参数比较
    @GetMapping({"/getUser2"})
    public UserInfo getUser2(@RequestParam("username") String username) {
        System.out.println(username+"-----参数-----");
        UserInfo user=userInfoService.findByUsername(username);
        System.out.println("进入后台-----"+user.toString());
        return user;
    }
    
//    @RequestMapping("/getUsers")
//    public List<User> getUsers() {
//    	List<User> users=userService.findAll();
//    	System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
//        return users;
//    }
}