/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ACER
 */
@WebServlet(urlPatterns = {"/checkUser"})
public class checkUser extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            Persistencia base = new Persistencia();
            
            String usuario = request.getParameter("inputEmail");
            String clave =request.getParameter("inputPassword");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href=\'./css/bootstrap.min.css\' rel=\'stylesheet\'>");
            
            if(usuario != null && usuario.trim().length()> 0 &&
                clave != null && clave.trim().length()>0){   
                ResultSet rs = (ResultSet) base.consultaSQL("SELECT * FROM usuarios "
                        + "where usuario = '"+ usuario 
                        +"' and clave = '"+ clave +"'");

                while(rs.next()){
                    out.println("<title>BIENVENIDO</title>"); 
                    out.println("</head>");
                    out.println("<body class='bg-success'>"); 
                    out.println("<div class='container d-flex  justify-content-center'>");                 
                    out.println("<div class='align-self-center w-50 w-50 m-5 p-5 border border-primary'>"); 
                    out.println("<p class='text-center fw-bold' style='font-size:48px; font-weight: bold'>BIENVENIDO</p>");
                    out.println("<p class='text-center' style='font-size:24px;'>Usuario: "+ rs.getString("usuario")+"</p>");
                    out.println("<p class='text-center' style='font-size:24px;'>Nombre completo: " +rs.getString("nombrecompleto")+"</p>");                               
                    break;
                } 
                //if(rs == null){
                if(rs.first() == false){
                    out.println("<title>Error</title>"); 
                    out.println("</head>");
                    out.println("<body class='bg-danger'>");                 
                    out.println("<div class='container d-flex  justify-content-center'>");                 
                    out.println("<div class='align-self-center w-50 m-5 p-5 border border-warning'>"); 
                    out.println("<p class='text-center fw-bold' style='font-size:48px; font-weight: bold'>DATOS INVALIDOS</p>");
                    out.println("<p class='text-center' style='font-size:24px;'>¡Usuario o contraseña incorrectos!</p>");  
                }  
            }else{
                out.println("<title>Error</title>"); 
                out.println("</head>");
                out.println("<body class='bg-danger'>");                 
                out.println("<div class='container d-flex  justify-content-center'>");                 
                out.println("<div class='align-self-center w-50 m-5 p-5 border border-warning'>"); 
                out.println("<p class='text-center fw-bold' style='font-size:48px; font-weight: bold'>FALTA COMPLETAR CAMPO USUARIO O CONTRASEÑA</p>");
                out.println("<p class='text-center' style='font-size:24px;'>¡Falta indicar usuario o contraseña!</p>");  
            }
            out.println("<div class='d-flex  justify-content-around'>");  
            out.println("<btn class='btn btn-primary'><a class='text-decoration-none text-light' style='font-weight: bold' href=\"index.html\">Volver al inicio</a></btn>");  
            out.println("<btn class='btn btn-primary'><a class='text-decoration-none text-light' style='font-weight: bold' href=\"login.html\">Volver al login</a></btn>");  
            out.println("</div>"); 
            out.println("</div>"); 
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (SQLException ex) {
            Logger.getLogger(checkUser.class.getName()).log(Level.SEVERE, null, ex);
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
