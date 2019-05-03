package com.example.search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie/SetCookie")
public class SetCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Cookie[] requestCookies = req.getCookies();
        out.write("<html><head></head><body>");
        out.write("<h3>Hello Browser!!</h3>");
        if (requestCookies != null) {
            out.write("<h3>Request Cookies:</h3>");
        }
        for (Cookie c : requestCookies) {
            out.write("Name=" + c.getName() + ",value=" + c.getValue() + ",comment=" + c.getComment() + ",Domain=" + c.getDomain() + ",MaxAge=" + c.getMaxAge() + ",Path=" + c.getPath() + ",Version=" + c.getVersion());
            out.write("<br>");
        }
        count++;
        Cookie counterCookie = new Cookie("counter", String.valueOf(count));
        counterCookie.setComment("Setcookie counter");
        counterCookie.setMaxAge(24 * 60 * 60);
        counterCookie.setPath("/cookie/SetCookie");
        resp.addCookie(counterCookie);
        Cookie domainCookie = new Cookie("test", "TestCookie" + String.valueOf(count));
        resp.addCookie(domainCookie);
        out.write("</body></html>");
    }
}
