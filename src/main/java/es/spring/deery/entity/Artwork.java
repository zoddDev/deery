package es.spring.deery.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Artwork {
    private Integer id;
    private byte[] img;
    private String title;
    private String description;
    private Date date;
    private Creator creatorByCreatorUserbdId;
    private Collection<ArtworkOcs> artworkOcsById;
    private Collection<Comment> commentsById;

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "img")
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artwork artwork = (Artwork) o;
        return Objects.equals(id, artwork.id) && Arrays.equals(img, artwork.img) && Objects.equals(title, artwork.title) && Objects.equals(description, artwork.description) && Objects.equals(date, artwork.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, description, date);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "creator_userbd_id", referencedColumnName = "userbd_id", nullable = false)
    public Creator getCreatorByCreatorUserbdId() {
        return creatorByCreatorUserbdId;
    }

    public void setCreatorByCreatorUserbdId(Creator creatorByCreatorUserbdId) {
        this.creatorByCreatorUserbdId = creatorByCreatorUserbdId;
    }

    @OneToMany(mappedBy = "artworkByArtworkId")
    public Collection<ArtworkOcs> getArtworkOcsById() {
        return artworkOcsById;
    }

    public void setArtworkOcsById(Collection<ArtworkOcs> artworkOcsById) {
        this.artworkOcsById = artworkOcsById;
    }

    @OneToMany(mappedBy = "artworkByArtworkId")
    public Collection<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<Comment> commentsById) {
        this.commentsById = commentsById;
    }
}
