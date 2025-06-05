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


public class FusionPage extends PagePanel {
    private JScrollPane scrollInstaller, scrollDecouverte, scrollOutils, scrollApplications, scrollVocabulaire;
    private JEditorPane textInstaller, text, textOutils, textApplication, textVocabulaire;
    private JTabbedPane onglets;
    private Color Noir = new Color(0,0,0), White = new Color(255, 255, 255), Rouge = new Color( 255, 100, 100);
    private Color InvertNoir = new Color(200, 200, 200), InvertWhite = new Color(33, 33, 33);
    private JPanel panelInstaller, panelFusionDecouverte, panelOutils, panelApplication, panelVocabulaire;
    private JLabel labelBoutonOutils, labelOutils;
    private JButton buttonOutils;
    private String htmlInstaller = """
    <html>
      <head>
        <meta charset="UTF-8">
        <title>Tutoriel : Installer Fusion 360</title>
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

        <h2>🔧 Tutoriel : Installer Autodesk Fusion 360 (usage personnel)</h2>

        <h3>1. Accéder à la page officielle</h3>
        <p>Rendez-vous sur :</p>
        <p>
          <a href="https://www.autodesk.com/ca-fr/products/fusion-360/personal" target="_blank">
            https://www.autodesk.com/ca-fr/products/fusion-360/personal
          </a>
        </p>
        <p>➡️ Cliquez sur <b>Obtenir Autodesk Fusion pour un usage personnel</b>.</p>
        <img src="obtenir-fusion.png" alt="Obtenir Fusion">
        <br>
        <img src="se-connecter.png" alt="Créer un compte">

        <h3>2. Créer un compte</h3>
        <p>➡️ Cliquez sur <b>Créer un compte</b>.</p>
        <img src="cree-compte.png" alt="Créer un compte">

        <h3>3. Revenir à la page</h3>
        <p>✅ Après avoir créé votre compte, retournez sur la page précédente.</p>
        <img src="revenir-page.png" alt="Retour à la page">

        <h3>4. Se reconnecter</h3>
        <p>➡️ Cliquez sur <b>Se connecter</b>.</p>
        <img src="se-connecter2.png" alt="Se connecter">

        <h3>5. Finaliser l'inscription</h3>
        <p>➡️ Complétez le formulaire d'inscription si ce n'est pas déjà fait.</p>
        <img src="inscription.png" alt="Inscription 1">

        <p>➡️ Remplissez comme ceci puis soumettez.</p>
        <img src="inscription-remplie.png" alt="Formulaire rempli">

        <h3>6. Télécharger Fusion 360</h3>
        <p>✅ Vous pouvez maintenant télécharger le logiciel.</p>
        <img src="telecharger-fusion.png" alt="Téléchargement Fusion">

        <h3>7. Vérification par e-mail</h3>
        <p>⚠️ Avant installation, il faudra se reconnecter une dernière fois.</p>
        <p>➡️ Vérifiez votre boîte mail et saisissez le code reçu en cliquant sur <b>Se connecter</b>.</p>
        <img src="verif-mail.png" alt="Vérification mail">

        <h3>8. Lancer l'installation</h3>
        <p>➡️ Double-cliquez sur le fichier téléchargé dans votre dossier <b>Téléchargements</b>.</p>
        <img src="fichier-telechargement.png" alt="Fichier téléchargé">

        <h3>9. Page d'accueil après installation</h3>
        <p>Voici l'écran d'accueil après installation :</p>
        <img src="page-apres-install.png" alt="Page d'accueil">

        <p>➡️ Connectez-vous avec vos identifiants Autodesk.</p>
        <img src="connexion-apres-install.png" alt="Connexion après installation">

        <h3>10. Finaliser la configuration</h3>
        <p>➡️ Vous pouvez maintenant accéder au produit.</p>
        <p>➡️ Passez les différentes pages en cliquant sur <b>Suivant</b>.</p>
        <p>✅ La première question sera : <b>Donnez un nom à votre hub</b>.</p>
        <img src="nom-hub.png" alt="Nom du hub">

        <p>➡️ Ne changez rien et cliquez sur <b>Créer</b>.</p>
        <img src="cree-hub.png" alt="Créer le hub">

        <p>➡️ Cliquez sur <b>Aller au hub</b>.</p>
        <img src="aller-au-hub.png" alt="Aller au hub">

        <h3>11. Créer un nouveau projet</h3>
        <p>➡️ Choisissez <b>Conception</b> puis cliquez sur <b>Mise en route</b>.</p>
        <p>➡️ Cliquez sur <b>New</b>.</p>
        <p>➡️ Une page apparaîtra, cliquez à nouveau sur <b>Mise en route</b>.</p>
        <p>➡️ Fermez les deux pages à droite.</p>
        <img src="fermer-pages-droite.png" alt="Fermer les pages">

        <h3>🎉 Prêt à concevoir !</h3>
        <p>Vous êtes maintenant prêt à commencer vos projets dans Fusion 360 !</p>

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
            Découverte Fusion 360 ➡️
          </a>
        </p>

      </body>
    </html>
    """;

