package es.spring.deery.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Creator {
    private Integer userId;
    private Collection<Artwork> artworksByUserId;
    private User userbdByUserId;
    private Collection<OC> originalCharactersByUserId;

    @Id
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return Objects.equals(userId, creator.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @OneToMany(mappedBy = "creatorByCreatorUserId")
    public Collection<Artwork> getArtworksByUserId() {
        return artworksByUserId;
    }

    public void setArtworksByUserId(Collection<Artwork> artworksByUserId) {
        this.artworksByUserId = artworksByUserId;
    }

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    public User getUserbdByUserId() {
        return userbdByUserId;
    }

    public void setUserbdByUserId(User userbdByUserId) {
        this.userbdByUserId = userbdByUserId;
    }

    @OneToMany(mappedBy = "creatorByCreatorUserId")
    public Collection<OC> getOriginalCharactersByUserId() {
        return originalCharactersByUserId;
    }

    public void setOriginalCharactersByUserId(Collection<OC> originalCharactersByUserId) {
        this.originalCharactersByUserId = originalCharactersByUserId;
    }
}
