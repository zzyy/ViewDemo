package com.zy.md.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Simon on 2016/12/8.
 */

public class GankDailyData {
    public List<String> category;

    public class Result {
        @SerializedName("Android") public List<GankItemData> androidList;
        @SerializedName("休息视频") public List<GankItemData> 休息视频List;
        @SerializedName("iOS") public List<GankItemData> iOSList;
        @SerializedName("福利") public List<GankItemData> 妹纸List;
        @SerializedName("拓展资源") public List<GankItemData> 拓展资源List;
        @SerializedName("瞎推荐") public List<GankItemData> 瞎推荐List;
        @SerializedName("App") public List<GankItemData> appList;
    }
}
