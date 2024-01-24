package game.tile.resources;

import org.junit.*;
import static org.junit.Assert.*;

public class RockResourceTest{

    private Resource rock;

    @Before
    public void before(){
        this.rock = new RockResource(4);
    }
    
    @Test 
    public void getNameTest(){
        assertEquals("Rock",this.rock.getName());
    }

    @Test 
    public void getValueTest(){
        assertEquals(4,this.rock.getValue());
    }

    @Test 
    public void equalsTest(){
        assertEquals(this.rock, new RockResource(4));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(RockResourceTest.class);
    }
}

