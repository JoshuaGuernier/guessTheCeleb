package au.edu.jcu.cp3406.guesstheceleb;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;


public class CelebrityManager {

    private static final String TAG = "CelebrityManager";

    private static String assetPath;
    private static String[] imageNames;
    private static String[] imageNamesReformatted;
    private static AssetManager assetManager;
    private static int celebrityCount;

    public CelebrityManager(AssetManager assetManagerInput, String assetPathInput) {
        try {
            Log.d(TAG, "CelebrityManager: Started. ");
            // Creates and associates universal arrays.
            assetPath = assetPathInput;
            assetManager = assetManagerInput;
            // Adds content to newly created
            imageNames = assetManager.list(assetPath);
            imageNamesReformatted = assetManager.list(assetPath);
            assert imageNamesReformatted != null;
            celebrityCount = 0;
            String tempString;
            for (String name : imageNamesReformatted) {
                tempString = uppercaseWord(replaceFromWord(name));
                imageNamesReformatted[celebrityCount] = tempString;
                celebrityCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Replaces - and .jpg from Name.
    public static String replaceFromWord(String word) {
        return word.replace('-', ' ').replace(".jpg", "");
    }

    // Uppercase word
    public static String uppercaseWord(String inputString) {
        String[] words = inputString.split("\\s");
        StringBuilder capitalizeWord = new StringBuilder();
        for (String word : words) {
            String first = word.substring(0, 1);
            String second = word.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(second).append(" ");
        }
        return capitalizeWord.toString().trim();
    }

    // Gets Image in relation to position in array.
    public static Bitmap get(int i) {
        InputStream inputStream;
        Bitmap bitmap = null;
        try {
//            System.out.println("outputStream " + assetPath + imageNames[i]);
            inputStream = assetManager.open(assetPath + '/' + imageNames[i]);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static int count(){
        return celebrityCount;
    }

    //Gets image name in relation to position of array.
    public static String getName(int i) {
        return imageNamesReformatted[i];
    }
    public static String[] getImageNamesReformatted(){
        return imageNamesReformatted;
    }
}
