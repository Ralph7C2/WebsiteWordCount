/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitewordcount;

import java.awt.Rectangle;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ralph Landon
 */
public class WebWordCountFrame extends javax.swing.JFrame {

    boolean excludeCommonWords;
    boolean useMin;
    boolean useMax;
    int minWordLength;
    int maxWordLength;

    WebsiteWordCount parent;
    String[] words;
    int[] counts;
    

    /**
     * Creates new form WebWordCountFrame
     *
     * @param parent The main program
     */
    public WebWordCountFrame(WebsiteWordCount parent) {
        this.parent = parent;
        initComponents();

        excludeCommonWords = false;
        useMin = false;
        useMax = false;
        minWordLength = 0;
        maxWordLength = 0;
    }

    protected void clearTable() {
        DefaultTableModel tm = (DefaultTableModel) wordCountTable.getModel();
        while (tm.getRowCount() > 0) {
            tm.removeRow(0);
        }
    }

    protected void fillTable(String[] words, int[] counts) {
        this.words = words;
        this.counts = counts;
        DefaultTableModel tm = (DefaultTableModel) wordCountTable.getModel();
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i] == null) {
                continue;
            }
            if ((!excludeCommonWords || !parent.commonWordList.contains(words[i]))&&(!useMin || words[i].length()>=minWordLength)&&(!useMax || words[i].length()<=maxWordLength)) {
                tm.addRow(new Object[]{words[i], counts[i]});
            }
        }
    }
    
    private void updateTable() {
        DefaultTableModel tm = (DefaultTableModel) wordCountTable.getModel();
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i] == null) {
                continue;
            }
            if ((!excludeCommonWords || !parent.commonWordList.contains(words[i]))&&(!useMin || words[i].length()>=minWordLength)&&(!useMax || words[i].length()<=maxWordLength)) {
                tm.addRow(new Object[]{words[i], counts[i]});
            }
        }
    }

    protected void setUrlText(String url) {
        urlTextField.setText(url);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        urlTextField = new javax.swing.JTextField();
        getWordsButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        wordCountTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        searchButton = new javax.swing.JButton();
        filterButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HyperlinkList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        replaceListButton = new javax.swing.JButton();
        addToListButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Website Word Count");

        urlTextField.setText("Enter Url");
        urlTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                urlTextFieldFocusGained(evt);
            }
        });
        urlTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlTextFieldActionPerformed(evt);
            }
        });

        getWordsButton.setText("Get Words");
        getWordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getWordsButtonActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, java.awt.Color.darkGray));

        wordCountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Word", "Count"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        wordCountTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(wordCountTable);
        if (wordCountTable.getColumnModel().getColumnCount() > 0) {
            wordCountTable.getColumnModel().getColumn(0).setResizable(false);
            wordCountTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jToolBar1.setRollover(true);

        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        searchButton.setFocusable(false);
        searchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(searchButton);

        filterButton.setText("Filter");
        filterButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        filterButton.setFocusable(false);
        filterButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filterButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(filterButton);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Word Count", jPanel3);

        HyperlinkList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(HyperlinkList);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));

        replaceListButton.setText("Replace List");
        replaceListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceListButtonActionPerformed(evt);
            }
        });

        addToListButton.setText("Add to List");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(replaceListButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addToListButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(replaceListButton)
                .addComponent(addToListButton))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Links", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(urlTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getWordsButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getWordsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void urlTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_urlTextFieldFocusGained
        urlTextField.selectAll();
    }//GEN-LAST:event_urlTextFieldFocusGained

    private void getWordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getWordsButtonActionPerformed
        parent.getWordCountFromUrl(urlTextField.getText());
    }//GEN-LAST:event_getWordsButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String searchWord = JOptionPane.showInputDialog(this, "Enter word to search", "Search", JOptionPane.QUESTION_MESSAGE);
        DefaultTableModel tm = (DefaultTableModel) wordCountTable.getModel();
        boolean found = false;
        for (int i = 0; i < tm.getRowCount(); i++) {
            if (searchWord.equalsIgnoreCase((String) tm.getValueAt(i, 0))) {
                wordCountTable.removeRowSelectionInterval(0, tm.getRowCount() - 1);
                wordCountTable.addRowSelectionInterval(i, i);
                wordCountTable.scrollRectToVisible(new Rectangle(wordCountTable.getCellRect(i, 0, true)));
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "The word \"" + searchWord + "\" was not found", "Word not found", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void urlTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlTextFieldActionPerformed
        parent.getWordCountFromUrl(urlTextField.getText());
    }//GEN-LAST:event_urlTextFieldActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        FilterDialog fd = new FilterDialog(this);
        fd.setVisible(true);
    }//GEN-LAST:event_filterButtonActionPerformed

    private void replaceListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceListButtonActionPerformed
        clearTable();
        parent.links.clear();
        String url = HyperlinkList.getSelectedValue();
        clearLinkList();
//System.out.println((HyperlinkList.getSelectionModel().isSelectionEmpty()?"Empty select":"non-empty select"));
        System.out.println("Replace list from: "+url);
        parent.getWordCountFromUrl(url);
    }//GEN-LAST:event_replaceListButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> HyperlinkList;
    private javax.swing.JButton addToListButton;
    private javax.swing.JButton filterButton;
    private javax.swing.JButton getWordsButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton replaceListButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField urlTextField;
    private javax.swing.JTable wordCountTable;
    // End of variables declaration//GEN-END:variables

    void setFilter(String filter, boolean bool, int num) {
        boolean filterChanged = false;
        switch (filter) {
            case "excludeCommon":
                if(excludeCommonWords != bool) {
                    filterChanged = true;
                }
                excludeCommonWords = bool;
                break;
            case "useMin":
                if(useMin != bool || minWordLength!=num) {
                    filterChanged = true;
                }
                useMin = bool;
                minWordLength = (num == -1 ? minWordLength : num);
                break;
            case "useMax":
                if(useMax != bool || maxWordLength!=num) {
                    filterChanged = true;
                }
                useMax = bool;
                maxWordLength = (num == -1 ? maxWordLength : num);
                break;
        }
        if(filterChanged) {
            clearTable();
            updateTable();
        }
    }

    boolean getFilter(String filter) {
        switch (filter) {
            case "excludeCommon":
                return excludeCommonWords;
            case "useMin":
                return useMin;
            case "useMax":
                return useMax;
            default:
                return false;
        }
    }

    String getFilterValue(String filter) {
        switch (filter) {
            case "min":
                return "" + minWordLength;
            case "max":
                return "" + maxWordLength;
            default:
                return "";
        }
    }

    void fillLinkList() {
        String[] words = new String[parent.links.size()];
        parent.links.toArray(words);
        HyperlinkList.setListData(words);
    }
    
    void clearLinkList() {
        HyperlinkList.setListData(new String[] {});
    }
}