    private String htmlDecouverte = """
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
          ul {
            margin-left: 20px;
          }
        </style>
      </head>
      <body>

        <h2>Découvrir la modélisation 3D avec Fusion 360</h2>

        <p>
          <b>Fusion 360</b> est un logiciel complet de modélisation 3D développé par Autodesk.
          Il permet de passer d’une idée à un objet réel en combinant des outils de
          conception, d’usinage et de simulation.
        </p>

        <h3>1. Créer une esquisse</h3>
        <ul>
          <li>Cliquez sur <i>“Créer une esquisse”</i> dans la barre d’outils.</li>
          <li>Sélectionnez un plan (horizontal, vertical, latéral) ou une face.</li>
          <li>Vous entrez alors dans l’espace de dessin 2D.</li>
        </ul>

        <h3>2. Dessiner des formes 2D</h3>
        <p>Utilisez les outils suivants :</p>
        <ul>
          <li><b>Ligne :</b> relie deux points par un segment droit.</li>
          <li><b>Rectangle :</b> centré ou à deux coins opposés.</li>
          <li><b>Cercle :</b> centre-rayon, deux points, etc.</li>
          <li><b>Arc, ellipse, polygone, spline…</b></li>
        </ul>

        <h3>3. Ajouter cotations et contraintes</h3>
        <ul>
          <li><b>Cotation :</b> définit les dimensions (longueur, angle…).</li>
          <li><b>Contraintes :</b> horizontale, perpendiculaire, égale…</li>
          <li>Une esquisse définie devient noire (au lieu de bleu).</li>
        </ul>

        <h3>4. Terminer l’esquisse</h3>
        <p>Cliquez sur <i>“Terminer l’esquisse”</i> pour revenir à l’environnement 3D.</p>

        <h3>5. Transformer en 3D</h3>
        <ul>
          <li><b>Extrusion :</b> tire une forme pour créer du volume.</li>
          <li><b>Révolution :</b> tourne un profil autour d’un axe.</li>
          <li><b>Balayage :</b> extrude le long d’un chemin.</li>
          <li><b>Loft :</b> relie plusieurs profils différents.</li>
        </ul>

        <h3>6. Conseils pour bien débuter</h3>
        <ul>
          <li>Sauvegardez régulièrement votre travail.</li>
          <li>Activez grilles et accrochages pour la précision.</li>
          <li>Nommez vos esquisses et composants.</li>
          <li>Clic droit pour explorer plus d’options.</li>
        </ul>

        <p class="italic">
          Tout commence par une bonne esquisse. Maîtriser cette étape est essentiel
          pour créer des objets 3D solides et précis.
        </p>

        <p style="text-align:right; margin-top:30px;">
          <a href="action:termine" style="
            display: inline-block;
            padding: 10px;
            font-weight: bold;
            font-size: 16px;
            color: #0099ff;
            text-decoration: none;
            border-radius: 6px;
            cursor: pointer;
            user-select: none;
          ">
            Outils Fusion 360 ➡️
          </a>
        </p>

      </body>
    </html>
    """;

    private String htmlOutils = """
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
            color: #0099ff;
            text-decoration: none;
            border-radius: 6px;
            cursor: pointer;
            user-select: none;
          ">
            Application Fusion 360 ➡️
          </a>
        </p>
      </body>
    </html>
    """;

    private String htmlApplication = """
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
        <h2>Applications de la modélisation 3D</h2>

        <h3>🏭 Conception Assistée par Ordinateur (CAO)</h3>
        <p>
          Utilisée en ingénierie mécanique, électronique ou architecturale, la CAO permet de concevoir des pièces techniques précises. Elle sert à simuler des contraintes, optimiser des assemblages et préparer la fabrication (impression 3D, usinage CNC, injection plastique).
        </p>

        <h3>🖨️ Impression 3D</h3>
        <p>
          La modélisation 3D est essentielle pour créer des objets physiques à partir de fichiers numériques. Elle facilite le prototypage rapide, la fabrication de pièces sur mesure, la réparation d’objets ou encore la création de prothèses et de maquettes.
        </p>

        <h3>🎮 Jeux Vidéo</h3>
        <p>
          Dans les jeux vidéo, la modélisation permet de concevoir personnages, décors, objets ou véhicules. Associée à l’animation et aux moteurs de jeu, elle contribue à créer des univers immersifs et interactifs.
        </p>

        <h3>🎬 Cinéma</h3>
        <p>
          Le cinéma utilise la 3D pour les effets spéciaux, l’animation ou la création d’environnements impossibles à filmer. Elle est incontournable dans les films d’animation, blockbusters ou séries modernes.
        </p>

        <h3>🏥 Médecine</h3>
        <p>
          En médecine, elle permet de modéliser des organes à partir d’IRM ou scanners, planifier des interventions, former les étudiants ou créer des prothèses sur mesure.
        </p>

        <h3>🏗️ Architecture</h3>
        <p>
          Les architectes conçoivent et visualisent leurs projets grâce à la modélisation 3D. Elle permet de simuler la lumière, optimiser les espaces et tester différentes solutions esthétiques ou structurelles.
        </p>

        <h3>🚗 Automobile</h3>
        <p>
          Dans l’automobile, elle est utilisée pour le design de véhicules, les tests aérodynamiques, la fabrication de prototypes et de pièces de rechange, accélérant le développement et réduisant les coûts.
        </p>

        <h3>📱 Réalité Augmentée / Virtuelle</h3>
        <p>
          Elle alimente les expériences immersives en AR/VR, en intégrant des objets 3D interactifs dans le monde réel ou simulé. Ces technologies sont utilisées dans le divertissement, la formation, l’industrie ou encore la santé.
        </p>

        <p class="italic">
          La modélisation 3D est un outil transversal au cœur de nombreuses innovations. Sa maîtrise ouvre des portes dans des domaines variés et passionnants.
        </p>

        <p style="text-align:right; margin-top:30px;">
          <a href="action:termine" style="
            display: inline-block;
            padding: 10px;
            font-weight: bold;
            font-size: 16px;
            color: #0099ff;
            text-decoration: none;
            border-radius: 6px;
            cursor: pointer;
            user-select: none;
          ">
            Vocabulaire Fusion 360 ➡️
          </a>
        </p>
      </body>
    </html>
    """;

