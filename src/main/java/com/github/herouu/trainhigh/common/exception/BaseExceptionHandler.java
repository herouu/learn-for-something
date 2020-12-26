package com.github.herouu.trainhigh.common.exception;

import com.github.herouu.trainhigh.common.enums.ResponseEnum;
import com.github.herouu.trainhigh.common.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Business exception handler类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180716 13:04:43
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public Object handleBusinessException(BaseException ex, Throwable t) {
    log.info(t.getMessage());
    return BaseResponse.fail(ex.getCode(), ex.getMessage());
  }


  @ExceptionHandler(Exception.class)
  public Object handleException(Throwable t) {
    log.error("", t);
    return BaseResponse.error(ResponseEnum.SYSTEM_ERROR.getMessage());
  }
}
