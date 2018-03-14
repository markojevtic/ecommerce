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
            * `converter` - converters Dto to entity and vice versa                  

### Naming Class/Interface naming:
* Entities, just name of entity without any suffix( i.e. `Customer, User, ...` )
* Repository components with suffix Repository ( i.e. `CustomerRepository` )
* Service component with suffix Service ( i.e. `CustomerService` )
* Interface implementation with Impl ( i.e. CustomerServiceImpl)
* Rest controllers with suffix Resource ( i.e. `CustomerResource` )
* DTO classes with suffix Dto (i.e. `CustomerDto` )
* Unit test with suffix UnitTest ( i.e. `CustomerServiceImplUnitTest` )

### Methods, variables, etc
Generally we should use default java convention and clean code principles. 
But here are a few advices:
* fields and variable should be self-explanatory 
    * i.e: `lastName`, `customer`, `billingAddress` instead of `lName`, `c`, `bAdd`
* usage of abbreviation is OK but only for well known(general or domain)
    * SMS, HTTP, VAT(value added tax), etc.     
* name with abbreviation only first letter should be capital (i.e. `String smsMessage`, `void sendSms()`,  `class SmsTemplate`)  
* avoid magic numbers/words instead of them use variables, constants
* method should be self-explanatory
* method implementation should not be too long, if it's, break it in a few smaller
* write comment only if it's needed, try to avoid dummy comments
    * i.e. `String lastName; //The last name`.
* test method should be named by case, and should test only one case.
* Just write clean code ;)