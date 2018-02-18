package com.test.newsaggregator.parser.logic;

import com.test.newsaggregator.db.dao.ParserConfigDao;
import com.test.newsaggregator.db.entity.ParserConfigEntity;
import com.test.newsaggregator.process.logic.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserService {
    @Autowired
    private ParserConfigDao _parserConfigDao;
    @Autowired
    private ProcessService _processService;

    public ParserConfigEntity save(ParserConfigEntity parserConfigEntity){
        ParserConfigEntity oldParserConfigEntity = _parserConfigDao.getBySource(parserConfigEntity.getSource());
        if(oldParserConfigEntity == null) {
            _parserConfigDao.save(parserConfigEntity);
            _processService.process(parserConfigEntity);
        }
        else{
            parserConfigEntity.setId(oldParserConfigEntity.getId());
            _parserConfigDao.saveOrUpdate(parserConfigEntity);
        }
        return parserConfigEntity;
    }

}
