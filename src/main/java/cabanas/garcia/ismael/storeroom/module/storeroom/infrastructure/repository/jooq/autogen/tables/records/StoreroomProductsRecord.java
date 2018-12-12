/*
 * This file is generated by jOOQ.
*/
package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.repository.jooq.autogen.tables.records;


import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.repository.jooq.autogen.tables.StoreroomProducts;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Storeroom products
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3",
        "schema version:storeroom_2018.11.19.0"
    },
    date = "2018-12-10T05:57:40.531Z",
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StoreroomProductsRecord extends UpdatableRecordImpl<StoreroomProductsRecord> implements Record6<UUID, UUID, String, Timestamp, Timestamp, Integer> {

    private static final long serialVersionUID = -1488516797;

    /**
     * Setter for <code>storeroom.storeroom_products.sp_id</code>. Private product identifier
     */
    public void setSpId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>storeroom.storeroom_products.sp_id</code>. Private product identifier
     */
    public UUID getSpId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>storeroom.storeroom_products.sp_storeroom_id</code>. Storeroom identifier
     */
    public void setSpStoreroomId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>storeroom.storeroom_products.sp_storeroom_id</code>. Storeroom identifier
     */
    public UUID getSpStoreroomId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>storeroom.storeroom_products.sp_name</code>. NAME OF the product
     */
    public void setSpName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>storeroom.storeroom_products.sp_name</code>. NAME OF the product
     */
    public String getSpName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>storeroom.storeroom_products.sp_creation</code>. Time instant when it is created
     */
    public void setSpCreation(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>storeroom.storeroom_products.sp_creation</code>. Time instant when it is created
     */
    public Timestamp getSpCreation() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>storeroom.storeroom_products.sp_modification</code>. Instant of the last update
     */
    public void setSpModification(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>storeroom.storeroom_products.sp_modification</code>. Instant of the last update
     */
    public Timestamp getSpModification() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>storeroom.storeroom_products.sp_stock</code>. Product stock
     */
    public void setSpStock(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>storeroom.storeroom_products.sp_stock</code>. Product stock
     */
    public Integer getSpStock() {
        return (Integer) get(5);
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
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<UUID, UUID, String, Timestamp, Timestamp, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<UUID, UUID, String, Timestamp, Timestamp, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field1() {
        return StoreroomProducts.STOREROOM_PRODUCTS.SP_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field2() {
        return StoreroomProducts.STOREROOM_PRODUCTS.SP_STOREROOM_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return StoreroomProducts.STOREROOM_PRODUCTS.SP_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return StoreroomProducts.STOREROOM_PRODUCTS.SP_CREATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return StoreroomProducts.STOREROOM_PRODUCTS.SP_MODIFICATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return StoreroomProducts.STOREROOM_PRODUCTS.SP_STOCK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value1() {
        return getSpId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value2() {
        return getSpStoreroomId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSpName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getSpCreation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getSpModification();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getSpStock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreroomProductsRecord value1(UUID value) {
        setSpId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreroomProductsRecord value2(UUID value) {
        setSpStoreroomId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreroomProductsRecord value3(String value) {
        setSpName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreroomProductsRecord value4(Timestamp value) {
        setSpCreation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreroomProductsRecord value5(Timestamp value) {
        setSpModification(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreroomProductsRecord value6(Integer value) {
        setSpStock(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreroomProductsRecord values(UUID value1, UUID value2, String value3, Timestamp value4, Timestamp value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StoreroomProductsRecord
     */
    public StoreroomProductsRecord() {
        super(StoreroomProducts.STOREROOM_PRODUCTS);
    }

    /**
     * Create a detached, initialised StoreroomProductsRecord
     */
    public StoreroomProductsRecord(UUID spId, UUID spStoreroomId, String spName, Timestamp spCreation, Timestamp spModification, Integer spStock) {
        super(StoreroomProducts.STOREROOM_PRODUCTS);

        set(0, spId);
        set(1, spStoreroomId);
        set(2, spName);
        set(3, spCreation);
        set(4, spModification);
        set(5, spStock);
    }
}