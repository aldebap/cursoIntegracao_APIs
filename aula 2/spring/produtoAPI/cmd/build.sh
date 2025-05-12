#!  /usr/bin/ksh

#   color constants
export  RED='\033[0;31m'
export  GREEN='\033[0;32m'
export  LIGHTGRAY='\033[0;37m'
export  NOCOLOR='\033[0m'

#   set environment
export  VERBOSE='true'

#   function to execute the "build" target action
function buildTarget {

    echo -e "[build] ${TARGET}: ${GREEN}build target ${LIGHTGRAY}${PROJECT_TARGET}${NOCOLOR}"

    mvn clean package
}

TARGET=build
PROJECT_TARGET=produto_api
BUILD_PARAMS=

buildTarget
