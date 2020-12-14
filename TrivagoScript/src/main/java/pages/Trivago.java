package pages;

import org.openqa.selenium.By;

public class Trivago {
	/*Home Page */
	public static By loginBtn = By.xpath("//*[text()='Log in']");
	public static By searchBox = By.id("querytext");
	public static By firstsuggestDrop = By.xpath("//div[@class='ssg-suggest']//ul//li");
	public static By cookiesOkBtn = By.xpath("//*[@id='onetrust-accept-btn-handler']");
	public static By userDropdown = By.xpath("//*[@id='user-text']");
	public static By accountsettingsDropdown = By.xpath("//*[@id='Account settings']");
	
	/*Home Page - Search details*/
	public static By date25 = By.xpath("//*[@class='cal-month']//time[@datetime='2020-12-25']");
	public static By date27 = By.xpath("//*[@class='cal-month']//time[@datetime='2020-12-27']");
	public static By monthheaderText = By.xpath("//th[@class='cal-heading-month']");
	public static By previousMonth = By.xpath("//*[@class='cal-btn-prev']");
	public static By checkinDate = By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/div/div[2]/button/span");
	public static By checkoutDate = By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/div/div[3]/button/span");
	public static By roomDetailsdrop = By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/div/button/span");
	public static By noOfAdultTxt = By.id("adults-input");
	public static By roomdetailsApplyBtn = By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/div/div[4]/fieldset/ul/li[2]/button");
	public static By searchBtn = By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/div/button[2]/span[2]");

	/*Account Settings Page*/
	public static By selectatopic = By.xpath("//*[@id='error-topics']");
	public static By selecterrorencounter = By.xpath("//*[@id='error-encounter']");
	public static By errorSubjectTxt = By.xpath("//*[@id='subject']");
	public static By errorDescriptionTxt = By.xpath("//*[@id='description']");
	public static By errorHotelNameTxt = By.xpath("//*[@id='hotel-name']");
	public static By senderrormessageBtn = By.xpath("//*[text()='Send message']");
	public static By confirmationMessage = By.xpath("//*[text()='Your message was sent successfully!']");
	
	/*Login Page*/
	public static By logineMailBox = By.id("check_email");
	public static By emailNextBtn = By.id("login_email_submit");
	public static By loginePassBox = By.id("login_password");
	public static By loginSubmitBtn = By.id("login_submit");
	
	/*Register Page*/
	public static By createAcctBtn = By.id("login_signup_link");
	public static By registerEmailBox = By.id("register_email");
	public static By registerPassBox = By.id("register_password");
	public static By registerEmailSubmitBtn = By.id("register_email_submit");
	
	/*Hotels list page*/
	public static By guestratingdrop = By.xpath("//div[@class='filter-toolbar']//ul//li[3]");
	public static By excellentDrop = By.xpath("//*[text()='Excellent']");
	public static By verygoodDrop = By.xpath("//*[text()='Very good']");
	public static By hotelnameslist = By.xpath("//div[@class='item__details item__details--reduced']//h3//span");
	public static By hotelAzayaDeal = By.xpath("//span[text()='View Deal']");
	public static By pageNextBtn = By.xpath("//div[@class='pagination melody-pagination']//button");
	}
