import java.util.ArrayList;

public abstract class PhotographContainer {

    /**
     * Private instance variables for the name and photos of the PhotographContainer object
     */
    protected String name;
    protected ArrayList<Photograph> photos;

    /**
     * Constructor used to create an PhotographContainer object
     * 
     * @param name: gives the variable name the name specified in the parameter
     */
    public PhotographContainer(String name) {
        this.name = name;
        photos = new ArrayList<Photograph>();
    }

    /**
     * Returns the name of the Album object
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the Album object
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list photos stores in the Album object
     * 
     * @return
     */
    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    /**
     * Returns an ArrayList with photos that have greater than or equal to the specified rating
     * 
     * @param rating: rating of the photo on scale 0-5 as an int
     * @return ArrayList<Photograph>: ArrayList containing photos with rating greater than or equal to the rating specified
     */
    public ArrayList<Photograph> getPhotos(int rating) {
        ArrayList<Photograph> withRatingHigher = new ArrayList<Photograph>();
        for (Photograph photo : this.photos) {
            if (photo.getRating() >= rating) {
                withRatingHigher.add(photo);
            }
        }
        return withRatingHigher;
    }

    /**
     * Returns ArrayList with Photos according to the specified year
     * 
     * @param year: int specifying the year the photo was taken
     * @return ArrayList<Photograph: Arraylist with Photos according to the specified year
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> withYear = new ArrayList<Photograph>();
        String yearAsString = Integer.toString(year);
        if (year < 0) {
            return null;
        }
        for (Photograph photo : this.photos) {
            String yearDateTaken = photo.getDateTaken().substring(0, 4);
            if (yearDateTaken.equals(yearAsString)) {
                withYear.add(photo);
            }
        }
        return withYear;
    }

    /**
     * Returns an ArrayList with photos according to the specified year and month
     * 
     * @param month: int specifying the month (two digits)
     * @param year: int specifying the year (four digits)
     * @return ArrayList<Photograph>: ArrayList with Photos according to the specifed year and month
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> withMonth = new ArrayList<Photograph>();
        if (year < 0 || month < 0 || month > 12) {
            return null;
        }
        for (Photograph photo : this.photos) {
            String stringYearDateTaken = photo.getDateTaken().substring(0, 4);
            String stringMonthDateTaken = photo.getDateTaken().substring(5, 7);
            int intYearDateTaken = Integer.parseInt(stringYearDateTaken);
            int intMonthDateTaken = Integer.parseInt(stringMonthDateTaken);
            if ((year == intYearDateTaken) && (month == intMonthDateTaken)) {
                withMonth.add(photo);
            }
        }
        return withMonth;
    }

    /**
     * Returns an ArrayList of photos from the photos feed that were taken between beginDate and endDate (not including
     * endDate)
     * 
     * @param beginDate: String specifying left bound for the date
     * @param endDate: String specifying the right bound for the date (exclusive)
     * @return ArrayList<Photograph>: ArrayList containing the photos taken between the specified date limits
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        ArrayList<Photograph> photosBetween = new ArrayList<Photograph>();
        if ((beginDate.length() != 10) || endDate.length() != 10) { // First check on the correct format of the date
            return null;
        }
        if ((!beginDate.substring(4, 5).equals("-")) || (!endDate.substring(4, 5).equals("-"))) { // remove hyphens to make
                                                                                                  // indexing the String easier
            return null;
        }
        if ((!beginDate.substring(7, 8).equals("-")) || (!endDate.substring(7, 8).equals("-"))) {
            return null;
        }
        String[] parts = beginDate.split("-");
        int year1 = Integer.valueOf(parts[0]); // accessing different parts of the String abd casting to an Int to get year month
                                               // and day
        int month1 = Integer.valueOf(parts[1]);
        int day1 = Integer.valueOf(parts[2]);

        if (year1 < 0 || month1 < 0 || month1 > 12 || day1 < 0 || day1 > 31) { // Check to make sure dates are valid
            return null;
        }

        String[] parts2 = endDate.split("-");
        int year2 = Integer.valueOf(parts2[0]); // repeating similar steps as above
        int month2 = Integer.valueOf(parts2[1]);
        int day2 = Integer.valueOf(parts2[2]);

        if (year2 < 0 || month2 < 0 || month2 > 12 || day2 < 0 || day2 > 31) {
            return null;
        }

        int date1 = Integer.valueOf(beginDate.replace("-", ""));
        int date2 = Integer.valueOf(endDate.replace("-", ""));

        for (int i = 0; i < getPhotos().size(); i++) {
            int date = Integer.valueOf(getPhotos().get(i).getDateTaken().replace("-", ""));
            if ((date >= date1) && (date <= date2)) {
                photosBetween.add(getPhotos().get(i));
            }
        }
        return photosBetween;

    }

    /**
     * Adds a photo to the collection of photos within the PhotographContainer
     * 
     * @param p: the Photograph object to be added
     * @return boolean: true if the PhotographContainer did not already contain the Photograph and the Photograph was
     *         succesfully added false if the PhotographContainer already had the photo or if the parameter p is null
     */
    public boolean addPhoto(Photograph p) {
        if (!this.photos.contains(p) && (p != null)) {
            this.photos.add(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return true if the current object has p in its list of photos. Otherwise return false.
     * 
     * @param p: a Photograph object passed in
     * @return boolean: true if the Album has the Photograph p and false if the Album does not have the Photograph p
     */
    public boolean hasPhoto(Photograph p) {
        if (this.photos.contains(p)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Remove Photograph p from the PhotographContainer, if it exists in the list of photos.
     * 
     * @param p: a Photograph object
     * @return true if the PhotographContainer has the Photograph and it is then removed, false if it does not have the
     *         Photograph object
     */
    public boolean removePhoto(Photograph p) {
        for (Photograph photo : this.photos) {
            if (p.equals(photo)) {
                this.photos.remove(photo);
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if the current object has p in its list of photos. Otherwise return false.
     * 
     * @param p: a Photograph object passed in
     * @return boolean: true if the PhotographContainer has the Photograph p and false if it does not have the Photograph p
     */
    public int numPhotographs() {
        return photos.size();
    }

    /**
     * Overrides the default equals method so that two PhotographContainer objects can be compared by name
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false; // returns false is object has the value null
        }
        if (!(o instanceof PhotographContainer)) {
            return false; // returns false if the object is not of the type PhotographContainer
        }
        PhotographContainer otherAlbum = (PhotographContainer) o; // cast the object to an PhotographContainer object that is
                                                                  // being compared to the
        // Album object
        if (this.getName() == otherAlbum.getName()) {
            return true; // returns true if the name variables of both objects are equal
        } else {
            return false; // returns false if other has wrong caption or field
        }
    }

    /**
     * Overrides the default toString() method displaying the name of the Album followed by the photos it contains
     * 
     * @return String: name on first line followed by list of photos
     */
    public String toString() {
        return this.name + "\n" + this.photos;
    }

    /**
     * Overrides the default hashCode() method
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
