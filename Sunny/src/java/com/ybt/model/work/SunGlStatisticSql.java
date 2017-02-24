package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;

@Entity
@Table(name = "sun_gl_statistic_sql")
public class SunGlStatisticSql extends BaseEntity{
	
	private String sqlCode;   				// 主键  = 类别代码_CODE 如：goods_statistic
	private String typeCode;   				// 类别代码
	private String type;   				// 类别
	private String title;   		// 标题
	private String sqlBody;   		// 统计查询sql主体
	private String headerCHS;   	// 结果表头（页面显示名）
	private String headerEN;   		// 英文表头（数据库字段名）
	private String bz;   				// 备注

	@Column(name = "title", length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "sql_body", length = 2000)
	public String getSqlBody() {
		return sqlBody;
	}
	public void setSqlBody(String sqlBody) {
		this.sqlBody = sqlBody;
	}
	
	@Column(name = "header_CHS", length = 500)
	public String getHeaderCHS() {
		return headerCHS;
	}

	public void setHeaderCHS(String headerCHS) {
		this.headerCHS = headerCHS;
	}

	@Column(name = "header_EN", length = 500)
	public String getHeaderEN() {
		return headerEN;
	}

	public void setHeaderEN(String headerEN) {
		this.headerEN = headerEN;
	}
	
	@Column(name = "bz", length = 100)
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name = "SQL_CODE")
	public String getSqlCode() {
		return sqlCode;
	}

	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}

	@Column(name = "TYPE_CODE")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
