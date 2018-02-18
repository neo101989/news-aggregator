package com.test.newsaggregator.db.dao;

import com.test.newsaggregator.db.dao.common.CommonDao;
import com.test.newsaggregator.db.entity.ChannelEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ChannelDao extends CommonDao<ChannelEntity> {

    @Transactional
    public ChannelEntity getByUrl(String url){
        return getByFieldSingle(ChannelEntity.class, "url", url);
    }

    @Transactional
    public List<ChannelEntity> getAll(){
        return get(ChannelEntity.class);
    }

}
