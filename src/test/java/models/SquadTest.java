package models;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {

    @Test
    public void NewSquadObjectGetsCorrectlyCreated_true() throws Exception {
        Squad squad = new Squad(4,"The Bomb Squad","bomb alert");
        assertEquals(true, squad instanceof Squad);
    }

    @Test
    public void SquadInstantiatesWithMaxSize_true() throws Exception {
        Squad squad = new Squad(4,"The Bomb Squad","bomb alert");
        assertEquals(4, squad.getMaxSize());
    }
    @Test
    public void SquadInstantiatesWithName_true() throws Exception {
        Squad squad = new Squad(4,"The Bomb Squad","bomb alert");
        assertEquals("The Bomb Squad", squad.getName());
    }
    @Test
    public void SquadInstantiatesWithCause_true() throws Exception {
        Squad squad = new Squad(4,"The Bomb Squad","bomb alert");
        assertEquals("bomb alert", squad.getCause());
    }


    @After
    public void tearDown() {
        Hero.clearAllHero(); //clear out all the hero before each test.
    }
}