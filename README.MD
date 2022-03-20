动作环境Java8
===============

## 环境搭建
### 配置要求
如全部在本机配置，本机内存至少16G，16G启动docker电脑会很卡，但不是不能运行，建议内存至少32G。
### postman
* postman安装：https://www.postman.com/downloads/
### docker
* docker desktop安装：https://www.docker.com/products/docker-desktop/
### elasticsearch & kibana
* 按顺序在cmd执行以下命令，执行一次即可，之后可在docker desktop图形界面启动elasticsearch & kibana
1. elasticsearch镜像下载：docker pull docker.elastic.co/elasticsearch/elasticsearch:7.15.2
2. kibana镜像下载：docker pull docker.elastic.co/kibana/kibana:7.15.2
3. elasticsearch & kibana docker网络创建：docker network create esnetwork
4. elasticsearch启动：docker run -d --name elasticsearch --net esnetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.15.2
5. kibana启动：docker run -d --name kibana --net esnetwork -p 5601:5601 docker.elastic.co/kibana/kibana:7.15.2
