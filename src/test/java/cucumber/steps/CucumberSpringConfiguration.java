package cucumber.steps;

import com.pokemon.demo.PokemonDemoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(
  classes = PokemonDemoApplication.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("h2")
public class CucumberSpringConfiguration {

}