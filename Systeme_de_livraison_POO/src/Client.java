public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String email;

    // Constructeur
    public Client(int id, String nom, String prenom, String telephone, String adresse, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
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

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Afficher les détails du client
    public void afficherDetails() {
        System.out.println("=== Client ===");
        System.out.println("ID : " + id);
        System.out.println("Nom : " + nom);
        System.out.println("Prénom : " + prenom);
        System.out.println("Téléphone : " + telephone);
        System.out.println("Adresse : " + adresse);
        System.out.println("Email : " + email);
    }

    @Override
    public String toString() {
        return  "Client :  [" + id + "] " + prenom + " " + nom + " - " + adresse + " - " + telephone + " - " + email;
    }
}