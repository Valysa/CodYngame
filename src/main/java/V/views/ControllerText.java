package V.views;


import javafx.scene.layout.VBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.flowless.VirtualizedScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerText extends VBox{

    private MainView mainApp;

    public ControllerText(MainView mainApp) {
        CodeArea codeArea = new CodeArea();

        //Définir la taille du CodeArea
        codeArea.setPrefSize(700, 800);
        // add ligne number
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        // add patterns
        Pattern keywordPattern = Pattern.compile("\\b(public|private|protected|class|static|void|int|double|new|if|else|extends|import|package)\\b");
        Pattern stringPattern = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"");
        Pattern intPattern = Pattern.compile("\\b\\d+\\b");
        // pay attention to changes in the text
        codeArea.textProperty().addListener((obs, oldText, newText) -> applyHighlighting(codeArea, keywordPattern, stringPattern,intPattern));

        // Initial application of syntax highlighting
        applyHighlighting(codeArea, keywordPattern, stringPattern,intPattern);

        // Ajout du CodeArea à la VBox avec un VirtualizedScrollPane
        this.getChildren().add(new VirtualizedScrollPane<>(codeArea));

    }

    private void applyHighlighting(CodeArea codeArea, Pattern keywordPattern, Pattern stringPattern, Pattern intPattern) {
        // Récupération du texte du CodeArea
        String text = codeArea.getText();

        // Réinitialisation des styles
        codeArea.clearStyle(0, text.length());

        // Application des styles pour les mots-clés
        Matcher matcher = keywordPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(), "keyword");
        }
        // Application des styles pour les chaînes de caractères
        matcher = stringPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(), "string");
        }
        matcher = intPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(),"int");
        }
    }
}