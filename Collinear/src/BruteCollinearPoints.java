import java.util.LinkedList;

public class BruteCollinearPoints {

    private static final int MIN_POINTS_IN_SEGMENT = 4;

    /**
     * Collinear lineSegments
     */
    LinkedList<LineSegment> lineSegments;

    /**
     * Finds all line lineSegments containing 4 points
     *
     * @param points on the plain
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Invalid null points are provided");
        }

        lineSegments = new LinkedList<>();

        int collinearCount;
        Point minCollinearPoint;
        Point maxCollinearPoint;

        for (int currPointIx = 0; currPointIx < points.length; currPointIx++) {
            if (points[currPointIx] == null) {
                throw new IllegalArgumentException("Illegal null point provided in " + currPointIx + " place");
            }

            Point curPoint = points[currPointIx];

            minCollinearPoint = curPoint;
            maxCollinearPoint = curPoint;

            for (int slopeToPointIx = 0; slopeToPointIx < points.length; slopeToPointIx++) {
                if (points[slopeToPointIx] == null) {
                    throw new IllegalArgumentException("Illegal null point provided in " + slopeToPointIx + " place");
                }

                double slope = curPoint.slopeTo(points[slopeToPointIx]);
                if (slope == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException("Illegal repeated point " + curPoint.toString() + " detected");
                }

                collinearCount = 1;
                if (minCollinearPoint.compareTo(points[slopeToPointIx]) > 0) {
                    minCollinearPoint = points[slopeToPointIx];
                }

                if (maxCollinearPoint.compareTo(points[slopeToPointIx]) < 0) {
                    maxCollinearPoint = points[slopeToPointIx];
                }

                for (int nextPointIx = 0; nextPointIx < points.length; nextPointIx++) {
                    if (points[nextPointIx] == null) {
                        throw new IllegalArgumentException("Illegal null point provided in " + nextPointIx + " place");
                    }

                    double nextSlope = curPoint.slopeTo(points[nextPointIx]);

                    if (nextSlope == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException("Illegal repeated point " + curPoint.toString() + " detected");
                    }

                    if (nextSlope == slope) {
                        collinearCount++;

                        if (minCollinearPoint.compareTo(points[nextPointIx]) > 0) {
                            minCollinearPoint = points[nextPointIx];
                        }

                        if (maxCollinearPoint.compareTo(points[nextPointIx]) < 0) {
                            maxCollinearPoint = points[nextPointIx];
                        }
                    }
                }

                if (collinearCount >= MIN_POINTS_IN_SEGMENT) {
                    lineSegments.add(new LineSegment(minCollinearPoint, maxCollinearPoint));
                }
            }
        }
    }

    /**
     * the number of line lineSegments
     *
     * @return the number of collinear lineSegments
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }

    /**
     * The line lineSegments
     *
     * @return collinear lineSegments
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}
