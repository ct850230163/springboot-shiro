package com.demo.exception;



import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异常处理器
 *
 * @author chentao
 * @email chen.tao@vpclub.cn
 * @date 2018-10-22 17:21
 */
@RestControllerAdvice
public class ShiroExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(AuthorizationException.class)
	public String handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		Map<String,Object> responseMap = new ConcurrentHashMap<>();
		responseMap.put("returnCode",HttpStatus.INTERNAL_SERVER_ERROR.value());
		responseMap.put("message","无权限访问,请联系管理员");
		return JSONObject.toJSONString(responseMap);
	}
}
