package biztrackme;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eran
 */
public class Order extends HttpServlet {

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
    
    // Connect the Database
    MySQLAccess db = new MySQLAccess(
      Config.DB_LOCATION,
      Config.DB_USER,
      Config.DB_PASS
    );
    
    // Build the product table
    ArrayList<Product> p = db.getProducts();
    
    if ("post".equalsIgnoreCase(request.getMethod())) {
      
      double totalCost = 0;
      
      Enumeration<String> parameterNames = request.getParameterNames();
      
      while (parameterNames.hasMoreElements()) {
        String param = parameterNames.nextElement();                
        try{
          int q = Integer.parseInt(request.getParameter(param));
          int id= Integer.parseInt(param.split("-")[1]);
          for(Product prod : p){
            if(prod.getID() == id){
              totalCost += prod.getPrice() * q;
              break;
            }
          }                      
        }catch(NumberFormatException ex){          
        }               
      }

      request.setAttribute("totalCost", totalCost);
      
      getServletConfig().getServletContext().getRequestDispatcher("/views/success.jsp").forward(request, response);
      
    }else{
           
      StringBuilder prodTable = new StringBuilder();

      prodTable.append("<div class=\"panel panel-default\"><div class=\"panel-heading\"><h2>Products (Total: ").append(p.size()).append(")</h2></div><div class=\"panel-body\">Enter quantities for the products you would like to order</div><table class=\"table\">");
      prodTable.append("<thead><tr><th>Product Name</th><th>SKU</th><th>Price</th><th>Color</th><th>Quantity</th></tr></thead>");
      for (Product prod : p) {
        prodTable.append("<tr>");
        prodTable.append("<td>").append(prod.getProductName()).append("</td>");
        prodTable.append("<td>").append(prod.getSku()).append("</td>");
        prodTable.append("<td>").append(prod.getPrice()).append("</td>");
        prodTable.append("<td>").append(prod.getColor()).append("</td>");
        prodTable.append("<td>").append("<input type='number' class='form-control' placeholder='0' name='qty-").append(prod.getID()).append("'>").append("</td>");
        prodTable.append("</tr>");
      }
      prodTable.append("</table></div>");

      request.setAttribute("prodTable", prodTable);

      getServletConfig().getServletContext().getRequestDispatcher("/views/order.jsp").forward(request, response);    
      
    }
    
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
