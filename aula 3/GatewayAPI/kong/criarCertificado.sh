#!  /usr/bin/sh

#   create a certificate for localhost
openssl req -x509 -newkey rsa:2048 -sha256 -nodes -days 365 -keyout certificate/local-kong.key -out certificate/local-kong.crt

#   add the cert to your keychain
open certificate/local-kong.crt
