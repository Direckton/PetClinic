/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.petclinicweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.model.Registration;

/**
 *
 * @author direc
 */
@WebServlet("/visits")
public class VisitsServlet extends HttpServlet {

    private Registration registration;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        @Override
    public void init() {
                registration = registration.getInstance();
            }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        

        
        int arg1 = Integer.parseInt(request.getParameter("id"));
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"css/main.css\">");            
            out.println("<title>Servlet VisitsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Pets</h1>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Id</td>");
            out.println("<td>Animal name</td>");
            out.println("<td>Age</td>");
            out.println("<td>Health</td>"); 
            out.println("</tr>");
            for(var visit : registration.getVisits(arg1)) //pass pet id from petsServlet
            {
                out.println("<tr>");
                out.println("<td>" + visit.getId() + "</td>");
                out.println("<td>" + visit.getDate()+ "</td>");
                out.println("<td>" + visit.getCostString()+ "</td>");
                out.println("<td>"); 
                if(visit.getHeld())
                {
                    out.println("<input type=\"checkbox\" id=\"wasHeld\" checked /> ");                
                }
                else
                {
                    out.println("<input type=\"checkbox\" id=\"wasHeld\" />");
                }
                out.println("</td>"); 
                out.println(""); 
                out.println(""); 
                out.println("</tr>");
                
            }
            out.println("</table>");
            out.println("<button>Add</button>");
            out.println("<button>Remove</button>");
            out.println("<button>Visits</button>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");

        if(id!= null) {
            PrintWriter out = response.getWriter();
            for(var visit : registration.getVisits(Integer.parseInt(id)))
            {
                out.println("<tr>");
                out.println("<td scope=\"row\">" + visit.getId() + "</td>");
                out.println("<td>" + visit.getTime().toLocalDate().toString() + "</td>");
                out.println("<td>" + visit.getTime().toLocalTime().toString() + "</td>");
                out.println("<td>" + visit.getCostString() + "</td>");
                out.println("<td>");
                out.println("<div class=\"form-check form-switch\">");
                out.print("<input class=\"form-check-input\" type=\"checkbox\" id=\"flexSwitchCheckChecked\"");
                if(visit.getHeld())
                {
                    out.print("checked");
                }
                out.println(">");
                out.println("</div>");
                out.println("</td>");
                out.println("<td>");
                out.println("<form method=\"POST\" action=\"/visits\">");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + visit.getId() + "\">");
                out.println("<input type=\"submit\" class=\"btn btn-outline-primary\" value=\"Medicines\" name=\"forward\">");
                out.println("</form>");
                out.println("</td>");
                out.println("<td>");
                out.println("<button type=\"button\" id=\"launch-modal\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" " +
                        "onclick=\"getVisit(" + visit.getId() + ");\">");
                out.println("Edit");
                out.println("</button>");
                out.println("</td>");
                out.println("<td>");
                out.println("<form method=\"POST\" action=\"/visits\">");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + visit.getId() + "\">");
                out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete\" name=\"delete\">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
        }

        String editId = request.getParameter("editId");
        String petId = request.getParameter("petId");

        if(editId!=null && petId!=null)
        {
            System.out.println(editId);
            System.out.println(petId);
//            PrintWriter out = response.getWriter();
//            var x = registration.getVisits();
//            for(var p :x)
//            {
//                if(p.getId()==Integer.parseInt(editId))
//                {
//                    String[] arr = {String.valueOf(p.getId()),p.getAnimal(),p.getAge(),p.getStringHealth()};
//                    out.write(Arrays.toString(arr));
//                }
//            }
        }


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
