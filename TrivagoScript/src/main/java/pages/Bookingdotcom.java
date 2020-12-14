package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Bookingdotcom {
	/*booking.com */
	
	/*Select Room Page*/
	public static By seeAvalabilityBtn = By.xpath("(//*[@class='txp-cta bui-button bui-button--primary sr_cta_button'])[1]");
	public static By roomtypelist = By.xpath("//div[@class='hprt-container']//table[@id='hprt-table']//a[@class='hprt-roomtype-link']");
	public static By roomquantitySelect = By.xpath("//span[contains(text(),'Essence Room King Bed')]//..//..//..//..//..//select");
	public static By reserveBtn = By.xpath("//div[@class='hprt-reservation-cta']//*[contains(text(),'reserve')]");

	/*Enter your details page*/
	public static By firstNameTxt =By.xpath("//*[@id='firstname']");
	public static By lastNameTxt =By.xpath("//*[@id='lastname']");
	public static By EmailTxt = By.xpath("//*[@id='email']");
	public static By confirmEmailTxt = By.xpath("//*[@id='email_confirm']");
	public static By finalDetailsBtn = By.xpath("(//*[@id='bookForm']//button)[3]");
	
	
}
