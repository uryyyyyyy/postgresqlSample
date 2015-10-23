
# postgresSample with Spring

## setup

```
# install
sudo apt-get install postgresql-9.3

# access
sudo su postgres
psql

# in postgreSQL
\password # add password
\q # exit

# change auth peer -> md5
sudo vim /etc/postgresql/9.3/main/pg_hba.conf
# local all  postgres md5

# login with psql
psql -U postgres # use password you set.

# create new database
create database my_database owner postgres encoding 'UTF8';

# logout
\q

# database migration
./flyway/flyway -user=postgres -password=postgres -url=jdbc:postgresql://localhost/my_database migrate

```

if you want to access from remote client

```
sudo vim /etc/postgresql/9.3/main/postgresql.conf
# enable listen_addresses = '*'

sudo vim /etc/postgresql/9.3/main/pg_hba.conf
# enable remote access as md5
# host    all             all             0.0.0.0/0            md5

```

## table1

sample for PK

## table2

sample for date type.

## table3

sample for array

## table4

sample for json

## database2

sample for transaction.
