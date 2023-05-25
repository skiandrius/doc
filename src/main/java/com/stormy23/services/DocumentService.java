package com.stormy23.services;

import com.stormy23.model.Document;
import com.stormy23.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> list() {
        return documentRepository.findAll();
    }

    public Document getByID(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    public void create(Document document) {
        documentRepository.save(document);
    }

    public void setExtern(Document document) {
        document.setExtern(true);
        documentRepository.save(document);
    }
}
