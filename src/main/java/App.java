import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        //6get: show all posts
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> hero = Hero.getAll();
            model.put("hero", hero);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hero", (req, res) -> {
            //just for testing - make two new objects so we have something to retrieve
            Hero hero = new Hero("Harley",20,"cuckoo", "joker");
            Hero otherHero = new Hero("BB",10,"bee","light");
            Map<String, ArrayList<Hero>> model = new HashMap<>();
            ArrayList myHeroesArrayList = Hero.getAll();
            model.put("myHeroes", myHeroesArrayList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad", (req, res) -> {
            //just for testing - make two new objects so we have something to retrieve
            Squad squad = new Squad(4,"cray crew", "attack brenda");
            Map<String, ArrayList<Squad>> model = new HashMap<>();
            ArrayList mySquadsArrayList = Squad.getAll();
            model.put("mySquads", mySquadsArrayList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hero-form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squad-form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            Integer age = Integer.valueOf(request.queryParams("age"));
            String specialPower = request.queryParams("specialPower");
            String weakness = request.queryParams("weakness");
            model.put("name", name);
            model.put("age", age);
            model.put("specialPower", specialPower);
            model.put("weakness", weakness);
            return new ModelAndView(model, "hero.hbs");
        }, new HandlebarsTemplateEngine());

        //7post: for new hero
        post("/hero/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Integer age = Integer.parseInt(req.queryParams("age"));
            String specialPower = req.queryParams("specialPower");
            String weakness = req.queryParams("weakness");
            Hero newHero = new Hero(name,age,specialPower,weakness);
            model.put("item",req.session().attribute("item"));
            model.put("hero", newHero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//7post: to post new squad
        post("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Integer maxSize = Integer.parseInt(req.queryParams("maxSize"));
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            Squad newSquad = new Squad(maxSize,name,cause);
            model.put("item",req.session().attribute("item"));
            model.put("squad", newSquad);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//5get: delete all heroes
        get("/hero/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHero();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//5get: delete all squads
        get("/squad/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clearAllSquad();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//4get: show an individual hero
        get("/hero/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params(":id"));
            Hero foundHero = Hero.findById(idOfHeroToFind);
            model.put("hero", foundHero);
            return new ModelAndView(model, "hero-details.hbs");
        }, new HandlebarsTemplateEngine());

//4get: show an individual squad
        get("/squad/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params(":id"));
            Squad foundSquad = Squad.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            return new ModelAndView(model, "squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

//3get: show a form to update a hero
        get("/hero/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());
//3get: show a form to update a squad
        get("/squad/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

//2post: process a form to update a hero
        post("/hero/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            Integer newAge = Integer.parseInt(req.queryParams("age"));
            String newSpecialPower = req.queryParams("specialPower");
            String newWeakness = req.queryParams("weakness");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            editHero.update(newName,newAge,newSpecialPower,newWeakness);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//2post: process a form to update a squad
        post("/squad/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Integer newMaxSize = Integer.parseInt(req.queryParams("maxSize"));
            String newName = req.queryParams("name");
            String newCause = req.queryParams("cause");
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            editSquad.update(newMaxSize,newName,newCause);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//1get: delete an individual hero
        get("/hero/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id"));
            Hero deleteHero = Hero.findById(idOfHeroToDelete);
            deleteHero.deleteHero();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//1get: delete an individual squad
        get("/squad/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id"));
            Squad deleteSquad = Squad.findById(idOfSquadToDelete);
            deleteSquad.deleteSquad();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
