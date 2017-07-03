package cn.lc.model.ui.login.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.login.model.RegistStep1Model;
import cn.lc.model.ui.login.presenter.RegistStep1Presenter;
import cn.lc.model.ui.login.view.RegistStep1View;
import mvp.cn.util.CommonUtil;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;


public class RegistStep1Activity extends BaseActivity<RegistStep1Model,RegistStep1View,RegistStep1Presenter> {


    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_getcode)
    Button btnGetcode;
    @BindView(R.id.cb_agreen)
    CheckBox cbAgreen;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.tv_agreen)
    TextView tvAgreen;

    private Handler handler = new Handler();
    public static final int MAX_TIME = 60;// 按钮 60秒内不能点击
    private int totalSecond = MAX_TIME;// 按钮 60秒内不能点击
    private int from = -1;
    private String mobile;
    /**
     * 服务器返回的验证码
     */
    private String mCaptchaServer;
    private String thirdType;
    private String thirdNum;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_regist);
        ButterKnife.bind(this);
    }


    @Override
    public void initView() {
        tvAgreen.setText("注册即同意<<Classical Music服务协议>>");
        mTitleBar.setBack(true);
        getPerfromData();
    }

    @Override
    public RegistStep1Presenter createPresenter() {
        return null;
    }

    @Override
    public RegistStep1Model createModel() {
        return null;
    }

    private void getPerfromData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getInt("from");
            thirdType = extras.getString("thirdType");
            thirdNum = extras.getString("thirdNum");
            if (from == Constants.FORGET) {
//                cbAgreen.setVisibility(View.INVISIBLE);
                mTitleBar.setTitle("找回密码");
            } else if (from == Constants.REGIST) {
                mTitleBar.setTitle("注册");
            } else if (from == Constants.BIND) {
                mTitleBar.setTitle("绑定手机号");
            }
        }
    }




    @OnClick({R.id.btn_getcode, R.id.tv_agreen, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_getcode:
                doGetCode();
                break;
            case R.id.tv_agreen:
                turnToAgreen();
                break;
            case R.id.bt_next:
                doNext();
                break;
        }
    }


    /**
     * 下一步
     */
    public void doNext() {
        String captcha = etCode.getText().toString().trim();
        mobile = etPhone.getText().toString().trim();
        if (!isPhoneChecked(mobile)) {
            return;
        }
        if (TextUtils.isEmpty(captcha)) {
            showToast("请填写验证码");
            return;
        }
        if (!captcha.equals(mCaptchaServer)) {
            showToast("验证码错误");
            return;
        }
        if (!cbAgreen.isChecked()) {
            showToast("请阅读并同意服务协议");
            return;
        }

        CommonUtil.closeSoftKeyboard(this, etCode);
        if (from == Constants.BIND) {
//            doBindRequest(mobile, captcha);
        } else {
//            doNextRequest(mobile, captcha);
            turnToPwdSet(captcha);
        }
    }

    private void turnToPwdSet(String captcha) {
        Bundle b = new Bundle();
        b.putString("mobile", mobile);
        b.putString("captcha", captcha);
        b.putString("thirdType", thirdType);
        b.putString("thirdNum", thirdNum);
        b.putInt("from", from);
        UIManager.turnToAct(RegistStep1Activity.this, RegistStep2Activity.class, b);
    }

    private void turnToAgreen() {
        UIManager.turnToAct(this, AgreeActivity.class);
    }

    /**
     * 返回
     */
    public void doBack() {
        finish();
    }

    /**
     * 获取验证码
     */
    public void doGetCode() {
        mobile = etPhone.getText().toString();
        if (!isPhoneChecked(mobile)) {
            return;
        }
//        doGetCodeRequest(mobile);
    }


    private void doTimer() {
        if (runnable == null) {
            runnable = new MyRunnable();
        }
        handler.post(runnable);
        btnGetcode.setClickable(false);
    }

    void stopTimmer() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
        }
        totalSecond = MAX_TIME;
        // 倒计时完成后让按钮可点击
        btnGetcode.setEnabled(true);
        btnGetcode.setClickable(true);
        btnGetcode.setText("重新获取验证码");
    }

    public MyRunnable runnable;




    public class MyRunnable implements Runnable {

        @SuppressLint("NewApi")
        @Override
        public void run() {
            handler.postDelayed(runnable, 1000);
            btnGetcode.setText(totalSecond + "s后重新发送");
            totalSecond--;
            if (totalSecond < 0) {
                stopTimmer();
            }
        }
    }


    /**
     * 手机号校验
     *
     * @param mobile
     * @return
     */
    private boolean isPhoneChecked(String mobile) {
        if (StringUtil.isNullOrEmpty(mobile)) {
            showToast("请输入手机号");
            return false;
        }
        if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
            showToast("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * 获取验证码
     *
     * @param mobile
     */
    String flag;

    /*private void doGetCodeRequest(final String mobile) {
        btNext.setClickable(false);
        showProgressDialog();
        String userId = null;
        if (from == Constants.BIND) {
            userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        }


        if (from == Constants.REGIST)
            flag = "0";
        else
            flag = "1";


        Request request = RequestMaker.getInstance().getCodeRequest(mobile, userId, flag);
        getNetWorkDate(request, new SubBaseParser<>(CaptchaBean.class), new OnCompleteListener<CaptchaBean>(this) {
            @Override
            public void onSuccessed(CaptchaBean result, String resultString) {
                showToast("获取验证码成功");
                if (result.data != null) {
                    mCaptchaServer = result.data.captcha;
                    etCode.setText(mCaptchaServer);
                }
                doTimer();
            }

            @Override
            public void onCompleted(CaptchaBean result, String resultString) {
                dismissProgressDialog();
                btNext.setClickable(true);
            }

        });
    }


    *//**
     * 校验验证码,下一步
     *
     * @param captcha
     * @param mobile
     *//*
    private void doBindRequest(final String mobile, final String captcha) {
        showProgressDialog();
        String userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        Request request = RequestMaker.getInstance().doBindRequest(thirdType, thirdNum, mobile, captcha, "0", null);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                SoftApplication.softApplication.setUserInfo(result.data);
                finishActivityAndAboveIt(LoginActivity.class.getName());
            }


            @Override
            public void onCodeError(UserResponse result) {
                if (result.errCode == -7) {
                    turnToPwdSet(captcha);
                } else {
                    super.onCodeError(result);
                }
            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
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
    private void doNextRequest(final String mobile, final String captcha) {
        showProgressDialog();
        Request request = RequestMaker.getInstance().getCheckCaptchaRequest(mobile, captcha);
        getNetWorkDate(request, new SubBaseParser<SubBaseResponse>(SubBaseResponse.class), new OnCompleteListener<SubBaseResponse>(this) {
            @Override
            public void onSuccessed(SubBaseResponse result, String resultString) {
                Bundle b = new Bundle();
                b.putString("mobile", mobile);
                b.putString("captcha", captcha);
                b.putInt("from", from);
                UIManager.turnToAct(RegistStep1Activity.this, RegistStep2Activity.class, b);
            }

            @Override
            public void onCompleted(SubBaseResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }*/

}
