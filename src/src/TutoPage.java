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


public class TutoPage extends PagePanel {
    private Color Noir = new Color(0,0,0), White = new Color(255, 255, 255);
    private Color InvertWhite = new Color(33, 33, 33);
    private JScrollPane scrollCube, scrollDes, scrollVaisseau;
    private JTabbedPane onglets;
    private JPanel panelCube, panelDes, panelVaisseau;
    JEditorPane text, textDes, textVaisseau;
    private final String htmlCube = """
<html>
<head>
  <style>
    body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 19px;
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

  <h2>Créer un cube avec un logiciel de modélisation 3D</h2>

  <h3>🧱 1. Créer une esquisse</h3>
  <ul>
    <li>Cliquez sur <b>« Créer une esquisse »</b>.</li>
    <li>Choisissez le <b>plan</b> sur lequel dessiner.</li>
    <br>
        <img src="Cube/Plan.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
    <li>Sélectionnez l’outil <b>rectangle à 2 points</b>.</li>
    <li>Tracez un carré de la taille souhaitée avec un clic gauche.</li>
    <br>
        <img src="Cube/Rectangle2pointPremiereEsquisse.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
    <li>Validez avec un autre <b>clic gauche</b>.</li>
    <br>
        <img src="Cube/Rectangle2pointPremiereEsquisseFait.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
  </ul>

  <h3>📏 2. Coter l’esquisse</h3>
  <ul>
    <li>Utilisez l’outil <b>« Cote d’esquisse »</b>.</li>
    <li>Cliquez sur un côté sans dimension et entrez une <b>valeur précise</b>.</li>
    <br>
        <img src="Cube/PremiereCoteCube.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
    <li>Répétez l’opération pour le second côté.</li>
  </ul>
  <p>
    Grâce à ces cotes, vous obtenez un <b>rectangle verrouillé</b> aux bonnes dimensions.  
    (Le quadrillage en fond peut aider, mais les cotes assurent la précision.)
    <br><br>
        <img src="Cube/CoteCreeCube.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
  </p>

  <h3>✅ 3. Terminer l’esquisse</h3>
  <ul>
    <li>Cliquez sur <b>« Terminer l’esquisse »</b> pour passer à l’étape suivante.</li>
  </ul>

  <h3>📐 4. Extruder pour créer un cube</h3>
  <ul>
    <li>Choisissez l’outil <b>extrusion</b>.</li>
    <li>Sélectionnez la <b>face du carré</b> à extruder.</li>
    <br>
        <img src="Cube/ExtruderCube.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
    <li>Entrez une valeur (ex : <b>25 mm</b>).</li>
    <br>
        <img src="Cube/TailleExtrusionCube.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
    <li>Validez l’opération.</li>
    <br>
        <img src="Cube/CubeCree.png" alt="Plan de l'esquisse" style="width:300px;">
    <br>
  </ul>

  <p class="italic">
    Et voilà ! Vous avez maintenant un cube parfait à partir d’un simple carré extrudé.
  </p>
<p style="text-align:right; margin-top:30px;">
  <a href="action:termine" style="
    display: inline-block;
    padding: 19px;
    font-weight: bold;
    color : 0099ff;
    font-size: 16px;
    text-decoration: none;
    border-radius: 6px;
    cursor: pointer;
    user-select: none;
  ">
    Dés ️️️➡️
  </a>
</p>
</body>
</html>
""";

