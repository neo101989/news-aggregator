package com.test.newsaggregator.db.dao;

import com.test.newsaggregator.db.dao.common.CommonDao;
import com.test.newsaggregator.db.entity.ParserConfigEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ParserConfigDao extends CommonDao<ParserConfigEntity> {

    @Transactional
    public List<ParserConfigEntity> getAll(){
        return get(ParserConfigEntity.class);
    }

    @Transactional
    public ParserConfigEntity getBySource(String source){
        return getByFieldSingle(ParserConfigEntity.class, "source", source);
    }

}
