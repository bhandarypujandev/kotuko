
## Obtaining JWT for ADMIN User

To access certain APIs with administrative privileges, you need to obtain a JWT (JSON Web Token). Follow these steps to obtain a JWT for the ADMIN user:

1. Use the following credentials for the ADMIN user:
- Username: pujan
- Password: pujan

2. Authenticate by making a POST request to the authentication endpoint of the API, providing the username and password in the request body. This will return a JWT.

3. Include the obtained JWT in the `Authorization` header of your requests to access the delete API.

## User Authorization

- The ADMIN user, with the credentials provided above, is authorized to use the delete API.

- All other users created within the system have permissions to perform all actions except delete.

Please make sure to handle the JWT and user authorization accordingly when interacting with the APIs.
