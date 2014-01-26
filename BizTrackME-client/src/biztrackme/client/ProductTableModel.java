package biztrackme.client;

import biztrackme.common.Product;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Eran
 */
public class ProductTableModel extends AbstractTableModel {

  private final ArrayList<String[]> data = new ArrayList();;
  private final String[] columnHeading = { "Product Name", "SKU", "Price", "Color" };

  public ProductTableModel(ArrayList<Product> products) {
    populateData(products);
  }  

  @Override
  public int getRowCount() {
    return data.size();
  }

  @Override
  public int getColumnCount() {
    return columnHeading.length;
  }

  @Override
  public Object getValueAt(int i, int i1) {
    return (data.get(i))[i1];
  }

  private void populateData(ArrayList<Product> products) {
    for(Product p : products){
      String[] insert = {
        p.getProductName(),
        p.getSku(),
        String.valueOf(p.getPrice()),
        p.getColor()
      };
      this.data.add(insert);
    }
  }
  
}
