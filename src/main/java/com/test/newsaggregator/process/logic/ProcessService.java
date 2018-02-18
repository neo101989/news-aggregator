package com.test.newsaggregator.process.logic;

import com.test.newsaggregator.db.dao.ChannelDao;
import com.test.newsaggregator.db.dao.NewsDao;
import com.test.newsaggregator.db.dao.ParserConfigDao;
import com.test.newsaggregator.db.entity.ChannelEntity;
import com.test.newsaggregator.db.entity.NewsEntity;
import com.test.newsaggregator.parser.logic.ParserFactory;
import com.test.newsaggregator.parser.logic.common.IParser;
import com.test.newsaggregator.db.entity.ParserConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProcessService {
    @Autowired
    public ParserConfigDao _parserConfigDao;
    @Autowired
    public ChannelDao _channelDao;
    @Autowired
    public NewsDao _newsDao;

    private ExecutorService _service = Executors.newFixedThreadPool(4);
    private ParserFactory _parserFactory = ParserFactory.getInstance();

    @Scheduled(cron="0 0 * * * *")
    public void run(){
        for(ParserConfigEntity configEntity : _parserConfigDao.getAll()){
            process(configEntity);
        }
    }

    public void process(ParserConfigEntity parserConfigEntity){
        try {
            final IParser parser = _parserFactory.createParser(parserConfigEntity);
            _service.submit(()->{
                try {
                    ChannelEntity channelEntity = parser.parse();
                    save(channelEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Transactional
    public  void save(ChannelEntity channelEntity){
        String url = channelEntity.getUrl();
        if(url != null) {
            ChannelEntity oldChannelEntity = _channelDao.getByUrl(channelEntity.getUrl());
            if(oldChannelEntity == null){
                _channelDao.save(channelEntity);
            }
            else{
                for(NewsEntity newsEntity : channelEntity.getNews()){
                    NewsEntity oldNewsEntity = _newsDao.getByUrl(newsEntity.getUrl());
                    if(oldNewsEntity == null){
                        newsEntity.setChannel(oldChannelEntity);
                        _newsDao.save(newsEntity);
                    }
                }
            }
        }

    }

}
