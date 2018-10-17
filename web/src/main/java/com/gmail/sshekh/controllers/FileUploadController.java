package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.UploadService;
import com.gmail.sshekh.service.properties.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;

@Controller
@MultipartConfig
@RequestMapping("/items/upload")
public class FileUploadController {

    private final UploadService uploadService;
    private final FileProperties fileProperties;

    @Autowired
    public FileUploadController(
            @Qualifier("uploadService") UploadService uploadService,
            @Qualifier("fileProperties") FileProperties fileProperties
    ) {
        this.uploadService = uploadService;
        this.fileProperties = fileProperties;
    }

    @GetMapping

    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String displayForm() {
        return "file.upload";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            ModelMap modelMap
    ) throws IOException {
        modelMap.addAttribute("file", file);
        file.transferTo(new File(fileProperties.getFilePath()));
        uploadService.upload();
        return "redirect:/items";
    }
}
