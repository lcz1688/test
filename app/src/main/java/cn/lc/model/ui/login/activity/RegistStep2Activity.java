package cn.lc.model.ui.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.lc.model.R;
import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.login.bean.UserResponse;
import cn.lc.model.ui.login.model.RegistStep2Model;
import cn.lc.model.ui.login.presenter.RegistStep2Presenter;
import cn.lc.model.ui.login.view.RegistStep2View;
import mvp.cn.util.CommonUtil;
import mvp.cn.util.CrcUtil;


public class RegistStep2Activity extends BaseActivity<RegistStep2Model, RegistStep2View, RegistStep2Presenter> implements View.OnClickListener {


    // Content View Elements

    private TitleBar mTitleBar;
    private EditText et_password;
    private EditText et_repassword;
    private Button bt_next;
    private String mMobile;
    private String mCptcha;
    private int from;
    private String thirdType;
    private String thirdNum;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_regist_2);
    }


    @Override
    public void initView() {
        bindViews();

        getPerformData();

        initLayout();
    }

    @Override
    public RegistStep2Model createModel() {
        return null;
    }

    @Override
    public RegistStep2Presenter createPresenter() {
        return null;
    }

    private void getPerformData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMobile = extras.getString("mobile");
            mCptcha = extras.getString("captcha");
            from = extras.getInt("from");
            thirdType = extras.getString("thirdType");
            thirdNum = extras.getString("thirdNum");
        }
    }

    private void initLayout() {
        mTitleBar.setBack(true);
        if (from == Constants.REGIST) {
            mTitleBar.setTitle("设置资料");
        } else if (from == Constants.FORGET) {
            mTitleBar.setTitle("设置密码");
        }
    }

    private void bindViews() {
        mTitleBar = (TitleBar) findViewById(R.id.mTitleBar);
        et_password = (EditText) findViewById(R.id.et_password);
        et_repassword = (EditText) findViewById(R.id.et_repassword);
        bt_next = (Button) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);


    }

    /**
     * 返回
     */
    public void doBack() {
        finish();
    }

    /**
     * 注册
     */
    public void doRegist() {
        String pwd1 = et_password.getText().toString().trim();
        String pwd2 = et_repassword.getText().toString().trim();

        if (!isOtherChecked(pwd1, pwd2)) {
            return;
        }
        CommonUtil.closeSoftKeyboard(this, et_password);

        if (from == Constants.BIND) {
//            doBindRequest(mMobile, mCptcha, pwd1);
        } else {
//            doResistRequest(mMobile, mCptcha, pwd1);
        }
    }

    /**
     * 其他校验
     *
     * @param pwd1
     * @param pwd2
     * @return
     */
    private boolean isOtherChecked(String pwd1, String pwd2) {

        if (TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)) {
            showToast("请输入密码");
            return false;
        } else if (pwd1.length() < 6) {
            showToast("密码长度不能小于6位");
            return false;
        } else if (pwd1.length() > 20) {
            showToast("密码长度不能大于20位");
            return false;
        }

        if (!pwd1.equals(pwd2)) {
            showToast("密码输入不一致");
            return false;
        }
        return true;
    }

    /**
     * 提交,设置密码,根据from来判断是忘记密码提交,还是注册提交
     *
     * @param pwd
     * @param captcha
     * @param mobile
     */
    /*private void doResistRequest(final String mobile, String captcha, String pwd) {
        String md5Pwd = null;
        try {
            md5Pwd = CrcUtil.MD5(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showProgressDialog();
        Request request = RequestMaker.getInstance().getRegistRequest(mobile, captcha, md5Pwd);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                showToast("注册成功");
                SoftApplication.softApplication.setUserInfo(result.data);
//                SoftApplication.softApplication.setSignId(result.token);

                if (from == Constants.REGIST) {
                    UIManager.turnToAct(RegistStep2Activity.this, CompleteInfoActivity.class);
                    finishActivityAndAboveIt(LoginActivity.class.getName());
                } else if (from == Constants.FORGET) {
                    //关闭前边的两个界面
                    finishActivityAndAboveIt(RegistStep1Activity.class.getName());
                }

            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                super.onCompleted(result, resultString);
                dismissProgressDialog();
            }
        });
    }


    *//**
     * 校验验证码,下一步
     *
     * @param captcha
     * @param mobile
     *//*
    private void doBindRequest(final String mobile, final String captcha, String pwd) {
        showProgressDialog();
        String userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        Request request = RequestMaker.getInstance().doBindRequest(thirdType, thirdNum, mobile, captcha, "1", pwd);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                SoftApplication.softApplication.setUserInfo(result.data);
                finishActivityAndAboveIt(LoginActivity.class.getName());
            }


            @Override
            public void onCodeError(UserResponse result) {
                super.onCodeError(result);
            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }*/


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                doRegist();
                break;
        }
    }



}
