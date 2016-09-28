package lotus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by flamen on 16-9-18.
 */
@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
public class Application {
    public static void main(String[] args) throws Throwable {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
