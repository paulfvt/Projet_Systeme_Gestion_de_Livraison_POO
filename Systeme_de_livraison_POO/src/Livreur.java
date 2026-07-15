public class Livreur {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String vehicule;

    // Constructeur
    public Livreur(int id, String nom, String prenom, String telephone, String vehicule) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.vehicule = vehicule;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    // Afficher les détails du livreur
    public void afficherDetails() {
        System.out.println("=== Livreur ===");
        System.out.println("ID : " + id);
        System.out.println("Nom : " + nom);
        System.out.println("Prénom : " + prenom);
        System.out.println("Téléphone : " + telephone);
        System.out.println("Véhicule : " + vehicule);
    }

    @Override
    public String toString() {
        return "Livreur :  [" + id + "] " + prenom + " " + nom + " - " + telephone  + " (" + vehicule + ")";
    }
}