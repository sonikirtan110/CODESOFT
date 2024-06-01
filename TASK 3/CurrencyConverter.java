import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame {
    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] currencies = { "USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR" };
    private final double[] exchangeRates = { 1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14 };

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setLayout(new GridLayout(5, 2, 10, 10));

        amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        fromLabel = new JLabel("From:");
        fromComboBox = new JComboBox<>(currencies);
        toLabel = new JLabel("To:");
        toComboBox = new JComboBox<>(currencies);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("", JLabel.CENTER);

        add(amountLabel);
        add(amountField);
        add(fromLabel);
        add(fromComboBox);
        add(toLabel);
        add(toComboBox);
        add(new JLabel("")); // Empty label for spacing
        add(convertButton);
        add(new JLabel("")); // Empty label for spacing
        add(resultLabel);

        convertButton.addActionListener(new ConvertButtonListener());

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount < 0) {
                    throw new NumberFormatException();
                }

                String fromCurrency = (String) fromComboBox.getSelectedItem();
                String toCurrency = (String) toComboBox.getSelectedItem();

                double result = convertCurrency(amount, fromCurrency, toCurrency);
                resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid positive number.");
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        }
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        int fromIndex = getCurrencyIndex(fromCurrency);
        int toIndex = getCurrencyIndex(toCurrency);

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException("Invalid currency.");
        }

        double exchangeRate = exchangeRates[toIndex] / exchangeRates[fromIndex];
        return amount * exchangeRate;
    }

    private int getCurrencyIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currencies[i].equals(currency)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
