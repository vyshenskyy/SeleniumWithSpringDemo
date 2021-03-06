# SeleniumWithSpringDemo

### Selenium Tests in Multiple Environments with Spring-based Framework

This example demonstrates how to run Selenium tests in multiple environments using a simple framework based on Spring.

The Spring Framework [https://spring.io](https://spring.io/) is an application framework and inversion of control container for the Java platform. 

#### Multiple URLs
We need access to the system under test from local (tester’s) computer and remote (Selenium Grid) computer
We need to have access to different test servers (test URLs), e.g. development test server, pre-production test server etc. 

#### Multiple tenants
We want to use different configurations for different tenants (users).

We can use property files to access different test URLs. 
We can specify different property files for different URLs and tenants

```
testUrl= http://localhost:8080 
loginName=admin@second
password=admin
tenant=second
```

We can use property files to run the tests in the specific browser. 

```
# Browser that is used for tests
#browser=firefox
browser=chrome
```

#### Login before test

Some tests do not require login. Other tests require to be logged in

``` java
public interface LoginBeforeClass
public interface LoginBeforeMethod

public class FirstTenantTest extends BaseTest implements LoginBeforeMethod{

In BaseTest:
@BeforeMethod
public void loginToApplication() {
  if(this instanceof LoginBeforeMethod ) {
    loginToTestUrl();
  }
}
```

#### Framework files
The proposed framework consists of only 9 classes. With this framework, we can run tests in parallel using required configuration. We save screenshots for failed tests. We use Page Object pattern.

<img src='/src/main/resources/FrameworkClasses.png'>

#### Spring Configuration

``` java
@Configuration
@ComponentScan({"sample.framework", "sample.application"})
@PropertySource({"classpath:env/${test.env}.properties", "classpath:browser.properties"})
// Properties of test environment;
// test.env must be defined, e.g. by -Dtest.env=.. in VM variables
public class SeleniumTestConfiguration {
// We need this method to run tests in parallel
 @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new ThreadScopeRegisteringBeanFactoryPostProcessor();
    }
}
```

We can use dependency injection using **@Autowired**  annotation, so we do not need to instantiate page objects with **new** keyword

``` java
public class SecondTenantTest extends MtBaseTest implements LoginBeforeMethod {

@Autowired
private MtSecondPage secondPage;

@Test
public void toggleTextTest() {
  boolean displayed = secondPage.getTenantTextElement().isDisplayed();
  Assert.assertFalse(displayed);

```

#### We can use properties _out of the box_
``` java
@PropertySource({"classpath:env/${test.env}.properties", "classpath:browser.properties"})
```

#### Web driver starts and stops by the framework
``` java
@Component
public class BrowserConfiguration {
  @Value("${selenium.browser}")
  private  String browser;
  @Bean(destroyMethod = "quit")
  @Scope("thread") 
  public WebDriver webDriver()  { 
     switch(browser) {
    case "chrome":
	return new ChromeDriver();
  }
```


To specify TestNG configuration files and properties for the test , we can use Surefire plugin. It is a convenient way to run required configuration on a local computer

``` xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>2.19.1</version>
  <configuration>
    <suiteXmlFiles>
      <suiteXmlFile>src/test/resources/${tests}.xml</suiteXmlFile>
    </suiteXmlFiles>
    <argLine>-Dtest.env=mt_second</argLine>
   <!-- <argLine>-Dtest.env=mt_first</argLine> -->
   </configuration>
</plugin>
```
---
To specify the testing configuration file and properties, we can use Maven command line. It is a convenient way to run Selenium tests on a Continuous Integration server

>  **mvn clean test -Dtests=mtFirstTestNG -Dtest.env=mt_first**

As a result, we will test the site [Multi environment demo](/src/test/resources/demo/multiEnv.html) running [mtFirstTestNG.xml](/src/test/resources/mtFirstTestNG.xml "TestNG configuration") using properties from the file [mt_first.properties](/src/test/resources/env/mt_first.properties)
 

If we run this command
> **mvn clean test -Dtests=tiTestNG -Dtest.env=ti**

we will test the site  [The internet](http://the-internet.herokuapp.com/) running [tiTestNG.xml](/src/test/resources/tiTestNG.xml "TestNG configuration") using properties from the file [ti.properties](/src/test/resources/env/ti.properties)








