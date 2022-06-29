package View;
import Controller.HeaderControllerTable;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Controller.LineControllerTable;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class ActionListener implements java.awt.event.ActionListener {
    public InvoiceFrame Frame;
    private HeaderText headerText;
    private LineText LineText;
    private SimpleDateFormat dF = new SimpleDateFormat("dd-MM-yyyy");
    public ActionListener(InvoiceFrame invoiceFrame){
        this.Frame = invoiceFrame;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Load Files":
                loadFiles();
                break;
            case "Save Files":
                try {
                    saveFiles();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Create Invoice Items":
                CreateInvoiceItems();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Cancel Line":
                CancelItems();
                break;
            case "OKInvoiceHeader":
                ok();
                break;
            case "CancelInvoiceHeader":
                cancelInvoice();
                break;
            case "OKInvoiceLine":
                okLine();
                break;
            case "CancelInvoiceLine":
                cancelInvoiceLine();
                break;

            default:
                throw new AssertionError();
        }
    }

    private void saveFiles() throws FileNotFoundException {
        ArrayList<InvoiceHeader> ArrayOfInvoices = Frame.getArrInvoiceHeader();
        JOptionPane.showMessageDialog(Frame, "Please Choose File Header to Save", "Invoice Header", 2);
        JFileChooser fileChoosers = new JFileChooser();
        int option = fileChoosers.showOpenDialog(Frame);
        File Csvfile;
        PrintWriter out;
        Iterator i;
        InvoiceHeader header;
        if (option == 0) {
            Csvfile = fileChoosers.getSelectedFile();
            out = new PrintWriter(Csvfile);
            i = ArrayOfInvoices.iterator();
            while(i.hasNext()) {
                header = (InvoiceHeader)i.next();
                out.printf("%d,%s,%s", header.getNum() , header.getCusName() ,header.getDate().toString());
                out.println();
            }

            out.close();
            JOptionPane.showMessageDialog(Frame, "Successfully Header Saved", "Information", 1);
        }

        JOptionPane.showMessageDialog(Frame, "Please Choose File Line to Save", "Invoice Line", 2);
        fileChoosers = new JFileChooser();
        option = fileChoosers.showOpenDialog(Frame);
        if (option == 0) {
            Csvfile = fileChoosers.getSelectedFile();
            out = new PrintWriter(Csvfile);

            i = ArrayOfInvoices.iterator();

            while(i.hasNext()) {
                header = (InvoiceHeader)i.next();
                Iterator var7 = header.getInvoiceLines().iterator();

                while(var7.hasNext()) {
                    InvoiceLine Lines = (InvoiceLine)var7.next();
                    out.printf("%s ,%s,%d", Lines.getProductName(), Lines.getPrice(), Lines.getCount());
                    out.println();
                }
            }
            out.close();
            JOptionPane.showMessageDialog(Frame, "Successfully Lines Saved", "Information", 1);
        }

    }
    
    private void loadFiles() {
        JOptionPane.showMessageDialog(Frame, "Select Invoice header file", "Inv Header", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        try{
            int R = fileChooser.showOpenDialog(Frame);
            if (R ==JFileChooser.APPROVE_OPTION){
                File Header = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(Header.getAbsolutePath());
                java.util.List<String> headerLines =Files.readAllLines(headerPath);
                ArrayList<InvoiceHeader>invoiceHeaders=new ArrayList<>();
                for(String headerLine : headerLines ){
                    String[] HeaderLines = headerLine.split(",");
                    String HeaderLines1 = HeaderLines[0];
                    String HeaderLines2 = HeaderLines[1];
                    String HeaderLines3 = HeaderLines[2];
                    int id = Integer.parseInt(HeaderLines1);
                    Date dateHeader = dF.parse(HeaderLines2);
                    InvoiceHeader headers = new InvoiceHeader(id,HeaderLines3,dateHeader);
                    invoiceHeaders.add(headers);
               
                }
                Frame.setArrayInvoice(invoiceHeaders);
                JOptionPane.showMessageDialog(Frame, "Select Invoice Line files", "Inv Line", JOptionPane.WARNING_MESSAGE);
                R=fileChooser.showOpenDialog(Frame);
                 if (R ==JFileChooser.APPROVE_OPTION){
                     File Lines = fileChooser.getSelectedFile();
                     Path linePath = Paths.get(Lines.getAbsolutePath());
                     java.util.List<String> invLines =Files.readAllLines(linePath);
                     ArrayList<InvoiceLine>invoiceLines=new ArrayList<>();
                     for(String invLine:invLines){
                        String[] invLinee = invLine.split(",");
                        String arr1 = invLinee[0];
                        String arr2 = invLinee[1];
                        String arr3 = invLinee[2];
                        String arr4 = invLinee[3];
                        int id = Integer.parseInt(arr1);
                        double price = Double.parseDouble(arr3);
                        int count = Integer.parseInt(arr4);
                        InvoiceHeader invoiceHeader = Frame.getItems(id);
                        InvoiceLine InvoiceLine = new InvoiceLine(arr2,price,count,invoiceHeader);
                        invoiceHeader.getInvoiceLines().add(InvoiceLine);

                     }
                 }
                 HeaderControllerTable table = new HeaderControllerTable(invoiceHeaders);
                 Frame.setTableModelInvoice(table);
                 Frame.getHeaderTabeL().setModel(table);
                 
                
            
            }
    }catch(IOException e){
            JOptionPane.showMessageDialog(Frame, "wrong file format","Error",JOptionPane.ERROR_MESSAGE);
    }   catch (ParseException ex) {
            JOptionPane.showMessageDialog(Frame, "Error" ,"Error",JOptionPane.ERROR_MESSAGE);
        }}


    private void createNewInvoice() {
        headerText = new HeaderText(Frame);
        headerText.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedInvoiceIndex = Frame.getHeaderTabeL().getSelectedRow();
        if (Frame.getHeaderTabeL().getSelectedRow() != -1) {
            Frame.getArrInvoiceHeader().remove(selectedInvoiceIndex);
            Frame.getTableInvoice().fireTableDataChanged();

            Frame.getLineTBL().setModel(new LineControllerTable(null));
            Frame.setArrInvoiceLine(null);
            Frame.getInvNm().setText("");
            Frame.getInvNm().setText("");
            Frame.getInvTL().setText("");
            Frame.getInvDt().setText("");
        }
    }

    private void CreateInvoiceItems() {
        LineText = new LineText(Frame);
        LineText.setVisible(true);
        
    }

    private void CancelItems() {
        
        if (Frame.getLineTBL().getSelectedRow() != -1) {
            Frame.getArrInvoiceLine().remove(Frame.getLineTBL().getSelectedRow());
            LineControllerTable lineModel = (LineControllerTable)Frame.getLineTBL().getModel();
            lineModel.fireTableDataChanged();
            Frame.getInvTL().setText(""+Frame.getArrInvoiceHeader().get(Frame.getHeaderTabeL().getSelectedRow()).getInvoiceTotalPrice());
            Frame.getTableInvoice().fireTableDataChanged();
            Frame.getHeaderTabeL().setRowSelectionInterval(Frame.getHeaderTabeL().getSelectedRow(), Frame.getHeaderTabeL().getSelectedRow());
    }
 
    }

    private void ok() {
        headerText.setVisible(false);
        String customerName = headerText.getCustomerNameTF().getText();
        String date = headerText.getDateTF().getText();
        Date datee = new Date();
        try {
            datee=Frame.dateformat.parse(date);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(Frame, "error data","invalid",JOptionPane.ERROR_MESSAGE);
        }
        int Num = 0;
        for (InvoiceHeader invH : Frame.getArrInvoiceHeader()) {
            if (invH.getNum() > Num) {
                Num = invH.getNum();
            }
        }
        Num++;
        InvoiceHeader InvoiceHeader= new InvoiceHeader(Num, customerName, datee);
        Frame.getArrInvoiceHeader().add(InvoiceHeader);
        Frame.getTableInvoice().fireTableDataChanged();
        headerText.dispose();
        headerText=null;
    }

    private void cancelInvoice() {
        headerText.setVisible(false);
        headerText.dispose();
        headerText = null;
    }


    private void okLine() {
        LineText.setVisible(false);
        
        String nameItem = LineText.getItemNameField().getText();
        String countItem = LineText.getItemCountField().getText();
        String priceItem = LineText.getItemPriceField().getText();
        int c = 1;
        double Price = 1;
        try {
            c = Integer.parseInt(countItem);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(Frame, "error data", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            Price = Double.parseDouble(priceItem);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(Frame, "error data", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
        int selectedHeader = Frame.getHeaderTabeL().getSelectedRow();
        if (selectedHeader != -1) {
            InvoiceHeader Header = Frame.getArrInvoiceHeader().get(selectedHeader);
            InvoiceLine line = new InvoiceLine(nameItem, Price, c, Header);
            Frame.getArrInvoiceLine().add(line);
            LineControllerTable lineTableModel = (LineControllerTable) Frame.getLineTBL().getModel();
            lineTableModel.fireTableDataChanged();
            Frame.getTableInvoice().fireTableDataChanged();
        }
        Frame.getHeaderTabeL().setRowSelectionInterval(selectedHeader, selectedHeader);
        LineText.dispose();
        LineText = null;
    }

    private void cancelInvoiceLine() {
        LineText.setVisible(false);
        LineText.dispose();
        LineText = null;
    }
    
}
