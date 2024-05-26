package V.views;

import C.Languages.LanguageFactory;
import M.ExerciseStdinStdout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.flowless.VirtualizedScrollPane;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerText extends VBox {

    private MainView mainApp;

    public ControllerText(MainView mainApp) {
        this.mainApp = mainApp;
        CodeArea initTextArea = mainApp.getInitTextArea();
        ChoiceBox<String> languages = mainApp.getLanguages();

        if (languages == null || initTextArea == null) {
            throw new IllegalArgumentException("languages or initTextArea is not initialized properly");
        }

        //initTextArea.setVisible(false);

        // Add a listener to detect selection changes
        languages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (languages.getValue() != null){
                    System.out.println("Language " + newValue + " has been selected");
                    applyHighlighting(initTextArea, newValue);
                    try {
                        mainApp.setStringInitTextArea(ExerciseStdinStdout.readMinimalFile(languages.getValue()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    initTextArea.replaceText(mainApp.getStringInitTextArea());
                }
            }
        });

        // Define CodeArea size
        initTextArea.setPrefSize(1000, 800);
        // Add line numbers
        initTextArea.setParagraphGraphicFactory(LineNumberFactory.get(initTextArea));

        // Monitor text changes without resetting the language
        initTextArea.textProperty().addListener((obs, oldText, newText) -> {
            // Apply syntax highlighting if the language hasn't changed
            if (languages.getValue() != null) {
                applyHighlighting(initTextArea, languages.getValue());
            }
        });

        // Initial application of syntax highlighting
        applyHighlighting(initTextArea, languages.getValue());

        // Add CodeArea to VBox with VirtualizedScrollPane
        this.getChildren().add(new VirtualizedScrollPane<>(initTextArea));
    }

    private void applyHighlighting(CodeArea codeArea, String language) {
        // Get the text from the CodeArea
        String text = codeArea.getText();

        // Reset styles
        codeArea.clearStyle(0, text.length());

        // Add patterns
        Pattern keywordPattern;
        Pattern stringPattern;
        Pattern intPattern = Pattern.compile("\\b\\d+\\b"); // Pattern for integers common to all languages
        Pattern functionPattern = Pattern.compile("\\b\\w+(?=\\s*\\()"); // Pattern for function common to all languages
        Pattern commentPattern;
        switch (language) {
            case "java":
                keywordPattern = Pattern.compile("\\b(public|private|protected|class|static|void|int|double|new|if|else|for|while|return|import|package)\\b");
                stringPattern = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"");
                commentPattern = Pattern.compile("//[^\n]*|/\\*(.|\\R)*?\\*/");
                break;
            case "py":
                keywordPattern = Pattern.compile("\\b(def|class|if|elif|else|while|for|in|import|from|as|return|lambda|try|except|finally|with|yield|assert|break|continue|del|global|nonlocal|pass|raise|True|False|None)\\b");
                stringPattern = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'");
                commentPattern = Pattern.compile("#[^\n]*");
                break;
            case "c":
                keywordPattern = Pattern.compile("\\b(int|float|double|char|void|if|else|for|while|return|struct|typedef|union|enum|extern|static|const|volatile|sizeof|break|continue|goto|switch|case|default|register|signed|unsigned|long|short|auto)\\b");
                stringPattern = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"");
                commentPattern = Pattern.compile("//[^\n]*|/\\*(.|\\R)*?\\*/");
                break;
            case "js":
            case "mjs":
                keywordPattern = Pattern.compile("\\b(var|let|const|if|else|for|while|function|return|class|import|export|from|as|try|catch|finally|throw|new|this|super|extends|constructor|get|set|async|await|static|public|private|protected|yield|true|false|null|undefined)\\b");
                stringPattern = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*`([^`\\\\]|\\\\.)*`");
                commentPattern = Pattern.compile("//[^\n]*|/\\*(.|\\R)*?\\*/");
                break;
            case "php":
                keywordPattern = Pattern.compile("\\b(<?php|class|function|if|else|elseif|endif|for|while|do|foreach|as|switch|case|default|break|continue|return|new|try|catch|finally|throw|namespace|use|public|protected|private|static|const|var|echo|print|true|false|null)\\b");
                stringPattern = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'");
                commentPattern = Pattern.compile("//[^\n]*|/\\*(.|\\R)*?\\*/|#.*");
                break;
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }

        // Apply styles for keywords
        Matcher matcher = keywordPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(), "keyword");
        }

        // Apply styles for strings
        matcher = stringPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(), "string");
        }

        // Apply styles for integers
        matcher = intPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(), "int");
        }

        // Apply styles for comments
        matcher = commentPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(), "comment");
        }
        // Apply styles for function names
        matcher = functionPattern.matcher(text);
        while (matcher.find()) {
            codeArea.setStyleClass(matcher.start(), matcher.end(), "function");
        }
    }
}
