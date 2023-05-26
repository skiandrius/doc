package com.stormy23.doc;

import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SearchController {

    private final DocumentService docservice;

    private final VersionService verservice;

    public SearchController(DocumentService docservice, VersionService verservice) {
        this.docservice = docservice;
        this.verservice = verservice;
    }

    @GetMapping("/search")
    public String greeting() {
        return "greeting";
    }

}