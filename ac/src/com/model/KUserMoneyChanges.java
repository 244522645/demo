package com.model;

/**
 * KUserMoneyChanges entity. @author MyEclipse Persistence Tools
 */

public class KUserMoneyChanges implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//����
	private Integer userId;//�û�ID
	private Integer cardKindsId;//��Ʒ����
	private Double money;//���׽��
	private Integer transactionType;//����Ϊ1���˿�Ϊ2
	private String transactionDate;//����ʱ��
	private Integer cardListId;//��ʱ����
	private Double beforeBalance;//����ǰ���
	private Double afterBalance;//���׺����

	// Constructors

	/** default constructor */
	public KUserMoneyChanges() {
	}

	/** full constructor */
	public KUserMoneyChanges(Integer userId, Integer cardKindsId, Double money,
			Integer transactionType, String transactionDate,
			Integer cardListId, Double beforeBalance, Double afterBalance) {
		this.userId = userId;
		this.cardKindsId = cardKindsId;
		this.money = money;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.cardListId = cardListId;
		this.beforeBalance = beforeBalance;
		this.afterBalance = afterBalance;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCardKindsId() {
		return this.cardKindsId;
	}

	public void setCardKindsId(Integer cardKindsId) {
		this.cardKindsId = cardKindsId;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getCardListId() {
		return this.cardListId;
	}

	public void setCardListId(Integer cardListId) {
		this.cardListId = cardListId;
	}

	public Double getBeforeBalance() {
		return this.beforeBalance;
	}

	public void setBeforeBalance(Double beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public Double getAfterBalance() {
		return this.afterBalance;
	}

	public void setAfterBalance(Double afterBalance) {
		this.afterBalance = afterBalance;
	}

}