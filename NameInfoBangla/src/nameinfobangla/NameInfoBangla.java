package nameinfobangla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NameInfoBangla {

    Map<Integer, String> Bmap = new HashMap<Integer, String>();
    Map<Integer, String> Gmap = new HashMap<Integer, String>();

    JFrame f;
    JTextField tf;
    private final JComboBox combo = new JComboBox();
    private final Vector<String> v = new Vector<String>();

    JTextArea jt2;
    String person = "";
    static int sz = 0;

    static int TotalBoys = 0;
    static int TotalGirls = 0;
    
    static int BG = 0;
     String[] STR;
    

    public NameInfoBangla() {
        SaveName();
        f = new JFrame(" Name Info");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.setVisible(true);
        f.setLocation(100, 50);

        f.setLayout(new FlowLayout());

        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(800, 600));
        jp.setBackground(Color.LIGHT_GRAY);
        f.add(jp);

        JLabel jl = new JLabel();
        jl.setPreferredSize(new Dimension(250, 60));
        jl.setBackground(Color.green);
        jp.add(jl);

        JButton jb = new JButton();
        Font myFont4 = new Font("SolaimanLipi", Font.BOLD, 20);
        jb.setFont(myFont4);
        jb.setText("নামটি লিখুন");
        jb.setPreferredSize(new Dimension(160, 40));
        jb.setForeground(Color.white);
        jb.setPreferredSize(new Dimension(200, 40));
        jb.setBackground(Color.red);
        jp.add(jb);

        JLabel jl2 = new JLabel();
        jl2.setPreferredSize(new Dimension(200, 10));
        jl2.setBackground(Color.green);
        jp.add(jl2);

        /*tf = new JTextField();
        tf.setPreferredSize(new Dimension(500, 40));
        tf.setBackground(Color.WHITE);
       jp.add(tf);
        Font myFont2 = new Font("SolaimanLipi", Font.PLAIN, 22);
        tf.setFont(myFont2);*/
        combo.setEditable(true);
        tf = (JTextField) combo.getEditor().getEditorComponent();
        //  tf.setPreferredSize(new Dimension(500, 40));
        tf.setBorder(BorderFactory.createEmptyBorder(1, 2, 10, 200));
        //tf.setPreferredSize(new Dimension(200, 40));
        Font myFont2 = new Font("SolaimanLipi", Font.PLAIN, 22);
        tf.setFont(myFont2);
        jp.add(combo);

        JButton jb2 = new JButton();
        Font myFont3 = new Font("SolaimanLipi", Font.BOLD, 16);
        jb2.setFont(myFont3);
        jb2.setText("অনুসন্ধান");
        jb2.setPreferredSize(new Dimension(100, 40));
        jb2.setForeground(Color.white);
        jb2.setBackground(Color.RED);
        jp.add(jb2);

        JLabel jl3 = new JLabel();
        jl3.setPreferredSize(new Dimension(10, 160));
        jl3.setBackground(Color.RED);
        jp.add(jl3);

        jt2 = new JTextArea();
        jt2.setPreferredSize(new Dimension(520, 160));
        jt2.setBackground(Color.DARK_GRAY);
        jt2.setForeground(Color.CYAN);
        jt2.setWrapStyleWord(true);
        jp.add(jt2);
         Font myFont5 = new Font("SolaimanLipi", Font.HANGING_BASELINE, 30);
       jt2.setFont(myFont5);
        MyButton mb = new MyButton();
        jb2.addActionListener(mb);

        //   super(new BorderLayout());
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                System.out.println("hi");
                combo.showPopup();
                 Font myFont7 = new Font("SolaimanLipi", Font.PLAIN, 22);
        combo.setFont(myFont7);
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        String text = tf.getText();
                        if (text.length() == 0) {
                            combo.hidePopup();
                            setModel(new DefaultComboBoxModel(v), "");
                        } else {
                            DefaultComboBoxModel m = getSuggestedModel(v, text);
                            if (m.getSize() == 0 || hide_flag) {
                                combo.hidePopup();
                                hide_flag = false;
                            } else {
                                setModel(m, text);
                                combo.showPopup();
                            }
                        }
                    }
                });
            }

            public void keyPressed(KeyEvent e) {
                String text = tf.getText();
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    if (!v.contains(text)) {
                        v.addElement(text);
                        Collections.sort(v);
                        setModel(getSuggestedModel(v, text), text);
                    }
                    hide_flag = true;
                } else if (code == KeyEvent.VK_ESCAPE) {
                    hide_flag = true;
                } else if (code == KeyEvent.VK_RIGHT) {
                    for (int i = 0; i < v.size(); i++) {
                        String str = v.elementAt(i);
                        if (str.startsWith(text)) {
                            combo.setSelectedIndex(-1);
                            tf.setText(str);
                            return;
                        }
                    }
                }
            }
        });
         
        STR = new String[4000];
        for (int j = 0; j < TotalGirls; j++) {
                String value = Gmap.get(j);
                STR[BG] = value;
                BG++;
        }
        for (int j = 0; j < TotalBoys; j++) {
                String value = Bmap.get(j);
                STR[BG] = value;
                BG++;
        }
        for (int i = 0; i < BG; i++) {
            v.addElement(STR[i]);
        }
        setModel(new DefaultComboBoxModel(v), "");
        //JPanel p = new JPanel(new BorderLayout());
        jp.setBorder(BorderFactory.createTitledBorder("Name Information"));
