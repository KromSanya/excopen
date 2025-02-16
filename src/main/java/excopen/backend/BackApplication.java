package excopen.backend;

import com.pgvector.PGvector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(BackApplication.class, args);
	}

}

