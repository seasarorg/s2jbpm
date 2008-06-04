package org.seasar.jbpm.settings;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.seasar.extension.unit.S2TestCase;

/**
 * JDBC の動作確認。
 * 
 * @author glad
 */
public class JdbcTest extends S2TestCase {

    DataSource dataSource;

    protected void setUp() throws Exception {
        include("j2ee.dicon");
    }

    public void testJdbcTx() throws SQLException {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
        try {
            Statement stmt = connection.createStatement();
            assertNotNull(stmt);
            try {
                ResultSet resultSet = stmt.executeQuery(
                        "SELECT CURRENT_TIMESTAMP FROM DUAL");
                assertNotNull(resultSet);
                boolean hasNext = resultSet.next();
                assertTrue(hasNext);
                Timestamp timestamp = resultSet.getTimestamp(1);
                assertNotNull(timestamp);
                assertFalse(resultSet.next());
            } finally {
                stmt.close();
            }
        } finally {
            connection.close();
        }
    }

}
