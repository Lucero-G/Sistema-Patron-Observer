package patronobserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccountGUI extends JFrame {
    private BankAccount bankAccount;
    private BankExpense commission;
    private BankExpense expense;
    private BankExpense compensation;

    private JTextField amountField;
    private JLabel balanceLabel;
    private JLabel commissionLabel;
    private JLabel expenseLabel;
    private JLabel compensationLabel;

    public BankAccountGUI() {
        bankAccount = new BankAccount();
        commission = new BankExpense("Comisión", 0.11d);
        expense = new BankExpense("Gasto", 0.22d);
        compensation = new BankExpense("Compensación", 0.33d);

        bankAccount.addObserver(commission);
        bankAccount.addObserver(expense);
        bankAccount.addObserver(compensation);

        setTitle("Bank Account GUI");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Monto:"));
        amountField = new JTextField();
        add(amountField);

        JButton addButton = new JButton("Añadir Monto");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    bankAccount.addAmount(amount);
                    updateLabels();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankAccountGUI.this, "Por favor, ingresa un número válido");
                }
            }
        });
        add(addButton);

        JButton withdrawButton = new JButton("Retirar Monto");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    bankAccount.withdrawAmount(amount);
                    updateLabels();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankAccountGUI.this, "Por favor, ingresa un número válido");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(BankAccountGUI.this, ex.getMessage());
                }
            }
        });
        add(withdrawButton);

        balanceLabel = new JLabel("Balance: 0.0");
        add(balanceLabel);

        commissionLabel = new JLabel("Comisión: 0.0");
        add(commissionLabel);

        expenseLabel = new JLabel("Gasto: 0.0");
        add(expenseLabel);

        compensationLabel = new JLabel("Compensación: 0.0");
        add(compensationLabel);

        setVisible(true);
    }

    private void updateLabels() {
        balanceLabel.setText("Balance: " + bankAccount.getBalance());
        commissionLabel.setText("Comisión: " + commission.getTotalCalculated());
        expenseLabel.setText("Gasto: " + expense.getTotalCalculated());
        compensationLabel.setText("Compensación: " + compensation.getTotalCalculated());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankAccountGUI();
            }
        });
    }
}