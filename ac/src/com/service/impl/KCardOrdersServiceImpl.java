package com.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.KCardKinds;
import com.model.KCardOrders;
import com.model.KUser;
import com.model.KUserMoneyChanges;
import com.model.KUserRanges;
import com.service.KCardOrdersService;
import com.util.EasyUiDataGridJson;

@Service
public class KCardOrdersServiceImpl extends BaseService implements KCardOrdersService {

	// �������
	@Transactional(rollbackFor = Exception.class)
	public int addCardOrders(KCardOrders cardOrders, HttpServletRequest request, Integer cardOrdersType) {
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		double balance = 0;
		int i = 0;
		KUser user = (KUser) request.getSession().getAttribute("username");
		KUser nowUser = getkUserMapper().findByUser(user.getId());
		KUserMoneyChanges kUserMoneyChanges = new KUserMoneyChanges();
		kUserMoneyChanges.setUserId(cardOrders.getUserId());
		kUserMoneyChanges.setCardKindsId(cardOrders.getCardKindsId());
		List<KUserRanges> ranges = getCardMapper().findByUserAndCard(user.getId(), cardOrders.getStartCardNum(),
				cardOrders.getEndCardNum());
		KUserRanges kUserRanges;
		if (ranges.size() == 0) {
			return 3;
		}
		// ��֤�û���Ϣ�����뿪����
		try {
			if (cardOrders.getInvoiceId() == 0 || cardOrders.getInvoiceId().equals("0")) {
				if (cardOrders.getStartCardNum() != null && cardOrders.getEndCardNum() != null
						&& !cardOrders.getStartCardNum().equals("") && !cardOrders.getEndCardNum().equals("")) {

					if ((double) cardOrders.getShuLiang() != Double.parseDouble(cardOrders.getEndCardNum())
							- Double.parseDouble(cardOrders.getStartCardNum()) + 1) {
						return 1;// ��������
					}
					System.out.println(cardOrders.getMoney());
					System.out.println(cardOrders.getDanJia() * cardOrders.getShuLiang());
					if (cardOrders.getMoney() != cardOrders.getDanJia() * cardOrders.getShuLiang()) {
						return 2;// ����
					}

				}
			}
			cardOrders.setUserId(Integer.parseInt(nowUser.getId()));
			cardOrders.setOdersDate(dateString);
			cardOrders.setCardOrdersType(cardOrdersType);
			if (cardOrdersType == 1) {
				log.info("������¼��" + "�û���" + nowUser.getUsername() + "------�Ŷ���" + cardOrders.getStartCardNum() + "----"
						+ cardOrders.getEndCardNum() + "������" + cardOrders.getShuLiang() + "������" + cardOrders.getDanJia()
						+ "�ܽ����" + cardOrders.getMoney() + "�Ƿ񿪷�Ʊ" + cardOrders.getInvoiceId());

			} else {
				log.info("��ֵ��¼" + "�û���" + nowUser.getUsername() + "------�Ŷ���" + cardOrders.getStartCardNum() + "----"
						+ cardOrders.getEndCardNum() + "������" + cardOrders.getShuLiang() + "������" + cardOrders.getDanJia()
						+ "�ܽ����" + cardOrders.getMoney() + "�Ƿ񿪷�Ʊ" + cardOrders.getInvoiceId());

			}

		} catch (NumberFormatException e) {
			i = 1;
			e.printStackTrace();
		}
		// ���û�з����쳣���뽻�׼�¼
		if (i == 0) {
			try {

				System.out.println(ranges.size());
				if (ranges.size() != 0) {
					kUserRanges = ranges.get(0);
					int userType = nowUser.getUserType();
					if (userType == 1) {
						System.out.println(cardOrders.getDanJia());
						System.out.println(cardOrders.getDianShu());
						kUserMoneyChanges.setMoney(cardOrders.getDanJia() * cardOrders.getShuLiang());
						balance = nowUser.getBalance() - (cardOrders.getDanJia() * cardOrders.getShuLiang());
						if (balance < 0) {
							return 4;
						}
					} else if (userType == 2) {
						kUserMoneyChanges.setMoney(cardOrders.getDanJia() * cardOrders.getShuLiang()
								* (Double.parseDouble(kUserRanges.getCardKindsPrice())));
						balance = nowUser.getBalance() - (cardOrders.getDanJia() * cardOrders.getShuLiang()
								* (Double.parseDouble(kUserRanges.getCardKindsPrice())));
						if (balance < 0) {
							return 4;
						}
					}
					kUserMoneyChanges.setUserId(Integer.valueOf(nowUser.getId()));
					if (cardOrdersType == 1) {
						kUserMoneyChanges.setTransactionType(1);

					} else {
						kUserMoneyChanges.setTransactionType(2);

					}

					kUserMoneyChanges.setTransactionDate(dateString);
					kUserMoneyChanges.setBeforeBalance(nowUser.getBalance());
					kUserMoneyChanges.setAfterBalance(balance);

					log.info("�û����Ѽ�¼��" + kUserMoneyChanges.getUserId() + "�û���" + kUserMoneyChanges.getUserId() + "���"
							+ kUserMoneyChanges.getMoney() + "��Ʒid" + kUserMoneyChanges.getCardKindsId() + "��������"
							+ kUserMoneyChanges.getTransactionType() + "����ʱ��" + kUserMoneyChanges.getTransactionDate()
							+ "����ǰ���" + kUserMoneyChanges.getBeforeBalance() + "���"
							+ kUserMoneyChanges.getAfterBalance());

				} else {
					return 3;// �Ҳ����û�����Ŀ�
				}
			} catch (NumberFormatException e) {
				i = 1;
				e.printStackTrace();
			}
		}
		// �޸��û����
		if (i == 0) {
			try {
				getCardOrdersMapper().addCardOrders(cardOrders);
				getkUserMoneyChangesMapper().addCard(kUserMoneyChanges);
				nowUser.setBalance(balance);
				log.info("�۳��û����" + balance);
				getkUserMapper().updateUser(nowUser);
				KUser sessionUser = getkUserMapper().findByUser(nowUser.getId());
				request.getSession().setAttribute("username", sessionUser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.gc();;
		return 0;

	}

	/*
	 * ��ѯ�û�����Ŀ�����¼
	 */
	public List<KCardOrders> findByUserCardOrders(String cardKingsId, String startCardNum, String endCardNum) {
		return getCardOrdersMapper().findByUserCardOrders(cardKingsId, startCardNum, endCardNum);

	}

	/*
	 * ��ѯ�úŶ���û���˿���¼
	 */
	public List<KCardOrders> findByQuitCardorders(String cardKingsId, String startCardNum, String endCardNum) {
		return getCardOrdersMapper().findByQuitCardorders(cardKingsId, startCardNum, endCardNum, 3);
	}

	/*
	 * û�п������˿���
	 */
	@Transactional(rollbackFor = Exception.class)
	public int addQuitCardOrders(KCardOrders cardOrders, HttpServletRequest request) {
		int tishi = 0;
		double changeMoney = 0;
		double balance = 0;
		KUser kUser = (KUser) request.getSession().getAttribute("username");
		KUser nowUser = getkUserMapper().findByUser(kUser.getId());
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		// ��ѯ��û������ŶεĿ�����¼
		List<KCardOrders> addkCardOrdersList = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
				cardOrders.getEndCardNum(), 1);
		if (addkCardOrdersList.size() != 0) {
			// ��ѯ����Ŷ���û���˿���¼
			List<KCardOrders> cardOrdersQuits = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
					cardOrders.getEndCardNum(), 3);
			if (cardOrdersQuits.isEmpty()) {
				cardOrders.setCardOrdersType(3);
				double cardnum = Double.parseDouble(cardOrders.getEndCardNum())
						- Double.parseDouble(cardOrders.getStartCardNum()) + 1;
				// �ж��û�����
				if (kUser.getUserType() == 1) {
					// �ж��ǲ��ǻ�Ա��
					if (cardOrders.getCardKindsId() == 1 || cardOrders.getCardKindsId() == 2) {
						double money = addkCardOrdersList.get(0).getDanJia() * cardnum;

						cardOrders.setMoney(money);
					} else {
						KCardKinds kCardKinds = getkCardKindsMapper().findKingsByid(cardOrders.getCardKindsId());
						if (addkCardOrdersList.get(0).getInvoiceId() == 0) {
							if (kCardKinds != null && !kCardKinds.equals("")) {
								changeMoney = kCardKinds.getCardKindsDefault() * cardnum * cardOrders.getDianShu();
								cardOrders.setMoney(changeMoney);
							}

						}
					}
				} else if (kUser.getUserType() == 2) {
					List<KUserRanges> userRangs = getUserCardMapper().findByUserAndCard(kUser.getId(),
							cardOrders.getStartCardNum(), cardOrders.getEndCardNum());
					// �ж��ǲ��ǻ�Ա��
					if (cardOrders.getCardKindsId() == 1 || cardOrders.getCardKindsId() == 2) {
						double money = addkCardOrdersList.get(0).getDanJia() * cardnum;

						changeMoney = money * (Double.parseDouble(userRangs.get(0).getCardKindsPrice()));
						cardOrders.setMoney(money * (Double.parseDouble(userRangs.get(0).getCardKindsPrice())));

					} else {
						KCardKinds kCardKinds = getkCardKindsMapper().findKingsByid(cardOrders.getCardKindsId());

						if (kCardKinds != null && !kCardKinds.equals("")) {
							changeMoney = (kCardKinds.getCardKindsDefault() * cardnum * cardOrders.getDianShu()
									* (Double.parseDouble(userRangs.get(0).getCardKindsPrice())));
							cardOrders.setMoney(changeMoney);
						}

					}

				}

			} else {
				tishi = 1;// �úŶ��п�����¼
				log.info("��ѯ����ǰ�Ŷ����˿���¼");
			}
		} else {
			tishi = 2;// û�в鵽�úŶεĿ�����¼
			log.info("û�в鵽���û��ĵ�ǰ�ŶεĿ�����¼");
		}
		cardOrders.setOdersDate(dateString);
		log.info("�û�" + kUser.getUsername() + "�˿����˿����" + cardOrders.getMoney() + "���˿�����" + dateString);
		int i = 0;
		try {
			getCardOrdersMapper().addCardOrders(cardOrders);
		} catch (Exception e) {
			i = 1;
			tishi = 3;// ����˿���¼�쳣
			e.printStackTrace();
		}
		if (i == 0) {

			try {

				KUserMoneyChanges kUserMoneyChanges = new KUserMoneyChanges();
				kUserMoneyChanges.setCardKindsId(cardOrders.getCardKindsId());
				kUserMoneyChanges.setMoney(changeMoney);
				kUserMoneyChanges.setUserId(Integer.valueOf(nowUser.getId()));
				kUserMoneyChanges.setTransactionType(3);
				kUserMoneyChanges.setTransactionDate(dateString);
				kUserMoneyChanges.setBeforeBalance(nowUser.getBalance());
				balance = nowUser.getBalance() + changeMoney;
				kUserMoneyChanges.setAfterBalance(balance);

				log.info("�û��˿���¼��" + kUserMoneyChanges.getUserId() + "�û���" + kUserMoneyChanges.getUserId() + "���"
						+ kUserMoneyChanges.getMoney() + "��Ʒid" + kUserMoneyChanges.getCardKindsId() + "��������"
						+ kUserMoneyChanges.getTransactionType() + "����ʱ��" + kUserMoneyChanges.getTransactionDate()
						+ "����ǰ���" + kUserMoneyChanges.getBeforeBalance() + "���" + kUserMoneyChanges.getAfterBalance());

				getkUserMoneyChangesMapper().addCard(kUserMoneyChanges);
			} catch (NumberFormatException e) {
				i = 1;
				tishi = 4;// ��ӽ��׼�¼�쳣
				e.printStackTrace();
			}

			if (i == 0) {
				try {
					nowUser.setBalance(balance);
					log.info("�۳��û����" + balance);
					getkUserMapper().updateUser(nowUser);
					KUser sessionUser = getkUserMapper().findByUser(nowUser.getId());
					request.getSession().setAttribute("username", sessionUser);
				} catch (Exception e) {
					i = 1;
					tishi = 5;// �޸��û�����쳣
					e.printStackTrace();
				}
			}
		}
		System.gc();
		return tishi;

	}

	/*
	 * �Ѿ��������˿���
	 */
	@Transactional(rollbackFor = Exception.class)
	public int addOpenCardOrders(KCardOrders cardOrders, HttpServletRequest request) {
		int tishi = 0;
		double changeMoney = 0;
		double balance = 0;
		KUser kUser = (KUser) request.getSession().getAttribute("username");
		KUser nowUser = getkUserMapper().findByUser(kUser.getId());
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		// ��ѯ��û������ŶεĿ�����¼
		List<KCardOrders> addkCardOrdersList = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
				cardOrders.getEndCardNum(), 1);
		if (addkCardOrdersList.size() != 0) {
			// ��ѯ����Ŷ���û���˿���¼
			List<KCardOrders> cardOrdersQuits = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
					cardOrders.getEndCardNum(), 3);
			if (cardOrdersQuits.isEmpty()) {
				cardOrders.setCardOrdersType(3);
				double cardnum = Double.parseDouble(cardOrders.getEndCardNum())
						- Double.parseDouble(cardOrders.getStartCardNum()) + 1;
				// �ж��û�����
				List<KUserRanges> userRangs = getUserCardMapper().findByUserAndCard(kUser.getId(),
						cardOrders.getStartCardNum(), cardOrders.getEndCardNum());
				if (kUser.getUserType() == 1) {
					// �ж��ǲ��ǻ�Ա��
					if (cardOrders.getCardKindsId() == 1 || cardOrders.getCardKindsId() == 2) {
						double money = addkCardOrdersList.get(0).getDianShu() * cardnum;
						changeMoney = money - money * (Double.parseDouble(userRangs.get(0).getCardKindsCost()) - 1);
						cardOrders.setMoney(
								money - money * (Double.parseDouble(userRangs.get(0).getCardKindsCost()) - 1));

					} else {
						KCardKinds kCardKinds = getkCardKindsMapper().findKingsByid(cardOrders.getCardKindsId());

						if (kCardKinds != null && !kCardKinds.equals("")) {

							double money = kCardKinds.getCardKindsDefault() * cardnum * cardOrders.getDianShu()
									- (kCardKinds.getCardKindsDefault() * cardnum * cardOrders.getDianShu()
											* (Double.parseDouble(userRangs.get(0).getCardKindsCost()) - 1));
							changeMoney = money;
							cardOrders.setMoney(money);
						}

					}
				} else if (kUser.getUserType() == 2) {
					cardOrders.setMoney((double) 0);
				}

			} else {
				tishi = 1;// �úŶ��п�����¼
				log.info("��ѯ����ǰ�Ŷ����˿���¼");
			}
		} else {
			tishi = 2;// û�в鵽�úŶεĿ�����¼
			log.info("û�в鵽���û��ĵ�ǰ�ŶεĿ�����¼");
		}
		cardOrders.setOdersDate(dateString);
		log.info("�û�" + kUser.getUsername() + "�˿����˿����" + cardOrders.getMoney() + "���˿�����" + cardOrders.getOdersDate());
		int i = 0;
		try {
			getCardOrdersMapper().addCardOrders(cardOrders);
		} catch (Exception e) {
			i = 1;
			tishi = 3;// ����˿���¼�쳣
			e.printStackTrace();
		}
		if (i == 0) {

			try {

				KUserMoneyChanges kUserMoneyChanges = new KUserMoneyChanges();
				kUserMoneyChanges.setCardKindsId(cardOrders.getCardKindsId());
				kUserMoneyChanges.setMoney(changeMoney);
				kUserMoneyChanges.setUserId(Integer.valueOf(nowUser.getId()));
				kUserMoneyChanges.setTransactionType(3);
				kUserMoneyChanges.setTransactionDate(dateString);
				kUserMoneyChanges.setBeforeBalance(nowUser.getBalance());
				balance = nowUser.getBalance() + changeMoney;
				kUserMoneyChanges.setAfterBalance(balance);

				log.info("�û��˿���¼��" + kUserMoneyChanges.getUserId() + "�û���" + kUserMoneyChanges.getUserId() + "���"
						+ kUserMoneyChanges.getMoney() + "��Ʒid" + kUserMoneyChanges.getCardKindsId() + "��������"
						+ kUserMoneyChanges.getTransactionType() + "����ʱ��" + kUserMoneyChanges.getTransactionDate()
						+ "����ǰ���" + kUserMoneyChanges.getBeforeBalance() + "���" + kUserMoneyChanges.getAfterBalance());

				getkUserMoneyChangesMapper().addCard(kUserMoneyChanges);
			} catch (NumberFormatException e) {
				i = 1;
				tishi = 4;// ��ӽ��׼�¼�쳣
				e.printStackTrace();
			}

			if (i == 0) {
				try {
					nowUser.setBalance(balance);
					log.info("�۳��û����" + balance);
					getkUserMapper().updateUser(nowUser);
					KUser sessionUser = getkUserMapper().findByUser(nowUser.getId());
					request.getSession().setAttribute("username", sessionUser);
				} catch (Exception e) {
					tishi = 5;// �޸��û�����쳣
					e.printStackTrace();
				}
			}

		}
		System.gc();
		return tishi;
	}

	/*
	 * (non-Javadoc) ���ݵ�¼�û���ѯ�û������Ѽ�¼
	 * 
	 * @see com.service.KCardOrdersService#findOrdersByUser(javax.servlet.http.
	 * HttpServletRequest, java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public EasyUiDataGridJson<Map<String, String>> findOrdersByUser(HttpServletRequest request, String startTime,
			String endTime, int page, int rows, int userType) {
		List<Map<String, String>> list = getCardOrdersMapper().findOrdersByUser(startTime, endTime, (page - 1) * rows,
				rows, userType);
		int totle = getCardOrdersMapper().countOrdersByUser(startTime, endTime, userType);
		EasyUiDataGridJson<Map<String, String>> json = new EasyUiDataGridJson<Map<String, String>>();
		json.setTotal(totle);
		json.setRows(list);
		System.gc();
		return json;

	}

	/*
	 * �û������ӿ�
	 */
	public Map<String, String> openCardInterface(HttpServletRequest request, String cardType) {

		Map<String, String> map = new HashMap<String, String>();
		int i = 0;
		KCardOrders cardOrders = new KCardOrders();
		KUserRanges kUserRanges = null;

		Date date = new Date();
		String dateString = formatter.format(date);
		double balance = 0;
		KUserMoneyChanges moneyChanges = new KUserMoneyChanges();
		try {

			// ��json��ʽ���ַ���ת��Ϊjson���󣬲�ȡ�øö���ġ�userName������ֵ
			String startCardNumStr = request.getParameter("startCardNum");
			String endCardNumStr = request.getParameter("endCardNum");
			String cardKindsName = request.getParameter("cardKindsName");
			String invoiceId = request.getParameter("invoiceId");
			String dianShu = request.getParameter("dianShu");
			String danJia = request.getParameter("danJia");
			String closingDate = request.getParameter("closingDate");

			if (startCardNumStr != null && !startCardNumStr.equalsIgnoreCase("")) {
				cardOrders.setStartCardNum(startCardNumStr);
			} else {
				map.put("error", "��ʼ�Ŷδ���");
				return map;
			}
			if (endCardNumStr != null && !endCardNumStr.equalsIgnoreCase("")) {
				cardOrders.setEndCardNum(endCardNumStr);
			} else {
				map.put("error", "�����Ŷδ���");
				return map;
			}
			if (cardKindsName != null && !cardKindsName.equalsIgnoreCase("")) {
				KCardKinds cardKinds = getkCardKindsMapper().fingByname(cardKindsName);
				cardOrders.setCardKindsId(cardKinds.getId());
			} else {
				map.put("error", "û�и����͵Ŀ�");
				return map;
			}
			if (invoiceId != null && !invoiceId.equalsIgnoreCase("")) {
				cardOrders.setInvoiceId(Integer.parseInt(invoiceId));
			} else {
				map.put("error", "��Ʊ�������");
				return map;
			}
			if (dianShu != null && !dianShu.equalsIgnoreCase("")) {
				cardOrders.setDianShu(Integer.parseInt(dianShu));
			} else {
				map.put("error", "����������������");
				return map;
			}

			if (danJia != null && !danJia.equalsIgnoreCase("")) {
				cardOrders.setDanJia(Double.parseDouble(danJia));
			} else {
				map.put("error", "���۴���");
				return map;
			}
			if (closingDate != null && !closingDate.equals("")) {
				cardOrders.setClosingDate(closingDate);
			} else {
				map.put("error", "����ʱ���ȡ����");
				return map;
			}
			if (startCardNumStr.length() != 11 || endCardNumStr.length() != 11) {
				map.put("error", "��ʼ�Ŷ�λ������");
				return map;
			}
			String startprefix = startCardNumStr.substring(0, 4);
			String endprefix = endCardNumStr.substring(0, 4);
			if (!startprefix.equals(endprefix)) {
				map.put("error", "��ʼ�ͽ�����ǰ׺��ͬ");
				return map;
			}
			if (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr) > 0) {
				cardOrders.setShuLiang(
						(int) (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr) + 1));
			} else {
				map.put("error", "��������");
				return map;
			}
			if (cardType.trim().equals("1")) {
				if (getCardOrdersMapper()
						.findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1).size() > 0) {
					map.put("error", "�úŶ����п�����¼");
					return map;
				}
			} else {
				if (getCardOrdersMapper()
						.findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1).size() <= 0) {
					map.put("error", "�úŶ�û�п�����¼");
					return map;
				}
			}

			List<KUserRanges> kUserRangesList = getUserCardMapper().findByCard(startCardNumStr, endCardNumStr);
			if (kUserRangesList.size() != 0) {
				kUserRanges = kUserRangesList.get(0);
			}
			if (kUserRanges == null) {
				map.put("error", "��ο���û�й�����");
				return map;
			}
			cardOrders.setUserId(kUserRanges.getUserId());
			cardOrders.setMoney(cardOrders.getShuLiang() * cardOrders.getDanJia());

			cardOrders.setCardOrdersType(Integer.parseInt(cardType));

			cardOrders.setOdersDate(dateString);

		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}
		KUser kUser = getkUserMapper().findByUser(cardOrders.getUserId().toString());
		if (i == 0) {
			try {

				moneyChanges.setUserId(Integer.parseInt(kUser.getId()));
				moneyChanges.setCardKindsId(Integer.parseInt(kUserRanges.getCardKindID()));
				moneyChanges.setTransactionType(1);
				moneyChanges.setTransactionDate(dateString);
				moneyChanges.setBeforeBalance(kUser.getBalance());
				List<KUserRanges> ranges = getCardMapper().findByUserAndCard(kUser.getId(),
						cardOrders.getStartCardNum(), cardOrders.getEndCardNum());
				if (ranges.size() != 0) {
					kUserRanges = ranges.get(0);
					int userType = kUser.getUserType();
					if (userType == 1) {

						moneyChanges.setMoney(cardOrders.getDanJia() * cardOrders.getShuLiang());
						balance = kUser.getBalance() - (cardOrders.getDanJia() * cardOrders.getShuLiang());
						if (kUser.getBalance() < balance) {
							map.put("error", "�û�����");
							return map;
						}
					} else if (userType == 2) {
						moneyChanges.setMoney(cardOrders.getDanJia() * cardOrders.getShuLiang()
								* (Double.parseDouble(kUserRanges.getCardKindsPrice())));
						balance = kUser.getBalance() - (cardOrders.getDanJia() * cardOrders.getShuLiang()
								* (Double.parseDouble(kUserRanges.getCardKindsPrice())));
						String xml = utils.getProportion();
						String points = utils.readUsercardXml(xml);
						if (points != null && !points.equals("")) {
							double pointsDouble = Double.parseDouble(points);
							double notUserd = pointsDouble * (kUser.getJmBiLi());
							double balancejiamen = kUser.getBalance() - notUserd;
							if (balancejiamen < (cardOrders.getDanJia() * cardOrders.getShuLiang()
									* (Double.parseDouble(kUserRanges.getCardKindsPrice())))) {
								map.put("error", "�û�����");
								return map;
							}
						}

					}

					moneyChanges.setAfterBalance(balance);
					if (cardType.trim().equals("1")) {
						log.info("�û����Ѽ�¼��" + moneyChanges.getUserId() + "�û���" + moneyChanges.getUserId() + "���"
								+ moneyChanges.getMoney() + "��Ʒid" + moneyChanges.getCardKindsId() + "��������"
								+ moneyChanges.getTransactionType() + "����ʱ��" + moneyChanges.getTransactionDate()
								+ "����ǰ���" + moneyChanges.getBeforeBalance() + "���" + moneyChanges.getAfterBalance());

						
					} else if (cardType.trim().equals("2")) {
						log.info("�û���ֵ��¼��" + moneyChanges.getUserId() + "�û���" + moneyChanges.getUserId() + "���"
								+ moneyChanges.getMoney() + "��Ʒid" + moneyChanges.getCardKindsId() + "��������"
								+ moneyChanges.getTransactionType() + "����ʱ��" + moneyChanges.getTransactionDate()
								+ "����ǰ���" + moneyChanges.getBeforeBalance() + "���" + moneyChanges.getAfterBalance());

						
					}

				}
			} catch (Exception e) {
				i = 1;
				e.printStackTrace();
			}

		}
		if (i == 0) {
			kUser.setBalance(balance);
			log.info("�۳��û����" + balance);
			try {
				getkUserMoneyChangesMapper().addCard(moneyChanges);
				getCardOrdersMapper().addCardOrders(cardOrders);
				getkUserMapper().updateUser(kUser);
				if (cardType.trim().equals("1")) {
					map.put("success", "�����ɹ�");
				}else{
					map.put("error", "�����Ѿ�����ͨ��");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		System.gc();
		return map;
	}

	@Override
	public Map<String, String> quitCardInterface(HttpServletRequest request) {
		KCardOrders cardOrders = new KCardOrders();
		Map<String, String> map = new HashMap<String, String>();
		KUserRanges kUserRanges = null;
		KUser kUser;
		KCardOrders openOrders = null;
		KUserMoneyChanges moneyChanges = new KUserMoneyChanges();
		double changeMoney = 0;
		double balance = 0;
		
		// �˿���֤
		String startCardNumStr = request.getParameter("startCardNum");
		String endCardNumStr = request.getParameter("endCardNum");
		String cardKindsName = request.getParameter("cardKindsName");
		String invoiceId = request.getParameter("invoiceId");
		String dianShu = request.getParameter("dianShu");
		String danJia = request.getParameter("danJia");

		if (startCardNumStr != null && !startCardNumStr.equalsIgnoreCase("")) {
			cardOrders.setStartCardNum(startCardNumStr);
			cardOrders.setEndCardNum(endCardNumStr);
		} else {
			map.put("error", "��ʼ�Ŷδ���");
			return map;
		}
		if (endCardNumStr != null && !endCardNumStr.equalsIgnoreCase("")) {
			cardOrders.setStartCardNum(endCardNumStr);
		} else {
			map.put("error", "�����Ŷδ���");
			return map;
		}
		if (cardKindsName != null && !cardKindsName.equalsIgnoreCase("")) {
			KCardKinds cardKinds = getkCardKindsMapper().fingByname(cardKindsName);
			cardOrders.setCardKindsId(cardKinds.getId());
		} else {
			map.put("error", "û�и����͵Ŀ�");
			return map;
		}
		if (invoiceId != null && !invoiceId.equalsIgnoreCase("")) {
			cardOrders.setInvoiceId(Integer.parseInt(invoiceId));
		} else {
			map.put("error", "��Ʊ�������");
			return map;
		}
		if (dianShu != null && !dianShu.equalsIgnoreCase("")) {
			cardOrders.setDianShu(Integer.parseInt(dianShu));
		} else {
			map.put("error", "����������������");
			return map;
		}

		if (danJia != null && !danJia.equalsIgnoreCase("")) {
			cardOrders.setDanJia(Double.parseDouble(danJia));
		} else {
			map.put("error", "���۴���");
			return map;
		}
		if (startCardNumStr.length() != 11 || endCardNumStr.length() != 11) {
			map.put("error", "��ʼ�Ŷ�λ������");
			return map;
		}
		String startprefix = startCardNumStr.substring(0, 4);
		String endprefix = endCardNumStr.substring(0, 4);
		if (!startprefix.equals(endprefix)) {
			map.put("error", "��ʼ�ͽ�����ǰ׺��ͬ");
			return map;
		}
		if (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr) > 0) {
			cardOrders.setShuLiang((int) (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr)));
		} else {
			map.put("error", "��������");
			return map;
		}

		if (getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1)
				.size() <= 0) {
			map.put("error", "û�иúŶεĿ�����¼");
			return map;
		}
		openOrders = getCardOrdersMapper()
				.findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1).get(0);

		if (getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 4)
				.size() > 0) {
			map.put("error", "�úŶ����˿���¼");
			return map;
		}

		List<KUserRanges> kUserRangesList = getUserCardMapper().findByCard(startCardNumStr, endCardNumStr);

		if (kUserRangesList.size() != 0) {
			kUserRanges = kUserRangesList.get(0);
			cardOrders.setCardKindsId(Integer.parseInt(kUserRanges.getCardKindID()));
		}

		if (kUserRanges == null || kUserRanges.equals("")) {
			map.put("error", "��ο���û�й�����");
			return map;
		}
		kUser = getkUserMapper().findByUser(kUserRangesList.get(0).getUserId().toString());
		cardOrders.setOdersDate(formatter.format(new Date()));
		cardOrders.setDianShu(Integer.parseInt(dianShu));
		cardOrders.setDanJia(Double.parseDouble(danJia));
		cardOrders.setMoney(cardOrders.getDanJia() * cardOrders.getShuLiang());
		cardOrders.setInvoiceId(openOrders.getInvoiceId());
		cardOrders.setUserId(openOrders.getUserId());
		cardOrders.setCardOrdersType(4);

		if (kUser.getUserType() == 1) {
			if (cardOrders.getCardKindsId() == 1 || cardOrders.getCardKindsId() == 2) {

				changeMoney = cardOrders.getMoney()
						- cardOrders.getMoney() * (Double.parseDouble(kUserRanges.getCardKindsCost()) - 1);
				cardOrders.setMoney(cardOrders.getMoney()
						- cardOrders.getMoney() * (Double.parseDouble(kUserRanges.getCardKindsCost()) - 1));
			} else {
				KCardKinds kCardKinds = getkCardKindsMapper().findKingsByid(cardOrders.getCardKindsId());
				if (kCardKinds != null && !kCardKinds.equals("")) {

					double money = kCardKinds.getCardKindsDefault() * cardOrders.getShuLiang() * cardOrders.getDianShu()
							- (kCardKinds.getCardKindsDefault() * cardOrders.getShuLiang() * cardOrders.getDianShu()
									* (Double.parseDouble(kUserRanges.getCardKindsCost()) - 1));
					changeMoney = money;
					cardOrders.setMoney(money);
				}
			}
		} else if (kUser.getUserType() == 2) {
			cardOrders.setMoney((double) 0);
		}
		log.info("�û�" + kUser.getUsername() + "�˿����˿����" + cardOrders.getMoney() + "���˿�����" + cardOrders.getOdersDate());
		moneyChanges.setUserId(cardOrders.getUserId());
		moneyChanges.setCardKindsId(cardOrders.getCardKindsId());
		moneyChanges.setTransactionDate("3");
		moneyChanges.setTransactionDate(formatter.format(new Date()));
		moneyChanges.setBeforeBalance(kUser.getBalance());
		balance = kUser.getBalance() + changeMoney;
		moneyChanges.setAfterBalance(balance);
		moneyChanges.setMoney(changeMoney);
		log.info("�û��˿���¼��" + moneyChanges.getUserId() + "�û���" + moneyChanges.getUserId() + "���"
				+ moneyChanges.getMoney() + "��Ʒid" + moneyChanges.getCardKindsId() + "��������"
				+ moneyChanges.getTransactionType() + "����ʱ��" + moneyChanges.getTransactionDate() + "����ǰ���"
				+ moneyChanges.getBeforeBalance() + "���" + moneyChanges.getAfterBalance());

		kUser.setBalance(balance);
		log.info("�۳��û����" + balance);
		try {
			getCardOrdersMapper().addCardOrders(cardOrders);
			getkUserMoneyChangesMapper().addCard(moneyChanges);
			getkUserMapper().updateUser(kUser);
			map.put("success","�˿��ɹ�");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.gc();
		return map;
	}

}
