package biztrackme.client;

import biztrackme.common.Customer;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Eran
 */
public class CustomerTableModel extends AbstractTableModel {

  private final ArrayList<String[]> data = new ArrayList();
  private final String[] columnHeading = { "ID", "First Name", "Last Name", "Address", "Phone" };

  public CustomerTableModel(ArrayList<Customer> customers) {
    populateData(customers);
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
    return true;
  }

  public String getColumnName(int col) {
    return columnHeading[col].toString();
  }

  private void populateData(ArrayList<Customer> customers) {
    for(Customer c : customers){
      String[] obj = new String[columnHeading.length];
      obj[0] = String.valueOf(c.getID());
      obj[1] = c.getFirstName();
      obj[2] = c.getLastName();
      obj[3] = c.getAddress();
      obj[4] = c.getPhone();
      data.add(obj);
    }
  }
  
}
