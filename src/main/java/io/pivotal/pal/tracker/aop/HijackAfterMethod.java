package io.pivotal.pal.tracker.aop;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class HijackAfterMethod {
    private static Logger log = Logger.getLogger(HijackAfterMethod.class.getName());

    private Counter actionCounter;

    public HijackAfterMethod(MeterRegistry meterRegistry) {
        this.actionCounter = meterRegistry.counter("time-entries", "type", "fromAspect");
    }

    @Before("execution(* io.pivotal.pal.tracker.controllers.*Controller.cre*(..))")
    public void incrementCounter(JoinPoint joinPoint) {
        actionCounter.increment();
        log.info("incrementCounter() is running!");

    }
}
