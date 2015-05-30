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
    
    //Create and retrive an activity
    @Test
    public void ActivityTest() {
        new Activity("Running", 10, 20, 30).save();
        Activity running = Activity.find.where().eq("name", "Running").findUnique();
        assertNotNull(running);
        assertEquals(10, running.low_intensity);
    }   
    
  //Create and retrive an activity level
    @Test
    public void ActivityLevelTest() {
        new ActivityLevel(1, 3, "High").save();
        ActivityLevel al = ActivityLevel.find.where().eq("id", 1).findUnique();
        assertNotNull(al);
        assertEquals(3, al.level);
    }
}