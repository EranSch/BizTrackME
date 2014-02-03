package biztrackme;

import biztrackme.common.Customer;
import biztrackme.common.Product;
import biztrackme.server.MySQLAccess;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eran
 */
public class Add extends HttpServlet {

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

    // Connect the Database
    MySQLAccess db = new MySQLAccess(
      Config.DB_LOCATION,
      Config.DB_USER,
      Config.DB_PASS
    );
    
    // This will eventually point to a specific JSP
    String addPath = "/views/add";
     
    switch(type){
      case "customer":
        if("post".equalsIgnoreCase(request.getMethod())){
          db.addCustomer(new Customer(
            request.getParameter("firstName"),
            request.getParameter("lastName"),
            request.getParameter("address"),
            request.getParameter("phone")
          ));
          response.sendRedirect("/BizTrackME/view");
          db.close();
          return;
        }else{
          addPath += "Customer.jsp";
        }
        break;
      case "product":
        if("post".equalsIgnoreCase(request.getMethod())){
          db.addProduct(new Product(
            request.getParameter("productName"),
            request.getParameter("sku"),
            Double.valueOf(request.getParameter("price")),
            request.getParameter("color")
          ));
          response.sendRedirect("/BizTrackME/view");
          db.close();
          return;
        }else{
          addPath += "Product.jsp";
        }    
        break;
      default:
        response.sendRedirect("/BizTrackME/view");
        return;
    }
    
    getServletConfig().getServletContext().getRequestDispatcher(addPath).forward(request, response);
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
