import static org.junit.Assert.*;

import java.util.HashSet;

import java.util.ArrayList;

import org.junit.Test;

public class HW4Tests {

    // Optional Tests

    // Tests used to practice JUnit
    @Test
    public void getNameTest1() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        assertEquals("pl1", pl1.getName()); // Testing getName method of PhotoLibrary
    }

    @Test
    public void hasPhotoTest() {
        PhotoLibrary pl2 = new PhotoLibrary("pl2", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        assertFalse(pl2.hasPhoto(photo1)); // Testing the hasPhoto Method from PhotoLibrary when it has the photo
    }

    @Test
    public void getRatingTest() {
        Photograph photo2 = new Photograph("photo", "photo", "2019-02-13", 3);
        assertEquals(3, photo2.getRating()); // Testing the new getRating method from PhotoLibrary
    }

    @Test
    public void getNameTest2() {
        Album album = new Album("album");
        assertEquals("album", album.getName()); // Testing the getName from Album
    }

    // Required Tests
    @Test
    public void getPhotosTest1() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        pl1.addPhoto(photo1);
        ArrayList<Photograph> a = new ArrayList<Photograph>();
        a.add(photo1);
        assertEquals(a, pl1.getPhotos()); // Testing getPhotos when the PhotoLibrary has at least one photo object
    }

    @Test
    public void getPhotosTest2() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        ArrayList<Photograph> a = new ArrayList<Photograph>();
        assertEquals(a, pl1.getPhotos()); // Testing getPhotos when the PhotoLibrary has 0 photo objects
    }

    @Test
    public void getPhotosInMonthTest1() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        Photograph photo2 = new Photograph("photo2", "photo2", "2019-02-14", 3);
        Photograph photo3 = new Photograph("photo2", "photo2", "2019-03-14", 3);
        pl1.addPhoto(photo1);
        pl1.addPhoto(photo2);
        pl1.addPhoto(photo3);
        ArrayList<Photograph> a = new ArrayList<Photograph>();
        a.add(photo1);
        a.add(photo2);
        assertEquals(a, pl1.getPhotosInMonth(2, 2019)); // Testing getPhotosInMonth when two of the three photos are inthe
                                                        // specified month
    }

    @Test
    public void getPhotosInMonthTest2() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        Photograph photo2 = new Photograph("photo2", "photo2", "2019-02-14", 3);
        Photograph photo3 = new Photograph("photo2", "photo2", "2019-03-14", 3);
        pl1.addPhoto(photo1);
        pl1.addPhoto(photo2);
        pl1.addPhoto(photo3);
        ArrayList<Photograph> a = new ArrayList<Photograph>();
        assertEquals(a, pl1.getPhotosInMonth(5, 2019)); // Testing getPhotosInMonth when no photos are in the specified month
    }

    @Test
    public void getPhotosBetweenTest1() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        Photograph photo2 = new Photograph("photo2", "photo2", "2019-02-14", 3);
        Photograph photo3 = new Photograph("photo3", "photo3", "2019-03-14", 3);
        pl1.addPhoto(photo1);
        pl1.addPhoto(photo2);
        pl1.addPhoto(photo3);
        ArrayList<Photograph> a = new ArrayList<Photograph>();
        a.add(photo1);
        a.add(photo2);
        a.add(photo3);
        assertEquals(a, pl1.getPhotosBetween("2017-11-12", "2020-05-05")); // Testing getPhotosBetween when three photos are in
                                                                           // between the limits
    }

    @Test
    public void getPhotosBetweenTest2() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        Photograph photo2 = new Photograph("photo2", "photo2", "2019-02-14", 3);
        Photograph photo3 = new Photograph("photo3", "photo3", "2019-03-14", 3);
        pl1.addPhoto(photo1);
        pl1.addPhoto(photo2);
        pl1.addPhoto(photo3);
        ArrayList<Photograph> a = new ArrayList<Photograph>();
        assertEquals(a, pl1.getPhotosBetween("2015-02-20", "2015-07-20")); // testing getPhotosBetweenTest2 when no photos are
                                                                           // between the two limits
    }

    @Test
    public void erasePhotoTest1() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        pl1.addPhoto(photo1);
        assertEquals(true, pl1.removePhoto(photo1)); // testing erase photo when there is one photo to be erased successfully
    }

    @Test
    public void erasePhotoTest2() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        Photograph photo2 = new Photograph("photo2", "photo2", "2019-02-14", 2);
        pl1.addPhoto(photo1);
        assertEquals(false, pl1.removePhoto(photo2)); // testing erase photo when the photo is not in the collection to be erased
    }

    @Test
    public void similarityTest1() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        pl1.addPhoto(photo1);

        PhotoLibrary pl2 = new PhotoLibrary("pl2", 1);
        pl2.addPhoto(photo1);

        assertEquals(1.0, PhotoLibrary.similarity(pl1, pl2), 0.1); // Test for similarity when at least one photo is in common
    }

    @Test
    public void similarityTest2() {
        PhotoLibrary pl1 = new PhotoLibrary("pl1", 1);
        Photograph photo1 = new Photograph("photo", "photo", "2019-02-13", 3);
        pl1.addPhoto(photo1);

        PhotoLibrary pl2 = new PhotoLibrary("pl2", 1);
        Photograph photo2 = new Photograph("photo2", "photo2", "2019-02-14", 2);
        pl1.addPhoto(photo1);
        pl2.addPhoto(photo2);

        assertEquals(0.0, PhotoLibrary.similarity(pl1, pl2), 0.1);  // Test for zero similarity between the two Photolibraries

    }

    @Test(timeout = 100)
    public void testPLConstructor() {
        PhotoLibrary b = new PhotoLibrary("MyLibrary", 14);
        assertEquals("PhotoLibrary's constructor failed to initialize name (getter did not return expected value)", "MyLibrary",
                b.getName());
        assertEquals("PhotoLibrary's constructor failed to initialize id (getter did not return expected value)", 14, b.getId());
        assertEquals("PhotoLibrary's constructor failed to initialize photos (getter did not return expected value)",
                new ArrayList<Photograph>(), b.getPhotos());
        assertEquals("PhotoLibrary's constructor failed to initialize albums (getter did not return expected value)",
                new HashSet<Album>(), b.getAlbums());
    }

    @Test(timeout = 100)
    public void testSetName() {
        PhotoLibrary b = new PhotoLibrary("Arphaxad", 14);
        b.setName("Peleg");
        assertEquals("PhotoLibrary.setName() failed (getter did not return expected value)", "Peleg", b.getName());
    }

    @Test(timeout = 100)
    public void testEraseEmpty() {
        PhotoLibrary b = new PhotoLibrary("Peleg", 17);
        assertFalse("PhotoLibrary.erasePhoto() failed (edge case)", b.removePhoto(new Photograph("Caption", "Filename")));
    }

    @Test(timeout = 100)
    public void testEraseThere() {
        PhotoLibrary b = new PhotoLibrary("Peleg", 17);
        b.getPhotos().add(new Photograph("C1", "F1"));
        b.getPhotos().add(new Photograph("C2", "F2"));
        b.getPhotos().add(new Photograph("C3", "F3"));
        assertTrue("PhotoLibrary createAlbum did not create the specified album.", b.createAlbum("Vacation"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album",
                b.addPhotoToAlbum(new Photograph("C2", "F2"), "Vacation"));
        assertTrue("PhotoLibrary createAlbum did not create the specified album.", b.createAlbum("Vacation2"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album",
                b.addPhotoToAlbum(new Photograph("C1", "F1"), "Vacation2"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album",
                b.addPhotoToAlbum(new Photograph("C2", "F2"), "Vacation2"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album",
                b.addPhotoToAlbum(new Photograph("C3", "F3"), "Vacation2"));

        assertTrue(b.removePhoto(new Photograph("C2", "F2")));
        assertFalse("PhotoLibrary.erasePhoto() failed (something wasn't removed) (also check getPhotos())",
                b.getPhotos().contains(new Photograph("C2", "F2")));
        Album a = null;
        for (Album c : b.getAlbums()) {
            if (c.getName().equals("Vacation"))
                a = c;
        }
        assertEquals("PhotoLibrary.erasePhoto() failed (photo wasn't removed from the albums) (also check getAlbums())",
                new ArrayList<Photograph>(), a.getPhotos());
        a = null;
        for (Album c : b.getAlbums()) {
            if (c.getName().equals("Vacation2"))
                a = c;
        }
        assertFalse("PhotoLibrary.erasePhoto() failed (something wasn't removed from an album) (also check getPhotos())",
                a.getPhotos().contains(new Photograph("C2", "F2")));
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much from albums) (also check getPhotos())",
                a.getPhotos().contains(new Photograph("C1", "F1")));
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much from albums) (also check getPhotos())",
                a.getPhotos().contains(new Photograph("C3", "F3")));
        assertEquals("PhotoLibrary erasePhoto removed too much from albums", 2, a.getPhotos().size());
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much) (also check getPhotos())",
                b.getPhotos().contains(new Photograph("C1", "F1")));
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much) (also check getPhotos())",
                b.getPhotos().contains(new Photograph("C3", "F3")));
        assertEquals("PhotoLibrary erasePhoto removed too much", 2, b.getPhotos().size());
    }

    @Test(timeout = 100)
    public void testGetPhotosYear() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("mypic45.jpg", "Grand Canyon", "2014-01-11", 2);
        Photograph c = new Photograph("water.jpg", "Rafting", "2016-05-11", 5);
        Photograph d = new Photograph("water2.jpg", "Rafting 2", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosInYear(year) did not return photos in year", 2, a.getPhotosInYear(2016).size());
        assertTrue("PhotoLibrary getPhotosInYear(year) did not return correct photos in year",
                a.getPhotosInYear(2016).contains(d));
        assertTrue("PhotoLibrary getPhotosInYear(year) did not return correct photos in year",
                a.getPhotosInYear(2016).contains(c));
    }

    @Test(timeout = 100)
    public void testGetPhotosYear2() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("mypic45.jpg", "Grand Canyon", "2014-01-11", 2);
        Photograph c = new Photograph("water.jpg", "Rafting", "2016-05-11", 5);
        Photograph d = new Photograph("water2.jpg", "Rafting 2", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosInYear() returned photos year is negative", null, a.getPhotosInYear(-2229));
    }

    @Test(timeout = 100)
    public void testGetPhotosBetween() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("mypic45.jpg", "Grand Canyon", "2016-09-11", 2);
        Photograph c = new Photograph("water.jpg", "Rafting", "2016-05-11", 5);
        Photograph d = new Photograph("water2.jpg", "Rafting 2", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosBetween() did not return photos in Between", 2,
                a.getPhotosBetween("2016-05-12", "2017-01-01").size());
        assertTrue("PhotoLibrary getPhotosBetween() did not return correct photos between good dates",
                a.getPhotosBetween("2016-05-12", "2017-01-01").contains(d));
        assertTrue("PhotoLibrary getPhotosBetween() did not return correct photos between good dates",
                a.getPhotosBetween("2016-05-12", "2017-01-01").contains(b));
        assertFalse("PhotoLibrary getPhotosBetween() did not return correct photos between good dates",
                a.getPhotosBetween("2016-05-12", "2017-01-01").contains(c));
    }

    @Test(timeout = 100)
    public void testGetPhotosBetween2() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("mypic45.jpg", "Grand Canyon", "2014-01-11", 2);
        Photograph c = new Photograph("water.jpg", "Rafting", "2016-05-11", 5);
        Photograph d = new Photograph("water2.jpg", "Rafting 2", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null,
                a.getPhotosBetween("2016", "2017"));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null,
                a.getPhotosBetween("2016-15-11", "2017-01-02"));
        ;
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null,
                a.getPhotosBetween("2016-10-11", "2017-04-34"));
    }

    @Test(timeout = 100)
    public void testGetPhotosBetween3() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("mypic45.jpg", "Grand Canyon", "2014-01-11", 2);
        Photograph c = new Photograph("water.jpg", "Rafting", "2016-05-11", 3);
        Photograph d = new Photograph("water2.jpg", "Rafting 2", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when it shouldn't have", 0,
                a.getPhotosBetween("2019-01-01", "2019-02-11").size());
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when it shouldn't have", 0,
                a.getPhotosBetween("2016-05-12", "2016-09-29").size());
    }

    @Test(timeout = 100)
    public void testRemoveAlbumNotThere() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);

        assertTrue("PhotoLibrary createAlbum() did not create an album", a.createAlbum("My Album"));
        assertTrue("PhotoLibrary createAlbum() did not create an album", a.createAlbum("Vacation Photos"));
        assertTrue("PhotoLibrary createAlbum() did not create an album", a.createAlbum("Vacation Photos 2"));

        assertFalse("PhotoLibrary removeAlbum() allowed removal of an album that was not there",
                a.removeAlbum("Vacation Photos 3"));
        boolean contains = false;
        Album c = new Album("Vacation Photos 3");
        for (Album b : a.getAlbums()) {
            if (c.equals(b))
                contains = true;
        }
        assertFalse("PhotoLibrary removeAlbum() accidentally added an album instead of removing", contains);
    }

    @Test(timeout = 100)
    public void testAddPhotoToAlbumEdge() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph p = new Photograph("Caption", "My Filename");
        a.addPhoto(p);

        assertFalse("PhotoLibrary addPhotoToAlbum() allowed adding photo to non-existant album",
                a.addPhotoToAlbum(p, "My Album"));
    }

    @Test(timeout = 100)
    public void testEqualsType() throws Exception {
        try {
            PhotoLibrary.class.getDeclaredMethod("equals", Object.class);
        } catch (NoSuchMethodException e) {
            fail("PhotoLibrary equals method not specified correctly.");
        } catch (Exception e) {
            throw e;
        }
    }

    @Test(timeout = 100)
    public void testEqualsSame() {
        PhotoLibrary b = new PhotoLibrary("Le Petit Prince", 42);
        assertTrue("PhotoLibrary.equals() failed: Symmetric", b.equals(b));
    }

    @Test(timeout = 100)
    public void testEqualsSimilar() {
        PhotoLibrary b = new PhotoLibrary("Le Petit Prince", 42);
        PhotoLibrary c = new PhotoLibrary("Le Petit Prince", 42);
        assertTrue("PhotoLibrary.equals() failed: Same id and name", b.equals(c));
    }

    @Test(timeout = 100)
    public void testEqualsDifferentTypes() throws Exception {
        try {
            PhotoLibrary b = new PhotoLibrary("Le Petit Prince", 42);
            assertFalse("PhotoLibrary.equals() failed: Compare PhotoLibrary to Integer", b.equals(42));
        } catch (ClassCastException e) {
            fail("PhotoLibrary.equals() failed: Casting a non-PhotoLibrary to a PhotoLibrary");
        } catch (Exception e) {
            throw e;
        }
    }

    // Tests for Homework 4
    @Test
    public void testRemovePhoto1() {
        PhotoLibrary pc = new PhotoLibrary("pc", 1);
        Photograph a = new Photograph("Rafting.jpg", "Rafting", "2014-01-11", 1);
        Photograph b = new Photograph("b.jpg", "b", "2016-05-11", 5);
        pc.addPhoto(a);
        assertEquals(false, pc.removePhoto(b));
    }

    @Test
    public void testRemovePhoto2() {
        PhotoLibrary pc = new PhotoLibrary("pc", 1);
        Photograph a = new Photograph("Rafting.jpg", "Rafting", "2014-01-11", 1);
        pc.addPhoto(a);
        assertEquals(true, pc.removePhoto(a));
    }

    @Test
    public void TestCompareTo1() {
        Photograph a = new Photograph("Rafting.jpg", "Rafting", "2014-01-11", 1);
        Photograph b = new Photograph("Rafting.jpg", "Rafting", "2016-05-11", 5);
        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void TestCompareTo2() {
        Photograph c = new Photograph("Rafting.jpg", "Rafting", "2017-01-11", 1);
        Photograph d = new Photograph("Rafting.jpg", "Rafting", "2016-05-11", 5);
        assertEquals(1, c.compareTo(d));
    }

    @Test
    public void testCompareByCaption1() {
        CompareByCaption cbc = new CompareByCaption();
        Photograph a = new Photograph("mypic45.jpg", "Grand Canyon", "2014-01-11", 2);
        Photograph b = new Photograph("water.jpg", "Rafting", "2016-05-11", 5);
        assertEquals(-1, cbc.compare(a, b));
    }

    @Test
    public void testCompareByCaption2() {
        CompareByCaption cbc = new CompareByCaption();
        Photograph c = new Photograph("Rafting.jpg", "Rafting", "2014-01-11", 1);
        Photograph d = new Photograph("Rafting.jpg", "Rafting", "2016-05-11", 5);
        assertEquals(1, cbc.compare(c, d));
    }

    @Test
    public void testCompareByRating1() {
        CompareByRating cbr = new CompareByRating();
        Photograph a = new Photograph("Rafting.jpg", "Rafting", "2014-01-11", 1);
        Photograph b = new Photograph("Rafting.jpg", "Rafting", "2016-05-11", 5);
        assertEquals(1, cbr.compare(a, b));
    }

    @Test
    public void testCompareByRating2() {
        CompareByRating cbr = new CompareByRating();
        Photograph c = new Photograph("Rafting.jpg", "aaaaa", "2014-01-11", 3);
        Photograph d = new Photograph("Rafting.jpg", "bbbbb", "2016-05-11", 3);
        assertEquals(-1, cbr.compare(c, d));
    }

}
