package tc_02;

import org.openqa.selenium.By;

public class Element {
	public static By userNameInput = By.id("user-name");
	public static By passInput = By.id("password");
	public static By loginBtn = By.id("login-button");
	public static By sortDropDown = By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[3]");
	public static By latestProduct = By.xpath("(//*[@class=\"inventory_item_label\"]/a)[6]");
	public static By addToCart = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button");
	public static By cartIcon = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
	public static By checkoutBtn = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]");
	public static By firstNameInput = By.id("first-name");
	public static By lastNameInput = By.id("last-name");
	public static By zipCodeInput = By.id("postal-code");
	public static By continueBtn = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input");
}


