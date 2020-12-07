import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //8get: show new hero form
        get("/hero/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero.hbs");
        }, new HandlebarsTemplateEngine());

        //8get: show new squad form
        get("/squad/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad.hbs");
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

        //6get: ALL
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
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
            int idOfHeroToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Hero foundHero = Hero.findById(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "hero-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //4get: show an individual squad
        get("/squad/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-detail.hbs"); //individual post page.
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
