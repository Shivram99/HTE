package com.tcs.sgv.common.valueobject;

// Generated Jun 9, 2007 12:04:11 PM by Hibernate Tools 3.2.0.beta8

/**
 * Payment generated by hbm2java
 */
public class Payment implements java.io.Serializable
{

    // Fields    

    private PaymentId id;


    // Constructors

    /** default constructor */
    public Payment()
    {
    }


    /** full constructor */
    public Payment(PaymentId id)
    {
        this.id = id;
    }


    // Property accessors
    public PaymentId getId()
    {
        return this.id;
    }


    public void setId(PaymentId id)
    {
        this.id = id;
    }

}
