package cn.lc.model.ui.login.bean;


import cn.lc.model.framework.base.BaseResponse;

/**
 *  验证码
 */
public class CaptchaBean extends BaseResponse {

    public CaptchaBean data;
    public String captcha;
    public String uid;
}
