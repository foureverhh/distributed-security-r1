package com.foureverhh.security.distributed.order.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeServices authorizationCondServices;

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
    //Token config


    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService); //client info
        services.setTokenStore(tokenStore); //how to store token
        services.setSupportRefreshToken(true); //whether allow to refresh token
        services.setAccessTokenValiditySeconds(7200);    //token stays valid in 2 hours
        services.setRefreshTokenValiditySeconds(259200); //token refresh valid in 3 days
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
                .autoApprove("false")
                .redirectUris("http://www.baidu.com");
    }

    //Token authorizationServiceEndPoint
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)         //Authentication mode
                .authorizationCodeServices(authorizationCondServices) //Authorization mode
                .tokenServices(tokenServices())                       //Token service
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //Allow post to upload token
    }

    //Token access strategy
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")    // /oauth/token_key   public_key access to all
                .checkTokenAccess("permitAll()")  // /oauth/check_token access to all
                .allowFormAuthenticationForClients(); //allow form upload to access
    }

}
