import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {

    @Override
    public int compare(Photograph p1, Photograph p2) {

        if (p1.getRating() > p2.getRating()) {
            return -1;
        } else if (p1.getRating() == p2.getRating()) {
            int compareP1P2 = p1.compareTo(p2);
            if (compareP1P2 < 0) {
                return -1;
            } else if (compareP1P2 == 0) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

}
