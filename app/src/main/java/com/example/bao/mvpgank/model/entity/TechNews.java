package com.example.bao.mvpgank.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BAO on 2017/12/7.
 */

public class TechNews {

    /**
     * error : false
     * results : [{"_id":"5a1d276e421aa90fe7253676","createdAt":"2017-11-28T17:07:58.240Z","desc":"业界首创Android Lint增量扫描实战纪要","publishedAt":"2017-12-06T08:49:34.303Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/4833a79e9396","used":true,"who":null},{"_id":"5a20b6b6421aa90fe50c024d","createdAt":"2017-12-01T09:56:06.457Z","desc":"Log4a 是一个基于 mmap, 高性能、高可用的 Android 日志收集框架","images":["http://img.gank.io/81ee843e-e749-48b6-9401-329af87f3051"],"publishedAt":"2017-12-06T08:49:34.303Z","source":"web","type":"Android","url":"https://github.com/pqpo/Log4a","used":true,"who":"Linmin Qiu"},{"_id":"5a2282ac421aa90fe7253688","createdAt":"2017-12-02T18:38:36.632Z","desc":"开源整理：有趣的Android动画交互设计","publishedAt":"2017-12-06T08:49:34.303Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/zjOWVVwZiDDrn9pwdgRXMw","used":true,"who":"D_clock"},{"_id":"5a25f42b421aa90fe50c0256","createdAt":"2017-12-05T09:19:39.759Z","desc":"Android性能优化之包体压缩，一篇文章教你玩转优化App","publishedAt":"2017-12-06T08:49:34.303Z","source":"web","type":"Android","url":"https://w.url.cn/s/A1IEDM3","used":true,"who":"codeGoogler"},{"_id":"5a2627aa421aa90fef203582","createdAt":"2017-12-05T12:59:22.876Z","desc":"深入源码，了解属性动画运行原理","publishedAt":"2017-12-06T08:49:34.303Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/27ac2a314fae","used":true,"who":"Kai Sun"},{"_id":"5a1f5e55421aa90fe725367f","createdAt":"2017-11-30T09:26:45.819Z","desc":"Activity、View、Window的理解一篇文章就够了","publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488456&idx=1&sn=4136c0a098ca71583534fb203738accc","used":true,"who":"陈宇明"},{"_id":"5a23cf60421aa90fef203577","createdAt":"2017-12-03T18:18:08.892Z","desc":"图解HashMap","images":["http://img.gank.io/35cd05e6-d27a-43ac-93e5-5e4461665ace"],"publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/12/03/%E5%9B%BE%E8%A7%A3HashMap(%E4%B8%80)/","used":true,"who":"HuYounger"},{"_id":"5a24b074421aa90fe725368a","createdAt":"2017-12-04T10:18:28.419Z","desc":"无需root，不用刷机也能使用xposed！","images":["http://img.gank.io/de2b867c-ca59-46f8-a5f9-e15c5303903f"],"publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"http://weishu.me/2017/12/02/non-root-xposed/","used":true,"who":"weishu"},{"_id":"5a255af2421aa90fe2f02cb2","createdAt":"2017-12-04T22:25:54.192Z","desc":"图解HashMap(二)","publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/12/04/%E5%9B%BE%E8%A7%A3HashMap(%E4%BA%8C)/","used":true,"who":"HuYounger"},{"_id":"5a256123421aa90fef203580","createdAt":"2017-12-04T22:52:19.981Z","desc":"MultiTypeTemplates 插件发布 v1.4.0 版本，新增加支持自动生成内部类代码功能，有助于更方便实现某些代码内聚","publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"https://github.com/drakeet/MultiTypeTemplates#action","used":true,"who":"drakeet"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean extends MultiItem implements Serializable{
        /**
         * _id : 5a1d276e421aa90fe7253676
         * createdAt : 2017-11-28T17:07:58.240Z
         * desc : 业界首创Android Lint增量扫描实战纪要
         * publishedAt : 2017-12-06T08:49:34.303Z
         * source : web
         * type : Android
         * url : http://www.jianshu.com/p/4833a79e9396
         * used : true
         * who : null
         * images : ["http://img.gank.io/81ee843e-e749-48b6-9401-329af87f3051"]
         */

        private String createdAt;
        private String desc;
        private String publishedAt;
        private String type;
        private String url;
        private String who;
        private List<String> images;


        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }



        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
