FROM eclipse-temurin:21-jdk-jammy

MAINTAINER cn.zhaoey@gmail.com

ENV SPRING_PROFILE="dev" \
    BASE_DIR="/home/eu" \
    JAVA="/opt/java/openjdk/bin/java" \
    JVM_XMS="1500m" \
    JVM_XMX="1500m" \
    JVM_MS="256m" \
    JVM_MMS="512m" \
    TIME_ZONE="Asia/Shanghai"

# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

WORKDIR $BASE_DIR

# 复制应用和启动脚本
ADD eu-admin/target/eu-admin.jar target/
ADD env/eu-admin/docker-startup.sh bin/docker-startup.sh

# 创建日志目录并重定向 stdout/stderr
RUN mkdir -p logs gc_logs \
    && touch logs/start.out \
    && ln -sf /dev/stdout logs/start.out \
    && ln -sf /dev/stderr logs/start.out \
    && chmod +x bin/docker-startup.sh

EXPOSE 8000

ENTRYPOINT ["bin/docker-startup.sh"]
