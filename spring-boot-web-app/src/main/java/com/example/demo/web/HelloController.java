package com.example.demo.web;

import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

	@GetMapping("/hello")
	public Map<String, Object> hello(@RequestParam(defaultValue = "World") String name) {
		return Map.of(
				"message", "Hello, " + name + "!",
				"timestamp", Instant.now().toString());
	}

	@GetMapping("/health-info")
	public Map<String, String> healthInfo() {
		return Map.of(
				"status", "UP",
				"application", "spring-boot-web-app");
	}
}
