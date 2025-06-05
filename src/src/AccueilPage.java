package src;// === src.AccueilPage.java ===
import javax.swing.*;
import java.awt.*;


public class AccueilPage extends PagePanel {
    private Color Bleu = (new Color(10,210,210)), Noir = new Color(0,0,0);
    private Color InvertBleu = (new Color(58, 77, 160)), InvertNoir = new Color(220,220,220);
    private JButton btnVersFusion, btnVersTuto, btnVersCura;
    private JLabel titreLabel;

    public AccueilPage(String titre, Runnable onFusion, Runnable onTuto, Runnable onCura) {
        // Utilisation d'un layout nul pour positionnement absolu des composants
        setLayout(null);

        // Création et configuration du JLabel pour le titre de la page
        titreLabel = new JLabel(titre);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Police Arial, gras, taille 24
        titreLabel.setBackground(Noir); // Fond du label (inutile si opaque=false, mais défini pour cohérence)
        titreLabel.setForeground(Noir); // Couleur du texte du titre
        // Positionnement : centré horizontalement, à 10% de la hauteur de la fenêtre, largeur 325, hauteur 100
        titreLabel.setBounds(
                (getWidth() / 2) - (325 / 2),
                (getHeight() / 100) * 10,
                325,
                100
        );

        // Bouton vers l'application Fusion 360
        btnVersFusion = new JButton("<html><center>Application fusion <br>360</center></html>");
        btnVersFusion.setFont(new Font("Arial", Font.PLAIN, 16)); // Police Arial, normal, taille 16
        btnVersFusion.setBackground(Bleu); // Couleur de fond du bouton
        btnVersFusion.setForeground(Noir); // Couleur du texte du bouton
        // Action à exécuter lors du clic : exécute le Runnable onFusion
        btnVersFusion.addActionListener(e -> onFusion.run());
        // Positionnement : à environ 3/16 de la largeur, 35% de la hauteur, taille 150x100
        btnVersFusion.setBounds(
                (getWidth() / 16) * 3 - (150 / 2),
                (getHeight() / 100) * 35,
                150,
                100
        );

        // Bouton vers le tutoriel de modélisation 3D
        btnVersTuto = new JButton("<html><center>Tutoriel de modélisation <br>3D</center></html>");
        btnVersTuto.setFont(new Font("Arial", Font.PLAIN, 16)); // Police Arial, normal, taille 16
        btnVersTuto.setBackground(Bleu); // Couleur de fond du bouton
        btnVersTuto.setForeground(Noir); // Couleur du texte du bouton
        // Action à exécuter lors du clic : exécute le Runnable onTuto
        btnVersTuto.addActionListener(e -> onTuto.run());
        // Positionnement : centré horizontalement, à 35% de la hauteur, taille 150x100
        btnVersTuto.setBounds(
                (getWidth() / 2) - (150 / 2),
                (getHeight() / 100) * 35,
                150,
                100
        );

        // Bouton vers l'application d'impression 3D Cura
        btnVersCura = new JButton("<html><center>Application d'impression 3D Cura</center></html>");
        btnVersCura.setFont(new Font("Arial", Font.PLAIN, 16)); // Police Arial, normal, taille 16
        btnVersCura.setBackground(Bleu); // Couleur de fond du bouton
        btnVersCura.setForeground(Noir); // Couleur du texte du bouton
        // Action à exécuter lors du clic : exécute le Runnable onCura
        btnVersCura.addActionListener(e -> onCura.run());
        // Positionnement : à environ 13/16 de la largeur, 35% de la hauteur, taille 150x100
        btnVersCura.setBounds(
                (getWidth() / 16) * 13 - (150 / 2),
                (getHeight() / 100) * 35,
                150,
                100
        );

        // Ajout des composants à la page
        add(titreLabel);
        add(btnVersFusion);
        add(btnVersTuto);
        add(btnVersCura);
    }

    public void modeNuitAcceuil(boolean boolModeNuit) {
        if (boolModeNuit) {
            // ===== Mode nuit : texte clair sur fonds sombres =====

            // Titre : fond sombre, texte bleu clair inversé
            titreLabel.setBackground(InvertNoir);
            titreLabel.setForeground(InvertBleu);

            // Bouton Fusion : fond bleu inversé (clair), texte sombre inversé
            btnVersFusion.setBackground(InvertBleu);
            btnVersFusion.setForeground(InvertNoir);

            // Bouton Tuto : même style qu'au-dessus
            btnVersTuto.setBackground(InvertBleu);
            btnVersTuto.setForeground(InvertNoir);

            // Bouton Cura : même style qu'au-dessus
            btnVersCura.setBackground(InvertBleu);
            btnVersCura.setForeground(InvertNoir);
        } else {
            // ===== Mode clair : texte sombre sur fonds clairs =====

            // Titre : fond noir, texte noir (ou adapté au thème clair)
            titreLabel.setBackground(Noir);
            titreLabel.setForeground(Noir);

            // Bouton Fusion : fond bleu standard, texte noir
            btnVersFusion.setBackground(Bleu);
            btnVersFusion.setForeground(Noir);

            // Bouton Tuto : même style qu'au-dessus
            btnVersTuto.setBackground(Bleu);
            btnVersTuto.setForeground(Noir);

            // Bouton Cura : même style qu'au-dessus
            btnVersCura.setBackground(Bleu);
            btnVersCura.setForeground(Noir);
        }
    }
}
