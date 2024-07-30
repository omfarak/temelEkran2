package adminNew.temelEkran.service;


import adminNew.temelEkran.entity.File;
import adminNew.temelEkran.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fRepo;

    private final String folder = "/Users/rak/Desktop/test/files/";



    public String uploadFileToSystem(MultipartFile file) throws IOException {
        String filePath = folder + file.getOriginalFilename();

        java.io.File fileToSave = new java.io.File(filePath);
        file.transferTo(fileToSave);

        File f = fRepo.save(File.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build());

        if (f != null) {
            return filePath;
        } else {
            throw new IOException("Failed to save file record in database.");
        }
    }

    public byte[] downloadFileFromFileSystem(String fileName) throws IOException {
        Optional<File> fileData = fRepo.findTopByName(fileName);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilePath();
            return Files.readAllBytes(new java.io.File(filePath).toPath());
        } else {
            throw new IOException("File not found in the database.");
        }
    }



}

