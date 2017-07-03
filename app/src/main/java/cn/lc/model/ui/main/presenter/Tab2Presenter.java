package cn.lc.model.ui.main.presenter;

import cn.lc.model.ui.main.model.Tab2Model;
import cn.lc.model.ui.main.view.Tab2View;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab2Presenter extends MvpRxPresenter<Tab2Model, Tab2View> {


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
