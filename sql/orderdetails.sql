DROP TABLE IF EXISTS vendor;
create table vendor
(
  vendorid   int primary key,
  vendorname varchar(100)
);
insert into vendor
values (1, 'amazon');
insert into vendor
values (2, 'flipkart');
insert into vendor
values (3, 'ebay');
DROP TABLE IF EXISTS orders;
create table orders
(
  orderid     int not null,
  vendorid    int not null,
  orderamount int,
  orderdate   date,
  dueperiod   int,
  PRIMARY KEY (orderid),
  FOREIGN KEY (vendorid) REFERENCES vendor (vendorid)
);
INSERT into orders
values (1, 1, 100, '2018-10-20', 30);
INSERT into orders
values (2, 2, 100, '2018-09-20', 15);
INSERT into orders
values (3, 1, 100, '2018-07-20', 25);
INSERT into orders
values (4, 1, -250, '2018-01-07', 45);
INSERT into orders
values (5, 2, 450, '2018-03-09', 30);
INSERT into orders
values (6, 3, 78, '2018-05-05', 30);




