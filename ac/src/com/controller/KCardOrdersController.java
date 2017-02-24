package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.KCardDeadline;
import com.model.KCardOrders;
import com.model.KUser;
import com.util.EasyUiDataGridJson;

@Controller
@RequestMapping("/KCardOrders")
public class KCardOrdersController extends BaseController {

	// 添加开卡记录
	@RequestMapping("/addCardOrders")
	@ResponseBody
	public int addCardOrders(KCardOrders cardOrders, HttpServletRequest request, String addchengben) {
		System.out.println("这是开卡成本" + addchengben);
		int i = 0;
		try {
			i = getCardOrdersService().addCardOrders(cardOrders, request, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/*
	 * 验证用户下面的余额
	 */
	@RequestMapping("/getProportion")
	@ResponseBody
	public String getProportion(HttpServletResponse response) {
		// UUid
		UUID uuid = UUID.randomUUID();
		String hashCodes = "ebae4cc720589685f517ed6ab95bac36" + "88888888-3333-3333-6666-888888888888" + uuid
				+ "95370000000-95379999999" + "GetCardRemainCount";

		String password = util.toMD5(hashCodes);
		String cs = "HashCode=" + password + "&UserId=88888888-3333-3333-6666-888888888888&OrderId=" + uuid
				+ "&FunctionID=GetCardRemainCount&HaoDuan=" + "95370000000-95379999999";
		String urls = "https://passport.kangou.cn/Service/CardAPI.aspx";
		System.out.println(findByCardBank(urls, cs));
		return findByCardBank(urls, cs);

	}

	/*
	 * 验证号段是否开通过 如果开通的时候返回1，没有开通返回0
	 */
	@RequestMapping("/findByCardnum")
	@ResponseBody
	public int findByCardnum(long startCardNums, long endCardNum) {
		System.out.println("这是验证卡号");
		String xmlError = null;

		int j = 0;
		for (long i = startCardNums; i <= endCardNum; i++) {
			System.out.println(i);
			UUID uuid = UUID.randomUUID();
			String hashCodes = "ebae4cc720589685f517ed6ab95bac36" + "88888888-3333-3333-6666-888888888888" + uuid + i
					+ "GetCardOpenStatus";
			String password = util.toMD5(hashCodes);
			String cs = "HashCode=" + password + "&UserId=88888888-3333-3333-6666-888888888888&OrderId=" + uuid
					+ "&FunctionID=GetCardOpenStatus&CardNumber=" + i;
			String urls = "https://passport.kangou.cn/Service/CardAPI.aspx";
			String xml = findByCardBank(urls, cs);
			xmlError = readStringXml(xml);
			System.out.println(xml);
			System.out.println(xmlError);

			if (xmlError != null && !xmlError.equalsIgnoreCase("")) {
					
			} else {
				j = 1;
				return j;
			}

		}
		System.out.println(j);
		return j;

	}

	/*
	 * 添加充值记录
	 */
	// 充值记录
	@RequestMapping("/addPayCardOrders")
	@ResponseBody
	public int addPayCardOrders(KCardOrders cardOrders, HttpServletRequest request, String addchengben) {
		System.out.println("这是开卡成本" + addchengben);
		int i = 0;
		try {
			i = getCardOrdersService().addCardOrders(cardOrders, request, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/*
	 * 查询当前用户下面的开卡记录
	 */
	@RequestMapping("/findByCardOrderByNum")
	@ResponseBody
	public List<Map<String, String>> findByCardOrderByNum(String startCardNums, String endCardNum, String cardKingsId) {
		System.out.println("这是要延期的卡类型" + cardKingsId + "这是要延期的开始号段" + startCardNums + "这是要延期的结束号段" + endCardNum);
		return getCardDeadlineService().findByCardOrderByNum(startCardNums, endCardNum, cardKingsId);
	}

	/*
	 * 添加延期记录
	 */
	@RequestMapping("/addAfterCard")
	@ResponseBody
	public int addAfterCard(KCardDeadline cardDeadline, HttpServletRequest request) {
		int i = 0;
		KUser kUser = (KUser) request.getSession().getAttribute("username");
		cardDeadline.setUserID(kUser.getId());
		try {
			getCardDeadlineService().addAfterCard(cardDeadline);
		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}
		return i;
	}

	/*
	 * 查询用户下面的开卡记录
	 */
	@RequestMapping("/findByUserCardOrders")
	@ResponseBody
	public List<KCardOrders> findByUserCardOrders(String cardKingsId, String startCardNum, String endCardNum) {
		List<KCardOrders> cardOrderuser = getCardOrdersService().findByUserCardOrders(cardKingsId, startCardNum,
				endCardNum);
		List<KCardOrders> cardOrderquits = getCardOrdersService().findByQuitCardorders(cardKingsId, startCardNum,
				endCardNum);
		if (cardOrderuser.size() > 0) {
			if (cardOrderquits.size() > 0) {
				return cardOrderquits;
			} else {
				return cardOrderuser;
			}
		} else {
			return null;
		}

	}

	/*
	 * 添加退卡记录
	 * 
	 */
	@RequestMapping("/addQuitCardOrders")
	@ResponseBody
	public int addQuitCardOrders(KCardOrders cardOrders, HttpServletRequest request) {
		int i = findByCardnum(Long.parseLong(cardOrders.getStartCardNum()), Long.parseLong(cardOrders.getEndCardNum()));
		if (i == 0) {
			// 这是已经开卡的处理方法
			int j = getCardOrdersService().addQuitCardOrders(cardOrders, request);
			return j;
		} else {
			// 这是没有开卡的处理方法
			int j = getCardOrdersService().addOpenCardOrders(cardOrders, request);
			return j;
		}
	}

	/*
	 * 查询当前用户下面的开卡记录
	 */
	@RequestMapping("/findOrdersByUser")
	@ResponseBody
	public EasyUiDataGridJson<Map<String, String>> findOrdersByUser(HttpServletRequest request, String startTime,
			String endTime, int page, int rows) {
		KUser kUser = (KUser) request.getSession().getAttribute("username");
		// 判断用户是不是管理员
		int usertype = 0;
		if (kUser.getUserType() == 0) {
			usertype = 0;
		} else {
			usertype = Integer.parseInt(kUser.getId());
		}
		return getCardOrdersService().findOrdersByUser(request, startTime, endTime, page, rows, usertype);
	}

	/*
	 * 用户延期查询
	 */
	@RequestMapping("/findDeadlineByPage")
	@ResponseBody
	public EasyUiDataGridJson<Map<String, String>> findDeadlineByPage(HttpServletRequest request, String startTime,
			String endTime, int page, int rows) {
		return getCardDeadlineService().findDeadlineByPage(request, startTime, endTime, page, rows);
	}

	/*
	 * 用户开卡接口 格式：orderJson={userName:userName,age:age};
	 */
	@RequestMapping("/openCardInterface")
	@ResponseBody
	public Map<String, String> addOpenCardInterface(HttpServletRequest request) throws UnsupportedEncodingException {
		String startCardNumStr = request.getParameter("startCardNum");
		String endCardNumStr = request.getParameter("endCardNum");
		String cardType = request.getParameter("cardType");
		Map<String, String> map = new HashMap<String, String>();
		
		if (cardType.trim().equalsIgnoreCase("1")) {
			
			int i = 0;
			if (startCardNumStr != null && !startCardNumStr.equals("")) {
				if (endCardNumStr != null && !endCardNumStr.equals("")) {
					i = findByCardnum(Long.parseLong(startCardNumStr), Long.parseLong(endCardNumStr));
				}
			}

			if (i != 0) {
				map.put("error", "卡号已经被开通过");
				return map;
			} else {
				map=getCardOrdersService().openCardInterface(request, cardType);
			}
			
		} else if (cardType.trim().equalsIgnoreCase("2")) {
			int i = 0;
			map.put("success","充值成功");
			if (startCardNumStr != null && !startCardNumStr.equals("")) {
				if (endCardNumStr != null && !endCardNumStr.equals("")) {
					i = this.findByCardnum(Long.parseLong(startCardNumStr), Long.parseLong(endCardNumStr));
				}
			}

			if (i == 0) {
				map.put("error", "这个没有被开通过");
				return map;
			} else {
				map=getCardOrdersService().openCardInterface(request, cardType);
			}
			
		} else if (cardType.trim().equalsIgnoreCase("3")) {
			// 卡延期
			
			map = getCardDeadlineService().addAfterCardInterFace(request);

		} else if (cardType.trim().equalsIgnoreCase("4")) {
			
			map = getCardOrdersService().quitCardInterface(request);
		}
		return map;
	}
}
