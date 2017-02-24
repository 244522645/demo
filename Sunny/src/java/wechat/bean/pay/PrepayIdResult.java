package wechat.bean.pay;

import wechat.bean.BaseResult;

/**
 * 生成预支付订单 返回结果 返回客户端
 * 
 * @author buddy
 * 
 */
public class PrepayIdResult extends BaseResult {
	private String appid;
	private String appkey;
	private String noncestr;
	// @JSONField(name="package")
	private String package_;
	private String partnerid;
	private String prepayid;
	private String timestamp;
	private String sign;

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getPackage_() {
		return package_;
	}

	public void setPackage_(String package_) {
		this.package_ = package_;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
