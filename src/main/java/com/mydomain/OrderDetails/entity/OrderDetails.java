package com.mydomain.OrderDetails.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class OrderDetails implements Serializable, Comparator<OrderDetails> {
    @Id
    @Column(name = "orderid")
    private int orderid;


    @Override
    public int compare(OrderDetails o1, OrderDetails o2) {
        if (o1.vendor.getVendorid() > o2.vendor.getVendorid()) {
            return -1;
        } else if (o1.vendor.getVendorid() < o2.vendor.getVendorid()) {
            return 1;
        } else
            return 0;

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

    @ManyToOne
    @JoinColumn(name = "vendorid")
    private Vendor vendor;

    @Column(name = "orderamount")
    private int orderamount;
    @Column(name = "orderdate")
    private Date orderdate;
    @Column(name = "dueperiod")
    private int dueperiod;

}
