package com.stormy23.doc;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.model.Version;
import com.stormy23.services.CardService;
import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

@Controller
public class VersionController {

    private final DocumentService docservice;

    private final VersionService verservice;

    private final CardService cardservice;

    public VersionController(DocumentService docservice, VersionService verservice, CardService cardservice) {
        this.docservice = docservice;
        this.verservice = verservice;
        this.cardservice = cardservice;
    }
    @RequestMapping (value = {"/version", "/version/", "/version/{id}"}, method = RequestMethod.GET)
    public ResponseEntity downloadVersion(HttpServletRequest request, HttpServletResponse response, @PathVariable(required = false) String id, Model model)  {
        if (id == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/error");
            return new ResponseEntity<String>(headers,HttpStatus.FOUND);
        }

        long long_id;

        try {
            long_id = Long.parseLong(id);
        } catch (NumberFormatException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/error");
            return new ResponseEntity<String>(headers,HttpStatus.FOUND);
        }

        Version version = verservice.getByID(long_id);

        if (version == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/error");
            return new ResponseEntity<String>(headers,HttpStatus.FOUND);
        }

        byte[] filebytes = version.getContent();

        if (filebytes == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/error");
            return new ResponseEntity<String>(headers,HttpStatus.FOUND);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + version.getAuthor() + "-version-" + version.getNumber() + ".txt\"")
                .body(version.getContent());
    }

}