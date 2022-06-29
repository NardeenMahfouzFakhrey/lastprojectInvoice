package View;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Controller.LineControllerTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

public class ListenerSelector implements ListSelectionListener{
    private InvoiceFrame selectorInvoiceFrame;

    public ListenerSelector(InvoiceFrame selectorInvoiceFrame) {

        this.selectorInvoiceFrame = selectorInvoiceFrame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("selected"+selectorInvoiceFrame.getHeaderTabeL().getSelectedRow());
        if(selectorInvoiceFrame.getHeaderTabeL().getSelectedRow()!=-1){
        InvoiceHeader selInvoice = selectorInvoiceFrame.getArrInvoiceHeader().get(selectorInvoiceFrame.getHeaderTabeL().getSelectedRow());
        ArrayList<InvoiceLine> line = selectorInvoiceFrame.getArrInvoiceHeader().get(selectorInvoiceFrame.getHeaderTabeL().getSelectedRow()).getInvoiceLines();
        LineControllerTable modelLine = new LineControllerTable(line);
            selectorInvoiceFrame.setArrInvoiceLine(line);
            selectorInvoiceFrame.getLineTBL().setModel(modelLine);
            selectorInvoiceFrame.getInvNo().setText(""+selInvoice.getNum());
            selectorInvoiceFrame.getInvDt().setText(""+selInvoice.getDate());
            selectorInvoiceFrame.getInvNm().setText(selInvoice.getCusName());
            selectorInvoiceFrame.getInvTL().setText(""+selInvoice.getInvoiceTotalPrice());
        }

    }
}
