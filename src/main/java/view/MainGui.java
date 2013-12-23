/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package view;

import controller.MainController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame implements Runnable{

    private static final long serialVersionUID = -8588752708534498496L;

    private JMenuBar menuBar;

    private JMenu menuGame;

    private JMenuItem menuItemToggle;

    private JPanel pnlPreGame;
    private JPanel pnlGame;
    private JPanel pnlButtons;

    private JLabel lblBackground;

    private JButton btnPlay;
    private JButton btnOptions;

    private MainController controller;
    private boolean initialized;

    private void initializeComponents() {
        menuBar = new JMenuBar();

        menuGame = new JMenu("Game");

        menuItemToggle = new JMenuItem("Start");

        menuGame.add(menuItemToggle);
        menuBar.add(menuGame);

        pnlPreGame = new JPanel();
        pnlPreGame.setLayout(new BorderLayout());

        pnlGame = new JPanel();
        pnlGame.setLayout(new FlowLayout());

        lblBackground = new JLabel(new ImageIcon(this.getClass().getResource("/graphics/background/main_background.png")));
        lblBackground.setLayout(new FlowLayout());

        pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout());
        pnlButtons.setOpaque(false);

        Font fontBtn = new Font("Agency FB", Font.PLAIN, 22);

        btnPlay = new JButton("Play");
        btnPlay.setFont(fontBtn);

        btnOptions = new JButton("Options");
        btnOptions.setFont(fontBtn);

        pnlButtons.add(btnPlay);
        Dimension dim = new Dimension(200, 0);
        pnlButtons.add(new Box.Filler(dim, dim, dim));
        pnlButtons.add(btnOptions);
        lblBackground.add(pnlButtons);

        pnlPreGame.add(lblBackground, BorderLayout.CENTER);

        getContentPane().add(pnlPreGame);
        setJMenuBar(menuBar);
    }

    private void initializeListeners() {
        ActionListener listenerToggle = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.isGameActive()){
                    menuItemToggle.setText("Start");
                    btnPlay.setText("Play");
                    controller.pauseGame();
                }else {
                    menuItemToggle.setText("Pause");
                    btnPlay.setText("Pause");
                    controller.startGame();
                }
                repaint();
            }

        };

        btnPlay.addActionListener(listenerToggle);
        menuItemToggle.addActionListener(listenerToggle);
    }

    public void startGame() {
        if(!this.initialized){
            this.initialize();
        }

        getContentPane().removeAll();
        getContentPane().add(pnlGame);
    }

    public void pauseGame() {
        if(!this.initialized){
            this.initialize();
        }

        getContentPane().removeAll();
        getContentPane().add(pnlPreGame);
    }

    @Override
    public void run() {
        if(!this.initialized){
            this.initialize();
        }
    }

    private synchronized void initialize() {
        this.initialized = true;

        controller = MainController.getInstance();

        setTitle("Pacman");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        initializeComponents();
        initializeListeners();

        setVisible(true);
    }
}
