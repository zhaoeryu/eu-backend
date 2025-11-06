#!/bin/bash
set -e
echo "=============================="
echo "========== Starting =========="
echo "=============================="

#===========================================================================================
# JVM Configuration for JDK 21 (G1GC + Unified Logging)
#===========================================================================================

# 基础内存设置（G1GC 下通常无需设置 -Xmn）
JAVA_OPT="${JAVA_OPT} -server -Xms${JVM_XMS} -Xmx${JVM_XMX}"
JAVA_OPT="${JAVA_OPT} -XX:MetaspaceSize=${JVM_MS} -XX:MaxMetaspaceSize=${JVM_MMS}"

# 使用 G1GC（JDK 21 默认 GC，适合中等堆）
JAVA_OPT="${JAVA_OPT} -XX:+UseG1GC"
JAVA_OPT="${JAVA_OPT} -XX:+UseStringDeduplication"
JAVA_OPT="${JAVA_OPT} -XX:MaxGCPauseMillis=200"

# 启用 OOM 自动堆转储
JAVA_OPT="${JAVA_OPT} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/java_heapdump.hprof"

# 统一日志格式（JDK 9+ 推荐，替代旧 PrintGC* 参数）
JAVA_OPT="${JAVA_OPT} -Xlog:gc*:file=${BASE_DIR}/logs/gc-%t.log:time,tags,uptime,level:filecount=14,filesize=100M"

#===========================================================================================
# System Properties
#===========================================================================================
JAVA_OPT="${JAVA_OPT} -DDB_HOST=${DB_HOST}"
JAVA_OPT="${JAVA_OPT} -DDB_PORT=${DB_PORT}"
JAVA_OPT="${JAVA_OPT} -DDB_USERNAME=${DB_USERNAME}"
JAVA_OPT="${JAVA_OPT} -DDB_PASSWORD=${DB_PASSWORD}"
JAVA_OPT="${JAVA_OPT} -DREDIS_HOST=${REDIS_HOST}"
JAVA_OPT="${JAVA_OPT} -DREDIS_PASSWORD=${REDIS_PASSWORD}"

JAVA_OPT="${JAVA_OPT} -Dspring.profiles.active=${SPRING_PROFILE}"
JAVA_OPT="${JAVA_OPT} -Dfile.encoding=UTF-8"
JAVA_OPT="${JAVA_OPT} -Djava.security.egd=file:/dev/./urandom"
JAVA_OPT="${JAVA_OPT} -jar ${BASE_DIR}/target/eu-admin.jar"

echo "Server is starting... You can use 'docker logs <container>' to view logs."
echo "Exec: java ${JAVA_OPT}"
exec $JAVA ${JAVA_OPT}