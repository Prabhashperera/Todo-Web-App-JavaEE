package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp.BasicDataSource;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener - contextInitialized");
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUsername("root");
        bds.setPassword("1234");
        bds.setUrl("jdbc:mysql://localhost:3306/todoApp");
        bds.setInitialSize(50);
        bds.setMaxIdle(100);

        ServletContext context = sce.getServletContext();
        context.setAttribute("db", bds);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextListener - contextDestroyed");
    }
}
