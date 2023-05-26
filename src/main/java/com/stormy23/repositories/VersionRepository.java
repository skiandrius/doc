package com.stormy23.repositories;

import com.stormy23.model.Document;
import com.stormy23.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    List<Version> findAllByDocument(Document document);
}
