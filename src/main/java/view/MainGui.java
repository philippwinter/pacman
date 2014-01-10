/******************************************************************************
 * This work is applicable to the conditions of the MIT License,              *
 * which can be found in the LICENSE file, or at                              *
 * https://github.com/philippwinter/pacman/blob/master/LICENSE                *
 *                                                                            *
 * Copyright (c) 2013 Philipp Winter, Jonas Heidecke & Niklas Kaddatz         *
 ******************************************************************************/

package view;

import controller.MainController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MainGui extends JFrame {

    private static final long serialVersionUID = 6812319670817799344L;

    private JMenuBar menuBar;

    private JMenu menuGame;

    private JMenuItem menuItemToggleGameState;
    private JMenuItem menuItemToggleOptions;

    private JPanel pnlPreGame;
    private JPanel pnlGame;
    private JPanel pnlOptions;

    private JPanel pnlButtons;

    private JLabel lblBackground;

    private JButton btnPlay;
    private JButton btnOptions;

    private final MainController controller;
    private final ImageOrganizer imgOrganizer;

    private boolean initialized;

    private boolean optionsShown = false;

    private final Renderer renderer;

    public MainGui() {
        controller = MainController.getInstance();
        imgOrganizer = ImageOrganizer.getInstance();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        renderer = new Renderer(this);

        this.initialize();
    }

    private void initializeComponents() {

        menuBar = new JMenuBar();

        menuGame = new JMenu("Game");

        menuItemToggleGameState = new JMenuItem("Start");
        menuItemToggleOptions = new JMenuItem("Options");

        menuGame.add(menuItemToggleGameState);
        menuGame.add(menuItemToggleOptions);

        menuBar.add(menuGame);

        pnlPreGame = new JPanel();
        pnlPreGame.setLayout(new BorderLayout());

        pnlGame = new JPanel();
        pnlGame.setLayout(new FlowLayout());

        pnlOptions = new JPanel();
        pnlOptions.setLayout(new FlowLayout());

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
        ActionListener toggleGameStateListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.isGameActive()) {
                    controller.pauseGame();
                } else {
                    controller.startGame();
                }
                repaint();
            }

        };

        ActionListener toggleOptionsListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.isGameActive()) {
                    controller.pauseGame();
                }

                if (!optionsShown) {
                    showOptions();
                } else {
                    showPreGame();
                }

                optionsShown = !optionsShown;
                repaint();
            }

        };

        btnPlay.addActionListener(toggleGameStateListener);
        menuItemToggleGameState.addActionListener(toggleGameStateListener);

        btnOptions.addActionListener(toggleOptionsListener);
        menuItemToggleOptions.addActionListener(toggleOptionsListener);

        KeyListener keyEventListener = new KeyboardProcesser();
        this.addKeyListener(keyEventListener);
        pnlGame.addKeyListener(keyEventListener);
    }

    public void showOptions() {
        getContentPane().removeAll();
        getContentPane().add(pnlOptions);
        setTitle("Pacman: Options");
    }

    public void showGame() {
        menuItemToggleGameState.setText("Pause");
        btnPlay.setText("Pause");
        getContentPane().removeAll();
        getContentPane().add(pnlGame);
        setTitle("Pacman: Game running");
    }

    public void showPreGame() {
        menuItemToggleGameState.setText("Start");
        btnPlay.setText("Play");
        getContentPane().removeAll();
        getContentPane().add(pnlPreGame);
        setTitle("Pacman: Game paused");
    }

    private synchronized void initialize() {
        if (!this.initialized) {
            setTitle("Pacman");

            try {
                setIconImage(ImageIO.read(this.getClass().getResource("/graphics/pacman/4.png")));
            } catch (IOException e) {
                MainController.uncaughtExceptionHandler.uncaught(e);
            }

            int windowWidth = renderer.mapWidth + (renderer.leftSpace * 2);
            int windowHeight = renderer.mapHeight + (renderer.topSpace * 3);

            setSize(windowWidth, windowHeight);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            getContentPane().setLayout(new BorderLayout());

            initializeComponents();
            initializeListeners();

            setVisible(true);

            this.initialized = true;
        }
    }

    public JPanel getPnlGame() {
        return pnlGame;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
