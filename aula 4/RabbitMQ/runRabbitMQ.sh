#!  /usr/bin/sh

#   run RabbitMQ container
#docker run -d --hostname local.rabbit --name rabbitMQ rabbitmq:3
docker run -d --rm --name rabbitMQ -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
