package com.tedu.jt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**ServletContext��������������*/
public class MyServletListener implements ServletContextListener{

	@Override/*��ServletContext����󴴽�ʱִ��*/
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		//��ȡ��ǰwebӦ�õ�����·��
		String contextPath = context.getContextPath();
		//����ServletContext����
		context.setAttribute("app", contextPath);
		System.out.println("app��ʼ�����");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
