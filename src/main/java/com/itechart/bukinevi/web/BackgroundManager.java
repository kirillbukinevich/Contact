package com.itechart.bukinevi.web;

import com.itechart.bukinevi.logic.commands.emailcommand.BirthdayNotice;
import com.itechart.bukinevi.logic.configuration.ConfigurationManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by aefrd on 26.09.2016.
 */
@WebListener
public class BackgroundManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConfigurationManager.initProperty(event.getServletContext());

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new BirthdayNotice(), 0, 1, TimeUnit.DAYS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

}