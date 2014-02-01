package biztrackme;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import biztrackme.common.Customer;
import biztrackme.common.Product;
import biztrackme.server.MySQLAccess;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eran
 */
public class Edit extends HttpServlet {

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
    
    String fullPath = request.getPathInfo();
    String[] pathParts = fullPath.split("/");
    String type = pathParts[1];
    String id = pathParts[2];
    
    // Connect the Database
    String DB_URL = "jdbc:mysql://localhost/it351db";
    MySQLAccess db = new MySQLAccess(DB_URL, "ctuonline", "student");
    
    // This will eventually point to a specific JSP
    String viewPath = "/views/edit";
    
    int editId = Integer.valueOf(id);
    
    switch(type){
      case "customer":        
        Customer c = db.getCustomer(editId);
        if( c != null ){
          viewPath += "Customer.jsp";
          request.setAttribute("firstName", c.getFirstName());
          request.setAttribute("lastName", c.getLastName());
          request.setAttribute("address", c.getAddress());
          request.setAttribute("phone", c.getPhone());
        }else{
          viewPath += "Error.jsp";
        }
        break;
      case "product":
        Product p = db.getProduct(editId);
        if( p != null ){
          viewPath += "Product.jsp";
          request.setAttribute("productName", p.getProductName());
          request.setAttribute("sku", p.getSku());
          request.setAttribute("price", p.getPrice());
          request.setAttribute("color", p.getColor());
        }
        break;
      default :
        break;
    }
    
    getServletConfig().getServletContext().getRequestDispatcher(viewPath).forward(request, response);
    db.close();
    
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
