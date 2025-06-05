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
      <img src="Cube/Rectangle2pointPremiereEsquisse.png" alt="Rectangle esquisse" style="width:300px;">
      <br>
      <li>Validez avec un autre <b>clic gauche</b>.</li>
      <br>
      <img src="Cube/Rectangle2pointPremiereEsquisseFait.png" alt="Rectangle fait" style="width:300px;">
      <br>
    </ul>

    <h3>📏 2. Coter l’esquisse</h3>
    <ul>
      <li>Utilisez l’outil <b>« Cote d’esquisse »</b>.</li>
      <li>Cliquez sur un côté sans dimension et entrez une <b>valeur précise</b>.</li>
      <br>
      <img src="Cube/PremiereCoteCube.png" alt="Première cote" style="width:300px;">
      <br>
      <li>Répétez l’opération pour le second côté.</li>
    </ul>
    <p>
      Grâce à ces cotes, vous obtenez un <b>rectangle verrouillé</b> aux bonnes dimensions.
      (Le quadrillage en fond peut aider, mais les cotes assurent la précision.)
      <br><br>
      <img src="Cube/CoteCreeCube.png" alt="Cotes créées" style="width:300px;">
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
      <img src="Cube/ExtruderCube.png" alt="Extrusion" style="width:300px;">
      <br>
      <li>Entrez une valeur (ex : <b>25 mm</b>).</li>
      <br>
      <img src="Cube/TailleExtrusionCube.png" alt="Taille extrusion" style="width:300px;">
      <br>
      <li>Validez l’opération.</li>
      <br>
      <img src="Cube/CubeCree.png" alt="Cube créé" style="width:300px;">
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
        color: 0099ff;
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
  </ul>
  <br>
  <img src="Des/TraitConstruction.png" alt="Trait de construction" style="width:300px;">
  <br>
  <ul>
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
    <li>
      Utilisez des <b>lignes de construction</b> et l’outil <b>coïncident</b> pour bien positionner les cercles. 
      Par exemple, créez un rectangle collé aux diagonales pour obtenir un carré grâce à l'outil coïncident. 
      Ensuite, placez chaque cercle dans les coins du carré puis les autres au bon endroit.
    </li>
    <li>Pour la face 6, on a :</li>
  </ul>
  <br>
  <img src="Des/Face6.png" alt="Face avec 6 cercles" style="width:300px;">
  <br>

  <h3>🔢 5. Astuces de placement</h3>
  <ul>
    <li><b>Face 2</b> : Une diagonale + deux petites allant au centre → 2 cercles au centre.</li>
  </ul>
  <br>
  <img src="Des/Face2.png" alt="Face avec 2 cercles" style="width:300px;">
  <br>
  <ul>
    <li><b>Face 3</b> : Même base que la face 2 + un cercle central.</li>
  </ul>
  <br>
  <img src="Des/Face3.png" alt="Face avec 3 cercles" style="width:300px;">
  <br>
  <ul>
    <li><b>Face 4</b> : Rectangle avec 4 coins et cercles dans chaque coin.</li>
  </ul>
  <br>
  <img src="Des/Face4.png" alt="Face avec 4 cercles" style="width:300px;">
  <br>
  <ul>
    <li><b>Face 5</b> : Même que face 4 + un cercle central.</li>
  </ul>
  <br>
  <img src="Des/Face5.png" alt="Face avec 5 cercles" style="width:300px;">
  <br>
  <ul>
    <li><b>Face 6</b> : Rectangle + 6 cercles (4 coins + 2 milieux des bords opposés).</li>
    <li><b>Résultat :</b></li>
  </ul>
  <br>
  <img src="Des/ResultatEsquisse.png" alt="Résultat esquisse" style="width:300px;">
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
  <img src="Des/ResultatDes1.png" alt="Résultat dé 1" style="width:300px;">
  <br>
  <img src="Des/ResultatDes2.png" alt="Résultat dé 2" style="width:300px;">
  <br>

  <p class="italic">
    Et voilà ! Vous avez maintenant un <b>dé réaliste</b> avec ses points correctement extrudés sur chaque face.
  </p>

  <p style="text-align:right; margin-top:30px;">
    <a href="action:termine" style="
      display: inline-block;
      padding: 19px;
      font-weight: bold;
      color: #0099ff;
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

    // Attribut indiquant si la flèche (bouton ou animation) est active ou non
    private boolean flecheActive = false;

    public TutoPage(int width, int height, CuraPage LaPageCura, Main LaPageMain) {
        // Utilisation d'un layout nul pour positionnement absolu des composants
        setLayout(null);

        // Configuration des couleurs et styles pour les onglets (JTabbedPane)
        // Fond de l'onglet sélectionné
        UIManager.put("TabbedPane.selected", White);
        // Couleur de fond de la zone d'affichage des onglets
        UIManager.put("TabbedPane.contentAreaColor", White);
        // Couleur du contour lorsqu'un onglet a le focus
        UIManager.put("TabbedPane.focus", White);

        // Création d'un JTabbedPane positionné sur la gauche
        onglets = new JTabbedPane(JTabbedPane.LEFT);
        // Positionnement et dimensions initiales du composant onglets
        onglets.setBounds(-30, -1, width + 95, height - 30);
        // Définition de la couleur de fond pour la zone des onglets
        onglets.setBackground(White);
        // Définition de la couleur du texte pour les titres d'onglets
        onglets.setForeground(Noir);

        // Ajout des onglets avec leurs panneaux respectifs
        // Onglet "Cube" : appelle la méthode createOngletCube() pour construire son contenu
        onglets.addTab("Cube", createOngletCube());
        // Onglet "Des" : appelle la méthode createOngletDes() pour construire son contenu
        onglets.addTab("Des", createOngletDes());
        // Onglet "Vaisseau" : appelle la méthode createOngletVaisseau(...) en passant en paramètre
        // les références aux pages Cura et Main si besoin de naviguer vers ces pages
        onglets.addTab("Vaisseau", createOngletVaisseau(LaPageCura, LaPageMain));

        // Ajout du JTabbedPane (les onglets) à la page TutoPage
        add(onglets);

        // Ajout d'un écouteur d'événement pour gérer le redimensionnement de la page
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


    private JPanel createOngletCube() {
        // Création du panneau principal pour l'onglet Cube
        panelCube = new JPanel();
        // Utilisation d'un BorderLayout pour remplir tout l'espace disponible
        panelCube.setLayout(new BorderLayout());
        // Couleur de fond et de premier plan du panneau
        panelCube.setBackground(White);
        panelCube.setForeground(Noir);
        // Position et dimension initiale du panneau (sera ajusté ultérieurement par actualiser())
        panelCube.setBounds(0, -30, getWidth(), getHeight());
        // Suppression de toute bordure par défaut
        panelCube.setBorder(null);

        // Création d'un JEditorPane pour afficher du contenu HTML
        text = new JEditorPane();

        // 5. On crée un kit HTML : c’est le moteur de rendu HTML fourni par Swing
        HTMLEditorKit kit = new HTMLEditorKit();

        // 6. On fabrique un document HTML vide mais « customisable » via ce kit
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

        // 7. On définit la base (le dossier) où seront résolus tous les src relatifs
        URL base = getClass().getResource("/imagesTuto/");
        if (base != null) {
            // Si le chemin vers le dossier images existe, on le passe au document
            doc.setBase(base);
        }

        // 8. On « colle » ce kit et ce document au JEditorPane pour pouvoir interpréter du HTML
        text.setEditorKit(kit);
        text.setDocument(doc);

        // 4. On injecte ensuite le contenu HTML (chaîne htmlCube définie ailleurs)
        text.setText(htmlCube);
        // On précise que le contenu est du HTML
        text.setContentType("text/html");
        // Autorise le JEditorPane à respecter les styles système (police, etc.)
        text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        // Rend le JEditorPane non éditable pour l'utilisateur
        text.setEditable(false);
        // Couleur de fond et de premier plan du texte
        text.setBackground(White);
        text.setForeground(Noir);
        // Rend l'arrière-plan du JEditorPane transparent (laisse apparaître panelCube derrière)
        text.setOpaque(false);
        // Suppression de toute bordure autour du JEditorPane
        text.setBorder(null);
        // Ne pas pouvoir recevoir le focus (évite des encadrements de sélection inattendus)
        text.setFocusable(false);
        // Position de départ du curseur (en haut du document)
        text.setCaretPosition(0);
        // Ajout d'un listener pour gérer les clics sur les liens HTML (HyperlinkEvents)
        text.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Lorsque l'utilisateur clique sur le lien "action:termine", on passe à l'onglet suivant (index 1)
                    onglets.setSelectedIndex(1);
                }
            }
        });

        // Encapsulation du JEditorPane dans un JScrollPane pour gérer le défilement vertical
        scrollCube = new JScrollPane(text);
        // Ajuste la vitesse de défilement de la molette (8 pixels par cran)
        scrollCube.getVerticalScrollBar().setUnitIncrement(8);

        // Applique les couleurs de fond/claire ou sombre au JScrollPane (méthode définie ailleurs)
        scrollPanelColor(scrollCube, false);

        // Ajout du JScrollPane (contenant le JEditorPane) au centre de panelCube
        panelCube.add(scrollCube, BorderLayout.CENTER);

        return panelCube;
    }

    private JPanel createOngletDes() {
        // Création du panneau principal pour l'onglet "Des"
        panelDes = new JPanel();
        // Utilisation d'un BorderLayout pour occuper tout l'espace disponible
        panelDes.setLayout(new BorderLayout());
        // Définition des couleurs de fond et de premier plan du panneau
        panelDes.setBackground(White);
        panelDes.setForeground(Noir);
        // Position et dimensions initiales (sera ajusté par actualiser() lors du redimensionnement)
        panelDes.setBounds(0, -30, getWidth(), getHeight());
        // Suppression de toute bordure par défaut
        panelDes.setBorder(null);

        // Création d'un JEditorPane pour afficher le contenu HTML
        textDes = new JEditorPane();

        // 5. On crée un kit HTML : c’est le moteur de rendu HTML fourni par Swing
        HTMLEditorKit kit = new HTMLEditorKit();

        // 6. On fabrique un document HTML vide mais « customisable » via ce kit
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

        // 7. On définit la base (le dossier) où seront résolus tous les src relatifs si l'URL est trouvé
        URL base = getClass().getResource("/imagesTuto/");
        if (base != null) {
            // Si le dossier imagesTuto existe dans le classpath, on le définit comme base
            doc.setBase(base);
        }

        // 8. On « colle » ce kit et ce document au JEditorPane pour permettre l'interprétation de HTML
        textDes.setEditorKit(kit);
        textDes.setDocument(doc);

        // 4. Ensuite, on injecte le contenu HTML (chaîne htmlDes définie ailleurs)
        textDes.setText(htmlDes);
        // On précise que le contenu est du HTML
        textDes.setContentType("text/html");
        // Autorise le JEditorPane à honorer les propriétés d’affichage (police système, etc.)
        textDes.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        // Rend le JEditorPane non éditable par l'utilisateur
        textDes.setEditable(false);
        // Définition de la couleur de fond et de premier plan
        textDes.setBackground(White);
        textDes.setForeground(Noir);
        // Rend le fond transparent pour laisser apparaître le panneau derrière
        textDes.setOpaque(false);
        // Suppression de toute bordure autour du JEditorPane
        textDes.setBorder(null);
        // Empêche le composant de recevoir le focus
        textDes.setFocusable(false);
        // Place le curseur au début du document
        textDes.setCaretPosition(0);
        // Ajout d'un listener pour gérer les clics sur les liens HTML
        textDes.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Lorsque l'utilisateur clique sur "action:termine", on passe à l'onglet "Vaisseau" (index 2)
                    onglets.setSelectedIndex(2);
                }
            }
        });

        // Encapsulation du JEditorPane dans un JScrollPane pour gérer le défilement vertical
        scrollDes = new JScrollPane(textDes);
        // Réglage de la vitesse de défilement de la molette (8 pixels par incrément)
        scrollDes.getVerticalScrollBar().setUnitIncrement(8);

        // Application des couleurs (clair/sombre) au JScrollPane
        scrollPanelColor(scrollDes, false);

        // Ajout du JScrollPane (contenant le JEditorPane) au centre du panneau panelDes
        panelDes.add(scrollDes, BorderLayout.CENTER);

        return panelDes;
    }

    private JPanel createOngletVaisseau(CuraPage LaPageCura, Main LaPageMain) {
        // Création du panneau principal pour l'onglet "Vaisseau"
        panelVaisseau = new JPanel();
        // Utilisation d'un BorderLayout pour occuper tout l'espace disponible
        panelVaisseau.setLayout(new BorderLayout());
        // Définition des couleurs de fond et de premier plan du panneau
        panelVaisseau.setBackground(White);
        panelVaisseau.setForeground(Noir);
        // Position et dimensions initiales (sera ajusté par actualiser() lors du redimensionnement)
        panelVaisseau.setBounds(0, -30, getWidth(), getHeight());
        // Suppression de toute bordure par défaut
        panelVaisseau.setBorder(null);

        // Création d'un JEditorPane pour afficher le contenu HTML
        textVaisseau = new JEditorPane();

        // 5. On crée un kit HTML : c’est le moteur de rendu HTML fourni par Swing
        HTMLEditorKit kit = new HTMLEditorKit();

        // 6. On fabrique un document HTML vide mais « customisable » via ce kit
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

        // 7. On définit la base (le dossier) où seront résolus tous les src relatifs si l'URL est trouvée
        URL base = getClass().getResource("/imagesTuto/");
        if (base != null) {
            // Si le dossier imagesTuto existe dans le classpath, on le définit comme base
            doc.setBase(base);
        }

        // 8. On « colle » ce kit et ce document au JEditorPane pour permettre l'interprétation de HTML
        textVaisseau.setEditorKit(kit);
        textVaisseau.setDocument(doc);

        // 4. Ensuite, on injecte le contenu HTML (chaîne htmlVaisseau définie ailleurs)
        textVaisseau.setText(htmlVaisseau);
        // On précise que le contenu est du HTML
        textVaisseau.setContentType("text/html");
        // Autorise le JEditorPane à honorer les propriétés d’affichage (police système, etc.)
        textVaisseau.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        // Rend le JEditorPane non éditable par l'utilisateur
        textVaisseau.setEditable(false);
        // Définition de la couleur de fond et de premier plan
        textVaisseau.setBackground(White);
        textVaisseau.setForeground(Noir);
        // Rend le fond transparent pour laisser apparaître le panneau derrière
        textVaisseau.setOpaque(false);
        // Suppression de toute bordure autour du JEditorPane
        textVaisseau.setBorder(null);
        // Empêche le composant de recevoir le focus
        textVaisseau.setFocusable(false);
        // Place le curseur au début du document
        textVaisseau.setCaretPosition(0);
        // Ajout d'un listener pour gérer les clics sur les liens HTML
        textVaisseau.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Lorsque l'utilisateur clique sur le lien "action:termine",
                    // on affiche la page Cura en utilisant la référence fournie
                    LaPageMain.afficherPage(LaPageCura);
                }
            }
        });

        // Encapsulation du JEditorPane dans un JScrollPane pour gérer le défilement vertical
        scrollVaisseau = new JScrollPane(textVaisseau);
        // Réglage de la vitesse de défilement de la molette (8 pixels par incrément)
        scrollVaisseau.getVerticalScrollBar().setUnitIncrement(8);

        // Application des couleurs (clair/sombre) au JScrollPane
        scrollPanelColor(scrollVaisseau, false);

        // Ajout du JScrollPane (contenant le JEditorPane) au centre du panneau panelVaisseau
        panelVaisseau.add(scrollVaisseau, BorderLayout.CENTER);

        return panelVaisseau;
    }

    public JTabbedPane getOnglets() {
        return onglets;
    }

    // Méthode pour activer/désactiver le thème sombre (mode nuit) dans la page Tuto
    public void modeNuitTuto(boolean boolModeNuit) {
        // Configuration du thème global pour le JTabbedPane
        if (boolModeNuit) {
            // En mode nuit : fond sombre pour les onglets
            onglets.setBackground(InvertWhite);
            UIManager.put("TabbedPane.selected", InvertWhite);
            UIManager.put("TabbedPane.contentAreaColor", InvertWhite);
            UIManager.put("TabbedPane.focus", InvertWhite);
        } else {
            // En mode clair : fond blanc pour les onglets
            onglets.setBackground(White);
            UIManager.put("TabbedPane.selected", White);
            UIManager.put("TabbedPane.contentAreaColor", White);
            UIManager.put("TabbedPane.focus", White);
        }

        // Définition des couleurs de texte et de fond selon le mode
        String textColor = boolModeNuit ? "#ffffff" : "#000000";
        String bgColor = boolModeNuit ? "#212121" : "#ffffff";

        // ===== Onglet "Cube" =====
        // Mise à jour du contenu HTML avec les nouvelles couleurs (texte + fond)
        text.setText(String.format(htmlCube, textColor, bgColor));
        // Repositionnement du curseur au début du document
        text.setCaretPosition(0);
        // Application du style clair/sombre au JScrollPane
        scrollPanelColor(scrollCube, boolModeNuit);
        // S’assure que la zone de visualisation (viewport) est opaque
        scrollCube.getViewport().setOpaque(true);
        // Définition du fond de la viewport selon le mode (gris foncé en mode nuit, blanc sinon)
        scrollCube.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        // Réactualisation visuelle du composant
        scrollCube.revalidate();
        scrollCube.repaint();

        // ===== Onglet "Des" =====
        // Mise à jour du contenu HTML pour l’onglet "Des"
        textDes.setText(String.format(htmlDes, textColor, bgColor));
        // Repositionnement du curseur au début du document
        textDes.setCaretPosition(0);
        // Application du style clair/sombre au JScrollPane
        scrollPanelColor(scrollDes, boolModeNuit);
        // Fond de la viewport (opaque) selon le mode
        scrollDes.getViewport().setOpaque(true);
        scrollDes.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        // Réactualisation visuelle du composant
        scrollDes.revalidate();
        scrollDes.repaint();

        // ===== Onglet "Vaisseau" =====
        // Les variables textAppColor et bgAppColor sont identiques à textColor/bgColor
        // mais séparées au cas où on voudrait un style différent pour ce sous-onglet
        String textAppColor = boolModeNuit ? "#ffffff" : "#000000";
        String bgAppColor = boolModeNuit ? "#212121" : "#ffffff";
        // Mise à jour du contenu HTML pour l’onglet "Vaisseau"
        textVaisseau.setText(String.format(htmlVaisseau, textAppColor, bgAppColor));
        // Repositionnement du curseur au début du document
        textVaisseau.setCaretPosition(0);
        // Application du style clair/sombre au JScrollPane
        scrollPanelColor(scrollVaisseau, boolModeNuit);
        // Fond de la viewport (opaque) selon le mode
        scrollVaisseau.getViewport().setOpaque(true);
        scrollVaisseau.getViewport().setBackground(boolModeNuit ? new Color(0x21, 0x21, 0x21) : White);
        // Réactualisation visuelle du composant
        scrollVaisseau.revalidate();
        scrollVaisseau.repaint();
    }

    public void scrollPanelColor(JScrollPane scrollPanel, boolean boolModeNuit) {
        // Personnalisation de l'apparence de la barre de défilement verticale
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                // Définition des couleurs du track (fond) et du thumb (curseur) selon le mode
                if (boolModeNuit) {
                    trackColor = InvertWhite; // Fond sombre en mode nuit
                    thumbColor = White;       // Curseur blanc en mode nuit
                } else {
                    trackColor = White;       // Fond blanc en mode clair
                    thumbColor = InvertWhite; // Curseur sombre en mode clair
                }
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                // Peinture du fond de la zone de track
                g.setColor(trackColor);
                g.fillRect(
                        trackBounds.x,
                        trackBounds.y,
                        trackBounds.width,
                        trackBounds.height
                );
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                // Peinture du curseur (thumb) avec coins arrondis
                Graphics2D g2 = (Graphics2D) g.create();
                // Activation de l'anticrénelage pour un rendu plus lisse
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Couleur du curseur selon le mode
                g2.setColor(thumbColor);
                int arc = 10; // Rayon des coins arrondis
                g2.fillRoundRect(
                        thumbBounds.x,
                        thumbBounds.y,
                        thumbBounds.width,
                        thumbBounds.height,
                        arc,
                        arc
                );

                g2.dispose();
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                // Remplace le bouton de réduction par un bouton invisible (hauteur nulle)
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                // Remplace le bouton d'agrandissement par un bouton invisible (hauteur nulle)
                return createZeroButton();
            }

            private JButton createZeroButton() {
                // Création d'un bouton de taille 0x0 pour masquer complètement les boutons flèche
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                button.setVisible(false);
                return button;
            }
        });

        // Rendre la JScrollPane et sa viewport transparentes pour voir la couleur du parent
        scrollPanel.setOpaque(false);
        scrollPanel.getViewport().setOpaque(false);

        // Suppression des bordures autour de la JScrollPane et de la viewport
        scrollPanel.setBorder(null);
        scrollPanel.getViewport().setBorder(null);

        // Suppression de la bordure de la barre de défilement et réduction de sa largeur
        scrollPanel.getVerticalScrollBar().setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(5, Integer.MAX_VALUE));
    }

    public void flecheActiveOuNon(boolean flecheActiver) {
        // Si le paramètre indique d’activer la flèche, on définit flecheActive à true
        if (flecheActiver) {
            flecheActive = true;
        } else {
            // Dans le cas contraire, on désactive la flèche (flecheActive = false)
            flecheActive = false;
        }
    }
}

