package com.stormy23.doc;

import com.stormy23.model.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UploadDocumentController {

    @GetMapping(value = {"/upload/document", "/upload/document/"})
    public String uploadDocumentForm(Model model) {
        return "upload-doc";
    }

    @PostMapping(value = {"/upload/document", "/upload/document/"})
    public String uploadDocumentSubmit(@ModelAttribute Document document) {
        return "redirect:/document/";
    }

}