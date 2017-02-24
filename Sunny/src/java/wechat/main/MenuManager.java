package wechat.main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wechat.api.MenuAPI;
import wechat.api.MessageAPI;
import wechat.bean.Menu;
import wechat.bean.MenuButtons;
import wechat.bean.MenuButtons.Button;
import wechat.util.JsonUtil;


/**
 * 菜单管理器类
 */
public class MenuManager {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private static MenuButtons getMenu() {
		MenuButtons menu=new MenuButtons();
		Button btn=new Button();
		btn.setName("一键救援");
		btn.setType("view");
		btn.setUrl("");
		//http://123.56.118.53/ybt/console/wechat/core/sendhelp
		//btn.setUrl("http://test.iqingu.com/ybt/console/wechat/rescue/help");
		
		Button btn2=new Button(); 
		btn2.setName("救援卡");
		btn2.setType(MessageAPI.EVENT_TYPE_CLICK);
		btn2.setKey("救援卡");
		Button btn3=new Button(); 
		btn3.setName("下载APP");
		btn3.setType("view");
		btn3.setUrl("http://www.yunbeitai.com/app/download.html");
		/*Button btn21=new Button();
		btn21.setName("最新活动");
		btn21.setType("view");
		btn21.setUrl("http://www.yunbeitai.com/activity/activity1/");
		Button btn22=new Button();
		btn22.setName("电子卡");
		btn22.setType("view");
		btn22.setUrl("http://www.yunbeitai.com/activity/activity1/");
		List<Button> subButton2=new ArrayList<Button>();
		subButton2.add(btn21);
		subButton2.add(btn22);
		btn2.setSub_button(subButton2);
		
		Button btn3=new Button();
		btn3.setName("云备胎");
		Button btn31=new Button();
		btn31.setName("关于我们");
		btn31.setType("view");
		btn31.setUrl("http://www.yunbeitai.com/aboutus/");
		Button btn32=new Button();
		btn32.setName("客户服务");
		btn32.setType("view");
		btn32.setUrl("http://www.yunbeitai.com/aboutus/");
		Button btn33=new Button();
		btn33.setName("我的车辆");
		btn33.setType(MessageAPI.EVENT_TYPE_CLICK);
		btn33.setKey(Wechat.HELP);
		//btn33.setUrl("http://www.yunbeitai.com/activity/activity1/");
		Button btn34=new Button();
		btn34.setName("商务合作");
		btn34.setType("view");
		btn34.setUrl("http://www.yunbeitai.com/activity/activity1/");
		Button btn35=new Button();
		btn35.setName("APP下载");
		btn35.setType("view");
		btn35.setUrl("http://www.yunbeitai.com/download/");
		List<Button> subButton3=new ArrayList<Button>();
		subButton3.add(btn31);
		subButton3.add(btn32);
		subButton3.add(btn33);
		subButton3.add(btn34);
		subButton3.add(btn35);
		btn3.setSub_button(subButton3);*/
		
		Button[] buttons={btn3};
		menu.setButton(buttons);
		return menu;
	}

	public static void main(String[] args) {
//		// 第三方用户唯一凭证
//		String appId = "wxa939ce6b64a4b7c6";
//		// 第三方用户唯一凭证密钥
//		String appSecret = "4e1d60eab61c19de6dc399899b81304e";
		//System.out.println(JsonUtil.toJSONString(getMenu()));
		// 调用接口获取凭证
//		
//		Token token = TokenAPI.token(Wechat.APPID, Wechat.AppSecret);
//			// 创建菜单
//			String access_token=token.getAccess_token();
		String	access_token="zP5Ft4hjkH756B8P1f7ztCQyKPKkuoOkOEJheGCdR6nPh3Lk1Kud3LHvCjTQRb5YRjM_LIw6NDGk0I0j62CJqLy-HJ0-6zgeXoIAVvQNY2k";
//			BaseResult result=MenuAPI.menuDelete(access_token);
//			BaseResult result = MenuAPI.menuCreate(access_token, getMenu());
//			System.out.println(result.getErrcode()+"/"+result.getErrmsg()+"/"+access_token);
			// 判断菜单创建结果
			/*if (result.getErrcode().equals("0"))
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
			*/
		    Menu menu=MenuAPI.menuGet(access_token);
			if(menu!=null){
				MenuButtons buttons=menu.getMenu();
				System.out.println(JsonUtil.toJSONString(buttons));
			}
		}
}
