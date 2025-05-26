// === MonAppImpression3D.java ===
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Arrays;

public class MonAppImpression3D extends JFrame {
    // Dimensions initiales de la fenêtre de l'application
    private int Largeur = 700;
    private int Hauteur = 500;
    private int largeurFleche;
    private int hauteurFleche;
    private int decalage = 1;
    private int posBtnTuto= 0;
    private int posBtnFusion = 0;
    private int posBtnCura = 0;

    // Définitions des couleurs personnalisées utilisées dans l'interface
    private Color Bleu = (new Color(10,210,210)), BleuGrisClair = (new Color(50,240,240)), Noir = (new Color(0,0,0)), Rouge = (new Color(255,10, 10)), GrisClair = (new Color(220,220,220)), Blanc = (new Color(255,255,255)), BleuClair = new Color(10,240,240);
    private Color InvertBleu = new Color(58, 77, 160), InvertBleuGrisClair = new Color(75, 97, 193), InvertNoir = new Color(200, 200, 200), InvertRouge = new Color(180, 0, 0), SurvolInvertBleuClair = new Color(70, 82, 96), InvertBlanc = new Color(30, 30, 30), InvertBleuClair = new Color(30, 42, 56);


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
    Font Titre2 = new Font("Arial", Font.BOLD, 22); // Police en gras pour titres secondaires

    public MonAppImpression3D() {
        super("App Impression 3D");                // Titre de la fenêtre
        setUndecorated(true);                        // Retire la décoration native OS
        setVisible(true);                            // Rend la fenêtre visible immédiatement

        // Icône de la fenêtre récupérée depuis les ressources
        Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.jpg"));
        setIconImage(logo);

        setDefaultCloseOperation(EXIT_ON_CLOSE);     // Ferme l'application au clic sur la croix
        setSize(Largeur, Hauteur);                   // Taille initiale
        setLocationRelativeTo(null);                 // Centre la fenêtre à l'écran
        setLayout(null);                             // Layout absolu pour positionnement manuel

        // Initialisation des objets PagePanel avec leurs actions associées
        Acceuil = new AccueilPage(
                "Application d'impression 3D",
                () -> afficherPage(Fusion),
                () -> afficherPage(Tuto),
                () -> afficherPage(Cura),
                () -> dispose()                    // Ferme l'application
        );
        Cura   = new CuraPage(Largeur, Hauteur);
        Tuto   = new TutoPage(Largeur, Hauteur, Cura, this);
        Fusion = new FusionPage(Largeur, Hauteur, Tuto, this);

        // Panneau latéral noir de bordure
        ligneCote = new JPanel();
        ligneCote.setBackground(Noir);
        ligneCote.setBounds(0, 0, Largeur, Hauteur);
        ligneCote.setLayout(null);

        barreBouton = new JPanel();
        barreBouton.setBackground(BleuClair);
        barreBouton.setBounds(1, 1, Largeur - 2, Hauteur - 2);
        barreBouton.setLayout(null);
        barreBouton.setOpaque(true);

        boutonFusion.setText("Fusion \u25BC");
        boutonFusion.setBackground(Bleu);
        boutonFusion.setBounds(2,32, 94, 20);
        boutonFusion.setFont(TitreBoutonGauche);
        boutonFusion.setFocusPainted(false);
        boutonFusion.addActionListener(e ->InvertBtnFusion());
        boutonFusion.setVisible(false);

        ongletsFusion = Fusion.getOnglets();  // tu ajoutes un getter
        btnAllerInstallerFusion.setText("<html><center>Installation de fusion 360</center></html>");
        btnAllerInstallerFusion.setBackground(Bleu);
        btnAllerInstallerFusion.setBounds(10,54, 86, 30);
        btnAllerInstallerFusion.setFont(SousTitreBoutonGauche);
        btnAllerInstallerFusion.setFocusPainted(false);
        btnAllerInstallerFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerInstallerFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(0));
        btnAllerInstallerFusion.setVisible(false);

