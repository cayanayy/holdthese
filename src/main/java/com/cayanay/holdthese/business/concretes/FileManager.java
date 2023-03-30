package com.cayanay.holdthese.business.concretes;

import com.cayanay.holdthese.business.abstracts.FileService;
import com.cayanay.holdthese.dataaccess.FileRepository;
import com.cayanay.holdthese.entities.File;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileManager implements FileService {
    private final FileRepository fileRepository;

    public void createFile(File file) {
        fileRepository.save(file);
    }

}
