
/*
 * Homework 2 - Even Instagram started somewhere Noal Zyglowicz , ntz3sw Sources : None
 */

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Defines a class object PhotoLibrary that behaves as a user that can store and compare photograph objects
 */
public class PhotoLibrary extends PhotographContainer {

    /**
     * Private instance variable that holds the ID of the PhotoLibrary object as an integer
     */
    private int id;

    /**
     * Private instance variable which is a HashSet of albums that store photos
     */
    private HashSet<Album> albums;

    /**
     * The single constructor that declares a PhotoLibrary object with an empty photos arrayList
     * 
     * @param name: creates and stores the name of the PhotoLibrary as a Sting
     * @param id: creates and stores the id of the PhotoLibrary as an int
     */
    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
        albums = new HashSet<Album>();
    }

    /**
     * Getter method used to get the ID from a PhotoLibrary object
     * 
     * @return int: the ID number of the PhotoLibrary
     */
    public int getId() {
        return this.id;
    }

    public HashSet<Album> getAlbums() {
        return this.albums;
    }

    /**
     * Creates a new Album with name albumName and adds it to the list of albums, only if an Album with that name does not
     * already exist. Returns true if the add was successful, false otherwise.
     * 
     * @param albumName
     * @return boolean: true if the Album with the name does not already exist and is then created false otherwise
     */
    public boolean createAlbum(String albumName) {
        for (Album a : getAlbums()) {
            if (a.getName().equals(albumName)) {
                return false;
            }
        }
        Album b = new Album(albumName);
        albums.add(b);
        return true;
    }

    /**
     * Removes the Album with name albumName if an Album with that name exists in the set of albums. Returns true if the
     * remove was successful, false otherwise.
     * 
     * @param albumName
     * @return boolean: true if the Hashset of Albums contains the Album and then removes the Album false otherwise
     */
    public boolean removeAlbum(String albumName) {
        for (Album a : albums) {
            if (a.getName().equals(albumName)) {
                getAlbums().remove(a);
                return true;
            }
        }
        return false;
    }

    /**
     * Add the Photograph p to the Album in the set of albums that has name albumName if and only if it is in the
     * PhotoLibrary’s list of photos and it was not already in that album. Return true if the Photograph was added; return
     * false if it was not added.
     * 
     * @param p
     * @param albumName
     * @return boolean: true if the Photograph is added and false if it is not added
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        if (!getPhotos().contains(p)) {
            return false;
        }
        for (Album b : getAlbums()) {
            if (!b.hasPhoto(p) && b.getName().equals(albumName)) {
                b.addPhoto(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the Photograph p from the Album in the set of albums that has name albumName. Return true if the photo was
     * successfully removed. Otherwise return false
     * 
     * @param p
     * @param albumName
     * @return boolean: true if the photo is successfully removed and false otherwise
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        for (Album b : getAlbums()) {
            if (b.hasPhoto(p) && b.getName().equals(albumName)) {
                b.removePhoto(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns album by the name variable specified in the parameter
     * 
     * @param albumName
     * @return album
     */
    private Album getAlbumByName(String albumName) {
        for (Album album : this.albums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }
        return null;
    }

    /**
     * Setter method used to set the name of a PhotoLibrary object
     * 
     * @param name: New String to be set as the PhotoLibrary object's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Removes a photo from a PhotoLibrary object
     * 
     * @param p: A Photograph object
     * @return boolean: true if the PhotoLibrary contained the photo and was then removed, false if the PhotoLibrary did not
     *         have the photo to begin with
     */
    public boolean removePhoto(Photograph p) {
        boolean photoRemoved = false; // State of which the photo needs to be removed or not
        for (Album album : this.albums) {
            if (album.hasPhoto(p)) {
                album.removePhoto(p);
                photoRemoved = true;
            }
        }
        if (getPhotos().contains(p)) {
            photos.remove(p);
            photoRemoved = true;
        }
        return photoRemoved;
    }

    /**
     * Overrides the equals method for the PhotoLibrary class
     * 
     * @return boolean: true if the id values are the same for both objects, false otherwise
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false; // o is null
        }
        if (!(o instanceof PhotoLibrary)) {
            return false;
        }
        PhotoLibrary otherPhotoLibrary = (PhotoLibrary) o;
        if (this.getId() == otherPhotoLibrary.getId()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Overridden toString method for the PhotoLibrary object
     * 
     * @return String displaying the name ID and photos the PhotoLibrary object contains
     */
    public String toString() {
        return albums.toString() + getName() + getId() + getPhotos();
    }

    /**
     * Compares the Photographs of two different PhotoLibrary objects and returns an ArrayList of the common Photographs
     * 
     * @param a: A PhotoLibrary object
     * @param b: Another Photolibrary object to be compared to
     * @return ArrayList<Photograph>: ArrayList of the Photograph objects the two PhotoLibrary objects share
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> commonPhotos = new ArrayList<Photograph>(); // empty arrayList created for return statement
        for (Photograph photo : a.getPhotos()) {
            for (Photograph p : b.getPhotos()) {
                if (p.equals(photo)) {
                    commonPhotos.add(photo);
                }
            }

        }
        return commonPhotos;
    }

    /**
     * Compares the similarity of two PhotoLibrary object's feeds
     * 
     * @param a: A PhotoLibrary object
     * @param b: Another PhotoLibrary object to be compared to
     * @return double: ratio of the similarity between Photograph feeds determined by the number of common Photographs
     *         divided by the shorter of the two feeds
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        int numberOfCommonPhotos = commonPhotos(a, b).size(); // variable created to more easily understand mathematics expression
                                                              // below
        if (a.numPhotographs() == 0 || b.numPhotographs() == 0) {
            return 0.0;
        }
        if (a.numPhotographs() > b.numPhotographs()) {
            return (float) numberOfCommonPhotos / (float) b.numPhotographs();
        } else {
            return (float) numberOfCommonPhotos / (float) a.numPhotographs();
        }
    }

}
