#! /bin/bash
set -e
set -u

function create_database(){
  local database=$1
  echo "Creating the database ${database}"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER"
    CREATE DATABASE "${database}";
  EOSQL
}
