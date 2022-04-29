import world.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class Game_GUI extends JFrame {
    private static final long serialVersionUID = 1L;



    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    JPanel main_panel;
    JPanel action_panel;
    List<JButton> buttonList;
    ImageIcon base_icon;
    String icon_path = null;
    ImageIcon icon;
    String kill_icon_path=null;
    ImageIcon kill_icon;


    protected Moves moves;
    String client_name = null;
    final String[] action = {null};

    Game_GUI(final Moves moves, String client_name,String icon_path,String kill_icon_path)
    {
        System.out.println(client_name);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        icon = createImageIcon(icon_path,"icon");
        kill_icon = createImageIcon(kill_icon_path, "kill_icon");
        main_panel = new JPanel();
        action_panel = new JPanel();
        main_panel.setLayout(new GridLayout(10,10));
        action_panel.setLayout(new BoxLayout(action_panel,BoxLayout.LINE_AXIS));
        buttonList = new ArrayList<JButton>();
        base_icon = createImageIcon("textures/base.jpg","base_icon");

        for(int i=0;i<100;i++) {
            JButton button = new JButton();

            button.setIcon(base_icon);


            buttonList.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(action[0]);
                    if (action[0] != null) {
                        if (action[0] == "populate")

                            button.setIcon(icon);
                        else
                            button.setIcon(kill_icon);
                    } else
                        action[0] = null;
                }
            });
            main_panel.add(button);
        }
//
        JButton populate = new JButton();
        populate.setText("Populate");
        populate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    action[0] = "populate";
            }
        });


        JButton kill = new JButton();
        kill.setText("Kill");
        kill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action[0] = "kill";
            }
        });

        action_panel.add(populate);
        action_panel.add(Box.createHorizontalGlue());
        action_panel.add(kill);

        this.add(main_panel);
        this.add(action_panel, BorderLayout.PAGE_END);
        this.setSize(500, 500);
        this.setTitle(client_name);
        this.setVisible(true);



    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry(2099);
        Moves moves = (Moves) reg.lookup(Moves.NAME);
        String name_on_create = null;
        String icon, kill_icon;

        int resp = moves.check_clients(name_on_create);
        if( resp == 0) {
            name_on_create = "red";
            icon = "textures/cross_edge.jpg";
            kill_icon = "textures/done_cir.jpg";
        }
        else {
            name_on_create = "blue";
            icon = "textures/circle_edge.jpg";
            kill_icon = "textures/done_cross.jpg";
        }
        new Game_GUI(moves, name_on_create, icon, kill_icon);
    }
}
