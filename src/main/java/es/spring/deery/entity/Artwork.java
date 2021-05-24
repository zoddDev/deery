package es.spring.deery.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Artwork {
    private Integer id;
    private byte[] img;
    private String title;
    private String description;
    private Creator creatorByCreatorUserId;
    private Collection<ArtworkOcs> artworkOcsById;

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
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
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Artwork artwork = (Artwork) o;
        return Objects.equals(id, artwork.id) && Arrays.equals(img, artwork.img) && Objects.equals(title, artwork.title) && Objects.equals(description, artwork.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, description);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CREATOR_USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public Creator getCreatorByCreatorUserId() {
        return creatorByCreatorUserId;
    }

    public void setCreatorByCreatorUserId(Creator creatorByCreatorUserId) {
        this.creatorByCreatorUserId = creatorByCreatorUserId;
    }

    @OneToMany(mappedBy = "artworkByArtworkId")
    public Collection<ArtworkOcs> getArtworkOcsById() {
        return artworkOcsById;
    }

    public void setArtworkOcsById(Collection<ArtworkOcs> artworkOcsById) {
        this.artworkOcsById = artworkOcsById;
    }
}
