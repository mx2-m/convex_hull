package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Main {


    private JLabel algorithm;
    private JLabel placementOfPoints;
    private JLabel numberOfPoints;
    private JPanel panelMain;
    //algoritmi
    private JRadioButton jarvis;
    private JRadioButton grahams;
    private JRadioButton quickHull;
    //raspored tocki
    private JRadioButton normal;
    private JRadioButton evenly;
    private JTextField number;

    private JButton calculateBtn;
    private JButton generatePointsBtn;
    ButtonGroup points = new ButtonGroup();

    DrawArea drawArea = new DrawArea();
    JarvisMarch jarvisMarch = new JarvisMarch();
    GrahamsScan grahamsScan = new GrahamsScan();
    QuickHull quickHullAlgorithm = new QuickHull();


    ActionListener generatingPoints = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int n;
            drawArea.clear();

            if (normal.isSelected()) {
                n = Integer.parseInt(number.getText());

                drawArea.drawingPointsNormaly(n);

            } else if (evenly.isSelected()) {
                n = Integer.parseInt(number.getText());

                drawArea.drawingPointsEvenly(n);

            }

        }
    };

    ActionListener solution = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jarvis.isSelected()) {

                ArrayList<Point> solution;
                long startTime = System.nanoTime();

                solution = jarvisMarch.jarvisMarch(drawArea.getPoints());

                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;

                int size = solution.size() - 1;
                drawArea.drawingDaljice(solution);
                drawArea.drawingTocki(solution.get(0).x, solution.get(0).y);
                drawArea.drawingDaljica(solution.get(size).x, solution.get(size).y, solution.get(0).x, solution.get(0).y);
                JOptionPane.showMessageDialog(panelMain, "Cas v milisekundama: " + timeElapsed / 1000000, "Cas", JOptionPane.INFORMATION_MESSAGE);

                solution.clear();
            } else if (grahams.isSelected()) {
                ArrayList<Point> solution;
                long startTime = System.nanoTime();
                solution = grahamsScan.grahamsScan(drawArea.getPoints());
                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;
                int size = solution.size() - 1;
                drawArea.drawingDaljice(solution);
                drawArea.drawingTocki(solution.get(0).x, solution.get(0).y);
                drawArea.drawingDaljica(solution.get(size).x, solution.get(size).y, solution.get(0).x, solution.get(0).y);
                JOptionPane.showMessageDialog(panelMain, "Cas v milisekundama: " + timeElapsed / 1000000, "Cas", JOptionPane.INFORMATION_MESSAGE);
               // drawArea.drawingTocki(grahamsScan.getO().x, grahamsScan.getO().y);

                solution.clear();


            } else if (quickHull.isSelected()) {
                ArrayList<Point> solution;
                long startTime = System.nanoTime();

                solution = quickHullAlgorithm.quickHull(drawArea.getPoints());

                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;
                int size = solution.size() - 1;
                drawArea.drawingDaljice(solution);
                drawArea.drawingDaljica(solution.get(size).x, solution.get(size).y, solution.get(0).x, solution.get(0).y);
                JOptionPane.showMessageDialog(panelMain, "Cas v milisekundama: " + timeElapsed / 1000000, "Cas", JOptionPane.INFORMATION_MESSAGE);

                solution.clear();

            }

        }
    };

    public void show() {

        // create main frame
        JFrame frame = new JFrame("App");
        Container content = frame.getContentPane();
        // set layout on content pane
        content.setLayout(new BorderLayout());

        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(16, 1));

        calculateBtn = new JButton("Izracunaj lupino");
        calculateBtn.addActionListener(solution);
        generatePointsBtn = new JButton("Generiraj tocke");
        generatePointsBtn.addActionListener(generatingPoints);

        number = new JTextField(5);
        number.setText("100");
        algorithm = new JLabel("Algoritmi: ");
        placementOfPoints = new JLabel("Porazdelitev tock: ");
        numberOfPoints = new JLabel("St. navkljucnih tock: ");

        jarvis = new JRadioButton("Jarvisov obhod");
        grahams = new JRadioButton("Grahamovo prebiranje");
        quickHull = new JRadioButton("Hitra konveksna lupina");

        ButtonGroup algorithams = new ButtonGroup();
        algorithams.add(jarvis);
        algorithams.add(grahams);
        algorithams.add(quickHull);

        normal = new JRadioButton("Normalna");
        evenly = new JRadioButton("Enakomerno");

        points.add(normal);
        points.add(evenly);

        controls.add(algorithm);
        controls.add(jarvis);
        controls.add(grahams);
        controls.add(quickHull);
        controls.add(placementOfPoints);
        controls.add(normal);
        controls.add(evenly);
        controls.add(numberOfPoints);
        controls.add(number);
        controls.add(generatePointsBtn);
        controls.add(calculateBtn);

        // add to content pane
        content.add(controls, BorderLayout.WEST);
        content.add(drawArea, BorderLayout.CENTER);
        //content.add(controls, BorderLayout.SOUTH);

        frame.setSize(850, 700);
        // can close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show
        frame.setVisible(true);


    }


    public static void main(String[] args) {
        // write your code here
        new Main().show();
    }
}
