package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;
@Entity
@Table(name = "sun_token")
public  class WxToken extends BaseEntity{

	@Column(name = "token")	
	private String token;
	@Column(name = "expires_in",columnDefinition="INT default 0")
	private int expires_in;//修改时间
	@Column(name = "type")	
	private String type="accessToken";
	
	@Column(name = "erro")	
	private String erro="";

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
