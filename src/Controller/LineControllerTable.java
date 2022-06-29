package Controller;

import Model.InvoiceLine;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class LineControllerTable extends AbstractTableModel {
    String [] names = {"No.Invoice" ,"Item Name" ,"Item Price" ,"Count" ,"Item Total"};

    private ArrayList<InvoiceLine> Lines;

    public LineControllerTable(ArrayList<InvoiceLine> Lines) {
        this.Lines = Lines;
    }

    @Override
    public int getRowCount() {
        return Lines == null ? 0 :  Lines.size();
    }

    @Override
    public int getColumnCount() {
        return  5;
    }


    @Override
    public String getColumnName(int c) {
        return names[c];
    }

    @Override
    public Object getValueAt(int row, int col) {
          InvoiceLine invLine = Lines.get(row);
        switch (col){
            case 0:return  invLine.getInvoiceHeader().getNum();
            case 1:return  invLine.getProductName();
            case 2:return  invLine.getPrice();
            case 3:return  invLine.getCount();
            case 4:return  invLine.getTotalLine();
        
        }
        
        return "";
    }

    public ArrayList<InvoiceLine> getLines() {
        return Lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        Lines = lines;
    }
}
