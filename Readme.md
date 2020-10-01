### Running the app
```shell script
docker-compose -f src/test/resources/docker-compose.yml up -d
mvn spring-boot:run
```
There is a postman collection in src/main/resources/employee hiearchy.postman_collection.json
### Todo's
There are a lot of todo's
- [ ] Beautify the json retrieved in both endpoints, it does not match the specification, you can use jackson annotations for that 
- [ ] Check for loops or multiple roots in the request body when creating the hierarchy, the first check could be to check if any name in the request is inverted, ie a:b and b:a in the same request
- [ ] build the app as a container to ease the way of deploying it and using just docker-compose to execute it locally
- [x] Remove the hardcoded default credentials and use env variables to inject them
- [ ] Test w/ an integration test the query of the supervisors
- [ ] Review the //FIXME comments 
- [ ] Use a RestHandlerAdvice to map custom exceptions to RESTfull codes and comprehensive error message