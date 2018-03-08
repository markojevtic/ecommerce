## Code Guidelines

### Package organisation

* package should be organised by layers:
    * `com.prodyna.ecommerce.server` - main package
        * `exception` - exceptions
        * `repository` - repository layer
            * `entity` - entities
            * `impl` - repository implementation
        * `services` - service layer
            * `impl`
        *  `resource` - controllers
            * `dto` - dto
            * `converter` - converters Dto to entity and                   

### Naming 
* Class/Interface naming:
    * Entities, just name of entity without any suffix( i.e. `Customer, User, ...` )
    * Repository components with suffix Repository ( i.e. `CustomerRepository` )
    * Service component with suffix Service ( i.e. `CustomerService` )
    * Interface implementation with Impl ( i.e. CustomerServiceImpl)
    * Rest controllers with suffix Resource ( i.e. `CustomerResource` )
    * DTO classes with suffix Dto (i.e. `CustomerDto` )
    * Unit test with suffix UnitTest ( i.e. `CustomerServiceImplUnitTest` )
* Methods, variables, etc
    * we should use default java convention plus clean code advices:
        * avoid magic numbers
        * fields and variable should be self-explanatory
        * method should be should be self-explanatory
        * method should not be too long, if it's, break it in a few smaller
        * write comment only if it's needed, try to avoid dummy comments
        * test method should be named by case, and should test only one case.
        * Follow SOLID principles and etc.     