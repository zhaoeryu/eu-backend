FROM mysql:5.7
MAINTAINER cn.zhaoey@gmail.com

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# eu db init
COPY eu.sql /docker-entrypoint-initdb.d

COPY mysql.cnf /etc/mysql/conf.d/mysql.cnf