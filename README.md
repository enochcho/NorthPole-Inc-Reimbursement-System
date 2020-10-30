# RevatureProject1
Project 1 for Revature, a Reimbursement System
  -THEME: A reimbursement system for Santa and his people. Santa and the reindeer and elves make reimbursement requests from their expenses and Mrs.Claus handles
  the requests by approving or denying them. 
  -The user is able to register as a new user and make reimbursement requests with a popup modal form that rerenders
  the table by sending the new reimbursement request and making another fetch request for the updated reimbursements. 
  The reimbursement table is created by making a post request to the database and getting back a json object of an array of the reimbursements. 
  The array is then parsed through and organized into the table rows and cells. 
  -Two types of users: Employee and Financial Manager
    - Employee:
      - Can view their own reimbursement requests and filter by status(pending, approved, and denied). 
      - They can also make new reimbursement requests
    - Financial Manager:
      - They can view all employee's reimbursement requests, and filter by employee as well as status. 
-One thing of note is that the user can filter the table by the reimbursement status, and if the user is a financial manager, 
 they can also filter by employee. The filters actually don't make new requests, they just display the table rows with the filter specifications. 
 The filter is applied with the class name when the table is first rendered. 
  - Basically, the table is rendered when an update is made or when the page is first loaded. Other than that, the page is hiding and showing 
    the rows of the table to match the filter. 

