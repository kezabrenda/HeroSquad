package models;

import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class HeroTest {

    @After
    public void tearDown() {
        Hero.clearAllHero(); //clear out all the hero before each test.
    }
                                 /****/
    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = new Hero("Raven",20,'F',"illusions","family issues");
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void HeroInstantiatesWithName_true() throws Exception {
        Hero hero = new Hero("Batman",30,'M',"utility belt","no super power");
        assertEquals("Batman", hero.getName());
    }
    @Test
    public void HeroInstantiatesWithAge_true() throws Exception {
        Hero hero = new Hero("Batman",30,'M',"utility belt","no super power");
        assertEquals(30, hero.getAge());
    }
    @Test
    public void HeroInstantiatesWithGender_true() throws Exception {
        Hero hero = new Hero("Batman",30,'M',"utility belt","no super power");
        assertEquals(java.util.Optional.of('M'), java.util.Optional.ofNullable(hero.getGender()));
    }
    @Test
    public void HeroInstantiatesWithSpecialPower_true() throws Exception {
        Hero hero = new Hero("Batman",30,'M',"utility belt","no super power");
        assertEquals("utility belt", hero.getSpecialPower());
    }
    @Test
    public void HeroInstantiatesWithWeakness_true() throws Exception {
        Hero hero = new Hero("Batman",30,'M',"utility belt","no super power");
        assertEquals("no super power", hero.getWeakness());
    }
                             /******************************************************************************/
    @Test
    public void AllHeroAreCorrectlyReturned_true() {
        Hero hero = new Hero("Raven",20,'F',"illusions","family issues");
        Hero otherHero = new Hero ("Batman",30,'M',"utility belt","no super power");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(otherHero));
    }

    @Test
    public void AllPostsContainsAllHero_true() {
        Hero hero = new Hero("Raven",20,'F',"illusions","family issues");
        Hero otherHero = new Hero ("Batman",30,'M',"utility belt","no super power");
        assertEquals(2, Hero.getAll().size());
    }
/********************************************************************************************/

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Hero myHero = new Hero("Raven",20,'F',"illusions","family issues");
        assertEquals(false, myHero.getPublished()); //should never start as published
    }
    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Hero myHero = setupNewHero();
        assertEquals(LocalDateTime.now().getDayOfWeek(), myHero.getCreatedAt().getDayOfWeek());
    }
    private Hero setupNewHero() {
        return new Hero("Raven",20,'F',"illusions","family issues");
    }
    @Test
    public void findReturnsCorrectHeroWhenMoreThanOneHeroesExists() throws Exception {
        Hero hero = setupNewHero();
        Hero otherHero = new Hero("Batman",30,'M',"utility belt","no super power");
        assertEquals(2, Hero.findById(otherHero.getId()).getId());
    }
    /************************************************************************************************/
    @Test
    public void getId_heroInstantiateWithAnID_1() throws Exception{
        Hero.clearAllHero();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
        Hero myHero = new Hero("Raven",20,'F',"illusions","family issues");
        assertEquals(1, myHero.getId());
    }

    @Test
    public void findReturnsCorrectHero() throws Exception {
        Hero hero = setupNewHero();
        assertEquals(1, Hero.findById(hero.getId()).getId());
    }
}