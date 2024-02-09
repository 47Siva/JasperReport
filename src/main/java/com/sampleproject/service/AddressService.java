package com.sampleproject.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.sampleproject.entity.Address;
import com.sampleproject.repository.AddressRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AddressService {

	@Autowired
	public AddressRepository addressRepository;

	public Address store(Address address) {
		return addressRepository.save(address);
	}

	public List<Address> getall() {
		return addressRepository.findAll();
	}

	// get report in chrome
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

		List<Address> address = addressRepository.findAll();
		
		//download path
		String path = "C:\\Users\\VC\\Desktop\\ReportsAndSave\\report";
		
		//upload path
		String filepath = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\springboot-jasper-report\\src\\main\\resources\\Report\\Address.jrxml";

		// load filepath and compile it
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		// maping jasper report and find all
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createdBy", "😎");

		// print jasper report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);

		// Html format
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\address.html");
		}
		// pdf format
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\address.pdf");
		}

		//
		return "report generatedin in path : " + path;
	}

//	public Collection<?> generateUserList() {
//		// TODO Auto-generated method stub
//		return addressRepository.findAll();
//	}

	// get report in post man
	public byte[] generateReport() throws JRException, FileNotFoundException {

		List<Address> address = addressRepository.findAll();
		String filepath = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\springboot-jasper-report\\src\\main\\resources\\Report\\Address.jrxml";

		// load file and compile it
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		// maping jasper report and find all
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createdBy", "😎");

		// print jasper report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public List<Address> getAny(Object key) {
		return addressRepository.findBykey(key);
	}

	
	//get the report in post man in felter method
	public byte[] getBykey(Object key) throws FileNotFoundException, JRException {
		
		List<Address> address = addressRepository.findBykey(key);
		String filepath = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\springboot-jasper-report\\src\\main\\resources\\Report\\Address.jrxml";

		// load file and compile it
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		// maping jasper report and find all
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createdBy", "😎");

		// print jasper report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	//get the report in chrome man in felter method 
	public String getBykeyFormat(Object key, String reportFormat) throws FileNotFoundException, JRException {
		List<Address> address = addressRepository.findBykey(key);
		String path = "C:\\Users\\VC\\Desktop\\newReport";
		String filepath = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\springboot-jasper-report\\src\\main\\resources\\Report\\Address.jrxml";

		// load file and compile it
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		// maping jasper report and find all
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createdBy", "😎");

		// print jasper report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);

		// Html format
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\address.html");
		}
		// pdf format
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\address.pdf");
		}

		//
		return "report generatedin in path : " + path;
		
	}

}
