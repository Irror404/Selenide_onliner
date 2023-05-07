package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CatalogHelper extends BasePage {
    //locators
    public final SelenideElement CATALOG_BUTTON = $x("//a[contains(@href, 'catalog') and contains(text(), 'Каталог')]");
    public final ElementsCollection COMPUTER_ITEMS = $$x("//div[@data-id='2']//div[contains(@class,'aside-list')]");
    public final SelenideElement COMPONENT_SUBDIRECTORY = $x("//div[text()=' Комплектующие ']");
    public final ElementsCollection COMPONENT_SUBDIRECTORY_ITEM_NAMES = COMPONENT_SUBDIRECTORY.parent().$$(".title");
    public final ElementsCollection COMPONENT_SUBDIRECTORY_ITEM_PRODUCTS = COMPONENT_SUBDIRECTORY.parent().$$(".price-current");
    public final ElementsCollection COMPONENT_SUBDIRECTORY_ITEM_PRICE = COMPONENT_SUBDIRECTORY.parent().$$(".price-current");
    public final SelenideElement PRODUCT_PAGE_TITLE_XPATH_PATTERN = $x("//*[contains(@class, 'header_title') and contains(text(), '%s')]");

    //methods
    public void chooseCatalogItem(int id) {
        $(byText(Integer.toString(id))).click();
    }

    public void chooseComputerSubdirectory(String name) {
        COMPONENT_SUBDIRECTORY.parent().$(Selectors.withText(name)).click();
    }

    public WebElement getCatalogItems() {
        List<String> items = new ArrayList<>();
        $$(".catalog-navigation-classifier__item-title").shouldHave(sizeGreaterThan(0)).forEach((SelenideElement item) -> items.add(item.getText()));
        return (WebElement) items.subList(1, 10);
    }

    public String getItemsFromComputersBlock() {
        List<String> items = new ArrayList<>();
        COMPUTER_ITEMS.first().$$(".title").shouldHave(sizeGreaterThan(0)).forEach((SelenideElement item) -> items.add(item.getText()));
        return String.valueOf(items);
    }
}
