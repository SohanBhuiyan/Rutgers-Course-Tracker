package MainFile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sniper implements Runnable {
    String url;
    Course currentCourse;
    public Sniper(Course c,String s) {
        url = s;
        currentCourse = c;
    }
    @Override
    public void run() {
        try {
            snipe(url);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public  void snipe(String url) throws InterruptedException {
        boolean open = false;
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        while(open == false) {
            driver.get(url);
            WebElement element;
            try {
                element = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("courseOpenSectionsNumeratorZero")));
            }catch (Exception e) { // RU course page changes class to the one listed below when class is open.
                element = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("courseOpenSectionsNumerator")));
            }


            if(!element.getText().equals("0")) {
                open = true;
                break;
            }
            Thread.sleep(5000);
        }
        // auto register
        currentCourse.setStatus("Open!");
        driver.close();
        autoRegistor();
        currentCourse.controller.updateListView();

    }


    public void autoRegistor() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String user = currentCourse.getUsername();
        String pass = currentCourse.getPassword();

        driver.get("https://sims.rutgers.edu/webreg/editSchedule.htm");
        try {
            // will execute if the page requires you to sign in.
            WebElement usernameTab = (new WebDriverWait(driver, 5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"username\"]")));
            WebElement passwordTab = (new WebDriverWait(driver, 5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]")));
            usernameTab.sendKeys(user);
            passwordTab.sendKeys(pass);
            passwordTab.submit();

            WebElement semesterSubmitButton = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"submit\"]")));
            semesterSubmitButton.submit();
            // finally in the section signup page.
            WebElement signInTab = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"i1\"]")));
            signInTab.sendKeys(currentCourse.getSection());
            signInTab.submit();

        }catch (Exception e) {
            //////////////////////In case you are already signed into your account//////////////////////////////////
            WebElement signInTab = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"i1\"]")));
            signInTab.sendKeys(currentCourse.getSection());
            signInTab.submit();
        }

    }

}
