import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FusionPage extends PagePanel {
    private JButton btnVersTuto;
    private JTabbedPane onglets;

    public FusionPage(int width, int height, Runnable actionVersTuto) {
        super("");
        setLayout(null);

        // Bouton vers le tuto
        btnVersTuto = BoutonFactory.creerBouton("Tuto", e -> actionVersTuto.run(), 11);
        btnVersTuto.setBounds(width - 120, height - 70, 80, 20);
        add(btnVersTuto);
        setComponentZOrder(btnVersTuto, 0); // Le place au-dessus de tous les autres composants


        // Onglets
        UIManager.put("TabbedPane.selected", Color.WHITE); // Fond onglet actif
        UIManager.put("TabbedPane.contentAreaColor", Color.WHITE); // Fond de la zone des onglets
        UIManager.put("TabbedPane.focus", Color.WHITE); // Couleur de focus (bordures)

        onglets = new JTabbedPane();
        onglets.setBounds(10, 10, width - 20, height - 50);
        onglets.setBackground(Color.WHITE);
        onglets.setForeground(Color.BLACK);

        onglets.addTab("Découverte", createOngletDecouverte());
        onglets.addTab("Types", createOngletTypes());
        onglets.addTab("Outils", createOngletOutils(width, height));
        onglets.addTab("Applications", createOngletApplications());
        onglets.addTab("Vocabulaire", createOngletVocabulaire());

        add(onglets);

        // Redimensionnement dynamique
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onglets.setBounds(10, 10, width - 20, height - 50);
                btnVersTuto.setBounds(width - 120, height - 80, 80, 20);
                setComponentZOrder(btnVersTuto, 0);
            }
        });
    }

    private JPanel createOngletDecouverte() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // contour blanc

        JEditorPane text = new JEditorPane();
        text.setContentType("text/html");
        text.setText("""
<html>
    <body style='font-family: Arial; font-size: 13px; line-height: 1.6; color: #333333; background-color: #ffffff; padding: 10px;'>

        <h2 style='color: #2a7ae2;'>Découvrir la modélisation 3D avec Fusion 360</h2>

        <p>
            <b>Fusion 360</b> est un logiciel complet de modélisation 3D développé par Autodesk.
            Il permet de passer d’une idée à un objet réel en combinant des outils de conception, d’usinage et de simulation.
        </p>

        <h3 style='color: #4a90e2;'>1. Créer une esquisse</h3>
        <ul>
            <li>Cliquez sur <i>“Créer une esquisse”</i> dans la barre d’outils.</li>
            <li>Sélectionnez un plan (horizontal, vertical, latéral) ou une face d’un objet existant.</li>
            <li>Vous entrez alors dans l’espace de dessin 2D.</li>
        </ul>

        <h3 style='color: #4a90e2;'>2. Dessiner des formes 2D</h3>
        <p>Utilisez les outils suivants pour créer votre base :</p>
        <ul>
            <li><b>Ligne :</b> relie deux points par un segment droit.</li>
            <li><b>Rectangle :</b> classique, centré ou à deux points opposés.</li>
            <li><b>Cercle :</b> par centre-rayon, deux points, etc.</li>
            <li><b>Arc, ellipse, polygone, spline…</b> pour des formes plus complexes.</li>
        </ul>

        <h3 style='color: #4a90e2;'>3. Ajouter cotations et contraintes</h3>
        <ul>
            <li><b>Cotation :</b> définit les dimensions précises (longueur, angle…).</li>
            <li><b>Contraintes :</b> garantissent une géométrie stable (horizontale, perpendiculaire, égale...).</li>
            <li>Une esquisse totalement définie devient noire (contrairement au bleu).</li>
        </ul>

        <h3 style='color: #4a90e2;'>4. Terminer l’esquisse</h3>
        <p>Cliquez sur <i>“Terminer l’esquisse”</i> pour revenir à l’environnement 3D.</p>

        <h3 style='color: #4a90e2;'>5. Transformer en 3D</h3>
        <p>À partir de votre esquisse, vous pouvez créer un volume :</p>
        <ul>
            <li><b>Extrusion :</b> tire une forme pour créer du volume.</li>
            <li><b>Révolution :</b> fait tourner une forme autour d’un axe.</li>
            <li><b>Balayage :</b> suit un chemin défini par une esquisse.</li>
            <li><b>Lissage (Loft) :</b> relie plusieurs profils différents.</li>
        </ul>

        <h3 style='color: #4a90e2;'>6. Conseils pour bien débuter</h3>
        <ul>
            <li>Sauvegardez régulièrement votre travail.</li>
            <li>Activez les grilles et les accrochages pour plus de précision.</li>
            <li>Nommez vos esquisses et composants pour rester organisé.</li>
            <li>Faites un clic droit sur les éléments pour explorer plus d’options.</li>
        </ul>

        <p style='margin-top: 20px; font-style: italic; color: #666666;'>
            Tout commence par une bonne esquisse. Maîtriser cette étape est essentiel pour créer des objets 3D solides et précis.
        </p>
    </body>
</html>
""");

