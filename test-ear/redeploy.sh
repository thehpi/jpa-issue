#!/bin/bash

docker exec payara bash -c 'asadmin --passwordfile=<(echo "AS_ADMIN_PASSWORD=admin") undeploy test-jpa-issue'

docker cp target/test-jpa-issue.ear payara:/tmp/
docker exec payara bash -c 'asadmin --passwordfile=<(echo "AS_ADMIN_PASSWORD=admin") deploy /tmp/test-jpa-issue.ear'
