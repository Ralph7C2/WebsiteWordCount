/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitewordcount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ralph Landon
 */
public class WebsiteWordCount {

    /**
     * Entry point
     *
     * @param args command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WebWordCountFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //Call the contrustor and start the program
        WebsiteWordCount wwc = new WebsiteWordCount();
    }

    /**
     *
     * @param arr The integer array to sort
     * @param w The String array(gets sorted with the integer array)
     * @param low The low end of this run
     * @param high The high end of this run
     */
    public static void quickSort(int[] arr, String[] w, int low, int high) {
        //Check to see if we are done
        if (low >= high) {
            return;
        }

        //Set the pivot to middle value
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        //Perform the swaps
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                String s = w[i];
                arr[i] = arr[j];
                w[i] = w[j];
                arr[j] = temp;
                w[j] = s;
                i++;
                j--;
            }
        }

        //Now sort either both above and below the pivot
        if (low < j) {
            quickSort(arr, w, low, j);
        }

        if (high > i) {
            quickSort(arr, w, i, high);
        }
    }

    WebWordCountFrame frame;
    ArrayList<String> commonWordList;
    ArrayList<String> links;

    /**
     * Constructor
     */
    public WebsiteWordCount() {
        commonWordList = new ArrayList<>();
        loadCommonWordList();

        //Initialize and show the frame
        frame = new WebWordCountFrame(this);
        frame.setVisible(true);
    }

    private String getValidatedUrl(String url) {
        if (url.length() == 0) {
            return null;
        }
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        if (url.contains(" ")) {
            JOptionPane.showMessageDialog(frame, "Invalid URL", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        frame.setUrlText(url);
        return url;
    }

    /**
     *
     * @param url The URL from which to gather the word count
     */
    protected void getWordCountFromUrl(String url) {
        frame.clearTable();
        //Create a new Document
        Document doc = null;
        try {
            //Get the url from the text field
            url = getValidatedUrl(url);
            //If it doesn't start with http(s) prepend it so we don't get MalformedException
            if (url == null) {
                return;
            }
            //use Jsoup to get the url content
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Could not connect to host", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        //Make sure we actually got the document
        if (doc == null) {
            return;
        }
        Elements ahref = doc.getElementsByTag("a");
        for(Element e : ahref) {
            links.add(e.attr("href"));
        }
        frame.fillLinksTable();
        
        //Split the words using regex so that we don't grab numbers and commas and the like
        String[] words = doc.text().split("[^A-Za-z]");
        //Splitting in this way does give us some empty elements, so lets move those to the end
        try {
            int lastIndex = words.length - 1;
            for (int i = 0; i < words.length; i++) {
                if (lastIndex == 0) {
                    System.out.println("Uh oh..");
                }
                if (words[i] == null) {
                    break;
                }
                while (words[i].length() == 0) {
                    words[i] = words[lastIndex];
                    words[lastIndex] = null;
                    lastIndex--;
                    if (words[i] == null) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(frame, "Internal Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Create an array to hold the unique words and their counts
        String[] uniqueWords = new String[words.length];
        int[] counts = new int[words.length];
        //Counter to help know where to add the new words to the end
        int uniqueWordCount = 0;
        //Loop through the words, incrementing if found, adding to the end if not
        for (String word : words) {
            boolean found = false;
            //Since we moved the empty string to the end and set them to null, check to see if we reached that dead space yet
            if (word == null) {
                break;
            }
            //Check found words to see if this word matched
            for (int i = 0; i < uniqueWords.length; i++) {
                //If the found word list is null, we didn't find it.
                if (uniqueWords[i] == null) {
                    break;
                }
                //Found the word, lets increment the count
                if (uniqueWords[i].equalsIgnoreCase(word)) {
                    counts[i]++;
                    found = true;
                    break;
                }
            }
            //Didn't find the word, add it to the end and set it's count to 1;
            if (!found) {
                uniqueWords[uniqueWordCount] = word.toLowerCase();
                counts[uniqueWordCount] = 1;
                uniqueWordCount++;
            }
        }
        //Sort the wordlist by count
        quickSort(counts, uniqueWords, 0, uniqueWordCount + 1);
        //Fill the table with our wordlist and counts
        frame.fillTable(uniqueWords, counts);
    }

    private void loadCommonWordList() {
        File f = new File("commonWordsList.dat");
        BufferedReader in = null;
        try {
            f = new File("commonWordsList.dat");
            in = new BufferedReader(new FileReader(f));
            String s;
            while ((s = in.readLine()) != null) {
                commonWordList.add(s);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Could not load common word list[commonWordsList.dat]", "File not found", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
