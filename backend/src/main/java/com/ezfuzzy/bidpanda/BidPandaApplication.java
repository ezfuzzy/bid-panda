package com.ezfuzzy.bidpanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@PropertySource(value = "classpath:custom.properties")
@SpringBootApplication
//@EnableScheduling
@EnableJpaAuditing
public class BidPandaApplication {

	public static void main(String[] args) { SpringApplication.run(BidPandaApplication.class, args); }

	@EventListener(ApplicationReadyEvent.class)
	public void init() throws Exception {
		System.out.println("\n### init ###\n");

		System.out.println("### ### ### ### ### ### ### ### ### ###");
		System.out.println("#                             		  #");
		System.out.println("#    서버가 성공적으로 실행되었습니다.   	  #");
		System.out.println("#                             		  #");
		System.out.println("### ### ### ### ### ### ### ###	###	###");
	}
}
