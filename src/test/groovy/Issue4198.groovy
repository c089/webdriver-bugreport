import spock.lang.Specification
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import spock.lang.Issue

@Issue('http://code.google.com/p/selenium/issues/detail?id=4198')
class Issue4198 extends Specification {
    void 'it should properly send my city to the form'() {
        given: 'a native-events enabled driver'
        final FirefoxProfile profile = new FirefoxProfile()
        profile.setEnableNativeEvents(true)
        final WebDriver driver = new FirefoxDriver(profile)

        when: 'I go to my page'
        URL url = this.class.getResource('form.html')
        driver.get(url.toString())

        and: 'enter a string with an umlaut into the form'
        def cityWithUmlaut = 'München'
        driver.findElementById('city').sendKeys(cityWithUmlaut)

        then: 'it is correctly entered into the form'
        driver.findElementById('city').getAttribute('value') == cityWithUmlaut

        cleanup:
        driver.quit()
    }
}
