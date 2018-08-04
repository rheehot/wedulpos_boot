package com.wedul.common.config;

import com.wedul.common.interceptor.AlwaysInterceptor;
import com.wedul.common.util.AES256Cipher;
import com.wedul.wedulpos.variables.service.VariablesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Properties;

/**
 * 서블렛 관련 Config
 *
 * @author jeongcheol
 * @date 2017. 6. 17.
 */
@Configuration
@EnableWebMvc // <annotation-driven /> // WebMvcConfiguration에서 구성한 스프링 MVC 구성을 불러올 수 있다.
@ComponentScan(basePackages = {"com.wedul.*", "com.wedul.common.interceptor" })
public class ServletContextConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/**");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/dist/**").addResourceLocations("/dist/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Always Interceptor
		registry.addInterceptor(alwaysInterceptor()).addPathPatterns("/**").excludePathPatterns("/weather");
	}
	
	/**
	 * 모든 페이지 인터셉터
	 *
	 * @date 2017. 7. 1.
	 * @author wedul
	 * @return AlwaysInterceptor
	 */
	@Bean
	public AlwaysInterceptor alwaysInterceptor() {
		AlwaysInterceptor interceptor = new AlwaysInterceptor();
		return interceptor;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		return converter;
	}
	
	@Component
	@ConfigurationProperties(prefix = "manager")
	public static class ManagerProperties {
		private String emailHost;
		private int emailPort;
		private String emailId;
		private String emailPassword;
		private String emailProtocol;

		public String getEmailHost() {
			return emailHost;
		}

		public void setEmailHost(String emailHost) {
			this.emailHost = emailHost;
		}

		public int getEmailPort() {
			return emailPort;
		}

		public void setEmailPort(int emailPort) {
			this.emailPort = emailPort;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getEmailPassword() {
			return emailPassword;
		}

		public void setEmailPassword(String emailPassword) {
			this.emailPassword = emailPassword;
		}

		public String getEmailProtocol() {
			return emailProtocol;
		}

		public void setEmailProtocol(String emailProtocol) {
			this.emailProtocol = emailProtocol;
		}

	}
	
	@Autowired
	ManagerProperties props;

	/**
	 * Mail 설정
	 * 
	 * @return
	 */
	@Bean
	public JavaMailSender getJavaMailSender(VariablesServiceImpl config) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(props.getEmailHost());
		mailSender.setPort(props.getEmailPort());

		mailSender.setUsername(props.getEmailId());
		mailSender.setPassword(AES256Cipher.getInstance().AES_Decode(props.getEmailPassword()));

		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", props.getEmailProtocol());
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");

		return mailSender;
	}
}
