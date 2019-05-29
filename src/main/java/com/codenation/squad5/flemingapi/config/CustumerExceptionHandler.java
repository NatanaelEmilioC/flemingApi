package com.codenation.squad5.flemingapi.config;


import com.codenation.squad5.flemingapi.config.exception.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustumerExceptionHandler {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ResponseEntity<ResponseError> processForbiddenException(IllegalAccessException e){
		log.warn(e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(new ResponseError(ResponseError.ERR_FORBIDDEN, "você nao possui acesso"));
	}
}
