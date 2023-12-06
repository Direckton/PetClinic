/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.petclinicweb;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/pets")
public class PetsServlet extends HttpServlet {

    private Registration registration;
    
    @Override
    public void init() {
        registration = registration.getInstance();
      //  super.init();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String searchId = request.getParameter("searchId");
        
        boolean showAll = searchId == null || searchId.length() == 0;

        
        PrintWriter out = response.getWriter();

        for(var pet : registration.getPetData())
        {
            if(showAll || String.valueOf(pet.getId()).contains(searchId)
                    || pet.getAnimal().toLowerCase().contains(searchId.toLowerCase())){
                out.println("<tr>");
                out.println("<td scope=\"row\">" + pet.getId() + "</td>");
                out.println("<td>" + pet.getAnimal()+ "</td>");
                out.println("<td>" + pet.getAge()+ "</td>");
                out.println("<td>" + pet.getHealth().toString() + "</td>");
                out.println("<td>");
                out.println("<form method=\"GET\" action=\"/Visits\">");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + pet.getId() + "\">");
                out.println("<input type=\"submit\" class=\"btn btn-outline-primary\" value=\"Visit\" name=\"forward\">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
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
        processRequest(request, response);
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
        System.out.println("post request was called");
        //processRequest(request, response);
        if(request.getParameter("forward")!=null)
        {
            String id = request.getParameter("id");
            getServletContext().getRequestDispatcher("/Visits?arg="+id).forward(request,response);
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