    private String htmlVocabulaire = """
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

        <h2>Vocabulaire de la modélisation 3D</h2>

        <h3>📐 Esquisse</h3>
        <p>
          Une esquisse est un dessin 2D tracé sur un plan, servant de base à la modélisation 3D.
          Elle contient des formes simples (lignes, cercles…) pouvant être <b>contraintes</b> (alignement, perpendicularité)
          et <b>cotées</b> (dimensions). Une esquisse bien définie est essentielle à un modèle précis.
        </p>

        <h3>🌀 Congé</h3>
        <p>
          Le congé arrondit l’angle entre deux surfaces. Il améliore la solidité, facilite l’usinage ou améliore l’esthétique.
          Dans Fusion 360, on sélectionne les arêtes et on définit le rayon.
        </p>

        <h3>🔧 Fonction Extrusion</h3>
        <p>
          Transforme une esquisse 2D en forme 3D en l’étirant. Peut aussi enlever de la matière (extrusion négative).
        </p>

        <h3>📐 Révolution</h3>
        <p>
          Fait tourner une esquisse autour d’un axe pour créer une forme. Exemple : un demi-cercle → sphère.
        </p>

        <h3>📈 Balayage (Sweep)</h3>
        <p>
          Extrude une esquisse le long d’un chemin. Utile pour tuyaux, câbles, rainures.
        </p>

        <h3>🔁 Symétrie</h3>
        <p>
          Duplique une forme par effet miroir. Très pratique pour les objets symétriques.
        </p>

        <h3>📊 Mise en plan</h3>
        <p>
          Génère des dessins techniques à partir du modèle 3D avec vues, cotes, annotations. Exportables en PDF ou DXF.
        </p>

        <h3>🔗 Assemblage</h3>
        <p>
          Rassemble plusieurs composants et utilise des joints pour simuler des mouvements mécaniques.
        </p>

        <h3>📎 Contraintes d’esquisse</h3>
        <p>
          Permettent d’imposer des relations (parallélisme, angles droits…). Assurent stabilité et cohérence.
        </p>

        <h3>📏 Cotations</h3>
        <p>
          Fixent des dimensions précises (longueur, diamètre…). Rendent le modèle modifiable paramétriquement.
        </p>

        <h3>🔄 Historique de conception</h3>
        <p>
          Toutes les étapes sont enregistrées dans une timeline. Modifier une étape met à jour les suivantes.
        </p>

        <h3>🔧 Composants et Corps</h3>
        <p>
          <b>Corps</b> : objets 3D simples (ex : une pièce).<br>
          <b>Composants</b> : contiennent corps, esquisses, contraintes. Indispensables pour les projets complexes.
        </p>

        <p style="text-align:right; margin-top:30px;">
          <a href="action:termine" style="
            display: inline-block;
            padding: 10px;
            font-weight: bold;
            font-size: 16px;
            color: #0099ff;
            text-decoration: none;
            border-radius: 6px;
            cursor: pointer;
            user-select: none;
          ">
            Tuto Cube ➡️
          </a>
        </p>

      </body>
    </html>
    """;

