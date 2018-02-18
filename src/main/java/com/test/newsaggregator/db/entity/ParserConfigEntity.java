package com.test.newsaggregator.db.entity;

import com.test.newsaggregator.db.entity.common.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_parser_config")
public class ParserConfigEntity extends AbstractEntity {
    public enum Format {
        rss, html
    }
    private String _source;
    private Format _format;
    private ChannelRuleEntity _channelRule;
    private ItemRuleEntity _itemRule;

    protected ParserConfigEntity() {
    }

    public ParserConfigEntity(String source, Format format, ChannelRuleEntity channelRuleEntity, ItemRuleEntity itemRule) {
        _source = source;
        _format = format;
        _channelRule = channelRuleEntity;
        _itemRule = itemRule;
    }

    @Column(name = "source")
    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    @Column(name = "format")
    public Format getFormat() {
        return _format;
    }

    public void setFormat(Format format) {
        _format = format;
    }

    @OneToOne(targetEntity = ChannelRuleEntity.class, cascade = CascadeType.ALL)
    public ChannelRuleEntity getChannelRule() {
        return _channelRule;
    }

    public void setChannelRule(ChannelRuleEntity channelRule) {
        _channelRule = channelRule;
    }

    @OneToOne(targetEntity = ItemRuleEntity.class, cascade = CascadeType.ALL)
    public ItemRuleEntity getItemRule() {
        return _itemRule;
    }

    public void setItemRule(ItemRuleEntity itemRule) {
        _itemRule = itemRule;
    }

}
