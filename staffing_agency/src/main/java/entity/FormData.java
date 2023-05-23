package entity;


import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
public class FormData {

    @RestForm("file")
    private FileUpload file;

    @RestForm("name")
    private String name;

    @RestForm("type")
    private String type;

    @RestForm("companyId")
    private String companyId;

    public FileUpload getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getType() {
        return type;
    }
}