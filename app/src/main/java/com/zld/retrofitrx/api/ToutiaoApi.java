package com.zld.retrofitrx.api;

import com.zld.retrofitrx.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lingdong on 2017/12/6.
 */

public interface ToutiaoApi {

    /**
     * http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
     */
    @GET("toutiao/index")
    Observable<NewsBean> getToutiaoNews(@Query("type")String type, @Query("key")String key);
}
