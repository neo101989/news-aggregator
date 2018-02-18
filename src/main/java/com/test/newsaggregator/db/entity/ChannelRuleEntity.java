package com.test.newsaggregator.db.entity;

import com.test.newsaggregator.db.entity.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tb_channel_rule")
public class ChannelRuleEntity extends AbstractEntity{
    private String channelSelector;
    private String titleSelector;
    private String urlSelector;
    private String itemsSelector;

    @Column(name = "channel_selector")
    public String getChannelSelector() {
        return channelSelector;
    }

    public void setChannelSelector(String channelSelector) {
        this.channelSelector = channelSelector;
    }

    @Column(name = "title_selector")
    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    @Column(name = "url_selector")
    public String getUrlSelector() {
        return urlSelector;
    }

    public void setUrlSelector(String urlSelector) {
        this.urlSelector = urlSelector;
    }

    @Column(name = "items_selector")
    public String getItemsSelector() {
        return itemsSelector;
    }

    public void setItemsSelector(String itemsSelector) {
        this.itemsSelector = itemsSelector;
    }
}
