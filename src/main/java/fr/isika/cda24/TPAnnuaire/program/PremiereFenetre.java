package fr.isika.cda24.TPAnnuaire.program;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class PremiereFenetre extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

// Etape 01: Les créations et instantions des variables:

		GridPane gridPane1 = new GridPane();

		Image logo = new Image("Logo-Isika.png");
		ImageView imageView = new ImageView(logo);
		imageView.setFitWidth(300);
		imageView.setFitHeight(200);
		imageView.preserveRatioProperty();

		Button btn1 = new Button("Consulter l'Annuaire");
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent argO) {
				DeuxiemeFenetre pane2 = new DeuxiemeFenetre();
				Scene scene2 = new Scene(pane2, 900,560);
				stage.setScene(scene2);
				}
			});
		
		HBox hBox1 = new HBox(200);
		HBox hBox2 = new HBox(200);
		VBox vBox1 = new VBox(300);

// Etape 02: Intégrer les variables avec le constructeur "obj.getChildren" :

		hBox1.getChildren().add(imageView);
		hBox2.getChildren().add(btn1);
		hBox1.setAlignment(Pos.CENTER);
		hBox2.setAlignment(Pos.BOTTOM_CENTER);
		vBox1.getChildren().addAll(hBox1, hBox2);

//		vBox1.getChildren().addAll(label, btn1);
		vBox1.setMinSize(300, 300);
		vBox1.setAlignment(Pos.BOTTOM_CENTER);

// Etape 03 :Tester:

		gridPane1.getChildren().addAll(vBox1);
		gridPane1.setAlignment(Pos.CENTER);
		gridPane1.setStyle("-fx-background-color:#fffff0; -fx-font-family:'serif';");

		Scene scene = new Scene(gridPane1, 900, 560);

		stage.setScene(scene);
		stage.setTitle("Bienvenue sur l'annuaire");
		stage.show();
	}
	}
