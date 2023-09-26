#!  /usr/bin/sh

#   Postgres variables
POSTGRES_DB=kong
POSTGRES_USER=kong
POSTGRES_PASSWORD=kongpasswd

#   run Postgres containers
docker run -d --name kong-db \
    --network=kong-net \
    -p 5432:5432 \
    -e "POSTGRES_DB=${POSTGRES_DB}" \
    -e "POSTGRES_USER=${POSTGRES_USER}" \
    -e "POSTGRES_PASSWORD=${POSTGRES_PASSWORD}" \
    postgres:13

#   configure Kong
docker run --rm \
    --network=kong-net \
    -e "KONG_DATABASE=postgres" \
    -e "KONG_PG_HOST=kong-db" \
    -e "KONG_PG_PASSWORD=${POSTGRES_PASSWORD}" \
    -e "KONG_PASSWORD=test" \
    kong/kong-gateway:3.3.0.0 kong migrations bootstrap

#   run Kong containers
docker run -d --name kong-gateway \
    --network=kong-net \
    -e "KONG_DATABASE=postgres" \
    -e "KONG_PG_HOST=kong-db" \
    -e "KONG_PG_USER=${POSTGRES_USER}" \
    -e "KONG_PG_PASSWORD=${POSTGRES_PASSWORD}" \
    -e "KONG_PROXY_ACCESS_LOG=/dev/stdout" \
    -e "KONG_ADMIN_ACCESS_LOG=/dev/stdout" \
    -e "KONG_PROXY_ERROR_LOG=/dev/stderr" \
    -e "KONG_ADMIN_ERROR_LOG=/dev/stderr" \
    -e "KONG_ADMIN_LISTEN=0.0.0.0:8001" \
    -e "KONG_ADMIN_GUI_URL=http://localhost:8002" \
    -e "KONG_PORTAL_GUI_HOST=localhost:8003" \
    -e "KONG_PORTAL=on" \
    -e KONG_LICENSE_DATA \
    -p 8000:8000 \
    -p 8443:8443 \
    -p 8001:8001 \
    -p 8444:8444 \
    -p 8002:8002 \
    -p 8445:8445 \
    -p 8003:8003 \
    -p 8004:8004 \
    kong/kong-gateway:3.3.0.0

#   enable Kong Portal
curl -i -X PATCH http://localhost:8001/workspaces/default --data "config.portal=true"

#   Kong dbless
#docker run -d --name kong-dbless -v "$(pwd)/conf:/kong/declarative/" \
#    -e "KONG_DATABASE=off" \
#    -e "KONG_DECLARATIVE_CONFIG=/kong/declarative/kong.yml" \
#    -e "KONG_PROXY_ACCESS_LOG=/dev/stdout" \
#    -e "KONG_ADMIN_ACCESS_LOG=/dev/stdout" \
#    -e "KONG_PROXY_ERROR_LOG=/dev/stderr" \
#    -e "KONG_ADMIN_ERROR_LOG=/dev/stderr" \
#    -e "KONG_ADMIN_LISTEN=0.0.0.0:8001" \
#    -e "KONG_ADMIN_GUI_URL=http://localhost:8002" \
#    -e KONG_LICENSE_DATA \
#    -p 8000:8000 \
#    -p 8443:8443 \
#    -p 8001:8001 \
#    -p 8444:8444 \
#    -p 8002:8002 \
#    -p 8445:8445 \
#    -p 8003:8003 \
#    -p 8004:8004 \
#    kong/kong-gateway:3.3.0.0
