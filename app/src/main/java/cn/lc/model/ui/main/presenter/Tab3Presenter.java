package cn.lc.model.ui.main.presenter;

import cn.lc.model.ui.main.model.Tab3Model;
import cn.lc.model.ui.main.view.Tab3View;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab3Presenter extends MvpRxPresenter<Tab3Model, Tab3View> {


    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        getModel().login("", "").subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object loginBean) {

            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
