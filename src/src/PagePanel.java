package src; // Définit le package auquel appartient cette classe

import javax.swing.*; // Importation de Swing pour les composants graphiques
import java.awt.*;    // Importation d’AWT pour les couleurs et autres outils graphiques

public class PagePanel extends JPanel {
    public PagePanel() {
        setLayout(null);         // Utilise un layout nul pour positionnement absolu des composants
        setBackground(Color.WHITE); // Définit la couleur de fond du panneau en blanc
    }
}