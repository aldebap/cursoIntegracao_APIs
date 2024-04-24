#!  /usr/bin/ksh

#   cria um upstream
curl -i -X POST localhost:8001/upstreams --data '{ "name": "Upstream-Hello", "algorithm": "round-robin", "tags": ["user-level", "low-priority"], "host_header": "hello.io", "use_srv_name": false }'

#   cria o servico Hello
curl -i -X POST localhost:8001/services --data '{ "name": "Hello 1", "url": "http://192.168.68.112:8080/api/v1/hello", "enabled": true }'
curl -i -X POST localhost:8001/services --data '{ "name": "Hello 2", "url": "http://192.168.68.112:8088/api/v1/hello", "enabled": true }'

#   cria a rota Hello
