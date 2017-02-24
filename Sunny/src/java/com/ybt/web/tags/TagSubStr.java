package com.ybt.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 字符串截取
 * 
 * @author Administrator
 * 
 */
public class TagSubStr extends RequestContextAwareTag {

	protected static final long serialVersionUID = 1L;

	/**
	 * <hq:subStr value="${his.insertTime }" length="20" />
	 */
	protected String value;

	protected Integer length = 10;

	protected Boolean dot = true;

	@Override
	protected int doStartTagInternal() throws Exception {
		if (null == value)
			return 0;
		JspWriter out = pageContext.getOut();
		try {
			if (value.length() <= length)
				out.write(value);
			else
				out.write(value.substring(0, length).concat(dot ? "..." : ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Boolean getDot() {
		return dot;
	}

	public void setDot(Boolean dot) {
		this.dot = dot;
	}
}
