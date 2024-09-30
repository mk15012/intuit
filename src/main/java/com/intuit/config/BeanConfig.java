package com.intuit.config;

import com.intuit.strategy.WinnerSelectionStrategy;
import com.intuit.strategy.impl.FirstComeFirstServedStrategy;
import com.intuit.strategy.impl.RandomSelectionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public WinnerSelectionStrategy firstComeFirstServedStrategy() {
        return new FirstComeFirstServedStrategy();
    }

    @Bean
    public WinnerSelectionStrategy randomSelectionStrategy() {
        return new RandomSelectionStrategy();
    }
}