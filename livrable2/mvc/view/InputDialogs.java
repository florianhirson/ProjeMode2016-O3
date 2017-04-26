package mvc.view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import mvc.model.Courbe;

public class InputDialogs {

	public static double saisieLambda() {
		double lambda = 0;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setHeaderText(null);
		dialog.setTitle("Saisie de lambda");
		dialog.setContentText("Veuillez entrer lambda : ");
		Optional<String> res = dialog.showAndWait();

		try {
			lambda = Double.parseDouble(res.get());
			return lambda;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public static int saisieOrdre() {
		int ordre = 0;
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setHeaderText(null);
		dialog.setTitle("Saisie de l'ordre pour la moyenne mobile");
		dialog.setContentText("Veuillez entrer l'ordre : ");
		Optional<String> res = dialog.showAndWait();

		try {
			ordre = Integer.parseInt(res.get());
			return ordre;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public static ArrayList<Courbe> saisieChoixCourbe (ArrayList<Courbe<Number,Number>> listCourbe) {
		ArrayList<Courbe> res = null;
		// Create the custom dialog.
		Dialog<ArrayList<Courbe>> dialog = new Dialog<>();
		dialog.setTitle("Choice dialog");
		dialog.setHeaderText("Look, a Custom Choice Dialog");
		dialog.setHeight(200);
		// Set the button types.
		ButtonType valider = new ButtonType("Valider", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(valider, ButtonType.CANCEL);

		// Create the listviews.
		GridPane gridpane = new GridPane();
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.setPadding(new Insets(20, 20, 20, 20));

		ColumnConstraints column1 = new ColumnConstraints(150, 150,Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(50);
		ColumnConstraints column3 = new ColumnConstraints(150, 150,Double.MAX_VALUE);
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		gridpane.getColumnConstraints().addAll(column1, column2, column3);

		Label candidatesLbl = new Label("Candidates");
		GridPane.setHalignment(candidatesLbl, HPos.CENTER);
		gridpane.add(candidatesLbl, 0, 0);

		Label selectedLbl = new Label("Selected");
		gridpane.add(selectedLbl, 2, 0);
		GridPane.setHalignment(selectedLbl, HPos.CENTER);

		// Candidates
		final ObservableList<Courbe> candidates = FXCollections.observableArrayList(listCourbe);
		final ListView<Courbe> candidatesListView = new ListView<>(candidates);
		candidatesListView.setMaxHeight(200);
		gridpane.add(candidatesListView, 0, 1);

		final ObservableList<Courbe> selected = FXCollections.observableArrayList();
		final ListView<Courbe> heroListView = new ListView<>(selected);
		heroListView.setMaxHeight(200);
		gridpane.add(heroListView, 2, 1);

		Button sendRightButton = new Button(" > ");
		sendRightButton.setOnAction((ActionEvent event) -> {
			Courbe potential = candidatesListView.getSelectionModel().getSelectedItem();
			if (potential != null) {
				candidatesListView.getSelectionModel().clearSelection();
				candidates.remove(potential);
				selected.add(potential);
			}
		});

		Button sendLeftButton = new Button(" < ");
		sendLeftButton.setOnAction((ActionEvent event) -> {
			Courbe s = heroListView.getSelectionModel().getSelectedItem();
			if (s != null) {
				heroListView.getSelectionModel().clearSelection();
				selected.remove(s);
				candidates.add(s);
			}
		});
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(sendRightButton, sendLeftButton);

		gridpane.add(vbox, 1, 1);
		dialog.getDialogPane().setContent(gridpane);

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == valider) {
		        return res;
		    }
		    return null;
		});

		dialog.showAndWait();





		return res;
	}
}