    private final String htmlDes = """
<html>
<head>
  <style>
    body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 19px;
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

  <h2>Créer un dé à jouer à partir d’un cube</h2>

  <h3>🎲 1. Préparer une face du cube</h3>
  <ul>
    <li>Créez un cube (voir tutoriel précédent).</li>
    <li>Créez une <b>nouvelle esquisse</b> sur l'une des faces du cube.</li>
  </ul>
  <br>
  <img src="Des/FaceSelectionnee.png" alt="Face sélectionnée" style="width:300px;">
  <br>

  <h3>✏️ 2. Créer une croix de construction</h3>
  <ul>
    <li>Sélectionnez l’outil <b>ligne</b>, activez le <b>mode "trait de construction"</b>.</li>
        <br>
        <img src="Des/TraitConstruction.png" alt="Trait de construction" style="width:300px;">
        <br>
    <li>Tracez deux <b>diagonales</b> pour former une croix.</li>
  </ul>
  <br>
  <img src="Des/CroixConstruction.png" alt="Croix de construction" style="width:300px;">
  <br>

  <h3>⚪ 3. Ajouter un point (cercle)</h3>
  <ul>
    <li>Créez un <b>cercle</b> au centre de la croix.</li>
    <li>Désactivez le mode "trait de construction" avant de le dessiner.</li>
  </ul>
  <br>
  <img src="Des/CercleCentre.png" alt="Cercle au centre" style="width:300px;">
  <br>

  <h3>📐 4. Répéter pour chaque face</h3>
  <ul>
    <li>Répétez l’opération sur chaque face avec des cercles de 1 à 6.</li>
    <li>Utilisez des <b>lignes de construction</b> et l’outil <b>coïncident</b> pour bien positionner les cercles. Par exemple, créé un rectangle que l'on colle au diagonale pour obtenir un carré grace a l'outils coïncident. A partir de la, je peux placer chaque cercle dans les coin du carré puis placer les autre ou il le faut.</li>
    <li>Pour la face 6, on à : </li>

  </ul>
  <br>
  <img src="Des/Face6.png" alt="Face avec 6 cercles" style="width:300px;">
  <br>

  <h3>🔢 5. Astuces de placement</h3>
  <ul>
    <li><b>Face 2</b> : Une diagonale + deux petites allant au centre → 2 cercles au centre.</li>
      <br>
      <img src="Des/Face2.png" alt="Face avec 5 cercles" style="width:300px;">
      <br>
    <li><b>Face 3</b> : Même base que la face 2 + un cercle central.</li>
      <br>
      <img src="Des/Face3.png" alt="Face avec 5 cercles" style="width:300px;">
      <br>
    <li><b>Face 4</b> : Rectangle avec 4 coins et cercles dans chaque coin.</li>
      <br>
      <img src="Des/Face4.png" alt="Face avec 5 cercles" style="width:300px;">
      <br>
    <li><b>Face 5</b> : Même que face 4 + un cercle central.</li>
      <br>
      <img src="Des/Face5.png" alt="Face avec 5 cercles" style="width:300px;">
      <br>
    <li><b>Face 6</b> : Rectangle + 6 cercles (4 coins + 2 milieux des bords opposés).</li>
      <br>
      <img src="Des/Face6.png" alt="Face avec 5 cercles" style="width:300px;">
      <br>
    <li><b>Resultat : </b></li>
  </ul>
  <br>
  <img src="Des/ResultatEsquisse.png" alt="Extrusion d’un cercle" style="width:300px;">
  <br>
  
  <h3>🕳️ 6. Extruder les points</h3>
  <ul>
    <li>Terminez chaque esquisse.</li>
    <li>Utilisez l’outil <b>extrusion</b> pour <b>creuser vers l’intérieur</b> chaque cercle (ex: -1 mm).</li>
    <li>Faites cela <b>face par face</b>.</li>
  </ul>
  <br>
  <img src="Des/ExtrusionPoint.png" alt="Extrusion d’un cercle" style="width:300px;">
  <br>

  <br>
  <img src="Des/ResultatDes1.png" alt="Extrusion d’un cercle" style="width:300px;">
  <br>
  
    <br>
  <img src="Des/ResultatDes2.png" alt="Extrusion d’un cercle" style="width:300px;">
  <br>
    <p class="italic">
    Et voilà ! Vous avez maintenant un <b>dé réaliste</b> avec ses points correctement extrudés sur chaque face.
  </p>
  <p style="text-align:right; margin-top:30px;">
  <a href="action:termine" style="
    display: inline-block;
    padding: 19px;
    font-weight: bold;
    color : 0099ff;
    font-size: 16px;
    text-decoration: none;
    border-radius: 6px;
    cursor: pointer;
    user-select: none;
  ">
    Vaisseau ️️️➡️
  </a>
</p>
</body>
</html>
""";


