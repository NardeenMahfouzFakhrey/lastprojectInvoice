package View;

import javax.swing.*;
import java.awt.*;

public class HeaderText extends JDialog {
    private JTextField customerNameField;
    private JTextField inDateField;
    private JLabel customerNameLabel;
    private JLabel invDateLabel;
    private JButton okButton;
    private JButton cancelButton;

    public HeaderText(InvoiceFrame invoiceFrame) {
        customerNameLabel = new JLabel("Customer Name : ");
        customerNameField = new JTextField(20);
        invDateLabel = new JLabel("Inv Date : ");
        inDateField = new JTextField(20);
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        okButton.setActionCommand("OKInvoiceHeader");
        cancelButton.setActionCommand("CancelInvoiceHeader");
        okButton.addActionListener(invoiceFrame.getMenuAction());
        cancelButton.addActionListener(invoiceFrame.getMenuAction());
        setLayout(new GridLayout(3, 2));

        add(customerNameLabel);
        add(customerNameField);
        add(invDateLabel);
        add(inDateField);
        add(okButton);
        add(cancelButton);
        pack();
        
    }
    public JTextField getCustomerNameTF() {
        return customerNameField;
    }

    public JTextField getDateTF() {
        return inDateField;
    }
}
