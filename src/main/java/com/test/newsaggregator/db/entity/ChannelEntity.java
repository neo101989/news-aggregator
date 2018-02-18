package com.test.newsaggregator.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.test.newsaggregator.db.entity.common.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_channel")
public class ChannelEntity extends AbstractEntity{
    private String title;
    private String url;
    private List<NewsEntity> news;

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

    @JsonBackReference
    @OneToMany(targetEntity = NewsEntity.class, cascade = CascadeType.ALL)
    public List<NewsEntity> getNews() {
        return news;
    }

    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }
}
