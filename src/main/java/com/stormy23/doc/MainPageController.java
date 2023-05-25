package com.stormy23.doc;

import com.stormy23.model.Document;
import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainPageController {
    private final DocumentService docservice;

    private final VersionService verservice;

    public MainPageController(DocumentService docservice, VersionService verservice) {
        this.docservice = docservice;
        this.verservice = verservice;
    }
    @GetMapping("/")
    public String greeting(Model model) {
        List<Document> documents = docservice.list();
        model.addAttribute("documents", documents);
        return "index";
    }

}