package com.capgemini.employeeapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServletConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
        //creating an object of container (WebApplicationContext)
		AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext(); //spring container obj creation
		//manages life cycle of beans
		
		
		
		//Specifying SpringMvcConfiguration class
		context.register(SpringMvcConfig.class);//specifying  what is the class that is alternative to xml file
		context.setServletContext(servletContext);//setting servletContext object for the container
		
		//configuring DispatcherServlet
		ServletRegistration.Dynamic dispatcher=servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		
		dispatcher.setLoadOnStartup(1);//obj of the above dispatcher servlet will be created
		dispatcher.addMapping("/");
		
	}

}
