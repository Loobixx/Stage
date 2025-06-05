package src;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;


public class CuraPage extends PagePanel {
    private Color Noir = new Color(0,0,0), White = new Color(255, 255, 255), Rouge = new Color( 255, 100, 100);
    private Color InvertNoir = new Color(200, 200, 200), InvertWhite = new Color(33, 33, 33);
    private JScrollPane scrollInstaller, scrollDecouverte, scrollOutils, scrollApplications, scrollVocabulaire;
    private JTabbedPane onglets;
    private JPanel panelInstaller, panelCuraDecouverte, panelOutils, panelApplication, panelVocabulaire;
    JButton buttonOutils;
    JLabel labelBoutonOutils;
    JEditorPane textInstaller, text, textOutils, textApplication, textVocabulaire;

    private final String htmlInstallerCura = """
<html>
  <head>
    <meta charset="UTF-8">
    <title>Tutoriel : Installer Ultimaker Cura</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 10px;
        color: %s;            /* couleur du texte */
        background-color: %s; /* couleur de fond */
      }
      h2 {
        color: #2a7ae2;
      }
      h3 {
        margin-top: 30px;
        color: #4a90e2;
      }
      img {
        width: 500px;
        margin: 10px 0;
        border: 1px solid #ccc;
      }
      p {
        margin-bottom: 10px;
      }
    </style>
  </head>
  <body>

    <h2>🔧 Tutoriel : Installer Ultimaker Cura</h2>

    <h3>1. Accéder à la page de téléchargement</h3>
    <p>Rendez-vous sur le site officiel :</p>
    <p>
      <a href="https://ultimaker.com/fr/software/ultimaker-cura/" target="_blank">
        https://ultimaker.com/fr/software/ultimaker-cura/
      </a>
    </p>
    <p>➡️ Cliquez sur <b>Download for free</b>.</p>
    <br>
    <img src="cura-download.png" alt="Télécharger Cura">

    <h3>2. Télécharger le fichier</h3>
    <p>➡️ Cliquez sur <b>Windows 64 bits (.exe)</b>.</p>
    <br>
    <img src="cura-win64.png" alt="Téléchargement Windows">
    <br>
    <p>➡️ Enregistrez le fichier à l'emplacement de votre choix.</p>

    <h3>3. Lancer l’installation</h3>
    <p>➡️ Double-cliquez sur le fichier <code>.exe</code> téléchargé.</p>
    <p>✅ Acceptez les autorisations demandées par Windows si nécessaire.</p>

    <h3>4. Suivre les étapes d'installation</h3>
    <p>➡️ Cliquez sur <b>Next</b>.</p>
    <p>➡️ Acceptez les conditions d'utilisation.</p>
    <p>➡️ Cliquez à nouveau sur <b>Next</b> sans changer le dossier d'installation.</p>
    <p>➡️ Cliquez sur <b>Install</b>.</p>

    <h3>5. Fin de l'installation</h3>
    <p>➡️ Cliquez sur <b>Finish</b>.</p>
    <p>✅ Cura est maintenant prêt à être utilisé !</p>

    <h3>🎉 Lancement de Cura</h3>
    <p>➡️ Lancez Cura depuis le menu Démarrer ou le raccourci bureau.</p>

    <p style="text-align:right; margin-top:30px;">
      <a href="action:termine" style="
        display: inline-block;
        padding: 10px;
        font-weight: bold;
        color: #0099ff;
        font-size: 16px;
        text-decoration: none;
        border-radius: 6px;
        cursor: pointer;
        user-select: none;
      ">
        Découverte de Cura ➡️
      </a>
    </p>

  </body>
</html>
""";

    private final String htmlDecouverte = """
<html>
  <head>
    <style>
      body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 10px;
        color: %s;            /* couleur du texte */
        background-color: %s; /* couleur de fond */
      }
      h2 {
        color: #2a7ae2;
      }
      h3 {
        margin-top: 30px;
        color: #4a90e2;
      }
      img {
        width: 500px;
        margin: 10px 0;
        border: 1px solid #ccc;
      }
      p {
        margin-bottom: 10px;
      }
    </style>
  </head>
  <body>

    <h2>Cura : Découverte</h2>

    <h3>🖨️ À quoi sert Cura ?</h3>
    <p>
      <b>Cura</b> est un slicer 3D open-source développé par Ultimaker.  
      Son rôle principal est de <b>convertir un modèle 3D</b> (fichiers .stl,.obj, .3mf…)  
      en <b>instructions G-code</b> compréhensibles par votre imprimante 3D.  
      Il fait le lien entre la modélisation et la fabrication.
    </p>
    <p>
      Cura offre également :
    </p>
    <ul>
      <li>Des profils d’impression prédéfinis pour de nombreux matériaux (PLA, ABS, PETG…)</li>
      <li>Une interface visuelle pour ajuster la position, l’orientation et l’échelle du modèle</li>
      <li>Des outils de prévisualisation couche par couche</li>
      <li>Un écosystème de plugins pour étendre ses fonctionnalités</li>
    </ul>

    <h3>⚙️ Comment fonctionne Cura ?</h3>
    <ul>
      <li><b>Importer</b> votre modèle 3D (glisser-déposer ou menu Fichier → Ouvrir).</li>
      <li><b>Choisir</b> votre imprimante et le matériau dans le panneau de configuration.</li>
      <li><b>Positionner</b> et orienter le modèle sur le plateau virtuel.</li>
      <li><b>Configurer</b> les paramètres d’impression :  
        hauteur de couche, remplissage, support, adhérence…  
      </li>
      <li><b>Cliquer sur « Préparer »</b> pour lancer le tranchage (slicing).</li>
      <li><b>Vérifier</b> l’aperçu des trajectoires de la buse, couche par couche.</li>
      <li><b>Exporter</b> le G-code (carte SD, USB ou réseau) et lancer l’impression.</li>
    </ul>

    <p class="italic">
      Cura simplifie grandement la préparation d’une impression 3D, tout en restant  
      puissant pour les utilisateurs avancés grâce à ses réglages fins et ses plugins.
    </p>
    <p style="text-align:right; margin-top:30px;">
      <a href="action:termine" style="
        display: inline-block;
        padding: 10px;
        font-weight: bold;
        font-size: 16px;
        color : 0099ff;
        text-decoration: none;
        border-radius: 6px;
        cursor: pointer;
        user-select: none;
      ">
        Outils de Cura ➡️
      </a>
    </p>

  </body>
</html>
""";

