package com.stormy23.doc;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.model.RemoveForm;
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
public class RemoveDocumentController {
    public static boolean isPureAscii(String v) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(v);
    }
    private final DocumentService docservice;

    private final VersionService verservice;

    private final CardService cardservice;

    public RemoveDocumentController(DocumentService docservice, VersionService verservice, CardService cardservice) {
        this.docservice = docservice;
        this.verservice = verservice;
        this.cardservice = cardservice;
    }
    @GetMapping(value = {"/remove", "/remove/", "/remove/{id}"})
    public String removeDocumentForm(@PathVariable(required = false) String id, Model model) {
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
        model.addAttribute("form", new RemoveForm());
        return "remove";
    }

    @PostMapping(value = {"/remove", "/remove/"})
    public String removeDocumentSubmit(@RequestParam("documentid") String id, @ModelAttribute RemoveForm form, Model model) {
        if (!isPureAscii(form.getExternnumber()) || !isPureAscii(form.getDocumentid())) {
            return "406";
        }
        long long_id;
        try {
            long_id = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "406";
        }
        Document document = docservice.getByID(long_id);

        if (document == null) {
            return "404";
        }

        if (document.getExtern()) {
            return "406";
        }

        Card card = cardservice.get(document);
        cardservice.extern(card, form.getExternnumber());
        docservice.setExtern(document);
        return "redirect:/document/" + document.getId();
    }
}