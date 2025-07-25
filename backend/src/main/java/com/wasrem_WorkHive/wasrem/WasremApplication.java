package com.wasrem_WorkHive.wasrem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

@PropertySource(value = "classpath:custom.properties")
@SpringBootApplication
//@EnableScheduling
public class WasremApplication {

	public static void main(String[] args) { SpringApplication.run(WasremApplication.class, args); }

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
