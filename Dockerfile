FROM postgres:latest

ENV POSTGRES_USER gris
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB cerberus

COPY init.sql /docker-entrypoint-initdb.d/