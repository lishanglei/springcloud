package com.shanglei.springCloud.app.common.handler;

import com.shanglei.springCloud.core.exception.GlobalException;
import com.shanglei.springCloud.core.exception.GlobalExceptionCode;
import com.shanglei.springCloud.core.wrapper.GlobalResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常拦截
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 自定义异常处理
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public GlobalResponseWrapper customerExceptionHandler(HttpServletRequest request, GlobalException e) throws Exception {
        log.info("拦截到抛出的自定义异常", e);
        return GlobalResponseWrapper.ok(e.getCode(),e.getMessage());
    }

    /**
     * 注解参数异常处理
     * 将全部的异常参数拼接返回
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public GlobalResponseWrapper methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exception) throws Exception {

        //获取全部异常
        BindingResult bindingResult = exception.getBindingResult();
        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();

        log.info("拦截到请求参数异常",message);

        return GlobalResponseWrapper.ok(GlobalExceptionCode.REQUEST_ARGUMENT_EXCEPTION, message);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public GlobalResponseWrapper constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException excpetion) throws Exception {


        String message = excpetion.getConstraintViolations().iterator().next().getMessage();
        log.info("拦截到请求参数异常",message);

        return GlobalResponseWrapper.ok(GlobalExceptionCode.REQUEST_ARGUMENT_EXCEPTION, message);

    }


    /**
     * 其他的异常拦截处理
     * 不建议放开这些异常拦截，因为这些拦截无法完全掌控，且其中会包含一些其他不确定因素
     * 拦截这些异常会影响到熔断器的使用
     *
     * 本项目为单节点项目，不会进行熔断处理
     * 全部异常已进行日志记录
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    /*@ExceptionHandler(Exception.class)
    @ResponseBody*/
    public GlobalResponseWrapper defaultExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) { //404处理
            log.warn("拦截到 404 异常",e);
        } else {    //其它全部为 500 异常
            log.error("拦截到服务器内部异常",e);
        }
        return GlobalResponseWrapper.ok(GlobalExceptionCode.ERROR,e.getMessage());
    }


}
