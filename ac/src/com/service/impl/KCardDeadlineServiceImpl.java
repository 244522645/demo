package com.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.model.KCardDeadline;
import com.model.KCardOrders;
import com.model.KUser;
import com.model.KUserRanges;
import com.service.KCardDeadlineService;
import com.util.EasyUiDataGridJson;

@Service
public class KCardDeadlineServiceImpl extends BaseService implements KCardDeadlineService {

	// 查询当前用户下面的开卡号段
	public List<Map<String, String>> findByCardOrderByNum(String startCardNums, String endCardNum, String cardKingsId) {
		return getCardDeadlineMapper().findByCardOrderByNum(startCardNums, endCardNum, cardKingsId);
	}

	// 添加延期记录
	public void addAfterCard(KCardDeadline cardDeadline) {
		log.info("添加延期记录" + cardDeadline.getId());
		Date currentTime = new Date();
		Timestamp nousedate = new Timestamp(currentTime.getTime());
		cardDeadline.setSubmitDate(nousedate);
		getCardDeadlineMapper().addAfterCard(cardDeadline);
		currentTime=null;
	}

	/*
	 * 用户延期查询
	 */
	@Override
	public EasyUiDataGridJson<Map<String, String>> findDeadlineByPage(HttpServletRequest request, String startTime,
			String endTime, int page, int rows) {
		KUser kUser = (KUser) request.getSession().getAttribute("username");

		int id = 0;
		if (kUser != null && !kUser.equals("")) {
			id = Integer.parseInt(kUser.getId());
		}
		if (kUser.getUserType() == 0) {
			id = 0;
		}
		List<Map<String, String>> list = getCardDeadlineMapper().findDeadlineByPage(startTime, endTime,
				(page - 1) * rows, rows, id);
		int totle = getCardDeadlineMapper().countDeadlineByPage(startTime, endTime, id);
		EasyUiDataGridJson<Map<String, String>> json = new EasyUiDataGridJson<Map<String, String>>();
		json.setTotal(totle);
		json.setRows(list);
		kUser=null;
		return json;
	}

	/*
	 * (non-Javadoc) 用户延期接口
	 * 
	 * @see
	 * com.service.KCardDeadlineService#addAfterCardInterFace(javax.servlet.http
	 * .HttpServletRequest)
	 */
	@Override
	public Map<String, String> addAfterCardInterFace(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String startCardNumStr = request.getParameter("startCardNum");
		String endCardNumStr = request.getParameter("endCardNum");
		String afterDate = request.getParameter("afterDate");
		map.put("success", "延期成功");
		if (startCardNumStr != null && !startCardNumStr.trim().equals("") && endCardNumStr != null
				&& !endCardNumStr.trim().equals("") && afterDate != null && !afterDate.trim().equals("")) {
			try {
				List<KUserRanges> kUserRangesList = getUserCardMapper().findByCard(startCardNumStr, endCardNumStr);
				List<KCardOrders> cardOrders = getCardOrdersMapper()
						.findByUserCardOrders(kUserRangesList.get(0).getCardKindID(), startCardNumStr, endCardNumStr);
				KCardDeadline cardDeadline = new KCardDeadline();
				cardDeadline.setStartCardNum(startCardNumStr);
				cardDeadline.setEndCardNum(endCardNumStr);
				cardDeadline.setCardKindsId(Integer.parseInt(kUserRangesList.get(0).getCardKindID()));
				cardDeadline.setBeforeDate(Timestamp.valueOf(cardOrders.get(0).getClosingDate()));
				cardDeadline.setAfterDate(Timestamp.valueOf(afterDate));
				cardDeadline.setSubmitDate(Timestamp.valueOf(formatter.format(new Date())));
				cardDeadline.setUserID(cardOrders.get(0).getUserId().toString());
				log.info("接口：延期  卡号" + cardDeadline.getEndCardNum() + "-" + cardDeadline.getEndCardNum() + "卡类型id"
						+ cardDeadline.getCardKindsId() + "修改前时间" + cardDeadline.getBeforeDate() + "修改后时间"
						+ cardDeadline.getAfterDate());
				getCardDeadlineMapper().addAfterCard(cardDeadline);
				kUserRangesList=null;
				cardOrders=null;
			} catch (NumberFormatException e) {
				map.put("error", "添加延期记录失败");
				e.printStackTrace();
			}
		} else {
			map.put("error", "数据传输错误，请重试");

		}
		startCardNumStr=null;
		endCardNumStr=null;
		afterDate=null;
		
		return map;
	}
}
