package com.manniwood.pgtypes.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.PGConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.manniwood.pgtypes.MD5Hash;

public class MD5HashTest {
    private final static Logger log = LoggerFactory.getLogger(MD5HashTest.class);

    @Test
    public void test() throws ClassNotFoundException, SQLException {
        log.info("Hello");
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost/testdb?user=testuser&password=testuser";
        Connection conn = DriverManager.getConnection(url);
        PGConnection pgConn = (PGConnection) conn;
        pgConn.addDataType(MD5Hash.TYPE_NAME, MD5Hash.class);
        conn.setAutoCommit(false);

        // make a table for us to put values in
        Statement st = conn.createStatement();
        st.execute("create temporary table foo (md5_sum md5hash)");
        st.close();
        conn.commit();

        MD5Hash md5Hash = new MD5Hash("6cd3556deb0da54bca060b4c39479839");
        // put a value in it
        PreparedStatement ps = conn.prepareStatement("insert into foo (md5_sum) values (?)");
        ps.setObject(1, md5Hash);
        ps.execute();
        ps.close();
        conn.commit();

        MD5Hash foundMD5Hash = null;
        PreparedStatement ps2 = conn.prepareStatement("select md5_sum from foo limit 1");
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            foundMD5Hash = (MD5Hash) rs.getObject("md5_sum");
        }
        rs.close();
        ps2.close();
        conn.rollback();  // just a select; no need to commit
        log.info("Found: {}", foundMD5Hash);

        Assert.assertEquals(foundMD5Hash, md5Hash);

        conn.close();
    }
}
