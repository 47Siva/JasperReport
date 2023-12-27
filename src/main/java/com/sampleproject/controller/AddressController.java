package com.sampleproject.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sampleproject.entity.Address;
import com.sampleproject.service.AddressService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	
	@Autowired
	AddressService addressService ;

	@PostMapping("/pot")
	public Address create (@RequestBody Address address) {
		return addressService.store(address); 
	}
	
	@GetMapping("/get")
	public List<Address> getAll ()
	{
		return addressService.getall();
	}
	
	@GetMapping("/getreport/{report}")
	public String generatedReport(@PathVariable ("report") String report) throws FileNotFoundException,JRException{
		
		addressService.exportReport(report);
		
		return  "YOUR REPORT GENERATED 😁😍";

	}
	
//	@GetMapping("/generatepdf")
//	public ResponseEntity<byte[]> generatepdf() throws FileNotFoundException,JRException
//	{
//	JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(addressService.generateUserList());
//	JasperReport compileReport=JasperCompileManager.compileReport("E:/Projects/spring boot/spring-boot/src/main/resources/Reports/schools.jrxml");
//	//asperCompileManager.compileReport(new String("src/main/sc.xml"));
//	HashMap<String,Object> map1=new HashMap<>();
//	JasperPrint report=JasperFillManager.fillReport(compileReport,map1,beanCollectionDataSource);
//	byte[] data=JasperExportManager.exportReportToPdf(report);
//	HttpHeaders headers=new HttpHeaders();
//	//headers.setContentType(MediaType.APPLICATION_PDF);
//	//headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=sc.pdf");
//	//return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
//	headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
//	headers.add("Content-Disposition", "inline; filename=generated.pdf");
//
//	return ResponseEntity.ok()
//	        .headers(headers)
//	        .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
//	        .body(data);
//	}
	
	@GetMapping(value = "getListReport" )
	public ResponseEntity<byte [] > generateReport() throws FileNotFoundException, JRException{
		
		byte [] reports = addressService.generateReport();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
//		header.setContentDispositionFormData("attachment", "report.pdf");
		return new ResponseEntity<>(reports,header,HttpStatus.OK);
		
	}
	
	@GetMapping("getany/{key}")
	public List<Address> getany(@PathVariable ("key") Object key){
		return addressService.getAny(key);
	}
	
	@GetMapping("getBykey/{key}")
	public ResponseEntity<byte [] >getBykey(@PathVariable ("key") Object key) throws FileNotFoundException, JRException {
		byte [] reports = addressService.getBykey(key);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		return new ResponseEntity<>(reports,header,HttpStatus.OK);
	}
	
}
