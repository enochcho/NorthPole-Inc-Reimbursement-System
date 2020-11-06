
# Project Description
  A reimbursement system for Santa and his people. Santa and the reindeer and elves make reimbursement requests from their expenses and Mrs.Claus handles
  the requests by approving or denying them. A user can register as a worker at North Pole Inc and start submitting reimbursement requests. Mrs.Claus has 
  a financial manager account that allows her to accept or deny your request. Each user can view their own requests, past and present, while Mrs.Claus can
  see all past and present requests.

# Technologies Used
  - Java 8 
  - Apache Maven - version 3.6.3
  - JUnit - version 4.13
  - Mockito - version 3.5.13
  - PostgreSql JDBC Driver - version 42.2.18
  - Log4J - version 1.2.17
  - Commons FileUpload - version 1.4
  - JSON - version 2.11.2
  - Jacoco - version 0.8.1
# Features
  - The user is able to register as a new user and make reimbursement requests with a popup modal form that rerenders
  the table by sending the new reimbursement request and making another fetch request for the updated reimbursements. 
  - The reimbursement table is created by making a post request to the database and getting back a json object of an array of the reimbursements. 
  The array is then parsed through and organized into the table rows and cells.
  - The filter feature in the application is dynamically changing what appears through the use of DOM manipulation, thus allowing faster loading times
   because the application is not getting the requests from the server everytime. The user can filter by reimbursement status and the financial manager 
   can filter by employee as well. 
  - Two types of users: Employee and Financial Manager
    - Employee:
      - Can view their own reimbursement requests and filter by status(pending, approved, and denied). 
      - They can also make new reimbursement requests
    - Financial Manager:
      - They can view all employee's reimbursement requests, and filter by employee as well as status. 
   - To-do list:
     - Have pictures of the receipt that can be uploaded by the user and stored in the database to be viewed later.
     - Email notifications when a request is approved or denied.
# Getting Started


