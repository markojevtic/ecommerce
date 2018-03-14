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