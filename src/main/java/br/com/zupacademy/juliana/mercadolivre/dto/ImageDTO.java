package br.com.zupacademy.juliana.mercadolivre.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImageDTO {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> image = new ArrayList<>();

    public void setImage(@Size(min = 1) List<MultipartFile> image) {
        this.image = image;
    }

    public List<MultipartFile> getImage() {
        return image;
    }
}