package notes.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String content;

    @CreationTimestamp
    private OffsetDateTime creationTimestamp;

    @UpdateTimestamp
    private OffsetDateTime updateTimestamp;

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(OffsetDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public OffsetDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(OffsetDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

}
