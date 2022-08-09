package stepDefs;

import cucumber.api.java.en.Given;
import testobjects.*;

public class SauceDemoSteps {


    @Given("^i login to application \"([^\"]*)\"$")
    public void i_login_to_application(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
      //  throw new PendingException();
        Login.loginToSauceDemo();
    }




}
