package vues;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import jpaDao.JpaDaoAgent;
import jpaDao.JpaDaoBienProprietaire;
import jpaDao.JpaDaoProprietaire;
import jpaDao.JpaDaoTiers;
import metier.Agent;
import metier.BienProprietaire;
import metier.Proprietaire;
import metier.Tiers;
import utils.ButtonsUtil;

import java.util.Date;
import java.util.List;

public class VueTiers {

    private Stage stage;
    private Scene scene;

    public VueTiers(Stage stage, Tiers tiersConnecte) {
        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Utilisateurs");
        titre.setStyle("-fx-font: 30 arial;-fx-text-fill: #5693bd;-fx-padding: 30px;");

        Button boutonGoBack = ButtonsUtil.createGoBackButton(this.stage, tiersConnecte);
        HBox hboxGoback = ButtonsUtil.createStyleButton(boutonGoBack, "vert");
        VBox containerGoback = new VBox();
        containerGoback.setSpacing(15);
        containerGoback.getChildren().add(boutonGoBack);

        //Récupération des agents
        JpaDaoAgent jpaAgent = new JpaDaoAgent();
        List<Agent> agents = jpaAgent.findAll();

        ObservableList<Tiers> listeAgents = FXCollections.observableArrayList();

        for (Agent agent : agents) {
            listeAgents.add(agent.getNoTiers());
        }

        TableView tableAgents = new TableView();
        tableAgents.setMaxWidth(1145);

        Label titreAgents = new Label("Agents :");
        titreAgents.setStyle("-fx-font: 30 arial;-fx-padding: 30px;");

        //Configuration des colonnes de la table agents
        TableColumn colonneNumero = new TableColumn("Numéro tiers");
        colonneNumero.setCellValueFactory(new PropertyValueFactory<Tiers, Integer>("id"));
        tableAgents.resizeColumn(colonneNumero, 20);

        TableColumn colonneNom = new TableColumn("Nom");
        colonneNom.setCellValueFactory(new PropertyValueFactory<Tiers, String>("nom"));
        tableAgents.resizeColumn(colonneNom, 50);

        TableColumn colonnePrenom = new TableColumn("Prenom");
        colonnePrenom.setCellValueFactory(new PropertyValueFactory<Tiers, String>("prenom"));
        tableAgents.resizeColumn(colonnePrenom, 50);

        TableColumn colonneNaissance = new TableColumn("Date de naissance");
        colonneNaissance.setCellValueFactory(new PropertyValueFactory<Tiers, Date>("dateDeNaissance"));
        tableAgents.resizeColumn(colonneNaissance, 50);

        TableColumn colonneSecurite = new TableColumn("N° sécurité sociale");
        colonneSecurite.setCellValueFactory(new PropertyValueFactory<Tiers, String>("noSs"));
        tableAgents.resizeColumn(colonneSecurite, 50);

        TableColumn colonneRib = new TableColumn("RIB");
        colonneRib.setCellValueFactory(new PropertyValueFactory<Tiers, String>("rib"));
        tableAgents.resizeColumn(colonneRib, 110);

        TableColumn colonneEmail = new TableColumn("Email");
        colonneEmail.setCellValueFactory(new PropertyValueFactory<Tiers, String>("email"));
        tableAgents.resizeColumn(colonneEmail, 110);

        TableColumn colonneSupprimer = new TableColumn("Supprimer");
        Callback<TableColumn<Tiers, Void>, TableCell<Tiers, Void>> cellFactory = new Callback<TableColumn<Tiers, Void>, TableCell<Tiers, Void>>() {
            @Override
            public TableCell<Tiers, Void> call(final TableColumn<Tiers, Void> param) {
                final TableCell<Tiers, Void> cell = new TableCell<Tiers, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {

                        btn.setOnAction((event) -> {
                            // Afficher une boîte de dialogue de confirmation
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText("Voulez-vous vraiment Supprimer cet agent?");
                            alert.setContentText("Cliquez sur OK pour confirmer.");

                            // Attendre la réponse de l'utilisateur
                            alert.showAndWait().ifPresent(response -> {
                                Tiers tier = getTableView().getItems().get(getIndex());

                                if (tier != null) {
                                    JpaDaoAgent jpaagent = new JpaDaoAgent();
                                    Agent agent = jpaagent.findByNoTiers(tier.getId());
                                    if (agent != null) {
                                        jpaagent.delete(agent);

                                        JpaDaoTiers jpatiers = new JpaDaoTiers();

                                        Tiers tier2 = jpatiers.find(Tiers.class, tier.getId());
                                        if (tier2 != null) {
                                            jpatiers.delete(tier2);
                                        }
                                        new VueTiers(stage, tiersConnecte);
                                    }
                                }
                            });
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colonneSupprimer.setCellFactory(cellFactory);
        colonneSupprimer.setStyle("-fx-alignment: CENTER;");

        tableAgents.resizeColumn(colonneSupprimer, 60);

        tableAgents.setItems(listeAgents);
        tableAgents.getColumns().addAll(colonneNumero, colonneNom, colonnePrenom, colonneNaissance, colonneSecurite, colonneRib, colonneEmail, colonneSupprimer);

        Button boutonNouvelAgent = ButtonsUtil.createNouveauTiersButton(this.stage, "Agent", tiersConnecte);
        HBox hboxNouvelAgent = ButtonsUtil.createStyleButton(boutonNouvelAgent, "vert");

        //Récupération des proprietaires
        JpaDaoProprietaire jpaProprietaire = new JpaDaoProprietaire();
        List<Proprietaire> proprietaires = jpaProprietaire.findAll();

        //Table proprietaires
        ObservableList<Tiers> listeProprietaires = FXCollections.observableArrayList();

        for (Proprietaire proprietaire : proprietaires) {
            listeProprietaires.add(proprietaire.getNoTiers());
        }

        TableView tableProprietaires = new TableView();
        tableProprietaires.setMaxWidth(1145);

        Label titreProprietaires = new Label("Propriétaires :");
        titreProprietaires.setStyle("-fx-font: 30 arial;-fx-padding: 30px;");

        //Configuration des colonnes de la table propriétaire
        TableColumn colonneNumero1 = new TableColumn("Numéro tiers");
        colonneNumero1.setCellValueFactory(new PropertyValueFactory<Tiers, Integer>("id"));
        tableProprietaires.resizeColumn(colonneNumero1, 20);

        TableColumn colonneNom1 = new TableColumn("Nom");
        colonneNom1.setCellValueFactory(new PropertyValueFactory<Tiers, String>("nom"));
        tableProprietaires.resizeColumn(colonneNom1, 50);

        TableColumn colonnePrenom1 = new TableColumn("Prenom");
        colonnePrenom1.setCellValueFactory(new PropertyValueFactory<Tiers, String>("prenom"));
        tableProprietaires.resizeColumn(colonnePrenom1, 50);

        TableColumn colonneNaissance1 = new TableColumn("Date de naissance");
        colonneNaissance1.setCellValueFactory(new PropertyValueFactory<Tiers, Date>("dateDeNaissance"));
        tableProprietaires.resizeColumn(colonneNaissance1, 50);

        TableColumn colonneSecurite1 = new TableColumn("N° sécurité sociale");
        colonneSecurite1.setCellValueFactory(new PropertyValueFactory<Tiers, String>("noSs"));
        tableProprietaires.resizeColumn(colonneSecurite1, 50);

        TableColumn colonneRib1 = new TableColumn("RIB");
        colonneRib1.setCellValueFactory(new PropertyValueFactory<Tiers, String>("rib"));
        tableProprietaires.resizeColumn(colonneRib1, 110);

        TableColumn colonneEmail1 = new TableColumn("Email");
        colonneEmail1.setCellValueFactory(new PropertyValueFactory<Tiers, String>("email"));
        tableProprietaires.resizeColumn(colonneEmail1, 110);

        TableColumn colonneSupprimer2 = new TableColumn("Supprimer");
        Callback<TableColumn<Tiers, Void>, TableCell<Tiers, Void>> cellFactory2 = new Callback<TableColumn<Tiers, Void>, TableCell<Tiers, Void>>() {
            @Override
            public TableCell<Tiers, Void> call(final TableColumn<Tiers, Void> param) {
                final TableCell<Tiers, Void> cell = new TableCell<Tiers, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((event) -> {
                            // Afficher une boîte de dialogue de confirmation
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText("Voulez-vous vraiment Supprimer cet agent?");
                            alert.setContentText("Cliquez sur OK pour confirmer.");

                            // Attendre la réponse de l'utilisateur
                            alert.showAndWait().ifPresent(response -> {
                                Tiers tier = getTableView().getItems().get(getIndex());
                                if (tier != null) {

                                    Proprietaire proprietaire = jpaProprietaire.findByNoTiers(tier.getId());

                                    if (proprietaire != null) {
                                        JpaDaoBienProprietaire jpaBienProprietaire = new JpaDaoBienProprietaire();
                                        List<BienProprietaire> bienProprietaire = jpaBienProprietaire.findAllBienProprioByProprietaire(proprietaire);
                                        if (bienProprietaire != null) {
                                            for (BienProprietaire selectBien : bienProprietaire) {
                                                if (selectBien != null) {
                                                    jpaBienProprietaire.delete(selectBien);
                                                }
                                            }
                                        }
                                        JpaDaoProprietaire jpaProprietaire2 = new JpaDaoProprietaire();
                                        Proprietaire proprietaire2 = jpaProprietaire2.findByNoTiers(tier.getId());
                                        jpaProprietaire2.delete(proprietaire2);

                                    }

                                    JpaDaoTiers jpatiers = new JpaDaoTiers();
                                    Tiers tier2 = jpatiers.find(Tiers.class, tier.getId());
                                    jpatiers.delete(tier2);
                                    new VueTiers(stage, tiersConnecte);
                                }
                            });
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colonneSupprimer2.setCellFactory(cellFactory2);
        colonneSupprimer2.setStyle("-fx-alignment: CENTER;");

        tableProprietaires.resizeColumn(colonneSupprimer2, 60);
        tableProprietaires.setItems(listeProprietaires);
        tableProprietaires.getColumns().addAll(colonneNumero1, colonneNom1, colonnePrenom1, colonneNaissance1, colonneSecurite1, colonneRib1, colonneEmail1, colonneSupprimer2);


        Button boutonNouveauProprietaire = ButtonsUtil.createNouveauTiersButton(this.stage, "Propriétaire", tiersConnecte);
        HBox hboxNouveauProprietaire = ButtonsUtil.createStyleButton(boutonNouveauProprietaire, "vert");

        VBox containerTable = new VBox(titreAgents, tableAgents, hboxNouvelAgent, titreProprietaires, tableProprietaires, hboxNouveauProprietaire);
        containerTable.setAlignment(Pos.CENTER);

        VBox container = new VBox(containerGoback, titre, containerTable);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(container);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        this.scene = new Scene(scroll);

        this.stage.setScene(this.scene);
    }
}
