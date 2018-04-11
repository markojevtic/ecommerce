### User stories

### [PDEC-1] - Create RestFul endpoint for all entities
Crete rest endpoint for all known entities, take a look into the [requirements](RequirementsSpecification.md).
For every entity we have to do following:
1. Create entity class(Customer, Contact)
2. Create service interface( CRUD methods ), not implementation (interface CustomerService)
    * load(String id)
    * insert(Customer)
    * update(Customer)
    * delete(Stirng id)
    * getAll()
3. Create rest controller(CustomerResource)    
4. Create DTO class (CustomerDto, ContactDto)
5. Create converter ( 4 converters Customer->CustomerDto, CustomerDto->Customer)
6. Exception handler ( EntityNotFound, 404)
7. Test RESTApi (CustomerRestTest)

### [PDEC-3] - Create Repository and Service
Crete repository and service implementation for all known entities, take a look into the [requirements](RequirementsSpecification.md).
For every entity we have to do following:
1. Create repository
2. Implement service interface( CRUD methods ), i.e. (class CustomerServiceImpl)
    * load(String id)
    * insert(Customer)
    * update(Customer)
    * delete(Stirng id)
    * getAll()
3. Test ServiceApi with Repository(ComponentTest)

### [PDEC-5] - Security
Intoduce securiry in our application. In order to do that we need to perform following steps:
1. Introduce User endpoint to allow creating and maintaning users.
   * create entities:
      - user( username, full_name, customer, active, List<Role> ), Role is an ENUM( ADMIN, USER )
      - authorization ( username, password )
   * create all necessary repositories, services, resources
2. Create custom authorization provider, it should use proper services to load given user, roles by username, and validate password hash. See: DummyAuthentication
3. Configure access by paths, user with role ADMIN can access all endpoints, user with role USER can access only related endpoints.
   See: securit-rest: SecurityConfiguration ( you don't need JwtAuthenticationProvider )
  
