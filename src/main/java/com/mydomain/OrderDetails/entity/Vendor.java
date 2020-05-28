package com.mydomain.OrderDetails.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vendor")
public class Vendor implements Serializable {

    @Id
    @Column(name = "vendorid")
    private int vendorid;
    @Column(name = "vendorname")
    private String vendorname;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderid")
    private Set<OrderDetails> orderDetails;


    public Vendor(int vendorid, String vendorname) {
        this.vendorid = vendorid;
        this.vendorname = vendorname;
    }
}
