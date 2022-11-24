package com.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.entity.Answersheet;
import com.api.entity.Form;
import com.api.helper.Helper;
import com.api.service.ExcelService;

@Controller
@CrossOrigin("*")
public class ExcelController {
	@Autowired
	private ExcelService service;

	@GetMapping("/register")
	public String showForm(Model model) {
		Form form = new Form();
		model.addAttribute("form", form);

		List<String> practice = Arrays.asList("JAVA", ".NET", "TESTING");
		model.addAttribute("practice", practice);
		
		List<String> level =Arrays.asList("Beginner","Intermediate","Advanced");
		model.addAttribute("level",level);
		
		return "register_form";
	}

	@PostMapping("/register")
	public String submitForm(@ModelAttribute("form") Form form) {
		return "register_success";
	}
	
	
	@PostMapping("/sheet/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		if (Helper.checkExcelFormat(file)) {
			// true

			this.service.save(file);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}

	@GetMapping("/sheet")
	public List<Answersheet> getAllProduct() {
		return this.service.getAllProducts();
	}
}
