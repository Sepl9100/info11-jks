package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton test_button;
    private JPanel panel1;

    public App() {
        test_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Test");
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("JKS - Spielesammlung");

        frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(1280, 720);
        frame.setVisible(true);
    }
}
