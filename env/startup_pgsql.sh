#!/bin/bash
set -e

PROJECT_NAME=eu

{
  docker inspect -f '{{.Id}}' $PROJECT_NAME-admin | xargs docker stop | xargs docker rm
} || {
  echo "容器 $PROJECT_NAME-admin 不存在"
}
{
  docker rmi $(docker images -q $PROJECT_NAME/eu-admin)
} || {
  echo "镜像 $PROJECT_NAME/eu-admin 不存在"
}
docker compose -p eu --env-file .env.pgsql -f docker-compose_pgsql.yml up -d
