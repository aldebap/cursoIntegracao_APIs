#!  /usr/bin/ksh

#   cria o servico Produtos
PAYLOAD=$( cat <<JSON
{
    "name": "Produtos",
    "url": "http://192.168.68.112:8080/api/v1/produto",
    "enabled": true
}
JSON
)

curl -i -X POST localhost:8001/services --data ${PAYLOAD}
