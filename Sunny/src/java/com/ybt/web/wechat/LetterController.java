package com.ybt.web.wechat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterBless;
import com.ybt.model.work.SunLetterCards;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyMusic;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.MusicService;
import com.ybt.service.work.SunLetterBlessService;
import com.ybt.service.work.SunLetterCardsService;
import com.ybt.service.work.SunLetterService;

/**
 *   信件 管理
 * */
@Controller
@RequestMapping(value = "/wechat/letter")
public class LetterController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(LetterController.class);
	
	@Autowired
	private SunLetterService letterService;
	@Autowired
	private MusicService musicService;
	@Autowired
	private SunLetterCardsService letterCardsService;
	@Autowired
	private SunLetterBlessService letterBlessService;
	@Autowired
	public IWechatService wechatService;
	
	
	private String baseView() {
		return "/work/wechat/letter";
	}
	
	/*
	 *信件 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		return baseView()+"/index";
	}
	
	/*
	 * 提交
	 */
	/**  
	 * @param model
	 * @param request
	 * @param letterId	邮件id
	 * @param sender	发件人姓名
	 * @param receiver	收件人姓名
	 * @param message  文本内容
	 * @param music		背景音乐id
	 * @param stamp	   邮票id
	 * @param orders[]   祝福 ids 数组
	 * @param cards[]    阳光卡 id 数组
	 * @param voiceId  语音id
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月25日 下午2:41:59 
	 */
	@RequestMapping(value ="put",method = RequestMethod.POST)
	@ResponseBody
	public Result<SunLetter> indexPut(Model model,HttpServletRequest request,
			@RequestParam(value = "letterId",defaultValue="") String letterId,
			@RequestParam(value = "sender") String sender,
			@RequestParam(value = "receiver") String receiver,
			@RequestParam(value = "music",defaultValue="") String music,
			@RequestParam(value = "stamp",defaultValue="") String stamp,
			@RequestParam(value = "message",defaultValue="") String message,
			@RequestParam(value = "orders[]",defaultValue="") String[] orders,
			@RequestParam(value = "cards[]",defaultValue="") String[] cards,
			@RequestParam(value = "voiceId",defaultValue="") String voiceId) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		return letterService.saveLetter( letterId,w.getId(), sender, receiver, music, stamp, message, orders,cards, voiceId);
	}
	
	/*
	 *信件查看
	 */
	@RequestMapping(value ="show")
	public String shou(Model model,HttpServletRequest request,
			@RequestParam(value="letterId" )String letterId) {
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		SunLetter letter = letterService.findOne(letterId);
		if(letter==null){
			return baseView()+"/show";
		}
		model.addAttribute("letter", letter);
		
		List<SunLetterCards> cards = null ;
		if(letter.getIscard()==1){
			cards = letterCardsService.findByLetterId(letterId);
			model.addAttribute("cards", cards);
		}
		List<SunLetterBless> blesss = null;
		if(letter.getIsorder()==1){
			blesss = letterBlessService.findByLetterId(letterId);
			model.addAttribute("blesss", blesss);
		}
		
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			//return baseView()+"/show";
	   	}
		
		//操作 收信人 获得
		letterService.pullLetter(letter, cards, blesss, w);
		
		if(w!=null&&w.getId().equals(letter.getUserId().getId())){
			request.setAttribute("author", "true");
		}
		
		return baseView()+"/show";
	}
	
	/*
	 *背景音乐列表
	 */
	@RequestMapping(method = RequestMethod.GET,value ="music")
	@ResponseBody
	public List<SunZyMusic> music(Model model,HttpServletRequest request) {
		return  musicService.findAll();
	}
	
	
	/**  
	 * 列表
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value="data")
	@ResponseBody
	public com.ybt.common.util.Page<SunLetter> data(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		
		com.ybt.common.util.Page<SunLetter> pageUtil1 = new com.ybt.common.util.Page<SunLetter>();
		pageUtil1.setPageNo(pageNumber);
		pageUtil1.setPageSize(pageSize);
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return pageUtil1;
	   	}
		
		letterService.getMyAllLetterList(w.getId(), pageUtil1);
		List<SunLetter> newlist = new ArrayList<SunLetter>();
		List<SunLetter> blist = pageUtil1.getResult();
		for (SunLetter letter : blist) {
			if(letter.getUserId().getId().equals(w.getId())){
				letter.setRole(0);
			}else{
				letter.setRole(1);
			};
			newlist.add(letter);
		}
		pageUtil1.setResult(newlist);
		return pageUtil1;
	}

	/**  
	 * 发送
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.POST,value="send")
	@ResponseBody
	public Result<SunLetter> send(Model model,HttpServletRequest request,
			@RequestParam(value = "letterId", defaultValue = "") String letterId) {
		if("".equals(letterId)){
			return new Result<SunLetter>("发送失败",null);
		}
		
		Result<SunLetter> res=letterService.sendLetter(letterId);
		
		return res;
	}
}