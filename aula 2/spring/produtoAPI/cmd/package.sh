#!  /usr/bin/ksh

#   color constants
export  RED='\033[0;31m'
export  GREEN='\033[0;32m'
export  LIGHTGRAY='\033[0;37m'
export  NOCOLOR='\033[0m'

#   set environment
export  VERBOSE='true'

#   function to execute the "package" target action
function packageTarget {

    local   DOCKER_FLAGS=''

    echo -e "[build] ${TARGET}: ${GREEN}package the target in a Docker image${NOCOLOR}"

    if [ ! -z "${PROJECT_DOCKER_FILE}" ]
    then
        if [ "${VERBOSE}" == 'false' ]
        then
            DOCKER_FLAGS='--quiet'
        fi

        #   variable PAT must be set to user's GitHub PAT (Personal Access Token)
        echo ${PAT} | docker login ghcr.io -u ${GITHUB_USER} --password-stdin
        docker build --tag $( echo ${PROJECT_TARGET} | tr [:upper:] [:lower:] ) --file ${PROJECT_DOCKER_FILE} ${DOCKER_FLAGS} .
        docker push $( echo ${PROJECT_TARGET} | tr [:upper:] [:lower:] )
    fi
}

TARGET=package
GITHUB_USER=aldebap
PROJECT_TARGET=ghcr.io/${GITHUB_USER}/cursoIntegracao_APIs
PROJECT_DOCKER_FILE=Dockerfile

packageTarget