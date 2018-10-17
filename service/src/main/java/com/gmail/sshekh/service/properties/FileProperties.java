package com.gmail.sshekh.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileProperties {


    @Value("${items.file.path}")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }
}

