package com.yan.common.constants;

public enum CommonErrorCodeEnum {

    SUCCESS("00000000","success"),
    FAIL("00000001","fail"),
    PARAM_ERROR("00000002","params error"),
    EXECUTE_LUA_SCRIPT_FAIL("00000003","execute_lua_script_fail");


    CommonErrorCodeEnum(String code,String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

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
