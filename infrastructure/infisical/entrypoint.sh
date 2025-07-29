#!/bin/sh

# Read the AUTH_SECRET from the mounted secret file
export ROOT_ENCRYPTION_KEY=$(cat /run/secrets/infisical_root_encryption_key)
export AUTH_SECRET=$(cat /run/secrets/infisical_auth_secret)
export DB_CONNECTION_URI=$(cat /run/secrets/infisical_db_connection_uri)

# Execute the original Infisical entrypoint command
# You'll need to find the actual command Infisical uses to start.
# It's often something like "npm run start" or a direct executable.
# Check Infisical's Dockerfile or documentation for its default CMD or ENTRYPOINT.
/backend/./standalone-entrypoint.sh
exec "$@"