package cn.mju.admintle.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.mju.admintle.shiro.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;


@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * Shiro内置过滤器：
         *     anon：无需认证（登录）可以访问
         *     authc：必须认证才可以访问
         *     user:如果使用remenberMe的功能可以直接访问
         *     perms:该资源必须得到资源权限才可以访问
         *     roles:所拥有角色，用法和perms一样
         *
         */
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/noAuth");



        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();


        linkedHashMap.put("/user/logout", "logout");

        linkedHashMap.put("/main","authc");
        linkedHashMap.put("/user/toPassword","authc");

        linkedHashMap.put("/emp/addEmp","roles[admin],perms[add]");
        linkedHashMap.put("/emp/update","roles[admin],perms[update]");
        linkedHashMap.put("/emp/delete","roles[admin],perms[delete]");
        linkedHashMap.put("/emp/deleteBatch","roles[admin],perms[delete]");

        linkedHashMap.put("/wages/add","roles[admin],perms[add]");
        linkedHashMap.put("/wages/delete","roles[admin],perms[delete]");
        linkedHashMap.put("/wages/deleteBatch","roles[admin],perms[delete]");


        linkedHashMap.put("/notice/publish","roles[admin],perms[add]");
        linkedHashMap.put("/notice/delete","roles[admin],perms[delete]");
        linkedHashMap.put("/notice/deleteBatch","roles[admin],perms[delete]");

        linkedHashMap.put("/time/approval","roles[admin],perms[update]");

        linkedHashMap.put("/health/healths","roles[admin],perms[query]");

        linkedHashMap.put("/static/**","anon");
        linkedHashMap.put("/user/**","anon");
        linkedHashMap.put("/emp/**","authc");
        linkedHashMap.put("/role/**","roles[admin]");
        linkedHashMap.put("/file/**","roles[admin]");
        linkedHashMap.put("/dept/**","roles[admin]");
        linkedHashMap.put("/notice/**","authc");
        linkedHashMap.put("/wages/**","authc");
        linkedHashMap.put("/app/**","roles[admin]");
        linkedHashMap.put("/time/**","authc");
        linkedHashMap.put("/health/**","authc");





        //角色权限
        /*linkedHashMap.put("/user/add", "roles[admin],perms[update]");
        linkedHashMap.put("/user/update", "roles[emp]");*/
        //**要放最下面




        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm
                                                                  ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean("userRealm")
    public UserRealm getRealm(@Qualifier("matcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        //关闭认证缓存
        userRealm.setAuthenticationCachingEnabled(false);
        //将规则匹配器注入到realm里
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }


    @Bean("matcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        return credentialsMatcher;
    }




    /**
     * 配置ShiroDialect，用于Shiro和thymeleaf标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }



}
