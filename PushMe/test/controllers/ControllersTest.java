package controllers;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

//JUnit test class
public class ControllersTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    //User authentication test: Checks if email and password authentication works
    @Test
    public void tryAuthenticateUser() {
        new User("bob@hotmail.com", "Bob", "secret").save();
        
        assertNotNull(User.authenticate("bob@hotmail.com", "secret"));
        assertNull(User.authenticate("bob@hotmail.com", "badpassword"));
        assertNull(User.authenticate("wrong@gmail.com", "secret"));
    }
    
}