#!  /usr/bin/sh

#   run Postgres and Kong containers
docker run -d --name kong-db \
    --network=kong-net \
    -p 5432:5432 \
    -e "POSTGRES_USER=kong" \
    -e "POSTGRES_DB=kong" \
    -e "POSTGRES_PASSWORD=kongpass" \
    postgres:13

docker run -d --name kong-gateway \
    --network=kong-net \
    -e "KONG_DATABASE=postgres" \
    -e "KONG_PG_HOST=kong-db" \
    -e "KONG_PG_USER=kong" \
    -e "KONG_PG_PASSWORD=kongpass" \
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
