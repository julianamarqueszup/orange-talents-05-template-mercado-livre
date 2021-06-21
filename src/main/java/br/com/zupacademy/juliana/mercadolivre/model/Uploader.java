package br.com.zupacademy.juliana.mercadolivre.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {
    Set<String> send(List<MultipartFile> image);
}