package src;// === src.Main.java ===
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Main extends JFrame {
    // Dimensions initiales de la fenêtre de l'application
    private int Largeur = 700;
    private int Hauteur = 500;
    private int largeurFleche;
    private int hauteurFleche;
    private int decalage = 1;
    private int posBtnTuto= 0;
    private int posBtnFusion = 0;

    // Définitions des couleurs personnalisées utilisées dans l'interface
    private Color Bleu = (new Color(10,210,210)), BleuGrisClair = (new Color(50,240,240)), Noir = (new Color(0,0,0)), Rouge = (new Color(255,10, 10)), Blanc = (new Color(255,255,255)), BleuClair = new Color(10,240,240);
    private Color InvertBleu = new Color(58, 77, 160), InvertBleuGrisClair = new Color(75, 97, 193), InvertNoir = new Color(200, 200, 200), SurvolInvertBleuClair = new Color(70, 82, 96), InvertBlanc = new Color(30, 30, 30), InvertBleuClair = new Color(30, 42, 56);


    // Bouton pour basculer entre agrandi/restauré
    private JButton btnAgrandir = new JButton(), btnNuit = new JButton(), btnMenu = new JButton(), btnCacher = new JButton(), btnQuitter = new JButton(), btnFleche = new JButton(), boutonFusion = new JButton(), btnAllerInstallerFusion = new JButton(),btnAllerDecouverteFusion = new JButton(), btnAllerOutilsFusion = new JButton(), btnAllerApplicationFusion = new JButton(), btnAllerVocabulaireFusion = new JButton(), boutonTuto = new JButton(), btnCube = new JButton(), btnDes = new JButton(), btnVaisseau = new JButton(), boutonCura = new JButton(), btnAllerInstallerCura = new JButton(), btnAllerDecouverteCura = new JButton(), btnAllerOutilsCura = new JButton(), btnAllerApplicationCura = new JButton(), btnAllerVocabulaireCura = new JButton();
    private JPanel ligneCote, ligneBarreSup, barreSuperieure, barreBouton, ligneFlecheActive; // Panneaux décoratifs
    // Barre supérieure personnalisée et pages de l'application
    private boolean flecheActive = false, btnFusion = false, btnTuto = false, btnCura = false, btnModeNuit = false;
    private AccueilPage Acceuil;
    private FusionPage Fusion;
    private CuraPage Cura;
    private TutoPage Tuto;
    private PagePanel Temporaire;
    JTabbedPane ongletsFusion, ongletsCura, ongletsTuto;


    // Définition des polices utilisées
    Font TitreBoutonGauche = new Font("Arial", Font.PLAIN, 12);   // Police standard pour le texte
    Font SousTitreBoutonGauche = new Font("Arial", Font.PLAIN, 8);   // Police standard pour le texte

    public Main() {
        // Appelle le constructeur de la classe parente JFrame avec le titre spécifié
        super("App Impression 3D");

        // Supprime la décoration native du système d'exploitation (barre de titre, bordures)
        setUndecorated(true);

        // Rend la fenêtre visible immédiatement (avant d'ajouter tous les composants)
        setVisible(true);

        // Charge l'icône de la fenêtre depuis les ressources et l'applique
        Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo/logo.png"));
        setIconImage(logo);

        // Spécifie l'action de fermeture : termine l'application quand la croix est cliquée
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Définit la taille initiale de la fenêtre
        setSize(Largeur, Hauteur);

        // Centre la fenêtre à l'écran
        setLocationRelativeTo(null);

        // Désactive tout gestionnaire de layout automatique pour un positionnement manuel
        setLayout(null);

        // ========= INITIALISATION DES PAGES =========

        // Page d'accueil avec trois actions pour afficher les différentes pages
        Acceuil = new AccueilPage(
                "Application d'impression 3D",
                () -> afficherPage(Fusion),
                () -> afficherPage(Tuto),
                () -> afficherPage(Cura)
        );

        // Création des pages Cura, Tuto et Fusion en passant les dimensions et références nécessaires
        Cura   = new CuraPage(Largeur, Hauteur);
        Tuto   = new TutoPage(Largeur, Hauteur, Cura, this);
        Fusion = new FusionPage(Largeur, Hauteur, Tuto, this);

        // ========= PANEAU LATÉRAL (bordure noire) =========

        ligneCote = new JPanel();
        ligneCote.setBackground(Noir);
        ligneCote.setBounds(0, 0, Largeur, Hauteur);  // Occupe toute la fenêtre en arrière-plan
        ligneCote.setLayout(null);

        // Panel principal des boutons (fond bleu clair)
        barreBouton = new JPanel();
        barreBouton.setBackground(BleuClair);
        barreBouton.setBounds(1, 1, Largeur - 2, Hauteur - 2);
        barreBouton.setLayout(null);
        barreBouton.setOpaque(true);

        // ========= BOUTON FUSION (menu déroulant) =========

        boutonFusion.setText("Fusion \u25BC");          // Texte + flèche bas
        boutonFusion.setBackground(Bleu);
        boutonFusion.setBounds(2, 32, 94, 20);         // Position et taille
        boutonFusion.setFont(TitreBoutonGauche);       // Police
        boutonFusion.setFocusPainted(false);           // Désactive le contour focus
        boutonFusion.addActionListener(e -> InvertBtnFusion()); // Affiche/masque sous-menus
        boutonFusion.setVisible(false);                // Caché par défaut

        // Récupération de l'objet JTabbedPane de la page Fusion pour sélectionner les onglets
        ongletsFusion = Fusion.getOnglets();

        // Bouton pour accéder à l'onglet "Installation de Fusion 360"
        btnAllerInstallerFusion.setText("<html><center>Installation de fusion 360</center></html>");
        btnAllerInstallerFusion.setBackground(Bleu);
        btnAllerInstallerFusion.setBounds(10, 54, 86, 30);
        btnAllerInstallerFusion.setFont(SousTitreBoutonGauche);
        btnAllerInstallerFusion.setFocusPainted(false);
        btnAllerInstallerFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerInstallerFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(0));
        btnAllerInstallerFusion.setVisible(false);

        // Bouton pour accéder à l'onglet "Découverte de Fusion 360"
        ongletsFusion = Fusion.getOnglets();  // Le getter est appelé à chaque initialisation pour s’assurer de la référence
        btnAllerDecouverteFusion.setText("<html><center>Découverte de fusion 360</center></html>");
        btnAllerDecouverteFusion.setBackground(Bleu);
        btnAllerDecouverteFusion.setBounds(10, 86, 86, 30);
        btnAllerDecouverteFusion.setFont(SousTitreBoutonGauche);
        btnAllerDecouverteFusion.setFocusPainted(false);
        btnAllerDecouverteFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerDecouverteFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(1));
        btnAllerDecouverteFusion.setVisible(false);

        // Bouton pour accéder à l'onglet "Outils de modélisation" de Fusion 360
        ongletsFusion = Fusion.getOnglets();
        btnAllerOutilsFusion.setText("<html><center>Outils de<br>modélisation</center></html>");
        btnAllerOutilsFusion.setBackground(Bleu);
        btnAllerOutilsFusion.setBounds(10, 118, 86, 30);
        btnAllerOutilsFusion.setFont(SousTitreBoutonGauche);
        btnAllerOutilsFusion.setFocusPainted(false);
        btnAllerOutilsFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerOutilsFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(2));
        btnAllerOutilsFusion.setVisible(false);

        // Bouton pour accéder à l'onglet "Domaine d'application" de Fusion 360
        ongletsFusion = Fusion.getOnglets();
        btnAllerApplicationFusion.setText("<html><center>Domaine<br>d'application</center></html>");
        btnAllerApplicationFusion.setBackground(Bleu);
        btnAllerApplicationFusion.setBounds(10, 150, 86, 30);
        btnAllerApplicationFusion.setFont(SousTitreBoutonGauche);
        btnAllerApplicationFusion.setFocusPainted(false);
        btnAllerApplicationFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerApplicationFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(3));
        btnAllerApplicationFusion.setVisible(false);

        // Bouton pour accéder à l'onglet "Vocabulaire utile" de Fusion 360
        ongletsFusion = Fusion.getOnglets();
        btnAllerVocabulaireFusion.setText("<html><center>Vocabulaire<br>utile</center></html>");
        btnAllerVocabulaireFusion.setBackground(Bleu);
        btnAllerVocabulaireFusion.setBounds(10, 182, 86, 30);
        btnAllerVocabulaireFusion.setFont(SousTitreBoutonGauche);
        btnAllerVocabulaireFusion.setFocusPainted(false);
        btnAllerVocabulaireFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerVocabulaireFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(4));
        btnAllerVocabulaireFusion.setVisible(false);

        // ========= BOUTON TUTO (menu déroulant TutoPage) =========

        boutonTuto.setText("Tuto \u25BC");
        boutonTuto.setBackground(Bleu);
        boutonTuto.setBounds(2, 54, 94, 20);
        boutonTuto.setFont(TitreBoutonGauche);
        boutonTuto.setFocusPainted(false);
        boutonTuto.addActionListener(e -> InvertBtnTuto()); // Montre/masque les sous-menus Tuto
        boutonTuto.setVisible(false);

        // Bouton "Cube" dans le menu Tuto
        ongletsTuto = Tuto.getOnglets();
        btnCube.setText("<html><center>Cube</center></html>");
        btnCube.setBackground(Bleu);
        btnCube.setBounds(10, 76, 86, 30);
        btnCube.setFont(SousTitreBoutonGauche);
        btnCube.setFocusPainted(false);
        btnCube.addActionListener(e -> afficherPage(Tuto));
        btnCube.addActionListener(e -> ongletsTuto.setSelectedIndex(0));
        btnCube.setVisible(true);

        // Bouton "Des" dans le menu Tuto
        ongletsTuto = Tuto.getOnglets();
        btnDes.setText("<html><center>Des</center></html>");
        btnDes.setBackground(Bleu);
        btnDes.setBounds(10, 108, 86, 30);
        btnDes.setFont(SousTitreBoutonGauche);
        btnDes.setFocusPainted(false);
        btnDes.addActionListener(e -> afficherPage(Tuto));
        btnDes.addActionListener(e -> ongletsTuto.setSelectedIndex(1));
        btnDes.setVisible(false);

        // Bouton "Vaisseau" dans le menu Tuto
        ongletsTuto = Tuto.getOnglets();
        btnVaisseau.setText("<html><center>Vaisseau</center></html>");
        btnVaisseau.setBackground(Bleu);
        btnVaisseau.setBounds(10, 140, 86, 30);
        btnVaisseau.setFont(SousTitreBoutonGauche);
        btnVaisseau.setFocusPainted(false);
        btnVaisseau.addActionListener(e -> afficherPage(Tuto));
        btnVaisseau.addActionListener(e -> ongletsTuto.setSelectedIndex(2));
        btnVaisseau.setVisible(false);

        // ========= BOUTON CURA (menu déroulant CuraPage) =========

        boutonCura.setText("Cura \u25BC");
        boutonCura.setBackground(Bleu);
        boutonCura.setBounds(2, 76, 94, 20);
        boutonCura.setFont(TitreBoutonGauche);
        boutonCura.setFocusPainted(false);
        boutonCura.addActionListener(e -> InvertBtnCura()); // Montre/masque les sous-menus Cura
        boutonCura.setVisible(false);

        // Bouton "Installation de Cura"
        ongletsCura = Cura.getOnglets();
        btnAllerInstallerCura.setText("<html><center>Installation de Cura</center></html>");
        btnAllerInstallerCura.setBackground(Bleu);
        btnAllerInstallerCura.setBounds(10, 98, 86, 30);
        btnAllerInstallerCura.setFont(SousTitreBoutonGauche);
        btnAllerInstallerCura.setFocusPainted(false);
        btnAllerInstallerCura.addActionListener(e -> afficherPage(Cura));
        btnAllerInstallerCura.addActionListener(e -> ongletsCura.setSelectedIndex(0));
        btnAllerInstallerCura.setVisible(false);

        // Bouton "Découverte de Cura"
        ongletsCura = Cura.getOnglets();
        btnAllerDecouverteCura.setText("<html><center>Découverte de Cura</center></html>");
        btnAllerDecouverteCura.setBackground(Bleu);
        btnAllerDecouverteCura.setBounds(10, 130, 86, 30);
        btnAllerDecouverteCura.setFont(SousTitreBoutonGauche);
        btnAllerDecouverteCura.setFocusPainted(false);
        btnAllerDecouverteCura.addActionListener(e -> afficherPage(Cura));
        btnAllerDecouverteCura.addActionListener(e -> ongletsCura.setSelectedIndex(1));
        btnAllerDecouverteCura.setVisible(false);

        // Bouton "Outils de Cura"
        ongletsCura = Cura.getOnglets();
        btnAllerOutilsCura.setText("<html><center>Outils de Cura</center></html>");
        btnAllerOutilsCura.setBackground(Bleu);
        btnAllerOutilsCura.setBounds(10, 162, 86, 30);
        btnAllerOutilsCura.setFont(SousTitreBoutonGauche);
        btnAllerOutilsCura.setFocusPainted(false);
        btnAllerOutilsCura.addActionListener(e -> afficherPage(Cura));
        btnAllerOutilsCura.addActionListener(e -> ongletsCura.setSelectedIndex(2));
        btnAllerOutilsCura.setVisible(false);

        // Bouton "Domaine d'application"
        ongletsCura = Cura.getOnglets();
        btnAllerApplicationCura.setText("<html><center>Domaine d'application</center></html>");
        btnAllerApplicationCura.setBackground(Bleu);
        btnAllerApplicationCura.setBounds(10, 194, 86, 30);
        btnAllerApplicationCura.setFont(SousTitreBoutonGauche);
        btnAllerApplicationCura.setFocusPainted(false);
        btnAllerApplicationCura.addActionListener(e -> afficherPage(Cura));
        btnAllerApplicationCura.addActionListener(e -> ongletsCura.setSelectedIndex(3));
        btnAllerApplicationCura.setVisible(false);

        // Bouton "Vocabulaire utile"
        ongletsCura = Cura.getOnglets();
        btnAllerVocabulaireCura.setText("<html><center>Vocabulaire<br>utile</center></html>");
        btnAllerVocabulaireCura.setBackground(Bleu);
        btnAllerVocabulaireCura.setBounds(10, 226, 86, 30);
        btnAllerVocabulaireCura.setFont(SousTitreBoutonGauche);
        btnAllerVocabulaireCura.setFocusPainted(false);
        btnAllerVocabulaireCura.addActionListener(e -> afficherPage(Cura));
        btnAllerVocabulaireCura.addActionListener(e -> ongletsCura.setSelectedIndex(4));
        btnAllerVocabulaireCura.setVisible(false);

        // Ajoute les boutons Fusion à la barre de boutons
        barreBouton.add(boutonFusion);
        barreBouton.add(btnAllerInstallerFusion);
        barreBouton.add(btnAllerDecouverteFusion);
        barreBouton.add(btnAllerOutilsFusion);
        barreBouton.add(btnAllerApplicationFusion);
        barreBouton.add(btnAllerVocabulaireFusion);

        // Ajoute les boutons Tuto à la barre de boutons
        barreBouton.add(boutonTuto);
        barreBouton.add(btnCube);
        barreBouton.add(btnDes);
        barreBouton.add(btnVaisseau);

        // Ajoute les boutons Cura à la barre de boutons
        barreBouton.add(boutonCura);
        barreBouton.add(btnAllerInstallerCura);
        barreBouton.add(btnAllerDecouverteCura);
        barreBouton.add(btnAllerOutilsCura);
        barreBouton.add(btnAllerApplicationCura);
        barreBouton.add(btnAllerVocabulaireCura);

        // ========= LIGNE DE NAVIGATION (flèche active) =========

        ligneFlecheActive = new JPanel();
        ligneFlecheActive.setBackground(Noir);
        ligneFlecheActive.setBounds(98, 1, 3, Hauteur); // Position à droite du panneau latéral
        ligneFlecheActive.setLayout(null);
        ligneFlecheActive.setOpaque(false);             // Transparent à l’exception du fond noir

        // ========= BARRE DE CONTRÔLE PERSONNALISÉE (haut de la fenêtre) =========

        barreSuperieure = creerBarrePersonnalisee();
        add(barreSuperieure);

        // Bouton flèche pour revenir à la page précédente
        Temporaire = Acceuil; // Page précédente initiale : accueil
        btnFleche = creerBouton(
                btnFleche,
                Bleu, BleuGrisClair, Blanc,
                "->", e -> boutonFlecheClick(),
                18, "Segoe UI Emoji"
        );
        btnFleche.addActionListener(e -> afficherPage(Temporaire));
        btnFleche.setBounds(1, 31, 30, 20);  // Position initiale
        btnFleche.setContentAreaFilled(true);
        btnFleche.setForeground(Noir);

        // Ajout des composants à la fenêtre (JLayeredPane pour bouton flèche)
        getLayeredPane().add(btnFleche, JLayeredPane.PALETTE_LAYER);
        add(ligneFlecheActive);
        add(Acceuil);
        add(Fusion);
        add(Tuto);
        add(Cura);
        add(barreBouton);
        add(ligneCote);

        // ========= INVOKE LATER POUR INITIALISER LES BOUNDS DES PAGES =========

        SwingUtilities.invokeLater(() -> {
            // Positionne chaque page (stackable) sous la barre de contrôle
            Acceuil.setBounds(1, 30, Largeur - 2, Hauteur - 31);
            Fusion.setBounds(1, 30, Largeur - 2, Hauteur - 31);
            Cura.setBounds(1, 30, Largeur - 2, Hauteur - 31);
            Tuto.setBounds(1, 30, Largeur - 2, Hauteur - 31);

            // Applique le thème (mode nuit si activé)
            if (btnModeNuit) {
                Acceuil.setBackground(InvertBlanc);
                Fusion.setBackground(InvertBlanc);
                Cura.setBackground(InvertBlanc);
                Tuto.setBackground(InvertBlanc);
            } else {
                Acceuil.setBackground(Blanc);
                Fusion.setBackground(Blanc);
                Cura.setBackground(Blanc);
                Tuto.setBackground(Blanc);
            }

            // Ajuste la position des composants internes
            repositionner();
        });

        // ========= GESTION DU REDIMENSIONNEMENT DE LA FENÊTRE =========

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Met à jour les dimensions globales
                Largeur = getWidth();
                Hauteur = getHeight();

                // Ajuste la barre supérieure et sa ligne inférieure
                barreSuperieure.setBounds(1, 1, Largeur - 2, 30);
                ligneBarreSup.setBounds(0, 28, Largeur, 2);

                // Repositionne les boutons de la barre supérieure
                int boutonLargeur = 50;
                int boutonHauteur = 28;
                barreSuperieure.getComponent(0).setBounds(1, 0, boutonLargeur, boutonHauteur);          // Menu
                barreSuperieure.getComponent(1).setBounds(boutonLargeur + 1, 0, boutonLargeur, boutonHauteur); // Mode nuit
                barreSuperieure.getComponent(2).setBounds(Largeur - 51, 0, boutonLargeur, boutonHauteur); // Quitter
                barreSuperieure.getComponent(3).setBounds(Largeur - 101, 0, boutonLargeur, boutonHauteur); // Agrandir
                barreSuperieure.getComponent(4).setBounds(Largeur - 151, 0, boutonLargeur, boutonHauteur); // Réduire

                // Ajuste les bound des pages pour remplir l’espace disponible
                Acceuil.setBounds(1, 30, Largeur - 2, Hauteur - 31);
                Fusion.setBounds(1, 30, Largeur - 2, Hauteur - 31);
                Cura.setBounds(1, 30, Largeur - 2, Hauteur - 31);
                Tuto.setBounds(1, 30, Largeur - 2, Hauteur - 31);

                // Met à jour l’état du bouton flèche selon la visibilité
                Tuto.flecheActiveOuNon(flecheActive);
                if (flecheActive) {
                    btnFleche.setBounds(30, Hauteur - 21, 30, 20);
                    btnFleche.setText("<-");
                } else {
                    btnFleche.setBounds(1, 31, 30, 20);
                    btnFleche.setText("->");
                }

                // Ajuste la position des composants internes si besoin
                repositionner();

                // Met à jour la bordure latérale pour couvrir toute la zone
                ligneCote.setBounds(0, 0, Largeur, Hauteur);
            }
        });

        // Applique la position initiale des composants des pages
        repositionner();

        // Définit le fond général de la fenêtre (gris ou blanc par défaut)
        getContentPane().setBackground(Blanc);

        // Active les poignées de redimensionnement personnalisées par glisser-déposer
        activerRedimensionnement();

        // S’assure que la fermeture se fait bien (au cas où unsetdecorated avait modifié le comportement)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Inverse l’état d’affichage des sous-menus Fusion et repositionne les composants.
     */
    public void InvertBtnFusion() {
        // Inverse le booléen qui contrôle la visibilité du menu Fusion
        btnFusion = !btnFusion;
        // Repositionne ou ajuste la mise en page en fonction du nouvel état
        repositionner();
    }

    /**
     * Inverse l’état d’affichage des sous-menus Tuto et repositionne les composants.
     */
    public void InvertBtnTuto() {
        // Inverse le booléen qui contrôle la visibilité du menu Tuto
        btnTuto = !btnTuto;
        // Repositionne ou ajuste la mise en page en fonction du nouvel état
        repositionner();
    }

    /**
     * Inverse l’état d’affichage des sous-menus Cura et repositionne les composants.
     */
    public void InvertBtnCura() {
        // Inverse le booléen qui contrôle la visibilité du menu Cura
        btnCura = !btnCura;
        // Repositionne ou ajuste la mise en page en fonction du nouvel état
        repositionner();
    }

    /**
     * Gère le clic sur la flèche de navigation : affiche ou masque la zone latérale
     * et met à jour la taille/position des pages principales.
     */
    public void boutonFlecheClick() {
        // Change l’état de la flèche (active ou non)
        flecheActive = !flecheActive;

        // Définit le décalage horizontal des pages : 1 px si flèche masquée, 100 px si affichée
        decalage = !flecheActive ? 1 : 100;

        // Positionne d’abord toutes les pages selon le décalage standard
        Acceuil.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);
        Fusion.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);
        Tuto.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);
        Cura.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);

        if (flecheActive) {
            // Lorsque la flèche est active (zone latérale visible) :
            // - place le bouton flèche à gauche de la zone latérale
            btnFleche.setBounds(decalage - 30, Hauteur - 21, 28, 20);
            btnFleche.setText("<-"); // Change le symbole en flèche vers la gauche

            // Réduit la largeur des pages pour tenir compte de la zone latérale étendue
            Acceuil.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Fusion.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Tuto.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Cura.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);

            // Rends la ligne de séparation visible (opaque) sous la flèche
            ligneFlecheActive.setOpaque(true);
        } else {
            // Lorsque la flèche n’est pas active (zone latérale masquée) :
            // - replace le bouton flèche en haut à gauche, près de la bordure
            btnFleche.setBounds(1, 31, 30, 20);
            btnFleche.setText("->"); // Change le symbole en flèche vers la droite

            // Cache la ligne de séparation sous la flèche
            ligneFlecheActive.setOpaque(false);
        }

        // Applique à nouveau le repositionnement général pour ajuster tous les composants
        repositionner();
    }


    private JPanel creerBarrePersonnalisee() {
        // Création du panneau principal de la barre supérieure
        barreSuperieure = new JPanel();
        barreSuperieure.setLayout(null);                         // Layout nul pour positionnement manuel
        barreSuperieure.setBackground(Bleu);                      // Fond bleu
        barreSuperieure.setBounds(1, 1, Largeur - 2, 29);         // Position et taille initiale
        barreSuperieure.setOpaque(true);                          // Rend le panneau opaque

        // Ligne noire fine sous la barre pour séparer visuellement
        ligneBarreSup = new JPanel();
        ligneBarreSup.setBackground(Noir);                        // Couleur noire
        ligneBarreSup.setBounds(0, 28, Largeur, 2);               // Position : juste sous la barre, hauteur 2px
        ligneBarreSup.setLayout(null);                            // Pas de layout pour cet élément

        // --------- BOUTON ACCUEIL (icône maison) ---------
        btnMenu = creerBouton(
                btnMenu,
                Bleu, BleuGrisClair, Blanc,                           // Couleurs : fond, hover, texte
                "🏠",                                                  // Icône maison (Unicode)
                e -> afficherPage(Acceuil),                            // Action : afficher la page d'accueil
                18, "Segoe UI Emoji"                                   // Taille et police pour l’emoji
        );
        btnMenu.setBounds(0, 0, 40, 30);                          // Position en haut à gauche, taille 40×30
        barreSuperieure.add(btnMenu);                             // Ajout à la barre supérieure

        // --------- BOUTON MODE NUIT (icône lune) ---------
        // Quand on clique, on bascule le mode nuit pour chaque page
        btnNuit.addActionListener(e -> Acceuil.modeNuitAcceuil(btnModeNuit));
        btnNuit.addActionListener(e -> Fusion.modeNuitFusion(btnModeNuit));
        btnNuit.addActionListener(e -> Tuto.modeNuitTuto(btnModeNuit));
        btnNuit.addActionListener(e -> Cura.modeNuitCura(btnModeNuit));
        btnNuit = creerBouton(
                btnNuit,
                Bleu, BleuGrisClair, Blanc,                           // Couleurs : fond, hover, texte
                "\uD83C\uDF19",                                        // Icône lune (Unicode)
                e -> InvertModeNuit(),                                 // Action pour basculer le booléen mode nuit
                18, "Segoe UI Emoji"                                   // Taille et police pour l’emoji
        );
        btnNuit.setBounds(40, 0, 40, 30);                          // Position à côté du bouton Accueil
        barreSuperieure.add(btnNuit);                              // Ajout à la barre supérieure

        // --------- BOUTON QUITTER (icône croix) ---------
        btnQuitter = creerBouton(
                btnQuitter,
                Bleu, Rouge, Blanc,                                    // Couleurs : fond, hover rouge, texte blanc
                "\uE711",                                              // Icône croix (Segoe MDL2 Assets)
                e -> System.exit(0),                                   // Ferme l’application immédiatement
                18, "Segoe MDL2 Assets"                                // Taille et police pour l’icône
        );
        btnQuitter.setBounds(Largeur - 50, 0, 40, 29);              // Position en haut à droite
        barreSuperieure.add(btnQuitter);                            // Ajout à la barre supérieure

        // --------- BOUTON AGRANDIR / RESTAURER (icône maxi) ---------
        btnAgrandir = creerBouton(
                btnAgrandir,
                Bleu, BleuGrisClair, Blanc,                             // Couleurs : fond, hover, texte
                "\uE922",                                               // Icône agrandir/restaurer (Segoe MDL2 Assets)
                e -> toggleMaximiser(),                                 // Action pour maximiser/restaurer la fenêtre
                12, "Segoe MDL2 Assets"                                 // Taille plus petite pour l’icône
        );
        btnAgrandir.setBounds(Largeur - 100, 0, 40, 29);            // Position à 100 px du bord droit
        barreSuperieure.add(btnAgrandir);                           // Ajout à la barre supérieure

        // --------- BOUTON RÉDUIRE (icône minimise) ---------
        btnCacher = creerBouton(
                btnCacher,
                Bleu, BleuGrisClair, Blanc,                             // Couleurs : fond, hover, texte
                "\uE921",                                               // Icône réduire (Segoe MDL2 Assets)
                e -> setState(JFrame.ICONIFIED),                        // Met la fenêtre en icône (minimise)
                12, "Segoe MDL2 Assets"                                 // Taille et police pour l’icône
        );
        btnCacher.setBounds(Largeur - 150, 0, 40, 29);              // Position à 150 px du bord droit
        barreSuperieure.add(btnCacher);                             // Ajout à la barre supérieure

        // --------- GESTION DU DÉPLACEMENT DE LA FENÊTRE PAR DRAG ---------
        final Point[] initialClick = { null };
        // Quand on appuie sur la barre, on retient le point de clic initial
        barreSuperieure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                initialClick[0] = e.getPoint();
            }
        });
        // Quand on déplace la souris en maintenant le clic, on recalcule la position de la fenêtre
        barreSuperieure.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                // Position actuelle de la fenêtre
                int thisX = getLocation().x;
                int thisY = getLocation().y;
                // Distance parcourue depuis le clic initial
                int xMoved = e.getX() - initialClick[0].x;
                int yMoved = e.getY() - initialClick[0].y;
                // Déplace la fenêtre en fonction du mouvement
                setLocation(thisX + xMoved, thisY + yMoved);
            }
        });

        // Ajoute la ligne de séparation noire sous la barre
        barreSuperieure.add(ligneBarreSup);

        return barreSuperieure;
    }

    /**
     * Inverse l’état du mode nuit pour toute l’application et repositionne les composants.
     */
    public void InvertModeNuit() {
        // Basculer le booléen qui indique si le mode nuit est activé
        btnModeNuit = !btnModeNuit;
        // Repositionne et redessine les composants pour appliquer le nouveau thème
        repositionner();
    }

    /**
     * Indique si la fenêtre est actuellement en mode maximisé (plein écran).
     */
    private boolean estMaximisee = false;

    /**
     * Bascule entre l’état maximisé et l’état restauré de la fenêtre.
     */
    private void toggleMaximiser() {
        if (estMaximisee) {
            // Si la fenêtre est maximisée, la restaurer à une taille fixe (700×500) centrée
            setSize(700, 500);
            setLocationRelativeTo(null);
            estMaximisee = false;
            // Remettre l’icône "agrandir" sur le bouton
            btnAgrandir.setText("\uE922"); // Unicode pour l’icône agrandir
            // Autoriser à nouveau le redimensionnement manuel
            setResizable(true);
        } else {
            // Si la fenêtre n’est pas maximisée, la passer en plein écran
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            estMaximisee = true;
            // Changer l’icône du bouton en "restaurer" pour indiquer la possibilité de revenir
            btnAgrandir.setText("\uE923"); // Unicode pour l’icône restaurer
            // Désactiver le redimensionnement manuel en mode plein écran
            setResizable(false);
            // Réappliquer les nouvelles dimensions (nécessaire pour certains OS)
            setBounds(getX(), getY(), getWidth(), getHeight());
        }
    }

    /**
     * Réagit au redimensionnement ou aux changements d'état pour repositionner
     * et redessiner tous les composants de l'application.
     */
    private void repositionner() {
        // Si la flèche de navigation est active (zone latérale déployée)
        if (flecheActive) {
            // --------- GESTION DU MENU FUSION ---------
            if (btnFusion) {
                // Menu Fusion ouvert : modifier le texte de la flèche et afficher les sous-boutons
                boutonFusion.setText("Fusion \u25B2");  // Flèche vers le haut pour indiquer "réduire"
                posBtnFusion = 160;                     // Espace vertical alloué aux sous-boutons Fusion
                btnAllerInstallerFusion.setVisible(true);
                btnAllerDecouverteFusion.setVisible(true);
                btnAllerOutilsFusion.setVisible(true);
                btnAllerApplicationFusion.setVisible(true);
                btnAllerVocabulaireFusion.setVisible(true);
            } else {
                // Menu Fusion fermé : flèche vers le bas et masquer les sous-boutons
                boutonFusion.setText("Fusion \u25BC");  // Flèche vers le bas pour indiquer "déployer"
                posBtnFusion = 0;
                btnAllerInstallerFusion.setVisible(false);
                btnAllerDecouverteFusion.setVisible(false);
                btnAllerOutilsFusion.setVisible(false);
                btnAllerApplicationFusion.setVisible(false);
                btnAllerVocabulaireFusion.setVisible(false);
            }

            // --------- GESTION DU MENU TUTO ---------
            if (btnTuto) {
                // Menu Tuto ouvert : modifier le texte de la flèche et afficher les sous-boutons
                boutonTuto.setText("Tuto \u25B2");  // Flèche vers le haut
                posBtnTuto = 96;                   // Espace vertical alloué aux sous-boutons Tuto
                btnCube.setVisible(true);
                btnDes.setVisible(true);
                btnVaisseau.setVisible(true);
            } else {
                // Menu Tuto fermé : flèche vers le bas et masquer les sous-boutons
                boutonTuto.setText("Tuto \u25BC");  // Flèche vers le bas
                posBtnTuto = 0;
                btnCube.setVisible(false);
                btnDes.setVisible(false);
                btnVaisseau.setVisible(false);
            }

            // --------- GESTION DU MENU CURA ---------
            if (btnCura) {
                // Menu Cura ouvert : modifier le texte de la flèche et afficher les sous-boutons
                boutonCura.setText("Cura \u25B2");  // Flèche vers le haut
                btnAllerInstallerCura.setVisible(true);
                btnAllerDecouverteCura.setVisible(true);
                btnAllerOutilsCura.setVisible(true);
                btnAllerApplicationCura.setVisible(true);
                btnAllerVocabulaireCura.setVisible(true);
            } else {
                // Menu Cura fermé : flèche vers le bas et masquer les sous-boutons
                boutonCura.setText("Cura \u25BC");  // Flèche vers le bas
                btnAllerInstallerCura.setVisible(false);
                btnAllerDecouverteCura.setVisible(false);
                btnAllerOutilsCura.setVisible(false);
                btnAllerApplicationCura.setVisible(false);
                btnAllerVocabulaireCura.setVisible(false);
            }

            // --------- THÈME : MODE NUIT OU JOUR ---------
            if (btnModeNuit) {
                // Mode nuit : appliquer les couleurs inversées aux pages principales
                Acceuil.setBackground(InvertBlanc);
                Fusion.setBackground(InvertBlanc);
                Tuto.setBackground(InvertBlanc);
                Cura.setBackground(InvertBlanc);
            } else {
                // Mode jour : rétablir le fond blanc
                Acceuil.setBackground(Blanc);
                Fusion.setBackground(Blanc);
                Tuto.setBackground(Blanc);
                Cura.setBackground(Blanc);
            }

            // --------- POSITIONNEMENT DU BOUTON FLÈCHE ---------
            // Place le bouton flèche à gauche de la zone latérale
            btnFleche.setBounds(decalage - 30, Hauteur - 21, 28, 20);
            btnFleche.setText("<-");  // Flèche vers la gauche

            // --------- REDÉFINITION DES BOUNDS DES PAGES PRINCIPALES ---------
            // Les pages principales sont réduites en largeur pour tenir compte de la zone latérale
            Acceuil.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Fusion.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Tuto.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Cura.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);

            // Rendre la ligne de séparation sous la flèche opaque (visible)
            ligneFlecheActive.setOpaque(true);

            // Récupère les dimensions globales pour le positionnement ultérieur
            hauteurFleche = Hauteur;
            largeurFleche = Largeur - 100;

            // Ajuste la ligne verticale de la flèche pour couvrir toute la hauteur
            ligneFlecheActive.setBounds(98, 1, 3, Hauteur);

            // --------- AFFICHAGE ET POSITIONNEMENT DES BOUTONS DE MENU ---------
            // Bouton Fusion : toujours visible car flecheActive==true
            boutonFusion.setVisible(true);
            boutonFusion.setBounds(2, 32, 94, 20);

            // Bouton Tuto : décalé verticalement en fonction de posBtnFusion
            boutonTuto.setVisible(true);
            boutonTuto.setBounds(2, 54 + posBtnFusion, 94, 20);

            // Sous-boutons Tuto : placés sous le bouton Tuto selon posBtnFusion
            btnCube.setBounds(10, 76 + posBtnFusion, 86, 30);
            btnDes.setBounds(10, 108 + posBtnFusion, 86, 30);
            btnVaisseau.setBounds(10, 140 + posBtnFusion, 86, 30);

            // Bouton Cura : décalé verticalement en fonction de posBtnFusion + posBtnTuto
            boutonCura.setVisible(true);
            boutonCura.setBounds(2, 76 + posBtnFusion + posBtnTuto, 94, 20);

            // Sous-boutons Cura : placés sous le bouton Cura
            btnAllerInstallerCura.setBounds(10, 98 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerDecouverteCura.setBounds(10, 130 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerOutilsCura.setBounds(10, 162 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerApplicationCura.setBounds(10, 194 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerVocabulaireCura.setBounds(10, 226 + posBtnFusion + posBtnTuto, 86, 30);

            // --------- POSITIONNEMENT DES COMPOSANTS SUR LA PAGE D'ACCUEIL ---------
            // Calcul de la largeur d'un composant pour centrer les boutons
            int monComposant = Acceuil.getComponent(2).getSize().width;

            // Titre : centré horizontalement à 10% de la hauteur
            Acceuil.getComponent(0).setBounds(
                    (largeurFleche / 2 - 325 / 2),
                    hauteurFleche / 100 * 10,
                    325, 100
            );

            // Bouton "Vers Fusion" : à 35% de la hauteur, positionné à 3/16 de la largeur
            Acceuil.getComponent(1).setBounds(
                    (largeurFleche / 16 * 3 - monComposant / 2),
                    hauteurFleche / 100 * 35,
                    150, 100
            );

            // Bouton "Vers Tuto" : centré horizontalement à 35% de la hauteur
            Acceuil.getComponent(2).setBounds(
                    (largeurFleche / 2 - monComposant / 2),
                    hauteurFleche / 100 * 35,
                    150, 100
            );

            // Bouton "Vers Cura" : à 35% de la hauteur, positionné à 13/16 de la largeur
            Acceuil.getComponent(3).setBounds(
                    (largeurFleche / 16 * 13 - monComposant / 2),
                    hauteurFleche / 100 * 35,
                    150, 100
            );

            // --------- REDÉFINITION DES BOUNDS POUR LE JSCROLLPANE DE CHAQUE PAGE ---------
            // Ajuste le JScrollPane de chaque page pour déborder légèrement de la taille visible
            Fusion.getComponent(0).setBounds(-100, -1, largeurFleche + 100, hauteurFleche - 30);
            Tuto.getComponent(0).setBounds(-100, -1, largeurFleche + 100, hauteurFleche - 30);
            Cura.getComponent(0).setBounds(-100, -1, largeurFleche + 100, hauteurFleche - 30);
        }
        // Sinon, si la flèche n'est pas active (zone latérale masquée)
        else {
            // --------- THÈME : MODE NUIT OU JOUR ---------
            if (btnModeNuit) {
                Acceuil.setBackground(InvertBlanc);
                Fusion.setBackground(InvertBlanc);
                Tuto.setBackground(InvertBlanc);
                Cura.setBackground(InvertBlanc);
            } else {
                Acceuil.setBackground(Blanc);
                Fusion.setBackground(Blanc);
                Tuto.setBackground(Blanc);
                Cura.setBackground(Blanc);
            }

            // Cacher tous les boutons de navigation de la zone latérale
            boutonFusion.setVisible(false);
            btnAllerInstallerFusion.setVisible(false);
            btnAllerDecouverteFusion.setVisible(false);
            btnAllerOutilsFusion.setVisible(false);
            btnAllerApplicationFusion.setVisible(false);
            btnAllerVocabulaireFusion.setVisible(false);

            boutonTuto.setVisible(false);
            btnCube.setVisible(false);
            btnDes.setVisible(false);
            btnVaisseau.setVisible(false);

            boutonCura.setVisible(false);
            btnAllerInstallerCura.setVisible(false);
            btnAllerDecouverteCura.setVisible(false);
            btnAllerOutilsCura.setVisible(false);
            btnAllerApplicationCura.setVisible(false);
            btnAllerVocabulaireCura.setVisible(false);

            // --------- POSITIONNEMENT DES COMPOSANTS SUR LA PAGE D'ACCUEIL ---------
            int monComposant = Acceuil.getComponent(2).getSize().width;

            // Bouton flèche : replacé en haut à gauche
            btnFleche.setBounds(1, 31, 30, 20);
            btnFleche.setText("->"); // Flèche vers la droite

            // La ligne de séparation sous la flèche est masquée
            ligneFlecheActive.setOpaque(false);

            // Titre centré horizontalement
            Acceuil.getComponent(0).setBounds(
                    (Largeur / 2 - 325 / 2),
                    Hauteur / 100 * 10,
                    325, 100
            );

            // Bouton "Vers Fusion"
            Acceuil.getComponent(1).setBounds(
                    (Largeur / 16 * 3 - monComposant / 2),
                    Hauteur / 100 * 35,
                    150, 100
            );

            // Bouton "Vers Tuto"
            Acceuil.getComponent(2).setBounds(
                    (Largeur / 2 - monComposant / 2),
                    Hauteur / 100 * 35,
                    150, 100
            );

            // Bouton "Vers Cura"
            Acceuil.getComponent(3).setBounds(
                    (Largeur / 16 * 13 - monComposant / 2),
                    Hauteur / 100 * 35,
                    150, 100
            );

            // --------- REDÉFINITION DES BOUNDS POUR LE JSCROLLPANE DE CHAQUE PAGE ---------
            Fusion.getComponent(0).setBounds(-100, -1, Largeur + 100, Hauteur - 30);
            Tuto.getComponent(0).setBounds(-100, -1, Largeur + 100, Hauteur - 30);
            Cura.getComponent(0).setBounds(-100, -1, Largeur + 100, Hauteur - 30);
        }

        // --------- REPOSITIONNEMENT DU PANNEAU (barreBouton) ---------
        barreBouton.setBounds(1, 1, Largeur - 2, Hauteur - 2);

        // --------- MISE À JOUR DES COULEURS SELON LE MODE (NUIT / JOUR) ---------
        if (btnModeNuit) {
            // Mode nuit : appliquer couleurs inversées pour tous les boutons
            btnNuit.setText("\uD83D\uDD06"); // Changer l'icône vers une lune inversée
            setCouleur(Blanc, btnNuit, InvertBleuClair, SurvolInvertBleuClair);
            setCouleur(Blanc, btnMenu, InvertBleuClair, SurvolInvertBleuClair);
            setCouleur(Blanc, btnAgrandir, InvertBleuClair, SurvolInvertBleuClair);
            setCouleur(Blanc, btnCacher, InvertBleuClair, SurvolInvertBleuClair);
            setCouleur(Blanc, btnQuitter, InvertBleuClair, Rouge);
            setCouleur(Blanc, btnFleche, InvertBleu, InvertBleuGrisClair);

            setCouleur(Blanc, boutonFusion, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerInstallerFusion, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerDecouverteFusion, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerOutilsFusion, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerApplicationFusion, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerVocabulaireFusion, InvertBleu, InvertBleuGrisClair);

            setCouleur(Blanc, boutonTuto, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnCube, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnDes, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnVaisseau, InvertBleu, InvertBleuGrisClair);

            setCouleur(Blanc, boutonCura, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerInstallerCura, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerDecouverteCura, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerOutilsCura, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerApplicationCura, InvertBleu, InvertBleuGrisClair);
            setCouleur(Blanc, btnAllerVocabulaireCura, InvertBleu, InvertBleuGrisClair);

            // Couleurs des panneaux et barres en mode nuit
            ligneCote.setBackground(InvertNoir);
            barreBouton.setBackground(InvertBleuClair);
            ligneFlecheActive.setBackground(InvertNoir);
            barreSuperieure.setBackground(InvertBleuClair);
            ligneBarreSup.setBackground(InvertNoir);

        } else {
            // Mode jour : couleurs standard pour tous les boutons
            btnNuit.setText("\uD83C\uDF19"); // Icône lune classique
            setCouleur(Blanc, btnMenu, Bleu, BleuGrisClair);
            setCouleur(Blanc, btnNuit, Bleu, BleuGrisClair);
            setCouleur(Blanc, btnCacher, Bleu, BleuGrisClair);
            setCouleur(Blanc, btnAgrandir, Bleu, BleuGrisClair);
            setCouleur(Blanc, btnQuitter, Bleu, Rouge);
            setCouleur(Noir, btnFleche, Bleu, BleuGrisClair);

            setCouleur(Noir, boutonFusion, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerInstallerFusion, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerDecouverteFusion, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerOutilsFusion, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerApplicationFusion, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerVocabulaireFusion, Bleu, BleuGrisClair);

            setCouleur(Noir, boutonTuto, Bleu, BleuGrisClair);
            setCouleur(Noir, btnCube, Bleu, BleuGrisClair);
            setCouleur(Noir, btnDes, Bleu, BleuGrisClair);
            setCouleur(Noir, btnVaisseau, Bleu, BleuGrisClair);

            setCouleur(Noir, boutonCura, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerInstallerCura, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerDecouverteCura, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerOutilsCura, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerApplicationCura, Bleu, BleuGrisClair);
            setCouleur(Noir, btnAllerVocabulaireCura, Bleu, BleuGrisClair);

            // Couleurs des panneaux et barres en mode jour
            ligneCote.setBackground(Noir);
            barreBouton.setBackground(BleuClair);
            ligneFlecheActive.setBackground(Noir);
            barreSuperieure.setBackground(Bleu);
            ligneBarreSup.setBackground(Noir);
        }

        // --------- VALIDE ET REDESSINE TOUT LE CONTENU ---------
        revalidate();
        repaint();
    }

    /**
     * Active le redimensionnement via les bords de la fenêtre.
     * Permet de redimensionner la fenêtre en faisant glisser ses bords ou coins.
     */
    private void activerRedimensionnement() {
        final int marge = 5; // Épaisseur de la zone sensible autour des bords pour le redimensionnement

        // Écoute les mouvements de souris pour changer le curseur en fonction de la position
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                if (estMaximisee) return; // Désactive le redimensionnement si la fenêtre est maximisée

                int x = e.getX();
                int y = e.getY();
                int largeur = getWidth();
                int hauteur = getHeight();

                // Change le curseur selon la position de la souris (coins, bords, ou zone centrale)
                if (x <= marge && y <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                } else if (x >= largeur - marge && y <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                } else if (x <= marge && y >= hauteur - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                } else if (x >= largeur - marge && y >= hauteur - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                } else if (x <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                } else if (x >= largeur - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                } else if (y <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else if (y >= hauteur - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (estMaximisee) return; // Pas de redimensionnement si la fenêtre est maximisée

                Cursor curseur = getCursor();
                Rectangle bounds = getBounds(); // Rectangle courant de la fenêtre
                int dx = e.getXOnScreen() - bounds.x; // Déplacement horizontal depuis l’origine
                int dy = e.getYOnScreen() - bounds.y; // Déplacement vertical depuis l’origine

                // Ajuste les dimensions de la fenêtre selon le type de curseur
                switch (curseur.getType()) {
                    case Cursor.E_RESIZE_CURSOR:
                        // Étirer horizontalement vers l’est
                        bounds.width = dx;
                        break;
                    case Cursor.S_RESIZE_CURSOR:
                        // Étirer verticalement vers le sud
                        bounds.height = dy;
                        break;
                    case Cursor.SE_RESIZE_CURSOR:
                        // Étirer dans les deux directions
                        bounds.width = dx;
                        bounds.height = dy;
                        break;
                    case Cursor.W_RESIZE_CURSOR:
                        // Étirer à l’ouest : on déplace le bord gauche
                        int newW = bounds.width - dx;
                        if (newW > 100) { // Largeur minimale de 100 px
                            bounds.x += dx;
                            bounds.width = newW;
                        }
                        break;
                    case Cursor.N_RESIZE_CURSOR:
                        // Étirer vers le nord : on déplace le bord supérieur
                        int newH = bounds.height - dy;
                        if (newH > 100) { // Hauteur minimale de 100 px
                            bounds.y += dy;
                            bounds.height = newH;
                        }
                        break;
                    case Cursor.NW_RESIZE_CURSOR:
                        // Coin nord-ouest : diminue largeur et hauteur simultanément
                        newW = bounds.width - dx;
                        newH = bounds.height - dy;
                        if (newW > 100) {
                            bounds.x += dx;
                            bounds.width = newW;
                        }
                        if (newH > 100) {
                            bounds.y += dy;
                            bounds.height = newH;
                        }
                        break;
                    case Cursor.NE_RESIZE_CURSOR:
                        // Coin nord-est : largeur vers l’est, hauteur vers le nord
                        bounds.width = dx;
                        newH = bounds.height - dy;
                        if (newH > 100) {
                            bounds.y += dy;
                            bounds.height = newH;
                        }
                        break;
                    case Cursor.SW_RESIZE_CURSOR:
                        // Coin sud-ouest : hauteur vers le sud, largeur vers l’ouest
                        bounds.height = dy;
                        newW = bounds.width - dx;
                        if (newW > 100) {
                            bounds.x += dx;
                            bounds.width = newW;
                        }
                        break;
                    default:
                        // Aucun redimensionnement si curseur différent
                        break;
                }

                repositionner();      // Ajuste tous les composants après redimensionnement
                setBounds(bounds);    // Applique les nouvelles dimensions et position
            }
        });

        // Lorsque la souris est relâchée après un drag, on repositionne une dernière fois tous les éléments
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                repositionner();
            }
        });
    }

    /**
     * Affiche la page spécifiée en masquant toutes les autres.
     */
    public void afficherPage(PagePanel pageAAfficher) {
        // Masquer toutes les pages : Accueil, Fusion, Tuto, Cura
        Acceuil.setVisible(false);
        Fusion.setVisible(false);
        Tuto.setVisible(false);
        Cura.setVisible(false);

        // Rendre visible uniquement la page demandée
        pageAAfficher.setVisible(true);

        // Conserver la référence de la page affichée dans Temporaire pour navigation ultérieure
        Temporaire = pageAAfficher;
    }

    /**
     * Crée et configure un JButton avec les couleurs, le texte, la police et l'action spécifiés.
     */
    public JButton creerBouton(JButton bouton,
                               Color couleur,
                               Color couleurSurvol,
                               Color couleurEcriture,
                               String texte,
                               ActionListener action,
                               int size,
                               String font) {
        bouton.setText(texte);                                  // Définit le texte ou l’icône Unicode
        bouton.setFont(new Font(font, Font.PLAIN, size));      // Définit la police et la taille
        bouton.setBackground(couleur);                          // Couleur de fond
        bouton.setForeground(couleurEcriture);                  // Couleur du texte
        bouton.setFocusPainted(false);                          // Désactive le contour focus
        bouton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); // Bordure vide pour espacer le texte
        bouton.setPreferredSize(new Dimension(120, 40));        // Taille préférée (peut être ignorée si layout nul)
        bouton.addActionListener(action);                       // Ajoute l’action au clic

        return bouton;
    }

    /**
     * Définit les couleurs de base et de survol pour un JButton, en supprimant
     * les anciens MouseListeners pour éviter les doublons, puis en ajoutant un
     * MouseAdapter pour gérer l’effet de survol.
     */
    public void setCouleur(Color couleurEcriture,
                           JButton bouton,
                           Color couleurNormale,
                           Color couleurSurvol) {
        bouton.setForeground(couleurEcriture); // Texte en couleur spécifiée
        bouton.setBackground(couleurNormale); // Fond normal

        // Supprime tous les MouseListeners existants pour éviter plusieurs écouteurs
        for (MouseListener ml : bouton.getMouseListeners()) {
            if (ml instanceof java.awt.event.MouseAdapter) {
                bouton.removeMouseListener(ml);
            }
        }

        // Ajoute un nouveau MouseAdapter pour changer la couleur au survol
        bouton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bouton.setBackground(couleurSurvol); // Change la couleur au survol
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bouton.setBackground(couleurNormale); // Restaure la couleur normale
            }
        });
    }

    /**
     * Point d'entrée de l'application Swing. Lance la création de la fenêtre sur l'EDT.
     */
    public static void main(String[] args) {
        // S’assure que la création et l’affichage de la GUI se font sur l’Event Dispatch Thread
        SwingUtilities.invokeLater(Main::new);
    }
}
