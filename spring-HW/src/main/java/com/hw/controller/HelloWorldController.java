package com.hw.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hw.entity.ArticleEntity;
import com.hw.service.ArticleService;
import com.hw.service.FileStorageService;

@RestController
public class HelloWorldController {
	
	@Autowired
	ArticleService articeService;
	
	//@Autowired
    //FileStorageService fileStorageService;
	
	@RequestMapping("/hw")
	public String getMsg() {
		return "Hello World";
	}
	
	@GetMapping("/getArticles")
	public ResponseEntity<List<ArticleEntity>> getArtice(){
		List<ArticleEntity> articlesList = articeService.findArticles();
		articlesList.forEach(System.out::println);
		ResponseEntity response = new ResponseEntity(articlesList,HttpStatus.OK);
		return response;
	}

	
	@GetMapping(value = "/reportInTxt")
	public String txtReport() throws Exception {
		String filePath="C:\\Users\\venkatesh\\Desktop\\MyArticle.txt";
		articeService.articesToTextFile(filePath);
		return "Successfully downloaded...@<a>"+filePath+"</a>";
    }
	
	
	
	@GetMapping(value = "/generateAndDownloadReport")
	public ResponseEntity excelCustomersReport() throws IOException {
		ByteArrayInputStream in = articeService.articesToExcel();
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Eenadu_Article.xlsx");
		
		 return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(new InputStreamResource(in));
    }
	
	/*@GetMapping("/downloadFile")
    public ResponseEntity<UrlResource> downloadFile(HttpServletRequest request) throws Exception {
        // Load file as Resource
        UrlResource resource = fileStorageService.loadFileAsResource("MyArticle.txt");

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*/
	
}
