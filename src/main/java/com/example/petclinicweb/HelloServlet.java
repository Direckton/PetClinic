package com.example.petclinicweb;

import com.example.model.Database;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/init")
public class HelloServlet extends HttpServlet {

    private Database db;

    public void init() {
        db = db.getInstance();
 
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String dbstatus = request.getParameter("db");
        
        if(dbstatus != null)
        {
            if(!db.DBStatus())
            {
                PrintWriter out = response.getWriter();
                out.println("<div class=\"alert alert-danger d-flex align-items-center\" role=\"alert\">");
                out.println("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" class=\"bi bi-exclamation-triangle-fill flex-shrink-0 me-2\" viewBox=\"0 0 16 16\" role=\"img\" aria-label=\"Warning:\">");
                out.println("<path d=\"M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"/>");
                out.println("</svg>");
                out.println("<div>");
                out.println("Database has been disconnected. Changes won't be saved!");
                out.println("</div>");
                out.println("</div>");
                
                db.connectToDB();
            }
        }
    }

    public void destroy() {
    }
}