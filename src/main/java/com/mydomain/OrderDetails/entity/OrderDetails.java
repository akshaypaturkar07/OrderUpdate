package com.mydomain.OrderDetails.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class OrderDetails implements Serializable {
    @Id
    @Column(name = "orderid")
    private int orderid;


    @ManyToOne
    @JoinColumn(name = "vendorid")
    private Vendor vendor;

    @Column(name = "orderamount")
    private int orderamount;
    @Column(name = "orderdate")
    private Date orderdate;
    @Column(name = "dueperiod")
    private int dueperiod;

    public OrderDetails(int orderid, Vendor vendor, int orderamount, Date orderdate, int dueperiod) {
        this.orderid = orderid;
        this.vendor = vendor;
        this.orderamount = orderamount;
        this.orderdate = orderdate;
        this.dueperiod = dueperiod;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return getVendor().equals(that.getVendor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVendor());
    }

}
