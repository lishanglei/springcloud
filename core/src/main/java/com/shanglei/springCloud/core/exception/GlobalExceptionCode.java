package com.shanglei.springCloud.core.exception;

/**
 * 全局异常状态吗
 */
public enum GlobalExceptionCode {


    /**
     * 请求成功
     */
    SUCCESS(0, "SUCCESS"),

    ERROR(1, "系统异常"),

    REQUEST_ARGUMENT_EXCEPTION(2, "请求参数异常"),

    USER_LOGIN_STATUS_EXCEPTION(3, "当前用户登陆信息未进行初始化"),

    NOT_FOUND_ENUM(4, "未找到对应枚举类型"),

    FEIGN_ERROR(5, "Feign 接口请求异常"),

    JEDIS_POOL_INIT_FAIL(6, "Jedis Pool 初始化失败"),

    HTTP_REQUEST_ERROR(7, "网络请求异常"),

    PARAM_EXCEPTION(70001,"参数异常"),

    DUPLICATED_GOODS_TYPE(70002,"商品类型重复"),

    GOODS_TYPE_EXCEPTION(70003,"商品类型错误"),

    PAR_TYPE_IS_NULL(70003,"商品大类不能为空"),

    DUPLICATED_GOODS_CODE(70003,"商品编码重复"),

    MISS_GOODS_LINK(70003,"未找到该商品溯源环节"),


    ;


    /**
     * 异常代码
     */
    private final int code;

    /**
     * 异常信息
     */
    private final String msg;


    GlobalExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }


    public static GlobalExceptionCode getByCode(int code) {

        GlobalExceptionCode[] values = GlobalExceptionCode.values();

        for (GlobalExceptionCode value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }

        throw new GlobalException(GlobalExceptionCode.NOT_FOUND_ENUM, String.format("未找到 [%s] 对应的异常类型!", code));
    }

}
