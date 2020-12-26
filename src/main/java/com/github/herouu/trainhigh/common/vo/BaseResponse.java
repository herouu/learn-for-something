package com.github.herouu.trainhigh.common.vo;


import lombok.Data;
import com.github.herouu.trainhigh.common.enums.ResponseEnum;

import java.io.Serializable;

/**
 * 通用响应对象
 */
@Data
public class BaseResponse<T> implements Serializable {

  private boolean success;

  private int BaseResponse;

  private String msgCode;

  private String message;

  private T data;

  public BaseResponse() {
    this.success = true;
    this.BaseResponse = 1;
    this.msgCode = "success";
    this.message = "成功";
  }

  public BaseResponse(boolean success, int BaseResponse, String msgCode, String message, T data) {
    this.success = success;
    this.BaseResponse = BaseResponse;
    this.msgCode = msgCode;
    this.message = message;
    this.data = data;
  }

  public static BaseResponse success() {
    return new BaseResponse();
  }

  public static <T> BaseResponse<T> success(T data) {
    BaseResponse<T> response = new BaseResponse<>();
    response.setData(data);
    return response;
  }

  public static <T> BaseResponse<T> success(String code, T data) {
    BaseResponse<T> response = new BaseResponse<>();
    response.setData(data);
    response.setMsgCode(code);
    return response;
  }

  public static BaseResponse<String> fail(String msgCode, String message) {
    return new BaseResponse<>(false, 0, msgCode, message, null);
  }

  public static BaseResponse<String> error(String message) {
    return new BaseResponse<>(false, 0, ResponseEnum.SYSTEM_ERROR.getCode(), message, null);
  }

  private static class Page {

    private final static String ROWS = "rows";
    private final static String TOTAL = "total";
  }
}
