package lc.cn.thirdplatform.pay;

import android.app.Activity;


public class PayActivity extends Activity {


    /*@Override
    public void setContentLayout() {
        setContentView(R.layout.pay);
    }

    @Override
    public void initView() {
        initData();
        initTitle();
    }

    private void initTitle() {
        mTitleBar.setTitle("支付");
        mTitleBar.setBack(true);
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderId = extras.getString("orderId");
            orderType = extras.getString("orderType");
            orderMoney = extras.getString("orderMoney");
            tvPrice.setText(String.format("%s元", orderMoney));
        }
    }

    @Override
    public void onNetChanged(boolean oldStatus, boolean newStatus) {

    }

    @OnClick({R.id.rl_wecat, R.id.rl_zhifubao, R.id.bt_pay, R.id.rl_qianbao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_wecat:
                payType = WECAT;
                setCheck(ivWecat);
                break;
            case R.id.rl_zhifubao:
                payType = ZHIFUBAO;
                setCheck(ivZhifubao);
                break;
            case R.id.rl_qianbao:
                payType = QIANBAO;
                setCheck(ivQianbao);
                break;
            case R.id.bt_pay:
                if (payType == QIANBAO) {
                    checkPwd();
                } else {
                    doPay();
                }
                break;
        }
    }


    public void setCheck(ImageView iv) {
        setNoCheck();
        iv.setImageResource(R.mipmap.pay_checked);
    }

    public void setNoCheck() {
        ivWecat.setImageResource(R.mipmap.pay_checknormal);
        ivZhifubao.setImageResource(R.mipmap.pay_checknormal);
        ivQianbao.setImageResource(R.mipmap.pay_checknormal);
    }


    *//**
     * 钱包是否设置了支付密码
     *//*
    private void checkPwd() {
        showProgressDialog();
        UserInfo userInfo = softApplication.getUserInfo();
        if (userInfo == null) {
            return;
        }
        Request request = RequestMaker.getInstance().getWalletRequest(userInfo.uid);
        getNetWorkDate(request, new SubBaseParser<>(WalletBean.class), new OnCompleteListener<WalletBean>(this) {

            @Override
            public void onSuccessed(WalletBean result, String resultString) {
                WalletBean bean = result.data;
                doApterCheckPwd(bean);
            }

            @Override
            public void onCompleted(WalletBean result, String resultString) {
                super.onCompleted(result, resultString);
                dismissProgressDialog();
            }
        });
    }

    private void doApterCheckPwd(WalletBean bean) {
        if (bean == null) {
            return;
        }
        if ("1".equals(bean.hadsetpass)) {
            UIManager.turnToActForresult(this, MyWalletPayActivity.class, QIANBAO_PAY_REQUSTCODE, null);
            return;
        }
        new AlertDialog.Builder(this).setTitle("提示").setMessage("未设置支付密码,请前往设置").
                setPositiveButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UIManager.turnToAct(getActivity(), MyWalletCaptchaActivity.class);
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QIANBAO_PAY_REQUSTCODE && resultCode == RESULT_OK) {
            password = data.getStringExtra("pwd");
            if (!StringUtil.isNullOrEmpty(password)) {
                doPay();
            }
        }
    }

    *//**
     * 支付
     *//*
    public void doPay() {

        if (!softApplication.isLogin()) {
            UIManager.turnToAct(this, LoginActivity.class);
            return;
        }
        showProgressDialog("初始化订单信息...");
        String uid = softApplication.getUserInfo().uid;
        Request request = RequestMaker.getInstance().doPayRequest(uid, payType, orderId, password);
        getNetWorkDate(request, new SubBaseParser<PayBean>(PayBean.class), new OnCompleteListener<PayBean>(this) {

            @Override
            public void onSuccessed(PayBean result, String resultString) {
                switch (payType) {
                    case WECAT:
                        new WecatPay(PayActivity.this).doPay(resultString);
                        break;
                    case ZHIFUBAO:
                        new Alipay(PayActivity.this).doPay(result.data.payLink);
                        break;
                    case QIANBAO:
                        EventBus.getDefault().post(new EventPay(0));
                        break;
                }
            }

            @Override
            public void onCompleted(PayBean result, String resultString) {
                dismissProgressDialog();
                super.onCompleted(result, resultString);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventPay eventPay) {
        if (eventPay.getErrCode() == 0) {
            Bundle b = new Bundle();
            b.putString("orderId", orderId);
            b.putString("orderType", orderType);
            UIManager.turnToAct(this, PaysucActivity.class, b);
            finish();
        } else {
            //支付失败
        }

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }*/
}
