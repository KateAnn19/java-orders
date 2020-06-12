package local.kmcgeeka.javaorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//(scanBasePackages = ("kmcgeeka.javaorders"))
//@EntityScan("kmcgeeka.javaorders")

public class JavaordersApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(JavaordersApplication.class,
            args);
    }

}
