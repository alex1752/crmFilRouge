FROM postgres:13

ENV POSTGRES_DB=crm
ENV POSTGRES_USER=crm
ENV POSTGRES_PASSWORD=crm12345
ENV POSTGRES_PORT=5432

COPY ./create_db.sql /docker-entrypoint-initdb.d/create_db.sql