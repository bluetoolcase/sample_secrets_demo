# Some notes about this sample
- To test java sample is working use the query `http://localhost:8091/user?email=galjoey@gmail.com`
- To test js sample is working use the query `http://localhost:8092/users?email=alice@example.com`
- Note secrets are not committed to source control
- Since this is an example the value of secrets for mongo is `example` and for `postgres` is infiscal


# Secrets Management
This sample uses infisical as a secrets management system. On first startup, infisical needs to be setup. Follow the following steps:
1. Infisical will not load if postgres and redis are not in a healthy state
2. Hit the endpoint http://localhost:8989, which will provide you an interface to create the organization.
3. Create the adminsitrator user account and main organization
4. Create a main project to test with, retrieve the project identifier and replace the project identifire `6c293bdb-b71d-4c18-8ee1-ce2739ceea14` in the code with the one created in infisical
5. Create the folder mongo and for the environment dev create two secrets
6. Name your first secret `USER` to store the username, in this sample root
7. Name your second secret `PASSWORD` to store the password, in this example the password is `example` 
8. Now you should be ready to load the two saas components! Enjoy testing around

# DISCLAIMERS
- In JavaScript sample Service , the windows batch file is for developers purpose only, you should never include the secret in the batch file as done here
- In a production environment, all services should deliver the service over https
- Communication to and from databases should also go over a secured channel.
