package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

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

}