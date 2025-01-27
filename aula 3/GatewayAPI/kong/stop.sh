#!  /usr/bin/sh

#   stop Postgres and Kong containers
docker kill kong-db kong-gateway
docker container rm kong-db kong-gateway
docker network rm kong-net
