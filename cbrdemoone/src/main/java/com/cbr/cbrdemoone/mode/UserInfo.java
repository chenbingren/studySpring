package com.cbr.cbrdemoone.mode;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 只要使用了@Data懒加载就会失效，但是你显示的重写一遍toString（），懒加载就能生效
 * 所以解决方案就是两种：
 * • 使用@Data同时再重写一遍toString()方法
 * 不用toString的话，就把@Data拆开：
 * @Setter
 * @Getter
 * @EqualsAndHashCode
 */
@Entity
@Setter
@Getter
@EqualsAndHashCode
//@Table(name="sys_user")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer uid;
    @Column(unique =true)
    private String username;//帐号
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    private String password; //密码;
    private String salt;//加密密码的盐
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}