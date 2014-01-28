package biztrackme.client;

import biztrackme.common.Product;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Eran
 */
public class ProductTableModel extends AbstractTableModel {

  private ArrayList<String[]> data = new ArrayList();;
  private final String[] columnHeading = { "ID", "Product Name", "SKU", "Price", "Color" };

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
  
  public boolean isCellEditable(int row, int col) {
    if(col == 0){
      return false;
    }
    return true;
  }
  
  public String getColumnName(int col) {
    return columnHeading[col].toString();
  }
  
  public void setValueAt(Object value, int row, int col) {
    data.get(row)[col] = value.toString();
    fireTableCellUpdated(row, col);
  }

  public void populateData(ArrayList<Product> products) {
    data.clear();
    for(Product p : products){
      String[] insert = {
        String.valueOf(p.getID()),
        p.getProductName(),
        p.getSku(),
        String.valueOf(p.getPrice()),
        p.getColor()
      };
      this.data.add(insert);
    }
  }
  
}
