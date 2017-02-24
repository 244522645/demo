package com.ybt.web.console.zy;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ybt.common.util.DateUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunCard;
import com.ybt.service.work.SunCardService;



/**
 * 用户管理处理类，用户的增 删 改 查
 * 
 */
@Controller
@RequestMapping(value = "/console/zy/suncard")
public class SunCardController {
	
	
	@Autowired
	SunCardService sunCardService;
	
	private String baseView() {
		return "/console/zy/";
	}
	
	/**
	 *@name    显示阳光卡列表
	 *@description 相关说明
	 *@time    创建时间:2016年6月16日上午11:15:11
	 *@param model
	 *@param pageNumber
	 *@param request
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String photoView(Model model,@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<SunCard> pageUtil = new com.ybt.common.util.Page<SunCard>();
		pageUtil.setPageNo(pageNumber);
		Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
		Page<SunCard> page = null ;
		try{
			 page = sunCardService.findSunCardByProperty(filters, pager);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		pageUtil.setTotalCount(page.getTotalElements());
		pageUtil.setResult(page.getContent());
		model.addAttribute("page", pageUtil);
		return baseView() +"suncardList";
	}
	/*//跳转到用户修改和查看页面
		@RequestMapping(value = "/edit/{cardId:.*}")
		public String editVile(@PathVariable("cardId") String cardId,Model model,HttpServletRequest request){
			SunCard sc = sunCardService.getOneCard(cardId);
			model.addAttribute("sc",sc);
			return baseView() +"sunCardEdit";
		}
		*/
		
	//批量禁用阳光卡
		@RequestMapping(method = RequestMethod.POST,value = "/batchDel")
		public String batchDel(Model model,HttpServletRequest request){
			String[] cardIds = request.getParameterValues("cardIds");
			
			if(cardIds!=null&&cardIds.length!=0)
				for(int i=0;i<cardIds.length;i++){
					sunCardService.delCard(cardIds[i]);
				}
			
			return "redirect:/console/zy/suncard";
		}
		

		

		/**
		 * 跳转到编辑 页面
		 * */
		@RequestMapping(value = "/jump")
		public String jump(HttpServletRequest request, Model model) {
			String cardId = request.getParameter("cardId");
			String page = request.getParameter("page");
			model.addAttribute("page",page);
			if (cardId != null && !cardId.equals("")) {
				SunCard ss = sunCardService.getOneCard(cardId);
				model.addAttribute("card",ss);

			}
			return baseView() +"sunCardEdit";
		}
		
	//提交
		@RequestMapping(value = "/put", method = RequestMethod.POST)	
		public String add(HttpServletRequest request,Model model, RedirectAttributes attr,
				@RequestParam(value = "number",required=false) String number,
				@RequestParam(value = "failureTime", required=true) String failureTime,
				@RequestParam(value = "type",required=false) String type,
				@RequestParam(value = "userId",required=false) String userId,
				@RequestParam(value = "status",required=false) Integer status,
				@RequestParam(value = "bindingTime", required=false) String bindingTime) {
			
			
			attr.addFlashAttribute("msg", false);  
			String id = request.getParameter("id");
			SunCard s = null;
			
			
			if(id != null && !"".equals(id)){
				s=  sunCardService.getOneCard(id);
				//为空时增加
				if(s!=null){
					s.setFailureTime(DateUtil.StringToDate(failureTime,"yyyy-MM-dd"));
					
					sunCardService.save(s);
				}
			}
			
			//id不为空才修改
			
			
			String page = request.getParameter("page");
			return "redirect:/console/zy/suncard?page="+page;
		}
		
		
	
}
