package com.stormy23.services;

import com.stormy23.model.Document;
import com.stormy23.model.Version;
import com.stormy23.repositories.VersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VersionService {
    private final VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    /*public Version store(MultipartFile file, Document document) throws IOException {
        Version version = new Version(document, file.getBytes());

        return versionRepository.save(version);
    }

    public Version getFile(String id) {
        return versionRepository.findById(id).get();
    }*/

    public List<Version> list(Document document) {
        return versionRepository.findAllByDocument(document);
    }

    public Version getByID(Long id) {
        return versionRepository.findById(id).orElse(null);
    }

    public Version create(Version version) {
        return versionRepository.save(version);
    }
}

