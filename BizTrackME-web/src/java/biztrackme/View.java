package biztrackme;

import java.io.IOException;
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
      
      String custTable = "<div class=\"panel  panel-info\"><div class=\"panel-heading\"><a class=\"btn btn-success pull-right\">+</a><h2>Customers</h2></div><div class=\"panel-body\">Click the plus sign to right to add a new Customer</div><table class=\"table\">";
      custTable += "<tr><th>First Name</th><th>Last Name</th><th>Address</th><th>Phone</th></tr>";
      custTable += "<tr><td>Eran</td><td>Schoellhorn</td><td>15907 NW 188th St</td><td>(970) 325-3726</td></tr>";
      custTable += "</table></div>";
      
      String prodTable = "<div class=\"panel  panel-info\"><div class=\"panel-heading\"><a class=\"btn btn-success pull-right\">+</a><h2>Products</h2></div><div class=\"panel-body\">Click the plus sign to right to add a new Customer</div><table class=\"table\">";
      prodTable += "<tr><th>First Name</th><th>Last Name</th><th>Address</th><th>Phone</th></tr>";
      prodTable += "<tr><td>Eran</td><td>Schoellhorn</td><td>15907 NW 188th St</td><td>(970) 325-3726</td></tr>";
      prodTable += "</table></div>";
      
      request.setAttribute("custTable", custTable);
      request.setAttribute("prodTable", prodTable);
      
      getServletConfig().getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
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
