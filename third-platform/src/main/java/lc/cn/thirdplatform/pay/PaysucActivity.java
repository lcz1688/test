package lc.cn.thirdplatform.pay;


import android.app.Activity;

public class PaysucActivity extends Activity {
   /* @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.ln_suc)
    LinearLayout ln_suc;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_doc)
    TextView tv_doc;
    @BindView(R.id.tv_touxie)
    TextView tv_touxie;
    @BindView(R.id.tv_appo_date)
    TextView tv_appo_date;
    @BindView(R.id.tv_order_date)
    TextView tv_order_date;
    private String orderId;
    private String orderType;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.pay_suc);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        bindViews();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderId = extras.getString("orderId");
            orderType = extras.getString("orderType");
        }
        if (orderType.equals("1")) {
            ln_suc.setVisibility(View.VISIBLE);
            getpayResult();
        } else {
            ln_suc.setVisibility(View.INVISIBLE);
        }
    }


    private void bindViews() {
        mTitleBar.setBack(true);
        mTitleBar.setTitle("支付结果");
    }

    @OnClick({R.id.bt_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_order://
                Bundle b = new Bundle();
                if (orderType.equals("2")) {
                    MyAskOrderBean bean = new MyAskOrderBean();
                    bean.order = new MyAskOrderBean.Order();
                    bean.order.order_id = orderId;
                    b.putSerializable("bean", bean);
                    UIManager.turnToAct(this, MyOnlineAskDetailActivity.class, b);
                } else if (orderType.equals("1")) {
                    UIManager.turnToAct(this, ExpertInquiryActivity.class);
                }
                break;
        }
    }

    @Override
    public void onNetChanged(boolean oldStatus, boolean newStatus) {

    }


    private void getpayResult() {

        showProgressDialog();
        final Request request = RequestMaker.getInstance().getpayResult(softApplication.getUserInfo().uid, orderId, String.valueOf(1));
        getNetWorkDate(request, new SubBaseParser<>(PaySucBean.class), new OnCompleteListener<PaySucBean>(getActivity()) {
            @Override
            public void onSuccessed(PaySucBean result, String resultString) {
                tv_name.setText("尊敬的" + result.getData().getUserInfo().getNickname()
                        + "，您已成功预约问诊");
                tv_doc.setText(result.getData().getData().getDoctor().getDoc_name());
                tv_touxie.setText(result.getData().getData().getDoctor().getProfessonalrank());
                tv_appo_date.setText("预约日期：" + result.getData().getData().getOrder().getSchedule_date());
                tv_order_date.setText("下单时间：" + result.getData().getData().getOrder().getCreate_order_time());
                dismissProgressDialog();
            }

            @Override
            public void onCodeError(PaySucBean result) {
                super.onCodeError(result);
                ln_suc.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCompleted(PaySucBean result, String resultString) {
                dismissProgressDialog();
            }
        });
    }*/

}