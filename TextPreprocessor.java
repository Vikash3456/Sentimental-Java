import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.SimpleTokenizer;

public class TextPreprocessing {
    private static final PorterStemmer stemmer = new PorterStemmer();
    private static final SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
    private static final List<String> stopWords = Arrays.asList("a", "an", "the", "and", "or", "but", "is", "are", "in", "on", "of", "for", "with", "at", "by");

    public static List<String> preprocess(String text) {
        List<String> tokens = tokenize(text);
        tokens = removeStopWords(tokens);
        tokens = stem(tokens);
        return tokens;
    }

    private static List<String> tokenize(String text) {
        return new ArrayList<>(Arrays.asList(tokenizer.tokenize(text)));
    }

    private static List<String> removeStopWords(List<String> tokens) {
        List<String> result = new ArrayList<>();
        for (String token : tokens) {
            if (!stopWords.contains(token.toLowerCase())) {
                result.add(token);
            }
        }
        return result;
    }

    private static List<String> stem(List<String> tokens) {
        List<String> result = new ArrayList<>();
        for (String token : tokens) {
            result.add(stemmer.stem(token));
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "The quick brown fox jumps over the lazy dog";
        List<String> preprocessedText = preprocess(text);
        System.out.println(preprocessedText); // Output: [quick, brown, fox, jump, over, lazi, dog]
    }
}