    public FusionPage(int width, int height, TutoPage LaPageTuto, Main LaPageMain) {
        setLayout(null); // Utilise un positionnement libre (absolu)

        // Personnalise l'apparence des onglets
        UIManager.put("TabbedPane.selected", White); // Fond de l'onglet sélectionné
        UIManager.put("TabbedPane.contentAreaColor", White); // Fond de la zone de contenu des onglets
        UIManager.put("TabbedPane.focus", White); // Couleur des bordures quand un onglet est sélectionné

        // Crée le composant des onglets, positionnés à gauche
        onglets = new JTabbedPane(JTabbedPane.LEFT);
        onglets.setBounds(-30, -1, width + 95, height - 30);
        onglets.setBackground(White);
        onglets.setForeground(Noir);

        // Ajoute les différents onglets avec leurs contenus respectifs
        onglets.addTab("Installer", createOngletInstaller());
        onglets.addTab("Découverte", createOngletDecouverte());
        onglets.addTab("Outils", createOngletOutils(width, height, false));
        onglets.addTab("Applications", createOngletApplications());
        onglets.addTab("Vocabulaire", createOngletVocabulaire(LaPageTuto, LaPageMain));

        // Ajoute les onglets à la page principale
        add(onglets);

        // Met à jour la taille des onglets si la fenêtre est redimensionnée
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int w = getWidth();
                int h = getHeight();
                actualiser(w, h);
            }
        });
    }

    // Met à jour la taille et la position du composant "onglets" en l'étendant vers la gauche
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

        // Crée un moteur de rendu HTML pour interpréter le contenu
        HTMLEditorKit kit = new HTMLEditorKit();

        // Crée un document HTML vide, personnalisable
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();

        // Définit le dossier de base pour retrouver les ressources (ex : images)
        URL base = null;
        base = getClass().getResource("/imagesFusion/Install/");

        if (base != null) {
            doc.setBase(base);
        }

        // Applique le moteur de rendu et le document HTML au composant
        textInstaller.setEditorKit(kit);
        textInstaller.setDocument(doc);

        // Ajoute le contenu HTML à afficher
        textInstaller.setText(htmlInstaller);
        textInstaller.setContentType("text/html");
        textInstaller.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textInstaller.setEditable(false);
        textInstaller.setBackground(White);
        textInstaller.setForeground(Noir);
        textInstaller.setOpaque(false);
        textInstaller.setBorder(null);
        textInstaller.setFocusable(false);
        textInstaller.setCaretPosition(0);

        // Gère les clics sur les liens dans le HTML
        textInstaller.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Si on clique sur "action:termine", on passe à l'onglet suivant
                    onglets.setSelectedIndex(1);
                }
            }
        });

        // Ajoute un scroll avec un défilement fluide
        scrollInstaller = new JScrollPane(textInstaller);
        scrollInstaller.getVerticalScrollBar().setUnitIncrement(8);

        // Applique une couleur personnalisée au scroll
        scrollPanelColor(scrollInstaller, false);

        // Ajoute le panneau avec le texte dans le panneau principal
        panelInstaller.add(scrollInstaller, BorderLayout.CENTER);

        return panelInstaller;
    }

    private JPanel createOngletDecouverte() {
        // Crée le panneau principal pour l’onglet "Découverte"
        panelFusionDecouverte = new JPanel();
        panelFusionDecouverte.setLayout(new BorderLayout());
        panelFusionDecouverte.setBackground(White);
        panelFusionDecouverte.setForeground(Noir);
        panelFusionDecouverte.setBounds(0, -30, getWidth(), getHeight());
        panelFusionDecouverte.setBorder(null);

        // Crée une zone de texte HTML pour afficher le contenu
        text = new JEditorPane();
        text.setContentType("text/html");
        text.setText(htmlDecouverte); // Injecte le contenu HTML
        text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        text.setEditable(false); // Rend le texte non modifiable
        text.setBackground(White);
        text.setForeground(Noir);
        text.setOpaque(false);
        text.setBorder(null);
        text.setFocusable(false);
        text.setCaretPosition(0); // Positionne le curseur en haut

        // Gère les clics sur les liens dans le HTML
        text.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Si on clique sur "action:termine", on passe à l'onglet suivant
                    onglets.setSelectedIndex(2);
                }
            }
        });

        // Ajoute une zone de défilement avec vitesse personnalisée
        scrollDecouverte = new JScrollPane(text);
        scrollDecouverte.getVerticalScrollBar().setUnitIncrement(8);

        // Applique une couleur personnalisée au scroll
        scrollPanelColor(scrollDecouverte, false);

        // Ajoute le contenu HTML défilable au panneau
        panelFusionDecouverte.add(scrollDecouverte, BorderLayout.CENTER);

        return panelFusionDecouverte;
    }

    private void creerBoutonOutil(boolean ModeNuit, JPanel panel, String imagePath, String tooltip, String message, int x, int y, int btnSize, String labelText) {
        // Crée un bouton avec une icône redimensionnée
        buttonOutils = new JButton(resizeIcon(new ImageIcon(getClass().getResource(imagePath)), btnSize, btnSize));
        buttonOutils.setToolTipText(tooltip); // Affiche un texte au survol
        buttonOutils.addActionListener(e -> JOptionPane.showMessageDialog(this, message)); // Affiche un message au clic
        buttonOutils.setBackground(White);
        buttonOutils.setForeground(Noir);
        buttonOutils.setBorder(BorderFactory.createLineBorder(Noir, 2)); // Bordure noire de 2px
        buttonOutils.setBounds(x, y, btnSize, btnSize); // Positionne le bouton

        // Crée un label sous le bouton avec un texte centré
        labelBoutonOutils = new JLabel("<html><center>" + labelText + "</center></html>");
        labelBoutonOutils.setBounds(x - 20, y + btnSize, btnSize + 40, 30); // Positionne le label sous le bouton
        labelBoutonOutils.setHorizontalAlignment(SwingConstants.CENTER);
        labelBoutonOutils.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Change la couleur du texte selon le mode nuit
        if (ModeNuit){
            labelBoutonOutils.setForeground(InvertNoir);
        }
        else{
            labelBoutonOutils.setForeground(Noir);
        }

        // Ajoute le bouton et le label au panneau
        panel.add(buttonOutils);
        panel.add(labelBoutonOutils);
    }

    private JScrollPane createOngletOutils(int width, int height, boolean modeNuit) {
        int btnSize = 70;                // Taille des boutons
        int spacing = 30;               // Espace entre boutons
        panelOutils = new JPanel(null); // Panneau avec Layout nul pour positionnement absolu

        // Définit la couleur de fond selon le mode (jour/nuit)
        if (modeNuit) {
            panelOutils.setBackground(InvertWhite);
        } else {
            panelOutils.setBackground(White);
        }

        int x = 50, y = 40; // Coordonnées de départ pour le premier bouton

        // Création du titre de la section "Esquisse"
        labelOutils = new JLabel("<html><center>" + "Esquisse" + "</center></html>");
        labelOutils.setBounds(35, 5, btnSize + 60, 30);             // Position et taille du label
        labelOutils.setHorizontalAlignment(SwingConstants.CENTER);  // Centre le texte horizontalement
        labelOutils.setFont(new Font("SansSerif", Font.PLAIN, 25)); // Police et taille du titre
        labelOutils.setForeground(Rouge);                           // Couleur du texte du titre
        panelOutils.add(labelOutils);                                // Ajoute le titre au panneau

        // ESQUISSE - Création : ensemble de boutons pour dessiner des formes de base
        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/CreeUneEsquisse.png",
                "Créer une esquisse", "Démarre une nouvelle esquisse 2D sur une face ou un plan.",
                x, y, btnSize, "Créer esquisse");
        x += btnSize + spacing; // Décale la position pour le bouton suivant

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Ligne.png",
                "Ligne", "Trace une ligne droite dans l’esquisse.",
                x, y, btnSize, "Ligne");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Rectangle2Point.png",
                "Rectangle 2 points", "Crée un rectangle défini par deux coins opposés.",
                x, y, btnSize, "Rectangle");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Rectangle3Point.png",
                "Rectangle 3 points", "Crée un rectangle en utilisant un angle et un côté.",
                x, y, btnSize, "Rectangle 3 pts");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/RectangleParLeCentre.png",
                "Rectangle par centre", "Crée un rectangle depuis le centre vers un coin.",
                x, y, btnSize, "Rect. centre");

        // Passe à la ligne suivante : remet x au départ et augmente y
        x = 50;
        y += btnSize + 60;

        // ESQUISSE - Formes courbes : boutons pour dessiner des cercles et arcs
        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Cercle.png",
                "Cercle centre-rayon", "Crée un cercle défini par un centre et un rayon.",
                x, y, btnSize, "Cercle");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Cercle2Point.png",
                "Cercle 2 points", "Crée un cercle à partir de deux points sur son diamètre.",
                x, y, btnSize, "Cercle 2 pts");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Cercle3Point.png",
                "Cercle 3 points", "Crée un cercle passant par trois points.",
                x, y, btnSize, "Cercle 3 pts");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/3PointArc.png",
                "Arc 3 points", "Crée un arc de cercle en trois points.",
                x, y, btnSize, "Arc");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Ellipse.png",
                "Ellipse", "Crée une ellipse par deux axes.",
                x, y, btnSize, "Ellipse");

        // Nouvelle ligne pour les autres formes courbes
        x = 50;
        y += btnSize + 60;

        // ESQUISSE - Autres formes : boutons pour Spline, conique, etc.
        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Fit Point Spline.png",
                "Spline", "Crée une courbe lisse passant par plusieurs points.",
                x, y, btnSize, "Spline");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Conic Curve.png",
                "Courbe conique", "Crée une courbe conique définie par points et angles.",
                x, y, btnSize, "Conique");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Point.png",
                "Point", "Ajoute un point de construction ou de référence.",
                x, y, btnSize, "Point");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Texte.png",
                "src.Texte", "Insère du texte dans l’esquisse.",
                x, y, btnSize, "src.Texte");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Mirror.png",
                "Symétrie", "Crée une symétrie d’objets esquissés par rapport à une ligne.",
                x, y, btnSize, "Symétrie");

        // Passage à la ligne suivante pour répétitions et cotations
        x = 50;
        y += btnSize + 60;

        // ESQUISSE - Répétition et cotation : motifs et cotes
        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Circular Pattern.png",
                "Motif circulaire", "Répète des éléments autour d’un centre.",
                x, y, btnSize, "Motif circ.");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Rectangular Pattern.png",
                "Motif rectangulaire", "Répète des éléments en grille.",
                x, y, btnSize, "Motif rect.");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Cote d'esquisse.png",
                "Cotation", "Ajoute des dimensions à l’esquisse.",
                x, y, btnSize, "Cote");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Congé.png",
                "Congé", "Ajoute un arrondi entre deux lignes.",
                x, y, btnSize, "Congé");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/HorizontaleVerticale.png",
                "H/V", "Contraint une ligne à l’horizontale ou verticale.",
                x, y, btnSize, "H/V");

        // Nouvelle ligne pour la section Contraintes
        x = 50;
        y += btnSize + 60;

        // CONTRAINTES : boutons pour différentes contraintes géométriques
        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Coïncident.png",
                "Coïncidence", "Contraint deux entités à se toucher.",
                x, y, btnSize, "Coïncident");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Tangent.png",
                "Tangente", "Contraint un cercle ou arc à être tangent à une ligne.",
                x, y, btnSize, "Tangente");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Egal.png",
                "Égalité", "Contraint deux longueurs ou rayons à être égaux.",
                x, y, btnSize, "Égal");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Parallele.png",
                "Parallèle", "Contraint deux lignes à être parallèles.",
                x, y, btnSize, "Parallèle");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Perpendiculaire.png",
                "Perpendiculaire", "Contraint deux lignes à être perpendiculaires.",
                x, y, btnSize, "Perpendiculaire");

        // Ligne suivante pour autres outils
        x = 50;
        y += btnSize + 60;

        // Boutons de verrouillage, milieu, inspection et terminer l’esquisse
        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Lock.png",
                "Fixer", "Verrouille la position d’un objet dans l’espace.",
                x, y, btnSize, "Fixer");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Milieu.png",
                "Milieu", "Contraint un point au milieu d’une ligne.",
                x, y, btnSize, "Milieu");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Inspect.png",
                "Inspecter", "Analyse les contraintes ou mesures dans l’esquisse.",
                x, y, btnSize, "Inspecter");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Terminer l'esquisse.png",
                "Terminer esquisse", "Quitte le mode esquisse et valide les formes.",
                x, y, btnSize, "Terminer esquisse");

        // Ajuste y pour passer à la section suivante, "Volumique"
        x = 50;
        y += (btnSize + 60) * 1.4;

        // Création du titre de la section "Volumique"
        labelOutils = new JLabel("<html><center>" + "Volumique" + "</center></html>");
        labelOutils.setBounds(42, y, btnSize + 60, 30);           // Position et taille du label
        labelOutils.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte
        labelOutils.setFont(new Font("SansSerif", Font.PLAIN, 25));
        labelOutils.setForeground(Rouge);                         // Couleur du titre
        panelOutils.add(labelOutils);                              // Ajoute le titre au panneau

        // Déplacement vertical pour commencer les boutons 3D
        x = 50;
        y += 40;

        // FONCTIONS 3D : extrusion, révolution, balayage, loft, trou
        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Extrusion.png",
                "Extrusion", "Transforme une esquisse en volume par extrusion.",
                x, y, btnSize, "Extrusion");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Revolve.png",
                "Révolution", "Crée un volume en tournant une esquisse autour d’un axe.",
                x, y, btnSize, "Révolution");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Sweep.png",
                "Balayage", "Extrude une esquisse le long d’un chemin.",
                x, y, btnSize, "Balayage");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Loft.png",
                "Fusion (Loft)", "Crée un volume entre plusieurs profils.",
                x, y, btnSize, "Loft");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Hole.png",
                "Trou", "Crée un trou dans un corps 3D.",
                x, y, btnSize, "Trou");

        // Nouvelle ligne pour forme de base 3D (boîte, cylindre, etc.)
        x = 50;
        y += btnSize + 60;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Box.png",
                "Boîte", "Crée une boîte 3D basique.",
                x, y, btnSize, "Boîte");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Cylinder.png",
                "Cylindre", "Crée un cylindre simple.",
                x, y, btnSize, "Cylindre");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Sphere.png",
                "Sphère", "Crée une sphère 3D.",
                x, y, btnSize, "Sphère");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Torus.png",
                "Tore", "Crée une forme en anneau (tore).",
                x, y, btnSize, "Tore");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Coil.png",
                "Ressort (coil)", "Crée un ressort hélicoïdal.",
                x, y, btnSize, "Ressort");

        // Ligne suivante pour motifs 3D et tuyaux
        x = 50;
        y += btnSize + 60;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Pipe.png",
                "Tuyau", "Crée une extrusion circulaire le long d’un chemin.",
                x, y, btnSize, "Tuyau");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/3DRectangularPattern.png",
                "Motif 3D rect.", "Répète un volume selon une grille 3D.",
                x, y, btnSize, "Motif 3D rect.");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/3DCircularPattern.png",
                "Motif 3D circ.", "Répète un volume autour d’un axe.",
                x, y, btnSize, "Motif 3D circ.");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/3DPatternOnPath.png",
                "Motif sur chemin", "Répète un volume le long d’un chemin 3D.",
                x, y, btnSize, "Motif chemin");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/3DMirror.png",
                "Symétrie 3D", "Crée une symétrie d’un corps 3D.",
                x, y, btnSize, "Sym. 3D");

        // Ligne suivante pour opérations solides (Push/Pull, congé, chanfrein)
        x = 50;
        y += btnSize + 60;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/AppuyerTirer.png",
                "Appuyer/Tirer", "Modifie rapidement la géométrie d’un solide.",
                x, y, btnSize, "Push/Pull");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Congé3D.png",
                "Congé 3D", "Arrondit une arête sur un volume 3D.",
                x, y, btnSize, "Congé 3D");
        x += btnSize + spacing;

        creerBoutonOutil(modeNuit, panelOutils, "/imagesFusion/Chanfrain.png",
                "Chanfrein", "Ajoute un biseau sur une arête 3D.",
                x, y, btnSize, "Chanfrein");

        // Création d’une zone de texte HTML explicative en bas de l’onglet Outils
        x = 250;
        textOutils = new JEditorPane();
        textOutils.setContentType("text/html");                    // Type HTML pour le contenu
        textOutils.setText(htmlOutils);                             // Texte HTML injecté
        textOutils.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textOutils.setEditable(false);                              // Non modifiable
        textOutils.setBackground(White);
        textOutils.setForeground(Noir);
        textOutils.setOpaque(false);
        textOutils.setBorder(null);
        textOutils.setFocusable(false);

        // Gère les clics sur les liens dans le texte HTML
        textOutils.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Si l’action "termine" est cliquée, on passe à l’onglet Applications
                    onglets.setSelectedIndex(3);
                }
            }
        });

        // Place la zone de texte en bas, à droite
        textOutils.setBounds(250, y, btnSize + 300, 80);
        panelOutils.add(textOutils);

        // Ajuste la taille préférée du panneau pour contenir tous les éléments
        panelOutils.setPreferredSize(new Dimension(500, y + btnSize + 60));

        // Crée la JScrollPane pour rendre le panneau défilable
        scrollOutils = new JScrollPane(panelOutils);
        scrollOutils.setPreferredSize(new Dimension(width, height));
        scrollOutils.setBorder(null);

        // Applique une couleur personnalisée au scroll
        scrollPanelColor(scrollOutils, false);

        // Vitesse de défilement personnalisée
        scrollOutils.getVerticalScrollBar().setUnitIncrement(8);
        scrollOutils.getVerticalScrollBar().setValue(0);

        // Garantit que le scroll commence en haut lorsque l’onglet est affiché
        SwingUtilities.invokeLater(() -> {
            scrollOutils.getVerticalScrollBar().setValue(0);
        });

        return scrollOutils;
    }

    private JPanel createOngletApplications() {
        panelApplication = new JPanel();                     // Crée le panneau principal pour l'onglet "Applications"
        panelApplication.setLayout(new BorderLayout());      // Utilise un BorderLayout pour organiser le contenu
        panelApplication.setBackground(White);                // Fond blanc
        panelApplication.setForeground(Noir);                 // Texte en noir
        panelApplication.setBounds(0, -30, getWidth(), getHeight()); // Positionnement et taille du panneau
        panelApplication.setBorder(null);                     // Pas de bordure

        // Crée une zone de texte HTML pour afficher le contenu des applications
        textApplication = new JEditorPane();
        textApplication.setContentType("text/html");          // Spécifie le type HTML
        textApplication.setText(htmlApplication);             // Injecte le contenu HTML
        textApplication.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textApplication.setEditable(false);                   // Rend le texte non modifiable
        textApplication.setBackground(White);                  // Fond blanc pour la zone de texte
        textApplication.setForeground(Noir);                   // Texte en noir
        textApplication.setOpaque(false);                      // Rend le composant transparent pour ne laisser apparaître que le texte
        textApplication.setBorder(null);                       // Pas de bordure
        textApplication.setFocusable(false);                   // Désactive le focus clavier

        // Gère les clics sur les liens dans le contenu HTML
        textApplication.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Lorsque le lien "action:termine" est cliqué, on passe à l'onglet suivant (index 4)
                    onglets.setSelectedIndex(4);
                }
            }
        });

        textApplication.setCaretPosition(0);                    // Place le curseur au début du texte

        // Rend la zone de texte défilable
        scrollApplications = new JScrollPane(textApplication);
        scrollApplications.getVerticalScrollBar().setUnitIncrement(8); // Vitesse de défilement personnalisée

        scrollPanelColor(scrollApplications, false);             // Applique la couleur personnalisée au scroll

        panelApplication.add(scrollApplications, BorderLayout.CENTER); // Ajoute la zone défilable au centre du panneau

        return panelApplication;                                  // Retourne le panneau complet
    }


    private JPanel createOngletVocabulaire(TutoPage LaPageTuto, Main LaPageMain) {
        // Panneau principal pour l'onglet "Vocabulaire" avec un BorderLayout
        panelVocabulaire = new JPanel(new BorderLayout());
        panelVocabulaire.setBackground(White);                                // Fond blanc
        panelVocabulaire.setBorder(BorderFactory.createLineBorder(White, 2)); // Bordure blanche de 2px (inutile puisque remplacée juste après)
        panelVocabulaire.setBorder(null);                                      // Supprime toute bordure

        // Zone de texte HTML pour afficher le vocabulaire
        textVocabulaire = new JEditorPane();
        textVocabulaire.setContentType("text/html");                           // Type HTML pour le contenu
        textVocabulaire.setText(htmlVocabulaire);                              // Injecte le contenu HTML
        textVocabulaire.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        textVocabulaire.setEditable(false);                                    // Rend le texte non modifiable
        textVocabulaire.setBackground(White);                                   // Fond blanc pour la zone de texte
        textVocabulaire.setForeground(Noir);                                    // Texte en noir
        textVocabulaire.setOpaque(false);                                       // Rend la zone transparente (on voit seulement le texte)
        textVocabulaire.setBorder(null);                                        // Pas de bordure
        textVocabulaire.setFocusable(false);                                    // Désactive le focus clavier
        textVocabulaire.setCaretPosition(0);                                     // Place le curseur au début du texte

        // Gère les clics sur les liens dans le contenu HTML
        textVocabulaire.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String desc = e.getDescription();
                if ("action:termine".equals(desc)) {
                    // Si on clique sur le lien "action:termine", affiche la page Tuto
                    LaPageMain.afficherPage(LaPageTuto);
                }
            }
        });

        // Rend la zone de texte défilable
        scrollVocabulaire = new JScrollPane(textVocabulaire);
        scrollVocabulaire.getVerticalScrollBar().setUnitIncrement(8);           // Vitesse de défilement personnalisée

        scrollPanelColor(scrollVocabulaire, false);                             // Applique la couleur personnalisée au scroll

        // Ajoute la zone défilable au centre du panneau
        panelVocabulaire.add(scrollVocabulaire, BorderLayout.CENTER);

        return panelVocabulaire;                                                 // Retourne le panneau complet
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        // Récupère l'objet Image à partir de l'ImageIcon existant
        Image img = icon.getImage();

        // Crée une nouvelle image redimensionnée avec l'algorithme de lissage
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Retourne une nouvelle ImageIcon basée sur l'image redimensionnée
        return new ImageIcon(resizedImage);
    }

    public JTabbedPane getOnglets() {
        return onglets;
    }

    // Mis à jour : méthode modeNuitFusion pour inclure le thème sombre sur l'onglet Applications
    public void modeNuitFusion(boolean boolModeNuit) {
        // Met à jour le thème global des onglets selon le mode nuit/jour
        if (boolModeNuit) {
            onglets.setBackground(InvertWhite);
            UIManager.put("TabbedPane.selected", InvertWhite);        // Fond de l’onglet sélectionné
            UIManager.put("TabbedPane.contentAreaColor", InvertWhite); // Fond de la zone de contenu des onglets
            UIManager.put("TabbedPane.focus", InvertWhite);            // Couleur des bordures au focus
        } else {
            onglets.setBackground(White);
            UIManager.put("TabbedPane.selected", White);
            UIManager.put("TabbedPane.contentAreaColor", White);
            UIManager.put("TabbedPane.focus", White);
        }

        // Met à jour le panneau "Outils" en recréant son contenu avec le bon thème
        panelOutils.setBackground(boolModeNuit ? InvertWhite : White);
        int idxOutils = onglets.indexOfTab("Outils");
        if (idxOutils != -1) {
            JScrollPane newOutils = createOngletOutils(getWidth(), getHeight(), boolModeNuit);
            onglets.setComponentAt(idxOutils, newOutils);      // Remplace l’onglet "Outils" par le nouveau contenu
            scrollPanelColor(scrollOutils, boolModeNuit);      // Met à jour la couleur du scroll
            scrollOutils = newOutils;                           // Référence au nouveau JScrollPane
        }

        // Définit les couleurs de texte et de fond pour les contenus HTML
        String textColor = boolModeNuit ? "#ffffff" : "#000000";
        String bgColor   = boolModeNuit ? "#212121" : "#ffffff";

        // --- Onglet Installer ---
        // Met à jour le HTML avec les nouvelles couleurs
        textInstaller.setText(String.format(htmlInstaller, textColor, bgColor));
        textInstaller.setCaretPosition(0);                       // Place le curseur au début
        scrollPanelColor(scrollInstaller, boolModeNuit);         // Met à jour la couleur du scroll
        scrollInstaller.getViewport().setOpaque(true);
        scrollInstaller.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White    // Fond du viewport selon le mode
        );
        scrollInstaller.revalidate();                            // Réajuste la mise en page
        scrollInstaller.repaint();                               // Rafraîchit l’affichage

        // --- Onglet Découverte ---
        text.setText(String.format(htmlDecouverte, textColor, bgColor));
        text.setCaretPosition(0);
        scrollPanelColor(scrollDecouverte, boolModeNuit);
        scrollDecouverte.getViewport().setOpaque(true);
        scrollDecouverte.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White
        );
        scrollDecouverte.revalidate();
        scrollDecouverte.repaint();

        // --- Onglet Applications ---
        textApplication.setText(String.format(htmlApplication, textColor, bgColor));
        textApplication.setCaretPosition(0);
        scrollPanelColor(scrollApplications, boolModeNuit);
        scrollApplications.getViewport().setOpaque(true);
        scrollApplications.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White
        );
        scrollApplications.revalidate();
        scrollApplications.repaint();

        // --- Onglet Vocabulaire ---
        textVocabulaire.setText(String.format(htmlVocabulaire, textColor, bgColor));
        textVocabulaire.setCaretPosition(0);
        scrollPanelColor(scrollVocabulaire, boolModeNuit);
        scrollVocabulaire.getViewport().setOpaque(true);
        scrollVocabulaire.getViewport().setBackground(
                boolModeNuit ? new Color(0x21, 0x21, 0x21) : White
        );
        scrollVocabulaire.revalidate();
        scrollVocabulaire.repaint();
    }

    public void scrollPanelColor(JScrollPane scrollPanel, boolean boolModeNuit) {
        // Personnalise l'apparence de la scrollbar verticale
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                // Définit les couleurs de la piste et du curseur selon le mode
                if (boolModeNuit) {
                    trackColor = InvertWhite; // Fond noir
                    thumbColor = White;       // Curseur blanc
                } else {
                    trackColor = White;       // Fond blanc
                    thumbColor = InvertWhite; // Curseur noir
                }
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                // Dessine la piste (fond) de la scrollbar dans la couleur définie
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
                // Dessine le "thumb" (le curseur) arrondi et anti-aliasé
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Remplit le rectangle arrondi avec la couleur du thumb
                g2.setColor(thumbColor);
                int arc = 10; // Rayon d'arrondi
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
                // Supprime le bouton de défilement vers le haut en le rendant invisible
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                // Supprime le bouton de défilement vers le bas en le rendant invisible
                return createZeroButton();
            }

            private JButton createZeroButton() {
                // Crée un bouton de taille zéro et invisible
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                button.setVisible(false);
                return button;
            }
        });

        // Rends le JScrollPane transparent pour ne pas afficher de fond indésirable
        scrollPanel.setOpaque(false);
        scrollPanel.getViewport().setOpaque(false);

        // Supprime toutes les bordures
        scrollPanel.setBorder(null);
        scrollPanel.getViewport().setBorder(null);
        scrollPanel.getVerticalScrollBar().setBorder(null);

        // Ajuste la largeur du scrollbar à 5 pixels seulement
        scrollPanel.getVerticalScrollBar().setPreferredSize(
                new Dimension(5, Integer.MAX_VALUE)
        );
    }
}

