package models;

import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;

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

    @Test
    public void AllSquadAreCorrectlyReturned_true() {
        Squad squad = new Squad(4,"The Bomb Squad","bomb alert");
        Squad squadTwo = new Squad(8,"Super Hero Girls","fight gender discrimination");
        assertTrue(Squad.getAll().contains(squad));
        assertTrue(Squad.getAll().contains(squadTwo));
    }

    @Test
    public void AllSquadContainsAllSquads_true() {
        Squad squad = new Squad(4,"The Bomb Squad","bomb alert");
        Squad squadTwo = new Squad(8,"Super Hero Girls","fight gender discrimination");
        assertEquals(2, Squad.getAll().size());
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Squad squadTwo = new Squad(8,"Super Hero Girls","fight gender discrimination");
        assertEquals(false, squadTwo.getPublished());
    }
    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Squad mySquad = setupNewSquad();
        assertEquals(LocalDateTime.now().getDayOfWeek(), mySquad.getCreatedAt().getDayOfWeek());
    }
    private Squad setupNewSquad() {
        return new Squad(8,"Super Hero Girls","fight gender discrimination");
    }
    @Test
    public void findReturnsCorrectSquadWhenMoreThanOneSquadsExists() throws Exception {
        Squad squad = setupNewSquad();
        Squad squadTwo = new Squad(8,"Super Hero Girls","fight gender discrimination");
        assertEquals(2, Squad.findById(squadTwo.getId()).getId());
    }

    @Test
    public void getId_squadInstantiateWithAnID_1() throws Exception{
        Squad.clearAllSquad();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
        Squad mySquad = new Squad(8,"Super Hero Girls","fight gender discrimination");
        assertEquals(1, mySquad.getId());
    }

    @Test
    public void findReturnsCorrectSquad() throws Exception {
        Squad squad = setupNewSquad();
        assertEquals(1, Squad.findById(squad.getId()).getId());
    }

    @Test
    public void deleteDeletesASpecificSquad() throws Exception {
        Squad squad = setupNewSquad();
        Squad squad1 = new Squad(8,"Super Hero Girls","fight gender discrimination");
        squad.deleteSquad();
        assertEquals(1, Squad.getAll().size());
        assertEquals(Squad.getAll().get(0).getId(), 2);
    }
    @Test
    public void deleteAllSquadsDeletesAllSquads() throws Exception {
        Squad squad = setupNewSquad();
        Squad squad1 = setupNewSquad();

        Squad.clearAllSquad();
        assertEquals(0, Squad.getAll().size());
    }

    @After
    public void tearDown() {
        Squad.clearAllSquad();
    }
}