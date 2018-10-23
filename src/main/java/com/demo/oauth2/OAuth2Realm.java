package com.demo.oauth2;


import cn.vpclub.wuhan.redis.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 认证
 *
 * @author chentao
 * @email chen.tao@vpclub.cn
 * @date 2018-10-22 17:21
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String accessToken = (String) principals.getPrimaryPrincipal();
        String permissions = redisUtils.get(accessToken);
        if (StringUtils.isEmpty(permissions)){
            return null;
        }
        SimpleAuthorizationInfo info = JSON.parseObject(permissions,SimpleAuthorizationInfo.class);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(accessToken, accessToken, getName());
        return info;
    }
}
