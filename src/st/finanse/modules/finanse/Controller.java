package st.finanse.modules.finanse;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML private TreeView<?> monthTree;

    @FXML private Label monthTitle;

    @FXML private TableView<?> table;

    @FXML private Spinner<?> entryDay;

    @FXML private CheckBox isHoliday;

    @FXML private TextField entryTitle;

    @FXML private TextField entryAmount;

    @FXML private Button entryAccepted;

    @FXML
    void addEntry(ActionEvent event) {

    }

    @FXML
    void monthChoosen(MouseEvent event) {

    }

    @FXML
    void tableClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }
}
