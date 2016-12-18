/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitewordcount;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author 110100100
 */
public class WebsiteWordCount {
    JFrame frame;
    JTextField urlField;
    JTable table;
    public WebsiteWordCount() {
        //Create the JFrame and set it up
        frame = new JFrame("WebsiteWordCount");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout frameLayout = new BorderLayout();
        frameLayout.setHgap(10);
        frameLayout.setVgap(10);
        frame.setLayout(frameLayout);
        
        //Create a panel to hold the urlfield and button
        JPanel topBar = new JPanel(new GridBagLayout());
        //Using gridBag so the button takes 10% of width
        GridBagConstraints gbc = new GridBagConstraints();
        //Create the field and add it to the panel
        urlField = new JTextField("Enter Url");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = .9;
        gbc.insets.right = 5;
        gbc.insets.top = 10;
        gbc.insets.left = 10;
        urlField.addActionListener((ActionEvent e) -> {
            getWordCountFromUrl();
        });
        urlField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                urlField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        topBar.add(urlField, gbc);
        //Create the go button and add it to the panel
        JButton goButton = new JButton("Get Words");
        gbc.gridx = 1;
        gbc.weightx = .1;
        goButton.addActionListener((ActionEvent e) -> {
            getWordCountFromUrl();
        });
        topBar.add(goButton, gbc);
        //Add the panel to the frame
        frame.add(topBar, BorderLayout.NORTH);
        
        //Create the table and add it to the frame
        table = new JTable();
        
        //Get the table model to set the column labels
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        tm.setColumnIdentifiers(new Object[] {"Word", "Count"});
        
        //Create a scrollable pane for the table
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);
        
        //Set the frame's size and show it
        frame.setSize(400,400);
        frame.setVisible(true);
    }
    
    public void fillTable(String[] words, int[] counts) {
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        for(int i = words.length-1;i>=0;i--) {
            if(words[i]==null) {
                continue;
            }
            tm.addRow(new Object[] {words[i], counts[i]});
        }
        
    }
    
    public static void main(String args[]) {
        //Call the contrustor and start the program
        WebsiteWordCount wwc = new WebsiteWordCount();
    }
    
    public static void quickSort(int[] arr, String[] w, int low, int high) {
		//Check to see if we are done
		if (low >= high)
			return;
 
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
		if (low < j)
			quickSort(arr, w, low, j);
 
		if (high > i)
			quickSort(arr, w, i, high);
	}

    private void clearTable() {
        DefaultTableModel tm = (DefaultTableModel)table.getModel();
        while(tm.getRowCount()>0) {
            tm.removeRow(0);
        }
    }
    
    private String getValidatedUrl() {
        String url = urlField.getText();
        if(url.length() == 0) {
            return null;
        }
        if(!url.startsWith("http")) {
            url = "http://"+url;
        }
        if(url.contains(" ")) {
            JOptionPane.showMessageDialog(frame, "Invalid URL", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        urlField.setText(url);
        return url;
    }
    
    private void getWordCountFromUrl() {
        clearTable();
        //Create a new Document
        Document doc = null;
        try {
            //Get the url from the text field
            String url = getValidatedUrl();
            //If it doesn't start with http(s) prepend it so we don't get MalformedException
            if(url == null) {
                return;
            }
            //use Jsoup to get the url content
            doc = Jsoup.connect(url).get();
        } catch(IOException e) {
            JOptionPane.showMessageDialog(frame, "Could not connect to host", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        //Make sure we actually got the document
        if(doc == null) {
            return;
        }
        //Split the words using regex so that we don't grab numbers and commas and the like
        String[] words = doc.text().split("[^A-Za-z]");
        //Splitting in this way does give us some empty elements, so lets move those to the end
        try {
            int lastIndex = words.length-1;
            for(int i = 0;i<words.length;i++) {
                if(lastIndex == 0) {
                    System.out.println("Uh oh..");
                }
                if(words[i] == null) {
                    break;
                }
                while(words[i].length()==0) {
                    words[i] = words[lastIndex];
                    words[lastIndex] = null;
                    lastIndex--;
                    if(words[i]==null) {
                        break;
                    }
                }
            }
        } catch(Exception e) {
            JOptionPane.showConfirmDialog(frame, "Internal Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Create an array to hold the unique words and their counts
        String[] uniqueWords = new String[words.length];
        int[] counts = new int[words.length];
        //Counter to help know where to add the new words to the end
        int uniqueWordCount = 0;
        //Loop through the words, incrementing if found, adding to the end if not
        for(String word : words) {
            boolean found = false;
            //Since we moved the empty string to the end and set them to null, check to see if we reached that dead space yet
            if(word == null) {
                break;
            }
            //Check found words to see if this word matched
            for(int i = 0;i<uniqueWords.length;i++) {
                //If the found word list is null, we didn't find it.
                if(uniqueWords[i] == null) {
                    break;
                }
                //Found the word, lets increment the count
                if(uniqueWords[i].equalsIgnoreCase(word)) {
                    counts[i]++;
                    found = true;
                    break;
                }
            }
            //Didn't find the word, add it to the end and set it's count to 1;
            if(!found) {
                uniqueWords[uniqueWordCount] = word.toLowerCase();
                counts[uniqueWordCount] = 1;
                uniqueWordCount++;
            }
        }
        //Sort the wordlist by count
        quickSort(counts, uniqueWords, 0, uniqueWordCount+1);
        //Fill the table with our wordlist and counts
        fillTable(uniqueWords, counts);
    }
}