package com.test.newsaggregator.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.test.newsaggregator.db.entity.common.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_news")
public class NewsEntity extends AbstractEntity {
    private ChannelEntity channel;
    private String title;
    private String url;
    private String description;
    private Date publicationDate;

    @JsonManagedReference
    @ManyToOne(targetEntity = ChannelEntity.class, cascade = CascadeType.ALL)
    public ChannelEntity getChannel() {
        return channel;
    }

    public void setChannel(ChannelEntity channel) {
        this.channel = channel;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "Description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "publication_date")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
