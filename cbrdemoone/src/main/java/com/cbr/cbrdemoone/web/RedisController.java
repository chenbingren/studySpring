package com.cbr.cbrdemoone.web;

import com.cbr.cbrdemoone.mode.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class RedisController {

	//存字符串用到，跟redisTemplate存的数据不能通用，存到后台是明文
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//存对象用到，存到后台是字节码
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * redis 手动方式存储提取
	 * @return
	 */
    @RequestMapping("/redis")
	public String redis() {
		log.info("stringRedisTemplate 方式-------------------");
		stringRedisTemplate.opsForValue().set("aaa", "111");
		String tempStr = stringRedisTemplate.opsForValue().get("aaa");
		log.info("获取存到的对象为："+tempStr);


		log.info("redisTemplate 方式-------------------");
		UserInfo user=null;
		ValueOperations<String, UserInfo> operations=redisTemplate.opsForValue();
		operations.set("cbr.user", user);
		//设置过期时间
		operations.set("cbr.user2", user,1, TimeUnit.SECONDS);

		//redisTemplate.delete("com.neo.f");
		boolean exists=redisTemplate.hasKey("cbr.user2");
		if(exists){
			log.info("exists is true");
		}else{
			log.info("exists is false");
		}

		String username = operations.get("cbr.user").getUsername();
		log.info("获取存到的对象为："+username);

		return "hello";
	}


	/**
	 * 如何在查找数据库的时候自动使用缓存呢====以下是:自动根据方法生成缓存【自动存储到redis】
	 * 第一次调用该方法，根据方法和对象名生成一个redis的key
	 * 【user-key::com.cbr.cbrdemoone.web.RedisControllergetUserRedis】
	 * 第二次调用如果存在了这个key，则不会往下走方法！也就是new User
	 *
	 * 配合类RedisConfig 使用
	 *
	 * @return
	 */
	@RequestMapping("/getUserRedis")
	@Cacheable(value="user-key")
	public UserInfo getUserRedis() {
		log.info("-------------------------------getUserRedis---begin--------------------------------------------");
		UserInfo user=null;
		System.out.println("若下面没出现---“无缓存的时候调用”-----字样且能打印出数据表示测试成功");
		log.info("--------------------------------getUserRedis----end------------------------------------------");
		return user;
	}


	/**
	 * 分布式系统中，session共享有很多的解决方案，其中托管到缓存中应该是最常用的方案之一
	 *
	 * 配合类SessionConfig 使用
	 * @param session
	 * @return
	 */
	@RequestMapping("/redisUid")
	String redisUid(HttpSession session) {
		log.info("-------------------------------redisUid---begin--------------------------------------------");
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			log.info("-------------------------------redisUid---create--------------------------------------------");
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		log.info("-------------------------------redisUid---end--------------------------------------------");
		return session.getId();
	}
}