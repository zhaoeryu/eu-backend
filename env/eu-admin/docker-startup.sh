#!/bin/bash
set -e
echo "=============================="
echo "========== Starting =========="
echo "=============================="

#===========================================================================================
# JVM Configuration
#===========================================================================================
JAVA_OPT="${JAVA_OPT} -server -Xms${JVM_XMS} -Xmx${JVM_XMX} -Xmn${JVM_XMN} -XX:MetaspaceSize=${JVM_MS} -XX:MaxMetaspaceSize=${JVM_MMS}"
JAVA_OPT="${JAVA_OPT} -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/java_heapdump.hprof -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC -XX:+PrintGCApplicationStoppedTime -Xloggc:${BASE_DIR}/logs/gc-%t.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=14 -XX:GCLogFileSize=100M"

JAVA_OPT="${JAVA_OPT} -DMYSQL_HOST=${MYSQL_HOST}"
JAVA_OPT="${JAVA_OPT} -DMYSQL_PASSWORD=${MYSQL_PASSWORD}"
JAVA_OPT="${JAVA_OPT} -DREDIS_HOST=${REDIS_HOST}"
JAVA_OPT="${JAVA_OPT} -DREDIS_PASSWORD=${REDIS_PASSWORD}"

#===========================================================================================
# Setting system properties
#===========================================================================================
JAVA_OPT="${JAVA_OPT} -Dspring.profiles.active=${SPRING_PROFILE}"
JAVA_OPT="${JAVA_OPT} -Dfile.encoding=utf-8"
JAVA_OPT="${JAVA_OPT} -Djava.security.egd=file:/dev/./urandom"
JAVA_OPT="${JAVA_OPT} -jar ${BASE_DIR}/target/eu-admin.jar"

echo "server is starting,you can docker logs your container"
echo "exec java ${JAVA_OPT}"
exec $JAVA ${JAVA_OPT}