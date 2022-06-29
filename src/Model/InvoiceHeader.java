package Model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private int num;
    private String cusName;
    private Date date;
    private double invTotal;
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader() {
    }
    public InvoiceHeader(int num, String cusName, Date date) {
        this.num = num;
        this.cusName = cusName;
        this.date = date;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(double invTotal) {
        this.invTotal = invTotal;
    }


    public ArrayList<InvoiceLine> getInvoiceLines() {
        if(lines==null){
            lines=new ArrayList<>();
        }
        return lines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.lines = invoiceLines;
    }
    
     public double getInvoiceTotalPrice(){
        double totalPrice=0.0;
        int i;
        for(  i = 0;i < getInvoiceLines().size(); i++){
            totalPrice+=getInvoiceLines().get(i).getTotalLine();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Header{" +
                "num=" + num +
                ", cusName='" + cusName + '\'' +
                ", date=" + date +
                '}';
    }
}
