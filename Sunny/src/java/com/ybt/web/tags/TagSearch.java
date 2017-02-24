package com.ybt.web.tags;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.ybt.common.util.Filtration;
import com.ybt.common.util.Filtration.FiledType;
import com.ybt.common.util.Filtration.MatchType;

/**   
 * spring标签实例
 * @author AndyBao  
 * @version 4.0, 2016年6月14日 下午3:11:46   
 */   
public class TagSearch extends RequestContextAwareTag {
	protected static final long serialVersionUID = 1L;

	protected String className;
	protected String styleName;
	protected String matchType;
	protected String fieldType;
	protected String fieldList;
	protected String value;
	protected String dataOptions;
	
	@Override
	public int doStartTagInternal() throws  Exception {
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
		
		ServletRequest request = pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		try {
			// filter_LIKES_name_OR_email
			String name = "filter_" + matchType + fieldType + "_" + fieldList.replaceAll(",", Filtration.OR);
			// String value = (String) (null == request.getAttribute(name) ? request.getParameter(name) : request.getAttribute(name));
			String value =  request.getParameter(name);
			if (null == value || "null".equalsIgnoreCase(value))
				value = this.value != null ? this.value :"";
			String idCode = (name != null && !"".equals(name)) ? "id=\"" + name + "\"" : "";
			String classCode = (className != null && !"".equals(className)) ? "class=\"" + className + "\"" : "";
			String styleCode = (styleName != null && !"".equals(styleName)) ? "style=\"" + styleName + "\"" : "";
			String dataOptionsCode= (dataOptions != null && !"".equals(dataOptions)) ? "data-options=\"" + dataOptions + "\"" : "";
			String html = "<input type=\"text\" " + idCode + " name=\"" + name + "\" value=\"" + value + "\" " + classCode + " " + styleCode + "" + dataOptionsCode + "/>";
			out.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}

	/*@Override
	public int doEndTag() throws JspException {

		ServletRequest request = pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		try {
			// filter_LIKES_name_OR_email
			String name = "filter_" + matchType + fieldType + "_" + fieldList.replaceAll(",", Filtration.OR);
			// String value = (String) (null == request.getAttribute(name) ? request.getParameter(name) : request.getAttribute(name));
			String value =  request.getParameter(name);
			if (null == value || "null".equalsIgnoreCase(value))
				value = this.value != null ? this.value :"";
			String idCode = (name != null && !"".equals(name)) ? "id=\"" + name + "\"" : "";
			String classCode = (className != null && !"".equals(className)) ? "class=\"" + className + "\"" : "";
			String styleCode = (styleName != null && !"".equals(styleName)) ? "style=\"" + styleName + "\"" : "";
			String dataOptionsCode= (dataOptions != null && !"".equals(dataOptions)) ? "data-options=\"" + dataOptions + "\"" : "";
			String html = "<input type=\"text\" " + idCode + " name=\"" + name + "\" value=\"" + value + "\" " + classCode + " " + styleCode + "" + dataOptionsCode + "/>";
			out.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}*/
	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType.toUpperCase();
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType.toUpperCase();
	}

	public String getFieldList() {
		return fieldList;
	}

	public void setFieldList(String fieldList) {
		this.fieldList = fieldList;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getDataOptions() {
		return dataOptions;
	}

	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}