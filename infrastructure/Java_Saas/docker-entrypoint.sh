#!/bin/sh

# Read the AUTH_SECRET from the mounted secret file
export INFISICAL_CLIENT_ID=$(cat /run/secrets/infisical_client_id)
export INFISICAL_CLIENT_SECRET=$(cat /run/secrets/infisical_client_secret)
java -Dbtc.infisical.client.id=$INFISICAL_CLIENT_ID -Dbtc.infisical.client.secret=$INFISICAL_CLIENT_SECRET -Dbtc.infisical.env="dev" -jar user_saas.jar 
exec "$@"