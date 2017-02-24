package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * member_tj(会员空间信息)
 */
@Entity
@Table(name = "sun_member_tj")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SunMemberTj{

//	member_tj(会员空间信息)
	@Id
	@Column(name = "id", length = 100,updatable=false)
	private String id;
	@Column(name = "send", nullable = false)
	private Integer send=0;
	@Column(name = "receive", nullable = false)
	private Integer receive=0;
	@Column(name = "sunb", nullable = false)
	private Integer sunb=0;
	@Column(name = "tougao", nullable = false)
	private Integer tougao=0;
	@Column(name = "tree", nullable = false)
	private Integer tree=0;
	
	@Column(name = "letter_send", nullable = false)
	private Integer letterSend=0;
	@Column(name = "letter_receive", nullable = false)
	private Integer letterReceive=0;
	@Column(name = "card", nullable = false)
	private Integer card=0;
	
	public Integer getLetterSend() {
		return letterSend;
	}

	public void setLetterSend(Integer letterSend) {
		this.letterSend = letterSend;
	}

	public Integer getLetterReceive() {
		return letterReceive;
	}

	public void setLetterReceive(Integer letterReceive) {
		this.letterReceive = letterReceive;
	}

	public Integer getCard() {
		return card;
	}

	public void setCard(Integer card) {
		this.card = card;
	}

	public Integer getTougao() {
		return tougao;
	}

	public void setTougao(Integer tougao) {
		this.tougao = tougao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSend() {
		return send;
	}

	public void setSend(Integer send) {
		this.send = send;
	}

	public Integer getReceive() {
		return receive;
	}

	public void setReceive(Integer receive) {
		this.receive = receive;
	}

	public Integer getSunb() {
		return sunb;
	}

	public void setSunb(Integer sunb) {
		this.sunb = sunb;
	}

	public Integer getTree() {
		return tree;
	}

	public void setTree(Integer tree) {
		this.tree = tree;
	}

}