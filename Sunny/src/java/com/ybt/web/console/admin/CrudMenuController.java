package com.ybt.web.console.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.model.admin.Menu;
import com.ybt.service.admin.MenuService;


/**
 * 菜单管理处理类，菜单的增 删 改 查
 * 
 */
@Controller
@RequestMapping(value = "/console/admin/menu")
public class CrudMenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping
	public String defaultView(Model model,HttpServletRequest request){
		return "console/admin/menu/menuList";
	}
	
	//获取菜单json
	@RequestMapping(value = "/jsonMenu")
	@ResponseBody
	public List<Map<String,String>> jsonMenu(Model model,HttpServletRequest request){
		List<Menu> list = menuService.findAll();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		for(Menu menu:list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id",menu.getMenuId());
			map.put("pId",menu.getParentId());
			map.put("name",menu.getName());
			if(1==menu.getIsdisplay()){
				map.put("name",menu.getName()+"(不显示)");
			}
			
			if("-1".equals(menu.getParentId())){
				map.put("open","true");
			}
			mapList.add(map);
		}
		return mapList;
	}
	
	//保存菜单
	@RequestMapping(value = "/jsonSaveMenu",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String jsonSaveMenu(Menu menu,HttpServletRequest request){
		Boolean msg = menuService.saveMenu(menu);
		if(msg)
			return "操作成功";
		return "操作失败";
	}
	
	//获取一个菜单
	@RequestMapping(value = "/jsonGetMenu")
	@ResponseBody
	public Menu jsonGetMenu(HttpServletRequest request){
		String menuId = request.getParameter("menuId");
		Menu menu = menuService.findOne(menuId);
		if(menu!=null){
			Menu pMenu = menuService.findOne(menu.getParentId());	
			if(pMenu!=null){
				menu.setParentName(pMenu.getName());
			}
		}
		return menu;
	}
	

	//删除菜单
	@RequestMapping(value = "/jsonDelMenu")
	@ResponseBody
	public boolean jsonDelMenu(HttpServletRequest request){
		String menuId = request.getParameter("menuId");
		Boolean flag = menuService.delMenu(menuId);
		if(flag==true){
			return true;
		}else
			return false;
	}
	
}
