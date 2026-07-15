public class Livraison {
    private Commande commande;
    private Livreur livreur;
    private String dateLivraisonPrevue;
    private String dateLivraisonReelle;
    private String statut; // "en cours", "terminée"
    private String typeLivraison; // "express", "standard"

    // Constructeur
    public Livraison(Commande commande, Livreur livreur, String dateLivraisonPrevue, String typeLivraison) {
        this.commande = commande;
        this.livreur = livreur;
        this.dateLivraisonPrevue = dateLivraisonPrevue;
        this.dateLivraisonReelle = null;
        this.statut = "en cours";
        this.typeLivraison = typeLivraison;
    }

    public Commande getCommande() {
        return commande;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public String getDateLivraisonPrevue() {
        return dateLivraisonPrevue;
    }

    public String getDateLivraisonReelle() {
        return dateLivraisonReelle;
    }

    public String getStatut() {
        return statut;
    }

    public String getTypeLivraison() {
        return typeLivraison;
    }

    public void setDateLivraisonPrevue(String dateLivraisonPrevue) {
        this.dateLivraisonPrevue = dateLivraisonPrevue;
    }

    // Terminer la livraison
    public void terminerLivraison(String dateReelle) {
        this.dateLivraisonReelle = dateReelle;
        this.statut = "terminée";
        this.commande.modifierStatut("livrée");
        System.out.println("Livraison terminée !");
    }

    // Afficher la livraison
    public void afficherLivraison() {
        System.out.println("=== Livraison ===");
        System.out.println("Commande ID : " + commande.getId());
        System.out.println("Client : " + commande.getClient().getPrenom() + " " + commande.getClient().getNom());
        System.out.println("Livreur : " + livreur.getPrenom() + " " + livreur.getNom());
        System.out.println("Type : " + typeLivraison);
        System.out.println("Date prévue : " + dateLivraisonPrevue);
        if (dateLivraisonReelle != null) {
            System.out.println("Date réelle : " + dateLivraisonReelle);
        }
        System.out.println("Statut: " + statut);
    }

    @Override
    public String toString() {
        return "Commande [" + commande.getId() + "] -> " + livreur.getPrenom() + " " + livreur.getNom() + " (" + statut + ")";
    }
}