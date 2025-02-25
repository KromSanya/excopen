package excopen.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(BackApplication.class, args);
	}

}