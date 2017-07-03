package cn.lc.model.ui.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TabBar;
import cn.lc.model.ui.main.fragment.Tab1Fragment;
import cn.lc.model.ui.main.fragment.Tab2Fragment;
import cn.lc.model.ui.main.fragment.Tab3Fragment;
import cn.lc.model.ui.main.fragment.Tab4Fragment;
import cn.lc.model.ui.main.model.MainModel;
import cn.lc.model.ui.main.modelimpl.MainModelImpl;
import cn.lc.model.ui.main.presenter.MainPresenter;
import cn.lc.model.ui.main.view.MainView;

public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter> implements MainView {

    @BindView(R.id.m_frameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.m_bottom)
    TabBar mBottom;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    public void initView() {
        fragments.add(new Tab1Fragment());
        fragments.add(new Tab2Fragment());
        fragments.add(new Tab3Fragment());
        fragments.add(new Tab4Fragment());
        //默认选中的界面
        mBottom.setOnItemChangedListener(onBottomItemClickListener);
        mBottom.setItemChecked(0);
    }

    /**
     * 底部导航栏的点击
     * 未登录状态下
     */
    TabBar.OnItemChangedListener onBottomItemClickListener = new TabBar.OnItemChangedListener() {
        @Override
        public boolean onItemChecked(int position) {
//            if (position == 2) {
//                if (!isLogin()) {
//                    return true;
//                }
//            }
            changeFragment(fragments.get(position));
            return false;
        }
    };


    private void changeFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.m_frameLayout, f);
        ft.commit();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public MainModel createModel() {
        return new MainModelImpl();
    }


}
