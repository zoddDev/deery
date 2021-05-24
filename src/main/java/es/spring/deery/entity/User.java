package es.spring.deery.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USERBD", schema = "DEERY", catalog = "")
public class User {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private Creator creatorById;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }

    @OneToOne(mappedBy = "userbdByUserId")
    public Creator getCreatorById() {
        return creatorById;
    }

    public void setCreatorById(Creator creatorById) {
        this.creatorById = creatorById;
    }
}
