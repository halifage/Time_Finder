import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TimeFinder {

    private static String location;

    public static void main(String[] args) throws Exception{

        System.setProperty("webdriver.chrome.driver", "//home//halif//chromedriver_linux64//chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        // Get user to input city/country for which to display current time
        while (true){
            try{
                System.out.println("Please enter a city/country: ");
                Scanner s = new Scanner(System.in);
                location = s.nextLine().trim().toLowerCase();
                break;
            }catch (Exception e){
                System.out.println("Invalid");
                continue;
            }
        }

        // open browser with time site
        driver.get("https://time.is/");

        // find the search box and type the city/country provided by user
        driver.findElement(By.cssSelector("input[type='text'][id='q']")).sendKeys(location);

        // press ENTER to search
        driver.findElement(By.cssSelector("input[type='text'][id='q']")).sendKeys(Keys.ENTER);

        // get time displayed and save it
        String time = driver.findElement(By.cssSelector("#twd")).getText();

        // print the time found
        System.out.println("The current time in " + location.toUpperCase() + " is: ");
        System.out.printf("%s:%s", time.substring(0,2), time.substring(3,5));

        //close the browser
        driver.close();

    }
}
