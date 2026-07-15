import java.util.ArrayList;

public class ServiceLivraison {
    private ArrayList<Client> listeClients;
    private ArrayList<Livreur> listeLivreurs;
    private ArrayList<Commande> listeCommandes;
    private ArrayList<Livraison> listeLivraisons;

    // Constructeur parametres avec des list
    public ServiceLivraison() {
        this.listeClients = new ArrayList<>();
        this.listeLivreurs = new ArrayList<>();
        this.listeCommandes = new ArrayList<>();
        this.listeLivraisons = new ArrayList<>();
    }

    // gestion clienst
    public void ajouterClient(Client client) {
        listeClients.add(client);
        System.out.println("Client ajouté !");
    }

    public boolean clientExisteDeja(String nom, String prenom, String email) {
        for (Client c : listeClients) {
            if (c.getNom().equalsIgnoreCase(nom) &&
                    c.getPrenom().equalsIgnoreCase(prenom) &&
                    c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public void supprimerClient(int idClient) {
        for (int i = 0; i < listeClients.size(); i++) {
            if (listeClients.get(i).getId() == idClient) {
                listeClients.remove(i);
                System.out.println("Client supprimé !");
                return;
            }
        }
        System.out.println("Client non trouvé !");
    }

    public Client rechercherClientParId(int idClient) {
        for (Client client : listeClients) {
            if (client.getId() == idClient) {
                return client;
            }
        }
        return null;
    }

    public ArrayList<Client> rechercherClientParNom(String nom) {
        ArrayList<Client> resultats = new ArrayList<>();
        for (Client client : listeClients) {
            if (client.getNom().equalsIgnoreCase(nom)) {
                resultats.add(client);
            }
        }
        return resultats;
    }

    public void afficherClients() {
        if (listeClients.isEmpty()) {
            System.out.println("Aucun client enregistre");
            return;
        }
        System.out.println("\n=== LISTE DES CLIENTS ===");
        for (Client client : listeClients) {
            System.out.println(client);
        }
        System.out.println();
    }

    public ArrayList<Client> getListeClients() {
        return listeClients;
    }

    // gestion des livreurs
    public void ajouterLivreur(Livreur livreur) {
        listeLivreurs.add(livreur);
        System.out.println("Livreur ajouté !");
    }

    public void supprimerLivreur(int idLivreur) {
        for (int i = 0; i < listeLivreurs.size(); i++) {
            if (listeLivreurs.get(i).getId() == idLivreur) {
                listeLivreurs.remove(i);
                System.out.println("Livreur supprimé !");
                return;
            }
        }
        System.out.println("Livreur non trouvé !");
    }

    public Livreur rechercherLivreurParId(int idLivreur) {
        for (Livreur livreur : listeLivreurs) {
            if (livreur.getId() == idLivreur) {
                return livreur;
            }
        }
        return null;
    }

    public ArrayList<Livreur> rechercherLivreurParNom(String nom) {
        ArrayList<Livreur> resultats = new ArrayList<>();
        for (Livreur livreur : listeLivreurs) {
            if (livreur.getNom().equalsIgnoreCase(nom)) {
                resultats.add(livreur);
            }
        }
        return resultats;
    }

    public void afficherLivreurs() {
        if (listeLivreurs.isEmpty()) {
            System.out.println("Aucun livreur enregistré");
            return;
        }
        System.out.println("\n=== LISTE DES LIVREURS ===");
        for (Livreur livreur : listeLivreurs) {
            System.out.println(livreur);
        }
        System.out.println();
    }

    public ArrayList<Livreur> getListeLivreurs() {
        return listeLivreurs;
    }

    // gestion des commande
    public void creerCommande(Commande commande) {
        listeCommandes.add(commande);
        System.out.println("Commande créée !");
    }

    public void supprimerCommande(int idCommande) {
        for (int i = 0; i < listeCommandes.size(); i++) {
            if (listeCommandes.get(i).getId() == idCommande) {
                listeCommandes.remove(i);
                System.out.println("Commande supprimée !");
                return;
            }
        }
        System.out.println("Commande non trouvée !");
    }

    public Commande rechercherCommandeParId(int idCommande) {
        for (Commande commande : listeCommandes) {
            if (commande.getId() == idCommande) {
                return commande;
            }
        }
        return null;
    }

    public ArrayList<Commande> afficherCommandesClient(int idClient) {
        ArrayList<Commande> commandesClient = new ArrayList<>();
        for (Commande commande : listeCommandes) {
            if (commande.getClient().getId() == idClient) {
                commandesClient.add(commande);
            }
        }
        return commandesClient;
    }

    public void afficherCommandes() {
        if (listeCommandes.isEmpty()) {
            System.out.println("Aucune commande enregistrée");
            return;
        }
        System.out.println("\n=== LISTE DES COMMANDES ===");
        for (Commande commande : listeCommandes) {
            System.out.println(commande);
        }
        System.out.println();
    }

    public ArrayList<Commande> getListeCommandes() {
        return listeCommandes;
    }

    //  gestion livraison
    public void affecterLivraison(Livraison livraison) {
        listeLivraisons.add(livraison);
        livraison.getCommande().modifierStatut("en livraison");
        System.out.println("Livraison affectée avec succès !");
    }

    public void afficherLivraisons() {
        if (listeLivraisons.isEmpty()) {
            System.out.println("Aucune livraison enregistrée");
            return;
        }
        System.out.println("\n=== LISTE DES LIVRAISONS ===");
        for (Livraison livraison : listeLivraisons) {
            System.out.println(livraison);
        }
        System.out.println();
    }

    public void afficherLivraisonsEnCours() {
        ArrayList<Livraison> enCours = new ArrayList<>();
        for (Livraison livraison : listeLivraisons) {
            if (livraison.getStatut().equals("en cours")) {
                enCours.add(livraison);
            }
        }
        if (enCours.isEmpty()) {
            System.out.println("Aucune livraison en cours");
            return;
        }
        System.out.println("\n=== LIVRAISONS EN COURS ===");
        for (Livraison livraison : enCours) {
            System.out.println(livraison);
        }
        System.out.println();
    }

    public void afficherLivraisonsTerminees() {
        ArrayList<Livraison> terminees = new ArrayList<>();
        for (Livraison livraison : listeLivraisons) {
            if (livraison.getStatut().equals("terminée")) {
                terminees.add(livraison);
            }
        }
        if (terminees.isEmpty()) {
            System.out.println("Aucune livraison terminée");
            return;
        }
        System.out.println("\n=== LIVRAISONS TERMINÉES ===");
        for (Livraison livraison : terminees) {
            System.out.println(livraison);
        }
        System.out.println();
    }

    public ArrayList<Livraison> getListeLivraisons() {
        return listeLivraisons;
    }

    //  fonction avancees
    public int getCommandesLivrees() {
        int compteur = 0;
        for (Commande commande : listeCommandes) {
            if (commande.getStatut().equals("livrée")) {
                compteur++;
            }
        }
        return compteur;
    }

    public void afficherStatistiquesLivreurs() {
        System.out.println("\n=== STATISTIQUES LIVREURS ===");
        for (Livreur livreur : listeLivreurs) {
            int compteur = 0;
            for (Livraison livraison : listeLivraisons) {
                if (livraison.getLivreur().getId() == livreur.getId()) {
                    compteur++;
                }
            }
            System.out.println(livreur.getPrenom() + " " + livreur.getNom() + ": " + compteur + " livraisons");
        }
        System.out.println();
    }

    public Livreur getLivreurPlusActif() {
        if (listeLivreurs.isEmpty()) {
            return null;
        }
        Livreur livreurMax = listeLivreurs.get(0);
        int maxLivraisons = 0;

        for (Livreur livreur : listeLivreurs) {
            int compteur = 0;
            for (Livraison livraison : listeLivraisons) {
                if (livraison.getLivreur().getId() == livreur.getId()) {
                    compteur++;
                }
            }
            if (compteur > maxLivraisons) {
                maxLivraisons = compteur;
                livreurMax = livreur;
            }
        }
        return livreurMax;
    }

    public void trierCommandesParDate() {

        for (int i = 0; i < listeCommandes.size() - 1; i++) {
            for (int j = 0; j < listeCommandes.size() - i - 1; j++) {
                if (listeCommandes.get(j).getDateCommande().compareTo(
                        listeCommandes.get(j + 1).getDateCommande()) > 0) {
                    Commande temp = listeCommandes.get(j);
                    listeCommandes.set(j, listeCommandes.get(j + 1));
                    listeCommandes.set(j + 1, temp);
                }
            }
        }
        System.out.println("Commandes triées par date !");
    }

    public void trierClientsParNom() {

        for (int i = 0; i < listeClients.size() - 1; i++) {
            for (int j = 0; j < listeClients.size() - i - 1; j++) {
                if (listeClients.get(j).getNom().compareTo(
                        listeClients.get(j + 1).getNom()) > 0) {
                    Client temp = listeClients.get(j);
                    listeClients.set(j, listeClients.get(j + 1));
                    listeClients.set(j + 1, temp);
                }
            }
        }
        System.out.println("Clients triés par nom !");
    }
}