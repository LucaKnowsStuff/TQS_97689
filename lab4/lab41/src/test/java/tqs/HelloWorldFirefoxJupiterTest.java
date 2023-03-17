package tqs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.assertj.core.api.Assertions.assertThat;



public class HelloWorldFirefoxJupiterTest {

    private WebDriver driver;

    @BeforeAll
    static void setuoClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    void test() {
        String sutUrl = "https://weirdorconfusing.com/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        System.out.println(title);

        assertThat(title).isEqualTo("Weird or Confusing");
        
    }

    @AfterEach
    void teardown() {
        driver.quit();

    }
}

