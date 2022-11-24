package com.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entity.Answersheet;
import com.api.helper.Helper;
import com.api.repo.excelRepository;

@Service
public class ExcelService {

	@Autowired
	private excelRepository repository;

	public void save(MultipartFile file) {

		try {
			List<Answersheet> products = Helper.convertExcelToListOfProduct(file.getInputStream());
			this.repository.saveAll(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Answersheet> getAllProducts() {
		return (List<Answersheet>) this.repository.findAll();
	}
}
