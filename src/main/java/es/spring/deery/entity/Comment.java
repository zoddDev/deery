package es.spring.deery.entity;

import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name="Comment" , strategy="increment")
    @GeneratedValue(generator="Comment")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(text, comment.text) && Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date);
    }

    @ManyToOne
    @JoinColumn(name = "original_character_id", referencedColumnName = "id")
    public OC getOriginalCharacterByOriginalCharacterId() {
        return originalCharacterByOriginalCharacterId;
    }

    public void setOriginalCharacterByOriginalCharacterId(OC originalCharacterByOriginalCharacterId) {
        this.originalCharacterByOriginalCharacterId = originalCharacterByOriginalCharacterId;
    }

    @ManyToOne
    @JoinColumn(name = "artwork_id", referencedColumnName = "id")
    public Artwork getArtworkByArtworkId() {
        return artworkByArtworkId;
    }

    public void setArtworkByArtworkId(Artwork artworkByArtworkId) {
        this.artworkByArtworkId = artworkByArtworkId;
    }

    @ManyToOne
    @JoinColumn(name = "userbd_id", referencedColumnName = "id", nullable = false)
    public User getUserbdByUserbdId() {
        return userbdByUserbdId;
    }

    public void setUserbdByUserbdId(User userbdByUserbdId) {
        this.userbdByUserbdId = userbdByUserbdId;
    }
}
