package com.example.demo.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.FileDB;
import com.example.demo.models.Home;
import com.example.demo.repository.FileDBRepository;
import com.example.demo.repository.HomeRepository;

@Service
public class FileStorageService {
	@Autowired
	private FileDBRepository fileDBRepository;

	@Autowired
	HomeRepository homeRepository;

	public FileDB store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

		return fileDBRepository.save(FileDB);
	}

	public FileDB saveFileWithHome(Long homeId, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

		Home home = homeRepository.findById(homeId).get();
		fileDB.setHome(home);

		return fileDBRepository.save(fileDB);
	}

	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
	
	public List<FileDB> getAllImages() {
		return fileDBRepository.findAll();
	}

	public Optional<FileDB> findImageByname(@PathVariable("name") String name) {
		return fileDBRepository.findById(name);
	}
}
