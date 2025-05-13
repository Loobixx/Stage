// === BoutonFactory.java ===

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoutonFactory {

    // Version avec couleur de survol personnalisée
    public static JButton creerBouton(Color couleur, Color couleurSurvol, Color couleurEcriture, String texte, ActionListener action, int size, String font) {
        JButton bouton = new JButton(texte);
        bouton.setFont(new Font(font, Font.PLAIN, size));
        bouton.setBackground(couleur);
        bouton.setForeground(couleurEcriture);
        bouton.setFocusPainted(false);
        bouton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        bouton.setPreferredSize(new Dimension(120, 40));
        bouton.addActionListener(action);

        bouton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bouton.setBackground(couleurSurvol); // Couleur de survol personnalisée
                bouton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bouton.setBackground(couleur);
                bouton.repaint();
            }
        });

        return bouton;
    }

    // Version simplifiée avec couleur de survol automatique (darker)
    public static JButton creerBouton(Color couleur, String texte, ActionListener action, int size, String font) {
        return creerBouton(couleur, couleur.darker(), Color.WHITE, texte, action, size, font);
    }

    // Version simplifiée par défaut (Arial, noir)
    public static JButton creerBouton(String texte, ActionListener action, int size) {
        return creerBouton(Color.BLACK, Color.DARK_GRAY, Color.WHITE, texte, action, size, "Arial");
    }

    public static JButton creerBouton(Color couleur, String texte, ActionListener action, int size) {
        return creerBouton(couleur, couleur.darker(), Color.WHITE, texte, action, size, "Arial");
    }

    // Version sans police personnalisée mais avec couleur de survol
    public static JButton creerBouton(Color couleur, Color couleurSurvol, String texte, ActionListener action, int size) {
        return creerBouton(couleur, couleurSurvol, Color.WHITE, texte, action, size, "Arial");
    }

    public static JButton creerBouton(Color couleur, Color couleurSurvol, Color couleurEcriture, String texte, ActionListener action, int size) {
        return creerBouton(couleur, couleurSurvol, couleurEcriture, texte, action, size, "Arial");
    }

}

