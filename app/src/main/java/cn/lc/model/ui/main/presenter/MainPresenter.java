package cn.lc.model.ui.main.presenter;

import cn.lc.model.ui.main.model.MainModel;
import cn.lc.model.ui.main.view.MainView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class MainPresenter extends MvpRxPresenter<MainModel, MainView> {


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
