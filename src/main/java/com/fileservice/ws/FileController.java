package com.fileservice.ws;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("files")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping("/upload")
	public ModelAndView directToFile(){
		System.out.println("get ne");
		return new ModelAndView("/home");
	}
	@CrossOrigin(origins = "*")
	@PostMapping
	public ResponseEntity<FileResponse> storeFilePost(@RequestParam("file") MultipartFile file,@RequestParam("userID") Long id){

		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());

		fileStorageService.storeFileToDatabase(fileResponse, id);
		return new ResponseEntity<FileResponse>(fileResponse,HttpStatus.OK);
	}
	@GetMapping("")
	public ResponseEntity<List<String>> getAllVideoUri(){
		List<String> list = fileStorageService.getAllVideoUri();
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		System.out.println("download");
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex) {
			System.out.println("Could not determine fileType");
		}
		
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	}
}
