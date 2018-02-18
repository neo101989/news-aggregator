package com.test.newsaggregator.parser.logic;

import com.test.newsaggregator.db.entity.ChannelEntity;
import com.test.newsaggregator.db.entity.ChannelRuleEntity;
import com.test.newsaggregator.db.entity.ItemRuleEntity;
import com.test.newsaggregator.db.entity.ParserConfigEntity;
import com.test.newsaggregator.parser.error.ParsingException;
import com.test.newsaggregator.parser.logic.common.IParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlParser implements IParser {
    private ParserConfigEntity _parserConfigEntity;

    public HtmlParser(ParserConfigEntity parserConfigEntity){
        _parserConfigEntity = parserConfigEntity;
    }

    @Override
    public ChannelEntity parse() {
        try {
            Document document = Jsoup.connect(_parserConfigEntity.getSource()).get();
            ChannelRuleEntity channelRule = _parserConfigEntity.getChannelRule();
            Elements elements = document.select(channelRule.getChannelSelector());
            String url = getAttrBySelector(elements, channelRule.getUrlSelector(), "href");
            url = url.isEmpty() ? _parserConfigEntity.getSource() : url;
            String title = getValueBySelector(elements, channelRule.getTitleSelector());
            ChannelBuilder builder = new ChannelBuilder(title, url);
            Elements itemElements = document.select(channelRule.getItemsSelector());
            ItemRuleEntity itemRule = _parserConfigEntity.getItemRule();
            for(Element element : itemElements.select(itemRule.getItemSelector())){
                title = getValueBySelector(element, itemRule.getTitleSelector());
                if(!title.isEmpty()) {
                    url = getAttrBySelector(element, itemRule.getUrlSelector(), "href");
                    String description = getValueBySelector(element, itemRule.getDescriptionSelector());
                    String dateString = getAttrBySelector(element, itemRule.getPublicationDateSelector(), "datetime");
                    if(dateString.isEmpty()){
                        dateString = getValueBySelector(element, itemRule.getPublicationDateSelector());
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(itemRule.getPublicationDateFormat());
                    Date date = null;
                    try {
                        date = simpleDateFormat.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    builder.addNews(title, url, description, date);
                }
            }
            return builder.getChannel();
        } catch (IOException e) {
            throw new ParsingException("Document didn't parse. Error: " + e.getMessage());
        }
    }

    private String getValueBySelector(Elements elements, String selector){
        Element element = elements.select(selector).first();
        return getValue(element);
    }

    private String getValueBySelector(Element element, String selector){
        Element subElement = element.select(selector).first();
        return getValue(subElement);
    }

    private String getValue(Element element){
        String value = "";
        if(element != null){
            value = element.val();
            if(value.isEmpty()){
                value = element.text();
            }
        }
        return value == null ? "" : value;
    }

    private String getAttrBySelector(Elements elements, String selector, String attribute){
        Element element = elements.select(selector).first();
        return getAttr(element, attribute);
    }

    private String getAttrBySelector(Element element, String selector, String attribute){
        Element subElement = element.select(selector).first();
        return getAttr(subElement, attribute);
    }

    private String getAttr(Element element, String attribute){
        String value = "";
        if(element != null){
            if(attribute.equals("href")){
                value = element.absUrl(attribute);
            }
            else {
                value = element.attr(attribute);
            }
        }
        return value == null ? "" : value;
    }
}
