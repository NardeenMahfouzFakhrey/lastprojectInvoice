package Controller;
import Model.InvoiceHeader;
import View.InvoiceFrame;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HeaderControllerTable extends AbstractTableModel {
    String[] names = { "No.", "Date" , "Customer" ,"Total"};
    private ArrayList<InvoiceHeader> Invoices;

    public HeaderControllerTable(ArrayList<InvoiceHeader> Invoices) {
        this.Invoices = Invoices;
    }

    @Override
    public int getRowCount() {
        return Invoices.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        InvoiceHeader invHeaders = Invoices.get(row);
        switch (col) {
            case 0:
                return invHeaders.getNum();
            case 1:
                return InvoiceFrame.dateformat.format(invHeaders.getDate());
            case 2:
                return invHeaders.getCusName();
            case 3:
                return invHeaders.getInvoiceTotalPrice();
        }
        return "";
    }
    @Override
    public String getColumnName(int c) {
      return names[c]; }
}
