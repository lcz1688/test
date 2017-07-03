package cn.lc.model.framework.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.widget.CustomerDialog;
import cn.lc.model.ui.login.activity.LoginActivity;
import mvp.cn.common.MvpView;
import mvp.cn.rx.MvpModel;
import mvp.cn.rx.MvpRxFragment;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;

/**
 * Created by hh on 2016/5/18.
 */
public abstract class BaseFragment<M extends MvpModel, V extends MvpView, P extends MvpRxPresenter<M, V>>
        extends MvpRxFragment<M,V,P> {


    protected SoftApplication softApplication;
    private CustomerDialog progressDialog;
    private View inflate;
    private int contentViewRes = -1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        softApplication = SoftApplication.softApplication;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (inflate == null) {
            LogUtil.log(getClass().getName() + "初始化");
            setContentLayout(savedInstanceState);
            if (contentViewRes == -1) {
                LogUtil.log("未设置布局");
                return null;
            }
            inflate = inflater.inflate(contentViewRes, null);
            ButterKnife.bind(this,inflate);
            if (inflate != null)
                initView(inflate);
        } else {
            LogUtil.log(getClass().getName() + "再次加载,无需初始化");
        }

        return inflate;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log(getClass().getName() + "[onDestroy]");
//        NetChangeManager.newInstance(softApplication).removeMinitor(this);
    }

    public abstract void setContentLayout(Bundle savedInstanceState);

    public abstract void initView(View v);

    public void setContentView(int resId) {
        this.contentViewRes = resId;
    }

    @Override
    public void showToast(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showToastLong(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new CustomerDialog(getActivity(), R.style.dialog_style);
        progressDialog.setMessage("加载中...");
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public void showProgressDialog(String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new CustomerDialog(getActivity(), R.style.dialog_style);
        progressDialog.setMessage(msg);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public void dismissProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing() == true) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    /**
     * @return
     */
    public boolean isLogin() {
        if (softApplication.isLogin()) {
            return true;
        }
        UIManager.turnToAct(getActivity(), LoginActivity.class);
        return false;
    }
}
