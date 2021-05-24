package es.spring.deery.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Comment {
    private Integer id;
    private String text;
    private Date date;
    private OC originalCharacterByOriginalCharacterId;
    private Artwork artworkByArtworkId;
    private User userbdByUserbdId;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(text, comment.text) && Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date);
    }

    @ManyToOne
    @JoinColumn(name = "ORIGINAL_CHARACTER_ID", referencedColumnName = "ID")
    public OC getOriginalCharacterByOriginalCharacterId() {
        return originalCharacterByOriginalCharacterId;
    }

    public void setOriginalCharacterByOriginalCharacterId(OC originalCharacterByOriginalCharacterId) {
        this.originalCharacterByOriginalCharacterId = originalCharacterByOriginalCharacterId;
    }

    @ManyToOne
    @JoinColumn(name = "ARTWORK_ID", referencedColumnName = "ID")
    public Artwork getArtworkByArtworkId() {
        return artworkByArtworkId;
    }

    public void setArtworkByArtworkId(Artwork artworkByArtworkId) {
        this.artworkByArtworkId = artworkByArtworkId;
    }

    @ManyToOne
    @JoinColumn(name = "USERBD_ID", referencedColumnName = "ID", nullable = false)
    public User getUserbdByUserbdId() {
        return userbdByUserbdId;
    }

    public void setUserbdByUserbdId(User userbdByUserbdId) {
        this.userbdByUserbdId = userbdByUserbdId;
    }
}
