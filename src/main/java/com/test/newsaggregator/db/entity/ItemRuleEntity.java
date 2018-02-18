package com.test.newsaggregator.db.entity;


import com.test.newsaggregator.db.entity.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_item_rule")
public class ItemRuleEntity extends AbstractEntity {
    private String itemSelector;
    private String titleSelector;
    private String urlSelector;
    private String descriptionSelector;
    private String publicationDateSelector;
    private String publicationDateFormat;

    @Column(name = "item_selector")
    public String getItemSelector() {
        return itemSelector;
    }

    public void setItemSelector(String itemSelector) {
        this.itemSelector = itemSelector;
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

    @Column(name = "description_selector")
    public String getDescriptionSelector() {
        return descriptionSelector;
    }

    public void setDescriptionSelector(String descriptionSelector) {
        this.descriptionSelector = descriptionSelector;
    }

    @Column(name = "publication_date_selector")
    public String getPublicationDateSelector() {
        return publicationDateSelector;
    }

    public void setPublicationDateSelector(String publicationDateSelector) {
        this.publicationDateSelector = publicationDateSelector;
    }

    @Column(name = "publication_date_format")
    public String getPublicationDateFormat() {
        return publicationDateFormat;
    }

    public void setPublicationDateFormat(String publicationDateFormat) {
        this.publicationDateFormat = publicationDateFormat;
    }
}
