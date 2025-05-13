import javax.swing.*;
import java.awt.*;

public class TutoPage extends PagePanel {
    private JButton btnRetourFusion, btnVersCura;

    public TutoPage(int width, int height, Runnable actionRetourFusion, Runnable actionVersCura) {
        super("Tuto");
        setLayout(null);

        btnRetourFusion = BoutonFactory.creerBouton("Fusion", e -> actionRetourFusion.run(), 11);
        btnVersCura = BoutonFactory.creerBouton("Cura", e -> actionVersCura.run(), 11);

        add(btnRetourFusion);
        add(btnVersCura);
    }

    public void repositionner(int width, int height) {
        positionTitre(width, height);
        btnRetourFusion.setBounds(20, height - 70, 80, 20);
        btnVersCura.setBounds(width - 120, height - 70, 80, 20);
    }
}
