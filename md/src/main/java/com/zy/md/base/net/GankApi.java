package com.zy.md.base.net;

import android.support.annotation.StringDef;

import com.zy.md.base.pojo.GankData;
import com.zy.md.base.pojo.Results;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Simon on 2016/11/3.
 */

public interface GankApi {
    final String BASE_URL = "http://gank.io/";


    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * <p>
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     */
    @GET("api/data/{type}/{pageSize}/{pageNo}")
    Observable<GankData<List<Results>>> getMenu(@Path("type") @Type String type, @Path("pageSize") int pageSize, @Path("pageNo") int pageNo);

    String TYPE_MEZI = "福利";
    String TYPE_ANDROID = "Android";
    String TYPE_IOS = "iOS";
    String TYPE_HTML = "前端";
    String TYPE_VIDEO = "休息视频";
    String TYPE_ALL = "all";

    @StringDef({TYPE_MEZI, TYPE_ANDROID, TYPE_IOS,TYPE_HTML, TYPE_VIDEO, TYPE_ALL })
    @Retention(RetentionPolicy.CLASS)
    @interface Type{}


    /**搜索 API

     http://gank.io/api/search/query/listview/category/Android/count/10/page/1
     注：
     category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     count 最大 50*/
}
