package src;// === src.AccueilPage.java ===
import javax.swing.*;
import java.awt.*;


public class AccueilPage extends PagePanel {
    private Color Bleu = (new Color(10,210,210)), Noir = new Color(0,0,0);
    private Color InvertBleu = (new Color(58, 77, 160)), InvertNoir = new Color(220,220,220);
    private JButton btnVersFusion, btnVersTuto, btnVersCura;
    private JLabel titreLabel;

    public AccueilPage(String titre, Runnable onFusion, Runnable onTuto, Runnable onCura, Runnable onFermer) {
        super("");

        setLayout(null);

        titreLabel = new JLabel(titre);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titreLabel.setBackground(Noir);
        titreLabel.setForeground(Noir);
        titreLabel.setBounds((getWidth() / 2 - 325 / 2), getHeight() / 100 * 10, 325, 100); // Titre


        btnVersFusion = new JButton("<html><center>Application fusion <br>360</center></html>");
        btnVersFusion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVersFusion.setBackground(Bleu);
        btnVersFusion.setForeground(Noir);
        btnVersFusion.addActionListener(e -> onFusion.run());
        btnVersFusion.setBounds(getWidth() / 16 * 3 - 150 / 2, getHeight() / 100 * 35, 150, 100); // btnVersFusion

        btnVersTuto = new JButton("<html><center>Tutoriel de modélisation <br>3D</center></html>");
        btnVersTuto.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVersTuto.setBackground(Bleu);
        btnVersTuto.setForeground(Noir);
        btnVersTuto.addActionListener(e -> onTuto.run());
        btnVersTuto.setBounds(getWidth() / 2 - 150 / 2, getHeight() / 100 * 35, 150, 100);  // btnVersTuto

        btnVersCura = new JButton("<html><center>Application d'impression 3D Cura</center></html>");
        btnVersCura.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVersCura.setBackground(Bleu);
        btnVersCura.setForeground(Noir);
        btnVersCura.addActionListener(e -> onCura.run());
        btnVersCura.setBounds(getWidth() / 16 * 13 - 150 / 2, getHeight() / 100 * 35, 150, 100);  // btnVersCura

        add(titreLabel);
        add(btnVersFusion);
        add(btnVersTuto);
        add(btnVersCura);

    }

    public void modeNuitAcceuil(boolean boolModeNuit) {
        if (boolModeNuit) {
            titreLabel.setBackground(InvertNoir);
            titreLabel.setForeground(InvertBleu);

            btnVersFusion.setBackground(InvertBleu);
            btnVersFusion.setForeground(InvertNoir);

            btnVersTuto.setBackground(InvertBleu);
            btnVersTuto.setForeground(InvertNoir);

            btnVersCura.setBackground(InvertBleu);
            btnVersCura.setForeground(InvertNoir);
        }
        else {
            titreLabel.setBackground(Noir);
            titreLabel.setForeground(Noir);

            btnVersFusion.setBackground(Bleu);
            btnVersFusion.setForeground(Noir);

            btnVersTuto.setBackground(Bleu);
            btnVersTuto.setForeground(Noir);

            btnVersCura.setBackground(Bleu);
            btnVersCura.setForeground(Noir);

        }

    }
}
