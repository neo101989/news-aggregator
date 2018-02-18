package com.test.newsaggregator.parser;

import com.test.newsaggregator.db.entity.ParserConfigEntity;
import com.test.newsaggregator.parser.logic.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParserController {
    @Autowired
    private ParserService _parserService;

    @RequestMapping(value = "/parser/add", method = RequestMethod.POST)
    public ResponseEntity addParser(@RequestBody ParserConfigEntity configEntity){
        configEntity = _parserService.save(configEntity);
        return ResponseEntity.ok().build();
    }
}
