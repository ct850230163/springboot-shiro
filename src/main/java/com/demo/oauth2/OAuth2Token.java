package com.demo.oauth2;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author chentao
 * @email chen.tao@vpclub.cn
 * @date 2018-10-22 17:21
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
