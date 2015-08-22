package org.fao.fenix.faostat.beans;

import junit.framework.TestCase;
import org.fao.fenix.faostat.constants.DRIVER;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 * */
public class TestDatasourceBean extends TestCase {

    private DatasourceBean b;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        b = new DatasourceBean();
    }

    public void testSetters() {
        b.setId("id");
        assertEquals("id", b.getId());
        b.setPassword("password");
        assertEquals("password", b.getPassword());
        b.setUsername("username");
        assertEquals("username", b.getUsername());
        b.setDbName("dbName");
        assertEquals("dbName", b.getDbName());
        b.setUrl("url");
        assertEquals("url", b.getUrl());
        b.setCreate(true);
        assertEquals(true, b.isCreate());
        b.setDelete(true);
        assertEquals(true, b.isDelete());
        b.setDriver(DRIVER.SQLSERVER2000);
        assertEquals(DRIVER.SQLSERVER2000, b.getDriver());
        b.setRetrieve(true);
        assertEquals(true, b.isRetrieve());
        b.setUpdate(true);
        assertEquals(true, b.isRetrieve());
    }

}