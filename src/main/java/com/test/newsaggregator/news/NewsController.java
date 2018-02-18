package com.test.newsaggregator.news;

import com.test.newsaggregator.db.entity.NewsEntity;
import com.test.newsaggregator.news.logic.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/news")
@RestController
public class NewsController {
    @Autowired
    private NewsService _newsService;

    @Transactional
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<NewsEntity> getAll(){
        return _newsService.getOrderByDate();
    }

    @Transactional
    @RequestMapping(value = "/search/title", method = RequestMethod.GET)
    public List<NewsEntity> search(@RequestParam String title){
        return _newsService.searchByTitle(title);
    }

}
