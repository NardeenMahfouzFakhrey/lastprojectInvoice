package Model;

public class InvoiceLine {
    private String productName;
    private double price;
    private int count;
    private InvoiceHeader invoiceHeader;

    public InvoiceLine(String productName, double price, int count, InvoiceHeader invoiceHeader) {
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.invoiceHeader = invoiceHeader;
    }

    public InvoiceLine() {
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public double getTotalLine(){
         return count*price;
     }
     

    @Override
    public String toString() {
        return invoiceHeader.getNum()+","+productName+","+price+","+count;
    }
    
}
