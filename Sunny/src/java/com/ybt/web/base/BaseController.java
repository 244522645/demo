package com.ybt.web.base;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.Page;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.ReflectUtils;
import com.ybt.common.util.Servlets;
import com.ybt.service.base.IBaseService;

public abstract class BaseController<T,ID extends Serializable> {
	
	public abstract IBaseService<T,ID> getBaseService();
	
	public abstract String baseView();

	/**
	 * 分页获取全部实体
	 * */
	@RequestMapping(value = "/pageList")
	public String pageList(HttpServletRequest request,Model model,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
																  @RequestParam(value = "order", defaultValue = "desc") String order,
																  @RequestParam(value = "sortType", defaultValue = "") String sortType){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		Page<T> page = new Page<T>();
		//判断是否有deleted字段，如果有加入到查询条件
		Type type = ReflectUtils.getClassGenricType(this.getClass()); 
	    Class<?> entityClass = (Class<?>) type;  
		if(ReflectUtils.equalsParam(entityClass, "deleted")){ 
			PropertyFilter pf = new PropertyFilter("EQS_deleted","0");
			filters.add(pf);
		}
		page.setPageNo(pageNumber);
		page.setOrder(order);
		model.addAttribute("order", order);
		model.addAttribute("sortType", sortType);
		page.setOrderBy(sortType);
		getBaseService().findAll(filters, page);
		model.addAttribute("page", page);
		return baseView()+"/pageList";
	}
	
	/**
	 * 删除实体
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * */
	@RequestMapping(value = "/batchDel",method = RequestMethod.POST)
	public String batchDel(@RequestParam(value = "id") ID[] ids,HttpServletRequest reques,@RequestParam(value = "view", defaultValue = "pageList") String view) throws InstantiationException, IllegalAccessException{
		for(int i=0;i<ids.length;i++){
			getBaseService().delete(ids[i]);
		}
		return "redirect:"+view;
	}
	
	/**
	 * 保存实体
	 * */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String batchDel(T t,HttpServletRequest reques) {
		getBaseService().save(t);
		return "redirect:pageList";
	}
	
	/**
	 * 获取实体 json 格式返回
	 * */
	@RequestMapping(value = "/getOneToJson")
	@ResponseBody
	public T getOneToJson(@RequestParam(value = "id") ID id,HttpServletRequest reques) {
		return getBaseService().findOne(id);
	}
	
	/**
	 * 获取全部实体 json 格式返回
	 * */
	@RequestMapping(value = "/getAllToJson")
	@ResponseBody
	public List<T> getAllToJson(HttpServletRequest reques) {
		return getBaseService().findAll();
	}
	
	/**
	 * 获取实体 返回指定页面
	 * */
	@RequestMapping(value = "/getOne")
	public String getOne(@RequestParam(value = "id") ID id,@RequestParam(value = "view", defaultValue = "add") String view,Model model) {
		T t = getBaseService().findOne(id);
		model.addAttribute("t",t);
		return baseView()+"/"+view;
	}
}
