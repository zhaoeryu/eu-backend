FROM openjdk:8u342-oraclelinux8

MAINTAINER cn.zhaoey@gmail.com

ENV SPRING_PROFILE="dev" \
    BASE_DIR="/home/eu" \
    JAVA="/usr/java/openjdk-8/bin/java" \
    JVM_XMS="64m" \
    JVM_XMX="384m" \
    JVM_XMN="32m" \
    JVM_MS="256m" \
    JVM_MMS="256m" \
    TIME_ZONE="Asia/Shanghai"

RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

WORKDIR $BASE_DIR

ADD eu-admin/target/eu-admin.jar target/
ADD env/eu-admin/docker-startup.sh bin/docker-startup.sh

# set startup log dir
RUN mkdir -p logs \
    && mkdir -p gc_logs \
	&& cd logs \
	&& touch start.out \
	&& ln -sf /dev/stdout start.out \
	&& ln -sf /dev/stderr start.out
RUN chmod +x bin/docker-startup.sh

EXPOSE 8000

ENTRYPOINT ["bin/docker-startup.sh"]
