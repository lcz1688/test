package cn.lc.model.framework.network.retrofit;

import java.util.Map;

import cn.lc.model.ui.home.bean.LoginBean;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetAPI {

    @FormUrlEncoded
    @POST(NetUrl.signup)
    Observable<LoginBean> login (@FieldMap Map<String,Object> map);

}
