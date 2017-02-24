package com.ybt.model.work;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qq.connect.utils.http.BASE64Encoder;
import com.ybt.model.BaseEntity;

/**   
 * 推广二维码
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_qrcode")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunQrcode extends BaseEntity{

	@Column(name = "name")
	protected String name;            	// 名字
	@Column(name = "value")
	protected Long value;            	// 值
	@Column(name = "qrcode")
	protected byte[] qrcode;            	//	二维码
	@Column(name = "ticket")
	protected String ticket;            	//	二维码ticket
	@Column(name = "content")
	protected String content;            	//	二维码 内容
	@Column(name = "type",columnDefinition="INT default 0")
	protected int type = 0;       			// 0 临时 1 永久
	@Column(name = "count",columnDefinition="INT default 0")
	protected int count = 0;       			// 次数
	@Column(name = "flow",columnDefinition="INT default 0")
	protected int flow = 0;       			// 流量
	@Column(name = "status",columnDefinition="INT default 0")
	protected int status = 0;       		// 状态  ,1已支付
	@Column(name = "module")
	protected String module;       //模块： order,daiyan 等
	
	
	@Column(name = "expire_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expireTime;//有效时间
	
	@Transient
	protected String img;
	@SuppressWarnings("static-access")
	public String getImg() {
		if(qrcode==null){
			return "";// 返回
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(qrcode);// 返回
	}
	public void setImg(String img) {
		this.img = img;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getQrcode() {
		
		
		return qrcode;
	}

	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
}