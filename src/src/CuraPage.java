package src;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class CuraPage extends PagePanel {
    private Color Noir = new Color(0,0,0), White = new Color(255, 255, 255), Rouge = new Color( 255, 100, 100);
    private Color InvertNoir = new Color(200, 200, 200), InvertWhite = new Color(33, 33, 33);
    private JScrollPane scrollInstaller, scrollDecouverte, scrollOutils, scrollApplications, scrollVocabulaire;
    private JTabbedPane onglets;
    private JPanel panelInstaller, panelDecouverte, panelOutils, panelApplication, panelVocabulaire;
    JButton buttonOutils;
    JLabel labelBoutonOutils, labelOutils;
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
        super("");
        setLayout(null);

        // Onglets
        UIManager.put("TabbedPane.selected", White); // Fond onglet actif
        UIManager.put("TabbedPane.contentAreaColor", White); // Fond de la zone des onglets
        UIManager.put("TabbedPane.focus", White); // Couleur de focus (bordures)

        onglets = new JTabbedPane(JTabbedPane.LEFT);
        onglets.setBounds(-30, -1, width + 95, height - 30);
        onglets.setBackground(White);
        onglets.setForeground(Noir);

        onglets.addTab("Installer Cura", createOngletInstaller());
        onglets.addTab("Découverte", createOngletDecouverte());
        onglets.addTab("Outils", createOngletOutils(width, height, false));
        onglets.addTab("Applications", createOngletApplications());
        onglets.addTab("Vocabulaire", createOngletVocabulaire());

        add(onglets);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int w = getWidth();
                int h = getHeight();
                actualiser(w, h);
            }
        });

    }

    private void actualiser(int width, int height) {
        onglets.setBounds(-100, -1, width + 100, height);
    }

    private JPanel createOngletInstaller() {
        panelInstaller = new JPanel();
        panelInstaller.setLayout(new BorderLayout());
        panelInstaller.setBackground(White);
        panelInstaller.setForeground(Noir);
        panelInstaller.setBounds(0, -30, getWidth(), getHeight());
        panelInstaller.setBorder(null);

        textInstaller = new JEditorPane();

// 5. On crée un kit HTML, c’est le moteur de rendu HTML/Swing
        HTMLEditorKit kit = new HTMLEditorKit();

// 6. On fabrique un document HTML vide mais « customisable »
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

// 7. On définit la base (le dossier) où seront résolus tous les src relatifs si l'url est trouver
        URL base = null;
        base = getClass().getResource("/imagesCura/");

        if (base != null) {
            doc.setBase(base);
        }

// 8. On « colle » ce kit et ce document au JEditorPane
        textInstaller.setEditorKit(kit);
        textInstaller.setDocument(doc);

// 4. Ensuite injecte ton HTML
        textInstaller.setText(htmlInstallerCura);
        textInstaller.setContentType("text/html");
        textInstaller.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textInstaller.setEditable(false);
        textInstaller.setBackground(White);
        textInstaller.setForeground(Noir);
        textInstaller.setOpaque(false);
        textInstaller.setBorder(null);
        textInstaller.setFocusable(false);
        textInstaller.setCaretPosition(0);
        textInstaller.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Action quand on clique sur le bouton HTML
                    onglets.setSelectedIndex(1);
                }
            }
        });


        scrollInstaller = new JScrollPane(textInstaller);
        scrollInstaller.getVerticalScrollBar().setUnitIncrement(8);

        scrollPanelColor(scrollInstaller, false);

        panelInstaller.add(scrollInstaller, BorderLayout.CENTER);

        return panelInstaller;
    }

    private JPanel createOngletDecouverte() {
        panelDecouverte = new JPanel();
        panelDecouverte.setLayout(new BorderLayout());
        panelDecouverte.setBackground(White);
        panelDecouverte.setForeground(Noir);
        panelDecouverte.setBounds(0, -30, getWidth(), getHeight());
        panelDecouverte.setBorder(null);

        text = new JEditorPane();
        text.setContentType("text/html");
        text.setText(htmlDecouverte);
        text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        text.setEditable(false);
        text.setBackground(White);
        text.setForeground(Noir);
        text.setOpaque(false);
        text.setBorder(null);
        text.setFocusable(false);
        text.setCaretPosition(0);
        text.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Action quand on clique sur le bouton HTML
                    onglets.setSelectedIndex(2);
                }
            }
        });


        scrollDecouverte = new JScrollPane(text);
        scrollDecouverte.getVerticalScrollBar().setUnitIncrement(8);

        scrollPanelColor(scrollDecouverte, false);

        panelDecouverte.add(scrollDecouverte, BorderLayout.CENTER);

        return panelDecouverte;
    }

    private void creerBoutonOutil(boolean ModeNuit, JPanel panel, String imagePath, String tooltip, String message, int x, int y, int btnSize, String labelText) {
        buttonOutils = new JButton(resizeIcon(new ImageIcon(getClass().getResource(imagePath)), btnSize, btnSize));
        buttonOutils.setToolTipText(tooltip);
        buttonOutils.addActionListener(e -> JOptionPane.showMessageDialog(this, message));
        buttonOutils.setBackground(White);
        buttonOutils.setForeground(Noir);
        buttonOutils.setBorder(BorderFactory.createLineBorder(Noir, 2));
        buttonOutils.setBounds(x, y, btnSize, btnSize);

        labelBoutonOutils = new JLabel("<html><center>" + labelText + "</center></html>");
        labelBoutonOutils.setBounds(x - 20, y + btnSize, btnSize + 40, 30);
        labelBoutonOutils.setHorizontalAlignment(SwingConstants.CENTER);
        labelBoutonOutils.setFont(new Font("SansSerif", Font.PLAIN, 12));

        if (ModeNuit){
            labelBoutonOutils.setForeground(InvertNoir);
        }
        else{
            labelBoutonOutils.setForeground(Noir);
        }

        panel.add(buttonOutils);
        panel.add(labelBoutonOutils);
    }

    private JScrollPane createOngletOutils(int width, int height, boolean modeNuit) {
        int btnSize = 70, spacing = 30;
        panelOutils = new JPanel(null);
        panelOutils.setBackground(modeNuit ? InvertWhite : White);

        JLabel labelOutils = new JLabel("<html><center>Paramètres</center></html>");
        labelOutils.setBounds(35, 5, btnSize + 60, 30);
        labelOutils.setHorizontalAlignment(SwingConstants.CENTER);
        labelOutils.setFont(new Font("SansSerif", Font.PLAIN, 25));
        labelOutils.setForeground(Rouge);
        panelOutils.add(labelOutils);

        int x = 50, y = 40;
        // Ligne 1
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/OuvrirFichier.png", "Importer", "Importe un fichier 3D pour le placer sur le plateau virtuel.", x, y, btnSize, "Importer"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Deplacer.png",       "Positionner", "Permet de déplacer, faire pivoter ou mettre à l’échelle le modèle sur le plateau.", x, y, btnSize, "Positionner"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/ChangerLaTaille.png","Échelle",     "Ajuste la taille du modèle en pourcentage ou valeur absolue.", x, y, btnSize, "Échelle"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Tourner.png",        "Orientation", "Oriente le modèle selon les axes X, Y et Z pour optimiser l’impression.", x, y, btnSize, "Orientation"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Miroir.png",         "Dupliquer miroir", "Génère une version miroir du modèle pour imprimer des pièces symétriques.", x, y, btnSize, "Dupliquer miroir");

        // Ligne 2
        x = 50; y += btnSize + 60;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Parametre.png",       "Profil",      "Choisit le profil d’impression (matériau, épaisseur de couche, remplissage).", x, y, btnSize, "Profil"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/CreationSupport.png","Supports",    "Active ou désactive la génération de supports pour les surplombs.", x, y, btnSize, "Supports"); x += btnSize + spacing;

        // Ligne 3
        x = 50; y += btnSize + 60;
        labelOutils = new JLabel("<html><center>Paramètres détaillés</center></html>");
        labelOutils.setBounds(35, y, btnSize + 200, 30);
        labelOutils.setHorizontalAlignment(SwingConstants.CENTER);
        labelOutils.setFont(new Font("SansSerif", Font.PLAIN, 25));
        labelOutils.setForeground(Rouge);
        panelOutils.add(labelOutils);

        x = 50; y += 40;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Quality.png",            "Hauteur de couche", "Spécifie l’épaisseur de chaque couche : plus fin = plus de détails, plus lent.", x, y, btnSize, "Hauteur de couche"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Walls.png",              "Parois",            "Détermine le nombre et l’épaisseur des parois du modèle pour la solidité.", x, y, btnSize, "Parois"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/TopBottom.png",          "Dessus/Dessous",     "Ajuste le nombre de couches solides en haut et en bas de l’impression.", x, y, btnSize, "Dessus/Dessous"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Infill.png",             "Remplissage",       "Définit le pourcentage et le motif de remplissage intérieur du modèle.", x, y, btnSize, "Remplissage"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Material.png",           "Matériau",          "Choisit le matériau et son diamètre pour optimiser les paramètres d’extrusion.", x, y, btnSize, "Matériau");

        // Ligne 4
        x = 50; y += btnSize + 60;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Speed.png",              "Vitesse",           "Modifie la vitesse d’impression : plus rapide = moins précis.", x, y, btnSize, "Vitesse"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Travel.png",             "Déplacement",       "Ajuste la vitesse des mouvements lorsque la buse ne dépose pas de filament.", x, y, btnSize, "Déplacement"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Cooling.png",            "Refroidissement",   "Active et règle la vitesse des ventilateurs pour solidifier chaque couche.", x, y, btnSize, "Refroidissement"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Support.png",            "Adhérence",         "Sélectionne le type d’adhérence au plateau (raft, skirt, brim).", x, y, btnSize, "Adhérence"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/BuildPlateAdhesion.png","Plateau",           "Permet de régler la distance initiale entre la buse et le plateau.", x, y, btnSize, "Plateau");

        // Ligne 5
        x = 50; y += btnSize + 60;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/DualExtrusion.png",     "Multi-extrusion",   "Permet d’imprimer en plusieurs matériaux ou couleurs simultanément.", x, y, btnSize, "Multi-extrusion"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/MeshFixes.png",         "Réparations",       "Corrige automatiquement les trous et intersections du modèle.", x, y, btnSize, "Réparations"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/SpecialModes.png",      "Modes spéciaux",    "Active des modes d’impression alternatifs (spiralize, coasting…).", x, y, btnSize, "Modes spéciaux"); x += btnSize + spacing;
        creerBoutonOutil(modeNuit, panelOutils, "/imagesCura/Experimental.png",      "Bêta/Expérimental", "Active les options expérimentales non finalisées.", x, y, btnSize, "Bêta/Expérimental");
        x = 50; y += (btnSize);


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
        textOutils.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Action quand on clique sur le bouton HTML
                    onglets.setSelectedIndex(3);
                }
            }
        });
        textOutils.setBounds(250, y, btnSize + 300, 80);
        panelOutils.add(textOutils);
        panelOutils.setPreferredSize(new Dimension(500, y + btnSize + 60));

        scrollOutils = new JScrollPane(panelOutils);
        scrollOutils.setPreferredSize(new Dimension(width, height));
        scrollOutils.setBorder(null);
        scrollPanelColor(scrollOutils, modeNuit);
        scrollOutils.getVerticalScrollBar().setUnitIncrement(8);

        return scrollOutils;
    }


    private JPanel createOngletApplications() {
        panelApplication = new JPanel();
        panelApplication.setLayout(new BorderLayout());
        panelApplication.setBackground(White);
        panelApplication.setForeground(Noir);
        panelApplication.setBounds(0, -30, getWidth(), getHeight());
        panelApplication.setBorder(null);

        textApplication = new JEditorPane();
        textApplication.setContentType("text/html");
        textApplication.setText(htmlApplication);
        textApplication.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textApplication.setEditable(false);
        textApplication.setBackground(White);
        textApplication.setForeground(Noir);
        textApplication.setOpaque(false);
        textApplication.setBorder(null);
        textApplication.setFocusable(false);
        textApplication.setCaretPosition(0);
        textApplication.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Action quand on clique sur le bouton HTML
                    onglets.setSelectedIndex(4);
                }
            }
        });


        scrollApplications = new JScrollPane(textApplication);
        scrollApplications.getVerticalScrollBar().setUnitIncrement(8);

        scrollPanelColor(scrollApplications, false);

        panelApplication.add(scrollApplications, BorderLayout.CENTER);

        return panelApplication;
    }

    private JPanel createOngletVocabulaire() {
        panelVocabulaire = new JPanel();
        panelVocabulaire.setLayout(new BorderLayout());
        panelVocabulaire.setBackground(White);
        panelVocabulaire.setForeground(Noir);
        panelVocabulaire.setBounds(0, -30, getWidth(), getHeight());
        panelVocabulaire.setBorder(null);


        textVocabulaire = new JEditorPane();
        textVocabulaire.setContentType("text/html");
        textVocabulaire.setText(htmlVocabulaire);
        textVocabulaire.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textVocabulaire.setEditable(false);
        textVocabulaire.setBackground(White);
        textVocabulaire.setForeground(Noir);
        textVocabulaire.setOpaque(false);
        textVocabulaire.setBorder(null);
        textVocabulaire.setFocusable(false);
        textVocabulaire.setCaretPosition(0);

        scrollVocabulaire = new JScrollPane(textVocabulaire);
        scrollVocabulaire.getVerticalScrollBar().setUnitIncrement(8);

        scrollPanelColor(scrollVocabulaire, false);

        panelVocabulaire.add(scrollVocabulaire, BorderLayout.CENTER);

        return panelVocabulaire;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public JTabbedPane getOnglets() {
        return onglets;
    }

    // Mis à jour : méthode modeNuitCura pour inclure le thème sombre sur l'onglet Applications
    public void modeNuitCura(boolean boolModeNuit) {
        // Thème global des onglets
        if (boolModeNuit) {
            onglets.setBackground(InvertWhite);
            UIManager.put("TabbedPane.selected", InvertWhite);
            UIManager.put("TabbedPane.contentAreaColor", InvertWhite);
            UIManager.put("TabbedPane.focus", InvertWhite);
        } else {
            onglets.setBackground(White);
            UIManager.put("TabbedPane.selected", White);
            UIManager.put("TabbedPane.contentAreaColor", White);
            UIManager.put("TabbedPane.focus", White);
        }

        String textAppColor = boolModeNuit ? "#ffffff" : "#000000";
        String bgAppColor = boolModeNuit ? "#212121" : "#ffffff";

        // Applications (mis à jour)
        textInstaller.setText(String.format(htmlInstallerCura, textAppColor, bgAppColor));
        textInstaller.setCaretPosition(0);
        scrollPanelColor(scrollInstaller, boolModeNuit);
        scrollInstaller.getViewport().setOpaque(true);
        scrollInstaller.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        scrollInstaller.revalidate();
        scrollInstaller.repaint();

        // Outils
        int idxOutils = onglets.indexOfTab("Outils");
        if (idxOutils != -1) {
            JScrollPane newOutils = createOngletOutils(getWidth(), getHeight(), boolModeNuit);
            onglets.setComponentAt(idxOutils, newOutils);
            scrollPanelColor(scrollOutils, boolModeNuit);
            scrollOutils = newOutils;
        }

        // Découverte
        text.setText(String.format(htmlDecouverte, textAppColor, bgAppColor));
        text.setCaretPosition(0);
        scrollPanelColor(scrollDecouverte, boolModeNuit);
        scrollDecouverte.getViewport().setOpaque(true);
        scrollDecouverte.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        scrollDecouverte.revalidate();
        scrollDecouverte.repaint();

        // Applications (mis à jour)
        textApplication.setText(String.format(htmlApplication, textAppColor, bgAppColor));
        textApplication.setCaretPosition(0);
        scrollPanelColor(scrollApplications, boolModeNuit);
        scrollApplications.getViewport().setOpaque(true);
        scrollApplications.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        scrollApplications.revalidate();
        scrollApplications.repaint();

        // Vocabulaire
        textVocabulaire.setText(String.format(htmlVocabulaire, textAppColor, bgAppColor));
        textVocabulaire.setCaretPosition(0);
        scrollPanelColor(scrollVocabulaire, boolModeNuit);
        scrollVocabulaire.getViewport().setOpaque(true);
        scrollVocabulaire.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        scrollVocabulaire.revalidate();
        scrollVocabulaire.repaint();
    }

    public void scrollPanelColor(JScrollPane scrollPanel, boolean boolModeNuit) {
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                if (boolModeNuit) {
                    trackColor = InvertWhite;              // fond noir
                    thumbColor = White;              // curseur blanc
                }
                else{
                    trackColor = White;              // fond blanc
                    thumbColor = InvertWhite;              // curseur noir
                }
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                // Peindre le fond (blanc)
                // track complètement dans la couleur de fond
                g.setColor(trackColor);
                g.fillRect(trackBounds.x, trackBounds.y,
                        trackBounds.width, trackBounds.height);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dessin du curseur noir arrondi
                g2.setColor(thumbColor);
                int arc = 10; // arrondi
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, arc, arc);

                g2.dispose();
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                button.setVisible(false);
                return button;
            }
        });
        scrollPanel.setOpaque(false);
        scrollPanel.getViewport().setOpaque(false);

        scrollPanel.setBorder(null);
        scrollPanel.getViewport().setBorder(null);

        scrollPanel.getVerticalScrollBar().setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(5, Integer.MAX_VALUE));


    }


    public void flecheActiveOuNon(boolean flecheActiver){
        if (flecheActiver){
            flecheActive = true;
        }
        else{
            flecheActive = false;
        }
    }

}