    private final String htmlOutils = """
<html>
  <head>
    <style>
      body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 10px;
        color: %s;            /* couleur du texte */
        background-color: %s; /* couleur de fond */
      }
      h2 {
        color: #2a7ae2;
      }
      h3 {
        margin-top: 30px;
        color: #4a90e2;
      }
      img {
        width: 500px;
        margin: 10px 0;
        border: 1px solid #ccc;
      }
      p {
        margin-bottom: 10px;
      }
    </style>
  </head>
  <body>
    <p style="text-align:right; margin-top:30px;">
      <a href="action:termine" style="
        display: inline-block;
        padding: 10px;
        font-weight: bold;
        font-size: 16px;
        color : 0099ff;
        text-decoration: none;
        border-radius: 6px;
        cursor: pointer;
        user-select: none;
      ">
        Application de Cura ➡️
      </a>
    </p>
  </body>
</html>
""";

    private final String htmlApplication = """
<html>
  <head>
    <style>
      body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 10px;
        color: %s;            /* couleur du texte */
        background-color: %s; /* couleur de fond */
      }
      h2 {
        color: #2a7ae2;
      }
      h3 {
        margin-top: 30px;
        color: #4a90e2;
      }
      img {
        width: 500px;
        margin: 10px 0;
        border: 1px solid #ccc;
      }
      p {
        margin-bottom: 10px;
      }
    </style>
  </head>
  <body>

    <h2>Domaines d'application de Cura</h2>

    <h3>🖨️ 1. Slicing pour l’impression 3D</h3>
    <p>
      Cura convertit des modèles 3D (.stl, .obj, .3mf…) en G-code, le langage
      natif des imprimantes 3D. Il permet de définir hauteur de couche, vitesse,
      température et autres paramètres essentiels avant chaque impression.
    </p>

    <h3>⚙️ 2. Optimisation des paramétrages</h3>
    <p>
      Grâce à ses profils prédéfinis et à ses options avancées, Cura aide à ajuster
      précisément les réglages (remplissage, supports, adhérence) pour optimiser
      la qualité, la résistance et la rapidité des pièces imprimées.
    </p>

    <h3>🔍 3. Visualisation couche par couche</h3>
    <p>
      Cura offre un aperçu détaillé du trajet de l’extrudeuse, couche par couche,
      permettant de détecter et de corriger les défauts avant lancement de l’impression.
    </p>

    <h3>🤝 4. Compatibilité multi-machines</h3>
    <p>
      Bien qu’originellement développé par Ultimaker, Cura supporte une vaste gamme
      d’imprimantes de différentes marques. Il synchronise les profils pour chaque
      machine et matériau.
    </p>

    <h3>📈 5. Analyse et amélioration</h3>
    <p>
      Avec ses outils d’analyse de support et de température, Cura permet de suivre
      les performances d’impression, d’anticiper les erreurs et d’ajuster les
      paramètres pour les impressions futures.
    </p>

    <h3>🌐 6. Intégration et automatisation</h3>
    <p>
      Cura peut être piloté par API ou plugins pour automatiser les flux de travail,
      envoyer des jobs à distance et gérer des fermes d’imprimantes dans un contexte
      professionnel ou éducatif.
    </p>

    <p class="italic">
      Cura est bien plus qu’un simple slicer : c’est un véritable hub de préparation
      et de gestion d’impression 3D, indispensable pour débutants comme experts.
    </p>
    <p style="text-align:right; margin-top:30px;">
      <a href="action:termine" style="
        display: inline-block;
        padding: 10px;
        font-weight: bold;
        font-size: 16px;
        color : 0099ff;
        text-decoration: none;
        border-radius: 6px;
        cursor: pointer;
        user-select: none;
      ">
        Vocabulaire Cura ➡️
      </a>
    </p>

  </body>
</html>
""";

