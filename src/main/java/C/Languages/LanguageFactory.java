package C.Languages;

/**
 * The LanguageFactory class is used to automatically assign a language to a given file
 * by looking at its extension.
 */

public class LanguageFactory {

    /**
     * Assigns a language to a given file based on its extension.
     *
     * @param filePath the path to the file.
     * @return an instance of a subclass of Language corresponding to the file extension.
     * @throws IllegalArgumentException if the file extension is not recognized.
     */

    //This class is used to auto assign a language to a gived file by looking at its extension
    public static Language assignLanguage(String filePath) {
        if (filePath.endsWith(".c")) {
            return new CLanguage();
        } else if (filePath.endsWith(".php")) {
            return new PHPLanguage();
        } else if (filePath.endsWith(".java")) {
            return new JavaLanguage();
        } else if (filePath.endsWith(".py")) {
            return new PythonLanguage();
        } else if (filePath.endsWith(".js")) {
            return new JsLanguage();
        } else if (filePath.endsWith(".mjs")) {  //This extension serve for import and export functions in the include mode
            return new JsLanguage();
        }
        
        
        // Si l'extension n'est pas reconnue, retournez null ou lancez une exception
        throw new IllegalArgumentException("Extension de fichier non reconnue : " + filePath);
    }
}