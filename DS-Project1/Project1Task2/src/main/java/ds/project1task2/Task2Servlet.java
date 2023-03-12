package ds.project1task2;

/*
 * @author Sreemoyee Mukherjee
 *
 * The servlet is acting as the controller.
 * There are two views - index.jsp and country.jsp.
 * It decides between the two by determining if there is a selected country parameter or
 * not. If there is no parameter, then it uses the index.jsp view, as a
 * starting place. If there is a selected country parameter, then it searches for
 * Women's World Cup information for that country and uses the country.jsp view.
 * The model is provided by Task2Model.
 */

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "womenWorldCup", value = "/womenWorldCup")
public class Task2Servlet extends HttpServlet {
    Task2Model tm = null;   // The "business model" for this app

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        tm = new Task2Model();
    }

    // This servlet will reply to HTTP GET requests via this doGet method
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextView;
        String countryName = request.getParameter("countries");
        if(countryName != null){
            // pass the nickname to the view after it is returned from the model
            String nickName = tm.getNickName(countryName);
            request.setAttribute("nickName", nickName);
            request.setAttribute("nickNameURL", tm.nickNameURL.substring(tm.nickNameURL.indexOf("www.")));
            // pass the capital name to the view after it is returned from the model
            String capital = tm.getCapital(countryName);
            request.setAttribute("capital", capital);
            request.setAttribute("capitalURL", tm.capitalURL.substring(tm.capitalURL.indexOf("www.")));
            // pass the top scorer name and number of goals to the view after it is returned from the model
            String topScorer = tm.getTopScorer(countryName);
            request.setAttribute("topScorer", topScorer);
            request.setAttribute("topScorerURL", tm.topScorerURL.substring(tm.topScorerURL.indexOf("www.")));
            // pass the url of the flag image to the view after it is returned from the model
            String flagURL = tm.getFlag(countryName);
            request.setAttribute("flagURL", flagURL);
            request.setAttribute("flagSiteURL", tm.flagSiteURL.substring(tm.flagSiteURL.indexOf("/www.")));
            // pass the url of the emoji to the view after it is returned from the model
            String EmojiURL = tm.getFlagEmoji(countryName);
            request.setAttribute("EmojiURL", EmojiURL);
            String flagEmojiURL = "www.cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/your-country-here.svg";
            request.setAttribute("flagEmojiURL", flagEmojiURL);
            // show the country view for the selected country parameter
            nextView = "country.jsp";
        }
        else {
            // no selected country parameter so choose the index view
            nextView = "index.jsp";
        }
        // Transfer control over to the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

}