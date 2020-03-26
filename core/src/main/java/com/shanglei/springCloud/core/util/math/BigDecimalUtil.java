package com.shanglei.springCloud.core.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal 工具类
 * 仅截取两位小数位，不考虑四舍五入等进位的情况
 */
public class BigDecimalUtil {


    /**
     * 全部的计算保留三位小数
     */
    private static final int SCALE = 3;


    /**
     * 零，有三位小数位
     */
    public static final BigDecimal ZERO = new BigDecimal("0.000");

    /**
     * 加法
     * a + b
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal jia(BigDecimal a, BigDecimal b) {
        return decimalProcess(a.add(b));
    }

    /**
     * 减法
     * a - b
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal jian(BigDecimal a, BigDecimal b) {
        return decimalProcess(a.subtract(b));
    }


    /**
     * 乘法
     * a * b
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal cheng(BigDecimal a, BigDecimal b) {
        return decimalProcess(a.multiply(b));
    }

    /**
     * 除法
     * a / b
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal chu(BigDecimal a, BigDecimal b) {
        return a.divide(b, 3, RoundingMode.DOWN);
    }

    /**
     * 小数处理
     * 保留三位小数
     * 不进行四舍五入等操作
     * @param a
     * @return
     */
    public static BigDecimal decimalProcess(BigDecimal a) {
        return a.setScale(SCALE, RoundingMode.DOWN);
    }

}
