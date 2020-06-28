import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph> {

    /**
     * implements the compare method of the Comparator interface by first comparing the caption (alphabetically) and then by
     * the rating (higher rating first) if necessary
     */
    @Override
    public int compare(Photograph p1, Photograph p2) {
        int compareP1P2 = p1.getCaption().compareTo(p2.getCaption());
        if (compareP1P2 < 0) {
            return -1;
        } else if (compareP1P2 == 0) {
            if (p1.getRating() > p2.getRating()) {
                return -1;
            } else if (p1.getRating() == p2.getRating()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }

    }
}
