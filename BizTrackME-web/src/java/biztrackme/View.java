package biztrackme;

import biztrackme.common.Customer;
import biztrackme.common.Product;
import biztrackme.server.MySQLAccess;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eran
 */
public class View extends HttpServlet {

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
    try {
      
      // Connect the Database
      MySQLAccess db = new MySQLAccess(
        Config.DB_LOCATION,
        Config.DB_USER,
        Config.DB_PASS
      );
      
      // Build the customer table
      ArrayList<Customer> c = db.getCustomers();      
      StringBuilder custTable = new StringBuilder();
      
      custTable.append("<div class=\"panel panel-default\"><div class=\"panel-heading\"><a class=\"btn btn-success pull-right\" href=\"/BizTrackME/add/customer\">+</a><h2>Customers</h2></div><div class=\"panel-body\">Click the plus sign to right to add a new Customer</div><table class=\"table\">");
      custTable.append("<thead><tr><th>First Name</th><th>Last Name</th><th>Address</th><th>Phone</th><th>Edit</th><th>Delete</th></tr></thead>");      
      for( Customer cust : c ){
        custTable.append("<tr>");
        custTable.append("<td>").append(cust.getFirstName()).append("</td>");
        custTable.append("<td>").append(cust.getLastName()).append("</td>");
        custTable.append("<td>").append(cust.getAddress()).append("</td>");
        custTable.append("<td>").append(cust.getPhone()).append("</td>");
        custTable.append("<td>").append("<a class=\"btn btn-primary\" href=\"edit/customer/").append(cust.getID()).append("\">Edit</a>").append("</td>");
        custTable.append("<td>").append("<a class=\"btn btn-danger\" href=\"delete/customer/").append(cust.getID()).append("\">Delete</a>").append("</td>");
        custTable.append("</tr>");
      }      
      custTable.append("</table></div>");
      
      // Build the product table
      ArrayList<Product> p = db.getProducts();
      StringBuilder prodTable = new StringBuilder();
      
      prodTable.append("<div class=\"panel panel-default\"><div class=\"panel-heading\"><a class=\"btn btn-success pull-right\" href=\"/BizTrackME/add/product\">+</a><h2>Products</h2></div><div class=\"panel-body\">Click the plus sign to right to add a new Product</div><table class=\"table\">");
      prodTable.append("<thead><tr><th>Product Name</th><th>SKU</th><th>Price</th><th>Color</th><th>Edit</th><th>Delete</th></tr></thead>");
      for( Product prod : p ){
        prodTable.append("<tr>");
        prodTable.append("<td>").append(prod.getProductName()).append("</td>");
        prodTable.append("<td>").append(prod.getSku()).append("</td>");
        prodTable.append("<td>").append(prod.getPrice()).append("</td>");
        prodTable.append("<td>").append(prod.getColor()).append("</td>");
        prodTable.append("<td>").append("<a class=\"btn btn-primary\" href=\"edit/product/").append(prod.getID()).append("\">Edit</a>").append("</td>");        
        prodTable.append("<td>").append("<a class=\"btn btn-danger\" href=\"delete/product/").append(prod.getID()).append("\">Delete</a>").append("</td>");        
        prodTable.append("</tr>");
      }
      prodTable.append("</table></div>");
      
      request.setAttribute("custTable", custTable.toString());
      request.setAttribute("prodTable", prodTable);     
      
      getServletConfig().getServletContext().getRequestDispatcher("/views/view.jsp").forward(request, response);
      
      db.close();
      
    } catch (IOException | ServletException ex) {
      System.err.println(ex.getStackTrace());
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
