package es.spring.deery.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ORIGINAL_CHARACTER", schema = "DEERY", catalog = "")
public class OC {
    private Integer id;
    private byte[] img;
    private String name;
    private String description;
    private Collection<ArtworkOcs> artworkOcsById;
    private Creator creatorByCreatorUserId;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IMG")
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OC oc = (OC) o;
        return Objects.equals(id, oc.id) && Arrays.equals(img, oc.img) && Objects.equals(name, oc.name) && Objects.equals(description, oc.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @OneToMany(mappedBy = "originalCharacterByOriginalcharacterId")
    public Collection<ArtworkOcs> getArtworkOcsById() {
        return artworkOcsById;
    }

    public void setArtworkOcsById(Collection<ArtworkOcs> artworkOcsById) {
        this.artworkOcsById = artworkOcsById;
    }

    @ManyToOne
    @JoinColumn(name = "CREATOR_USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public Creator getCreatorByCreatorUserId() {
        return creatorByCreatorUserId;
    }

    public void setCreatorByCreatorUserId(Creator creatorByCreatorUserId) {
        this.creatorByCreatorUserId = creatorByCreatorUserId;
    }
}
