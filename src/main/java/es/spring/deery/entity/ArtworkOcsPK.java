package es.spring.deery.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ArtworkOcsPK implements Serializable {
    private Integer originalcharacterId;
    private Integer artworkId;

    @Column(name = "ORIGINALCHARACTER_ID")
    @Id
    public Integer getOriginalcharacterId() {
        return originalcharacterId;
    }

    public void setOriginalcharacterId(Integer originalcharacterId) {
        this.originalcharacterId = originalcharacterId;
    }

    @Column(name = "ARTWORK_ID")
    @Id
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
        ArtworkOcsPK that = (ArtworkOcsPK) o;
        return Objects.equals(originalcharacterId, that.originalcharacterId) && Objects.equals(artworkId, that.artworkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalcharacterId, artworkId);
    }
}
