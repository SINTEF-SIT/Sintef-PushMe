package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

//JUnit test class
public class ModelsTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    //Create and retrive a user
    @Test
    public void createAndRetrieveUser() {
        new User("embugge@hotmail.com", "Bob", "secret").save();
        User bob = User.find.where().eq("email", "embugge@hotmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }    
}