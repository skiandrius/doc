package com.stormy23.doc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UploadDocumentController {

    @GetMapping(value = {"/upload/document", "/upload/document/", "/upload/document/{id}"})
    public String greeting(@PathVariable(required = false) String id, Model model) {
        model.addAttribute("name", id);
        return "greeting";
    }

}