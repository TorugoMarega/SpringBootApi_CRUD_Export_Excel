package br.com.torugo.cadatro_pessoa_excel.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private Object object;
    private String route;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    public Response(String message, String route) {
        this.message = message;
        this.route = route;
        this.timestamp = LocalDateTime.now();
    }

    public Response(String message, Object object, String route) {
        this.message=message;
        this.object = object;
        this.route=route;
    }
}
