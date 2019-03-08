package com.niuparser.yazhiwebapi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
	/**
	 *  permitall没有绕过spring security，其中包含了登录的以及匿名的。
	 * @param http
	 * @throws Exception
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        //允许所有用户访问"/"和"/home"
//        http.authorizeRequests()
//                .antMatchers("/translate/**").permitAll()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();//就是这一行啦;
//                //其他地址的访问均需验证权限
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")  //指定登录页是"/login"
//                .defaultSuccessUrl("/list")  //登录成功后默认跳转到"list"
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/home")  //退出登录后的默认url是"/home"
//                .permitAll();
//    	http.authorizeRequests()
//    			.antMatchers("/swagger-resources/**","/index/**").permitAll();
    }

	/**
	 * ingore是完全绕过了spring security的所有filter，相当于不走spring security
    */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
    	//web.ignoring().antMatchers("/swagger-resources/**","/testApi/**","/translate/**","/beiJingShow/**","/cipolShow/**");
    	//web.ignoring().antMatchers("/swagger-resources/**","/nlpApi/**","/pyhtonApi/**");
    	//nlp演示只保留cipolShow
    	web.ignoring().antMatchers("/swagger-resources/**","/cipolShow/**","/voiceToText/**");
    }
}