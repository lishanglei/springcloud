package com.shanglei.springCloud.core.thread;



import com.shanglei.springCloud.core.exception.GlobalException;
import com.shanglei.springCloud.core.exception.GlobalExceptionCode;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 当前请求用户信息
 */
public class CurrentUser {


    /**
     * 当前线程是否进行过初始化
     */
    private static final ThreadLocal<AtomicBoolean> isInit = ThreadLocal.withInitial(AtomicBoolean::new);


    /**
     * 记录当前线程操作用户主键 ID
     */
    private static final ThreadLocal<Long> id = new ThreadLocal<>();

    /**
     * 初始化数据
     *
     * @param userId
     */
    public static void init(long userId) {

        /*
        若未进行初始化那么将对其进行值的初始化
         */
        if (isInit.get().compareAndSet(false, true)) {
            id.set(userId);
        }

    }

    /**
     * 验证是否进行过初始化，若未进行初始化则抛出异常
     * 以防止某些不可预测的异常发生，导致获取到了空的主键 ID
     */
    private static void isInit() {

        if (isInit.get().compareAndSet(false, false)) {
            throw new GlobalException(GlobalExceptionCode.USER_LOGIN_STATUS_EXCEPTION);
        }

    }

    /**
     * 为防止线程池缓存这些变量，在每次请求结束之后需要主动清理他们
     */
    public static void destroy() {
        isInit.remove();
        id.remove();
    }


    /**
     * 获取当前登陆用户主键 ID
     * @return
     */
    public static Long getId() {
        isInit();
        return id.get();
    }



}
