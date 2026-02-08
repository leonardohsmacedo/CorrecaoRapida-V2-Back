package br.com.correcaorapida.sistema.data.payload.respostas;

import lombok.Data;

import java.util.List;

@Data
public class JwtResposta {

    private String token;
    private String type = "Bearer";
//    private String refreshToken;
//    private Long id;
//    private String username;
//    private String email;
//    private List<String> roles;

//    public JwtResposta(String token, String refreshToken, Long id, String username, String email, List<String> roles) {
//    public JwtResposta(String token,  Long id, String username, String email, List<String> roles) {
//public JwtResposta(String token, String email) {
public JwtResposta(String token) {
        this.token = token;
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.roles = roles;
    }

}
