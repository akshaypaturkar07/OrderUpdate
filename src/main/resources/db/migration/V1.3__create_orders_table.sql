create table orders
(
  orderid     int not null ,
  vendorid    int not null ,
  orderamount int,
  orderdate   date,
  dueperiod   int,
  PRIMARY KEY (orderid),
  FOREIGN KEY (vendorid) REFERENCES vendor (vendorid)
);