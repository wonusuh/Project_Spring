package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.zerock.service.HelloService;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Controller
@RequiredArgsConstructor
@ToString
public class HelloController {
	private final HelloService helloService;
}
