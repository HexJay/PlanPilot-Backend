package com.jay.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    FAILURE("0001", "失败"),
    UN_ERROR("0001-1", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    ;

    private String code;
    private String info;

}
