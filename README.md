# Customers

A basic Mule application to provide a simple API design and implementation for creating, listing, deleting and editing customers

### Design decisions

During the development I tried to choose the simplest solution for the API design because of the limited time
and since the project being a proof of concept. Instead of creating a deep and thorough implementation I focused on showing that the application 
can match all the criteries which were mentioned in the description including DataWeave, MUnit tests and error handling for the Mulesoft. 
And best REST API design practices and using of HTTP protocols for the RAML design. 

* The API design follows the standard REST concepts
* For simplicity MuleSoft object store was chosen as the datastore
* For testing MUnit Java is used, because I was more familiar with the syntax
* Some field names in the customers/customerId:get output payload were changed using DataWeave to help the user readability
