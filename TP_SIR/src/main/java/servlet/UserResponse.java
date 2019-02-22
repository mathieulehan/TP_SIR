package main.java.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="userResponse",
urlPatterns={"/UserResponse"})
public class UserResponse extends HttpServlet {
public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
     throws ServletException, IOException {
    response.setContentType("text/html");

    PrintWriter out = response.getWriter();
    
    out.println("<HTML><head><meta charset=\"utf-8\"/></head>\n<BODY>\n" +
                "<a href='/'>Retour</a><br><H1>Recapitulatif de la réponse</H1>\n" +
                "<UL>\n" +            
        " <LI>02 janvier 2019 - 16h30: "
                + request.getParameter("coin1_response") + "\n" +
                " <LI>03 janvier 2019 - 14h30: "
                + request.getParameter("coin2_response") + "\n" +
                " <LI>05 janvier 2019 - 15h30: "
                + request.getParameter("coin3_response") + "\n" +
                " <LI>05 janvier 2019 - 17h30: "
                + request.getParameter("coin4_response") + "\n" +
                "</UL>\n" +                
        "</BODY></HTML>");
}
}
