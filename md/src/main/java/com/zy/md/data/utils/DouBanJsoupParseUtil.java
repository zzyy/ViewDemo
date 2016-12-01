package com.zy.md.data.utils;

import com.zy.md.data.pojo.DouBanGirlItemData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DouBanJsoupParseUtil {
    /**
     * 解析每一页妹子的数据
     *
     * @param response
     * @return
     */
    public static List<DouBanGirlItemData> parseGirls(String response) {
        Document document = Jsoup.parse(response);
        Elements elements = document.select("div[class=img_single] > a");
        List<DouBanGirlItemData> list = new ArrayList<>();
        DouBanGirlItemData data;
        for (Element element : elements) {
            data = new DouBanGirlItemData();
            data.setId(element.attr("href").substring(element.attr("href").lastIndexOf("/") + 1));
            data.setTitle(element.select("img").attr("title"));
            data.setUrl(element.select("img").attr("src"));
            list.add(data);
        }

        return list;
    }

    /**
     * 解析妹子数据的详情
     *
     * @param response
     * @return
     */
    public static List<String> parseGirlDetail(String response) {
        Document document = Jsoup.parse(response);
        Elements elements = document.select("div[class = topic-figure cc] > img");
        List<String> list = new ArrayList<>();
        for (Element element : elements) {
            list.add(element.select("img").attr("src"));
        }

        return list;
    }
}
