package com.test.newsaggregator.db.dao;

import com.test.newsaggregator.db.dao.common.CommonDao;
import com.test.newsaggregator.db.entity.NewsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class NewsDao extends CommonDao<NewsEntity> {

    @Transactional
    public NewsEntity getByUrl(String url){
        return getByFieldSingle(NewsEntity.class, "url", url);
    }

    @Transactional
    public List<NewsEntity> getAll(){
        return get(NewsEntity.class);
    }

    @Transactional
    public List<NewsEntity> getOrderByDate(){
        return getSession().createQuery("from NewsEntity n order by n.publicationDate DESC").list();
    }

    @Transactional
    public List<NewsEntity> searchByTitle(String title){
        return getSession().createQuery("from NewsEntity n where n.title like :title order by n.publicationDate DESC").setParameter("title", "%" + title + "%").list();
    }

}
