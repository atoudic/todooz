package fr.todooz.domain;

import org.apache.commons.lang.StringUtils;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date createdAt = new Date();

    @Column
    private String title;

    @Column(length = 4000, nullable = true)
    private String text;

    @Column
    private Date date;

    @Column(nullable = true)
    private String tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String[] getTagArray() {
        return StringUtils.split(tags, ",");
    }
}
