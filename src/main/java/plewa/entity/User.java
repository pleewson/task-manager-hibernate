package plewa.entity;
import jakarta.persistence.*;

@Entity
public class User {
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String email;
}
