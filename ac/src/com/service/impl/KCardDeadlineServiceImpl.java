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

	// ��ѯ��ǰ�û�����Ŀ����Ŷ�
	public List<Map<String, String>> findByCardOrderByNum(String startCardNums, String endCardNum, String cardKingsId) {
		return getCardDeadlineMapper().findByCardOrderByNum(startCardNums, endCardNum, cardKingsId);
	}

	// ������ڼ�¼
	public void addAfterCard(KCardDeadline cardDeadline) {
		log.info("������ڼ�¼" + cardDeadline.getId());
		Date currentTime = new Date();
		Timestamp nousedate = new Timestamp(currentTime.getTime());
		cardDeadline.setSubmitDate(nousedate);
		getCardDeadlineMapper().addAfterCard(cardDeadline);
		currentTime=null;
	}

	/*
	 * �û����ڲ�ѯ
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
	 * (non-Javadoc) �û����ڽӿ�
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
		map.put("success", "���ڳɹ�");
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
				log.info("�ӿڣ�����  ����" + cardDeadline.getEndCardNum() + "-" + cardDeadline.getEndCardNum() + "������id"
						+ cardDeadline.getCardKindsId() + "�޸�ǰʱ��" + cardDeadline.getBeforeDate() + "�޸ĺ�ʱ��"
						+ cardDeadline.getAfterDate());
				getCardDeadlineMapper().addAfterCard(cardDeadline);
				kUserRangesList=null;
				cardOrders=null;
			} catch (NumberFormatException e) {
				map.put("error", "������ڼ�¼ʧ��");
				e.printStackTrace();
			}
		} else {
			map.put("error", "���ݴ������������");

		}
		startCardNumStr=null;
		endCardNumStr=null;
		afterDate=null;
		
		return map;
	}
}
