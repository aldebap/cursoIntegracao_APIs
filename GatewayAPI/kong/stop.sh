#!  /usr/bin/sh

#   stop Postgres and Kong containers
docker stop kong-db kong-gateway
docker rm kong-db kong-gateway
