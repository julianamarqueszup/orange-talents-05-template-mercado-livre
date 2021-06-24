package br.com.zupacademy.juliana.mercadolivre.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private List<Field> field;

    public static class Field {
        private String name;
        private String message;


        public Field(String name, String message) {
            super();
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String nome) {
            this.name = nome;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String mensagem) {
            this.message = message;
        }

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dataHora) {
        this.dateTime = dataHora;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Field> getField() {
        return field;
    }

    public void setField(List<Field> campos) {
        this.field = field;
    }
}