package com.ybt.common.interceptor;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.ybt.common.constant.Wechat;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.IWechatService;

public class AuthorizedInterceptor implements HandlerInterceptor {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	@Autowired
	public IWechatService wechatService;
  /** 
    * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 
    * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。 
    */ 
  public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3)
      throws Exception {
  }

   /** 
    * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之 
    * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操 
    * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像， 
    * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor 
    * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。 
    */  
  public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception {
  }

   /** 
    * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在 
    * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在 
    * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返 
    * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。 
    */  
  public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
	// 开头添加
	  if (handler instanceof ResourceHttpRequestHandler){
		  System.out.println("ResourceHttpRequestHandler");
	      return true;}
	  if(handler instanceof DefaultServletHttpRequestHandler){
		  System.out.println("DefaultServletHttpRequestHandler");
          return true;
      }
	  HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
	  String uri = request.getRequestURI();
	 request.setAttribute("config", constant);
    //登陆请求不拦截
      if(uri.indexOf("login") != -1){
      	return true;
      }
      
      if(uri.indexOf("wechat") != -1){
    	  	
    	  	if(uri.indexOf("wechat/core") != -1)
    	  		return true;
    	     //保存微信信息
    		SunWechatUser  w = wechatService.saveSNSSunWechatUser(request,true);
    	    //测试微信号----start-----------------------------
    		//SunWechatUser  w = wechatService.findById("oWL5RuJoTvOo2ZyHwLfafhE3B3-M");
    		 //测试微信号--end-------------------------------
    		HttpSession session;
    		session =request.getSession(true);
    		session.setAttribute("wxinfo", "false");
    		if(w==null  ){
    			session.setAttribute("wxlogin", "false");
    		}else if(w!=null && w.getSubscribe() == 2){
    			session.setAttribute(Wechat.WECATOPENID, w.getId() );
    			session.setAttribute("userInfo", w );
    			session.setAttribute("wxlogin", "true");
    			
    		}else{
    			session.setAttribute("wxlogin", "true");
    			if(w.getWechatNickname()!=null){
    				session.setAttribute("wxinfo", "true");
    			}
    			session.setAttribute("userInfo", w );
    			session.setAttribute(Wechat.WECATOPENID, w.getId()  );
    		}
    		
    		
    		 if (method.getAnnotation(Authorization.class) != null&&w==null) {
    			 request.getRequestDispatcher("/wechat/v2/loading").forward(request, response);
    		 }
    		
    		return true;
      }
     
    //设置不拦截的对象
      String[] noFilters = new String[] {"login","static","index","console"};  //对登录本身的页面以及业务不拦截,"forget","register","emailsuccess","checkemail","emailsuccess","checkreset","checkreset"
      boolean beFilter = true; 
      for (String s : noFilters) {  
        if (uri.indexOf(s) != -1) {  
           beFilter = false;  
           break;  
        }  
      }
    //设置特殊拦截的对象
      String[] userFilters = new String[] {"my","users"};  
      boolean userFilter = false; 
      boolean islogin=false;
      for (String s : userFilters) {  
        if (uri.indexOf(s) != -1) {  
        	userFilter = true;  
           break;  
        }  
      }
      
	  if (beFilter) {//除了不拦截的对象以外
	    
	  }
	  
	  //用户中心 设置
	  if((userFilter&&!islogin)&&beFilter){
		  request.getSession().setAttribute("errorInfo", "请登录！");
		  String path = request.getContextPath();
		  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		  System.out.println("拦截");
		  response.sendRedirect(basePath + "login");
	      return false;
	  }
	  
    return true;
  }

}
