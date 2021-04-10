package nameinfo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NameInfo {

    JFrame f;
    JTextField jt;
    JTextArea jt2;
    String person = "";
    static int sz = 0;

    static int TotalBoys = 0;
    static int TotalGirls = 0;

    public NameInfo() {
        f = new JFrame("");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.setVisible(true);
        f.setLocation(100, 50);

        f.setLayout(new FlowLayout());

        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(800, 600));
        jp.setBackground(Color.BLACK);
        f.add(jp);

        JLabel jl = new JLabel();
        jl.setPreferredSize(new Dimension(250, 60));
        jl.setBackground(Color.green);
        jp.add(jl);

        JButton jb = new JButton();
        jb.setText("Enter Name");
        jb.setPreferredSize(new Dimension(200, 40));
        jb.setBackground(Color.blue);
        jp.add(jb);

        JLabel jl2 = new JLabel();
        jl2.setPreferredSize(new Dimension(200, 10));
        jl2.setBackground(Color.green);
        jp.add(jl2);

        jt = new JTextField();
        jt.setPreferredSize(new Dimension(500, 40));
        jt.setBackground(Color.WHITE);
        jp.add(jt);

        JButton jb2 = new JButton();
        jb2.setText("send");
        jb2.setPreferredSize(new Dimension(80, 40));
        jb2.setBackground(Color.blue);
        jp.add(jb2);

        JLabel jl3 = new JLabel();
        jl3.setPreferredSize(new Dimension(10, 160));
        jl3.setBackground(Color.green);
        jp.add(jl3);

        jt2 = new JTextArea();
        jt2.setPreferredSize(new Dimension(520, 160));
        jt2.setBackground(Color.ORANGE);
        jt2.setWrapStyleWord(true);
        
        jp.add(jt2);

        MyButton mb = new MyButton();
        jb2.addActionListener(mb);

        f.validate();
    }

    class MyButton implements ActionListener {

        Map<Integer, String> Bmap = new HashMap<Integer, String>();
        Map<Integer, String> Gmap = new HashMap<Integer, String>();

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                person = jt.getText();
                person = person.toLowerCase();
                BufferedReader BN = new BufferedReader(new FileReader("D:\\Project Work - I\\NameInfo\\BoysName.txt"));
                BufferedReader GN = new BufferedReader(new FileReader("D:\\Project Work - I\\NameInfo\\GirlsName.txt"));
                String BoysName = "";
                String GirlsName = "";
                int i = 0;
                while ((BoysName = BN.readLine()) != null) {
                    // System.out.println(BoysName);
                    Bmap.put(i, BoysName);
                    ++i;
                }
                BN.close();
                i = 0;
                while ((GirlsName = GN.readLine()) != null) {
                    Gmap.put(i, GirlsName);
                    ++i;
                }
                GN.close();
                TotalBoys = Bmap.size();
                TotalGirls = Gmap.size();

                String[] Arr;
                Arr = new String[20];
                sz = 0;
                StringTokenizer token = new StringTokenizer(person, " ");
                while (token.hasMoreTokens()) {
                    Arr[sz] = token.nextToken();
                    //System.out.println(Arr[sz]);
                    ++sz;
                }
                //System.out.println(person);
                int t = 1;
                for (int j = 0; j < TotalGirls; j++) {
                    String value = Gmap.get(j);
                    //System.out.println(value);
                    for (i = 0; i < sz; i++) {
                        if (Arr[i].equals(value)) {
                            String temp = person + " is a Female.";
                            jt2.append(temp);
                           // jt2.append("\n");
                            //jt2.append(temp);
                            //jt2.append("\n");
                            t = 0;
                            break;
                        }
                    }
                }
                if(t==1){
                     String temp = person + " is a Male.";
                     jt2.setText(temp);
                }
                System.out.println(person);

            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }

        }
    }

    public static void main(String[] args) {

        NameInfo g = new NameInfo();

    }
}