    private final String htmlVaisseau = """
<html>
<head>
  <style>
    body {
        font-family: Arial, sans-serif;
        font-size: 14px;
        line-height: 1.6;
        padding: 19px;
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

  <h2>Créer un vaisseau spatial avec un logiciel de modélisation 3D</h2>
    <img src="Vaisseau/Vaisseau.png" alt="Carré de base" style="width:300px;">
  <h3>🟦 1. Création du carré de base</h3>
  <ul>
    <li>Cliquez sur <b>« Créer une esquisse »</b>.</li>
    <li>Sélectionnez un plan de base (XY par exemple).</li>
    <li>Créez un <b>carré</b> centré de la taille de votre choix.</li>
    <br>
    <img src="Vaisseau/CarreBase.png" alt="Carré de base" style="width:300px;">
  </ul>

  <h3>📄 2. Créer un plan de décalage</h3>
  <ul>
    <li>Quittez l’esquisse précédente.</li>
    <li>Cliquez sur <b>« Créer un plan de décalage »</b>.</li>
    <br>
        <img src="Vaisseau/PlanDecalage.png" alt="Plan de décalage" style="width:300px;">
    <li>Sélectionnez le plan contenant le carré, puis entrez <b>150 mm</b>.</li>
    <br>
    <img src="Vaisseau/PlanDecalage1.png" alt="Plan de décalage" style="width:300px;">
    <br>
        <img src="Vaisseau/PlanDecalage1A.png" alt="Plan de décalage" style="width:300px;">
  </ul>

  <h3>⚪ 3. Créer un cercle sur le plan décalé</h3>
  <ul>
    <li>Créez une esquisse sur ce nouveau plan.</li>
    <li>Placez un <b>cercle de 50 mm</b> de diamètre centré.</li>
    <br>
    <img src="Vaisseau/Cercle150.png" alt="Cercle sur le plan à 150mm" style="width:300px;">
  </ul>

  <h3>🔗 4. Utiliser l'outil lissage</h3>
  <ul>
    <li>Cliquez sur l’outil <b>Lissage</b>.</li>
    <li>Sélectionnez les deux formes (le carré et le cercle).</li>
    <br>
    <img src="Vaisseau/Lissage1.png" alt="Lissage entre carré et cercle" style="width:300px;">
    <li>Validez pour obtenir la première forme du vaisseau.</li>
  </ul>

  <h3>📄 5. Créer un second plan de décalage</h3>
  <ul>
    <li>Créez un nouveau plan décalé à <b>30 mm</b> du cercle.</li>
    <br>
        <img src="Vaisseau/PlanDecalage2.png" alt="Carré de 350mm sur le plan" style="width:300px;">
    <li>Créez une esquisse sur ce plan.</li>
    <li>Ajoutez un <b>carré centré de 350 mm</b> de côté.</li>
    <br>
    <img src="Vaisseau/Carre350.png" alt="Carré de 350mm sur le plan" style="width:300px;">
  </ul>

  <h3>🔗 6. Lissage entre cercle et carré</h3>
  <ul>
    <li>Utilisez à nouveau l’outil <b>Lissage</b> pour relier le cercle et le grand carré.</li>
    <br>
    <img src="Vaisseau/Lissage2.png" alt="Lissage avec le grand carré" style="width:300px;">
  </ul>

  <h3>🪞 7. Faire une symétrie miroir</h3>
  <ul>
    <li>Cliquez sur <b>Symétrie miroir</b>.</li>
    <li>Sélectionnez l’option <b>Fonction</b>.</li>
    <li>Choisissez comme plan miroir celui utilisé pour le carré initial.</li>
    <br>
    <img src="Vaisseau/Miroir1.png" alt="Symétrie de fonction" style="width:300px;">
  </ul>

  <h3>🌐 8. Ajouter une sphère centrale</h3>
  <ul>
    <li>Utilisez l’outil <b>Sphère</b>.</li>
    <li>Placez-la au centre du vaisseau avec un <b>diamètre de 200 mm</b>.</li>
    <li>Paramètre : <b>Joindre</b> aux autres pièces.</li>
    <br>
    <img src="Vaisseau/Sphere.png" alt="Sphère centrale" style="width:300px;">
  </ul>

  <h3>🪞 9. Deuxième symétrie miroir (objet)</h3>
  <ul>
    <li>Si le plan miroir n’est pas visible, cliquez sur <b>Construction</b> puis activez l'œil correspondant.</li>
    <br>
    <img src="Vaisseau/Visibilite.png" alt="Deuxième symétrie" style="width:300px;">
    <br>
    <img src="Vaisseau/Visibilite2.png" alt="Deuxième symétrie" style="width:300px;">
    <li>Refaites une symétrie, cette fois en choisissant <b>Objet</b>.</li>
    <br>
    <img src="Vaisseau/ParametreMiroir2.png" alt="ParametreMiroir2" style="width:300px;">
    <br>
    <img src="Vaisseau/Miroir2.png" alt="Deuxième symétrie" style="width:300px;">
    <br>
    <br>
    <li>Et voici notre vaisseau !</li>
    <img src="Vaisseau/Vaisseau1.png" alt="Deuxième symétrie" style="width:300px;">
  </ul>

  <h3>🛠️ 10. Améliorations possibles</h3>

  <h4>🔘 Ajouter des congés</h4>
  <ul>
    <li>Cliquez sur <b>Congé</b>.</li>
    <li>Sélectionnez les arêtes reliant les ailes au cockpit.</li>
    <br>
    <img src="Vaisseau/ChoixConges.png" alt="Ajout de congés" style="width:300px;">
    <li>Entrez un rayon de <b>20 mm</b>.</li>
    <br>
    <img src="Vaisseau/Conges.png" alt="Ajout de congés" style="width:300px;">
  </ul>

  <h4>📐 Ajouter des chanfreins</h4>
  <ul>
    <li>Cliquez sur <b>Chanfrein</b>.</li>
    <li>Sélectionnez les mêmes arêtes.</li>
    <br>
    <img src="Vaisseau/ChoixChanfreins.png" alt="Ajout de chanfreins" style="width:300px;">
    <li>Entrez également une valeur de <b>20 mm</b>.</li>
    <br>
    <img src="Vaisseau/Chanfreins.png" alt="Ajout de chanfreins" style="width:300px;">
  </ul>

  <p class="italic">
    Bravo ! Vous avez maintenant un vaisseau 3D stylisé. Vous pouvez personnaliser les dimensions et les détails pour obtenir des formes variées.
  </p>

  <p style="text-align:right; margin-top:30px;">
    <a href="action:termine" style="
      display: inline-block;
      padding: 19px;
      font-weight: bold;
      color : 0099ff;
      font-size: 16px;
      text-decoration: none;
      border-radius: 6px;
      cursor: pointer;
      user-select: none;
    ">
      Installation de Cura ➡️
    </a>
  </p>

</body>
</html>
""";
    
