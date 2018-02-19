import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mc")
public class ConnectCitiesApp {
    public static void main(String[] args) {
        SpringApplication.run(ConnectCitiesApp.class, args);
    }

}
