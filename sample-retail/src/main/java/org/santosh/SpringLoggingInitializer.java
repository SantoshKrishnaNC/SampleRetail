package org.santosh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringLoggingInitializer implements ApplicationContextInitializer {

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextInitializer#initialize(org.springframework.context.ConfigurableApplicationContext)
     * 
     * We need to turn off Spring logging since we want write the generated message to console. 
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Class[] loggers = {SpringApplication.class, /*App.class,*/ ConfigFileApplicationListener.class,
                ConditionEvaluationReportLoggingListener.class};
        Logger log = (Logger) LoggerFactory.getLogger("ROOT");
        log.atLevel(Level.ERROR);
        for (Class logger : loggers) {
            log = (Logger) LoggerFactory.getLogger(logger);
            log.atLevel(Level.ERROR);
        }
    }
}