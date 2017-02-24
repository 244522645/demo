package com.ybt.common.bean;
/**
 * 微信 全局返回码Model 
 * http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html 查询全局返回码说明
 * @author buddy
 *
 */
public class WXErrorCodeModel {
    private String errcode;//全局返回码
    private String errmsg;//说明
     
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}