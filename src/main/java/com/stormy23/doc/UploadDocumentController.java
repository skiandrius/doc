package com.stormy23.doc;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.model.DocumentUploadForm;
import com.stormy23.model.Version;
import com.stormy23.services.CardService;
import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;

@Controller
public class UploadDocumentController {
    public static boolean isPureAscii(String v) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(v);
    }
    private final DocumentService docservice;

    private final VersionService verservice;

    private final CardService cardservice;

    public UploadDocumentController(DocumentService docservice, VersionService verservice, CardService cardservice) {
        this.docservice = docservice;
        this.verservice = verservice;
        this.cardservice = cardservice;
    }

    @GetMapping(value = {"/upload/document", "/upload/document/"})
    public String uploadDocumentForm(Model model) {
        model.addAttribute("form", new DocumentUploadForm());
        return "upload-doc";
    }

    @PostMapping(value = {"/upload/document", "/upload/document/"})
    public String uploadDocumentSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute DocumentUploadForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "406";
        }
        if (!isPureAscii(form.getAuthor()) || !isPureAscii(form.getName()) || !isPureAscii(form.getNumber())) {
            return "406";
        }
        Document document = new Document(form.getName(), form.getAuthor());
        String message = "";
        Version version;
        Card card;
        try {
            version = new Version(document, form.getAuthor(), file.getBytes(), 1);
            card = new Card(document, form.getNumber());
            document = docservice.create(document);
            verservice.create(version);
            cardservice.create(card);
        } catch (Exception e) {
            return "406";
        }

        if (document == null) {
            return "500";
        }

        return "redirect:/document/" + document.getId();
    }

}