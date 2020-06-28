import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class PhotoViewer extends JFrame {

    private PhotographContainer imageAlbum;

    private int albumPosition = 0;

    private JPanel thumbnailPanel;
    private JPanel imagePanel;       // panel variables
    private JPanel ratingPanel;
    private JPanel functionPanel;

    private JPanel thumbnailSubPanel1;
    private JPanel thumbnailSubPanel2; // subpanels to go on the thumbnail panel
    private JPanel thumbnailSubPanel3;
    private JPanel thumbnailSubPanel4;
    private JPanel thumbnailSubPanel5;

    private ButtonGroup buttonGroup;
    private ArrayList<JRadioButton> radioButtonList;

    private JRadioButton radio1;
    private JRadioButton radio2;   // radio button variables
    private JRadioButton radio3;
    private JRadioButton radio4;
    private JRadioButton radio5;

    private JButton dateButton;
    private JButton captionButton;
    private JButton ratingButton;   // function button variables
    private JButton nextButton;
    private JButton previousButton;

    private JLabel picLabel1;

    private JLabel thumbPicLabel1;
    private JLabel thumbPicLabel2; // labels to be put on the subpanels so that an imageicon can be added
    private JLabel thumbPicLabel3;
    private JLabel thumbPicLabel4;
    private JLabel thumbPicLabel5;

    private JLabel thumbnailCaption1;
    private JLabel thumbnailDate1;
    private JLabel thumbnailRating1;

    public PhotoViewer() {

        setBounds(100, 100, 450, 300);

        // Set what happens when you click the close button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Give it a layout: border = N, S, E, W, and Center
        getContentPane().setLayout(new BorderLayout(0, 0));         // add a layout to the content pane and frame for organization

        JPanel ratingPanel = new JPanel();
        ratingPanel.setBackground(Color.WHITE);
        getContentPane().add(ratingPanel, BorderLayout.SOUTH);
        radio1 = new JRadioButton("1");
        radio2 = new JRadioButton("2");
        radio3 = new JRadioButton("3");
        radio4 = new JRadioButton("4");
        radio5 = new JRadioButton("5");
        ButtonGroup buttonGroup = new ButtonGroup();         // create a button group for the radio buttons to be added to so that
                                                             // only one can be selected at a time and they respond together
        ratingPanel.add(radio1);
        ratingPanel.add(radio2);
        ratingPanel.add(radio3);
        ratingPanel.add(radio4);                            // add radio buttons to button grou[
        ratingPanel.add(radio5);
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);
        buttonGroup.add(radio4);
        buttonGroup.add(radio5);
        radioButtonList = new ArrayList<>();
        radioButtonList.add(radio1);                     // add radio buttons to an arrayList so they cna bea easily accessed
                                                         // later for implemented function buttons and listener responses
        radioButtonList.add(radio2);
        radioButtonList.add(radio3);
        radioButtonList.add(radio4);
        radioButtonList.add(radio5);

        imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        getContentPane().add(imagePanel, BorderLayout.CENTER);

        functionPanel = new JPanel();
        functionPanel.setBackground(Color.WHITE);
        getContentPane().add(functionPanel, BorderLayout.NORTH);
        dateButton = new JButton("Date");
        captionButton = new JButton("Caption");                  // initialize function buttons
        ratingButton = new JButton("Rating");
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        functionPanel.add(dateButton);
        functionPanel.add(captionButton);                          // add function buttons to the function panel
        functionPanel.add(ratingButton);
        functionPanel.add(nextButton);
        functionPanel.add(previousButton);

        GridLayout thumbnailGridLayout = new GridLayout(0, 1);            // create the grid layout to be added to the west
                                                                          // thumbnail panel so that sub panels can be neatly
                                                                          // added
        thumbnailPanel = new JPanel();
        thumbnailPanel.setLayout(thumbnailGridLayout);
        // thumbnailPanel = new JPanel();
        thumbnailPanel.setBackground(Color.WHITE);
        getContentPane().add(thumbnailPanel, BorderLayout.WEST);

        thumbnailSubPanel1 = new JPanel();
        thumbnailSubPanel2 = new JPanel();                  // create subPanels for the west panel
        thumbnailSubPanel3 = new JPanel();
        thumbnailSubPanel4 = new JPanel();
        thumbnailSubPanel5 = new JPanel();
        thumbnailPanel.add(thumbnailSubPanel1, thumbnailGridLayout);
        thumbnailPanel.add(thumbnailSubPanel2, thumbnailGridLayout); // add subpanels to the west panel with a grid layout
        thumbnailPanel.add(thumbnailSubPanel3, thumbnailGridLayout);
        thumbnailPanel.add(thumbnailSubPanel4, thumbnailGridLayout);
        thumbnailPanel.add(thumbnailSubPanel5, thumbnailGridLayout);

        dateButton.setActionCommand("date");
        dateButton.addActionListener(new dateButtonListener());
        captionButton.setActionCommand("caption");                             // add listeners and sets commands for each of the
                                                                               // function buttons
        captionButton.addActionListener(new captionButtonListener());
        ratingButton.setActionCommand("rating");
        ratingButton.addActionListener(new ratingButtonListener());
        nextButton.setActionCommand("next");
        nextButton.addActionListener(new nextButtonListener());
        previousButton.setActionCommand("previous");
        previousButton.addActionListener(new previousButtonListener());
        radio1.setActionCommand("radio1");                                 // add listeners and set action command for radio
                                                                           // buttons
        radio2.setActionCommand("radio2");
        radio3.setActionCommand("radio3");
        radio4.setActionCommand("radio4");
        radio5.setActionCommand("radio5");
        radio1.addActionListener(new radioButtonListener());
        radio2.addActionListener(new radioButtonListener());
        radio3.addActionListener(new radioButtonListener());
        radio4.addActionListener(new radioButtonListener());
        radio5.addActionListener(new radioButtonListener());

        thumbnailSubPanel1.addMouseListener(new MouseEventListener1());
        thumbnailSubPanel2.addMouseListener(new MouseEventListener2());  // adds the correct mouse listener to each panel
        thumbnailSubPanel3.addMouseListener(new MouseEventListener3());
        thumbnailSubPanel4.addMouseListener(new MouseEventListener4());
        thumbnailSubPanel5.addMouseListener(new MouseEventListener5());

        String imageDirectory = "C:\\Users\\student\\OneDrive\\Documents\\Workspace\\Homework5\\images\\"; // variable used to
                                                                                                           // simplify the
                                                                                                           // retrieving of photos
                                                                                                           // from a directory

        imageAlbum = new Album("a");

        Photograph p1 = new Photograph(imageDirectory + "frisbeeDog.jpg", "frisbee!", "2019-06-15", 3);
        Photograph p2 = new Photograph(imageDirectory + "Dog5.jpg", "I'm a dog!", "2018-07-30", 2);      // defines the
                                                                                                         // information for the
                                                                                                         // photograph objects
        Photograph p3 = new Photograph(imageDirectory + "Dog2.jpg", "summer!", "2019-01-03", 4);
        Photograph p4 = new Photograph(imageDirectory + "Dog3.jpg", "sup!", "2015-09-22", 3);
        Photograph p5 = new Photograph(imageDirectory + "Dog4.jpg", "hike!", "2016-02-17", 5);

        imageAlbum.addPhoto(p1);
        imageAlbum.addPhoto(p2); // adds photos to the imageAlbum arrayList
        imageAlbum.addPhoto(p3);
        imageAlbum.addPhoto(p4);
        imageAlbum.addPhoto(p5);

        int radioButtonNumber = imageAlbum.photos.get((0 + albumPosition) % 5).getRating();
        radioButtonList.get(radioButtonNumber - 1).setSelected(true);

        try { // why this? Checked Exception (what if file doesn't exist!)
              // Note that the filenames without a path are in the root of our project (drag/drop the file)

            BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get(0).getFilename()));
            BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get(1).getFilename()));
            BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get(2).getFilename())); // retrive pictures from
                                                                                                       // album
            BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get(3).getFilename()));
            BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get(4).getFilename()));

            Image newImage1 = myPicture1.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

            Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail size
            Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

            picLabel1 = new JLabel(new ImageIcon(newImage1));
            picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(0).getRating());

            thumbPicLabel1 = new JLabel(new ImageIcon(newThumbnail1));
            thumbPicLabel2 = new JLabel(new ImageIcon(newThumbnail2)); // put thumbnail pictures on a label
            thumbPicLabel3 = new JLabel(new ImageIcon(newThumbnail3));
            thumbPicLabel4 = new JLabel(new ImageIcon(newThumbnail4));
            thumbPicLabel5 = new JLabel(new ImageIcon(newThumbnail5));

            thumbPicLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(0).getRating());
            thumbPicLabel2.setText(imageAlbum.photos.get(1).getCaption() + " Date: " + imageAlbum.photos.get(1).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(1).getRating()); // set the caption and rating for each thumbnail
            thumbPicLabel3.setText(imageAlbum.photos.get(2).getCaption() + " Date: " + imageAlbum.photos.get(2).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(2).getRating());
            thumbPicLabel4.setText(imageAlbum.photos.get(3).getCaption() + " Date: " + imageAlbum.photos.get(3).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(3).getRating());
            thumbPicLabel5.setText(imageAlbum.photos.get(4).getCaption() + " Date: " + imageAlbum.photos.get(4).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(4).getRating());

            imagePanel.add(picLabel1);

            // thumbnailPanel.add(thumbPicLabel1);
            // thumbnailPanel.add(thumbPicLabel2); //add thumbnail labels to thumbnail panel
            // thumbnailPanel.add(thumbPicLabel3);
            // thumbnailPanel.add(thumbPicLabel4);
            // thumbnailPanel.add(thumbPicLabel5);

            thumbnailSubPanel1.add(thumbPicLabel1);
            thumbnailSubPanel2.add(thumbPicLabel2); // add thumbnail labels to thumbnail panel
            thumbnailSubPanel3.add(thumbPicLabel3);
            thumbnailSubPanel4.add(thumbPicLabel4);
            thumbnailSubPanel5.add(thumbPicLabel5);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // main method used to call constructor and catch any potential exceptions
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    PhotoViewer window = new PhotoViewer();

                    // Resize to fit content
                    // window.pack();

                    // Display the Window
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // TODO Auto-generated method stub
        PhotoViewer myViewer = new PhotoViewer();

    }

    private class dateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("date")) {
                Collections.sort(imageAlbum.photos);
            }

            resetImages(); // uses helper function to reset the display correctly

            int radioButtonNumber = imageAlbum.photos.get((0 + albumPosition) % 5).getRating();  // retrieves the rating to
                                                                                                 // correctly set the radio button
                                                                                                 // that needs to be selected
            radioButtonList.get(radioButtonNumber - 1).setSelected(true);

        }

    }

    private class captionButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("caption")) {
                Collections.sort(imageAlbum.photos, new CompareByCaption());  // sort arrayList to be in order of caption
                                                                              // alphabetically
            }

            resetImages();

            int radioButtonNumber = imageAlbum.photos.get((0 + albumPosition) % 5).getRating();
            radioButtonList.get(radioButtonNumber - 1).setSelected(true);
        }

    }

    private class ratingButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // part 1
            if (e.getActionCommand().equals("rating")) {
                Collections.sort(imageAlbum.photos, new CompareByRating()); // sort arrayList to be in order of rating with higher
                                                                            // rated items being displayed first
            }

            resetImages();

            int radioButtonNumber = imageAlbum.photos.get((0 + albumPosition) % 5).getRating();
            radioButtonList.get(radioButtonNumber - 1).setSelected(true);

        }

    }

    private class nextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            albumPosition = albumPosition + 1; // variable used to move about the starting point when next or previous buttons are
                                               // pressed.

            try {
                BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get((0 + albumPosition) % 5).getFilename()));
                BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get((1 + albumPosition) % 5).getFilename()));
                BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get((2 + albumPosition) % 5).getFilename())); // retrive
                                                                                                                                 // pictures
                                                                                                                                 // from
                                                                                                                                 // album
                BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get((3 + albumPosition) % 5).getFilename()));
                BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get((4 + albumPosition) % 5).getFilename())); // uses
                                                                                                                                 // modulo
                                                                                                                                 // to
                                                                                                                                 // prevent
                                                                                                                                 // array
                                                                                                                                 // out
                                                                                                                                 // of
                                                                                                                                 // bounds
                                                                                                                                 // errors
                                                                                                                                 // and
                                                                                                                                 // loop
                                                                                                                                 // back
                                                                                                                                 // around

                Image newImage1 = myPicture1.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

                Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail
                                                                                                   // size
                Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

                thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
                thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
                thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));  // allows photos to be added to the labels on the subpanels
                                                                       // as thumbnail pictures
                thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
                thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

                picLabel1.setIcon(new ImageIcon(newImage1));
                picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());

            } catch (Exception f) { // catches any exceptions that may occur when accessing photos
                f.printStackTrace();
            }

            int radioButtonNumber = imageAlbum.photos.get((0 + albumPosition) % 5).getRating();   // set the correct radio button
                                                                                                  // to be selected based on the
                                                                                                  // rating of the main photo
            radioButtonList.get(radioButtonNumber - 1).setSelected(true);

        }

    }

    private class previousButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // part 1
            albumPosition = albumPosition - 1;
            try {
                BufferedImage myPicture1 = ImageIO
                        .read(new File(imageAlbum.photos.get(Math.abs((0 + albumPosition) % 5)).getFilename()));
                BufferedImage myPicture2 = ImageIO
                        .read(new File(imageAlbum.photos.get(Math.abs((1 + albumPosition) % 5)).getFilename()));
                BufferedImage myPicture3 = ImageIO
                        .read(new File(imageAlbum.photos.get(Math.abs((2 + albumPosition) % 5)).getFilename())); // retrive
                                                                                                                 // pictures from
                                                                                                                 // album
                BufferedImage myPicture4 = ImageIO
                        .read(new File(imageAlbum.photos.get(Math.abs((3 + albumPosition) % 5)).getFilename()));
                BufferedImage myPicture5 = ImageIO
                        .read(new File(imageAlbum.photos.get(Math.abs((4 + albumPosition) % 5)).getFilename()));

                Image newImage1 = myPicture1.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

                Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail
                                                                                                   // size
                Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

                thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
                thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
                thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));
                thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
                thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

                picLabel1.setIcon(new ImageIcon(newImage1));
                picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());

            } catch (Exception f) {
                f.printStackTrace();
            }

            int radioButtonNumber = imageAlbum.photos.get((0 + albumPosition) % 5).getRating();
            radioButtonList.get(radioButtonNumber - 1).setSelected(true);

        }

    }

    private class radioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // part 1
            if (e.getActionCommand().equals("radio1")) {
                imageAlbum.photos.get(0).setRating(1);
            }
            if (e.getActionCommand().equals("radio2")) {   // if statements checking to see which radio buttons are pressed
                                                           // resetting the rating according to the number of the button pressed
                imageAlbum.photos.get(0).setRating(2);
            }
            if (e.getActionCommand().equals("radio3")) {
                imageAlbum.photos.get(0).setRating(3);
            }
            if (e.getActionCommand().equals("radio4")) {
                imageAlbum.photos.get(0).setRating(4);
            }
            if (e.getActionCommand().equals("radio5")) {
                imageAlbum.photos.get(0).setRating(5);
            }
        }

    }

    private class MouseEventListener1 implements MouseListener {

        public void mousePressed(MouseEvent e) {
            // Other mouseListener methods do not need to be implmented
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

            try {
                BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get(0).getFilename()));
                BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get(1).getFilename()));
                BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get(2).getFilename())); // retrive pictures
                                                                                                           // from album
                BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get(3).getFilename()));
                BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get(4).getFilename()));

                Image newImage1 = myPicture1.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

                Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail
                                                                                                   // size
                Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

                thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
                thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
                thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));
                thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
                thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

                thumbPicLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());
                thumbPicLabel2.setText(imageAlbum.photos.get(1).getCaption() + " Date: " + imageAlbum.photos.get(1).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(1).getRating());
                thumbPicLabel3.setText(imageAlbum.photos.get(2).getCaption() + " Date: " + imageAlbum.photos.get(2).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(2).getRating());
                thumbPicLabel4.setText(imageAlbum.photos.get(3).getCaption() + " Date: " + imageAlbum.photos.get(3).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(3).getRating());
                thumbPicLabel5.setText(imageAlbum.photos.get(4).getCaption() + " Date: " + imageAlbum.photos.get(4).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(4).getRating());

                picLabel1.setIcon(new ImageIcon(newImage1));
                picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());

            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    //uses a different mouse Listener for each thumbnail so the mouse listener knows which click on which thumbnail to respond to and implement the correct action
    private class MouseEventListener2 implements MouseListener {

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

            try {
                BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get(0).getFilename()));
                BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get(1).getFilename()));
                BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get(2).getFilename())); // retrive pictures
                                                                                                           // from album
                BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get(3).getFilename()));
                BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get(4).getFilename()));

                Image newImage1 = myPicture2.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

                Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail
                                                                                                   // size
                Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

                thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
                thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
                thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));
                thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
                thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

                thumbPicLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());
                thumbPicLabel2.setText(imageAlbum.photos.get(1).getCaption() + " Date: " + imageAlbum.photos.get(1).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(1).getRating());
                thumbPicLabel3.setText(imageAlbum.photos.get(2).getCaption() + " Date: " + imageAlbum.photos.get(2).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(2).getRating());
                thumbPicLabel4.setText(imageAlbum.photos.get(3).getCaption() + " Date: " + imageAlbum.photos.get(3).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(3).getRating());
                thumbPicLabel5.setText(imageAlbum.photos.get(4).getCaption() + " Date: " + imageAlbum.photos.get(4).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(4).getRating());

                picLabel1.setIcon(new ImageIcon(newImage1));
                picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());

            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    private class MouseEventListener3 implements MouseListener {

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

            try {
                BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get(0).getFilename()));
                BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get(1).getFilename()));
                BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get(2).getFilename())); // retrive pictures
                                                                                                           // from album
                BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get(3).getFilename()));
                BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get(4).getFilename()));

                Image newImage1 = myPicture3.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

                Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail
                                                                                                   // size
                Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

                thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
                thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
                thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));
                thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
                thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

                thumbPicLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());
                thumbPicLabel2.setText(imageAlbum.photos.get(1).getCaption() + " Date: " + imageAlbum.photos.get(1).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(1).getRating());
                thumbPicLabel3.setText(imageAlbum.photos.get(2).getCaption() + " Date: " + imageAlbum.photos.get(2).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(2).getRating());
                thumbPicLabel4.setText(imageAlbum.photos.get(3).getCaption() + " Date: " + imageAlbum.photos.get(3).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(3).getRating());
                thumbPicLabel5.setText(imageAlbum.photos.get(4).getCaption() + " Date: " + imageAlbum.photos.get(4).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(4).getRating());

                picLabel1.setIcon(new ImageIcon(newImage1));
                picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());

            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    private class MouseEventListener4 implements MouseListener {

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

            try {
                BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get(0).getFilename()));
                BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get(1).getFilename()));
                BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get(2).getFilename())); // retrive pictures
                                                                                                           // from album
                BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get(3).getFilename()));
                BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get(4).getFilename()));

                Image newImage1 = myPicture4.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

                Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail
                                                                                                   // size
                Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

                thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
                thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
                thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));
                thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
                thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

                thumbPicLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());
                thumbPicLabel2.setText(imageAlbum.photos.get(1).getCaption() + " Date: " + imageAlbum.photos.get(1).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(1).getRating());
                thumbPicLabel3.setText(imageAlbum.photos.get(2).getCaption() + " Date: " + imageAlbum.photos.get(2).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(2).getRating());
                thumbPicLabel4.setText(imageAlbum.photos.get(3).getCaption() + " Date: " + imageAlbum.photos.get(3).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(3).getRating());
                thumbPicLabel5.setText(imageAlbum.photos.get(4).getCaption() + " Date: " + imageAlbum.photos.get(4).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(4).getRating());

                picLabel1.setIcon(new ImageIcon(newImage1));
                picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());

            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    private class MouseEventListener5 implements MouseListener {

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

            try {
                BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get(0).getFilename()));
                BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get(1).getFilename()));
                BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get(2).getFilename())); // retrive pictures
                                                                                                           // from album
                BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get(3).getFilename()));
                BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get(4).getFilename()));

                Image newImage1 = myPicture5.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

                Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail
                                                                                                   // size
                Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

                thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
                thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
                thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));
                thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
                thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

                thumbPicLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());
                thumbPicLabel2.setText(imageAlbum.photos.get(1).getCaption() + " Date: " + imageAlbum.photos.get(1).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(1).getRating());
                thumbPicLabel3.setText(imageAlbum.photos.get(2).getCaption() + " Date: " + imageAlbum.photos.get(2).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(2).getRating());
                thumbPicLabel4.setText(imageAlbum.photos.get(3).getCaption() + " Date: " + imageAlbum.photos.get(3).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(3).getRating());
                thumbPicLabel5.setText(imageAlbum.photos.get(4).getCaption() + " Date: " + imageAlbum.photos.get(4).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(4).getRating());

                picLabel1.setIcon(new ImageIcon(newImage1));
                picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                        + " Rating: " + imageAlbum.photos.get(0).getRating());

            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    /**
     * Helper function used to set and display the images each time a function/sort button is pressed
     */
    public void resetImages() {
        try {
            BufferedImage myPicture1 = ImageIO.read(new File(imageAlbum.photos.get(0).getFilename()));
            BufferedImage myPicture2 = ImageIO.read(new File(imageAlbum.photos.get(1).getFilename()));
            BufferedImage myPicture3 = ImageIO.read(new File(imageAlbum.photos.get(2).getFilename())); // retrive pictures from
                                                                                                       // album
            BufferedImage myPicture4 = ImageIO.read(new File(imageAlbum.photos.get(3).getFilename()));
            BufferedImage myPicture5 = ImageIO.read(new File(imageAlbum.photos.get(4).getFilename()));

            Image newImage = myPicture2.getScaledInstance(1280, 1024, Image.SCALE_DEFAULT);

            Image newThumbnail1 = myPicture1.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image newThumbnail2 = myPicture2.getScaledInstance(100, 100, Image.SCALE_DEFAULT); // scale images for thumbnail size
            Image newThumbnail3 = myPicture3.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image newThumbnail4 = myPicture4.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            Image newThumbnail5 = myPicture5.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

            thumbPicLabel1.setIcon(new ImageIcon(newThumbnail1));
            thumbPicLabel2.setIcon(new ImageIcon(newThumbnail2));
            thumbPicLabel3.setIcon(new ImageIcon(newThumbnail3));
            thumbPicLabel4.setIcon(new ImageIcon(newThumbnail4));
            thumbPicLabel5.setIcon(new ImageIcon(newThumbnail5));

            thumbPicLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(0).getRating());
            thumbPicLabel2.setText(imageAlbum.photos.get(1).getCaption() + " Date: " + imageAlbum.photos.get(1).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(1).getRating());
            thumbPicLabel3.setText(imageAlbum.photos.get(2).getCaption() + " Date: " + imageAlbum.photos.get(2).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(2).getRating());
            thumbPicLabel4.setText(imageAlbum.photos.get(3).getCaption() + " Date: " + imageAlbum.photos.get(3).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(3).getRating());
            thumbPicLabel5.setText(imageAlbum.photos.get(4).getCaption() + " Date: " + imageAlbum.photos.get(4).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(4).getRating());

            picLabel1.setIcon(new ImageIcon(newImage));
            picLabel1.setText(imageAlbum.photos.get(0).getCaption() + " Date: " + imageAlbum.photos.get(0).getDateTaken()
                    + " Rating: " + imageAlbum.photos.get(0).getRating());

        } catch (Exception f) {
            f.printStackTrace();
        }
    }

}
