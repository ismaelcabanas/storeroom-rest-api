/*
 * This file is generated by jOOQ.
*/
package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.records;


import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.Products;

import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Storeroom products
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3",
        "schema version:storeroom_2018.08.02.1"
    },
    date = "2018-10-03T21:54:39.893Z",
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProductsRecord extends UpdatableRecordImpl<ProductsRecord> implements Record2<UUID, String> {

    private static final long serialVersionUID = 757515058;

    /**
     * Setter for <code>storeroom.products.p_id</code>. Private product identifier
     */
    public void setPId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>storeroom.products.p_id</code>. Private product identifier
     */
    public UUID getPId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>storeroom.products.p_name</code>. Name of the product
     */
    public void setPName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>storeroom.products.p_name</code>. Name of the product
     */
    public String getPName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UUID, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UUID, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field1() {
        return Products.PRODUCTS.P_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Products.PRODUCTS.P_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value1() {
        return getPId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getPName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductsRecord value1(UUID value) {
        setPId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductsRecord value2(String value) {
        setPName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductsRecord values(UUID value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProductsRecord
     */
    public ProductsRecord() {
        super(Products.PRODUCTS);
    }

    /**
     * Create a detached, initialised ProductsRecord
     */
    public ProductsRecord(UUID pId, String pName) {
        super(Products.PRODUCTS);

        set(0, pId);
        set(1, pName);
    }
}