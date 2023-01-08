package com.Hao.codeSharingProject.Config.Routes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Hao.codeSharingProject.CodeItem;
import com.Hao.codeSharingProject.Config.CodeRepository;
import com.google.gson.Gson;

@Controller
public class CodeController {
	@Autowired
	private CodeRepository<CodeItem, String> codeRepository;
	
	@Autowired
	private Gson gson;
	
	@PostMapping("/save")
	public ResponseEntity<UUID> Save(@RequestBody String code) {
		CodeItem codeItem = gson.fromJson(code, CodeItem.class);
		codeRepository.insert(codeItem);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("exclude\\UUIDSTORE.txt", true));
			writer.write(codeItem.getUUID().toString());
			writer.newLine();
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Code saved.");
		
		return ResponseEntity.ok(codeItem.getUUID());
	}
	
	@GetMapping("/view/{uuid}")
	public String ViewCode(@PathVariable String uuid, Model model) {
		UUID uuidMatch = UUID.fromString(uuid);
	
		CodeItem item = codeRepository.findByUUID(uuidMatch);
		
		if (Objects.isNull(item)) {
			return "redirect:/";
		}
		
		model.addAttribute("code", item.getCode());
		
		return "view";
	}
	
	@GetMapping("/edit/{uuid}")
	public String EditCode(@PathVariable String uuid, Model model) {
		UUID uuidMatch = UUID.fromString(uuid);
		CodeItem item = codeRepository.findByUUID(uuidMatch);

		model.addAttribute("code", item.getCode());
		
		return "index";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException() {
		return ResponseEntity.badRequest().body("Bad request");
	}
}
