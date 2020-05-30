# OrderUpdate
This is implementation to update order details based on order amount per vendor , if order amount goes negative update date to negative value date .
Table structure :
 
Vendor table
=======================
vendorID |  vendorName
=======================
1           amazon
2           flipkart
3           ebay
========================

Order table

====================================================
OrderId | VendorId |OrderAmount |orderDate |duePeriod
====================================================
  1	        1	      100	          2018-10-20	30
  2	        2	      100	          2018-09-20	15
  3	        1	      100	          2018-07-20	25
  4	        1	      -250	        2018-01-07	45
  5	        2	      450	          2018-03-09	30
  6	        3	      78	          2018-05-05	30  
  
  As per above order table , if orders are sorted by vendorID  and resulting orderAmounts will be negative value upon addition of all order with same vendor id .
  Update orderDate with last added orderAmount orderdate with addition of duePeriod.

Rest API call - 

http://localhost:9090/updateOrders

O/P:
 Order Updated Successfully .. ! 
 {"orderid":1,"vendor":{"vendorid":1,"vendorname":"amazon"},"orderamount":100,"orderdate":1526495400000,"dueperiod":30}{"orderid":3,"vendor":{"vendorid":1,"vendorname":"amazon"},"orderamount":100,"orderdate":1526063400000,"dueperiod":25}
 
 mvn flyway:clean
 baseline, clean, info, migrate, repair, undo, validate 