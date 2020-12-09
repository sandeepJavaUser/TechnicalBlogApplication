package technicalblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class TechnicalBlogApplication {

    public static void main(String [] args){
        SpringApplication.run(TechnicalBlogApplication.class, args);
    }
//1.CLOBS error :If we dont intilize values in entity class we get this and we can ignore this by
    //adding this in persistence.xml :<property name="hibernate.temp.use_jdbc_metadata_defaults" value="false"/>
    //It is not working for me but it worked for SME
    //2.SQL Exception: We used @Transient Annotation,this annotation helps not to map a specific attribute with
    // the database column, and this variable can be used as part of the application logic rather
    // than persisting in the database.
}
