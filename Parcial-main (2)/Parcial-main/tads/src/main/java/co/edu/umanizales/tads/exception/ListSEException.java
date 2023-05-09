package co.edu.umanizales.tads.exception;

import lombok.Data;

@Data
public class ListSEException extends RuntimeException{
    private String code;
    public ListSEException(String code, String message){
        super(message);
        this.code=code;
    }
}
