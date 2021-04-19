package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dto.MessageDto;
import com.example.demo.service.MailService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	private MailService mailService;

	@PostMapping("/sendMessage")
	public boolean sendMessage(@RequestBody MessageDto messageDto) {

		return mailService.sendMail(messageDto);
	}
}
