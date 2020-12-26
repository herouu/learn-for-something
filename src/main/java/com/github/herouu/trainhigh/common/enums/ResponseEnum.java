package com.github.herouu.trainhigh.common.enums;

/**
 * @author Administrator
 */

public enum ResponseEnum {

  SYSTEM_ERROR("system.error", "系统异常"),
  PARAM_VALUE_BE_NOT_NULL("param.value.be.not.null", "参数值不能为空"),
  CONTENT_BE_NOT_NULL("content.be.not.null", "内容不能为空"),
  PARAM_VALUE_INVALID("param.value.invalid", "参数值非法"),
  MOBILE_NO_INVALID("mobile.no.invalid", "手机号无效"),
  SMS_VALIDATE_CODE_OVER_LIMIT("sms.validate.code.over.limit", "短信验证码发送次数超过限制"),
  MOBILE_NO_ALREADY_EXIST("mobile.no.already.exist", "手机号已注册"),
  MOBILE_NO_NON_EXIST("mobile.no.non.exist", "手机号未注册"),
  NEED_USER_BIND_MOBILE("need.user.bind.mobile", "需要用户绑定手机号"),
  SMS_CODE_INVALID("sms.code.invalid", "短信验证码无效"),
  PASSWORD_INVALID("password.invalid", "密码无效"),
  PASSWORD_INCORRECT("password.incorrect", "密码错误"),
  USER_INFO_NON_EXIST("user.info.non.exist", "用户信息不存在"),
  USER_TOKEN_INVALID("user.token.invalid", "token已失效"),
  DB_DATA_NON_EXIST("db.data.non.exist", "数据不存在"),
  DB_DATA_INCORRECT("db.data.incorrect", "数据不正确"),
  USER_POINTS_NOT_ENOUGH("user.points.not.enough", "用户积分不足"),
  ACCOUNT_AMOUNT_NOT_ENOUGH("account.amount.not.enough", "账户余额不足"),
  NO_AUTH_HANDLE("no.auth.handle", "无权限操作"),
  RELEASE_REQUIRE_PAY_AMOUNT_INVALID("release.require.pay.amount.invalid", "打赏的金额不正确"),
  PAY_PLAT_INVALID("pay.plat.invalid", "支付平台不正确"),
  DB_REQUIRE_NON_EXIST("db.require.non.exist", "需求不存在"),

  FILE_TYPE_INVALID("file.type.invalid", "不支持上传文件格式"),
  FILE_SIZE_OVER_INVALID("file.size.over.invalid", "文件大小超过限制"),
  FILE_SIZE_IS_ZERO("file.size.is.zero", "文件大小为0"),
  LOGIN_ERROR("login.error", "用户需要重新登陆"),
  FILE_NOT_FOUND_INVALID("file.not.found.invalid", "文件不存在"),
  FILE_HAS_EXIST("file.has.exist", "该文件名已存在，请重新编辑"),
  FILE_UPLOAD_ERROR("file.upload.error", "文件上传失败"),
  FOLDER_NAME_IS_EMPTY("folder.name.is.empty", "文件夹名称不能为空"),
  FOLDER_NAME_LENGTH_ISOVER("folder.name.length.is.over", "文件名称长度超出");

  private String code;
  private String message;

  ResponseEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
