package com.ybt.web.tags;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 时间格式
 * 
 * @author Administrator
 * 
 */
public class TagFormatDate extends RequestContextAwareTag {

	protected static final long serialVersionUID = 1L;

	/**
	 * <hq:formatDate value="${his.insertTime }" pattern="yyyy-MM-dd hh:mm" />
	 */
	protected Date value = null;

	protected String pattern = "wantonly";

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		if (null == value)
			return 0;
		if (null == pattern)
			pattern = "wantonly";

		JspWriter out = pageContext.getOut();
		try {
			if (null == pattern || "wantonly".equals(pattern) || "".equals(pattern))
				out.write("<font title='" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.value) + "'>" + wantonly(this.value) + "</font>");
			else
				out.write(new java.text.SimpleDateFormat(this.pattern).format(this.value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private String wantonly(Date v) {

		String str = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(v);

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		Calendar vv = Calendar.getInstance();
		vv.setTime(v);

		int time;
		String unit;
		int calendar;

		calendar = Calendar.YEAR;
		unit = "年前";
		time = (today.get(calendar) - vv.get(calendar));
		if (time > 0) {
			return time + unit;
		}
		calendar = Calendar.MONTH;
		unit = "个月前";
		time = (today.get(calendar) - vv.get(calendar));
		if (time > 0) {
			return time + unit;
		}
		calendar = Calendar.DATE;
		unit = "天前";
		time = (today.get(calendar) - vv.get(calendar));
		if (time > 0) {
			return time + unit;
		}
		calendar = Calendar.HOUR_OF_DAY;
		unit = "小时前";
		time = (today.get(calendar) - vv.get(calendar));
		if (time > 0) {
			return time + unit;
		}
		calendar = Calendar.MINUTE;
		unit = "分钟前";
		time = (today.get(calendar) - vv.get(calendar));
		if (time > 0) {
			return time + unit;
		}
		unit = "秒钟前";
		time = (int) ((today.getTimeInMillis() - vv.getTimeInMillis()) / 1000l);
		if (time > 2) {
			return time + unit;
		}
		if (time < 2) {
			return "刚刚";
		}
		return str;
	}
}
