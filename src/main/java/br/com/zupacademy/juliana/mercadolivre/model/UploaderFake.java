package br.com.zupacademy.juliana.mercadolivre.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader{

    @Override
    public Set<String> send(List<MultipartFile> image) {
        return image.stream()
                .map(img -> "http://bucket.io/"+
                        img.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}