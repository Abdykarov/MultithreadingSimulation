package com.cvut.simulation.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private JButton btnNew;
    private JButton btnLoad;
    private JPanel mainPanel;

    MainMenu(JPanel mainPanel, CardLayout ca){
        this.mainPanel = mainPanel;
        setLayout(null);

        btnNew = new JButton("new simulation");
        btnNew.setLocation(450, 250);
        btnNew.setSize(150, 60);
        this.add(btnNew);

        btnLoad = new JButton("load simulation");
        btnLoad.setLocation(450, 350);
        btnLoad.setSize(150, 60);
        this.add(btnLoad);

        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "params");
            }
        });

        setOpaque(true);
        setBackground(new Color(40, 40, 40));
    }

}
