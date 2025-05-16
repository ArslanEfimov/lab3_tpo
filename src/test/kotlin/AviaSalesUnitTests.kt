import org.example.factories.BrowserType
import org.example.factories.WebDriverFactory
import org.example.pages.HomeAviaSalesPage
import org.example.pages.HotelAviaSalesPage
import org.example.pages.SearchFastAirlinePage
import org.example.pages.SearchHotelPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@Execution(ExecutionMode.CONCURRENT)
class AviaSalesUnitTests {

    private lateinit var driver: WebDriver

    @AfterEach
    fun tearDown(){
        driver.quit()
    }

    @ParameterizedTest
    @EnumSource(BrowserType::class)
    fun `try to fast find tickets test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.inputFromCity("Москва")
        homePage.selectDropDownFromCity()
        homePage.inputToCity("Санкт-")
        val searchPage: SearchFastAirlinePage = homePage.selectDropDownToCity()
        assertTrue(searchPage.hasResults())
    }

    @ParameterizedTest
    @EnumSource(BrowserType::class)
    fun `try to find hotels test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.closeHotelClue()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        val hotelPage: HotelAviaSalesPage = homePage.goToHotelPage()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        hotelPage.inputCityHotel("Москв")
        hotelPage.selectDropdownCityHotel()
        hotelPage.selectArrivalAndDepartureDate()
        val searchHotelPage: SearchHotelPage = hotelPage.searchHotels()
        assertTrue(searchHotelPage.hasResults())
    }

    @ParameterizedTest
    @EnumSource(BrowserType::class)
    fun `try to find city at short page test`(browserType: BrowserType){
        val city: String = "Москва"
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeHotelClue()
        homePage.acceptCookiesIfExist()
        val shortAviaSalesPage = homePage.goToShortPage()
        assertTrue(shortAviaSalesPage.hasResults())
        shortAviaSalesPage.searchCity(city)
        assertTrue(shortAviaSalesPage.hasResults())
        assertEquals(city, shortAviaSalesPage.getCityName("Москва"))

    }

    @ParameterizedTest
    @EnumSource(BrowserType::class)
    fun `switch to dark theme test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        val settingsAviaSalesPage = homePage.goToSettingsPage()
        settingsAviaSalesPage.switchToDarkUserTheme()
        assertTrue(settingsAviaSalesPage.isDarkThemeIcon())
    }

    @ParameterizedTest
    @EnumSource(BrowserType::class)
    fun `try to slow find tickets test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        homePage.inputFromCity("Москва")
        homePage.selectDropDownFromCity()
        homePage.inputToCity("Санкт-")
        homePage.selectDropDownToCity()
        homePage.selectArrivedAndDepartureDate()
        val searchSlowAirlinePage = homePage.searchTicket()
        homePage.acceptCookiesIfExist()
        assertTrue(searchSlowAirlinePage.hasResults())
    }

    @ParameterizedTest
    @EnumSource(BrowserType::class)
    fun `try to slow find tickets and sort ascending test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        homePage.inputFromCity("Моск")
        homePage.selectDropDownFromCity()
        homePage.inputToCity("Санкт-")
        homePage.selectDropDownToCity()
        homePage.selectArrivedAndDepartureDate()
        val searchSlowAirlinePage = homePage.searchTicket()
        homePage.acceptCookiesIfExist()
        searchSlowAirlinePage.sortTicketsByPrice()
        assertTrue(searchSlowAirlinePage.isSortedByPriceAscending())

    }

    @ParameterizedTest
    @EnumSource(BrowserType::class)
    fun `try to find five stars hotels test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeHotelClue()
        homePage.closeGoogleIFrameIfExist()
        val hotelPage: HotelAviaSalesPage = homePage.goToHotelPage()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        hotelPage.inputCityHotel("Москв")
        hotelPage.selectDropdownCityHotel()
        hotelPage.selectArrivalAndDepartureDate()
        val searchHotelPage: SearchHotelPage = hotelPage.searchHotels()
        homePage.closeGoogleIFrameIfExist()
        homePage.acceptCookiesIfExist()
        searchHotelPage.filterHotelsByFiveStar()
        assertTrue(searchHotelPage.isFilterByFiveStars())
    }

    @ParameterizedTest
    @EnumSource
    fun `try to add ticket to favorites fail test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        homePage.inputFromCity("Моск")
        homePage.selectDropDownFromCity()
        homePage.inputToCity("Санкт-")
        homePage.selectDropDownToCity()
        homePage.selectArrivedAndDepartureDate()
        val searchSlowAirlinePage = homePage.searchTicket()
        homePage.acceptCookiesIfExist()
        assertTrue( searchSlowAirlinePage.hasResults())
        assertEquals(searchSlowAirlinePage.addToFavorites(), "Войти в профиль")
    }

    @ParameterizedTest
    @EnumSource
    fun `try to find tickets with multiway path test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        val multiPathTage = homePage.goToMultiWayAviaSalesPage()

        multiPathTage.inputFromCityFirst("Москв")
        multiPathTage.selectDropDownFromCityFirst()
        multiPathTage.inputToCityFirst("Санкт-Пе")
        multiPathTage.selectDropDownToCityFirst()
        multiPathTage.selectArrivedDateFirst()

        multiPathTage.inputFromCitySecond("Санкт-Пе")
        multiPathTage.selectDropDownFromCitySecond()
        multiPathTage.inputToCitySecond("Нижнекамск")
        multiPathTage.selectDropDownToCitySecond()
        multiPathTage.selectArrivedDateSecond()

        val searchMultiWayAirlinePage = multiPathTage.searchTicket()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        assertTrue(searchMultiWayAirlinePage.hasResults())

    }

    @ParameterizedTest
    @EnumSource
    fun `check that filter sea and beaches at short page is work test`(browserType: BrowserType){
        driver = WebDriverFactory.createDriver(browserType)
        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(driver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeHotelClue()
        homePage.closeGoogleIFrameIfExist()
        val shortAviaSalesPage = homePage.goToShortPage()
        val places: List<WebElement> = shortAviaSalesPage.getPlacesCity()
        val beachesPlace = places.filter { it.findElement(By.tagName("h4")).text == "Пляжи" }
        assertNotNull(beachesPlace)
        assertEquals("Пляжи\n" +
                "Где плавать и загорать", beachesPlace[0].text)
    }


}