package es.spring.deery.entity;

import javax.persistence.*;
import java.sql.Date;
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
    private Date date;
    private Collection<ArtworkOcs> artworkOcsById;
    private Collection<Comment> commentsById;
    private Creator creatorByCreatorId;

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

    @Basic
    @Column(name = "DATE")
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
        OC oc = (OC) o;
        return Objects.equals(id, oc.id) && Arrays.equals(img, oc.img) && Objects.equals(name, oc.name) && Objects.equals(description, oc.description) && Objects.equals(date, oc.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, date);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @OneToMany(mappedBy = "originalCharacterByOriginalCharacterId")
    public Collection<ArtworkOcs> getArtworkOcsById() {
        return artworkOcsById;
    }

    public void setArtworkOcsById(Collection<ArtworkOcs> artworkOcsById) {
        this.artworkOcsById = artworkOcsById;
    }

    @OneToMany(mappedBy = "originalCharacterByOriginalCharacterId")
    public Collection<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<Comment> commentsById) {
        this.commentsById = commentsById;
    }

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID", referencedColumnName = "USERBD_ID", nullable = false)
    public Creator getCreatorByCreatorId() {
        return creatorByCreatorId;
    }

    public void setCreatorByCreatorId(Creator creatorByCreatorId) {
        this.creatorByCreatorId = creatorByCreatorId;
    }
}
