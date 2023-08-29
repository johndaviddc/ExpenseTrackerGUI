import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Expense {
    String description;
    double amount;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }
}

public class ExpenseTrackerGUI extends JFrame {
    private ArrayList<Expense> expenses;
    private JTextField descriptionField, amountField;
    private JTextArea expenseListArea;
    private double totalExpenses;

    public ExpenseTrackerGUI() {
        expenses = new ArrayList<>();
        totalExpenses = 0;

        setTitle("Expense Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        
        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        JPanel expensePanel = new JPanel();
        expensePanel.setLayout(new BorderLayout());
        
        expenseListArea = new JTextArea();
        expenseListArea.setEditable(false);
        expensePanel.add(new JScrollPane(expenseListArea), BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        JLabel totalLabel = new JLabel("Total Expenses: $0.00");
        summaryPanel.add(totalLabel);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(addButton, BorderLayout.CENTER);
        getContentPane().add(expensePanel, BorderLayout.CENTER);
        getContentPane().add(summaryPanel, BorderLayout.SOUTH);
    }

    private void addExpense() {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());

        expenses.add(new Expense(description, amount));
        totalExpenses += amount;

        updateExpenseList();
        updateTotalLabel();

        descriptionField.setText("");
        amountField.setText("");
    }

    private void updateExpenseList() {
        expenseListArea.setText("");
        for (Expense expense : expenses) {
            expenseListArea.append(expense.description + ": $" + expense.amount + "\n");
        }
    }

    private void updateTotalLabel() {
        totalExpenses = 0;
        for (Expense expense : expenses) {
            totalExpenses += expense.amount;
        }
        getContentPane().getComponent(3).removeAll();
        JLabel totalLabel = new JLabel("Total Expenses: $" + String.format("%.2f", totalExpenses));
        getContentPane().add(totalLabel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExpenseTrackerGUI().setVisible(true);
            }
        });
    }
}