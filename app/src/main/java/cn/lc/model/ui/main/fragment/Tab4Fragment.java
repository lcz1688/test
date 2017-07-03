package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.main.model.Tab4Model;
import cn.lc.model.ui.main.presenter.Tab4Presenter;
import cn.lc.model.ui.main.view.Tab4View;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab4Fragment extends BaseFragment<Tab4Model,Tab4View,Tab4Presenter> implements Tab4View{


    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab4);
    }

    @Override
    public void initView(View v) {

    }

    @Override
    public Tab4Model createModel() {
        return null;
    }

    @Override
    public Tab4Presenter createPresenter() {
        return new Tab4Presenter();
    }
}
