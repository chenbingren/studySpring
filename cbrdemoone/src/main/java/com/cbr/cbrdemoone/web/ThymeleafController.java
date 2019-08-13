package com.cbr.cbrdemoone.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 使用Thymeleaf时，不能使用 @RestController 只能使用 @Controller ，否则会导致Thymeleaf无法跳转到页面，而返回了json
 */
@Slf4j
@Controller
public class ThymeleafController {
	
    @RequestMapping("/thymeleaf")
	public String thymeleaf(Locale locale, Model model) {

    	log.info("--------------------Thymeleaf------------------------");

		model.addAttribute("greeting", "Hello!");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);        
		String formattedDate = dateFormat.format(date);
		model.addAttribute("currentTime", formattedDate);

		return "hello";
	}

}