// Pour éviter la barre de scroll, on l’ajoute sans JScrollPane :
        text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);  // Pour utiliser la police système
        text.setEditable(false);
        text.setBackground(Color.WHITE); // fond noir
        text.setForeground(Color.BLACK); // texte blanc
        text.setCaretColor(Color.BLACK);
        text.setOpaque(true); // important pour que le fond soit bien pris en compte
        text.setBorder(null);
        text.setFocusable(false);
        text.setCaretPosition(0); // Force le scroll en haut



        JScrollPane scroll = new JScrollPane(text);
        scroll.setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // contour blanc
        scroll.getVerticalScrollBar().setUnitIncrement(8);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createOngletTypes() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(createBLACKLabel("🔷 Modélisation surfacique – Pour les formes complexes et lisses."));
        panel.add(createBLACKLabel("🔶 Modélisation volumique – Solide, utile en ingénierie."));
        panel.add(createBLACKLabel("⚙️ Modélisation paramétrique – Contrôle précis via des paramètres."));
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);
        return panel;
    }

    private JPanel createOngletOutils(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Exemple outil 1 : extrusion
        JButton btnExtrude = new JButton(new ImageIcon(getClass().getResource("images/CreeUneEsquisse.png")));
        btnExtrude.setToolTipText("Créer une esquisse");
        btnExtrude.addActionListener(e -> JOptionPane.showMessageDialog(this, "Créer un nouveau plan en 2D"));
        btnExtrude.setBackground(Color.WHITE);
        btnExtrude.setForeground(Color.BLACK);
        btnExtrude.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnExtrude.setBounds(0, 0, width-26, (height/2)-45);  // Utilisation de width et height

        // Utilisation des paramètres width et height directement
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("images/Extrusion.png"));
        ImageIcon smallIcon = resizeIcon(originalIcon, 32, 32); // 👈 Choisis la taille que tu veux
        JButton btnRotate = new JButton(smallIcon);
        btnRotate.setToolTipText("Rotation – Tourne autour d’un axe");
        btnRotate.addActionListener(e -> JOptionPane.showMessageDialog(this, "La rotation permet de créer une forme autour d’un axe."));
        btnRotate.setBackground(Color.WHITE);
        btnRotate.setForeground(Color.BLACK);
        btnRotate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnRotate.setBounds(0, (height-height/2) - 40, width-26, (height/2)-45);  // Utilisation de width et height

        panel.add(btnExtrude);
        panel.add(btnRotate);
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);

        return panel;
    }


    private JPanel createOngletApplications() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        panel.add(createAppIcon("🏭 CAO", "Conception de pièces techniques"));
        panel.add(createAppIcon("🖨️ Impression 3D", "Fabrication rapide d’objets physiques"));
        panel.add(createAppIcon("🎮 Jeux Vidéo", "Création de personnages ou décors"));
        panel.add(createAppIcon("🎬 Cinéma", "Effets spéciaux et animations 3D"));

        return panel;
    }

    private JPanel createOngletVocabulaire() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(createBLACKLabel("🔷 Modélisation surfacique – Pour les formes complexes et lisses."));
        panel.add(createBLACKLabel("🔶 Modélisation volumique – Solide, utile en ingénierie."));
        panel.add(createBLACKLabel("⚙️ Modélisation paramétrique – Contrôle précis via des paramètres."));
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);
        return panel;
    }

    private JPanel createAppIcon(String titre, String description) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);

        JLabel label = createBLACKLabel(titre);
        JTextArea desc = new JTextArea(description);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setBorder(null);
        desc.setFocusable(false);
        desc.setForeground(Color.BLACK); // <- important !
        desc.setBackground(Color.WHITE); // optionnel ici, vu qu’opaque est false

        panel.add(label, BorderLayout.NORTH);
        panel.add(desc, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createBLACKLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        return label;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

}
