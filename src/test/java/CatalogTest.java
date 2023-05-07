import com.codeborne.selenide.CollectionCondition;
import helpers.CatalogHelper;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constant.Url.ONLINER_START_PAGE;
import static enums.CatalogItems.*;
import static enums.SubCatalogItems.*;

public class CatalogTest {

    CatalogHelper catalogHelper = new CatalogHelper();
    SoftAssert softly = new SoftAssert();

    @BeforeMethod
    public void openCatalogPage() {
        open(ONLINER_START_PAGE);
        $(catalogHelper.CATALOG_BUTTON).click();
    }

    @Test(priority = 3, groups = "catalogTests")
    public void shouldCheckCatalogItems() {
        //given
        List<String> items = new ArrayList<>();
        items.add(ELECTRONIC.getText());
        items.add(COMPUTERS.getText());
        items.add(HOUSEHOLD_APPLIANCE.getText());
        items.add(FOR_EVERY_DAY.getText());
        items.add(BUILDIND_AND_REPAIR.getText());
        items.add(HOME_AND_GARDEN.getText());
        items.add(AUTO_AND_MOTO.getText());
        items.add(BEAUTY_AND_SPORT.getText());
        items.add(CHILDREN_AND_MOTHERS.getText());
        //then
        softly.assertEquals(catalogHelper.getCatalogItems(), items);
    }

    @Test(priority = 1, groups = "catalogTests")
    public void shouldCheckComputerCatalog() {
        //given
        int id= 2;
        List<String> items = new ArrayList<>();
        items.add(LAPTOPS_COMPUTERS_MONITORS.getText());
        items.add(ACCESSORIES.getText());
        items.add(DATA_STORAGE.getText());
        items.add(NETWORK_EQUIPMENT.getText());
        //when
        catalogHelper.chooseCatalogItem(id);
        //then
        $((WebElement) catalogHelper.COMPUTER_ITEMS).shouldBe(visible);
        $$(catalogHelper.getItemsFromComputersBlock()).shouldHave(allMatch("All items should be displayed", el -> el.isDisplayed()));
        softly.assertAll();
    }


    @Test(priority = 2, groups = "catalogTests")
    public void shouldCheckComponentsSubdirectory() {
        //given
        int id = 2;
        //when
        catalogHelper.chooseCatalogItem(id);
        catalogHelper.chooseComputerSubdirectory(ACCESSORIES.getText());
        //then
        $$(catalogHelper.getItemsFromComputersBlock()).shouldHave(CollectionCondition.allMatch("visible items", el -> el.isDisplayed()));
        $$(catalogHelper.COMPONENT_SUBDIRECTORY_ITEM_PRODUCTS).forEach(WebElement::isDisplayed);
        $$(catalogHelper.COMPONENT_SUBDIRECTORY_ITEM_PRICE).forEach(WebElement::isDisplayed);

        softly.assertAll();
    }
}
