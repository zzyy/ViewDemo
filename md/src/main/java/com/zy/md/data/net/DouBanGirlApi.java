package com.zy.md.data.net;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Simon on 2016/12/1.
 */

public interface DouBanGirlApi {
    String BASE_URL = "http://www.dbmeinv.com/dbgroup/";




    @GET("show.htm")
    Observable<String> getGirlItemData(@Query("cid") String cid, @Query("pager_offset") int pager_offset);

    @GET("{id}")
    Observable<String> getGirlDetailData(@Path("id") String id);
}
