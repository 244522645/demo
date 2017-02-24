package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.KUser;
import com.model.KUserRightModel;
import com.model.KUserRoles;
import com.service.KUserRolesService;
import com.util.EasyUiDataGridJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/KUserRoles")
public class KUserRolesController extends BaseController {
	

	@Autowired(required = false)
	private KUserRolesService kuserRolesService;

	/*
	 * 查询该用户的菜单
	 */
	@RequestMapping("/findbyUser")
	public void findbyUser(HttpServletRequest request, HttpServletResponse response) {
		KUser kUser = (KUser) request.getSession().getAttribute("username");
		if (kUser != null && !kUser.equals("")) {
			List<KUserRightModel> list = kuserRolesService.findbyUser(kUser.getRoleID());
			response.setContentType("text/json;charset=UTF-8");
			try {
				response.getWriter().print(JSONArray.fromObject(list).toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};

	/*
	 * 查询所有角色
	 * 
	 */
	@RequestMapping("/findRoleBy")

	public void findRoleBy(int page, int rows, HttpServletRequest request, HttpServletResponse response) {
		List<KUserRoles> role = kuserRolesService.findRoleBy(rows, (page - 1) * rows);
		int totle = kuserRolesService.countRole();
		EasyUiDataGridJson<KUserRoles> json = new EasyUiDataGridJson<KUserRoles>();
		json.setTotal(totle);
		json.setRows(role);

		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(JSONObject.fromObject(json).toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 添加角色
	 */
	@RequestMapping("/addRole")
	public void addRole(String roleKind, String roleName, String roleDescription, HttpServletResponse response) {
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		int i = 0;
		try {
			kuserRolesService.addRole(roleKind, roleName, roleDescription, dateString);
		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}
		try {

			response.getWriter().print(i);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 修改角色
	 */
	@RequestMapping("/updateRole")
	public void updateRole(String roleId,String roleKind, String roleName, String roleDescription, HttpServletResponse response) {
		int i = 0;
		try {
			kuserRolesService.updateRole(roleId, roleKind, roleName, roleDescription);
		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}
		try {

			response.getWriter().print(i);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 查询所有角色权限
	 */
	@RequestMapping("/findAllRights")
	public void findAllRights(String roleId,HttpServletResponse response){
		
			try {
				List<KUserRightModel> kUserRightModel=kuserRolesService.findAllRights();
				response.setContentType("text/json;charset=UTF-8");
				response.getWriter().print(JSONArray.fromObject(kUserRightModel).toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/*
	 * 查询当前角色下面的权限
	 * 
	 */
	@RequestMapping("/findRoleRights")
	public void findRoleRights(String roleId,HttpServletResponse response){
		
		try {
			List<String> roleIds=kuserRolesService.findUserRights(roleId);
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(roleIds).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 修改当前角色下面权限
	 */
	@RequestMapping("updateRoleRight")
	public void updateRoleRight(String rolerightid,String [] rightid,HttpServletResponse response){
		int c=0;
		try {
			List<String> roleIds=kuserRolesService.findUserRights(rolerightid);
			List<String>  addlist=new ArrayList<String>();
			List<String>  deletelist=new ArrayList<String>();
			deletelist.addAll(roleIds);
			if(rightid!=null&&!rightid.equals("")){
				for(int i=deletelist.size()-1;i>=0;i--){
					for(int j=0;j<rightid.length;j++){
						if(deletelist.get(i).equals(rightid[j])){
							deletelist.remove(i);
							break;
						}
					}
				}
				for(int i=0;i<roleIds.size();i++){
					for(int j=0;j<rightid.length;j++){
						if(roleIds.get(i).equals(rightid[j])){
							
							rightid[j]=null;
						}
					}
				}
				for(int i=0;i<rightid.length;i++){
					if(rightid[i]!=null&&!rightid[i].equalsIgnoreCase("")){
						
						addlist.add(rightid[i]);
					}
				}
				if(addlist!=null&&!addlist.equals("")){
					for(int i=0;i<addlist.size();i++){
						kuserRolesService.addrights(rolerightid, addlist.get(i));
					}
					
				}
				
				if(deletelist!=null&&!deletelist.equals("")){
					for(int i=0;i<deletelist.size();i++){
						kuserRolesService.deleterights(rolerightid, deletelist.get(i));
					}
				}
			}else{
				kuserRolesService.deleterights(rolerightid,null);
			}
		
		} catch (Exception e) {
			c=1;
			e.printStackTrace();
		}
		try {
			response.getWriter().print(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
