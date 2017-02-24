package wechat.bean.pay;

import wechat.bean.BaseResult;
/**
 * 生成预支付订单 返回结果
 * @author buddy
 *
 */
public class Prepayid extends BaseResult {
	private String prepayid;

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
}
