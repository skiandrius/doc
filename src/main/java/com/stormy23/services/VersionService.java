package com.stormy23.services;

import com.stormy23.model.Version;
import com.stormy23.repositories.VersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {
    private final VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public List<Version> list() {
        return versionRepository.findAll();
    }

    public Version getByID(Long id) {
        return versionRepository.findById(id).orElse(null);
    }

    public void create(Version version) {
        versionRepository.save(version);
    }
}

