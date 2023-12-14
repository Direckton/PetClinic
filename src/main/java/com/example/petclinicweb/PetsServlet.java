/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.petclinicweb;

import com.example.model.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.model.Entry;
import com.example.model.Pet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.model.Registration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author direc
 */
@WebServlet("/pets")
public class PetsServlet extends HttpServlet {

    private Registration registration;
    private Database db;
    
    @Override
    public void init() {
        registration = registration.getInstance();
        db = db.getInstance();
        
         var tmpPets = db.getPetData();
        
        for(var p : tmpPets)
        {
            registration.addNewPet(p);
        }
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

        if(searchId!=null)
        {
            boolean showAll = searchId.isEmpty();

            PrintWriter out = response.getWriter();

            for(var pet : registration.getPetData())
            {
                if(showAll || String.valueOf(pet.getId()).contains(searchId)
                        || pet.getAnimal().toLowerCase().contains(searchId.toLowerCase())){
                    out.println("<tr>");
                    out.println("<td scope=\"row\">" + pet.getId() + "</td>");
                    out.println("<td>" + pet.getAnimal()+ "</td>");
                    out.println("<td>" + pet.getAgeString()+ "</td>");
                    out.println("<td>" + pet.getHealth().toString() + "</td>");
                    out.println("<td>");
//                    out.println("<form method=\"GET\" action=\"/visits\">");
                    out.println("<form method=\"POST\" action=\"/pets\">");
                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + pet.getId() + "\">");
                    out.println("<input type=\"submit\" class=\"btn btn-outline-primary\" value=\"Visit\" name=\"forward\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<button type=\"button\" id=\"launch-modal\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" " +
                            "onclick=\"getPet(" + pet.getId() + ");\">");
                    out.println("Edit");
                    out.println("</button>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<form method=\"POST\" action=\"/pets\">");
                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + pet.getId() + "\">");
                    out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete\" name=\"delete\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
            }
        }

        String editId = request.getParameter("editId");
        if(editId!=null)
        {
            System.out.println(editId);
            PrintWriter out = response.getWriter();
            var x = registration.getPetData();
            for(var p :x)
            {
                if(p.getId()==Integer.parseInt(editId))
                {
                    String[] arr = {String.valueOf(p.getId()),p.getAnimal(),p.getAgeString(),p.getStringHealth()};
                    out.write(Arrays.toString(arr));
                }
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
//            getServletContext().getRequestDispatcher("/Visits?arg="+id).forward(request,response);
            response.sendRedirect("/visit.jsp?id="+id);
        }
        if(request.getParameter("delete")!=null)
        {
            String id = request.getParameter("id");
            registration.deleteRecord(Integer.parseInt(id));
            db.query("DELETE FROM Pet WHERE id = " + id);
            getServletContext().getRequestDispatcher("/pet.jsp").forward(request,response);
        }
        if(request.getParameter("saveEdit")!=null)
        {
            String id = request.getParameter("staticId");
            String animalName = request.getParameter("inputAnimal");
            String age = request.getParameter("inputAge");
            String health = request.getParameter("inputHealth");
            System.out.println(health);
            if(id!=null && animalName!=null && age!= null && health!=null) {
                var pet = registration.findPet(Integer.parseInt(id));
                registration.editPet(animalName, pet);
                registration.editPet(Integer.parseInt(age), pet);
                registration.editPet(Pet.Health.valueOf(health), pet);
                pet = registration.findPet(Integer.parseInt(id));
                
                db.query("UPDATE Pet SET animal = '" + pet.getAnimal()
                + "', age = " + pet.getAge() + ", health = '" + pet.getStringHealth()
                 + "'\n WHERE  id = " + pet.getId());
                
            }
            getServletContext().getRequestDispatcher("/pet.jsp").forward(request,response);

        }
        if(request.getParameter("add")!=null)
        {
            int id = 1;
            while(registration.checkValidId(id)) //true==id exists
            {
                id++;
            }
            Pet pet = new Pet(id, "Animal Name",0, Pet.Health.NA);
            registration.addNewRecord(pet,new ArrayList<>());
            db.insertData("Pet", new String(pet.getId()+",'"+ pet.getAnimal()+"',"+pet.getAgeString()+",'"+pet.getStringHealth()+"'"));
            getServletContext().getRequestDispatcher("/pet.jsp").forward(request,response);

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
