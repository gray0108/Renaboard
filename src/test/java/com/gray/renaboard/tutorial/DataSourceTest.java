package com.gray.renaboard.tutorial;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class DataSourceTest {

    @Inject
    private DataSource dataSource;

    @Test
    public void testConnection() throws Exception {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
