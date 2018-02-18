package com.test.newsaggregator.parser.logic;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.test.newsaggregator.db.entity.ChannelEntity;
import com.test.newsaggregator.parser.error.ParserCreateException;
import com.test.newsaggregator.parser.logic.common.IParser;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class RssParser implements IParser{
    private SyndFeed _syncFeed;

    public RssParser(String url) throws ParserCreateException {
        try {
            _syncFeed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (IllegalArgumentException|FeedException|IOException e) {
            throw new ParserCreateException("Parser didn't create. Error: " + e.getMessage());
        }
    }


    @Override
    public ChannelEntity parse() {
        String channelTitle = _syncFeed.getTitle();
        String channelUrl = _syncFeed.getLink();
        ChannelBuilder builder = new ChannelBuilder(channelTitle, channelUrl);
        for(Object entry : _syncFeed.getEntries()){
            SyndEntry syndEntry = (SyndEntry) entry;
            String title = syndEntry.getTitle();
            String url = syndEntry.getLink();
            SyndContent syndContent = syndEntry.getDescription();
            String description = syndContent.getValue();
            Date publicationDate = syndEntry.getPublishedDate();
            if(publicationDate != null){
                publicationDate = new Date(publicationDate.getTime() - 2 * 60 * 60 * 1000);
            }
            builder.addNews(title, url, description, publicationDate);
        }
        return builder.getChannel();
    }
}
