package es.spring.deery.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ARTWORK_OCS", schema = "DEERY", catalog = "")
@IdClass(ArtworkOcsPK.class)
public class ArtworkOcs {
    private Integer originalCharacterId;
    private Integer artworkId;
    private OC originalCharacterByOriginalCharacterId;
    private Artwork artworkByArtworkId;

    @Id
    @Column(name = "ORIGINAL_CHARACTER_ID", insertable = false, updatable = false)
    public Integer getOriginalCharacterId() {
        return originalCharacterId;
    }

    public void setOriginalCharacterId(Integer originalCharacterId) {
        this.originalCharacterId = originalCharacterId;
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
        return Objects.equals(originalCharacterId, that.originalCharacterId) && Objects.equals(artworkId, that.artworkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalCharacterId, artworkId);
    }

    @ManyToOne
    @JoinColumn(name = "ORIGINAL_CHARACTER_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public OC getOriginalCharacterByOriginalCharacterId() {
        return originalCharacterByOriginalCharacterId;
    }

    public void setOriginalCharacterByOriginalCharacterId(OC originalCharacterByOriginalCharacterId) {
        this.originalCharacterByOriginalCharacterId = originalCharacterByOriginalCharacterId;
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
