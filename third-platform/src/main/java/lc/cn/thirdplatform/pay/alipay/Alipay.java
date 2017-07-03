package lc.cn.thirdplatform.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

import mvp.cn.util.LogUtil;


public class Alipay {

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    private Activity act;


    public Alipay(Activity ct) {
        this.act = ct;
    }


    /**
     * 支付
     */
    public void doPay(final String payLink) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(act);
                // 调用支付接口，获取支付结果
                Map<String, String> result = alipay.payV2(payLink, true);
                LogUtil.log("payLink==" + payLink);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 支付成功
     */
    protected void doResultOK() {
//		CommonReceiver.getInstance().sendBroadCast(act, CommonReceiver.ORDER_STATUS_CHANGED);
        act.finish();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(act, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(act, "支付失败-" + resultStatus, Toast.LENGTH_SHORT).show();
                        LogUtil.log("resultInfo::" + msg.obj);
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(act, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

}
