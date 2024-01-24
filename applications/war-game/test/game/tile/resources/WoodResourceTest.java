package game.tile.resources;

import org.junit.*;
import static org.junit.Assert.*;

public class WoodResourceTest{

    private Resource wood;

    @Before
    public void before(){
        this.wood = new WoodResource(1);
    }
    
    @Test 
    public void getNameTest(){
        assertEquals("Wood",this.wood.getName());
    }

    @Test 
    public void getValueTest(){
        assertEquals(1,this.wood.getValue());
    }

    @Test 
    public void equalsTest(){
        assertEquals(this.wood, new WoodResource(1));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WoodResourceTest.class);
    }
}

