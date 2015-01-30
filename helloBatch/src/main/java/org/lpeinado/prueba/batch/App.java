package org.lpeinado.prueba.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Hello world!
 *
 */
@ComponentScan
@EnableAutoConfiguration
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        List<Person> results = ctx.getBean(JdbcTemplate.class).query("SELECT first_name, last_name FROM people", new RowMapper<Person>() {
            
            public Person mapRow(ResultSet rs, int row) throws SQLException {
                return new Person(rs.getString(1), rs.getString(2));
            }
        });

        for (Person person : results) {
            System.out.println("Found <" + person + "> in the database.");
        }
    }
}
