/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.petclinicweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.model.Pet;
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
                out.print("<input class=\"form-check-input\" type=\"checkbox\" id=\"flexSwitchCheckChecked\" onclick=\"handleCheckbox(this," + visit.getId()+ "," + id + ");\"");
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
                out.println("<input type=\"hidden\" name=\"visitId\" value=\"" + visit.getId() + "\">");
                out.println("<input type=\"hidden\" name=\"petId\" value=\"" + id + "\">");
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
            PrintWriter out = response.getWriter();
            var x = registration.getVisits(Integer.parseInt(petId));
            for(var v :x)
            {
                if(v.getId()==Integer.parseInt(editId))
                {
                    String[] arr = {String.valueOf(v.getId()),v.getTime().toLocalDate().toString(),v.getTime().toLocalTime().toString(),v.getCostString()};
                    out.write(Arrays.toString(arr));
                }
            }
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
        //processRequest(request, response);
        System.out.println("POST in visits");
        if(request.getParameter("checkbox")!=null &&
        request.getParameter("visitId")!=null &&
        request.getParameter("petId")!=null)
        {
            boolean held = Boolean.parseBoolean(request.getParameter("checkbox"));
            int petId = Integer.parseInt(request.getParameter("petId"));
            int visitId = Integer.parseInt(request.getParameter("visitId"));
            var visits = registration.getVisits(petId);
            for(var v : visits)
            {
                if(v.getId() == visitId)
                {
                    v.setHeld(held);
                    break;
                }
            }
        }

        if(request.getParameter("saveEdit")!=null &&
                request.getParameter("petId")!=null&&
                request.getParameter("staticId")!=null)
        {
            int petId = Integer.parseInt(request.getParameter("petId"));
            int visitId = Integer.parseInt(request.getParameter("staticId"));
            var visits = registration.getVisits(petId);
            for(var v : visits)
            {
                if(v.getId() == visitId)
                {
                    String date = request.getParameter("inputDate");
                    String time = request.getParameter("inputTime");
                    String cost = request.getParameter("inputCost");
                    if(date != null && time != null && cost != null)
                    {
                        v.setDate(date+"T"+time);
                        v.setCost(Float.parseFloat(cost));
                    }

                }
            }
            response.sendRedirect("/visit.jsp?id="+petId);

        }


        if(request.getParameter("delete") != null)
        {
            String petId = request.getParameter("petId");
            String visitId = request.getParameter("visitId");
            var visits = registration.getVisits(Integer.parseInt(petId));
            for(var v : visits)
            {
                if(v.getId() == Integer.parseInt(visitId))
                {
                    visits.remove(v);
                    break;
                }
            }
            response.sendRedirect("/visit.jsp?id="+petId);

        }

        if(request.getParameter("add")!=null &&
        request.getParameter("petId")!=null)
        {
            int petId =Integer.parseInt(request.getParameter("petId"));
            var visits = registration.getVisits(petId);
            registration.CreateVisit(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),petId);
            response.sendRedirect("/visit.jsp?id="+petId);


        }
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
