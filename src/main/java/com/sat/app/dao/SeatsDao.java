package com.sat.app.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class SeatsDao {

	public String getAllSeats() {
		// Resource resource = resourceLoader.getResource("file:E:/Seats/seats.json");
		Resource resource = new ClassPathResource("seats.json");
		InputStream inputStream;
		byte[] fileData = null;
		try {
			inputStream = resource.getInputStream();
			fileData = FileCopyUtils.copyToByteArray(inputStream);

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		String outputString = new String(fileData);
		System.out.println(outputString);
		return outputString;
	}

	public void updateAllocations(String json) {
		// Creating an instance of file
		Path path = Paths.get("E:\\Seats\\seats.json");
		// Path path = Paths.get("resources/seats.json");
		// File file = new File("resources/seats.json");

		try {

			Files.writeString(path, json, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			System.out.print("Invalid Path");
		}
	}

	public String getAllocations() {
		 File file = new File("E:\\Seats\\seats.json");
		//File file = new File("seats.json");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br.lines().collect(Collectors.joining());
	}

}

/*
 * public static <T> T json2Java(String fileName, Class<T> classType) {
 * 
 * T t = null; File file = new File("src/main/resources/" + fileName);
 * 
 * try { ObjectMapper mapper = new ObjectMapper();
 * mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); t =
 * mapper.readValue(file, classType); } catch (Exception e) {
 * e.printStackTrace(); }
 * 
 * return t; }
 */
