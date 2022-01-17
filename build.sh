#!/bin/bash

nr=${1:-4}

mvn clean install -DskipTests

./redeploy.sh ${nr}
