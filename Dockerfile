FROM payara/server-full:5.2021.10

ENV PAYARA_HOME=/opt/payara/appserver

WORKDIR $PAYARA_HOME

COPY lib/postgresql-42.2.6.jar $PAYARA_HOME/glassfish/domains/domain1/lib/
