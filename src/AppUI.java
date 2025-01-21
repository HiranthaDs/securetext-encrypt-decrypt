import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppUI {
    private final JFrame frame;
    private final JTextArea inputTextArea;
    private final JTextArea outputTextArea;
    private final EncryptorDecryptor encryptorDecryptor;

    public AppUI() {
        frame = new JFrame("Encryption & Decryption App");
        inputTextArea = new JTextArea(5, 30);
        outputTextArea = new JTextArea(5, 30);
        encryptorDecryptor = new EncryptorDecryptor();
    }

    public void showUI() {
        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Text"));
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);

        // Output Panel
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output Text"));
        outputPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        outputTextArea.setEditable(false);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");

        encryptButton.addActionListener(this::encryptAction);
        decryptButton.addActionListener(this::decryptAction);

        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void encryptAction(ActionEvent e) {
        String inputText = inputTextArea.getText();
        if (!inputText.isEmpty()) {
            String encryptedText = encryptorDecryptor.encrypt(inputText);
            outputTextArea.setText(encryptedText);
        } else {
            JOptionPane.showMessageDialog(frame, "Input text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void decryptAction(ActionEvent e) {
        String inputText = inputTextArea.getText();
        if (!inputText.isEmpty()) {
            try {
                String decryptedText = encryptorDecryptor.decrypt(inputText);
                outputTextArea.setText(decryptedText);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid encrypted text.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Input text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
