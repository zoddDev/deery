package es.spring.deery.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ARTWORK_OCS", schema = "DEERY", catalog = "")
@IdClass(ArtworkOcsPK.class)
public class ArtworkOcs {
    private Integer originalcharacterId;
    private Integer artworkId;
    private OC originalCharacterByOriginalcharacterId;
    private Artwork artworkByArtworkId;

    @Id
    @Column(name = "ORIGINALCHARACTER_ID", insertable = false, updatable = false)
    public Integer getOriginalcharacterId() {
        return originalcharacterId;
    }

    public void setOriginalcharacterId(Integer originalcharacterId) {
        this.originalcharacterId = originalcharacterId;
    }

    @Id
    @Column(name = "ARTWORK_ID", insertable = false, updatable = false)
    public Integer getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Integer artworkId) {
        this.artworkId = artworkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtworkOcs that = (ArtworkOcs) o;
        return Objects.equals(originalcharacterId, that.originalcharacterId) && Objects.equals(artworkId, that.artworkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalcharacterId, artworkId);
    }

    @ManyToOne
    @JoinColumn(name = "ORIGINALCHARACTER_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public OC getOriginalCharacterByOriginalcharacterId() {
        return originalCharacterByOriginalcharacterId;
    }

    public void setOriginalCharacterByOriginalcharacterId(OC originalCharacterByOriginalcharacterId) {
        this.originalCharacterByOriginalcharacterId = originalCharacterByOriginalcharacterId;
    }

    @ManyToOne
    @JoinColumn(name = "ARTWORK_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public Artwork getArtworkByArtworkId() {
        return artworkByArtworkId;
    }

    public void setArtworkByArtworkId(Artwork artworkByArtworkId) {
        this.artworkByArtworkId = artworkByArtworkId;
    }
}
