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
    MySQLAccess db = new MySQLAccess(
      Config.DB_LOCATION,
      Config.DB_USER,
      Config.DB_PASS
    );
    
    // This will eventually point to a specific JSP
    String viewPath = "/views/edit";
    
    int editId = Integer.valueOf(id);
    
    switch(type){
      case "customer":
        if("post".equalsIgnoreCase(request.getMethod())){
          db.update(
              "customers", 
              request.getParameter("id"),
              new String[]{"first_name", "last_name", "address", "phone"},
              new String[]{
                request.getParameter("editFirstName"),
                request.getParameter("editLastName"),
                request.getParameter("editAddress"),
                request.getParameter("editPhone")
              }
            );
          response.sendRedirect("/BizTrackME/view");
          db.close();
          return;
        }else{
            Customer c = db.getCustomer(editId);
            if( c != null ){
              viewPath += "Customer.jsp";
              request.setAttribute("id", c.getID());
              request.setAttribute("firstName", c.getFirstName());
              request.setAttribute("lastName", c.getLastName());
              request.setAttribute("address", c.getAddress());
              request.setAttribute("phone", c.getPhone());
            }else{
              viewPath += "Error.jsp";
            }
        }
        break;
      case "product":
        if ("post".equalsIgnoreCase(request.getMethod())) {
          db.update(
            "products",
            request.getParameter("id"),
            new String[]{"product_name", "sku", "price", "color"},
            new String[]{
              request.getParameter("editProductName"),
              request.getParameter("editSku"),
              request.getParameter("editPrice"),
              request.getParameter("editColor")
            }
          );
          response.sendRedirect("/BizTrackME/view");
          db.close();
          return;
        }else{
          Product p = db.getProduct(editId);
          if (p != null) {
            viewPath += "Product.jsp";
            request.setAttribute("id", p.getID());
            request.setAttribute("productName", p.getProductName());
            request.setAttribute("sku", p.getSku());
            request.setAttribute("price", p.getPrice());
            request.setAttribute("color", p.getColor());
          }
          break;
        }
      default :
        response.sendRedirect("/BizTrackME/view");
        return;
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