    private boolean flecheActive = false;
    public TutoPage(int width, int height, CuraPage LaPageCura, Main LaPageMain) {
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

        onglets.addTab("Cube", createOngletCube());
        onglets.addTab("Des", createOngletDes());
        onglets.addTab("Vaisseau", createOngletVaisseau(LaPageCura, LaPageMain));

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


    private JPanel createOngletCube() {
        panelCube = new JPanel();
        panelCube.setLayout(new BorderLayout());
        panelCube.setBackground(White);
        panelCube.setForeground(Noir);
        panelCube.setBounds(0, -30, getWidth(), getHeight());
        panelCube.setBorder(null);

        text = new JEditorPane();

// 5. On crée un kit HTML, c’est le moteur de rendu HTML/Swing
        HTMLEditorKit kit = new HTMLEditorKit();

// 6. On fabrique un document HTML vide mais « customisable »
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

// 7. On définit la base (le dossier) où seront résolus tous les src relatifs si l'url est trouver
        URL base = null;
        base = getClass().getResource("/imagesTuto/");

        if (base != null) {
            doc.setBase(base);
        }

// 8. On « colle » ce kit et ce document au JEditorPane
        text.setEditorKit(kit);
        text.setDocument(doc);

// 4. Ensuite injecte ton HTML
        text.setText(htmlCube);
        text.setContentType("text/html");
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
                    onglets.setSelectedIndex(1);
                }
            }
        });

        scrollCube = new JScrollPane(text);
        scrollCube.getVerticalScrollBar().setUnitIncrement(8);

        scrollPanelColor(scrollCube, false);

        panelCube.add(scrollCube, BorderLayout.CENTER);

        return panelCube;
    }

    private JPanel createOngletDes() {
        panelDes = new JPanel();
        panelDes.setLayout(new BorderLayout());
        panelDes.setBackground(White);
        panelDes.setForeground(Noir);
        panelDes.setBounds(0, -30, getWidth(), getHeight());
        panelDes.setBorder(null);

        textDes = new JEditorPane();

// 5. On crée un kit HTML, c’est le moteur de rendu HTML/Swing
        HTMLEditorKit kit = new HTMLEditorKit();

// 6. On fabrique un document HTML vide mais « customisable »
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

// 7. On définit la base (le dossier) où seront résolus tous les src relatifs si l'url est trouver
        URL base = null;
        base = getClass().getResource("/imagesTuto/");

        if (base != null) {
            doc.setBase(base);
        }

// 8. On « colle » ce kit et ce document au JEditorPane
        textDes.setEditorKit(kit);
        textDes.setDocument(doc);

// 4. Ensuite injecte ton HTML
        textDes.setText(htmlDes);
        textDes.setContentType("text/html");
        textDes.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textDes.setEditable(false);
        textDes.setBackground(White);
        textDes.setForeground(Noir);
        textDes.setOpaque(false);
        textDes.setBorder(null);
        textDes.setFocusable(false);
        textDes.setCaretPosition(0);
        textDes.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Action quand on clique sur le bouton HTML
                    onglets.setSelectedIndex(2);
                }
            }
        });

        scrollDes = new JScrollPane(textDes);
        scrollDes.getVerticalScrollBar().setUnitIncrement(8);

        scrollPanelColor(scrollDes, false);

        panelDes.add(scrollDes, BorderLayout.CENTER);

        return panelDes;
    }

    private JPanel createOngletVaisseau(CuraPage LaPageCura, Main LaPageMain) {
        panelVaisseau = new JPanel();
        panelVaisseau.setLayout(new BorderLayout());
        panelVaisseau.setBackground(White);
        panelVaisseau.setForeground(Noir);
        panelVaisseau.setBounds(0, -30, getWidth(), getHeight());
        panelVaisseau.setBorder(null);

        textVaisseau = new JEditorPane();

// 5. On crée un kit HTML, c’est le moteur de rendu HTML/Swing
        HTMLEditorKit kit = new HTMLEditorKit();

// 6. On fabrique un document HTML vide mais « customisable »
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

// 7. On définit la base (le dossier) où seront résolus tous les src relatifs si l'url est trouver
        URL base = null;
        base = getClass().getResource("/imagesTuto/");

        if (base != null) {
            doc.setBase(base);
        }


// 8. On « colle » ce kit et ce document au JEditorPane
        textVaisseau.setEditorKit(kit);
        textVaisseau.setDocument(doc);

// 4. Ensuite injecte ton HTML
        textVaisseau.setText(htmlVaisseau);
        textVaisseau.setContentType("text/html");
        textVaisseau.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textVaisseau.setEditable(false);
        textVaisseau.setBackground(White);
        textVaisseau.setForeground(Noir);
        textVaisseau.setOpaque(false);
        textVaisseau.setBorder(null);
        textVaisseau.setFocusable(false);
        textVaisseau.setCaretPosition(0);
        textVaisseau.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Action quand on clique sur le bouton HTML
                    LaPageMain.afficherPage(LaPageCura);
                }
            }
        });

        scrollVaisseau = new JScrollPane(textVaisseau);
        scrollVaisseau.getVerticalScrollBar().setUnitIncrement(8);

        scrollPanelColor(scrollVaisseau, false);

        panelVaisseau.add(scrollVaisseau, BorderLayout.CENTER);

        return panelVaisseau;
    }

    public JTabbedPane getOnglets() {
        return onglets;
    }

    // Mis à jour : méthode modeNuitCura pour inclure le thème sombre sur l'onglet Applications
    public void modeNuitTuto(boolean boolModeNuit) {
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

        String textColor = boolModeNuit ? "#ffffff" : "#000000";
        String bgColor = boolModeNuit ? "#212121" : "#ffffff";

        // Cube
        text.setText(String.format(htmlCube, textColor, bgColor));
        text.setCaretPosition(0);
        scrollPanelColor(scrollCube, boolModeNuit);
        scrollCube.getViewport().setOpaque(true);
        scrollCube.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        scrollCube.revalidate();
        scrollCube.repaint();

        // Des
        textDes.setText(String.format(htmlDes, textColor, bgColor));
        textDes.setCaretPosition(0);
        scrollPanelColor(scrollDes, boolModeNuit);
        scrollDes.getViewport().setOpaque(true);
        scrollDes.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        scrollDes.revalidate();
        scrollDes.repaint();

        // Vaisseau
        String textAppColor = boolModeNuit ? "#ffffff" : "#000000";
        String bgAppColor = boolModeNuit ? "#212121" : "#ffffff";
        textVaisseau.setText(String.format(htmlVaisseau, textAppColor, bgAppColor));
        textVaisseau.setCaretPosition(0);
        scrollPanelColor(scrollVaisseau, boolModeNuit);
        scrollVaisseau.getViewport().setOpaque(true);
        scrollVaisseau.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        scrollVaisseau.revalidate();
        scrollVaisseau.repaint();
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

