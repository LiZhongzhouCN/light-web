package com.liz.light.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
@AllArgsConstructor
@Getter
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNKNOWN_EXCEPTION(19999, "未知异常");

    private long code;
    private String message;
}
