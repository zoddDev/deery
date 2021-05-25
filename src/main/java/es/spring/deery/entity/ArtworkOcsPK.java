package es.spring.deery.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ArtworkOcsPK implements Serializable {
    private Integer originalCharacterId;
    private Integer artworkId;

    @Column(name = "original_character_id")
    @Id
    public Integer getOriginalCharacterId() {
        return originalCharacterId;
    }

    public void setOriginalCharacterId(Integer originalCharacterId) {
        this.originalCharacterId = originalCharacterId;
    }

    @Column(name = "artwork_id")
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
        return Objects.equals(originalCharacterId, that.originalCharacterId) && Objects.equals(artworkId, that.artworkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalCharacterId, artworkId);
    }
}
