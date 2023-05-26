package com.stormy23.doc;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.model.Version;
import com.stormy23.services.CardService;
import com.stormy23.services.DocumentService;
import com.stormy23.services.VersionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
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
    public String greeting(HttpServletRequest request, HttpServletResponse response, @PathVariable(required = false) String id, Model model)  {
        if (id == null) {
            return "notfound";
        }

        long long_id;

        try {
            long_id = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "406";
        }

        Version version = verservice.getByID(long_id);

        if (version == null) {
            return "notfound";
        }

        File file = version.getContent();

        if (file == null) {
            return "404";
        }

        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            try {
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            } catch (IOException e) {
                return "500";
            }

        }

        return "document";
    }

}