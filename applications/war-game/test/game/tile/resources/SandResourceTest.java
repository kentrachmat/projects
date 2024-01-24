package game.tile.resources;

import org.junit.*;
import static org.junit.Assert.*;

public class SandResourceTest{

    private Resource sand;

    @Before
    public void before(){
        this.sand = new SandResource(2);
    }
    
    @Test 
    public void getNameTest(){
        assertEquals("Sand",this.sand.getName());
    }

    @Test 
    public void getValueTest(){
        assertEquals(2,this.sand.getValue());
    }

    @Test 
    public void equalsTest(){
        assertEquals(this.sand, new SandResource(2));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(SandResourceTest.class);
    }
}

