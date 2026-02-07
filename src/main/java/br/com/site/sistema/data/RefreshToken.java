//package br.com.site.sistema.data;
//
//import jakarta.persistence.*;
//
//import java.time.Instant;
//
//@Entity(name = "refresh_token")
//public class RefreshToken {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private Usuario usuario;
//
//    @Column(nullable = false, unique = true)
//    private String token;
//
//    @Column(nullable = false)
//    private Instant expirationDate;
//
//    public RefreshToken() {
//    }
//
//    public RefreshToken(Long id, Usuario usuario, String token, Instant expirationDate) {
//        this.id = id;
//        this.usuario = usuario;
//        this.token = token;
//        this.expirationDate = expirationDate;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Usuario getUser() {
//        return usuario;
//    }
//
//    public void setUser(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public Instant getExpirationDate() {
//        return expirationDate;
//    }
//
//    public void setExpirationDate(Instant expirationDate) {
//        this.expirationDate = expirationDate;
//    }
//}