    private final String htmlVocabulaire = """
<html>
  <head>
    <style>
      body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 10px;
        color: %s;            /* couleur du texte */
        background-color: %s; /* couleur de fond */
      }
      h2 {
        color: #2a7ae2;
      }
      h3 {
        margin-top: 30px;
        color: #4a90e2;
      }
      img {
        width: 500px;
        margin: 10px 0;
        border: 1px solid #ccc;
      }
      p {
        margin-bottom: 10px;
      }
    </style>
  </head>
  <body>

    <h2>Vocabulaire de Cura</h2>

    <h3>🔪 Slicer</h3>
    <p>
      Un <b>slicer</b> est un logiciel qui découpe un modèle 3D en
      couches horizontales (slicing) et génère le fichier G-code pour
      l’imprimante 3D.
    </p>

    <h3>📜 G-code</h3>
    <p>
      Langage d’instructions composé de commandes (M et G) qui dictent
      les mouvements, la température et la vitesse de l’extrudeuse.
    </p>

    <h3>📏 Hauteur de couche (Layer Height)</h3>
    <p>
      Épaisseur de chaque couche imprimée. Plus elle est fine, plus
      les détails sont précis, au prix d’un temps d’impression plus long.
    </p>

    <h3>🧩 Infill (Remplissage)</h3>
    <p>
      Structure interne du modèle. Les pourcentages d’infill (ex : 20/100)
      influent sur la solidité, le poids et la consommation de filament.
    </p>

    <h3>🛠️ Supports</h3>
    <p>
      Structures temporaires imprimées sous les surplombs pour soutenir
      les parties en porte-à-faux. On les retire après impression.
    </p>

    <h3>🌉 Adhérence au plateau (Build Plate Adhesion)</h3>
    <p>
      Techniques (brim, raft, skirt) pour améliorer la fixation de l’objet
      au plateau et éviter le warping ou le décollement.
    </p>

    <h3>⏱️ Vitesse d’impression</h3>
    <p>
      Vitesse de déplacement de l’extrudeuse. Affecte la qualité et la durée
      de l’impression.
    </p>

    <h3>🔄 Rétraction (Retraction)</h3>
    <p>
      Raccourcissement du filament pour prévenir les bavures lors des
      déplacements hors impression.
    </p>

    <h3>🔍 Visualisation couche par couche</h3>
    <p>
      Aperçu du parcours de l’extrudeuse pour chaque couche, utile pour
      détecter les erreurs avant impression.
    </p>

    <h3>⚙️ Profil d’impression</h3>
    <p>
      Ensemble de paramètres (température, vitesse, infill…) préconfigurés
      pour un matériau ou une qualité d’impression.
    </p>

    <h3>🧪 Température d’extrusion</h3>
    <p>
      Chaleur appliquée au filament pour le rendre malléable. Doit être
      ajustée selon le type de matériau (PLA, ABS, PETG…).
    </p>

  </body>
</html>
""";

