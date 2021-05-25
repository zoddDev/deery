package es.spring.deery.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Creator {
    private Integer userbdId;
    private Collection<Artwork> artworksByUserbdId;
    private User userbdByUserbdId;
    private Collection<OC> originalCharactersByUserbdId;

    @Id
    @Column(name = "userbd_id")
    public Integer getUserbdId() {
        return userbdId;
    }

    public void setUserbdId(Integer userbdId) {
        this.userbdId = userbdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return Objects.equals(userbdId, creator.userbdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userbdId);
    }

    @OneToMany(mappedBy = "creatorByCreatorUserbdId")
    public Collection<Artwork> getArtworksByUserbdId() {
        return artworksByUserbdId;
    }

    public void setArtworksByUserbdId(Collection<Artwork> artworksByUserbdId) {
        this.artworksByUserbdId = artworksByUserbdId;
    }

    @OneToOne
    @JoinColumn(name = "userbd_id", referencedColumnName = "id", nullable = false)
    public User getUserbdByUserbdId() {
        return userbdByUserbdId;
    }

    public void setUserbdByUserbdId(User userbdByUserbdId) {
        this.userbdByUserbdId = userbdByUserbdId;
    }

    @OneToMany(mappedBy = "creatorByCreatorUserbdId")
    public Collection<OC> getOriginalCharactersByUserbdId() {
        return originalCharactersByUserbdId;
    }

    public void setOriginalCharactersByUserbdId(Collection<OC> originalCharactersByUserbdId) {
        this.originalCharactersByUserbdId = originalCharactersByUserbdId;
    }
}
