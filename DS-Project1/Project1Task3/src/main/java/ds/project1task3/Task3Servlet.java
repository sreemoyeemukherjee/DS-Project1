package ds.project1task3;

/*
 * @author Sreemoyee Mukherjee
 *
 * The servlet is acting as the controller.
 * There are three views - index.jsp, results.jsp and noresult.jsp.
 * It decides between the three by determining if the user is submitting answers
 * or viewing results. If the user is submitting answers, then it uses the index.jsp view, as a
 * starting place. If the user is viewing results are there are results available then
 * it displays the count of each option in the survey by using the results.jsp view.
 * If the user is viewing results are there are no result available then
 * the noresult.jsp view displays appropriate message.
 * The model is provided by Task3Model.
 */

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "classClicker", urlPatterns = {"/submit", "/getResults"})
public class Task3Servlet extends HttpServlet {
    Task3Model tm = null;   // The "business model" for this app
    boolean flag = false;   // flag to check if there are any results available or not

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        tm = new Task3Model();
    }

    // This servlet will reply to HTTP GET requests via this doGet method
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if the user wants to submit answers
        if(request.getServletPath().equals("/submit")){
            String answer = request.getParameter("options");
            // if any answer was just submitted, then display appropriate message
            if(!(answer == null)) {
                flag = true;    // results are now available
                tm.setResults(answer);
                // message showing user's submission has been recorded
                String message = "Your \"" + answer + "\" has been registered";
                request.setAttribute("message", message);
            }
            // Transfer control over to the correct "view"
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        }
        // if the user wants to view results
        else if (request.getServletPath().equals("/getResults")) {
            // if the user has submitted answers
            if(flag) {
                // show count of each option selected
                request.setAttribute("countA", tm.getCountA());
                tm.setCountA(0);
                request.setAttribute("countB", tm.getCountB());
                tm.setCountB(0);
                request.setAttribute("countC", tm.getCountC());
                tm.setCountC(0);
                request.setAttribute("countD", tm.getCountD());
                tm.setCountD(0);
                flag = false;   // results have been cleared
                // Transfer control over to the correct "view"
                RequestDispatcher view = request.getRequestDispatcher("results.jsp");
                view.forward(request, response);
            }
            // if the user has not submitted any new answers yet
            else {
                // Transfer control over to the correct "view"
                RequestDispatcher view = request.getRequestDispatcher("noresult.jsp");
                view.forward(request, response);
            }
        }
    }
}