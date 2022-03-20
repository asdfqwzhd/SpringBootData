动作环境Java8
===============
## 开发工具
### idea
* idea下载：https://www.jetbrains.com/idea/download/#section=windows
### docker desktop
* docker desktop下载：https://www.docker.com/products/docker-desktop/
### a5m2
* a5m2下载：https://a5m2.mmatsubara.com/
### postman
* postman下载：https://www.postman.com/downloads/

## 环境搭建
### 配置要求
如全部在本机配置，本机内存至少16G，16G启动docker电脑会很卡，但不是不能运行，建议内存至少32G。
### elasticsearch & kibana
* 按顺序在cmd执行以下命令，执行一次即可，之后可在docker desktop图形界面启动elasticsearch & kibana
1. elasticsearch镜像下载：docker pull docker.elastic.co/elasticsearch/elasticsearch:7.15.2
2. kibana镜像下载：docker pull docker.elastic.co/kibana/kibana:7.15.2
3. elasticsearch & kibana docker网络创建：docker network create esnetwork
4. elasticsearch启动：docker run -d --name elasticsearch --net esnetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.15.2
5. kibana启动：docker run -d --name kibana --net esnetwork -p 5601:5601 docker.elastic.co/kibana/kibana:7.15.2
### postgres
* 按顺序在cmd执行以下1.2命令，执行一次即可，之后可在docker desktop图形界面启动postgres。
* 按顺序在a5m2执行以下3-6操作，创建测试用户及数据库。
1. postgres镜像下载：docker pull postgres:14.2
2. postgres启动：docker run --name postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
3. 以管理员用户链接数据库：以database name：postgres，user id：postgres，password：postgres链接数据库。
4. 创建新用户sql：CREATE USER test WITH PASSWORD 'test';
5. 创建新数据库sql：CREATE DATABASE test ENCODING UTF8 OWNER test;
6. 以test用户链接test数据库：以database name：test，user id：test，password：test链接数据库。