package com.clotheshop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class saveFileUtils {

	public static String saveFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// path location
		Path path = Paths.get("src/main/resources/static/web/assets/img");
		// save the file on the local file system
		try {
			Path filePath = path.resolve(fileName);
			// copy file
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.print(e);
		}
		return fileName;
	}
}
