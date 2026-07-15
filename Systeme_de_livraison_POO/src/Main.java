import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Couleur
    public static final String RESET = "\u001B[0m";  // Pour revenir à la couleur normale
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";

    // Styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    // essai pour clear
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static ServiceLivraison service = new ServiceLivraison();
    private static Scanner scanner = new Scanner(System.in);
    private static int prochainIdClient = 1;
    private static int prochainIdLivreur = 1;
    private static int prochainIdCommande = 1;


    // Scan de la commande du user
    private static int lireEntier() {
        try {
            int valeur = Integer.parseInt(scanner.nextLine());
            return valeur;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: veuillez entrer un nombre!");
            return -1;
        }
    }

    public static void main(String[] args) {
        boolean continuer = true;

        System.out.println(BOLD + RED + "╔════════════════════════════════════════╗");
        System.out.println("║  APPLICATION GESTION DE LIVRAISON      ║");
        System.out.println("╚════════════════════════════════════════╝\n" + RESET);

        while (continuer) {
            afficherMenuPrincipal();
            int choix = lireEntier();

            switch (choix) {
                case 1:
                    //clearScreen();
                    menuClients();
                    break;
                case 2:
                    //clearScreen();   // tentative de clear le terminal
                    menuLivreurs();
                    break;
                case 3:
                    menuCommandes();
                    break;
                case 4:
                    menuLivraisons();
                    break;
                case 5:
                    menuPerformance();
                    break;
                case 6:
                    System.out.println("Merci d'avoir utilisé l'application!");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide!");
            }
        }
        scanner.close();
    }

    private static void afficherMenuPrincipal() {
        System.out.println(PURPLE + BOLD +"\n╔════ MENU PRINCIPAL ═══════╗");
        System.out.println("║ 1. Gestion des clients    ║");
        System.out.println("║ 2. Gestion des livreurs   ║");
        System.out.println("║ 3. Gestion des commandes  ║");
        System.out.println("║ 4. Gestion des livraisons ║");
        System.out.println("║ 5. Performance            ║");
        System.out.println("║ 6. Quitter                ║");
        System.out.println("╚═══════════════════════════╝" +"\n"+ RESET);
        System.out.print(GREEN + UNDERLINE + "Votre choix :"+RESET);
    }

    //menu client
    private static void menuClients() {
        boolean continuer = true;
        while (continuer) {

            System.out.println(BLUE + BOLD +"\n╔════ GESTION CLIENTS ════╗");
            System.out.println("║ 1. Ajouter un client    ║");
            System.out.println("║ 2. Afficher les clients ║");
            System.out.println("║ 3. Rechercher un client ║");
            System.out.println("║ 4. Modifier un client   ║");
            System.out.println("║ 5. Supprimer un client  ║");
            System.out.println("║ 6. Retour               ║");
            System.out.println("╚═════════════════════════╝" + "\n"+ RESET);
            System.out.print(GREEN + UNDERLINE + "Votre choix :"+RESET);
            int choix = lireEntier();

            switch (choix) {
                case 1:
                    ajouterClient();
                    break;
                case 2:
                    service.afficherClients();
                    break;
                case 3:
                    rechercherClient();
                    break;
                case 4:
                    modifierClient();
                    break;
                case 5:
                    supprimerClient();
                    break;
                case 6:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide!");
            }
        }
    }

    private static void ajouterClient() {
        System.out.println("\n--- Ajouter un client ---");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        // 1. Validation de l'Email (uniquement @edu.ece.fr)
        String email;
        while (true) {
            System.out.print("Email : ");
            email = scanner.nextLine();
            if (email.toLowerCase().endsWith("@edu.ece.fr")) {
                break;
            }
            System.out.println("Erreur: L'email doit être une adresse @edu.ece.fr !");
        }

        // 2. Vérification des doublons (Nom + Prénom + Email identiques)
        if (service.clientExisteDeja(nom, prenom, email)) {
            System.out.println("Erreur: Ce client existe déjà avec exactement les mêmes caractéristiques !");
            return; // On arrête la fonction ici
        }

        // 3. Validation du Téléphone (exactement 10 chiffres)
        String telephone;
        while (true) {
            System.out.print("Téléphone (10 chiffres): ");
            telephone = scanner.nextLine();
            if (telephone.matches("\\d{10}")) {
                break;
            }
            System.out.println("Erreur: Le numéro doit contenir exactement 10 chiffres !");
        }

        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();

        // Création et ajout
        Client client = new Client(prochainIdClient++, nom, prenom, telephone, adresse, email);
        service.ajouterClient(client);
    }

    private static void rechercherClient() {
        System.out.println("\n--- Rechercher un client ---");
        System.out.print("Rechercher par (1=ID, 2=Nom) : ");
        int choix = lireEntier();

        if (choix == 1) {
            System.out.print("ID du client : ");
            int id = lireEntier();
            Client client = service.rechercherClientParId(id);
            if (client != null) {
                client.afficherDetails();
            } else {
                System.out.println("Client non trouvé !");
            }
        } else if (choix == 2) {
            System.out.print("Nom du client : ");
            String nom = scanner.nextLine();
            ArrayList<Client> clients = service.rechercherClientParNom(nom);
            if (clients.isEmpty()) {
                System.out.println("Aucun client trouvé avec ce nom !");
            } else {
                for (Client client : clients) {
                    client.afficherDetails();
                }
            }
        }
    }

    private static void modifierClient() {
        System.out.println("\n--- Modifier un client ---");
        System.out.print("ID du client à modifier : ");
        int id = lireEntier();
        Client client = service.rechercherClientParId(id);

        if (client == null) {
            System.out.println("Client non trouvé !");
            return;
        }

        System.out.println("Que voulez-vous modifier ?");
        System.out.println("1. Nom");
        System.out.println("2. Prénom");
        System.out.println("3. Téléphone");
        System.out.println("4. Adresse");
        System.out.println("5. Email");
        System.out.print("Votre choix : ");
        int choix = lireEntier();

        switch (choix) {
            case 1:
                System.out.print("Nouveau nom : ");
                client.setNom(scanner.nextLine());
                break;
            case 2:
                System.out.print("Nouveau prénom : ");
                client.setPrenom(scanner.nextLine());
                break;
            case 3:
                System.out.print("Nouveau téléphone : ");
                client.setTelephone(scanner.nextLine());
                break;
            case 4:
                System.out.print("Nouvelle adresse : ");
                client.setAdresse(scanner.nextLine());
                break;
            case 5:
                System.out.print("Nouvel email : ");
                client.setEmail(scanner.nextLine());
                break;
            default:
                System.out.println("Choix invalide !");
                return;
        }
        System.out.println("Client modifié avec succès !");
    }

    private static void supprimerClient() {
        System.out.println("\n--- Supprimer un client ---");
        System.out.print("ID du client à supprimer : ");
        int id = lireEntier();
        service.supprimerClient(id);
    }

    private static void menuLivreurs() {
        boolean continuer = true;
        while (continuer) {
            System.out.println(BLUE + BOLD +"\n╔════ GESTION LIVREURS ════╗");
            System.out.println("║ 1. Ajouter un livreur    ║");
            System.out.println("║ 2. Afficher les livreurs ║");
            System.out.println("║ 3. Rechercher un livreur ║");
            System.out.println("║ 4. Modifier un livreur   ║");
            System.out.println("║ 5. Supprimer un livreur  ║");
            System.out.println("║ 6. Retour                ║");
            System.out.println("╚══════════════════════════╝" + "\n" + RESET);
            System.out.print(GREEN + UNDERLINE + "Votre choix :" + RESET);
            int choix = lireEntier();

            switch (choix) {
                case 1:
                    ajouterLivreur();
                    break;
                case 2:
                    service.afficherLivreurs();
                    break;
                case 3:
                    rechercherLivreur();
                    break;
                case 4:
                    modifierLivreur();
                    break;
                case 5:
                    supprimerLivreur();
                    break;
                case 6:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private static void ajouterLivreur() {
        System.out.println("\n--- Ajouter un livreur ---");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();
        System.out.print("Véhicule : ");
        String vehicule = scanner.nextLine();

        Livreur livreur = new Livreur(prochainIdLivreur++, nom, prenom, telephone, vehicule);
        service.ajouterLivreur(livreur);
    }

    private static void rechercherLivreur() {
        System.out.println("\n--- Rechercher un livreur ---");
        System.out.print("Rechercher par (1=ID, 2=Nom) : ");
        int choix = lireEntier();

        if (choix == 1) {
            System.out.print("ID du livreur : ");
            int id = lireEntier();
            Livreur livreur = service.rechercherLivreurParId(id);
            if (livreur != null) {
                livreur.afficherDetails();
            } else {
                System.out.println("Livreur non trouvé !");
            }
        } else if (choix == 2) {
            System.out.print("Nom du livreur : ");
            String nom = scanner.nextLine();
            ArrayList<Livreur> livreurs = service.rechercherLivreurParNom(nom);
            if (livreurs.isEmpty()) {
                System.out.println("Aucun livreur trouvé avec ce nom !");
            } else {
                for (Livreur livreur : livreurs) {
                    livreur.afficherDetails();
                }
            }
        }
    }

    private static void modifierLivreur() {
        System.out.println("\n--- Modifier un livreur ---");
        System.out.print("ID du livreur à modifier : ");
        int id = lireEntier();
        Livreur livreur = service.rechercherLivreurParId(id);

        if (livreur == null) {
            System.out.println("Livreur non trouvé !");
            return;
        }

        System.out.println("Que voulez-vous modifier ?");
        System.out.println("1. Nom");
        System.out.println("2. Prénom");
        System.out.println("3. Téléphone");
        System.out.println("4. Véhicule");
        System.out.print("Votre choix : ");
        int choix = lireEntier();

        switch (choix) {
            case 1:
                System.out.print("Nouveau nom : ");
                livreur.setNom(scanner.nextLine());
                break;
            case 2:
                System.out.print("Nouveau prénom : ");
                livreur.setPrenom(scanner.nextLine());
                break;
            case 3:
                System.out.print("Nouveau téléphone : ");
                livreur.setTelephone(scanner.nextLine());
                break;
            case 4:
                System.out.print("Nouveau véhicule : ");
                livreur.setVehicule(scanner.nextLine());
                break;
            default:
                System.out.println("Choix invalide !");
                return;
        }
        System.out.println("Livreur modifié avec succès !");
    }

    private static void supprimerLivreur() {
        System.out.println("\n--- Supprimer un livreur ---");
        System.out.print("ID du livreur à supprimer : ");
        int id = lireEntier();
        service.supprimerLivreur(id);
    }

    // menu commande
    private static void menuCommandes() {
        boolean continuer = true;
        while (continuer) {
            System.out.println(BLUE + BOLD +"\n╔═════ GESTION COMMANDES ═════╗");
            System.out.println("║ 1. Créer une commande       ║");
            System.out.println("║ 2. Afficher les commandes   ║");
            System.out.println("║ 3. Rechercher une commande  ║");
            System.out.println("║ 4. Modifier statut          ║");
            System.out.println("║ 5. Supprimer une commande   ║");
            System.out.println("║ 6. Afficher cmdes client    ║");
            System.out.println("║ 7. Retour                   ║");
            System.out.println("╚═════════════════════════════╝" +"\n" + RESET);
            System.out.print(GREEN + UNDERLINE + "Votre choix :" + RESET);
            int choix = lireEntier();

            switch (choix) {
                case 1:
                    creerCommande();
                    break;
                case 2:
                    service.afficherCommandes();
                    break;
                case 3:
                    rechercherCommande();
                    break;
                case 4:
                    modifierStatutCommande();
                    break;
                case 5:
                    supprimerCommande();
                    break;
                case 6:
                    afficherCommandesClient();
                    break;
                case 7:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private static void creerCommande() {
        System.out.println("\n--- Créer une commande ---");
        service.afficherClients();
        System.out.print("ID du client : ");
        int idClient = lireEntier();
        Client client = service.rechercherClientParId(idClient);

        if (client == null) {
            System.out.println("Client non trouvé !");
            return;
        }

        System.out.print("Description de la commande : ");
        String description = scanner.nextLine();
        System.out.print("Date (JJ/MM/YYYY) : ");
        String date = scanner.nextLine();

        Commande commande = new Commande(prochainIdCommande++, client, description, date);
        service.creerCommande(commande);
    }

    private static void rechercherCommande() {
        System.out.println("\n--- Rechercher une commande ---");
        System.out.print("ID de la commande : ");
        int id = lireEntier();
        Commande commande = service.rechercherCommandeParId(id);

        if (commande != null) {
            commande.afficherCommande();
        } else {
            System.out.println("Commande non trouvée !");
        }
    }

    private static void modifierStatutCommande() {
        System.out.println("\n--- Modifier le statut ---");
        System.out.print("ID de la commande : ");
        int id = lireEntier();
        Commande commande = service.rechercherCommandeParId(id);

        if (commande == null) {
            System.out.println("Commande non trouvée !");
            return;
        }

        System.out.println("Nouveaux statuts :");
        System.out.println("1. en attente");
        System.out.println("2. en préparation");
        System.out.println("3. en livraison");
        System.out.println("4. livrée");
        System.out.print("Votre choix: ");
        int choix = lireEntier();

        String[] statuts = {"", "en attente", "en préparation", "en livraison", "livrée"};
        if (choix >= 1 && choix <= 4) {
            commande.modifierStatut(statuts[choix]);
        } else {
            System.out.println("Choix invalide !");
        }
    }

    private static void supprimerCommande() {
        System.out.println("\n--- Supprimer une commande ---");
        System.out.print("ID de la commande à supprimer : ");
        int id = lireEntier();
        service.supprimerCommande(id);
    }

    private static void afficherCommandesClient() {
        System.out.println("\n--- Commandes d'un client ---");
        service.afficherClients();
        System.out.print("ID du client : ");
        int id = lireEntier();
        ArrayList<Commande> commandes = service.afficherCommandesClient(id);

        if (commandes.isEmpty()) {
            System.out.println("Aucune commande pour ce client");
            return;
        }

        System.out.println("\n=== COMMANDES ===");
        for (Commande commande : commandes) {
            System.out.println(commande);
        }
    }

    // menu livraison
    private static void menuLivraisons() {
        boolean continuer = true;
        while (continuer) {
            System.out.println(BLUE + BOLD +"\n╔════ GESTION LIVRAISONS ════╗");
            System.out.println("║ 1. Créer une livraison     ║");
            System.out.println("║ 2. Afficher les livraisons ║");
            System.out.println("║ 3. Livraisons en cours     ║");
            System.out.println("║ 4. Livraisons terminées    ║");
            System.out.println("║ 5. Terminer une livraison  ║");
            System.out.println("║ 6. Retour                  ║");
            System.out.println("╚════════════════════════════╝" + "\n" + RESET);
            System.out.print(GREEN + UNDERLINE + "Votre choix :" + RESET);
            int choix = lireEntier();

            switch (choix) {
                case 1:
                    creerLivraison();
                    break;
                case 2:
                    service.afficherLivraisons();
                    break;
                case 3:
                    service.afficherLivraisonsEnCours();
                    break;
                case 4:
                    service.afficherLivraisonsTerminees();
                    break;
                case 5:
                    terminerLivraison();
                    break;
                case 6:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private static void creerLivraison() {
        System.out.println("\n--- Créer une livraison ---");
        service.afficherCommandes();
        System.out.print("ID de la commande : ");
        int idCommande = lireEntier();
        Commande commande = service.rechercherCommandeParId(idCommande);

        if (commande == null) {
            System.out.println("Commande non trouvée !");
            return;
        }

        service.afficherLivreurs();
        System.out.print("ID du livreur : ");
        int idLivreur = lireEntier();
        Livreur livreur = service.rechercherLivreurParId(idLivreur);

        if (livreur == null) {
            System.out.println("Livreur non trouvé !");
            return;
        }

        System.out.print("Date de livraison prévue (JJ/MM/YYYY) : ");
        String date = scanner.nextLine();
        System.out.println("Type de livraison : (1=Express, 2=Standard)");
        System.out.print("Votre choix : ");
        int choixType = lireEntier();
        String type = (choixType == 1) ? "express" : "standard";

        Livraison livraison = new Livraison(commande, livreur, date, type);
        service.affecterLivraison(livraison);
    }

    private static void terminerLivraison() {
        System.out.println("\n--- Terminer une livraison ---");
        service.afficherLivraisonsEnCours();
        System.out.print("ID de la commande : ");
        int idCommande = lireEntier();

        Livraison livraisonTerminee = null;
        for (Livraison livraison : service.getListeLivraisons()) {
            if (livraison.getCommande().getId() == idCommande &&
                    livraison.getStatut().equals("en cours")) {
                livraisonTerminee = livraison;
                break;
            }
        }

        if (livraisonTerminee == null) {
            System.out.println("Livraison non trouvée !");
            return;
        }

        System.out.print("Date de livraison réelle (JJ/MM/YYYY) : ");
        String dateReelle = scanner.nextLine();
        livraisonTerminee.terminerLivraison(dateReelle);
    }

    // menu perf
    private static void menuPerformance() {
        boolean continuer = true;
        while (continuer) {
            System.out.println(BLUE + BOLD +"\n╔═════ PERFORMANCES ═══════╗");
            System.out.println("║ 1. Commandes livrées     ║");
            System.out.println("║ 2. Livreur le plus actif ║");
            System.out.println("║ 3. Stats des livreur     ║");
            System.out.println("║ 4. Trier clients         ║");
            System.out.println("║ 5. Trier commandes       ║");
            System.out.println("║ 6. Retour                ║");
            System.out.println("╚══════════════════════════╝" + "\n" + RESET);
            System.out.print(GREEN + UNDERLINE + "Votre choix :" + RESET);
            int choix = lireEntier();

            switch (choix) {
                case 1:
                    System.out.println("\nNombre de commandes livrées : " + service.getCommandesLivrees());
                    break;
                case 2:
                    Livreur livreurActif = service.getLivreurPlusActif();
                    if (livreurActif != null) {
                        System.out.println("\nLivreur le plus actif : " + livreurActif.getPrenom() +
                                " " + livreurActif.getNom());
                    }
                    break;
                case 3:
                    service.afficherStatistiquesLivreurs();
                    break;
                case 4:
                    service.trierClientsParNom();
                    service.afficherClients();
                    break;
                case 5:
                    service.trierCommandesParDate();
                    service.afficherCommandes();
                    break;
                case 6:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
}