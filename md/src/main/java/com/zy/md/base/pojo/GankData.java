package com.zy.md.base.pojo;

import java.util.List;

/**
 * Created by Simon on 2016/11/3.
 */

public class GankData<T> {
    boolean error;

    T results;

    public boolean isError() {
        return error;
    }

    public T getResults() {
        return results;
    }
}
