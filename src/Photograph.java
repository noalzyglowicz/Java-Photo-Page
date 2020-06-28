import java.io.File;

/**
 * Defines a class object Photograph that behaves as online photograph that holds both a caption and a filename
 */
public class Photograph implements Comparable<Photograph> {

    /**
     * Private instance variable that holds the caption information in a String
     */
    private String caption;

    /**
     * private instance variable that holds the filename of the Photograph object in a String
     */
    private String filename;

    /**
     * private instance variable that holds the date the Photograph was taken as a String in the form "YYYY-MM-DD"
     */
    private String dateTaken;

    /**
     * private instance variable denoting the rating of the Photograph on a 0-5 scale
     */
    private int rating;
    
    private File imageFile;

    /**
     * Constructor used to declare a Photograph object
     * 
     * @param caption: creates the caption to be stored in the caption variable
     * @param filename: creates the filename to be stored in the filename variable
     */
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        imageFile = new File(filename);
    }

    /**
     * Additional Constructor used to declare a Photograph object
     */
    public Photograph(String filename, String caption, String dateTaken, int rating) {
        this.filename = filename;
        this.caption = caption;
        this.dateTaken = dateTaken;
        this.rating = rating;
        imageFile = new File(filename);
    }

    /**
     * Getter used to get the caption variable from a photograph object
     * 
     * @return String: the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Getter used to get the filename variable from a photograph object
     * 
     * @return String: the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Getter used to get the dateTaken variable from a Photograph object
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     * Getter used to get the rating variable from a Photograph object
     */
    public int getRating() {
        return rating;
    }

    /**
     * Getter used to get the imageFile
     */
    public File getImageFile() {
        return imageFile;
    }
    
    /**
     * Setter used to change the value of the dateTaken variable
     */
    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    /**
     * Setter used to change the value of the rating variable
     */
    public void setRating(int rating) {
        if ((rating <= 5) && (rating >= 0)) { // new feature limiting the values of which the rating can be
            this.rating = rating;
        }
    }
    
    /**
     * Sets the imageFile to the specified imageFile
     */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public int hashCode() {
        return (this.caption + "---" + this.filename).hashCode();
    }

    /**
     * Overides the equals function for the Photograph class
     * 
     * @param o: object passed into equals method
     * @return boolean: false if object is of an uncomparable type or contains different values in its variables true if
     *         both objects contain the same values in their variables
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false; // returns false is object has the value null
        }
        if (!(o instanceof Photograph)) {
            return false; // returns false if the object is not of the type photograph
        }
        Photograph otherPhotograph = (Photograph) o; // cast the object to a photograph object that is being compared to the
                                                     // photograph object
        if (this.getCaption() == otherPhotograph.getCaption() && this.getFilename() == otherPhotograph.getFilename()) {
            return true; // returns true if captions and filename variables of each photograph contain the same values
        } else {
            return false; // returns false if other has wrong caption or field
        }

    }

    /**
     * Overrides the default toString method to display the caption and filename of the photograph object
     * 
     * @return String: String displaying caption and filename of the photograph
     */
    public String toString() {
        return "Caption: " + caption + " Filename: " + filename;
    }

    /**
     * Overrides the default compareTo method and compare the photograph objects first by their date taken (earlier pictures
     * first) and then by their caption (alphabetically) if necessary
     */
    @Override
    public int compareTo(Photograph p) {
        int thisYear = Integer.parseInt(this.getDateTaken().substring(0, 4)); //make integer variables for each part of the date so that thery are more easily comparable
        int thisMonth = Integer.parseInt(this.getDateTaken().substring(5, 7));
        int thisDay = Integer.parseInt(this.getDateTaken().substring(8, 10));
        int pYear = Integer.parseInt(p.getDateTaken().substring(0, 4));
        int pMonth = Integer.parseInt(p.getDateTaken().substring(5, 7));
        int pDay = Integer.parseInt(p.getDateTaken().substring(8, 10));
        if (thisYear < pYear) {
            return -1;
        } else if (thisYear == pYear) {
            if (thisMonth < pMonth) {
                return -1;
            } else if (thisMonth == pMonth) {
                if (thisDay < pDay) {
                    return -1;
                } else if (thisDay == pDay) {
                    int compareP1P2 = this.getCaption().compareTo(p.getCaption());
                    if (compareP1P2 < 0) {
                        return -1;
                    } else if (compareP1P2 == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        } else {
            return 1;
        }
        return 1;
    }

}
