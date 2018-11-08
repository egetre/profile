package com.tedu.jt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**ServletContext域对象的生命周期*/
public class MyServletListener implements ServletContextListener{

	@Override/*在ServletContext域对象创建时执行*/
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		//获取当前web应用的虚拟路径
		String contextPath = context.getContextPath();
		//存入ServletContext域中
		context.setAttribute("app", contextPath);
		System.out.println("app初始化完成");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
