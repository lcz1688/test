package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.main.model.Tab3Model;
import cn.lc.model.ui.main.presenter.Tab3Presenter;
import cn.lc.model.ui.main.view.Tab3View;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab3Fragment extends BaseFragment<Tab3Model, Tab3View, Tab3Presenter> implements Tab3View {


    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab3);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);

    }

    @Override
    public Tab3Model createModel() {
        return null;
    }

    @Override
    public Tab3Presenter createPresenter() {
        return new Tab3Presenter();
    }
}