    private boolean flecheActive = false;
    public CuraPage(int width, int height) {
        // Utilisation d'un layout nul pour positionnement absolu des composants
        setLayout(null);

        // Configuration des couleurs et styles par défaut pour les onglets (JTabbedPane)
        // Fond de l'onglet sélectionné
        UIManager.put("TabbedPane.selected", White);
        // Couleur de fond de la zone d'affichage des onglets
        UIManager.put("TabbedPane.contentAreaColor", White);
        // Couleur du contour lorsqu'un onglet a le focus
        UIManager.put("TabbedPane.focus", White);

        // Création du JTabbedPane positionné à gauche
        onglets = new JTabbedPane(JTabbedPane.LEFT);
        // Positionnement et dimensions initiales du composant onglets
        onglets.setBounds(-30, -1, width + 95, height - 30);
        // Définition de la couleur de fond pour la zone des onglets
        onglets.setBackground(White);
        // Définition de la couleur du texte pour les titres d'onglets
        onglets.setForeground(Noir);

        // Ajout des onglets avec leurs panneaux respectifs
        // Onglet "Installer Cura" : appelle createOngletInstaller() pour construire son contenu
        onglets.addTab("Installer Cura", createOngletInstaller());
        // Onglet "Découverte" : appelle createOngletDecouverte() pour construire son contenu
        onglets.addTab("Découverte", createOngletDecouverte());
        // Onglet "Outils" : appelle createOngletOutils(width, height, false)
        // Le booléen false indique que l'onglet n'est pas en mode nuit par défaut
        onglets.addTab("Outils", createOngletOutils(width, height, false));
        // Onglet "Applications" : appelle createOngletApplications() pour construire son contenu
        onglets.addTab("Applications", createOngletApplications());
        // Onglet "Vocabulaire" : appelle createOngletVocabulaire() pour construire son contenu
        onglets.addTab("Vocabulaire", createOngletVocabulaire());

        // Ajout du JTabbedPane (les onglets) à la page CuraPage
        add(onglets);

        // Ajout d'un écouteur pour gérer le redimensionnement de la page
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Lorsqu'on redimensionne la fenêtre, on récupère la nouvelle largeur et hauteur
                int w = getWidth();
                int h = getHeight();
                // Appel à la méthode actualiser() pour repositionner/ajuster les composants
                actualiser(w, h);
            }
        });
    }

    private void actualiser(int width, int height) {
        onglets.setBounds(-100, -1, width + 100, height);
    }

    private JPanel createOngletInstaller() {
        // Création du panneau principal pour l'onglet "Installer Cura"
        panelInstaller = new JPanel();
        // Utilisation d'un BorderLayout pour occuper tout l'espace disponible
        panelInstaller.setLayout(new BorderLayout());
        // Définition des couleurs de fond et de premier plan du panneau
        panelInstaller.setBackground(White);
        panelInstaller.setForeground(Noir);
        // Position et dimensions initiales (sera ajusté par actualiser() lors du redimensionnement)
        panelInstaller.setBounds(0, -30, getWidth(), getHeight());
        // Suppression de toute bordure par défaut
        panelInstaller.setBorder(null);

        // Création d'un JEditorPane pour afficher le contenu HTML
        textInstaller = new JEditorPane();

        // 5. On crée un kit HTML : c’est le moteur de rendu HTML fourni par Swing
        HTMLEditorKit kit = new HTMLEditorKit();

        // 6. On fabrique un document HTML vide mais « customisable » via ce kit
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

        // 7. On définit la base (le dossier) où seront résolus tous les src relatifs si l'URL est trouvée
        URL base = getClass().getResource("/imagesCura/");
        if (base != null) {
            // Si le dossier imagesCura existe dans le classpath, on le définit comme base
            doc.setBase(base);
        }

        // 8. On « colle » ce kit et ce document au JEditorPane pour permettre l'interprétation de HTML
        textInstaller.setEditorKit(kit);
        textInstaller.setDocument(doc);

        // 4. Ensuite, on injecte le contenu HTML (chaîne htmlInstallerCura définie ailleurs)
        textInstaller.setText(htmlInstallerCura);
        // On précise que le contenu est du HTML
        textInstaller.setContentType("text/html");
        // Autorise le JEditorPane à honorer les propriétés d’affichage (police système, etc.)
        textInstaller.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        // Rend le JEditorPane non éditable par l'utilisateur
        textInstaller.setEditable(false);
        // Définition de la couleur de fond et de premier plan
        textInstaller.setBackground(White);
        textInstaller.setForeground(Noir);
        // Rend le fond transparent pour laisser apparaître le panneau derrière
        textInstaller.setOpaque(false);
        // Suppression de toute bordure autour du JEditorPane
        textInstaller.setBorder(null);
        // Empêche le composant de recevoir le focus
        textInstaller.setFocusable(false);
        // Place le curseur au début du document
        textInstaller.setCaretPosition(0);
        // Ajout d'un listener pour gérer les clics sur les liens HTML
        textInstaller.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Lorsque l'utilisateur clique sur le lien "action:termine", on passe à l'onglet suivant (index 1)
                    onglets.setSelectedIndex(1);
                }
            }
        });

        // Encapsulation du JEditorPane dans un JScrollPane pour gérer le défilement vertical
        scrollInstaller = new JScrollPane(textInstaller);
        // Réglage de la vitesse de défilement de la molette (8 pixels par incrément)
        scrollInstaller.getVerticalScrollBar().setUnitIncrement(8);

        // Application des couleurs (clair/sombre) au JScrollPane (false = mode clair par défaut)
        scrollPanelColor(scrollInstaller, false);

        // Ajout du JScrollPane (contenant le JEditorPane) au centre du panneau panelInstaller
        panelInstaller.add(scrollInstaller, BorderLayout.CENTER);

        return panelInstaller;
    }

    private JPanel createOngletDecouverte() {
        // Création du panneau principal pour l'onglet "Découverte"
        panelCuraDecouverte = new JPanel();
        // Utilisation d'un BorderLayout pour que le contenu occupe tout l'espace disponible
        panelCuraDecouverte.setLayout(new BorderLayout());
        // Définition des couleurs de fond et de premier plan pour le panneau
        panelCuraDecouverte.setBackground(White);
        panelCuraDecouverte.setForeground(Noir);
        // Positionnement initial du panneau (sera ajusté par actualiser() lors du redimensionnement)
        panelCuraDecouverte.setBounds(0, -30, getWidth(), getHeight());
        // Suppression de toute bordure par défaut
        panelCuraDecouverte.setBorder(null);

        // Création d'un JEditorPane pour afficher du contenu HTML
        text = new JEditorPane();
        // On indique que le contenu est du HTML
        text.setContentType("text/html");
        // Injection du HTML (chaîne htmlDecouverte définie ailleurs)
        text.setText(htmlDecouverte);
        // Autorise le JEditorPane à respecter les propriétés d’affichage (police système, etc.)
        text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        // Rend le JEditorPane non éditable par l'utilisateur
        text.setEditable(false);
        // Définition de la couleur de fond et du texte du JEditorPane
        text.setBackground(White);
        text.setForeground(Noir);
        // Rend le fond transparent pour laisser apparaître la couleur du panneau parent
        text.setOpaque(false);
        // Suppression de toute bordure autour du JEditorPane
        text.setBorder(null);
        // Empêche le composant de recevoir le focus
        text.setFocusable(false);
        // Positionne le curseur au début du document HTML
        text.setCaretPosition(0);
        // Ajout d'un listener pour gérer les clics sur les liens HTML
        text.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Lorsque l'utilisateur clique sur le lien "action:termine", on passe à l'onglet suivant (index 2)
                    onglets.setSelectedIndex(2);
                }
            }
        });

        // Encapsulation du JEditorPane dans un JScrollPane pour gérer le défilement vertical
        scrollDecouverte = new JScrollPane(text);
        // Réglage de la vitesse de défilement de la molette (8 pixels par incrément)
        scrollDecouverte.getVerticalScrollBar().setUnitIncrement(8);

        // Application du style clair/sombre au JScrollPane (false = mode clair par défaut)
        scrollPanelColor(scrollDecouverte, false);

        // Ajout du JScrollPane au centre du panneau panelCuraDecouverte
        panelCuraDecouverte.add(scrollDecouverte, BorderLayout.CENTER);

        return panelCuraDecouverte;
    }

    private void creerBoutonOutil(boolean ModeNuit, JPanel panel, String imagePath, String tooltip, String message, int x, int y, int btnSize, String labelText) {
        // Création du JButton avec une icône redimensionnée selon btnSize × btnSize
        buttonOutils = new JButton(
                resizeIcon(
                        new ImageIcon(getClass().getResource(imagePath)),
                        btnSize,
                        btnSize
                )
        );
        // Définition du texte qui s’affiche en info-bulle au survol
        buttonOutils.setToolTipText(tooltip);
        // Ajout d’un ActionListener pour afficher un message dans une boîte de dialogue lors du clic
        buttonOutils.addActionListener(e -> JOptionPane.showMessageDialog(this, message));
        // Couleur de fond et de premier plan par défaut en mode clair
        buttonOutils.setBackground(White);
        buttonOutils.setForeground(Noir);
        // Ajout d’une bordure noire épaisse de 2 pixels pour délimiter le bouton
        buttonOutils.setBorder(BorderFactory.createLineBorder(Noir, 2));
        // Positionnement absolu du bouton (x, y) avec la taille btnSize × btnSize
        buttonOutils.setBounds(x, y, btnSize, btnSize);

        // Création d’un JLabel centré, placé sous le bouton pour décrire sa fonction
        labelBoutonOutils = new JLabel("<html><center>" + labelText + "</center></html>");
        // Positionnement du label : centré sous le bouton (décalage en X de 20 pixels de chaque côté)
        labelBoutonOutils.setBounds(x - 20, y + btnSize, btnSize + 40, 30);
        // Alignement horizontal du texte au centre
        labelBoutonOutils.setHorizontalAlignment(SwingConstants.CENTER);
        // Police SansSerif, taille 12, style normal
        labelBoutonOutils.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Choix de la couleur du texte du label selon le mode nuit ou jour
        if (ModeNuit) {
            // En mode nuit : texte clair (InvertNoir)
            labelBoutonOutils.setForeground(InvertNoir);
        } else {
            // En mode jour : texte noir
            labelBoutonOutils.setForeground(Noir);
        }

        // Ajout du bouton et du label au panneau fourni
        panel.add(buttonOutils);
        panel.add(labelBoutonOutils);
    }

    private JScrollPane createOngletOutils(int width, int height, boolean modeNuit) {
        // Taille standard des boutons d’outil et espacement horizontal entre chaque
        int btnSize = 70;
        int spacing = 30;

        // Création d’un JPanel personnalisé sans layout manager (positionnement absolu)
        panelOutils = new JPanel(null);
        // Couleur de fond du panneau : sombre si mode nuit, blanc sinon
        panelOutils.setBackground(modeNuit ? InvertWhite : White);

        // --- Titre général de la section "Paramètres" ---
        JLabel labelOutils = new JLabel("<html><center>Paramètres</center></html>");
        // Positionnement du label (x=35, y=5, largeur btnSize+60, hauteur 30)
        labelOutils.setBounds(35, 5, btnSize + 60, 30);
        labelOutils.setHorizontalAlignment(SwingConstants.CENTER);
        labelOutils.setFont(new Font("SansSerif", Font.PLAIN, 25));
        labelOutils.setForeground(Rouge);  // Couleur du texte rouge
        panelOutils.add(labelOutils);

        // Coordonnées de départ pour placer les boutons (première ligne)
        int x = 50;
        int y = 40;

        // --- Ligne 1 : Boutons d'import, position, échelle, orientation, miroir ---
        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/OuvrirFichier.png",
                "Importer",
                "Importe un fichier 3D pour le placer sur le plateau virtuel.",
                x, y,
                btnSize,
                "Importer"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Deplacer.png",
                "Positionner",
                "Permet de déplacer, faire pivoter ou mettre à l’échelle le modèle sur le plateau.",
                x, y,
                btnSize,
                "Positionner"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/ChangerLaTaille.png",
                "Échelle",
                "Ajuste la taille du modèle en pourcentage ou valeur absolue.",
                x, y,
                btnSize,
                "Échelle"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Tourner.png",
                "Orientation",
                "Oriente le modèle selon les axes X, Y et Z pour optimiser l’impression.",
                x, y,
                btnSize,
                "Orientation"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Miroir.png",
                "Dupliquer miroir",
                "Génère une version miroir du modèle pour imprimer des pièces symétriques.",
                x, y,
                btnSize,
                "Dupliquer miroir"
        );

        // --- Ligne 2 : Réinitialiser x, avancer y, puis boutons Profil et Supports ---
        x = 50;
        y += btnSize + 60;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Parametre.png",
                "Profil",
                "Choisit le profil d’impression (matériau, épaisseur de couche, remplissage).",
                x, y,
                btnSize,
                "Profil"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/CreationSupport.png",
                "Supports",
                "Active ou désactive la génération de supports pour les surplombs.",
                x, y,
                btnSize,
                "Supports"
        );

        // --- Ligne 3 : Réinitialiser x, avancer y, puis titre "Paramètres détaillés" ---
        x = 50;
        y += btnSize + 60;

        labelOutils = new JLabel("<html><center>Paramètres détaillés</center></html>");
        // Positionnement du sous-titre (couleur rouge, police 25)
        labelOutils.setBounds(35, y, btnSize + 200, 30);
        labelOutils.setHorizontalAlignment(SwingConstants.CENTER);
        labelOutils.setFont(new Font("SansSerif", Font.PLAIN, 25));
        labelOutils.setForeground(Rouge);
        panelOutils.add(labelOutils);

        // --- Boutons de la ligne 3 : différents paramètres avancés ---
        x = 50;
        y += 40; // Légère marge avant d’ajouter les boutons

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Quality.png",
                "Hauteur de couche",
                "Spécifie l’épaisseur de chaque couche : plus fin = plus de détails, plus lent.",
                x, y,
                btnSize,
                "Hauteur de couche"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Walls.png",
                "Parois",
                "Détermine le nombre et l’épaisseur des parois du modèle pour la solidité.",
                x, y,
                btnSize,
                "Parois"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/TopBottom.png",
                "Dessus/Dessous",
                "Ajuste le nombre de couches solides en haut et en bas de l’impression.",
                x, y,
                btnSize,
                "Dessus/Dessous"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Infill.png",
                "Remplissage",
                "Définit le pourcentage et le motif de remplissage intérieur du modèle.",
                x, y,
                btnSize,
                "Remplissage"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Material.png",
                "Matériau",
                "Choisit le matériau et son diamètre pour optimiser les paramètres d’extrusion.",
                x, y,
                btnSize,
                "Matériau"
        );

        // --- Ligne 4 : Réinitialiser x, avancer y, puis boutons Vitesse, Déplacement, Refroidissement, Adhérence, Plateau ---
        x = 50;
        y += btnSize + 60;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Speed.png",
                "Vitesse",
                "Modifie la vitesse d’impression : plus rapide = moins précis.",
                x, y,
                btnSize,
                "Vitesse"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Travel.png",
                "Déplacement",
                "Ajuste la vitesse des mouvements lorsque la buse ne dépose pas de filament.",
                x, y,
                btnSize,
                "Déplacement"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Cooling.png",
                "Refroidissement",
                "Active et règle la vitesse des ventilateurs pour solidifier chaque couche.",
                x, y,
                btnSize,
                "Refroidissement"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Support.png",
                "Adhérence",
                "Sélectionne le type d’adhérence au plateau (raft, skirt, brim).",
                x, y,
                btnSize,
                "Adhérence"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/BuildPlateAdhesion.png",
                "Plateau",
                "Permet de régler la distance initiale entre la buse et le plateau.",
                x, y,
                btnSize,
                "Plateau"
        );

        // --- Ligne 5 : Réinitialiser x, avancer y, puis boutons Multi-extrusion, Réparations, Modes spéciaux, Bêta/Expérimental ---
        x = 50;
        y += btnSize + 60;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/DualExtrusion.png",
                "Multi-extrusion",
                "Permet d’imprimer en plusieurs matériaux ou couleurs simultanément.",
                x, y,
                btnSize,
                "Multi-extrusion"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/MeshFixes.png",
                "Réparations",
                "Corrige automatiquement les trous et intersections du modèle.",
                x, y,
                btnSize,
                "Réparations"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/SpecialModes.png",
                "Modes spéciaux",
                "Active des modes d’impression alternatifs (spiralize, coasting…).",
                x, y,
                btnSize,
                "Modes spéciaux"
        );
        x += btnSize + spacing;

        creerBoutonOutil(
                modeNuit,
                panelOutils,
                "/imagesCura/Experimental.png",
                "Bêta/Expérimental",
                "Active les options expérimentales non finalisées.",
                x, y,
                btnSize,
                "Bêta/Expérimental"
        );
        x = 50;
        y += btnSize;

        // --- Zone d’affichage HTML "htmlOutils" pour expliquer chaque bouton de manière détaillée ---
        textOutils = new JEditorPane();
        textOutils.setContentType("text/html");
        textOutils.setText(htmlOutils);
        textOutils.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textOutils.setEditable(false);
        textOutils.setBackground(White);
        textOutils.setForeground(Noir);
        textOutils.setOpaque(false);
        textOutils.setBorder(null);
        textOutils.setFocusable(false);
        // Listener pour gérer les liens cliquables du HTML (redirige vers onglet "Applications")
        textOutils.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    onglets.setSelectedIndex(3);
                }
            }
        });
        // Placement de la zone HTML sous les boutons
        textOutils.setBounds(250, y, btnSize + 300, 80);
        panelOutils.add(textOutils);

        // Ajustement de la taille préférée du panneau au besoin (hauteur dynamique)
        panelOutils.setPreferredSize(new Dimension(500, y + btnSize + 60));

        // Encapsulation du panelOutils dans une JScrollPane pour permettre le défilement
        scrollOutils = new JScrollPane(panelOutils);
        scrollOutils.setPreferredSize(new Dimension(width, height));
        scrollOutils.setBorder(null);
        // Application du style de scroll (clair/sombre) selon modeNuit
        scrollPanelColor(scrollOutils, modeNuit);
        // Réglage de la vitesse de défilement vertical (8 pixels par incrément)
        scrollOutils.getVerticalScrollBar().setUnitIncrement(8);

        return scrollOutils;
    }

    private JPanel createOngletApplications() {
        // Création du panneau principal pour l'onglet "Applications"
        panelApplication = new JPanel();
        // Utilisation d'un BorderLayout pour occuper tout l'espace disponible
        panelApplication.setLayout(new BorderLayout());
        // Définition des couleurs de fond et de premier plan du panneau
        panelApplication.setBackground(White);
        panelApplication.setForeground(Noir);
        // Positionnement initial du panneau (sera ajusté par actualiser() lors du redimensionnement)
        panelApplication.setBounds(0, -30, getWidth(), getHeight());
        // Suppression de toute bordure par défaut
        panelApplication.setBorder(null);

        // Création d'un JEditorPane pour afficher le contenu HTML
        textApplication = new JEditorPane();
        // On indique que le contenu est du HTML
        textApplication.setContentType("text/html");
        // Injection du code HTML (chaîne htmlApplication définie ailleurs)
        textApplication.setText(htmlApplication);
        // Autorise le JEditorPane à honorer les propriétés d’affichage (police système, etc.)
        textApplication.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        // Rend le JEditorPane non éditable par l'utilisateur
        textApplication.setEditable(false);
        // Définition de la couleur de fond et du texte du JEditorPane
        textApplication.setBackground(White);
        textApplication.setForeground(Noir);
        // Rend le fond transparent pour laisser apparaître la couleur du panneau parent
        textApplication.setOpaque(false);
        // Suppression de toute bordure autour du JEditorPane
        textApplication.setBorder(null);
        // Empêche le composant de recevoir le focus
        textApplication.setFocusable(false);
        // Positionne le curseur au début du document HTML
        textApplication.setCaretPosition(0);
        // Ajout d'un listener pour gérer les clics sur les liens HTML
        textApplication.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Lorsque l'utilisateur clique sur le lien "action:termine", on passe à l'onglet suivant (index 4)
                    onglets.setSelectedIndex(4);
                }
            }
        });

        // Encapsulation du JEditorPane dans un JScrollPane pour gérer le défilement vertical
        scrollApplications = new JScrollPane(textApplication);
        // Réglage de la vitesse de défilement de la molette (8 pixels par incrément)
        scrollApplications.getVerticalScrollBar().setUnitIncrement(8);

        // Application du style clair/sombre au JScrollPane (false = mode clair par défaut)
        scrollPanelColor(scrollApplications, false);

        // Ajout du JScrollPane au centre du panneau panelApplication
        panelApplication.add(scrollApplications, BorderLayout.CENTER);

        return panelApplication;
    }

    private JPanel createOngletVocabulaire() {
        // Création du panneau principal pour l'onglet "Vocabulaire"
        panelVocabulaire = new JPanel();
        // Utilisation d'un BorderLayout pour occuper tout l'espace disponible
        panelVocabulaire.setLayout(new BorderLayout());
        // Définition des couleurs de fond et de premier plan du panneau
        panelVocabulaire.setBackground(White);
        panelVocabulaire.setForeground(Noir);
        // Positionnement initial du panneau (sera ajusté par actualiser() lors du redimensionnement)
        panelVocabulaire.setBounds(0, -30, getWidth(), getHeight());
        // Suppression de toute bordure par défaut
        panelVocabulaire.setBorder(null);

        // Création d'un JEditorPane pour afficher le contenu HTML
        textVocabulaire = new JEditorPane();
        // On indique que le contenu est du HTML
        textVocabulaire.setContentType("text/html");
        // Injection du code HTML (chaîne htmlVocabulaire définie ailleurs)
        textVocabulaire.setText(htmlVocabulaire);
        // Autorise le JEditorPane à honorer les propriétés d’affichage (police système, etc.)
        textVocabulaire.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        // Rend le JEditorPane non éditable par l'utilisateur
        textVocabulaire.setEditable(false);
        // Définition de la couleur de fond et du texte du JEditorPane
        textVocabulaire.setBackground(White);
        textVocabulaire.setForeground(Noir);
        // Rend le fond transparent pour laisser apparaître la couleur du panneau parent
        textVocabulaire.setOpaque(false);
        // Suppression de toute bordure autour du JEditorPane
        textVocabulaire.setBorder(null);
        // Empêche le composant de recevoir le focus
        textVocabulaire.setFocusable(false);
        // Positionne le curseur au début du document HTML
        textVocabulaire.setCaretPosition(0);

        // Encapsulation du JEditorPane dans un JScrollPane pour gérer le défilement vertical
        scrollVocabulaire = new JScrollPane(textVocabulaire);
        // Réglage de la vitesse de défilement de la molette (8 pixels par incrément)
        scrollVocabulaire.getVerticalScrollBar().setUnitIncrement(8);

        // Application du style clair/sombre au JScrollPane (false = mode clair par défaut)
        scrollPanelColor(scrollVocabulaire, false);

        // Ajout du JScrollPane au centre du panneau panelVocabulaire
        panelVocabulaire.add(scrollVocabulaire, BorderLayout.CENTER);

        return panelVocabulaire;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        // Récupère l’objet Image sous-jacent de l’ImageIcon
        Image img = icon.getImage();
        // Crée une nouvelle image redimensionnée en utilisant un algorithme de mise à l’échelle lisse
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Retourne la nouvelle ImageIcon contenant l’image redimensionnée
        return new ImageIcon(resizedImage);
    }

    public JTabbedPane getOnglets() {
        return onglets;
    }

    // Mis à jour : méthode modeNuitCura pour inclure le thème sombre sur l'onglet Applications
    public void modeNuitCura(boolean boolModeNuit) {
        // === MISE À JOUR DU STYLE DES ONGLETS ===

        if (boolModeNuit) {
            // Mode nuit : fond sombre pour l'arrière-plan des onglets
            onglets.setBackground(InvertWhite); // Couleur inversée définie ailleurs
            UIManager.put("TabbedPane.selected", InvertWhite); // Onglet actif
            UIManager.put("TabbedPane.contentAreaColor", InvertWhite); // Zone de contenu
            UIManager.put("TabbedPane.focus", InvertWhite); // Focus visuel
        } else {
            // Mode clair : fond blanc pour l'arrière-plan des onglets
            onglets.setBackground(White);
            UIManager.put("TabbedPane.selected", White);
            UIManager.put("TabbedPane.contentAreaColor", White);
            UIManager.put("TabbedPane.focus", White);
        }

        // === DÉFINITION DES COULEURS HTML ===

        // Couleur du texte dans les blocs HTML
        String textAppColor = boolModeNuit ? "#ffffff" : "#000000"; // blanc en nuit, noir en jour

        // Couleur de fond dans les blocs HTML
        String bgAppColor = boolModeNuit ? "#212121" : "#ffffff"; // gris foncé ou blanc

        // === ONGLET : INSTALLER CURA ===

        // Met à jour le texte HTML avec les nouvelles couleurs
        textInstaller.setText(String.format(htmlInstallerCura, textAppColor, bgAppColor));
        textInstaller.setCaretPosition(0); // Replace le curseur au début (évite de rester en bas)
        scrollPanelColor(scrollInstaller, boolModeNuit); // Met à jour le style de la scrollbar

        // Rend la vue opaque avec un fond adapté (important pour le rendu visuel)
        scrollInstaller.getViewport().setOpaque(true);
        scrollInstaller.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White
        );

        // Force une mise à jour de l'affichage
        scrollInstaller.revalidate();
        scrollInstaller.repaint();

        // === ONGLET : OUTILS ===

        // Recherche l'index de l'onglet "Outils"
        int idxOutils = onglets.indexOfTab("Outils");

        if (idxOutils != -1) {
            // Recrée complètement le panneau "Outils" avec le nouveau thème
            JScrollPane newOutils = createOngletOutils(getWidth(), getHeight(), boolModeNuit);

            // Remplace l'ancien contenu de l'onglet par le nouveau
            onglets.setComponentAt(idxOutils, newOutils);

            // Applique les couleurs personnalisées à la nouvelle scrollbar
            scrollPanelColor(scrollOutils, boolModeNuit);

            // Met à jour la référence pour les appels futurs
            scrollOutils = newOutils;
        }

        // === ONGLET : DÉCOUVERTE ===

        // Met à jour le contenu HTML avec les couleurs dynamiques
        text.setText(String.format(htmlDecouverte, textAppColor, bgAppColor));
        text.setCaretPosition(0); // Remet le curseur en haut
        scrollPanelColor(scrollDecouverte, boolModeNuit); // Style de la scrollbar

        // Fond du panneau selon le thème
        scrollDecouverte.getViewport().setOpaque(true);
        scrollDecouverte.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White
        );

        // Mise à jour visuelle
        scrollDecouverte.revalidate();
        scrollDecouverte.repaint();

        // === ONGLET : APPLICATIONS ===

        // Mise à jour du contenu HTML avec les couleurs actuelles
        textApplication.setText(String.format(htmlApplication, textAppColor, bgAppColor));
        textApplication.setCaretPosition(0);
        scrollPanelColor(scrollApplications, boolModeNuit); // Scrollbar stylée

        // Fond du viewport selon le mode
        scrollApplications.getViewport().setOpaque(true);
        scrollApplications.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White
        );

        // Rafraîchissement
        scrollApplications.revalidate();
        scrollApplications.repaint();

        // === ONGLET : VOCABULAIRE ===

        // Idem pour le texte HTML du vocabulaire
        textVocabulaire.setText(String.format(htmlVocabulaire, textAppColor, bgAppColor));
        textVocabulaire.setCaretPosition(0);
        scrollPanelColor(scrollVocabulaire, boolModeNuit); // Scrollbar

        // Fond personnalisé
        scrollVocabulaire.getViewport().setOpaque(true);
        scrollVocabulaire.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White
        );

        // Actualisation
        scrollVocabulaire.revalidate();
        scrollVocabulaire.repaint();
    }

    public void scrollPanelColor(JScrollPane scrollPanel, boolean boolModeNuit) {
        // Applique une UI personnalisée à la scrollbar verticale
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

            // Définition des couleurs de la piste et du curseur
            @Override
            protected void configureScrollBarColors() {
                if (boolModeNuit) {
                    trackColor = InvertWhite; // Couleur de fond (track) sombre
                    thumbColor = White;       // Curseur clair
                } else {
                    trackColor = White;       // Fond clair
                    thumbColor = InvertWhite; // Curseur sombre
                }
            }

            // Dessine la piste de la scrollbar (zone de fond)
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(trackColor); // Utilise la couleur définie ci-dessus
                g.fillRect(trackBounds.x, trackBounds.y,
                        trackBounds.width, trackBounds.height); // Remplit la zone
            }

            // Dessine le curseur (thumb) avec des bords arrondis
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();

                // Active l'antialiasing pour un rendu plus propre
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(thumbColor); // Couleur du curseur
                int arc = 10;            // Rayon de l'arrondi
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y,
                        thumbBounds.width, thumbBounds.height,
                        arc, arc); // Curseur arrondi

                g2.dispose(); // Libère les ressources graphiques
            }

            // Supprime le bouton de défilement vers le haut
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton(); // Retourne un bouton invisible
            }

            // Supprime le bouton de défilement vers le bas
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton(); // Idem
            }

            // Crée un bouton "vide" (0x0) invisible pour supprimer les flèches par défaut
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                button.setVisible(false);
                return button;
            }
        });

        // === CONFIGURATION GÉNÉRALE DU SCROLLPANE ===

        scrollPanel.setOpaque(false);                // Scrollpane transparent
        scrollPanel.getViewport().setOpaque(false);  // Vue interne transparente

        scrollPanel.setBorder(null);                 // Suppression des bordures
        scrollPanel.getViewport().setBorder(null);
        scrollPanel.getVerticalScrollBar().setBorder(null);

        // Réduit la largeur de la scrollbar verticale pour un look moderne et discret
        scrollPanel.getVerticalScrollBar().setPreferredSize(
                new Dimension(5, Integer.MAX_VALUE)
        );
    }
}

