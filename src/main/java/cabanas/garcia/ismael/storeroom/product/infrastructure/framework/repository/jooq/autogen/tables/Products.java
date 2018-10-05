/*
 * This file is generated by jOOQ.
*/
package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables;


import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.Keys;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.Storeroom;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.records.ProductsRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Products extends TableImpl<ProductsRecord> {

    private static final long serialVersionUID = 1166196757;

    /**
     * The reference instance of <code>storeroom.products</code>
     */
    public static final Products PRODUCTS = new Products();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProductsRecord> getRecordType() {
        return ProductsRecord.class;
    }

    /**
     * The column <code>storeroom.products.p_id</code>. Private product identifier
     */
    public final TableField<ProductsRecord, UUID> P_ID = createField("p_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "Private product identifier");

    /**
     * The column <code>storeroom.products.p_name</code>. Name of the product
     */
    public final TableField<ProductsRecord, String> P_NAME = createField("p_name", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "Name of the product");

    /**
     * Create a <code>storeroom.products</code> table reference
     */
    public Products() {
        this("products", null);
    }

    /**
     * Create an aliased <code>storeroom.products</code> table reference
     */
    public Products(String alias) {
        this(alias, PRODUCTS);
    }

    private Products(String alias, Table<ProductsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Products(String alias, Table<ProductsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Storeroom products");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Storeroom.STOREROOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ProductsRecord> getPrimaryKey() {
        return Keys.PK_PRODUCTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ProductsRecord>> getKeys() {
        return Arrays.<UniqueKey<ProductsRecord>>asList(Keys.PK_PRODUCTS, Keys.UQ_PRODUCTS_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Products as(String alias) {
        return new Products(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Products rename(String name) {
        return new Products(name, null);
    }
}