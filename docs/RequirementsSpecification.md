## ECommerce requirements specification

### General overview 

We need a simply ecommerce(B2B) solution to support our AM team. They want to allow our customers to create order and associate it
with a cost center. When customer create order, our AM should be able to see the order, and process it. We don't need a payment mechanism.
In order to aim it, we should cover manipulation of following data:  
* customer - data about our customers(business partners) 
* product - data about products(name, category, etc) 
* ordering - information about order, who did make order, what did it order.
* other helpers data - categories, cost centers, etc.

### Main use case

When customer come to our ecommerce portal, it performs following steps:
1. Login - type credentials, and login successfully.
2. List products - customer should be able to see available list of products.
3. Ordering - put products to the basket.
4. Order finalization - complete order, chose cost center
5. When order is finalized, system should notify our AM team with email(later create ticket)

### Entities
As it's mentioned above we should allow manipulation with following entity data
##### Customer 
* customerId
* name
* address
* contacts(List)
    * name
    * email
    * phone
* billing addresses(List)
    * City
    * Zip
    * address(street&number)
* cost center
* active (true/false)

##### CostCenter
* costCenterId
* name
* address
* contacts(List)
      * name
      * email
      * phone

##### Category
* categoryId
* name

##### Product
* productId
* name
* description
* photoUrl
* category
* active (true/false)

##### Order
* orderId
* orderDate
* customer
* orderStatus( new, accepted, dismissed, ordered, delivered )
* orderDetails(List)
    * ordinal
    * product
    * price
    * quantity
    * amount
    
##### ShopCart
TBD    

