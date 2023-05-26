package com.stormy23.doc;

import com.stormy23.model.*;
import com.stormy23.services.CardService;
import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.List;

@Controller
public class UploadVersionController {
    public static boolean isPureAscii(String v) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(v);
    }
    private final DocumentService docservice;

    private final VersionService verservice;

    private final CardService cardservice;

    public UploadVersionController(DocumentService docservice, VersionService verservice, CardService cardservice) {
        this.docservice = docservice;
        this.verservice = verservice;
        this.cardservice = cardservice;
    }
    @GetMapping(value = {"/upload/version", "/upload/version/", "/upload/version/{id}"})
    public String uploadVersionForm(@PathVariable(required = false) String id, Model model) {
        if (!isPureAscii(id)) {
            return "406";
        }
        long long_id;

        try {
            long_id = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "406";
        }
        Document document = docservice.getByID(long_id);

        if (document.getExtern()) {
            return "406";
        }

        model.addAttribute("document_id", id);
        model.addAttribute("form", new VersionUploadForm());
        return "upload-ver";
    }

    @PostMapping(value = {"/upload/version", "/upload/version/"})
    public String uploadVersionSubmit(@RequestParam("documentid") String id, @RequestParam("file") MultipartFile file, @ModelAttribute VersionUploadForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "406";
        }
        if (!isPureAscii(form.getAuthor()) || !isPureAscii(form.getDocumentid())) {
            return "406";
        }
        long docid = Long.parseLong(id);
        Document document = docservice.getByID(docid);
        List<Version> versionsList = verservice.list(document);
        long largest = 0;
        for (Version ver : versionsList) {
            if (ver.getNumber() > largest) {
                largest = ver.getNumber();
            }
        }
        Version version;
        Card card;
        try {
            version = new Version(document, form.getAuthor(), file.getBytes(), largest+1);
            verservice.create(version);
        } catch (Exception e) {
            return "406";
        }

        if (document == null) {
            return "500";
        }

        return "redirect:/document/" + document.getId();
    }
}