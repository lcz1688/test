package cn.lc.model.ui.login.activity;


import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.model.MainModel;
import cn.lc.model.ui.main.presenter.MainPresenter;
import cn.lc.model.ui.main.view.MainView;


public class AgreeActivity extends BaseActivity<MainModel,MainView,MainPresenter> implements MainView{


    // Content View Elements

    private TitleBar mTitleBar;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_agree);
    }


    @Override
    public void initView() {
        bindViews();
    }

    // End Of Content View Elements

    private void bindViews() {
        mTitleBar = (TitleBar) findViewById(R.id.mTitleBar);
        mTitleBar.setBack(true);
        mTitleBar.setTitle("阅读协议");
    }


    @Override
    public MainModel createModel() {
        return null;
    }

    @Override
    public MainPresenter createPresenter() {
        return null;
    }

}
