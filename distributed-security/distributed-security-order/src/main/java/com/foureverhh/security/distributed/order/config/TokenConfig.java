package com.foureverhh.security.distributed.order.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class TokenConfig {

    //token store strategy
    @Bean
    public TokenStore tokenStore(){
        //store in memory
        return new InMemoryTokenStore();
    }
}
