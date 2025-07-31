#!/bin/sh

# Read the AUTH_SECRET from the mounted secret file
export INFISICAL_CLIENTID=$(cat /run/secrets/infisical_client_id)
export INFISICAL_SECRET=$(cat /run/secrets/infisical_client_secret)
npm run start:prod
exec "$@"