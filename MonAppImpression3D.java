// === MonAppImpression3D.java ===
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class MonAppImpression3D extends JFrame {
    // Dimensions initiales de la fenêtre de l'application
    private int width = 700;
    private int height = 500;

    // Définitions des couleurs personnalisées utilisées dans l'interface
    private Color Bleu = (new Color(10,210,210));      // Couleur principale bleu
    private Color BleuGrisClair = (new Color(50,240,240)); // Variante plus GrisClaire de bleu
    private Color Gris = (new Color(210,210,210));      // Gris moyen pour fonds
    private Color GrisFoncé = (new Color(100,100,100)); // Gris foncé pour contrastes
    private Color Rouge = (new Color(255,10, 10));      // Rouge pour boutons d'alerte
    private Color GrisClair = (new Color(220,220,220));     // Gris GrisClair pour éléments
    private Color Blanc = (new Color(255,255,255)); // Très GrisClair presque blanc

    // Bouton pour basculer entre agrandi/restauré
    private JButton btnAgrandir;
    private JPanel ligneCote, ligneBarreSup; // Panneaux décoratifs

    // Définition des polices utilisées
    Font font = new Font("Arial", Font.PLAIN, 16);   // Police standard pour le texte
    Font Titre2 = new Font("Arial", Font.BOLD, 22); // Police en gras pour titres secondaires

    // Barre supérieure personnalisée et pages de l'application
    private JPanel barreSuperieure;
    private PagePanel Acceuil, Fusion, Tuto, Cura;      // Différentes pages
    private List<PagePanel> pages;                      // Liste pour faciliter la navigation

    public MonAppImpression3D() {
        super("App Impression 3D");                // Titre de la fenêtre
        setUndecorated(true);                        // Retire la décoration native OS
        setVisible(true);                            // Rend la fenêtre visible immédiatement

        // Icône de la fenêtre récupérée depuis les ressources
        Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.jpg"));
        setIconImage(logo);

        setDefaultCloseOperation(EXIT_ON_CLOSE);     // Ferme l'application au clic sur la croix
        setSize(width, height);                      // Taille initiale
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
        Fusion = new FusionPage(width, height, () -> afficherPage(Tuto));
        Tuto   = new TutoPage(width, height, () -> afficherPage(Fusion), () -> afficherPage(Cura));
        Cura   = new CuraPage(width, height, () -> afficherPage(Tuto));

        // Panneau latéral noir de bordure
        ligneCote = new JPanel();
        ligneCote.setBackground(Color.black);
        ligneCote.setBounds(0, 0, width, height);
        ligneCote.setLayout(null);
        add(ligneCote);

        // Regroupement des pages
        pages = Arrays.asList(Acceuil, Fusion, Tuto, Cura);

        // Création et ajout de la barre de contrôle personnalisée
        barreSuperieure = creerBarrePersonnalisee();
        add(barreSuperieure);

        // Ajout de chaque page au conteneur principal
        add(Acceuil);
        add(Fusion);
        add(Tuto);
        add(Cura);
        add(ligneCote);

        // Positionnement initial des composants
        repositionner();
        revalidate(); // Rafraîchit la mise en page
        repaint();    // Redessine la fenêtre

        // Affiche la page d'accueil après l'initialisation de la GUI
        SwingUtilities.invokeLater(() -> {
            for (PagePanel page : pages) {
                page.setBounds(1, 30, width - 2, height - 31);
                page.setVisible(false);
            }
            Acceuil.setVisible(true);
            repositionner();
        });

        // Gestion du redimensionnement de la fenêtre
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                width = getWidth();
                height = getHeight();

                // Ajustement de la barre supérieure et de sa ligne inférieure
                barreSuperieure.setBounds(1, 1, width - 2, 30);
                ligneBarreSup.setBounds(0, 28, width, 2);

                // Repositionne les boutons selon la nouvelle taille
                int boutonLargeur = 50;
                int boutonHauteur = 28;
                barreSuperieure.getComponent(0).setBounds(1, 0, boutonLargeur, boutonHauteur);          // Menu
                barreSuperieure.getComponent(1).setBounds(width - 51, 0, boutonLargeur, boutonHauteur); // Quitter
                barreSuperieure.getComponent(2).setBounds(width - 101, 0, boutonLargeur, boutonHauteur);// Agrandir
                barreSuperieure.getComponent(3).setBounds(width - 151, 0, boutonLargeur, boutonHauteur);// Réduire

                // Redimensionne chaque page
                for (PagePanel page : pages) {
                    page.setBounds(1, 30, width - 2, height - 31);
                }
                repositionner();
                revalidate();
                repaint();

                // Met à jour la bordure latérale
                ligneCote.setBounds(0, 0, width, height);
            }
        });

        getContentPane().setBackground(new Color(240, 240, 240)); // Fond général
        activerRedimensionnement(); // Active le redimensionnement manuel par les bords
    }

    /**
     * Crée la barre de contrôle personnalisée (menu, fermer, agrandir, réduire)
     */
    private JPanel creerBarrePersonnalisee() {
        barreSuperieure = new JPanel();
        barreSuperieure.setLayout(null);
        barreSuperieure.setBackground(Bleu);
        barreSuperieure.setBounds(1, 1, width-2, 29);
        barreSuperieure.setOpaque(true);

        // Ligne noire sous la barre
        ligneBarreSup = new JPanel();
        ligneBarreSup.setBackground(Color.black);
        ligneBarreSup.setBounds(0, 28, width, 2);
        ligneBarreSup.setLayout(null);

        // Bouton Accueil (maison)
        JButton btnMenu = BoutonFactory.creerBouton(
                Bleu, BleuGrisClair, Blanc,
                "🏠", e -> afficherPage(Acceuil),
                18, "Segoe UI Emoji"
        );
        btnMenu.setBounds(0, 0, 40, 30);
        barreSuperieure.add(btnMenu);

        // Bouton Quitter (croix)
        JButton btnQuitter = BoutonFactory.creerBouton(
                Bleu, Rouge, Blanc,
                "\uE711", e -> System.exit(0),
                18, "Segoe MDL2 Assets"
        );
        btnQuitter.setBounds(width - 50, 0, 40, 29);
        barreSuperieure.add(btnQuitter);

        // Bouton Agrandir/Restaurer
        btnAgrandir = BoutonFactory.creerBouton(
                Bleu, BleuGrisClair, Blanc,
                "\uE922", e -> toggleMaximiser(),
                12, "Segoe MDL2 Assets"
        );
        btnAgrandir.setBounds(width - 100, 0, 40, 29);
        barreSuperieure.add(btnAgrandir);

        // Bouton Réduire
        JButton btnCacher = BoutonFactory.creerBouton(
                Bleu, BleuGrisClair, Blanc,
                "\uE921", e -> setState(JFrame.ICONIFIED),
                12, "Segoe MDL2 Assets"
        );
        btnCacher.setBounds(width - 150, 0, 40, 29);
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

    // Indique si la fenêtre est actuellement maximisée
    private boolean estMaximisee = false;

    /**
     * Bascule entre les modes maximisé et restauré
     */
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

    /**
     * Réagit au redimensionnement manuel pour repositionner tous les composants
     */
    private void repositionner() {
        Acceuil.positionTitre(width, height);
        Fusion.positionTitre(width, height);
        Tuto.positionTitre(width, height);
        Cura.positionTitre(width, height);

        // Positionne les boutons de navigation sur chaque page selon la taille
        Acceuil.getComponent(1).setBounds(width / 100 * 2, height / 100 * 20, 200, 240); // btnVersFusion
        Acceuil.getComponent(2).setBounds(width / 2 - 100, height / 100 * 20, 200, 240);  // btnVersTuto
        Acceuil.getComponent(3).setBounds(width - (width / 100 * 2) - 200 , height / 100 * 20, 200, 240);  // btnVersCura

        Fusion.getComponent(1).setBounds(width - 120, height - 80, 80, 20);              // btnVersTuto
        Fusion.getComponent(2).setBounds(10, 10, width - 20, height - 50);            // JScrollPane

        Tuto.getComponent(1).setBounds(20, height - 70, 80, 20);                        // btnVersFusion
        Tuto.getComponent(2).setBounds(width - 120, height - 70, 80, 20);               // btnVersCura

        Cura.getComponent(1).setBounds(width / 2 - 130, height / 100 * 70, 120, 40);    // btnRetourFusion
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
                if (x < marge && y < marge) { setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR)); }
                else if (x > getWidth() - marge && y < marge) { setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR)); }
                else if (x < marge && y > getHeight() - marge) { setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR)); }
                else if (x > getWidth() - marge && y > getHeight() - marge) { setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR)); }
                else if (x < marge) { setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR)); }
                else if (x > getWidth() - marge) { setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR)); }
                else if (y < marge) { setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR)); }
                else if (y > getHeight() - marge) { setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR)); }
                else { setCursor(Cursor.getDefaultCursor()); }
            }

            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (estMaximisee) return;

                Cursor c = getCursor();
                Rectangle bounds = getBounds();
                int dx = e.getXOnScreen() - bounds.x;
                int dy = e.getYOnScreen() - bounds.y;

                // Ajuste les dimensions selon le type de curseur
                switch (c.getType()) {
                    case Cursor.E_RESIZE_CURSOR: bounds.width = dx; break;
                    case Cursor.S_RESIZE_CURSOR: bounds.height = dy; break;
                    case Cursor.SE_RESIZE_CURSOR: bounds.width = dx; bounds.height = dy; break;
                    case Cursor.W_RESIZE_CURSOR:
                        int newW = bounds.width - dx;
                        if (newW > 100) { bounds.x += dx; bounds.width = newW; }
                        break;
                    case Cursor.N_RESIZE_CURSOR:
                        int newH = bounds.height - dy;
                        if (newH > 100) { bounds.y += dy; bounds.height = newH; }
                        break;
                    case Cursor.NW_RESIZE_CURSOR:
                        newW = bounds.width - dx;
                        newH = bounds.height - dy;
                        if (newW > 100) { bounds.x += dx; bounds.width = newW; }
                        if (newH > 100) { bounds.y += dy; bounds.height = newH; }
                        break;
                    case Cursor.NE_RESIZE_CURSOR:
                        bounds.width = dx;
                        newH = bounds.height - dy;
                        if (newH > 100) { bounds.y += dy; bounds.height = newH; }
                        break;
                    case Cursor.SW_RESIZE_CURSOR:
                        bounds.height = dy;
                        newW = bounds.width - dx;
                        if (newW > 100) { bounds.x += dx; bounds.width = newW; }
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

    private void afficherPage(PagePanel pageAAfficher) {
        // Masque toutes les pages puis affiche la page demandée
        for (PagePanel page : pages) {
            page.setVisible(false);
        }
        pageAAfficher.setVisible(true);
    }

    public static void main(String[] args) {
        // Point d'entrée de l'application Swing
        SwingUtilities.invokeLater(MonAppImpression3D::new);
    }
}
