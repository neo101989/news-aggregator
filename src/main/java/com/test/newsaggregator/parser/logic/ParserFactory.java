package com.test.newsaggregator.parser.logic;

import com.test.newsaggregator.parser.error.ParserCreateException;
import com.test.newsaggregator.parser.logic.common.IParser;
import com.test.newsaggregator.db.entity.ParserConfigEntity;

public class ParserFactory {
    private static final ParserFactory _parserFactory = new ParserFactory();

    public static ParserFactory getInstance(){
        return _parserFactory;
    }

    private ParserFactory() {
    }

    public IParser createParser(ParserConfigEntity parserConfigEntity){
        switch (parserConfigEntity.getFormat()){
            case rss:
                return new RssParser(parserConfigEntity.getSource());
            case html:
                return new HtmlParser(parserConfigEntity);
        }
        throw new ParserCreateException("Parser didn't create. Error: Parser with " + parserConfigEntity.getFormat().name() + " format didn't found!");
    }
}
