package com.zy.md.utils.common;

/**
 * Created by Simon on 2016/12/13.
 */

public class MathUtils {

    /**
     * 计算对角线长度
     */
    public static float calculateCatercorner(float width, float height) {
        return (float) Math.sqrt(width * width + height + height);
    }

}
