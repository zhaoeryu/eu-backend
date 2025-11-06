FROM postgres:16
MAINTAINER cn.zhaoey@gmail.com

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# eu db init
COPY init.sh /docker-entrypoint-initdb.d/
COPY init.sql /docker-entrypoint-initdb.d/

# 设置文件权限
RUN chmod +x /docker-entrypoint-initdb.d/init.sh

# 暴露端口
EXPOSE 5432