package br.com.torugo.cadatro_pessoa_excel.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ConnectionResponse {
    private String message;
    private String route;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ConnectionResponse(String message, String route) {
        this.message=message;
        this.route=route;
    }

    @Override
    public String toString() {
        return  message + ", route='" + route + ", timestamp=" + timestamp ;
    }
}
