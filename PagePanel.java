// === PagePanel.java ===
import javax.swing.*;
import java.awt.*;

public class PagePanel extends JPanel {
    public JLabel titre;

    public PagePanel(String titreTexte) {
        setLayout(null);
        setBackground(Color.WHITE);
        titre = new JLabel(titreTexte);
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        titre.setForeground(new Color(33, 33, 33)); // GRIS
        add(titre);
    }

    public void positionTitre(int width, int height) {
        Dimension size = titre.getPreferredSize();
        titre.setBounds((width - size.width) / 2, height / 100 * 5, size.width, size.height);
    }
}