package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;
import pages.Bookingdotcom;
import pages.Trivago;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Cucumber.class)
public class StepDefinition {

	WebDriver driver;
	WebDriverWait Wait;
	Actions actions;
	JSONObject jsonobject;
	JSONParser jsonParser;
	FileReader reader;
	Random randomGenerator;

	@Before
	public void open_ChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "src//main//java//chromedriver.exe");
		driver = new ChromeDriver();
		Wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		jsonobject = new JSONObject();
		jsonParser = new JSONParser();
		randomGenerator = new Random();
		try {
			reader = new FileReader("src//main//java//json//input.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Given("^Navigate to Trivago Web Application$")
	public void Navigate_to_Trivago_Web_Application() throws Throwable {
		driver.get("https://www.trivago.in/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@When("^User clicks on Login Button$")
	public void user_clicks_on_login_button() throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.loginBtn));
		driver.findElement(Trivago.loginBtn).click();
	}

	@When("^User clicks Register Button$")
	public void user_clicks_register_button() throws Throwable {
		driver.findElement(Trivago.registerEmailSubmitBtn).click();
		
	}

	@When("^User provides all the necessary Details$")
	public void user_provides_all_the_necessary_details() throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.selectatopic));
		Select errortype = new Select(driver.findElement(Trivago.selectatopic));
		errortype.selectByValue("Incorrect hotel");
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.errorSubjectTxt));
		driver.findElement(Trivago.errorSubjectTxt).sendKeys("Test Subject");
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.errorDescriptionTxt));
		driver.findElement(Trivago.errorDescriptionTxt).sendKeys("For Testing purpose");
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.errorHotelNameTxt));
		driver.findElement(Trivago.errorHotelNameTxt).sendKeys("Hotel Test");
		WebElement fileInput = driver.findElement(By.name("uploadfile"));
		fileInput.sendKeys("src//main//java//Test.jpg");
	}

	@When("^User searches hotels in \"([^\"]*)\" with the checkin, checkout dates and Number of adults \"([^\"]*)\"$")
	public void user_searches_hotels_in_something_with_the_checkin_checkout_dates_and_number_of_adults_something(
			String strArg1, String strArg2) throws Throwable {
		driver.findElement(Trivago.searchBox).sendKeys(strArg1);
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.firstsuggestDrop));
		driver.findElement(Trivago.firstsuggestDrop).click();
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.cookiesOkBtn));
		driver.findElement(Trivago.cookiesOkBtn).click();
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.monthheaderText));
		jsonobject = (JSONObject) jsonParser.parse(reader);
		String checkin = jsonobject.get("checkin").toString();
		String checkout = jsonobject.get("checkout").toString();
		for (int i = 0; i < 10; i++) {
			String text = driver.findElement(Trivago.monthheaderText).getText();
			System.out.println(text);
			if (text.contains("December")) {
				driver.findElement(By.xpath("//*[@class='cal-month']//time[@datetime='" + checkin + "']")).click();
				break;
			} else {
				driver.findElement(Trivago.previousMonth).click();
			}
		}
		Wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@class='cal-month']//time[@datetime='" + checkout + "']")));
		driver.findElement(By.xpath("//*[@class='cal-month']//time[@datetime='" + checkout + "']")).click();
		Thread.sleep(3000);
		driver.findElement(Trivago.noOfAdultTxt).click();
		driver.findElement(Trivago.noOfAdultTxt).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.findElement(Trivago.noOfAdultTxt).sendKeys(strArg2);
		driver.findElement(Trivago.roomdetailsApplyBtn).click();
		driver.findElement(Trivago.searchBtn).click();
	}

	@When("^The user sort hotels list with guest rating more than \"([^\"]*)\"$")
	public void the_user_sort_hotels_list_with_guest_rating_more_than_something(String rating) throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.guestratingdrop));
		driver.findElement(Trivago.guestratingdrop).click();
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.excellentDrop));
		if (rating.equals("8")) {
			driver.findElement(Trivago.verygoodDrop).click();
		} else if (rating.equals("8.5")) {
			driver.findElement(Trivago.excellentDrop).click();
		}
	}

	@When("^User should be able to find Azaya hotel and click on view deal button$")
	public void user_should_be_able_to_find_azaya_hotel_and_click_on_view_deal_button() throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.hotelnameslist));
		List<WebElement> hotelnameslist = driver.findElements(Trivago.hotelnameslist);

		int pagenationcount = driver.findElements(By.xpath("//div[@class='pagination melody-pagination']//button"))
				.size();
		boolean hotelflag = false;
		for (int pagecount = 1; pagecount <= pagenationcount; pagecount++) {
			if (hotelflag) {
				break;
			}
			for (int j = 0; j < hotelnameslist.size(); j++) {
				String hotelname = driver.findElements(Trivago.hotelnameslist).get(j).getText();
				if (hotelname.contains("Azaya Beach Resort Goa")) {
					driver.findElements(Trivago.hotelAzayaDeal).get(j).click();
					hotelflag = true;
					break;
				}
			}
			if (hotelflag == false) {
				driver.findElement(
						By.xpath("(//div[@class='pagination melody-pagination']//button)[" + pagecount + "]")).click();
				pagenationcount = driver.findElements(Trivago.pageNextBtn).size();
			}
		}

	}

	@When("^The user click search availability button$")
	public void the_user_click_search_availability_button() throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Bookingdotcom.seeAvalabilityBtn));
		System.out.println(driver.getTitle());
		driver.findElement(Bookingdotcom.seeAvalabilityBtn).click();
	}

	@When("^Select \"([^\"]*)\"$")
	public void select(String roomneeded) throws Throwable {
		List<WebElement> roomtypelist = driver.findElements(Bookingdotcom.roomtypelist);
		for (int row = 1; row <= roomtypelist.size(); row++) {
			String roomtype = driver.findElement(By
					.xpath("(//div[@class='hprt-container']//table[@id='hprt-table']//a[@class='hprt-roomtype-link'])["
							+ row + "]"))
					.getText();
			if (roomtype.contains(roomneeded)) {
				Select selectavilla = new Select(driver.findElement(Bookingdotcom.roomquantitySelect));
				selectavilla.selectByValue("1");
				break;
			}
		}
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-350)", "");
		Wait.until(ExpectedConditions.elementToBeClickable(Bookingdotcom.reserveBtn));
		actions.moveToElement(driver.findElement(Bookingdotcom.reserveBtn));
		actions.perform();
		driver.findElement(Bookingdotcom.reserveBtn).click();
	}

	@Then("^User clicks on Create an account button$")
	public void user_clicks_on_create_an_account_button() throws Throwable {

		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.createAcctBtn));
		driver.findElement(Trivago.createAcctBtn).click();
	}

	@Then("^User should reach Home Page and clicks Account settings option$")
	public void user_should_reach_home_page_and_clicks_account_settings_option() throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.userDropdown));
		actions.moveToElement(driver.findElement(Trivago.userDropdown));
		actions.perform();
		driver.findElement(Trivago.accountsettingsDropdown).click();
		// clicking on account settings list page
	}

	@Then("^The Issue should be selected and the Success message is captured$")
	public void the_issue_should_be_selected_and_the_success_message_is_captured() throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.confirmationMessage));
	}

	@Then("^The user should be navigated to Bookingdotcom website$")
	public void the_user_should_be_navigated_to_bookingdotcom_website() throws Throwable {
		System.out.println(driver.getTitle());
		Set<String> t = driver.getWindowHandles();
		Iterator<String> it = t.iterator();
		it.next();
		String childid = it.next();
		driver.switchTo().window(childid);
	}

	@Then("^The user should be navigated to the address details page$")
	public void the_user_should_be_navigated_to_the_address_details_page() throws Throwable {
	}

	@Then("^Provide all the information$")
	public void provide_all_the_information() throws Throwable {

		int randomInt = randomGenerator.nextInt(1000);
		Wait.until(ExpectedConditions.elementToBeClickable(Bookingdotcom.firstNameTxt));
		driver.findElement(Bookingdotcom.firstNameTxt).sendKeys(randomInt + "first");
		driver.findElement(Bookingdotcom.lastNameTxt).sendKeys(randomInt + "last");
		String username = "tester" + randomInt + "@gmail.com";
		driver.findElement(Bookingdotcom.EmailTxt).sendKeys(username);
		driver.findElement(Bookingdotcom.confirmEmailTxt).sendKeys(username);
		actions.moveToElement(driver.findElement(Bookingdotcom.finalDetailsBtn));
		actions.perform();
		driver.findElement(Bookingdotcom.finalDetailsBtn).click();
	}

	@And("^User enters email address, password$")
	public void user_enters_email_address_password() throws Throwable {
		int randomInt = randomGenerator.nextInt(1000);
		String username = "tester" + randomInt + "@gmail.com";
		String password = "Testtester@12345";
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.registerEmailBox));
		driver.findElement(Trivago.registerEmailBox).sendKeys(username);
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.registerPassBox));
		driver.findElement(Trivago.registerPassBox).sendKeys(password);
		Thread.sleep(5000);
	}

	@And("^User select \"([^\"]*)\" as Issue topic$")
	public void user_select_something_as_issue_topic(String error) throws Throwable {
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.selectatopic));
		Select selecterror = new Select(driver.findElement(Trivago.selectatopic));
		selecterror.selectByValue(error);
	}

	@And("^Click Send Message Button$")
	public void click_send_message_button() throws Throwable {
		Thread.sleep(3000);
		Wait.until(ExpectedConditions.elementToBeClickable(Trivago.senderrormessageBtn));
		driver.findElement(Trivago.senderrormessageBtn).click();
		
	}
	@After
	public void close_browser() throws Throwable {
		driver.quit();
	}
}