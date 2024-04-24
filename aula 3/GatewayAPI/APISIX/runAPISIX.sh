#!  /usr/bin/sh

#docker-compose -p docker-compose.yml up -d
#docker-compose up -d

docker run -d --name dashboard -p 9080:9080 -v ./conf/conf.yml:/usr/local/apisix-dashboard/conf/conf.yaml apache/apisix-dashboard
#docker run -d --name dashboard -p 9000:9000 apache/apisix-dashboard
