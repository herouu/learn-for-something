#### 局域网、公网
* 目标各种环境单机10000+ QPS

 类型 | 功能 | 持久 
 :---: | :---: | :---: 
http 请求 |  select  | redis  
http 请求 |  update,delete,insert | redis 
http 请求 |  select | mysql 
http 请求 | update,delete,insert | mysql 
socket |  select | redis     
socket  | update,delete,insert | redis
socket  | select | mysql
socket | update,delete,insert | mysql


* QPS 量级 100  200  400  600  1000  1500  2000   ... 
* 响应时间控制200ms内
* 并发数  20  40  80  120  200  300  400 ...
* 机器  4核2G
* CPU  i5-8250U  
* 磁盘  HDD  SSD
* 带宽  10MB 20M 50M  100M
* 压测工具  4核2G webbench