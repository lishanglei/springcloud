package com.shanglei.springCloud.core.wrapper;



import com.shanglei.springCloud.core.exception.GlobalExceptionCode;
import com.shanglei.springCloud.core.util.json.GsonUtil;
import lombok.Data;

/**
 * 返回值装载器
 *
 * 全部的API接口的返回信息必须返回成本类型的对象进行返回
 */
@Data
public class GlobalResponseWrapper {

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 要返回的数据
     */
    private Object data;

    public GlobalResponseWrapper() {

    }

    /**
     * 使用全局异常
     * @param resultCode
     */
    private GlobalResponseWrapper(GlobalExceptionCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    /**
     * 使用全局异常并自定义异常信息
     * @param resultCode
     * @param msg
     */
    private GlobalResponseWrapper(GlobalExceptionCode resultCode, String msg) {
        this.code = resultCode.getCode();
        this.msg = msg;
    }

    /**
     * 使用全局异常并存在返回值
     * @param resultCode
     * @param data
     */
    private GlobalResponseWrapper(GlobalExceptionCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    /**
     * 使用全局异常，自定义异常信息且存在返回值
     * @param resultCode
     * @param msg
     * @param data
     */
    private GlobalResponseWrapper(GlobalExceptionCode resultCode, String msg, Object data) {
        this(resultCode,msg);
        this.data = data;
    }



    /**
     * 请求成功，无返回值
     * @return
     */
    public static GlobalResponseWrapper success() {
        return new GlobalResponseWrapper(GlobalExceptionCode.SUCCESS);
    }

    /**
     * 请求成功且自定义返回信息
     * @param msg
     * @return
     */
    public static GlobalResponseWrapper success(String msg) {
        return new GlobalResponseWrapper(GlobalExceptionCode.SUCCESS,msg);
    }

    /**
     * 请求成功，有返回值
     * @param data  返回值
     * @return
     */
    public static GlobalResponseWrapper success(Object data) {
        return new GlobalResponseWrapper(GlobalExceptionCode.SUCCESS, data);
    }

    /**
     * 请求完成，使用全局异常
     * @param resultCode
     * @return
     */
    public static GlobalResponseWrapper ok(GlobalExceptionCode resultCode) {
        return new GlobalResponseWrapper(resultCode);
    }

    /**
     * 请求完成，使用全局异常并自定义异常信息
     * @param resultCode
     * @param msg
     * @return
     */
    public static GlobalResponseWrapper ok(GlobalExceptionCode resultCode,String msg) {
        return new GlobalResponseWrapper(resultCode, msg);
    }

    /**
     * 请求完成，使用全局异常并存在返回值
     * @param resultCode
     * @param data
     * @return
     */
    public static GlobalResponseWrapper ok(GlobalExceptionCode resultCode, Object data) {
        return new GlobalResponseWrapper(resultCode, data);
    }

    /**
     * 请求完成，使用全局异常，自定义异常信息且存在返回值
     * @param resultCode
     * @param msg
     * @param data
     * @return
     */
    public static GlobalResponseWrapper success(GlobalExceptionCode resultCode, String msg, Object data) {
        return new GlobalResponseWrapper(resultCode, msg, data);
    }

    /**
     * 得到当前对象的 Json 字符串
     * @return
     */
    public String toJson() {
        return GsonUtil.getJson(this);
    }


}
