package com.test.newsaggregator.news.logic;

import com.test.newsaggregator.db.dao.NewsDao;
import com.test.newsaggregator.db.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsDao _newsDao;

    @Transactional
    public List<NewsEntity> getAll(){
        return _newsDao.getAll();
    }

    @Transactional
    public List<NewsEntity> getOrderByDate(){
        return _newsDao.getOrderByDate();
    }

    @Transactional
    public List<NewsEntity> searchByTitle(String title){
        return _newsDao.searchByTitle(title);
    }
}
