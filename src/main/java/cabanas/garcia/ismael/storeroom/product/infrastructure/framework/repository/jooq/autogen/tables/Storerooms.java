/*
 * This file is generated by jOOQ.
*/
package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables;


import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.Keys;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.Storeroom;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.records.StoreroomsRecord;

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
 * Storeroom
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
public class Storerooms extends TableImpl<StoreroomsRecord> {

    private static final long serialVersionUID = 58306654;

    /**
     * The reference instance of <code>storeroom.storerooms</code>
     */
    public static final Storerooms STOREROOMS = new Storerooms();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StoreroomsRecord> getRecordType() {
        return StoreroomsRecord.class;
    }

    /**
     * The column <code>storeroom.storerooms.s_id</code>. Private storeroom identifier
     */
    public final TableField<StoreroomsRecord, UUID> S_ID = createField("s_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "Private storeroom identifier");

    /**
     * The column <code>storeroom.storerooms.s_name</code>. Name of the storeroom
     */
    public final TableField<StoreroomsRecord, String> S_NAME = createField("s_name", org.jooq.impl.SQLDataType.VARCHAR.length(25).nullable(false), this, "Name of the storeroom");

    /**
     * Create a <code>storeroom.storerooms</code> table reference
     */
    public Storerooms() {
        this("storerooms", null);
    }

    /**
     * Create an aliased <code>storeroom.storerooms</code> table reference
     */
    public Storerooms(String alias) {
        this(alias, STOREROOMS);
    }

    private Storerooms(String alias, Table<StoreroomsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Storerooms(String alias, Table<StoreroomsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Storeroom");
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
    public UniqueKey<StoreroomsRecord> getPrimaryKey() {
        return Keys.PK_STOREROOMS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StoreroomsRecord>> getKeys() {
        return Arrays.<UniqueKey<StoreroomsRecord>>asList(Keys.PK_STOREROOMS, Keys.UQ_STOREROOMS_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Storerooms as(String alias) {
        return new Storerooms(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Storerooms rename(String name) {
        return new Storerooms(name, null);
    }
}
