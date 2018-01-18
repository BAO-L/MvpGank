package com.example.bao.mvpgank.model.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BAO on 2017/12/10.
 */

public class Daily  {

    /**
     * category : ["休息视频","iOS","Android","前端","瞎推荐","福利","App"]
     * error : false
     * results : {"Android":[{"_id":"5a027569421aa90fe7253610","createdAt":"2017-11-08T11:09:29.236Z","desc":"免费专栏推荐：小米 MIUI 系统工程师 的《从源码角度看 Android》","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzU4MjAzNTAwMA==&mid=2247483816&idx=1&sn=9129a1fff64c6da7dde9143dc6995ec1&chksm=fdbf32ffcac8bbe943e81a23a487e3caac0dbb64e18509e61b28666074234c58e820b1a0aeea#rd","used":true,"who":null},{"_id":"5a02d0d1421aa90fe2f02c37","createdAt":"2017-11-08T17:39:29.976Z","desc":"这可能是第二好的自定义 View 教程之绘制","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/7cea643df11b","used":true,"who":"LiuShilin"},{"_id":"5a0312a9421aa90fe7253615","createdAt":"2017-11-08T22:20:25.632Z","desc":"雕虫晓技(一) Android 组件化","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzI3MzMzNjgzMA==&mid=2247483674&idx=1&sn=fc93e05445a8fefcde0fdfb2c0145321&chksm=eb25918bdc52189d3ce82d7796ec95105927734d0cf47544126937d3d1255f41002f0cfb3afe&mpshare=1&scene=1&srcid=1106IA3DQP1u3IkOcCVwq1u2&key=5dba587a8b06ccca4ff30ff629f458ed05559774b37f3b9080982d26a7151db05c0324f15551f87029d3a11fee57d8fd51c5622f26b109f6f59a4c6c4ee811a840d7e9862b796b9dbdea8c14a1d32e9f&ascene=0&uin=NjM4NDkwNzgy&devicetype=iMac+MacBookAir7%2C2+OSX+OSX+10.12.4+build(16E195)&version=12010110&nettype=WIFI&fontScale=100&pass_ticket=wtm7nx0Fp2owsbSKcyoiIgV4pVu0Rw0GZicnXakuWo0tyipA8VCHAfpOkJGhp3yb","used":true,"who":"sloop"},{"_id":"5a03b2aa421aa90fe50c01e9","createdAt":"2017-11-09T09:43:06.493Z","desc":"我所理解的Android和iOS上的View","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/W9lRR4K3XRNTPvXf64qdhA","used":true,"who":"D_clock"},{"_id":"5a03ee2c421aa90fe7253619","createdAt":"2017-11-09T13:57:00.165Z","desc":"Kotlin 系列 - 从0到1 开发一个 App","images":["http://img.gank.io/35be6898-b5b8-4fa9-8b60-e49c6d6b998c"],"publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"http://caimuhao.com/2017/11/02/Learn-Kotlin-While-Developing-An-Android-App-Introduction/","used":true,"who":null},{"_id":"5a041483421aa90fe2f02c3f","createdAt":"2017-11-09T16:40:35.807Z","desc":"那些年遇到的奇葩后台写的奇葩接口","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/5a723f0b7c1e","used":true,"who":"阿韦"}],"App":[{"_id":"5a03ff66421aa90fef203514","createdAt":"2017-11-09T15:10:30.389Z","desc":"微信个人网页版API的微信机器人","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"App","url":"http://www.jianshu.com/p/ce911828dec5","used":true,"who":"joehe_cn"}],"iOS":[{"_id":"5a0010ce421aa90fe2f02c2b","createdAt":"2017-11-06T15:35:42.778Z","desc":"在实际项目中经常会遇到因方法调用频繁而导致的 UI 闪动问题和性能问题，这时用某种策略需要控制调用频率，以达到节流和防抖的效果。MessageThrottle 是我实现的一个 Objective-C 消息节流和防抖的轻量级工具库，使用便捷且业务无关。","images":["http://img.gank.io/1e0ec318-2d54-4967-beb8-2d894206feec"],"publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"iOS","url":"http://yulingtianxia.com/blog/2017/11/05/Objective-C-Message-Throttle-and-Debounce/","used":true,"who":"杨萧玉"}],"休息视频":[{"_id":"59ff2676421aa90fef203500","createdAt":"2017-11-05T22:55:50.174Z","desc":"细读经典 17: 十年过去，它依然是最好的国产喜剧电影","publishedAt":"2017-11-10T08:10:02.838Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av16046374/","used":true,"who":"LHF"}],"前端":[{"_id":"5a02d3c5421aa90fe2f02c38","createdAt":"2017-11-08T17:52:05.174Z","desc":"js2flowchart - a visualization library to convert any JavaScript code into beautiful SVG flowchart.","images":["http://img.gank.io/13746ca0-4ba3-4a01-9ccf-1768e9ed842a"],"publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"前端","url":"https://github.com/Bogdan-Lyashenko/js-code-to-svg-flowchart","used":true,"who":"鑫花璐放"},{"_id":"5a02deb6421aa90fe2f02c39","createdAt":"2017-11-08T18:38:46.901Z","desc":"簡易好用的 vue-router & vue-i18n 開發樣板","images":["http://img.gank.io/40862355-4687-45e6-a9de-4ff084c77557"],"publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"前端","url":"https://github.com/WeiChiaChang/vue-boilerplate/","used":true,"who":"WesleyChang"}],"瞎推荐":[{"_id":"5a03b292421aa90fe7253617","createdAt":"2017-11-09T09:42:42.844Z","desc":"Record and share your terminal sessions, the right way.","publishedAt":"2017-11-10T08:10:02.838Z","source":"chrome","type":"瞎推荐","url":"https://asciinema.org/","used":true,"who":"lxxself"},{"_id":"5a04453d421aa90fe50c01ed","createdAt":"2017-11-09T20:08:29.463Z","desc":"发现最适合您的产品与服务","publishedAt":"2017-11-10T08:10:02.838Z","source":"chrome","type":"瞎推荐","url":"http://www.mulumu.com/","used":true,"who":"lxxself"}],"福利":[{"_id":"5a03b502421aa90fe7253618","createdAt":"2017-11-09T09:53:06.802Z","desc":"11-9","publishedAt":"2017-11-10T08:10:02.838Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171109095254_dOw5qh_bluenamchu_9_11_2017_9_52_47_256.jpeg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }



    public static class ResultsBean {
        private List<TechNews.ResultsBean> Android;
        @SerializedName("福利")
        private List<TechNews.ResultsBean> Fuli;
        private List<TechNews.ResultsBean> iOS;
        private List<TechNews.ResultsBean> App;
        @SerializedName("休息视频")
        private List<TechNews.ResultsBean> Video;
        @SerializedName("前端")
        private List<TechNews.ResultsBean> Web;
        @SerializedName("瞎推荐")
        private List<TechNews.ResultsBean> Blind;

        public List<TechNews.ResultsBean> getApp() {
            return App;
        }

        public void setApp(List<TechNews.ResultsBean> app) {
            App = app;
        }

        public List<TechNews.ResultsBean> getVideo() {
            return Video;
        }

        public void setVideo(List<TechNews.ResultsBean> video) {
            Video = video;
        }

        public List<TechNews.ResultsBean> getWeb() {
            return Web;
        }

        public void setWeb(List<TechNews.ResultsBean> web) {
            Web = web;
        }

        public List<TechNews.ResultsBean> getBlind() {
            return Blind;
        }

        public void setBlind(List<TechNews.ResultsBean> blind) {
            Blind = blind;
        }

        public List<TechNews.ResultsBean> getiOS() {
            return iOS;
        }

        public void setiOS(List<TechNews.ResultsBean> iOS) {
            this.iOS = iOS;
        }

        public List<TechNews.ResultsBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<TechNews.ResultsBean> Android) {
            this.Android = Android;
        }

        public List<TechNews.ResultsBean> getFuli() {
            return Fuli;
        }

        public void setFuli(List<TechNews.ResultsBean> Fuli) {
            this.Fuli = Fuli;
        }


    }
}
