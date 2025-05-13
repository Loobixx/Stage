// === AccueilPage.java ===
import javax.swing.*;
import java.awt.*;

public class AccueilPage extends PagePanel {
    private Color Bleu = new Color(10,210,210);
    private Color BleuFoncé = new Color(7,150,150);
    private Color Noir = new Color(0,0,0);

    public AccueilPage(String titre, Runnable onFusion, Runnable onTuto, Runnable onCura, Runnable onFermer) {
        super(titre);

        JButton btnVersFusion = BoutonFactory.creerBouton(Bleu, BleuFoncé, Noir, "Application fusion 360", e -> onFusion.run(), 16);
        JButton btnVersTuto = BoutonFactory.creerBouton(Bleu,BleuFoncé, Noir, "<html><center>Tutoriel de modélisation <br>3D</center></html>", e -> onTuto.run(), 16);
        JButton btnVersCura = BoutonFactory.creerBouton(Bleu, BleuFoncé, Noir,
                "<html><center>Application d'impression 3D Cura</center></html>",
                e -> onCura.run(),
                16
        );


        add(btnVersFusion);
        add(btnVersTuto);
        add(btnVersCura);
    }
}
