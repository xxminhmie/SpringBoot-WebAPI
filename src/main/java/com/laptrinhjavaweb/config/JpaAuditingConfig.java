package com.laptrinhjavaweb.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
	
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareIml();
	}
	
	public static class AuditorAwareIml implements AuditorAware<String>{
        @Override 
		public Optional<String> getCurrentAuditor(){
        	//flag
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	if(authentication == null || !authentication.isAuthenticated()) {
        		return Optional.of("Nobody");
        	}
        	return Optional.of(authentication.getName());
        	
        	
//        	return Optional.of("ABC");
//			//flag
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			//isAuthenticated() means whether user has signed in 
//			if(authentication == null || !authentication.isAuthenticated()) {
//				return null;
//			}
////			return Optional.ofNullable(Optional.of(authentication.getName()).orElse("uuu"));
////			return Optional.of("Atta");
////			return Optional.of(authentication.getName());
//	        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

		}
	}
}
