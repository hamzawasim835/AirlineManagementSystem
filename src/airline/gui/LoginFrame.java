package airline.gui;

import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton userBtn = new JButton("User");
        JButton adminBtn = new JButton("Admin");

        userBtn.addActionListener(e -> {
            new MainFrame("USER");
            dispose();
        });

        adminBtn.addActionListener(e -> {
            new MainFrame("ADMIN");
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(userBtn);
        panel.add(adminBtn);

        add(panel);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