//       jp.add(combo, BorderLayout.NORTH);

        //jp.add (combo);
        //add(p);
        //setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //setPreferredSize(new Dimension(300, 350));
        f.validate();
    }

    void SaveName() {
        try {
            
            //System.out.println("YESSSSSS");
            String path = "D:\\Project Work - I\\NameInfoBangla\\GirlsName.txt";
            FileReader f = new FileReader(path);
            BufferedReader GN = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String GirlsName = "";
            //System.out.println("YESSSSSS");
            int i = 0;
            while ((GirlsName = GN.readLine()) != null) {
                //Font myFont = new Font("SolaimanLipi", Font.HANGING_BASELINE, 30);
                //jt2.setFont(myFont);
               //STR[BG] = GirlsName;
                //++BG;
                Gmap.put(i, GirlsName);
             //   System.out.println("YESSSSSS");
                ++i;
            }
            GN.close();
            TotalGirls = Gmap.size();
            //System.out.println(TotalGirls);

            path = "D:\\Project Work - I\\NameInfoBangla\\BoysName.txt";
            FileReader f2 = new FileReader(path);
            BufferedReader BN = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String BoysName = "";
            i = 0;
            while ((BoysName = BN.readLine()) != null) {
                //Font myFont2 = new Font("SolaimanLipi", Font.HANGING_BASELINE, 30);
                //jt2.setFont(myFont2);
                //STR[BG] = BoysName;
                //++BG;
                Bmap.put(i, BoysName);
                ++i;
            }
            BN.close();
            TotalBoys = Bmap.size();
           // System.out.println(TotalBoys);
            System.out.println(BG);

        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        combo.setModel(mdl);
        combo.setSelectedIndex(-1);
        tf.setText(str);
    }

    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            if (s.startsWith(text)) {
                m.addElement(s);
            }
        }
        return m;
    }

    class MyButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            person = tf.getText();
            int i;
            String[] Arr;
            Arr = new String[20];
            sz = 0;
            StringTokenizer token = new StringTokenizer(person, " ");
            while (token.hasMoreTokens()) {
                Arr[sz] = token.nextToken();
                ++sz;
            }
            int t = 1;
            for (int j = 0; j < TotalGirls; j++) {
                String value = Gmap.get(j);
                //System.out.println(value);
                for (i = 0; i < sz; i++) {
                    if (Arr[i].equals(value)) {
                        String temp = "  " + person + " একজন" + " মেয়ে ।" + "";
                        jt2.setText(temp);
                        //jt2.append("\n");
                        //jt2.append(temp);
                        //jt2.append("\n");
                        System.out.println(temp);
                        t = 0;
                        break;
                    }
                }
            }
            if (t == 1) {
                for (int j = 0; j < TotalBoys; j++) {
                    String value = Bmap.get(j);
                    //System.out.println(value);
                    for (i = 0; i < sz; i++) {
                        if (Arr[i].equals(value)) {
                            t = 2;
                            break;
                        }
                    }
                    if (t == 2) {
                        String temp = "  " + person + " একজন" + " ছেলে ।" + "";
                        jt2.setText(temp);
                    } else {
                        String temp = "  " + " আপনার সঠিক নাম লিখুন ।";
                        jt2.setText(temp);
                    }
                }
                System.out.println(person);

            }
        }
    }
        public static void main(String[] args) {

            NameInfoBangla g = new NameInfoBangla();
            //System.out.println;

        }
}
