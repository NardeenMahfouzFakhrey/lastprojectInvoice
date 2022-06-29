package View;

import javax.swing.*;
import java.awt.*;

public class LineText extends JDialog{
    private JTextField itemNameField;
    private JLabel itemNameLabel;

    private JTextField itemCountField;
    private JLabel itemCountLabel;

    private JTextField itemPriceField;
    private JLabel itemPriceLabel;

    private JButton okButton;
    private JButton cancelButton;

    public LineText(InvoiceFrame invoiceFrame) {
        itemNameLabel = new JLabel("Item Name : ");
        itemNameField = new JTextField(20);

        itemCountLabel = new JLabel("Count : ");
        itemCountField = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price : ");
        itemPriceField = new JTextField(20);

        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");

        okButton.setActionCommand("OKInvoiceLine");
        cancelButton.setActionCommand("CancelInvoiceLine");

        okButton.addActionListener(invoiceFrame.getMenuAction());
        cancelButton.addActionListener(invoiceFrame.getMenuAction());
        setLayout(new GridLayout(4, 2));

        setLayout(new GridLayout(4, 2));
        setSize(200, 200);
        setLocation(200, 300);

        add(itemNameLabel);
        add(itemNameField);
        add(itemCountLabel);
        add(itemCountField);
        add(itemPriceLabel);
        add(itemPriceField);
        add(okButton);
        add(cancelButton);

        pack();

    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}
