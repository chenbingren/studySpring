package com.cbr.cbrdemoone.reporitory;

import com.cbr.cbrdemoone.mode.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *   JpaRepository<UserInfo, Long>  是最顶层包，     CrudRepository<UserInfo,Long> 是下一层包
 */

public interface UserRepository extends CrudRepository<UserInfo,Long> {

    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
}