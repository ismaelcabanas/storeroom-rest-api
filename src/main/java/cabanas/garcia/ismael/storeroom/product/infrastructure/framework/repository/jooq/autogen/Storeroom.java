/*
 * This file is generated by jOOQ.
*/
package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen;


import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.Products;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.Storerooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3",
        "schema version:storeroom_2018.10.16.1"
    },
    date = "2018-10-16T19:46:42.297Z",
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Storeroom extends SchemaImpl {

    private static final long serialVersionUID = 1317338800;

    /**
     * The reference instance of <code>storeroom</code>
     */
    public static final Storeroom STOREROOM = new Storeroom();

    /**
     * Storeroom products
     */
    public final Products PRODUCTS = cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.Products.PRODUCTS;

    /**
     * Storeroom
     */
    public final Storerooms STOREROOMS = cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.Storerooms.STOREROOMS;

    /**
     * No further instances allowed
     */
    private Storeroom() {
        super("storeroom", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Products.PRODUCTS,
            Storerooms.STOREROOMS);
    }
}
