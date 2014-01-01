package hello;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    	String serverIP = "127.0.0.1";
    	String keyspace = "schema1";

    	Cluster cluster = Cluster.builder()
    	  .addContactPoints(serverIP)
    	  .build();

    	Session session = cluster.connect(keyspace);
    	
    	String cqlStatement = "SELECT * FROM users";
    	for (Row row : session.execute(cqlStatement)) {
    	  System.out.println(row.toString());
    	  System.out.println(row.getString(2));
    	  
    	}
    }
}
