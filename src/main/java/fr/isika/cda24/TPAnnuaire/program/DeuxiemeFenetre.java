package fr.isika.cda24.TPAnnuaire.program;

import java.net.PasswordAuthentication;

//import javax.naming.AuthenticationException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import fr.isika.cda24.TPAnnuaire.entitees.Stagiaire;

public class DeuxiemeFenetre extends GridPane {

	// pour la connexion
	private boolean isAdmin = false;

	public DeuxiemeFenetre() {
// Etape 01: Création du GridPane et de ses paramètres :	
		// GridPane gridPane2 = new GridPane();

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color:#fffff0; -fx-font-family:'serif';");
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHgrow(Priority.ALWAYS);
		this.getColumnConstraints().add(column1);

// Création de la TableView
		TableView<Stagiaire> tableView = creerTableView();

// Etape 01c: Ajouter na nouvelle image en tant qu'element du GridPane:
		Image logo2 = new Image("Logo-Isika2.png");
		ImageView imageView = new ImageView(logo2);
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
//		imageView.preserveRatioProperty();

// Etape 02: Les créations et instanciations des variables pour la HBox situé en haut:

		Label lbl1 = new Label("Login : ");
		TextField tf1 = new TextField("Login");
		Label lbl2 = new Label("Mot de passe : ");
		PasswordField pf1 = new PasswordField();
		Button btn1 = new Button("Se connecter");
		HBox hBox = new HBox();
		hBox.getChildren().addAll(lbl1, tf1, lbl2, pf1, btn1);
		hBox.setPrefSize(800, 25);
		hBox.setStyle("-fx-background-color:#fffff0");
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(10));
//		btn1.setMinWidth(hBox.getPrefWidth());

// Etape 03: Les créations et instanciations des éléments de recherche pour la VBox à gauche:

		Label lbl3 = new Label("Nom :");
		TextField tf2 = new TextField("Entrez le nom");
		Label lbl4 = new Label("Prénom :");
		TextField tf3 = new TextField("Entrez le prénom");
		Label lbl5 = new Label("Département :");
		TextField tf4 = new TextField("Entrez le département");
		Label lbl6 = new Label("Année :");
		TextField tf5 = new TextField("Entrez l'année");
		Label lbl7 = new Label("Promotion :");
		TextField tf6 = new TextField("Entrez la promotion");
		Button btn2 = new Button("Rechercher");
		Button btn3 = new Button("Imprimer");
		Button btn4 = new Button("Modifier");
		Button btn5 = new Button("Supprimer");

// Etape 03a : Mettre en place la connexion de l'Admin pour activer les btn4 et btn5:		
		btn1.setOnAction(event -> {
			if(btn1.getText().equals("Se connecter")) {
				
			String username = tf1.getText();
			String password = pf1.getText();
			if ((username.equals("André") && password.equals("Bastos"))
					|| (username.equals("Florian") && password.equals("Lestieux"))
					|| (username.equals("David") && password.equals("Aitcheou"))) {
				isAdmin = true;
				btn4.setVisible(isAdmin);
				btn5.setVisible(isAdmin);
				btn1.setText("Se déconnecter");
				
// Etape 03b : Faire le bnt1 changer de "Se Deconecter" pour "Se connecter" et faire disparaitre btn4 et btn5:				
					/*
					 * btn1.setOnAction(event2 -> {
					 * 
					 * });
					 */
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.getDialogPane().setStyle("-fx-background-color:#fffff0; -fx-font-family:'serif';");	
				
				alert.setTitle("Erreur de connexion");
				alert.setHeaderText(null);
				alert.setContentText("Veuillez vérifier vos informations d'identification et réessayer.\nNom d'utilisation ou mot de passe incorrect.");
				alert.showAndWait();
				
				btn4.setVisible(false);
				btn5.setVisible(false);
				tf1.clear();
				pf1.clear();
			}
			} else if (btn1.getText().equals("Se déconnecter")) {
				btn1.setText("Se connecter");
				isAdmin = false;
				btn4.setVisible(isAdmin);
				btn5.setVisible(isAdmin);
				tf1.clear();
				pf1.clear();
			}
		});

// Etape 03c : Rendre les boutons btn4 et btn5 visibles quand d'un login ok:		
		btn4.setVisible(isAdmin);
		btn5.setVisible(isAdmin);

// Etape 04: Création de la VBox et ajout des éléments :

		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setMaxWidth(200);
		vBox.setAlignment(Pos.CENTER_LEFT);
		vBox.getChildren().addAll(lbl3, tf2, lbl4, tf3, lbl5, tf4, lbl6, tf5, lbl7, tf6, btn2, btn3, btn4, btn5);
		vBox.setPrefSize(200, 200);
		vBox.setStyle("-fx-background-color:#fffff0");
		btn2.setMinWidth(vBox.getPrefWidth());
		btn3.setMinWidth(vBox.getPrefWidth());
		btn4.setMinWidth(vBox.getPrefWidth());
		btn5.setMinWidth(vBox.getPrefWidth());

// Etape 05: Ajout des HBox et VBox dans le GridPane :

// GridPane.setConstraints(node, colonne, ligne)

		GridPane.setConstraints(imageView, 0, 0);
		this.getChildren().add(imageView);
		GridPane.setConstraints(hBox, 1, 0);
		this.getChildren().add(hBox);
		GridPane.setConstraints(vBox, 0, 1);
		this.getChildren().add(vBox);
		GridPane.setConstraints(tableView, 1, 1);

		this.getChildren().add(tableView);
		this.setStyle("-fx-background-color:#fffff0; -fx-font-family:'serif';");
//		// Scene scene = new Scene(gridPane2, 900, 560);
//		// stage.setScene(scene);
//		// .setTitle("Annuaire - Page de consultation");
//		// stage.show();
	}

// Etape 06: Création des collonnes d'une TableView:

	private TableView<Stagiaire> creerTableView() {
		TableView<Stagiaire> tableView = new TableView<>();
		TableColumn<Stagiaire, String> nomColonne = new TableColumn<>("Nom");
		nomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nomColonne.setMinWidth(100);
		TableColumn<Stagiaire, String> prenomColonne = new TableColumn<>("Prénom");
		prenomColonne.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		prenomColonne.setMinWidth(100);
		TableColumn<Stagiaire, String> departementColonne = new TableColumn<>("Département");
		departementColonne.setCellValueFactory(new PropertyValueFactory<>("departement"));
		departementColonne.setMinWidth(100);
		TableColumn<Stagiaire, String> promotionColonne = new TableColumn<>("Promotion");
		promotionColonne.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		promotionColonne.setMinWidth(100);
		TableColumn<Stagiaire, Integer> anneeColonne = new TableColumn<>("Année");
		anneeColonne.setCellValueFactory(new PropertyValueFactory<>("annee"));
		anneeColonne.setMinWidth(100);

// Etape 06a: Intégration des colonnes à la TableView:

		tableView.getColumns().addAll(nomColonne, prenomColonne, departementColonne, promotionColonne, anneeColonne);

// Etape 06b: Ajustement de la largeur des colonnes:

		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return tableView;
	}
}
