package com.ybt.web.tags;

import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.apache.poi.ss.formula.functions.T;

import com.ybt.common.util.Filtration.FiledType;
import com.ybt.common.util.Filtration.MatchType;

/**
 * 选择框标签 combobox
 * @author Buddy
 *
 */
public class TagSearchSelectDict extends TagSearch {
	protected static final long serialVersionUID = 1L;
	/**
	 * 输入方式 选择方式
	 */
	protected String inputType="combobox";
	
	/**
	 * 字典类型
	 */
	protected String typeCode = null;
	
	@Override
	public int doStartTagInternal() throws Exception {
		try {
			Enum.valueOf(MatchType.class, matchType);
		} catch (Exception e) {
			throw new IllegalArgumentException("filter名称" + matchType + "没有按规则编写,无法得到属性比较类型.", e);
		}
		try {
			Enum.valueOf(FiledType.class, fieldType).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + fieldType + "没有按规则编写,无法得到属性值类型.", e);
		}
		
		if (null == fieldList || "".equals(fieldList) || null == typeCode || "".equals(typeCode))
			return 0;

		JspWriter out = pageContext.getOut();

		try {
			StringBuffer html = new StringBuffer();
			//********
			out.write(html.toString());
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 生成 select
	 * @param name
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unused")
	private String loadSelectHtmlCode(String name,List<T> list) {
		
		StringBuffer html = new StringBuffer();
		String value = pageContext.getRequest().getParameter(name);// MyStringUtils.iso2utf(request.getParameter(name));
		if (null == value)
			value = ( this.value == null ) ? "" : this.value  ;
		
		return html.toString();
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
}
