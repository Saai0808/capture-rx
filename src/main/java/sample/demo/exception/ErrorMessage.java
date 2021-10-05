package sample.demo.exception;

import lombok.*;

@Getter
@Setter
@Builder
public class ErrorMessage {
    private String message;
    private String description;
}