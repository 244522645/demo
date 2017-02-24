package com.model;

/**
 * KCardOrders entity. @author MyEclipse Persistence Tools
 */

public class KCardOrders implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1649469909863556975L;
	private Integer id;            // 主键
	private String odersDate;      // 日期
	private String salesPerson;    // 销售员
	private String invoiceTitle;   // 发票抬头
	private Integer cardKindsId;   // 关联K_CardKinds表主键
	private Integer dianShu;       // 点数
	private Double danJia;         // 单价
	private Integer shuLiang;      // 数量
	private Double money;          // 金额
	private String startCardNum;   // 开始卡号
	private String endCardNum;		// 结束卡号
	private String closingDate;		// 截至日期
	private Integer invoiceId;		// 发票ID,关联发票表主键,0表示未开发票
	private Double invoiceMoney;	// 发票金额
	private String invoiceContent;	// 发票内容
	private Integer invoiceCode;	// 发票号码
	private String daiLi;			// 代理名称
	private Integer userId;			// 关联用户表主键
	private String cardNumHead;		// 号段
	private String voucherNum;		// 凭证号
	private String operationRecord;// 操作记录
	private Integer cardOrdersType;// 操作类型,1表示开卡,2表示充值,3表示退卡
	private Double kanLiJia;		// 刊例价
	private String memo;			// 备注

	/** default constructor */
	public KCardOrders() {
	}

	/** full constructor */
	public KCardOrders(String odersDate, String salesPerson, String invoiceTitle, Integer cardKindsId, Integer dianShu,
			Double danJia, Integer shuLiang, Double money, String startCardNum, String endCardNum, String closingDate,
			Integer invoiceId, Double invoiceMoney, String invoiceContent, Integer invoiceCode, String daiLi,
			Integer userId, String cardNumHead, String voucherNum, String operationRecord, Integer cardOrdersType,
			Double kanLiJia, String memo) {
		this.odersDate = odersDate;
		this.salesPerson = salesPerson;
		this.invoiceTitle = invoiceTitle;
		this.cardKindsId = cardKindsId;
		this.dianShu = dianShu;
		this.danJia = danJia;
		this.shuLiang = shuLiang;
		this.money = money;
		this.startCardNum = startCardNum;
		this.endCardNum = endCardNum;
		this.closingDate = closingDate;
		this.invoiceId = invoiceId;
		this.invoiceMoney = invoiceMoney;
		this.invoiceContent = invoiceContent;
		this.invoiceCode = invoiceCode;
		this.daiLi = daiLi;
		this.userId = userId;
		this.cardNumHead = cardNumHead;
		this.voucherNum = voucherNum;
		this.operationRecord = operationRecord;
		this.cardOrdersType = cardOrdersType;
		this.kanLiJia = kanLiJia;
		this.memo = memo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOdersDate() {
		return this.odersDate;
	}

	public void setOdersDate(String odersDate) {
		this.odersDate = odersDate;
	}

	public String getSalesPerson() {
		return this.salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public Integer getCardKindsId() {
		return this.cardKindsId;
	}

	public void setCardKindsId(Integer cardKindsId) {
		this.cardKindsId = cardKindsId;
	}

	public Integer getDianShu() {
		return this.dianShu;
	}

	public void setDianShu(Integer dianShu) {
		this.dianShu = dianShu;
	}

	public Double getDanJia() {
		return this.danJia;
	}

	public void setDanJia(Double danJia) {
		this.danJia = danJia;
	}

	public Integer getShuLiang() {
		return this.shuLiang;
	}

	public void setShuLiang(Integer shuLiang) {
		this.shuLiang = shuLiang;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getStartCardNum() {
		return this.startCardNum;
	}

	public void setStartCardNum(String startCardNum) {
		this.startCardNum = startCardNum;
	}

	public String getEndCardNum() {
		return this.endCardNum;
	}

	public void setEndCardNum(String endCardNum) {
		this.endCardNum = endCardNum;
	}

	public String getClosingDate() {
		return this.closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Double getInvoiceMoney() {
		return this.invoiceMoney;
	}

	public void setInvoiceMoney(Double invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public Integer getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setInvoiceCode(Integer invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getDaiLi() {
		return this.daiLi;
	}

	public void setDaiLi(String daiLi) {
		this.daiLi = daiLi;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCardNumHead() {
		return this.cardNumHead;
	}

	public void setCardNumHead(String cardNumHead) {
		this.cardNumHead = cardNumHead;
	}

	public String getVoucherNum() {
		return this.voucherNum;
	}

	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}

	public String getOperationRecord() {
		return this.operationRecord;
	}

	public void setOperationRecord(String operationRecord) {
		this.operationRecord = operationRecord;
	}

	public Integer getCardOrdersType() {
		return this.cardOrdersType;
	}

	public void setCardOrdersType(Integer cardOrdersType) {
		this.cardOrdersType = cardOrdersType;
	}

	public Double getKanLiJia() {
		return this.kanLiJia;
	}

	public void setKanLiJia(Double kanLiJia) {
		this.kanLiJia = kanLiJia;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}