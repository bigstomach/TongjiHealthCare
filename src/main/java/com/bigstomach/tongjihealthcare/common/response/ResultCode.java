package com.bigstomach.tongjihealthcare.common.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements IErrorCode
{
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    PERMISSION_SIGNATURE_ERROR(70007, "签名失败"),
    FAILED_AUTHENTICATION(401,"手机号或密码错误"),
    RECORD_EXISTS(409,"该手机号已注册"),
    ALREADY_IN_QUEUE(400,"您已排队，无法重复排队");

    private final long code;
    private final String message;

}