        ongletsFusion = Fusion.getOnglets();  // tu ajoutes un getter
        btnAllerDecouverteFusion.setText("<html><center>Découverte de fusion 360</center></html>");
        btnAllerDecouverteFusion.setBackground(Bleu);
        btnAllerDecouverteFusion.setBounds(10,86, 86, 30);
        btnAllerDecouverteFusion.setFont(SousTitreBoutonGauche);
        btnAllerDecouverteFusion.setFocusPainted(false);
        btnAllerDecouverteFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerDecouverteFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(1));
        btnAllerDecouverteFusion.setVisible(false);

        ongletsFusion = Fusion.getOnglets();  // tu ajoutes un getter
        btnAllerOutilsFusion.setText("<html><center>Outils de<br>modélisation</center></html>");
        btnAllerOutilsFusion.setBackground(Bleu);
        btnAllerOutilsFusion.setBounds(10,118, 86, 30);
        btnAllerOutilsFusion.setFont(SousTitreBoutonGauche);
        btnAllerOutilsFusion.setFocusPainted(false);
        btnAllerOutilsFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerOutilsFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(2));
        btnAllerOutilsFusion.setVisible(false);

        ongletsFusion = Fusion.getOnglets();  // tu ajoutes un getter
        btnAllerApplicationFusion.setText("<html><center>Domaine<br>d'application</center></html>");
        btnAllerApplicationFusion.setBackground((Bleu));
        btnAllerApplicationFusion.setBounds(10,150, 86, 30);
        btnAllerApplicationFusion.setFont(SousTitreBoutonGauche);
        btnAllerApplicationFusion.setFocusPainted(false);
        btnAllerApplicationFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerApplicationFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(3));
        btnAllerApplicationFusion.setVisible(false);

        ongletsFusion = Fusion.getOnglets();  // tu ajoutes un getter
        btnAllerVocabulaireFusion.setText("<html><center>Vocabulaire<br>utile</center></html>");
        btnAllerVocabulaireFusion.setBackground((Bleu));
        btnAllerVocabulaireFusion.setBounds(10,182, 86, 30);
        btnAllerVocabulaireFusion.setFont(SousTitreBoutonGauche);
        btnAllerVocabulaireFusion.setFocusPainted(false);
        btnAllerVocabulaireFusion.addActionListener(e -> afficherPage(Fusion));
        btnAllerVocabulaireFusion.addActionListener(e -> ongletsFusion.setSelectedIndex(4));
        btnAllerVocabulaireFusion.setVisible(false);


        boutonTuto.setText("Tuto \u25BC");
        boutonTuto.setBackground((Bleu));
        boutonTuto.setBounds(2,54, 94, 20);
        boutonTuto.setFont(TitreBoutonGauche);
        boutonTuto.setFocusPainted(false);
        boutonTuto.addActionListener(e ->InvertBtnTuto());
        boutonTuto.setVisible(false);

        ongletsTuto = Tuto.getOnglets();  // tu ajoutes un getter
        btnCube.setText("<html><center>Cube</center></html>");
        btnCube.setBackground(Bleu);
        btnCube.setBounds(10,76, 86, 30);
        btnCube.setFont(SousTitreBoutonGauche);
        btnCube.setFocusPainted(false);
        btnCube.addActionListener(e -> afficherPage(Tuto));
        btnCube.addActionListener(e -> ongletsTuto.setSelectedIndex(0));
        btnCube.setVisible(true);

        ongletsTuto = Tuto.getOnglets();  // tu ajoutes un getter
        btnDes.setText("<html><center>Des</center></html>");
        btnDes.setBackground(Bleu);
        btnDes.setBounds(10,108, 86, 30);
        btnDes.setFont(SousTitreBoutonGauche);
        btnDes.setFocusPainted(false);
        btnDes.addActionListener(e -> afficherPage(Tuto));
        btnDes.addActionListener(e -> ongletsTuto.setSelectedIndex(1));
        btnDes.setVisible(false);

        ongletsTuto = Tuto.getOnglets();  // tu ajoutes un getter
        btnVaisseau.setText("<html><center>Vaisseau</center></html>");
        btnVaisseau.setBackground(Bleu);
        btnVaisseau.setBounds(10,140, 86, 30);
        btnVaisseau.setFont(SousTitreBoutonGauche);
        btnVaisseau.setFocusPainted(false);
        btnVaisseau.addActionListener(e -> afficherPage(Tuto));
        btnVaisseau.addActionListener(e -> ongletsTuto.setSelectedIndex(2));
        btnVaisseau.setVisible(false);


        boutonCura.setText("Cura \u25BC");
        boutonCura.setBackground((Bleu));
        boutonCura.setBounds(2,76, 94, 20);
        boutonCura.setFont(TitreBoutonGauche);
        boutonCura.setFocusPainted(false);
        boutonCura.addActionListener(e ->InvertBtnCura());
        boutonCura.setVisible(false);

        ongletsCura = Cura.getOnglets();  // tu ajoutes un getter
        btnAllerInstallerCura.setText("<html><center>Installation de Cura</center></html>");
        btnAllerInstallerCura.setBackground(Bleu);
        btnAllerInstallerCura.setBounds(10,98, 86, 30);
        btnAllerInstallerCura.setFont(SousTitreBoutonGauche);
        btnAllerInstallerCura.setFocusPainted(false);
        btnAllerInstallerCura.addActionListener(e -> afficherPage(Cura));
        btnAllerInstallerCura.addActionListener(e -> ongletsCura.setSelectedIndex(0));
        btnAllerInstallerCura.setVisible(false);

        ongletsCura = Cura.getOnglets();  // tu ajoutes un getter
        btnAllerDecouverteCura.setText("<html><center>Découverte de Cura</center></html>");
        btnAllerDecouverteCura.setBackground(Bleu);
        btnAllerDecouverteCura.setBounds(10,130, 86, 30);
        btnAllerDecouverteCura.setFont(SousTitreBoutonGauche);
        btnAllerDecouverteCura.setFocusPainted(false);
        btnAllerDecouverteCura.addActionListener(e -> afficherPage(Cura));
        btnAllerDecouverteCura.addActionListener(e -> ongletsCura.setSelectedIndex(1));
        btnAllerDecouverteCura.setVisible(false);

        ongletsCura = Cura.getOnglets();  // tu ajoutes un getter
        btnAllerOutilsCura.setText("<html><center>Outils de Cura</center></html>");
        btnAllerOutilsCura.setBackground(Bleu);
        btnAllerOutilsCura.setBounds(10,162, 86, 30);
        btnAllerOutilsCura.setFont(SousTitreBoutonGauche);
        btnAllerOutilsCura.setFocusPainted(false);
        btnAllerOutilsCura.addActionListener(e -> afficherPage(Cura));
        btnAllerOutilsCura.addActionListener(e -> ongletsCura.setSelectedIndex(2));
        btnAllerOutilsCura.setVisible(false);

        ongletsCura = Cura.getOnglets();  // tu ajoutes un getter
        btnAllerApplicationCura.setText("<html><center>Domaine d'application</center></html>");
        btnAllerApplicationCura.setBackground((Bleu));
        btnAllerApplicationCura.setBounds(10,194, 86, 30);
        btnAllerApplicationCura.setFont(SousTitreBoutonGauche);
        btnAllerApplicationCura.setFocusPainted(false);
        btnAllerApplicationCura.addActionListener(e -> afficherPage(Cura));
        btnAllerApplicationCura.addActionListener(e -> ongletsCura.setSelectedIndex(3));
        btnAllerApplicationCura.setVisible(false);

        ongletsCura = Cura.getOnglets();  // tu ajoutes un getter
        btnAllerVocabulaireCura.setText("<html><center>Vocabulaire<br>utile</center></html>");
        btnAllerVocabulaireCura.setBackground((Bleu));
        btnAllerVocabulaireCura.setBounds(10,226, 86, 30);
        btnAllerVocabulaireCura.setFont(SousTitreBoutonGauche);
        btnAllerVocabulaireCura.setFocusPainted(false);
        btnAllerVocabulaireCura.addActionListener(e -> afficherPage(Cura));
        btnAllerVocabulaireCura.addActionListener(e -> ongletsCura.setSelectedIndex(4));
        btnAllerVocabulaireCura.setVisible(false);

        barreBouton.add(boutonFusion);
        barreBouton.add(btnAllerInstallerFusion);
        barreBouton.add(btnAllerDecouverteFusion);
        barreBouton.add(btnAllerOutilsFusion);
        barreBouton.add(btnAllerApplicationFusion);
        barreBouton.add(btnAllerVocabulaireFusion);

        barreBouton.add(boutonTuto);
        barreBouton.add(btnCube);
        barreBouton.add(btnDes);
        barreBouton.add(btnVaisseau);

        barreBouton.add(boutonCura);
        barreBouton.add(btnAllerInstallerCura);
        barreBouton.add(btnAllerDecouverteCura);
        barreBouton.add(btnAllerOutilsCura);
        barreBouton.add(btnAllerApplicationCura);
        barreBouton.add(btnAllerVocabulaireCura);


        ligneFlecheActive = new JPanel();
        ligneFlecheActive.setBackground(Noir);
        ligneFlecheActive.setBounds(98, 1, 3, Hauteur);
        ligneFlecheActive.setLayout(null);
        ligneFlecheActive.setOpaque(false);

        // Création et ajout de la barre de contrôle personnalisée
        barreSuperieure = creerBarrePersonnalisee();
        add(barreSuperieure);
        Temporaire = Acceuil;
        btnFleche = creerBouton(btnFleche,
                Bleu, BleuGrisClair, Blanc,
                "->", e -> boutonFlecheClick(),
                18, "Segoe UI Emoji"
        );
        btnFleche.addActionListener(e -> afficherPage(Temporaire));
        btnFleche.setBounds(1, 31, 30, 20);
        btnFleche.setContentAreaFilled(true);
        btnFleche.setForeground(Noir);

        // Ajout de chaque page au conteneur principal
        getLayeredPane().add(btnFleche, JLayeredPane.PALETTE_LAYER);
        add(ligneFlecheActive);
        add(Acceuil);
        add(Fusion);
        add(Tuto);
        add(Cura);
        add(barreBouton);
        add(ligneCote);


        SwingUtilities.invokeLater(() -> {

            // Affiche la page d'accueil après l'initialisation de la GUI
            Acceuil.setBounds(1, 30, Largeur - 2, Hauteur - 31);
            Fusion.setBounds(1, 30, Largeur - 2, Hauteur - 31);
            Cura.setBounds(1, 30, Largeur - 2, Hauteur - 31);
            Tuto.setBounds(1, 30, Largeur - 2, Hauteur - 31);

            if (btnModeNuit == true) {
                Acceuil.setBackground(InvertBlanc);
                Fusion.setBackground(InvertBlanc);
                Cura.setBackground(InvertBlanc);
                Tuto.setBackground(InvertBlanc);
            }
            else{
                Acceuil.setBackground(Blanc);
                Fusion.setBackground(Blanc);
                Cura.setBackground(Blanc);
                Tuto.setBackground(Blanc);
            }
            repositionner();
            revalidate();
            repaint();
        });

        // Gestion du redimensionnement de la fenêtre
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Largeur = getWidth();
                Hauteur = getHeight();

                // Ajustement de la barre supérieure et de sa ligne inférieure
                barreSuperieure.setBounds(1, 1, Largeur - 2, 30);
                ligneBarreSup.setBounds(0, 28, Largeur, 2);

                // Repositionne les boutons selon la nouvelle taille
                int boutonLargeur = 50;
                int boutonHauteur = 28;
                barreSuperieure.getComponent(0).setBounds(1, 0, boutonLargeur, boutonHauteur);          // Menu
                barreSuperieure.getComponent(1).setBounds(boutonLargeur + 1, 0, boutonLargeur, boutonHauteur); // Mode nuit
                barreSuperieure.getComponent(2).setBounds(Largeur - 51, 0, boutonLargeur, boutonHauteur); // Quitter
                barreSuperieure.getComponent(3).setBounds(Largeur - 101, 0, boutonLargeur, boutonHauteur);// Agrandir
                barreSuperieure.getComponent(4).setBounds(Largeur - 151, 0, boutonLargeur, boutonHauteur);// Réduire

                // Redimensionne chaque page
                Acceuil.setBounds(1, 30, Largeur - 2, Hauteur - 31);
                Fusion.setBounds(1, 30, Largeur - 2, Hauteur - 31);
                Cura.setBounds(1, 30, Largeur - 2, Hauteur - 31);
                Tuto.setBounds(1, 30, Largeur - 2, Hauteur - 31);
                if (flecheActive){
                    btnFleche.setBounds(30, Hauteur - 21, 30, 20);
                    btnFleche.setText("<-");
                }
                else {
                    btnFleche.setBounds(1, 31, 30, 20);
                    btnFleche.setText("->");
                }
                repositionner();
                revalidate();
                repaint();

                // Met à jour la bordure latérale
                ligneCote.setBounds(0, 0, Largeur, Hauteur);
            }
        });

        repositionner();
        revalidate();
        repaint();

        getContentPane().setBackground(Blanc); // Fond général
        activerRedimensionnement(); // Active le redimensionnement manuel par les bords
        // Positionnement initial des composants
    }

    public void InvertBtnFusion(){
        btnFusion = !btnFusion;
        repositionner();
        revalidate();
        repaint();
    }

    public void InvertBtnTuto(){
        btnTuto = !btnTuto;
        repositionner();
        revalidate();
        repaint();
    }

    public void InvertBtnCura(){
        btnCura = !btnCura;
        repositionner();
        revalidate();
        repaint();
    }

    /**
     * Crée la barre de contrôle personnalisée (menu, fermer, agrandir, réduire)
     */

    public void boutonFlecheClick() {
        flecheActive = !flecheActive;

        // Met à jour les tailles/positions des pages selon le nouvel état
        decalage = !flecheActive ? 1 : 100;

        Acceuil.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);
        Fusion.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);
        Tuto.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);
        Cura.setBounds(decalage, 30, Largeur - 2, Hauteur - 31);
        ((FusionPage) Fusion).flecheActiveOuNon(flecheActive);
        if (flecheActive){
            btnFleche.setBounds(decalage - 30, Hauteur-21, 28, 20);
            btnFleche.setText("<-");
            Acceuil.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Fusion.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Tuto.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Cura.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            ligneFlecheActive.setOpaque(true);
        }
        else {
            btnFleche.setBounds(1, 31, 30, 20);
            btnFleche.setText("->");
            ligneFlecheActive.setOpaque(false);
        }

        repositionner();
        revalidate();
        repaint();
    }


    private JPanel creerBarrePersonnalisee() {
        barreSuperieure = new JPanel();
        barreSuperieure.setLayout(null);
        barreSuperieure.setBackground(Bleu);
        barreSuperieure.setBounds(1, 1, Largeur-2, 29);
        barreSuperieure.setOpaque(true);

        // Ligne noire sous la barre
        ligneBarreSup = new JPanel();
        ligneBarreSup.setBackground(Noir);
        ligneBarreSup.setBounds(0, 28, Largeur, 2);
        ligneBarreSup.setLayout(null);

        // Bouton Accueil (maison)
        btnMenu = creerBouton(btnMenu,
                Bleu, BleuGrisClair, Blanc,
                "🏠", e -> afficherPage(Acceuil),
                18, "Segoe UI Emoji"
        );
        btnMenu.setBounds(0, 0, 40, 30);
        barreSuperieure.add(btnMenu);

        btnNuit.addActionListener(e -> ((AccueilPage) Acceuil).modeNuitAcceuil(btnModeNuit));
        btnNuit.addActionListener(e -> ((FusionPage) Fusion).modeNuitFusion(btnModeNuit));
        btnNuit.addActionListener(e -> ((TutoPage) Tuto).modeNuitTuto(btnModeNuit));
        btnNuit.addActionListener(e -> ((CuraPage) Cura).modeNuitCura(btnModeNuit));
        btnNuit = creerBouton(btnNuit,
                Bleu, BleuGrisClair, Blanc,
                "\uD83C\uDF19", e -> InvertModeNuit(),
                18, "Segoe UI Emoji"
        );
        btnNuit.setBounds(40, 0, 40, 30);

        barreSuperieure.add(btnNuit);

        // Bouton Quitter (croix)
        btnQuitter = creerBouton(btnQuitter,
                Bleu, Rouge, Blanc,
                "\uE711", e -> System.exit(0),
                18, "Segoe MDL2 Assets"
        );
        btnQuitter.setBounds(Largeur - 50, 0, 40, 29);
        barreSuperieure.add(btnQuitter);

        // Bouton Agrandir/Restaurer
        btnAgrandir = creerBouton(btnAgrandir,
                Bleu, BleuGrisClair, Blanc,
                "\uE922", e -> toggleMaximiser(),
                12, "Segoe MDL2 Assets"
        );
        btnAgrandir.setBounds(Largeur - 100, 0, 40, 29);
        barreSuperieure.add(btnAgrandir);

        // Bouton Réduire
        btnCacher = creerBouton(btnCacher,
                Bleu, BleuGrisClair, Blanc,
                "\uE921", e -> setState(JFrame.ICONIFIED),
                12, "Segoe MDL2 Assets"
        );
        btnCacher.setBounds(Largeur - 150, 0, 40, 29);
        barreSuperieure.add(btnCacher);

        // Gestion du déplacement de la fenêtre via drag de la barre
        final Point[] initialClick = {null};
        barreSuperieure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                initialClick[0] = e.getPoint(); // Retient le point de clic initial
            }
        });
        barreSuperieure.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                // Calcule le déplacement et déplace la fenêtre
                int thisX = getLocation().x;
                int thisY = getLocation().y;
                int xMoved = e.getX() - initialClick[0].x;
                int yMoved = e.getY() - initialClick[0].y;
                setLocation(thisX + xMoved, thisY + yMoved);
            }
        });
        barreSuperieure.add(ligneBarreSup);
        return barreSuperieure;
    }

    public void InvertModeNuit(){
        btnModeNuit = !btnModeNuit;
        repositionner();
        revalidate();
        repaint();
    }


    // Indique si la fenêtre est actuellement maximisée
    private boolean estMaximisee = false;

    // Bascule entre les modes maximisé et restauré
    private void toggleMaximiser() {
        if (estMaximisee) {
            // Restaurer aux dimensions initiales
            setSize(700, 500);
            setLocationRelativeTo(null);
            estMaximisee = false;
            btnAgrandir.setText("\uE922"); // Icône agrandir
            setResizable(true);               // Autorise le redimensionnement manuel
        } else {
            // Maximiser la fenêtre
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            estMaximisee = true;
            btnAgrandir.setText("\uE923"); // Icône restaurer
            setResizable(false);              // Désactive le redimensionnement
            setBounds(getX(), getY(), getWidth(), getHeight());
        }
    }

    // Réagit au redimensionnement manuel pour repositionner tous les composants
    private void repositionner() {
        if(flecheActive) {
            if (btnFusion == true){
                boutonFusion.setText("Fusion \u25B2");
                posBtnFusion = 160;
                btnAllerInstallerFusion.setVisible(true);
                btnAllerDecouverteFusion.setVisible(true);
                btnAllerOutilsFusion.setVisible(true);
                btnAllerApplicationFusion.setVisible(true);
                btnAllerVocabulaireFusion.setVisible(true);
            }
            else{
                boutonFusion.setText("Fusion \u25BC");
                posBtnFusion = 0;
                btnAllerInstallerFusion.setVisible(false);
                btnAllerDecouverteFusion.setVisible(false);
                btnAllerOutilsFusion.setVisible(false);
                btnAllerApplicationFusion.setVisible(false);
                btnAllerVocabulaireFusion.setVisible(false);
            }

            if (btnTuto == true){
                boutonTuto.setText("Tuto \u25B2");
                posBtnTuto = 96;
                btnCube.setVisible(true);
                btnDes.setVisible(true);
                btnVaisseau.setVisible(true);
            }
            else{
                boutonTuto.setText("Tuto \u25BC");
                posBtnTuto= 0;
                btnCube.setVisible(false);
                btnDes.setVisible(false);
                btnVaisseau.setVisible(false);
            }

            if (btnCura == true){
                boutonCura.setText("Cura \u25B2");
                btnAllerInstallerCura.setVisible(true);
                btnAllerDecouverteCura.setVisible(true);
                btnAllerOutilsCura.setVisible(true);
                btnAllerApplicationCura.setVisible(true);
                btnAllerVocabulaireCura.setVisible(true);
            }
            else{
                boutonCura.setText("Cura \u25BC");
                btnAllerInstallerCura.setVisible(false);
                btnAllerDecouverteCura.setVisible(false);
                btnAllerOutilsCura.setVisible(false);
                btnAllerApplicationCura.setVisible(false);
                btnAllerVocabulaireCura.setVisible(false);
            }

            if (btnModeNuit == true) {
                Acceuil.setBackground(InvertBlanc);
                Fusion.setBackground(InvertBlanc);
                Tuto.setBackground(InvertBlanc);
                Cura.setBackground(InvertBlanc);
            }
            else{
                Acceuil.setBackground(Blanc);
                Fusion.setBackground(Blanc);
                Tuto.setBackground(Blanc);
                Cura.setBackground(Blanc);
            }

            btnFleche.setBounds(decalage - 30, Hauteur-21, 28, 20);
            btnFleche.setText("<-");

            Acceuil.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Fusion.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Tuto.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);
            Cura.setBounds(decalage, 30, Largeur - 101, Hauteur - 31);

            ligneFlecheActive.setOpaque(true);

            hauteurFleche = Hauteur;
            largeurFleche = Largeur - 100;

            ligneFlecheActive.setBounds(98, 1, 3, Hauteur);


            boutonFusion.setVisible(true);
            boutonFusion.setBounds(2,32, 94, 20);

            boutonTuto.setVisible(true);
            boutonTuto.setBounds(2,54 + posBtnFusion, 94, 20);
            btnCube.setBounds(10,76 + posBtnFusion, 86, 30);
            btnDes.setBounds(10, 108 + posBtnFusion, 86, 30);
            btnVaisseau.setBounds(10, 140 + posBtnFusion, 86, 30);

            boutonCura.setVisible(true);
            boutonCura.setBounds(2, 76 + posBtnFusion + posBtnTuto, 94, 20);
            btnAllerInstallerCura.setBounds(10, 98 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerDecouverteCura.setBounds(10, 130 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerOutilsCura.setBounds(10, 162 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerApplicationCura.setBounds(10, 194 + posBtnFusion + posBtnTuto, 86, 30);
            btnAllerVocabulaireCura.setBounds(10, 226 + posBtnFusion + posBtnTuto, 86, 30);

            // Positionne les boutons de navigation sur chaque page selon la taille

            int monComposant = Acceuil.getComponent(2).getSize().width;

            Acceuil.getComponent(1).setBounds((largeurFleche / 2 - 325 / 2), hauteurFleche / 100 * 10, 325, 100); // Titre
            Acceuil.getComponent(2).setBounds((largeurFleche / 16 * 3 - monComposant / 2), hauteurFleche / 100 * 35, 150, 100); // btnVersFusion
            Acceuil.getComponent(3).setBounds((largeurFleche / 2 - monComposant / 2), hauteurFleche / 100 * 35, 150, 100);  // btnVersTuto
            Acceuil.getComponent(4).setBounds((largeurFleche / 16 * 13 - monComposant / 2), hauteurFleche / 100 * 35, 150, 100);  // btnVersCura

            Fusion.getComponent(1).setBounds(-100, -1, largeurFleche + 100, hauteurFleche - 30);            // JScrollPane

            Tuto.getComponent(1).setBounds(-100, -1, largeurFleche + 100, hauteurFleche - 30);            // JScrollPane

            Cura.getComponent(1).setBounds(-100, -1, largeurFleche + 100, hauteurFleche - 30);            // JScrollPane
        }
        else {
            if (btnModeNuit == true) {
                Acceuil.setBackground(InvertBlanc);
                Fusion.setBackground(InvertBlanc);
                Tuto.setBackground(InvertBlanc);
                Cura.setBackground(InvertBlanc);
            }
            else{
                Acceuil.setBackground(Blanc);
                Fusion.setBackground(Blanc);
                Tuto.setBackground(Blanc);
                Cura.setBackground(Blanc);
            }
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

            // Positionne les boutons de navigation sur chaque page selon la taille
            int monComposant = Acceuil.getComponent(2).getSize().width;

            btnFleche.setBounds(1, 31, 30, 20);
            btnFleche.setText("->");
            ligneFlecheActive.setOpaque(false);


            Acceuil.getComponent(1).setBounds((Largeur / 2 - 325 / 2), Hauteur / 100 * 10, 325, 100); // Titre
            Acceuil.getComponent(2).setBounds(Largeur / 16 * 3 - monComposant / 2, Hauteur / 100 * 35, 150, 100); // btnVersFusion
            Acceuil.getComponent(3).setBounds(Largeur / 2 - monComposant / 2, Hauteur / 100 * 35, 150, 100);  // btnVersTuto
            Acceuil.getComponent(4).setBounds(Largeur / 16 * 13 - monComposant / 2, Hauteur / 100 * 35, 150, 100);  // btnVersCura


            Fusion.getComponent(1).setBounds(-100, -1, Largeur + 100, Hauteur - 30);       // JScrollPane

            Tuto.getComponent(1).setBounds(-100, -1, Largeur + 100, Hauteur - 30);       // JScrollPane

            Cura.getComponent(1).setBounds(-100, -1, Largeur + 100, Hauteur - 30);       // JScrollPane
        }

        barreBouton.setBounds(1, 1, Largeur - 2, Hauteur - 2);
        if (btnModeNuit == true){
            btnNuit.setText("\uD83D\uDD06");
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

            ligneCote.setBackground(InvertNoir);
            barreBouton.setBackground(InvertBleuClair);
            ligneFlecheActive.setBackground(InvertNoir);
            barreSuperieure.setBackground(InvertBleuClair);
            ligneBarreSup.setBackground(InvertNoir);

        }
        else{
            btnNuit.setText("\uD83C\uDF19");
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

            ligneCote.setBackground(Noir);
            barreBouton.setBackground(BleuClair);
            ligneFlecheActive.setBackground(Noir);
            barreSuperieure.setBackground(Bleu);
            ligneBarreSup.setBackground(Noir);
        }

        repaint();
    }

    /**
     * Active le redimensionnement via les bords de la fenêtre
     */
    private void activerRedimensionnement() {
        final int marge = 5; // Zone sensible au redimensionnement
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent e) {
                if (estMaximisee) return; // Pas de redimensionnement si maximisé

                int x = e.getX();
                int y = e.getY();
                // Change le curseur selon la position (bords, coins)

                if (x <= marge && y <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                } else if (x >= getWidth() - marge && y <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                } else if (x <= marge && y >= getHeight() - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                } else if (x >= getWidth() - marge && y >= getHeight() - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                } else if (x <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                } else if (x >= getWidth() - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                } else if (y <= marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else if (y >= getHeight() - marge) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            }

            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (estMaximisee) return;

                Cursor c = getCursor();
                Rectangle bounds = getBounds();
                int dx = e.getXOnScreen() - bounds.x;
                int dy = e.getYOnScreen() - bounds.y;

                // Ajuste les dimensions selon le type de curseur
                switch (c.getType()) {
                    case Cursor.E_RESIZE_CURSOR:
                        bounds.width = dx;
                        break;
                    case Cursor.S_RESIZE_CURSOR:
                        bounds.height = dy;
                        break;
                    case Cursor.SE_RESIZE_CURSOR:
                        bounds.width = dx;
                        bounds.height = dy;
                        break;
                    case Cursor.W_RESIZE_CURSOR:
                        int newW = bounds.width - dx;
                        if (newW > 100) {
                            bounds.x += dx;
                            bounds.width = newW;
                        }
                        break;
                    case Cursor.N_RESIZE_CURSOR:
                        int newH = bounds.height - dy;
                        if (newH > 100) {
                            bounds.y += dy;
                            bounds.height = newH;
                        }
                        break;
                    case Cursor.NW_RESIZE_CURSOR:
                        newW = bounds.width - dx;
                        newH = bounds.height - dy;
                        if (newW > 100) {
                            bounds.x += dx;
                            bounds.width = newW;
                        }
                        if (newH > 100) {
                            bounds.y += dy;
                            bounds.height = newH;  // CORRIGÉ ICI
                        }
                        break;
                    case Cursor.NE_RESIZE_CURSOR:
                        bounds.width = dx;
                        newH = bounds.height - dy;
                        if (newH > 100) {
                            bounds.y += dy;
                            bounds.height = newH;
                        }
                        break;
                    case Cursor.SW_RESIZE_CURSOR:
                        bounds.height = dy;
                        newW = bounds.width - dx;
                        if (newW > 100) {
                            bounds.x += dx;
                            bounds.width = newW;
                        }
                        break;
                }

                setBounds(bounds);
                revalidate();
                repaint();
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
                repositionner(); // Met à jour si la taille a changé
            }
        });
    }

    public void afficherPage(PagePanel pageAAfficher) {
        // Masque toutes les pages puis affiche la page demandée
        Acceuil.setVisible(false);
        Fusion.setVisible(false);
        Tuto.setVisible(false);
        Cura.setVisible(false);

        pageAAfficher.setVisible(true);
        Temporaire = pageAAfficher;
    }

    public JButton creerBouton(JButton bouton, Color couleur, Color couleurSurvol, Color couleurEcriture, String texte, ActionListener action, int size, String font) {
        bouton.setText(texte);
        bouton.setFont(new Font(font, Font.PLAIN, size));
        bouton.setBackground(couleur);
        bouton.setForeground(couleurEcriture);
        bouton.setFocusPainted(false);
        bouton.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        bouton.setPreferredSize(new Dimension(120, 40));
        bouton.addActionListener(action);

        return bouton;
    }

    public void setCouleur(Color couleurEcriture, JButton bouton, Color couleurNormale, Color couleurSurvol) {
        bouton.setForeground(couleurEcriture);
        bouton.setBackground(couleurNormale);

        // D'abord, on supprime les anciens MouseListeners (pour éviter les doublons)
        for (MouseListener ml : bouton.getMouseListeners()) {
            if (ml instanceof java.awt.event.MouseAdapter) {
                bouton.removeMouseListener(ml);
            }
        }

        // Puis on ajoute un nouveau listener avec les couleurs mises à jour
        bouton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bouton.setBackground(couleurSurvol);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bouton.setBackground(couleurNormale);
            }
        });
    }




    public static void main(String[] args) {
        // Point d'entrée de l'application Swing
        SwingUtilities.invokeLater(MonAppImpression3D::new);
    }
}
