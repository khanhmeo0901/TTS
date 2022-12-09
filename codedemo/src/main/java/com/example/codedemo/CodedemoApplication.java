package com.example.codedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CodedemoApplication {

	public static void main(String[] args) {

		// ApplicationContext chính là container, chứa toàn bộ các Bean
		ApplicationContext context = SpringApplication.run(CodedemoApplication.class, args);

		// Khi chạy xong, lúc này context sẽ chứa các Bean có đánh
		// dấu @Component.

		// Lấy Bean ra bằng cách
		Outfit outfit = context.getBean(Outfit.class);

		// In ra để xem thử nó là gì
		System.out.println("Instance: " + outfit);
		// xài hàm wear()
		outfit.wear();
	}

}
