package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RuchomySprite::new);
    }
}
 class PanelGry extends JPanel {
    private static final long serialVersionUID = 1L;
    BufferedImage obrazek;
    int x, y, r;

    public PanelGry(Dimension d, String sciezka) {
        super();
        setPreferredSize(d);
        setBackground(Color.white);

        this.x = 0;
        this.y = 0;
        try {
            this.obrazek = ImageIO.read(new File(sciezka));
            this.r = obrazek.getWidth();
            if (obrazek.getWidth() != obrazek.getHeight()) {
                throw new IllegalArgumentException();
            }
        } catch (IOException | IllegalArgumentException e) {
            this.obrazek = null;
            this.r = 50;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (obrazek != null) {
            g.drawImage(obrazek, x, y, null);
        } else {
            g.setColor(Color.red);
            g.fillOval(x, y, r, r);
        }
    }
}
 class RuchomySprite extends JFrame implements KeyListener, ActionListener {
    private static final long serialVersionUID = 1L;
    JButton btnLewo;
    JButton btnPrawo;
    JButton btnGora;
    JButton btnDol;
    PanelGry p;

    public RuchomySprite() {
        super("Ruchomy Duszek");


        p = new PanelGry(new Dimension(400, 400), "src/resources/celownik.png");


        JPanel btnPanel = new JPanel(new FlowLayout());

        btnLewo = new JButton("Lewo ");
        btnLewo.addActionListener(this);
        btnPanel.add(btnLewo);

        btnPrawo = new JButton("Prawo");
        btnPrawo.addActionListener(this);
        btnPanel.add(btnPrawo);

        btnGora = new JButton("Gora");
        btnGora.addActionListener(this);
        btnPanel.add(btnGora);

        btnDol = new JButton("Dol");
        btnDol.addActionListener(this);
        btnPanel.add(btnDol);


        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(p, BorderLayout.CENTER);
        cp.add(btnPanel, BorderLayout.SOUTH);


        addKeyListener(this);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLewo) {
            p.x -= 10;
        } else if (e.getSource() == btnPrawo) {
            p.x += 10;
        } else if (e.getSource() == btnGora) {
            p.y -= 10;
        } else if (e.getSource() == btnDol) {
            p.y += 10;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                p.x -= 10;
                break;
            case KeyEvent.VK_RIGHT:
                p.x += 10;
                break;
            case KeyEvent.VK_UP:
                p.y -= 10;
                break;
            case KeyEvent.VK_DOWN:
                p.y += 10;
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}