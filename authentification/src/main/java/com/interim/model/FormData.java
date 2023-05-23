package com.interim.model;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class FormData {

    @RestForm("file")
    private FileUpload file;

    @RestForm("name")
    private String name;

    public FileUpload getFile() {
        return file;
    }

    public String getName() {
        return name;
    }
}