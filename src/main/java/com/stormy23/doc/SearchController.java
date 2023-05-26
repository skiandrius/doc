package com.stormy23.doc;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.model.SearchForm;
import com.stormy23.services.CardService;
import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Controller
public class SearchController {
    public static boolean isPureAscii(String v) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(v);
    }
    private final DocumentService docservice;

    private final VersionService verservice;

    private final CardService cardservice;

    public SearchController(DocumentService docservice, VersionService verservice, CardService cardservice) {
        this.docservice = docservice;
        this.verservice = verservice;
        this.cardservice = cardservice;
    }
    @GetMapping(value = {"/search", "/search/"})
    public String removeDocumentForm(Model model) {
        model.addAttribute("form", new SearchForm());
        return "search";
    }

    @PostMapping(value = {"/search", "/search/"})
    public String removeDocumentSubmit(@RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date dateintro, @ModelAttribute SearchForm form, Model model) {
        if (!isPureAscii(form.getDocname()) || !isPureAscii(form.getIntronumber())) {
            return "406";
        }

        List<Document> documents = docservice.getDocumentsByName(form.getDocname());
        Document result;
        for (Document document : documents) {
            Card card = cardservice.get(document);
            System.out.println("looking");
            if (Objects.equals(card.getIntronumber(), form.getIntronumber()) && dateintro.equals(card.getDateintro())) {
                System.out.println("found");
                return "redirect:/document/" + document.getId();
            }
        }

        return "404";
    }
}