package com.test.newsaggregator.parser.logic;

import com.test.newsaggregator.db.entity.ChannelEntity;
import com.test.newsaggregator.db.entity.NewsEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChannelBuilder {
    private ChannelEntity _channel;

    public ChannelBuilder(String title, String url) {
        _channel = new ChannelEntity();
        _channel.setUrl(url);
        _channel.setTitle(title);
        _channel.setNews(new ArrayList<>());
    }

    public void addNews(String title, String url, String description, Date publicationDate){
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setChannel(_channel);
        newsEntity.setDescription(description);
        newsEntity.setPublicationDate(publicationDate == null ? new Date() : publicationDate);
        newsEntity.setUrl(url);
        newsEntity.setTitle(title);
        List<NewsEntity> news = _channel.getNews();
        news.add(newsEntity);
    }

    public ChannelEntity getChannel() {
        return _channel;
    }
}
