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

        get("/hero/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: for new hero
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

        //post: to post new squad
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

        //get: ALL
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/hero/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHero();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all squads
        get("/squad/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clearAllSquad();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero
        get("/hero/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Hero foundHero = Hero.findById(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "hero-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show an individual squad
        get("/squad/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());


    }
}
