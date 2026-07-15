public class Commande {
    private int id;
    private Client client;
    private String description;
    private String dateCommande;
    private String statut; // "en attente", "en préparation", "en livraison", "livrée"

    // Constructeur
    public Commande(int id, Client client, String description, String dateCommande) {
        this.id = id;
        this.client = client;
        this.description = description;
        this.dateCommande = dateCommande;
        this.statut = "en attente";
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    // Modifier le statut de la commande
    public void modifierStatut(String nouveauStatut) {
        String[] statutsValides = {"en attente", "en préparation", "en livraison", "livrée"};
        boolean valide = false;
        for (String s : statutsValides) {
            if (s.equals(nouveauStatut)) {
                valide = true;
                break;
            }
        }
        if (valide) {
            this.statut = nouveauStatut;
            System.out.println("Statut modifié à : " + nouveauStatut);
        } else {
            System.out.println("Statut invalide !");
        }
    }

    // Afficher la commande
    public void afficherCommande() {
        System.out.println("=== Commande ===");
        System.out.println("ID : " + id);
        System.out.println("Client : " + client.getPrenom() + " " + client.getNom());
        System.out.println("Description : " + description);
        System.out.println("Date : " + dateCommande);
        System.out.println("Statut : " + statut);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + client.getPrenom() + " " + client.getNom() + " - " + description + " (" + statut + ")";
    }
}