package ds.project1task1;

/*
 * @author Sreemoyee Mukherjee
 *
 * The servlet is acting as the controller.
 * There are two views - index.jsp.
 *
 * This file also models the business logic for the web application.
 * In this case, the business logic involves computing MD5 or SHA-256 hashing(as per user request)
 * of the text entered by the user. We display the output both in Hex and Base64 notation.
 */

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

@WebServlet(name = "computeHashes", value = "/computeHashes")
public class ComputeHashes extends HttpServlet {
    private String method, text;
    byte[] result;

    public void init() {
    }

    // This servlet will reply to HTTP GET requests via this doGet method
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        method = request.getParameter("hash_method");
        text = request.getParameter("text");
        MessageDigest md = null;
        // Computing MD5 hashing
        if(method.equals("MD5")) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            md.update(text.getBytes());
            result = md.digest();
        }
        // Computing SHA-256 hashing
        else if (method.equals("SHA-256")) {
            try {
                md = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            md.update(text.getBytes());
            result = md.digest();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p> Original text: " + text + "</p>");
        out.println("<p> Name of hash function: " + method + "</p>");
        // Showing the output in Hex
        out.println("<p> Hash value (hexadecimal encoded): " + DatatypeConverter.printHexBinary(result) + "</p>");
        // Showing the output in Base64 notation
        out.println("<p> Hash value (Base64 encoded): " + DatatypeConverter.printBase64Binary(result) + "</p>");
        out.println("</body></html>");
    }
}