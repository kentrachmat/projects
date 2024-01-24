package game.tile.resources;

import org.junit.*;
import static org.junit.Assert.*;

public class CornResourceTest{
    
	// Resource corn
    private Resource corn;

    @Before
    public void before(){
        this.corn = new CornResource(3);
    }
    
    @Test 
    public void getNameTest(){
        assertEquals("Corn",this.corn.getName());
    }

    @Test 
    public void getValueTest(){
        assertEquals(3,this.corn.getValue());
    }

    @Test 
    public void equalsTest(){
        assertEquals(this.corn, new CornResource(3));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(CornResourceTest.class);
    }
}

