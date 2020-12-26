package com.github.herouu.trainhigh.common.exception;

import java.io.Serializable;

import com.github.herouu.trainhigh.common.enums.ResponseEnum;
import lombok.Data;

@Data
public class BaseException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 1L;
  private String msg;
  private String code;

  public BaseException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public BaseException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public BaseException(String code, String msg) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public BaseException(String msg, String code, Throwable e) {
    super(msg, e);
    this.msg = msg;
    this.code = code;
  }

  public BaseException(ResponseEnum responseEnum) {
    this.msg = responseEnum.getMessage();
    this.code = responseEnum.getCode();
  }
}
