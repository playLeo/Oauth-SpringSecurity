package com.santas.OauthSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll() // login URL에는 누구나 접근 가능하게 합니다.
                .anyRequest().authenticated() // 그 이외에는 인증된 사용자만 접근 가능하게 합니다.
                .and()
                .oauth2Login() // oauth2Login 설정 시작
                .defaultSuccessUrl("/home")
                .userInfoEndpoint() // oauth2Login 성공 이후의 설정을 시작
                .userService(customOAuth2UserService); // customOAuth2UserService에서 처리하겠다.
    }
}
