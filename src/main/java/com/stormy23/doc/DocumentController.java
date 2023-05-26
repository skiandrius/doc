package com.stormy23.doc;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.model.Version;
import com.stormy23.services.CardService;
import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DocumentController {
    private final DocumentService docservice;

    private final VersionService verservice;

    private final CardService cardservice;

    public DocumentController(DocumentService docservice, VersionService verservice, CardService cardservice) {
        this.docservice = docservice;
        this.verservice = verservice;
        this.cardservice = cardservice;
    }
    @GetMapping(value = {"/document", "/document/", "/document/{id}"})
    public String greeting(@PathVariable(required = false) String id, Model model) {
        if (id == null) {
            return "notfound";
        }

        long long_id;

        try {
            long_id = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "406";
        }

        Document document = docservice.getByID(long_id);

        if (document == null) {
            return "notfound";
        }

        List<Version> versions = verservice.list(document);
        Card card = cardservice.get(document);
        System.out.println(card);

        model.addAttribute("versions", versions);
        model.addAttribute("document", document);
        model.addAttribute("card", card);

        return "document";
    }

}