package com.laptrinhjavaweb.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.entity.NewsEntity;
import com.laptrinhjavaweb.exporter.NewsPDFExporter;
import com.laptrinhjavaweb.service.INewsService;

//@Controller
@CrossOrigin
@RestController

public class NewAPI {
	//@RequestBody
	//@RequestMapping(value = "/new", method = RequestMethod.POST)
	
	@Autowired
	private INewsService newsService;
	
	@PostMapping(value = "/new")
	public NewsDTO createNew(@RequestBody NewsDTO model) {
		return newsService.save(model);
	}
	
	@PutMapping(value ="/new/{id}")
	public NewsDTO updateNew(@RequestBody NewsDTO model, @PathVariable("id") long id) {
		model.setId(id);
		
		return newsService.save(model);
	}
	
	@DeleteMapping(value ="/new")
	public void deleteNew(@RequestBody long[] ids) {
		newsService.delete(ids);
	}
	//http://localhost:8081/new.pdf
	@RequestMapping(value = "/new/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> export() throws IOException {

        List<NewsEntity> newsEntity = (List<NewsEntity>) newsService.findAll();

        ByteArrayInputStream bis = NewsPDFExporter.export(newsEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=bill.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	

}
