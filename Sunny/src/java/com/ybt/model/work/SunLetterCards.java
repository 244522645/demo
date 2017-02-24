package com.ybt.model.work;
// default package

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.model.BaseEntity;

/**   
 * 信件-卡 关联
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_letter_card")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunLetterCards extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "letter_id")
	protected  SunLetter letterId;            //	邮件id
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "card_id")
	protected  SunCard card;          //	照片 id
	public SunLetter getLetterId() {
		return letterId;
	}
	public void setLetterId(SunLetter letterId) {
		this.letterId = letterId;
	}
	public SunCard getCard() {
		return card;
	}
	public void setCard(SunCard card) {
		this.card = card;
	}
	
}