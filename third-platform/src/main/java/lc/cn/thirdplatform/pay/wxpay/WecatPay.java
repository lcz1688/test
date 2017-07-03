package lc.cn.thirdplatform.pay.wxpay;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import mvp.cn.util.LogUtil;


public class WecatPay {

    private Activity ct;
    private IWXAPI api;

    public static final String APP_ID = "wx4ec0b35473fa09da";
    public static final String APP_SECRET = "fdbe92e06e843b0d597d0229e04c0fad";

    public WecatPay(Activity act) {
        this.ct = act;
        api = WXAPIFactory.createWXAPI(act, APP_ID);
        api.registerApp(APP_ID);
    }

    public void doPay(String content) {

        Log.e("get server pay params:", content);
        JSONObject json;
        try {
            json = new JSONObject(content);
            if (json != null) {
                json = (JSONObject) json.get("data");
                if (null != json) {
                    PayReq req = new PayReq();
                    // req.appId = "wxf8b4f85f3a794e77"; // 测试用appId
                    req.appId = json.getString("appid");
                    req.partnerId = json.getString("partnerid");
                    req.prepayId = json.getString("prepayid");
                    req.nonceStr = json.getString("noncestr");
                    req.timeStamp = json.getString("timestamp");
                    req.packageValue = json.getString("package");
                    req.sign = json.getString("paySign");
                    req.extData = "app data"; // optional
                    LogUtil.log("appid==="+json.getString("appid"));
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);
                } else {
                    Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
                    Toast.makeText(ct, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
