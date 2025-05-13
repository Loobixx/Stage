import javax.swing.*;
import java.awt.*;

public class CuraPage extends PagePanel {
    private JButton btnRetourTuto;

    public CuraPage(int width, int height, Runnable actionRetourTuto) {
        super("Merci et à bientôt !");
        setLayout(null);

        btnRetourTuto = BoutonFactory.creerBouton("Retour", e -> actionRetourTuto.run(), 11);
        add(btnRetourTuto);
    }

    public void repositionner(int width, int height) {
        positionTitre(width, height);
        btnRetourTuto.setBounds(width / 2 - 130, height * 70 / 100, 120, 40);
    }
}
