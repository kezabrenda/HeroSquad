import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        get("/hero/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/hero/new", (req, res) -> { //URL to make new post on POST route
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

        post("/squads/new", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            Integer maxSize = Integer.parseInt(req.queryParams("maxSize"));
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            Squad newSquad = new Squad(maxSize,name,cause);
            model.put("item",req.session().attribute("item"));
            model.put("squad", newSquad);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
