package com.foureverhh.security.distributed.uaa.config;

import javafx.scene.layout.BorderImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore);
        services.setClientDetailsService(clientDetailsService);
        services.setReuseRefreshToken(true);
        services.setAccessTokenValiditySeconds(7200);
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }



    //config client authorization
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("c1") //set client_id
                .secret(new BCryptPasswordEncoder().encode("secret")) //set client_secret
                .resourceIds("res1") //authorized accessible resources
                //5 Oauth2 supported authorization methods
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
                //client authorization read or write or all
                .scopes("all")
                .autoApprove("false") //false will redirect to authorize page
                .redirectUris("http://www.baidu.com"); //call back url
    }


    //handle different authentication methods
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenServices)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")   // /oauth/token_key is accessible to all
                .checkTokenAccess("permitAll()")  //
                .allowFormAuthenticationForClients();
    }

}
