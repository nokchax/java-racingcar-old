package racingcar;

import racingcar.dto.RacingResult;
import racingcar.racing.Racing;
import racingcar.handlebar.CustomHandlebarsTemplateEngine;
import spark.ModelAndView;
import spark.Request;

import java.util.*;

import static spark.Spark.*;

public class WebMain {
    private static final String NAMES = "names";
    private static final String TURN = "turn";
    private static final String SPLIT = " ";

    public static void main(String[] args) {
        port(8080);

        get("/", (req, res) -> {
            req.session(true);
            Map<String, Object> model = new HashMap<>();
            model.put("name", "nokchax");

            return render(model, "/index.html");
        });


        post("/name", (req, res) -> {
            clearSession(req, NAMES);
            String name = req.queryParams(NAMES);
            List<String> names = Arrays.asList(name.split(SPLIT));

            req.session()
                    .attribute(NAMES, names);

            Map<String, Object> model = new HashMap<>();
            model.put(NAMES, names);

            return render(model, "/game.html");
        });

        get("/result", (req, res) -> {
            List<String> userNames = req.session().attribute(NAMES);
            int round = Integer.parseInt(req.queryParams(TURN));

            Racing racing = new Racing(userNames, round);

            racing.race();
            RacingResult result = racing.result();

            Map<String, Object> model = new HashMap<>();
            model.put("winnerNames", result.getWinnerNames());
            model.put("histories", result.getRacingHistory());
            model.put("name", "nokchax");

            return render(model, "result.html");
        });
    }

    private static void clearSession(Request request, String attribute) {
        request.session()
                .attribute(attribute, null);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new CustomHandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
