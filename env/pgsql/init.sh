#!/bin/bash
set -e

echo "执行数据库初始化脚本..."
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -f /docker-entrypoint-initdb.d/init.sql

echo "数据库初始化完成！"