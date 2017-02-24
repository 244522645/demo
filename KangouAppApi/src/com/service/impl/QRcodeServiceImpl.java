package com.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.CardsTong;
import com.model.UserError;
import com.model.UserInfo;
import com.service.QRcodeService;
import com.util.Utils;

@Service
public class QRcodeServiceImpl extends BaseService implements QRcodeService {

	@Override
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Map<String, String> prepaidCard(String cardNum, String cardPass, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		log.info("这是用户ID" + userInfo.getId());
		int countNum = getUserErrorMapper().countByerror(userInfo.getId(), "2", format.format(new Date()));
		if (countNum > 20) {

			map.put("messageid", "1904");
			map.put("message", "错误次数超过20次,当天不能再次购卡");
			return map;

		}
		int countcard = getUserErrorMapper().countByCard(userInfo.getId(), "2", format.format(new Date()), cardNum);
		if (countcard > 3) {
			map.put("messageid", "1905");
			map.put("message", "错误次数超过3次,当前卡不能再次使用");
			return map;
		}

		List<CardsTong> cardsTongs = getCardsTongMapper().selectBycardNum(cardNum);
		if (cardsTongs.size() < 1) {

			map.put("messageid", "1906");
			map.put("message", "没有当前卡号");
			return map;
		}
		CardsTong cardsTong = cardsTongs.get(0);
		// 查询接口返回密码，判断密码是否正确
		Utils utils = new Utils();
		String password = utils.findByCardBank("https://passport.kangou.cn/service/app/GetCardCodeForApp.aspx?cardcode="
				+ cardPass + "&card=" + cardNum, "");
		System.out.println("这是卡的卡号和密码" + password);
		System.out.println(cardsTong.getCardtcode());
		if (!password.equals(cardsTong.getCardtcode())) {

			UserError userError = new UserError();
			userError.setErrortime(new Date());
			userError.setErrornum(cardNum);
			userError.setErrortype(2);
			userError.setErroruserid(userInfo.getId());
			getUserErrorMapper().insertSelective(userError);

			map.put("messageid", "1907");
			map.put("message", "密码错误");
			return map;

		}
		if (cardsTong.getCardtstatus() != 1) {

			map.put("messageid", "1908");
			map.put("message", "当前卡不能用，请联系管理员");
			return map;

		}
		List<Map<String, String>> pricemap = getCardsTongMapper().selectByCardprice(userInfo.getParentid());
		request.getSession().setAttribute("cardNum", cardNum);
		request.getSession().setAttribute("cardpassword", cardPass);
		request.getSession().setAttribute("cardpasstime", cardsTong.getCardtdateend());
		request.getSession().setAttribute("pricemap", pricemap);
		request.getSession().setAttribute("cardsTong", cardsTong);
		map.put("messageid", "1900");
		map.put("message", "该影票可用");
		return map;
	}

	/*
	 * 
	 * (张卫恒)
	 * 
	 * @see com.service.QRcodeService#ticket() 把卡号和密码传到接口，修改数据库的流水号和校验码
	 */
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Map<String, String> ticket(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<Map<String, String>> resultList = getPosCinemasMapper().selectPosid(userInfo.getParentid());
			if (resultList == null && "".equals(resultList)) {
				map.put("messageid", "2103");
				map.put("message", "影院为空");
				return map;
			}
			Map<String, String> resultMap = resultList.get(0);
			String serialnumberString = resultMap.get("serialnumber");
			int serialnumber = Integer.parseInt(serialnumberString);
			DecimalFormat countFormat = new DecimalFormat("000000");
			getPosCinemasMapper().updateSerialnumber(resultMap.get("poscinemaid"),
					countFormat.format(serialnumber) + "");
			Utils utils = new Utils();
			String baoxianresult = utils.tenrodem();
			utils = null;

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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("messageid", "2137");
		map.put("message", "未知错误，请联系管理员");

		return map;

	}

	@Override
	public String qrCode(String cardNum, String cardPass, HttpServletRequest request) {
		List<CardsTong> cardsTongs = getCardsTongMapper().selectBycardNum(cardNum);
		if (cardsTongs.size() < 1) {
			request.setAttribute("messageid", "1906");
			request.setAttribute("message", "没有当前卡号");
			return "404";
		}
		CardsTong cardsTong = cardsTongs.get(0);

		if (cardsTong.getCardtstatus() != 1) {
			request.setAttribute("messageid", "1908");
			request.setAttribute("message", "当前卡不能用，请联系管理员");
			return "404";
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("currentUser");
		List<Map<String, String>> map = getCardsTongMapper().selectByCardprice(userInfo.getParentid());
		request.getSession().setAttribute("cardNum", cardNum);
		request.getSession().setAttribute("cardpassword", cardPass);
		log.info("这是卡号到期时间" + cardsTong.getCardtdateend());
		request.getSession().setAttribute("cardpasstime", cardsTong.getCardtdateend());
		request.setAttribute("pricemap", map);
		request.setAttribute("cardsTong", cardsTong);
		return "qrCode";
	}

	/*
	 * 
	 * (张卫恒)
	 * 
	 * @see com.service.QRcodeService#ticket() 把卡号和密码传到接口，修改数据库的流水号和校验码
	 */
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Map<String, String> ticketQRcode(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
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
			getPosCinemasMapper().updateSerialnumber(resultMap.get("poscinemaid"),
					countFormat.format(serialnumber) + "");
			String baoxianresult = utils.tenrodem();
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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		map.put("messageid", "2137");
		map.put("message", "未知错误，请联系管理员");

		return map;
	}

	@Override
	public Map<String, String> cardByUrl(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
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
		request.getSession().setAttribute("cardByurl", url);
		request.getSession().setAttribute("cardnumtext", cardnumtext);
		request.getSession().setAttribute("price", price);

		map.put("messageid", "2199");
		map.put("message", "正在购买中，请稍后");
		return map;
	}
	/*
	 * (non-Javadoc)张卫恒
	 * 
	 * @see com.service.QRcodeService#qrCodeByUrl(com.model.UserInfo,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * javax.servlet.http.HttpServletRequest) 扫码过来的链接获取接口的url
	 */

	public Map<String, String> qrCodeByUrl(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
			String price, HttpServletRequest request) {
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
		request.getSession().setAttribute("cardByurl", url);
		request.getSession().setAttribute("cardnumtext", cardnumtext);
		request.getSession().setAttribute("price", price);

		map.put("messageid", "2199");
		map.put("message", "正在购买中，请稍后");
		return map;
	}

	@Override
	public Map<String, String> buyCard(UserInfo userInfo, String cardsNum, String cardPass, String cardnumtext,
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

		log.info("这是返回的结果"+fanhui);
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
}
