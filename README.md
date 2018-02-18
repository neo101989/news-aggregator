# news-aggregator
Process RSS and HTML content
## Configuration
Project tested on PostgreSQL 9.3 and MySQL 5.7.21.
* Set in application.properties to use PostgreSQL:
```
db.username = user
db.password = password
db.url=jdbc:postgresql://localhost:5432/test?user=user&password=password

hibernate.dialect=org.hibernate.dialect.PostgreSQL93Dialect
hibernate.hbm2ddl.auto=update
hibernate.connection.driver_class=org.postgresql.Driver
hibernate.show_sql=true
```
* Set in application.properties to use MySQL:
```
db.username = user
db.password = password
db.url=jdbc:mysql://localhost:3306/test?useUnicode=yes&characterEncoding=UTF-8&createDatabaseIfNotExist=true

hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
hibernate.hbm2ddl.auto=update
hibernate.connection.driver_class=com.mysql.jdbc.Driver
hibernate.show_sql=true
```
Need create empty DB in PostgreSQL before the server start.
## Build
mvn package
## Use
To connect RSS source need input url and select format **rss**. For example: 
```
Source: https://iz.ru/xml/rss/all.xml
Format: rss
```
To connect HTML source need input url, select format **html**, describe channel and item rule by JQuery selectors. For example: 
```
Source: https://www.gazeta.ru/politics/news/
Format: html
Channel Selector: div[itemtype='http://schema.org/ItemList']
Title Selector: h1[itemprop=name]
Url Selector: link[itemprop=mainEntityOfPage]
Items Selector: div[id=news_main_zone]
Item Selector: article
Title Item Selector: span[itemprop=headline]
Url Item Selector: a[itemprop='mainEntityOfPage url']
Description Selector: span[itemprop="description"] a
Publication Date Selector: time
Publication Date Format: yyyy-MM-dd'T'HH:mm:ssXXX
```
When new source is added the data is began to collect immediately. Then it is collected every hour.
