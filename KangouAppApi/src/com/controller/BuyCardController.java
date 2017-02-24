package com.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapper.PosCinemasMapper;
import com.mapper.PosOrdersMapper;
import com.model.UserInfo;
import com.util.Utils;

@Controller
@RequestMapping("buyCard")
public class BuyCardController extends BaseController {
	/*
	 * 密码直接购卡，不通过中转页面
	 *
	 */
	@RequestMapping("buyCard")
	@ResponseBody
	public Map<String, String> buyCard(String form_card, String form_cardnum, String form_time, String cardnumtext,
			String price, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {

			String cardsNum = (String) request.getSession().getAttribute("cardNum");
			String cardPass = (String) request.getSession().getAttribute("cardpassword");
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
			if (cardsNum == null && "".equals(cardsNum)) {
				map.put("messageid", "2101");
				map.put("message", "卡号为空");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "密码为空");
				return map;
			}
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "卡过期");
				return map;
			}

			return this.buyCardService(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "购卡失败，请联系管理员");
			return map;

		}
	}

	private PosCinemasMapper posCinemasMapper;

	public PosCinemasMapper getPosCinemasMapper() {
		return posCinemasMapper;
	}

	public void setPosCinemasMapper(PosCinemasMapper posCinemasMapper) {
		this.posCinemasMapper = posCinemasMapper;
	}

	public Map<String, String> buyCardService(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, String>> resultList = getPosCinemasMapper().selectPosid(userInfo.getParentid());
		if (resultList == null && "".equals(resultList)) {
			map.put("messageid", "2103");
			map.put("message", "影院为空");
			return map;
		}
		Map<String, String> resultMap = resultList.get(0);
		String serialnumberString = resultMap.get("serialnumber");
		int serialnumber = Integer.parseInt(serialnumberString);
		serialnumber = serialnumber + 1;
		DecimalFormat countFormat = new DecimalFormat("000000");
		getPosCinemasMapper().updateSerialnumber(resultMap.get("poscinemaid"), countFormat.format(serialnumber) + "");
		String url = "http://192.168.1.16:4066/lookangocom/KangouAppConsume.ashx?posid=" + resultMap.get("posid")
				+ "&batchnumber=000000&serialnumber=" + countFormat.format(serialnumber) + "&cardid=" + cardsNum + ":"
				+ cardPass + "&posordercount=" + cardnumtext + "&unitprice=" + price + "&userid="
				+ userInfo.getCinamausername() + "&md5key=kangouApp3lsderi&type=KG_App_PosPurchase";
		Utils utils = new Utils();
		String fanhui = utils.sendGet(url.trim());

		log.info("这是返回的结果" + fanhui);
		String baoxianresult = utils.tenrodem();
		if (fanhui.equals("0")) {
			List<Map<String, String>> cinemasIdlist;
			// 消费成功
			String shuzi = cardsNum.substring(0, 1);
			if (!shuzi.equals("9")) {
				cinemasIdlist = getPosCinemasMapper().selectnineResule(resultMap.get("posid"),
						countFormat.format(serialnumber) + "", userInfo.getParentid());
				if (cinemasIdlist.size() < 0) {
					map.put("messageid", "2104");
					map.put("message", "没有该条消费记录");
					return map;
				}
				String posorderpurchasecount = cinemasIdlist.get(0).get("posorderpurchasecount").toString();
				String posorderticketremaincount = cinemasIdlist.get(0).get("posorderticketremaincount").toString();
				System.out.println(resultList.size());
				Map<String, String> resultmap = new HashMap<String, String>();
				resultmap.put("posid", resultMap.get("posid"));
				resultmap.put("batch", cinemasIdlist.get(0).get("batch"));
				resultmap.put("serialnumber", countFormat.format(serialnumber) + "");
				resultmap.put("cardnum", cardsNum);
				resultmap.put("price", countFormat.format(Integer.parseInt(price)) + "");
				resultmap.put("type", "密码交易");
				resultmap.put("num", cardnumtext);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				resultmap.put("time", format.format(new Date()));
				resultmap.put("userinfo", userInfo.getCinamausername().substring(0, 3) + "****"
						+ userInfo.getCinamausername().substring(7));
				resultmap.put("baoxianresult", baoxianresult);
				resultmap.put("posorderpurchasecount", posorderpurchasecount);
				resultmap.put("posorderticketremaincount", posorderticketremaincount);
				request.getSession().setAttribute("Smallticket", resultmap);
				getPosCinemasMapper().uodatenineResule(cinemasIdlist.get(0).get("posorderid") + "", baoxianresult);
				map.put("messageid", "2100");
				map.put("message", "购票成功");
				return map;
			}
			cinemasIdlist = getPosCinemasMapper().selectnotnineResule(resultMap.get("posid"),
					countFormat.format(serialnumber) + "", userInfo.getParentid());
			if (cinemasIdlist == null && "".equals(cinemasIdlist)) {
				map.put("messageid", "2104");
				map.put("message", "没有该条消费记录");
				return map;
			}

			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("posid", resultMap.get("posid"));
			resultmap.put("batch", resultList.get(0).get("batch"));

			resultmap.put("serialnumber", countFormat.format(serialnumber) + "");
			resultmap.put("cardnum", cardsNum);
			resultmap.put("price", countFormat.format(price));
			resultmap.put("type", "密码交易");
			resultmap.put("num", cardnumtext);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			resultmap.put("time", format.format(new Date()));
			resultmap.put("userinfo",
					userInfo.getCinamausername().substring(0, 3) + "****" + userInfo.getCinamausername().substring(7));
			resultmap.put("baoxianresult", baoxianresult);
			resultmap.put("posorderpurchasecount", cinemasIdlist.get(0).get("posorderpurchasecount"));
			resultmap.put("posorderticketremaincount", cinemasIdlist.get(0).get("posorderticketremaincount"));
			request.getSession().setAttribute("Smallticket", resultmap);
			getPosCinemasMapper().uodatenotnineResule(cinemasIdlist.get(0).get("posorderid") + "", baoxianresult);
			map.put("messageid", "2100");
			map.put("message", "购票成功");
			return map;
		}
		if (fanhui.equals("10")) {
			map.put("messageid", "2110");
			map.put("message", "无效卡,卡不存在");
			return map;
		}
		if (fanhui.equals("11")) {
			map.put("messageid", "2111");
			map.put("message", "余额不足");
			return map;
		}
		if (fanhui.equals("12")) {
			map.put("messageid", "2112");
			map.put("message", "本月额度不足 卡超过月消费最大数");
			return map;
		}
		if (fanhui.equals("13")) {
			map.put("messageid", "2113");
			map.put("message", "日期格式不正确");
			return map;
		}
		if (fanhui.equals("14")) {
			map.put("messageid", "2114");
			map.put("message", "卡过期");
			return map;
		}
		if (fanhui.equals("15")) {
			map.put("messageid", "2115");
			map.put("message", "日期超出范围");
			return map;
		}
		if (fanhui.equals("16")) {
			map.put("messageid", "2116");
			map.put("message", "商户终端号错误");
			return map;
		}
		if (fanhui.equals("17")) {
			map.put("messageid", "2117");
			map.put("message", "此影院不支持");
			return map;
		}
		if (fanhui.equals("18")) {
			map.put("messageid", "2118");
			map.put("message", "无数据");
			return map;
		}
		if (fanhui.equals("20")) {
			map.put("messageid", "2120");
			map.put("message", "此终端号不存在");
			return map;
		}
		if (fanhui.equals("21")) {
			map.put("messageid", "2121");
			map.put("message", "此终端号未启用");
			return map;
		}
		if (fanhui.equals("22")) {
			map.put("messageid", "2122");
			map.put("message", "此卡未开通");
			return map;
		}
		if (fanhui.equals("23")) {
			map.put("messageid", "2123");
			map.put("message", "密码错误");
			return map;
		}
		if (fanhui.equals("30")) {
			map.put("messageid", "2130");
			map.put("message", "此卡已作废");
			return map;
		}
		if (fanhui.equals("32")) {
			map.put("messageid", "2132");
			map.put("message", "此卡未到开通日期");
			return map;
		}
		if (fanhui.equals("40")) {
			map.put("messageid", "2140");
			map.put("message", "不支持商户模式消费");
			return map;
		}
		if (fanhui.equals("41")) {
			map.put("messageid", "2141");
			map.put("message", "此卡不支持普通电影");
			return map;
		}
		if (fanhui.equals("42")) {
			map.put("messageid", "2142");
			map.put("message", "此卡不支持3D电影");
			return map;
		}
		if (fanhui.equals("43")) {
			map.put("messageid", "2143");
			map.put("message", "此卡不支持VIP电影");
			return map;
		}
		if (fanhui.equals("44")) {
			map.put("messageid", "2144");
			map.put("message", "此卡不支持IMAX电影");
			return map;
		}
		if (fanhui.equals("45")) {
			map.put("messageid", "2145");
			map.put("message", "此卡不支持白场优惠普通电影");
			return map;
		}
		if (fanhui.equals("46")) {
			map.put("messageid", "2146");
			map.put("message", "此卡不支持白场优惠3D电影");
			return map;
		}
		if (fanhui.equals("47")) {
			map.put("messageid", "2147");
			map.put("message", "此卡不支持白场优惠VIP电影");
			return map;
		}
		if (fanhui.equals("48")) {
			map.put("messageid", "2148");
			map.put("message", "此卡不支持白场优惠IMAX电影");
			return map;
		}
		if (fanhui.equals("49")) {
			map.put("messageid", "2149");
			map.put("message", "输入的单价太小");
			return map;
		}
		if (fanhui.equals("50")) {
			map.put("messageid", "2150");
			map.put("message", "输入的单价太大");
			return map;
		}
		if (fanhui.equals("51")) {
			map.put("messageid", "2151");
			map.put("message", "输入的单价错误");
			return map;
		}
		if (fanhui.equals("52")) {
			map.put("messageid", "2152");
			map.put("message", "卡类型错误");
			return map;
		}
		if (fanhui.equals("53")) {
			map.put("messageid", "2153");
			map.put("message", "票卡的单价太小");
			return map;
		}
		if (fanhui.equals("6")) {
			map.put("messageid", "2106");
			map.put("message", "输入的单价错误");
			return map;
		}
		if (fanhui.equals("35")) {
			map.put("messageid", "2135");
			map.put("message", "出错");
			return map;
		}
		if (fanhui.equals("36")) {
			map.put("messageid", "2136");
			map.put("message", "消费记录已有");
			return map;
		}
		map.put("messageid", "2137");
		map.put("message", "未知错误，请联系管理员");

		return map;
	}

	/*
	 * 二维码购卡
	 */
	@RequestMapping("qrCodeBuyCard")
	@ResponseBody
	public Map<String, String> qrCodeBuyCard(String form_card, String form_cardnum, String form_time,
			String cardnumtext, String price, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String cardsNum = (String) request.getSession().getAttribute("cardNum");
			String cardPass = (String) request.getSession().getAttribute("cardpassword");
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
			if (cardsNum == null && "".equals(cardsNum)) {
				map.put("messageid", "2101");
				map.put("message", "卡号为空");
				return map;
			}
			if (cardPass == null && "".equals(cardPass)) {
				map.put("messageid", "2102");
				map.put("message", "密码为空");
				return map;
			}
			log.info(request.getSession().getAttribute("cardpasstime"));
			String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
			Date date = df.parse(request.getSession().getAttribute("cardpasstime") + "");
			Date nowdate = new Date();
			if (nowdate.getTime() > date.getTime()) {
				map.put("messageid", "2105");
				map.put("message", "卡过期");
				return map;
			}
			return this.qrCodeBuyCardService(userInfo, cardsNum, cardPass, cardnumtext, price, request);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("messageid", "2106");
			map.put("message", "购卡失败，请联系管理员");
			return map;
		}
	}

	public Map<String, String> qrCodeBuyCardService(UserInfo userInfo, String cardsNum, String cardPass,
			String cardnumtext, String price, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		DecimalFormat countFormat = new DecimalFormat("000000");
		List<Map<String, String>> resultList = getPosCinemasMapper().selectPosid(userInfo.getParentid());
		if (resultList == null && "".equals(resultList)) {
			map.put("messageid", "2103");
			map.put("message", "影院为空");
			return map;
		}
		Map<String, String> resultMap = resultList.get(0);
		String serialnumberString = resultMap.get("serialnumber");
		int serialnumber = Integer.parseInt(serialnumberString);
		serialnumber = serialnumber + 1;
		getPosCinemasMapper().updateSerialnumber(resultMap.get("poscinemaid"), countFormat.format(serialnumber) + "");
		String url = "http://192.168.1.16:4066/lookangocom/KangouAppConsume.ashx?posid=" + resultMap.get("posid")
				+ "&batchnumber=000000&serialnumber=" + countFormat.format(serialnumber) + "&cardid=" + cardPass
				+ "&posordercount=" + cardnumtext + "&unitprice=" + price + "&userid=" + userInfo.getCinamausername()
				+ "&md5key=kangouApp3lsderi&type=KG_App_PosPurchase";

		String fanhui = utils.sendGet(url.trim());
		log.info("这是二维码返回的结果" + fanhui);

		String baoxianresult = utils.tenrodem();
		if (fanhui.equals("0")) {
			List<Map<String, String>> cinemasIdlist;
			// 消费成功
			String shuzi = cardsNum.substring(0, 1);
			if (!shuzi.equals("9")) {
				cinemasIdlist = getPosCinemasMapper().selectnineResule(resultMap.get("posid"),
						countFormat.format(serialnumber) + "", userInfo.getParentid());
				if (cinemasIdlist == null && "".equals(cinemasIdlist)) {
					map.put("messageid", "2104");
					map.put("message", "没有该条消费记录");
					return map;
				}
				System.out.println(resultList.size());
				Map<String, String> resultmap = new HashMap<String, String>();
				resultmap.put("posid", resultMap.get("posid"));
				resultmap.put("batch", cinemasIdlist.get(0).get("batch"));

				resultmap.put("serialnumber", countFormat.format(serialnumber) + "");
				resultmap.put("cardnum", cardsNum);
				resultmap.put("price", countFormat.format(Integer.parseInt(price)) + "");
				resultmap.put("type", "刷卡消费");
				resultmap.put("num", cardnumtext);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				resultmap.put("time", format.format(new Date()));
				resultmap.put("userinfo", userInfo.getCinamausername().substring(0, 3) + "****"
						+ userInfo.getCinamausername().substring(7));
				resultmap.put("baoxianresult", baoxianresult);
				resultmap.put("posorderpurchasecount", cinemasIdlist.get(0).get("posorderpurchasecount"));
				resultmap.put("posorderticketremaincount", cinemasIdlist.get(0).get("posorderticketremaincount"));
				request.getSession().setAttribute("Smallticket", resultmap);
				getPosCinemasMapper().uodatenineResule(cinemasIdlist.get(0).get("posorderid") + "", baoxianresult);
				map.put("messageid", "2100");
				map.put("message", "购票成功");
				return map;
			}
			cinemasIdlist = getPosCinemasMapper().selectnotnineResule(resultMap.get("posid"),
					countFormat.format(serialnumber) + "", userInfo.getParentid());
			if (cinemasIdlist == null && "".equals(cinemasIdlist)) {
				map.put("messageid", "2104");
				map.put("message", "没有该条消费记录");
				return map;
			}
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("posid", resultMap.get("posid"));
			resultmap.put("batch", resultList.get(0).get("batch"));

			resultmap.put("serialnumber", countFormat.format(serialnumber) + "");
			resultmap.put("cardnum", cardsNum);
			resultmap.put("price", countFormat.format(price));
			resultmap.put("type", "刷卡消费");
			resultmap.put("num", cardnumtext);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			resultmap.put("time", format.format(new Date()));
			resultmap.put("userinfo",
					userInfo.getCinamausername().substring(0, 3) + "****" + userInfo.getCinamausername().substring(7));
			resultmap.put("baoxianresult", baoxianresult);
			resultmap.put("posorderpurchasecount", cinemasIdlist.get(0).get("posorderpurchasecount"));
			resultmap.put("posorderticketremaincount", cinemasIdlist.get(0).get("posorderticketremaincount"));
			request.getSession().setAttribute("Smallticket", resultmap);
			getPosCinemasMapper().uodatenotnineResule(cinemasIdlist.get(0).get("posorderid") + "", baoxianresult);
			map.put("messageid", "2100");
			map.put("message", "购票成功");
			return map;
		}
		if (fanhui.equals("10")) {
			map.put("messageid", "2110");
			map.put("message", "无效卡,卡不存在");
			return map;
		}
		if (fanhui.equals("11")) {
			map.put("messageid", "2111");
			map.put("message", "余额不足");
			return map;
		}
		if (fanhui.equals("12")) {
			map.put("messageid", "2112");
			map.put("message", "本月额度不足 卡超过月消费最大数");
			return map;
		}
		if (fanhui.equals("13")) {
			map.put("messageid", "2113");
			map.put("message", "日期格式不正确");
			return map;
		}
		if (fanhui.equals("14")) {
			map.put("messageid", "2114");
			map.put("message", "卡过期");
			return map;
		}
		if (fanhui.equals("15")) {
			map.put("messageid", "2115");
			map.put("message", "日期超出范围");
			return map;
		}
		if (fanhui.equals("16")) {
			map.put("messageid", "2116");
			map.put("message", "商户终端号错误");
			return map;
		}
		if (fanhui.equals("17")) {
			map.put("messageid", "2117");
			map.put("message", "此影院不支持");
			return map;
		}
		if (fanhui.equals("18")) {
			map.put("messageid", "2118");
			map.put("message", "无数据");
			return map;
		}
		if (fanhui.equals("20")) {
			map.put("messageid", "2120");
			map.put("message", "此终端号不存在");
			return map;
		}
		if (fanhui.equals("21")) {
			map.put("messageid", "2121");
			map.put("message", "此终端号未启用");
			return map;
		}
		if (fanhui.equals("22")) {
			map.put("messageid", "2122");
			map.put("message", "此卡未开通");
			return map;
		}
		if (fanhui.equals("23")) {
			map.put("messageid", "2123");
			map.put("message", "密码错误");
			return map;
		}
		if (fanhui.equals("30")) {
			map.put("messageid", "2130");
			map.put("message", "此卡已作废");
			return map;
		}
		if (fanhui.equals("32")) {
			map.put("messageid", "2132");
			map.put("message", "此卡未到开通日期");
			return map;
		}
		if (fanhui.equals("40")) {
			map.put("messageid", "2140");
			map.put("message", "不支持商户模式消费");
			return map;
		}
		if (fanhui.equals("41")) {
			map.put("messageid", "2141");
			map.put("message", "此卡不支持普通电影");
			return map;
		}
		if (fanhui.equals("42")) {
			map.put("messageid", "2142");
			map.put("message", "此卡不支持3D电影");
			return map;
		}
		if (fanhui.equals("43")) {
			map.put("messageid", "2143");
			map.put("message", "此卡不支持VIP电影");
			return map;
		}
		if (fanhui.equals("44")) {
			map.put("messageid", "2144");
			map.put("message", "此卡不支持IMAX电影");
			return map;
		}
		if (fanhui.equals("45")) {
			map.put("messageid", "2145");
			map.put("message", "此卡不支持白场优惠普通电影");
			return map;
		}
		if (fanhui.equals("46")) {
			map.put("messageid", "2146");
			map.put("message", "此卡不支持白场优惠3D电影");
			return map;
		}
		if (fanhui.equals("47")) {
			map.put("messageid", "2147");
			map.put("message", "此卡不支持白场优惠VIP电影");
			return map;
		}
		if (fanhui.equals("48")) {
			map.put("messageid", "2148");
			map.put("message", "此卡不支持白场优惠IMAX电影");
			return map;
		}
		if (fanhui.equals("49")) {
			map.put("messageid", "2149");
			map.put("message", "输入的单价太小");
			return map;
		}
		if (fanhui.equals("50")) {
			map.put("messageid", "2150");
			map.put("message", "输入的单价太大");
			return map;
		}
		if (fanhui.equals("51")) {
			map.put("messageid", "2151");
			map.put("message", "输入的单价错误");
			return map;
		}
		if (fanhui.equals("52")) {
			map.put("messageid", "2152");
			map.put("message", "卡类型错误");
			return map;
		}
		if (fanhui.equals("53")) {
			map.put("messageid", "2153");
			map.put("message", "票卡的单价太小");
			return map;
		}
		if (fanhui.equals("6")) {
			map.put("messageid", "2106");
			map.put("message", "输入的单价错误");
			return map;
		}
		if (fanhui.equals("35")) {
			map.put("messageid", "2135");
			map.put("message", "出错");
			return map;
		}
		if (fanhui.equals("36")) {
			map.put("messageid", "2136");
			map.put("message", "消费记录已有");
			return map;
		}
		map.put("messageid", "2137");
		map.put("message", "未知错误，请联系管理员");
		return map;
	}

	@Autowired(required = false)
	private PosOrdersMapper posOrdersMapper;

	public PosOrdersMapper getPosOrdersMapper() {
		return posOrdersMapper;
	}

	public void setPosOrdersMapper(PosOrdersMapper posOrdersMapper) {
		this.posOrdersMapper = posOrdersMapper;
	}

	@RequestMapping("backCard")
	@ResponseBody
	public Map<String, String> backCard(String swiftNumber, HttpServletRequest request) throws ParseException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
			@SuppressWarnings("unchecked")
			Map<String, String> cinemaName = (Map<String, String>) request.getSession().getAttribute("cinemaName");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str = sdf.format(new Date());
			List<Map<String, String>> list = getPosOrdersMapper().findByTimeAndPhone(swiftNumber, cinemaName.get("id"),
					cinemaName.get("posid"),str,userInfo.getCinamausername());
			if (null == list || "".equals(list)) {
				map.put("messageId", "2201");
				map.put("message", "没有这笔消费记录");
				return map;
			}
			
			String fanhui = "0";
			if (fanhui.equals("0")) {
				map.put("messageId", "2202");
				map.put("message", "退票成功");
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("messageId", "2203");
		map.put("message", "发生未知错误");
		return map;
	}
}
