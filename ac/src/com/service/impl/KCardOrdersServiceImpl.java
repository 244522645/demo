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

	// 开卡添加
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
		// 验证用户信息并插入开卡单
		try {
			if (cardOrders.getInvoiceId() == 0 || cardOrders.getInvoiceId().equals("0")) {
				if (cardOrders.getStartCardNum() != null && cardOrders.getEndCardNum() != null
						&& !cardOrders.getStartCardNum().equals("") && !cardOrders.getEndCardNum().equals("")) {

					if ((double) cardOrders.getShuLiang() != Double.parseDouble(cardOrders.getEndCardNum())
							- Double.parseDouble(cardOrders.getStartCardNum()) + 1) {
						return 1;// 数量不对
					}
					System.out.println(cardOrders.getMoney());
					System.out.println(cardOrders.getDanJia() * cardOrders.getShuLiang());
					if (cardOrders.getMoney() != cardOrders.getDanJia() * cardOrders.getShuLiang()) {
						return 2;// 金额不对
					}

				}
			}
			cardOrders.setUserId(Integer.parseInt(nowUser.getId()));
			cardOrders.setOdersDate(dateString);
			cardOrders.setCardOrdersType(cardOrdersType);
			if (cardOrdersType == 1) {
				log.info("开卡记录：" + "用户名" + nowUser.getUsername() + "------号段是" + cardOrders.getStartCardNum() + "----"
						+ cardOrders.getEndCardNum() + "数量是" + cardOrders.getShuLiang() + "单价是" + cardOrders.getDanJia()
						+ "总金额是" + cardOrders.getMoney() + "是否开发票" + cardOrders.getInvoiceId());

			} else {
				log.info("充值记录" + "用户名" + nowUser.getUsername() + "------号段是" + cardOrders.getStartCardNum() + "----"
						+ cardOrders.getEndCardNum() + "数量是" + cardOrders.getShuLiang() + "单价是" + cardOrders.getDanJia()
						+ "总金额是" + cardOrders.getMoney() + "是否开发票" + cardOrders.getInvoiceId());

			}

		} catch (NumberFormatException e) {
			i = 1;
			e.printStackTrace();
		}
		// 如果没有发生异常插入交易记录
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

					log.info("用户消费记录：" + kUserMoneyChanges.getUserId() + "用户名" + kUserMoneyChanges.getUserId() + "金额"
							+ kUserMoneyChanges.getMoney() + "产品id" + kUserMoneyChanges.getCardKindsId() + "交易类型"
							+ kUserMoneyChanges.getTransactionType() + "交易时间" + kUserMoneyChanges.getTransactionDate()
							+ "交易前金额" + kUserMoneyChanges.getBeforeBalance() + "余款"
							+ kUserMoneyChanges.getAfterBalance());

				} else {
					return 3;// 找不到用户下面的卡
				}
			} catch (NumberFormatException e) {
				i = 1;
				e.printStackTrace();
			}
		}
		// 修改用户余额
		if (i == 0) {
			try {
				getCardOrdersMapper().addCardOrders(cardOrders);
				getkUserMoneyChangesMapper().addCard(kUserMoneyChanges);
				nowUser.setBalance(balance);
				log.info("扣除用户余额" + balance);
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
	 * 查询用户下面的开卡记录
	 */
	public List<KCardOrders> findByUserCardOrders(String cardKingsId, String startCardNum, String endCardNum) {
		return getCardOrdersMapper().findByUserCardOrders(cardKingsId, startCardNum, endCardNum);

	}

	/*
	 * 查询该号段有没有退卡记录
	 */
	public List<KCardOrders> findByQuitCardorders(String cardKingsId, String startCardNum, String endCardNum) {
		return getCardOrdersMapper().findByQuitCardorders(cardKingsId, startCardNum, endCardNum, 3);
	}

	/*
	 * 没有开卡的退卡单
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
		// 查询有没有这个号段的开卡记录
		List<KCardOrders> addkCardOrdersList = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
				cardOrders.getEndCardNum(), 1);
		if (addkCardOrdersList.size() != 0) {
			// 查询这个号段有没有退卡记录
			List<KCardOrders> cardOrdersQuits = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
					cardOrders.getEndCardNum(), 3);
			if (cardOrdersQuits.isEmpty()) {
				cardOrders.setCardOrdersType(3);
				double cardnum = Double.parseDouble(cardOrders.getEndCardNum())
						- Double.parseDouble(cardOrders.getStartCardNum()) + 1;
				// 判断用户类型
				if (kUser.getUserType() == 1) {
					// 判断是不是会员卡
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
					// 判断是不是会员卡
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
				tishi = 1;// 该号段有开卡记录
				log.info("查询到当前号段有退卡记录");
			}
		} else {
			tishi = 2;// 没有查到该号段的开卡记录
			log.info("没有查到改用户的当前号段的开卡记录");
		}
		cardOrders.setOdersDate(dateString);
		log.info("用户" + kUser.getUsername() + "退卡，退卡金额" + cardOrders.getMoney() + "，退卡日期" + dateString);
		int i = 0;
		try {
			getCardOrdersMapper().addCardOrders(cardOrders);
		} catch (Exception e) {
			i = 1;
			tishi = 3;// 添加退卡记录异常
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

				log.info("用户退卡记录：" + kUserMoneyChanges.getUserId() + "用户名" + kUserMoneyChanges.getUserId() + "金额"
						+ kUserMoneyChanges.getMoney() + "产品id" + kUserMoneyChanges.getCardKindsId() + "交易类型"
						+ kUserMoneyChanges.getTransactionType() + "交易时间" + kUserMoneyChanges.getTransactionDate()
						+ "交易前金额" + kUserMoneyChanges.getBeforeBalance() + "余款" + kUserMoneyChanges.getAfterBalance());

				getkUserMoneyChangesMapper().addCard(kUserMoneyChanges);
			} catch (NumberFormatException e) {
				i = 1;
				tishi = 4;// 添加交易记录异常
				e.printStackTrace();
			}

			if (i == 0) {
				try {
					nowUser.setBalance(balance);
					log.info("扣除用户余额" + balance);
					getkUserMapper().updateUser(nowUser);
					KUser sessionUser = getkUserMapper().findByUser(nowUser.getId());
					request.getSession().setAttribute("username", sessionUser);
				} catch (Exception e) {
					i = 1;
					tishi = 5;// 修改用户余额异常
					e.printStackTrace();
				}
			}
		}
		System.gc();
		return tishi;

	}

	/*
	 * 已经开卡的退卡单
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
		// 查询有没有这个号段的开卡记录
		List<KCardOrders> addkCardOrdersList = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
				cardOrders.getEndCardNum(), 1);
		if (addkCardOrdersList.size() != 0) {
			// 查询这个号段有没有退卡记录
			List<KCardOrders> cardOrdersQuits = getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(),
					cardOrders.getEndCardNum(), 3);
			if (cardOrdersQuits.isEmpty()) {
				cardOrders.setCardOrdersType(3);
				double cardnum = Double.parseDouble(cardOrders.getEndCardNum())
						- Double.parseDouble(cardOrders.getStartCardNum()) + 1;
				// 判断用户类型
				List<KUserRanges> userRangs = getUserCardMapper().findByUserAndCard(kUser.getId(),
						cardOrders.getStartCardNum(), cardOrders.getEndCardNum());
				if (kUser.getUserType() == 1) {
					// 判断是不是会员卡
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
				tishi = 1;// 该号段有开卡记录
				log.info("查询到当前号段有退卡记录");
			}
		} else {
			tishi = 2;// 没有查到该号段的开卡记录
			log.info("没有查到改用户的当前号段的开卡记录");
		}
		cardOrders.setOdersDate(dateString);
		log.info("用户" + kUser.getUsername() + "退卡，退卡金额" + cardOrders.getMoney() + "，退卡日期" + cardOrders.getOdersDate());
		int i = 0;
		try {
			getCardOrdersMapper().addCardOrders(cardOrders);
		} catch (Exception e) {
			i = 1;
			tishi = 3;// 添加退卡记录异常
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

				log.info("用户退卡记录：" + kUserMoneyChanges.getUserId() + "用户名" + kUserMoneyChanges.getUserId() + "金额"
						+ kUserMoneyChanges.getMoney() + "产品id" + kUserMoneyChanges.getCardKindsId() + "交易类型"
						+ kUserMoneyChanges.getTransactionType() + "交易时间" + kUserMoneyChanges.getTransactionDate()
						+ "交易前金额" + kUserMoneyChanges.getBeforeBalance() + "余款" + kUserMoneyChanges.getAfterBalance());

				getkUserMoneyChangesMapper().addCard(kUserMoneyChanges);
			} catch (NumberFormatException e) {
				i = 1;
				tishi = 4;// 添加交易记录异常
				e.printStackTrace();
			}

			if (i == 0) {
				try {
					nowUser.setBalance(balance);
					log.info("扣除用户余额" + balance);
					getkUserMapper().updateUser(nowUser);
					KUser sessionUser = getkUserMapper().findByUser(nowUser.getId());
					request.getSession().setAttribute("username", sessionUser);
				} catch (Exception e) {
					tishi = 5;// 修改用户余额异常
					e.printStackTrace();
				}
			}

		}
		System.gc();
		return tishi;
	}

	/*
	 * (non-Javadoc) 根据登录用户查询用户的消费记录
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
	 * 用户开卡接口
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

			// 将json格式的字符串转换为json对象，并取得该对象的“userName”属性值
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
				map.put("error", "开始号段错误");
				return map;
			}
			if (endCardNumStr != null && !endCardNumStr.equalsIgnoreCase("")) {
				cardOrders.setEndCardNum(endCardNumStr);
			} else {
				map.put("error", "结束号段错误");
				return map;
			}
			if (cardKindsName != null && !cardKindsName.equalsIgnoreCase("")) {
				KCardKinds cardKinds = getkCardKindsMapper().fingByname(cardKindsName);
				cardOrders.setCardKindsId(cardKinds.getId());
			} else {
				map.put("error", "没有该类型的卡");
				return map;
			}
			if (invoiceId != null && !invoiceId.equalsIgnoreCase("")) {
				cardOrders.setInvoiceId(Integer.parseInt(invoiceId));
			} else {
				map.put("error", "发票传输错误");
				return map;
			}
			if (dianShu != null && !dianShu.equalsIgnoreCase("")) {
				cardOrders.setDianShu(Integer.parseInt(dianShu));
			} else {
				map.put("error", "点数（次数）错误");
				return map;
			}

			if (danJia != null && !danJia.equalsIgnoreCase("")) {
				cardOrders.setDanJia(Double.parseDouble(danJia));
			} else {
				map.put("error", "单价错误");
				return map;
			}
			if (closingDate != null && !closingDate.equals("")) {
				cardOrders.setClosingDate(closingDate);
			} else {
				map.put("error", "到期时间获取错误");
				return map;
			}
			if (startCardNumStr.length() != 11 || endCardNumStr.length() != 11) {
				map.put("error", "开始号段位数不对");
				return map;
			}
			String startprefix = startCardNumStr.substring(0, 4);
			String endprefix = endCardNumStr.substring(0, 4);
			if (!startprefix.equals(endprefix)) {
				map.put("error", "开始和结束的前缀不同");
				return map;
			}
			if (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr) > 0) {
				cardOrders.setShuLiang(
						(int) (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr) + 1));
			} else {
				map.put("error", "数量不对");
				return map;
			}
			if (cardType.trim().equals("1")) {
				if (getCardOrdersMapper()
						.findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1).size() > 0) {
					map.put("error", "该号段已有开卡记录");
					return map;
				}
			} else {
				if (getCardOrdersMapper()
						.findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1).size() <= 0) {
					map.put("error", "该号段没有开卡记录");
					return map;
				}
			}

			List<KUserRanges> kUserRangesList = getUserCardMapper().findByCard(startCardNumStr, endCardNumStr);
			if (kUserRangesList.size() != 0) {
				kUserRanges = kUserRangesList.get(0);
			}
			if (kUserRanges == null) {
				map.put("error", "这段卡号没有归属人");
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
							map.put("error", "用户余额不足");
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
								map.put("error", "用户余额不足");
								return map;
							}
						}

					}

					moneyChanges.setAfterBalance(balance);
					if (cardType.trim().equals("1")) {
						log.info("用户消费记录：" + moneyChanges.getUserId() + "用户名" + moneyChanges.getUserId() + "金额"
								+ moneyChanges.getMoney() + "产品id" + moneyChanges.getCardKindsId() + "交易类型"
								+ moneyChanges.getTransactionType() + "交易时间" + moneyChanges.getTransactionDate()
								+ "交易前金额" + moneyChanges.getBeforeBalance() + "余款" + moneyChanges.getAfterBalance());

						
					} else if (cardType.trim().equals("2")) {
						log.info("用户充值记录：" + moneyChanges.getUserId() + "用户名" + moneyChanges.getUserId() + "金额"
								+ moneyChanges.getMoney() + "产品id" + moneyChanges.getCardKindsId() + "交易类型"
								+ moneyChanges.getTransactionType() + "交易时间" + moneyChanges.getTransactionDate()
								+ "交易前金额" + moneyChanges.getBeforeBalance() + "余款" + moneyChanges.getAfterBalance());

						
					}

				}
			} catch (Exception e) {
				i = 1;
				e.printStackTrace();
			}

		}
		if (i == 0) {
			kUser.setBalance(balance);
			log.info("扣除用户余额" + balance);
			try {
				getkUserMoneyChangesMapper().addCard(moneyChanges);
				getCardOrdersMapper().addCardOrders(cardOrders);
				getkUserMapper().updateUser(kUser);
				if (cardType.trim().equals("1")) {
					map.put("success", "开卡成功");
				}else{
					map.put("error", "卡号已经被开通过");
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
		
		// 退卡验证
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
			map.put("error", "开始号段错误");
			return map;
		}
		if (endCardNumStr != null && !endCardNumStr.equalsIgnoreCase("")) {
			cardOrders.setStartCardNum(endCardNumStr);
		} else {
			map.put("error", "结束号段错误");
			return map;
		}
		if (cardKindsName != null && !cardKindsName.equalsIgnoreCase("")) {
			KCardKinds cardKinds = getkCardKindsMapper().fingByname(cardKindsName);
			cardOrders.setCardKindsId(cardKinds.getId());
		} else {
			map.put("error", "没有该类型的卡");
			return map;
		}
		if (invoiceId != null && !invoiceId.equalsIgnoreCase("")) {
			cardOrders.setInvoiceId(Integer.parseInt(invoiceId));
		} else {
			map.put("error", "发票传输错误");
			return map;
		}
		if (dianShu != null && !dianShu.equalsIgnoreCase("")) {
			cardOrders.setDianShu(Integer.parseInt(dianShu));
		} else {
			map.put("error", "点数（次数）错误");
			return map;
		}

		if (danJia != null && !danJia.equalsIgnoreCase("")) {
			cardOrders.setDanJia(Double.parseDouble(danJia));
		} else {
			map.put("error", "单价错误");
			return map;
		}
		if (startCardNumStr.length() != 11 || endCardNumStr.length() != 11) {
			map.put("error", "开始号段位数不对");
			return map;
		}
		String startprefix = startCardNumStr.substring(0, 4);
		String endprefix = endCardNumStr.substring(0, 4);
		if (!startprefix.equals(endprefix)) {
			map.put("error", "开始和结束的前缀不同");
			return map;
		}
		if (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr) > 0) {
			cardOrders.setShuLiang((int) (Double.parseDouble(endCardNumStr) - Double.parseDouble(startCardNumStr)));
		} else {
			map.put("error", "数量不对");
			return map;
		}

		if (getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1)
				.size() <= 0) {
			map.put("error", "没有该号段的开卡记录");
			return map;
		}
		openOrders = getCardOrdersMapper()
				.findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 1).get(0);

		if (getCardOrdersMapper().findOrdersByCardNum(cardOrders.getStartCardNum(), cardOrders.getEndCardNum(), 4)
				.size() > 0) {
			map.put("error", "该号段有退卡记录");
			return map;
		}

		List<KUserRanges> kUserRangesList = getUserCardMapper().findByCard(startCardNumStr, endCardNumStr);

		if (kUserRangesList.size() != 0) {
			kUserRanges = kUserRangesList.get(0);
			cardOrders.setCardKindsId(Integer.parseInt(kUserRanges.getCardKindID()));
		}

		if (kUserRanges == null || kUserRanges.equals("")) {
			map.put("error", "这段卡号没有归属人");
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
		log.info("用户" + kUser.getUsername() + "退卡，退卡金额" + cardOrders.getMoney() + "，退卡日期" + cardOrders.getOdersDate());
		moneyChanges.setUserId(cardOrders.getUserId());
		moneyChanges.setCardKindsId(cardOrders.getCardKindsId());
		moneyChanges.setTransactionDate("3");
		moneyChanges.setTransactionDate(formatter.format(new Date()));
		moneyChanges.setBeforeBalance(kUser.getBalance());
		balance = kUser.getBalance() + changeMoney;
		moneyChanges.setAfterBalance(balance);
		moneyChanges.setMoney(changeMoney);
		log.info("用户退卡记录：" + moneyChanges.getUserId() + "用户名" + moneyChanges.getUserId() + "金额"
				+ moneyChanges.getMoney() + "产品id" + moneyChanges.getCardKindsId() + "交易类型"
				+ moneyChanges.getTransactionType() + "交易时间" + moneyChanges.getTransactionDate() + "交易前金额"
				+ moneyChanges.getBeforeBalance() + "余款" + moneyChanges.getAfterBalance());

		kUser.setBalance(balance);
		log.info("扣除用户余额" + balance);
		try {
			getCardOrdersMapper().addCardOrders(cardOrders);
			getkUserMoneyChangesMapper().addCard(moneyChanges);
			getkUserMapper().updateUser(kUser);
			map.put("success","退卡成功");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.gc();
		return map;
	}

}
