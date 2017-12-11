package st.finanse;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorControl {
    private ErrorControl() {}

    public static void handle(Thread thread, Throwable throwable) {
        handle(thread, throwable, true);
    }

    public static void handle(Throwable thr) {
        handle(Thread.currentThread(), thr, true);
    }

    private static void handle(Thread thread, Throwable throwable, boolean displayExceptionAlert) {
        if (displayExceptionAlert) {
            showExceptionAlert(thread, throwable);
        }
    }

    private static void showExceptionAlert(Thread thread, Throwable throwable) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wystąpił błąd");
            alert.setContentText("Skopiuj treść poniżej i wyślij ją twórcy programu.");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            String exceptionText = sw.toString();
            Label label = new Label("Stos wyjątku:");
            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
        } catch (Throwable thr2) {
            handle(thread, thr2, false);
        }
    }
}
