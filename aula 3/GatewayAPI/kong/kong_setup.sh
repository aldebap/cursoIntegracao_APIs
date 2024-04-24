#!  /usr/bin/sh

docker network create kong-net
docker pull kong/kong-gateway:3.3.0.0

#   start Postgres before configure Kong
docker run -d --name kong-db \
    --network=kong-net \
    -p 5432:5432 \
    -e "POSTGRES_USER=kong" \
    -e "POSTGRES_DB=kong" \
    -e "POSTGRES_PASSWORD=kongpass" \
    postgres:13

#   configure Kong
docker run --rm \
    --network=kong-net \
    -e "KONG_DATABASE=postgres" \
    -e "KONG_PG_HOST=kong-db" \
    -e "KONG_PG_PASSWORD=kongpass" \
    -e "KONG_PASSWORD=test" \
    kong/kong-gateway:3.3.0.0 kong migrations bootstrap
