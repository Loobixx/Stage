package src;

public class Texte {
    public static String getTexteHTMLFusion() {
        return """
        <html>
            <body style='font-family: Arial; font-size: 12px; line-height: 1.4;'>
                <h2 style='color: #2a7ae2; font-size: 16px; margin-bottom: 10px;'>Fusion 360</h2>

                <p>
                    <b>Fusion 360</b> est un logiciel de <b>conception assistée par ordinateur (CAO)</b>, 
                    de <b>fabrication assistée par ordinateur (FAO)</b> et d’<b>ingénierie assistée par ordinateur (IAO)</b> 
                    développé par Autodesk. Il offre une plateforme unifiée de design, simulation et production.
                </p>

                <h3 style='color: #4a90e2; font-size: 16px;'>Fonctionnalités clés</h3>
                <ul>
                    <li>Modélisation paramétrique et surfacique</li>
                    <li>Assemblages complexes avec gestion des contraintes</li>
                    <li>Simulation mécanique et thermique</li>
                    <li>Création de trajectoires d’usinage pour CNC</li>
                    <li>Rendu photo-réaliste intégré</li>
                </ul>

                <p>
                    Grâce à son interface intuitive et à ses outils collaboratifs dans le cloud, Fusion 360 
                    permet aux équipes réparties géographiquement de travailler simultanément sur un même projet.
                </p>

                <h3 style='color: #4a90e2; font-size: 16px;'>Cas d’usage</h3>
                <p>On l’utilise notamment pour :</p>
                <ol>
                    <li>Concevoir des prototypes de produits industriels</li>
                    <li>Imaginer des pièces mécaniques sur mesure</li>
                    <li>Développer des objets connectés (IoT) avec boîtiers imprimables</li>
                    <li>Créer des outils d’aide à la maintenance</li>
                </ol>

                <h3 style='color: #4a90e2; font-size: 16px;'>Avantages</h3>
                <ul>
                    <li>Flux de travail tout-en-un : CAO, FAO, IAO</li>
                    <li>Mises à jour régulières et innovations constantes</li>
                    <li>Accès multiplateforme (Windows, macOS, mobile)</li>
                    <li>Bibliothèques de composants et de matériaux fournies</li>
                </ul>

                <h3 style='color: #4a90e2; font-size: 16px;'>Ressources pour débuter</h3>
                <p>
                    Pour aller plus loin, consultez :
                    <ul>
                        <li>Les tutoriels officiels sur le site Autodesk</li>
                        <li>Les cours gratuits disponibles sur YouTube</li>
                        <li>Les forums de la communauté pour l’entraide</li>
                        <li>La documentation API pour automatiser vos flux</li>
                    </ul>
                </p>

                <p style='margin-top:16px; font-style: italic;'>
                    En résumé, Fusion 360 se positionne comme la solution idéale pour qui cherche à 
                    passer rapidement du concept à la production, tout en bénéficiant d’outils puissants 
                    de simulation et de collaboration.
                </p>
            </body>
        </html>
    """;
    }
}
