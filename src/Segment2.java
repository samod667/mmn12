/**
 * Segment2 represents a line (parallel to the x-axis) using a center point and length.
 * @author Dor Samoha
 * @version Oct 29th 2020
 */

public class Segment2 {

    ///DECLARING VARIABLES///
    private Point _poCenter;
    private double _length;

    ///DECLARING FINALS
    final int ZERO = 0;
    final int POWER_OF_TWO = 2;

    ///CONSTRUCTORS///////////////////////////

    /**
     * Constructs a new segment using two Points. If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     *
     * @param left  the left point on the segment
     * @param right the right point on the segment
     */
    public Segment2(Point left, Point right) {
        this._length = getSegmentLength(left.getX(), right.getX());
        this._poCenter = new Point(right.getX() - (this._length / POWER_OF_TWO), left.getY());
    }

    /**
     * Constructs a new segment using 4 specified x y coordinates: two coordinates for the left point and two coordinates for the right point. If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     *
     * @param leftX  X value on the left point
     * @param leftY  Y value on the left pont
     * @param rightX X value on the right point
     * @param rightY Y value on the right point
     */
    public Segment2(double leftX, double leftY, double rightX, double rightY) {
        if (rightY != leftY) {
            rightY = leftY;
        }
        this._length = getSegmentLength(leftX, rightX);

        this._poCenter = new Point(rightX - (this._length / POWER_OF_TWO), rightY);
    }

    /**
     * Copy Constructor. Construct a segment using a reference segment.
     *
     * @param other the other segment
     */
    public Segment2(Segment2 other) {
        this._length = other._length;
        this._poCenter = new Point(other._poCenter);
    }

    /**
     * Constructs a new segment using a center point and the segment length.
     *
     * @param point  the center point
     * @param length the length of segment
     */
    public Segment2(Point point, double length) {
        this._poCenter = new Point(point);

        if (length > ZERO) {
            this._length = length;
        } else {
            this._length = ZERO;
        }
    }
    ////////////////////////////////////////////////////////////

    ///PRIVATE HELPER GETTING SEGMENT LENGTH METHOD///
    private double getSegmentLength(double leftX, double rightX) {
        return Math.abs(rightX - leftX);
    }
    //////////////////////////////////////////////////

    ///OTHER METHODS//////////////////////////////////

    /**
     * Returns the left point of the segment.
     *
     * @return The left point
     */
    Point getPoLeft() {
        return new Point(this._poCenter.getX() - (this._length / POWER_OF_TWO), this._poCenter.getY());
    }

    /**
     * Returns the right point of the segment.
     *
     * @return the right point
     */
    Point getPoRight() {
        return new Point(this._poCenter.getX() + (this._length / POWER_OF_TWO), this._poCenter.getY());
    }

    /**
     * Returns the segment length.
     *
     * @return the segment's length
     */
    public double getLength() {
        return getSegmentLength(getPoLeft().getX(), getPoRight().getX());
    }

    /**
     * Check if the reference segment is equal to this segment.
     *
     * @param other the other segment
     * @return True if the reference segment is equal to this segment
     */
    public boolean equals(Segment2 other) {
        return this._poCenter.equals(other._poCenter) && this._length == other._length;
    }

    /**
     * Check if this segment is above a reference segment.
     *
     * @param other the other segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove(Segment2 other) {
        return this._poCenter.getY() > other._poCenter.getY();
    }

    /**
     * Check if this segment is under a reference segment.
     *
     * @param other the other segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder(Segment2 other) {
        return other.isAbove(this);
    }

    /**
     * Check if this segment is left of a received segment.
     *
     * @param other the other segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment2 other) {
        return this.getPoRight().getX() < other.getPoLeft().getX();
    }

    /**
     * heck if this segment is right of a received segment.
     *
     * @param other the other segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight(Segment2 other) {
        return other.isLeft(this);
    }

    /**
     * Move the segment horizontally by delta. Will be implemented only for a valid delta
     *
     * @param delta the displacement size
     */
    public void moveHorizontal(double delta) {
        double leftX = this.getPoLeft().getX();
        double rightX = this.getPoRight().getX();

        if (leftX + delta >= ZERO) {
            leftX += delta;
            rightX += delta;

            this._length = getSegmentLength(leftX, rightX);
            this._poCenter.setX(rightX - (_length / POWER_OF_TWO));
        }
    }

    /**
     * Move the segment vertically by delta. Will be implemented only for a valid delta
     *
     * @param delta the displacement size
     */
    public void moveVertical(double delta) {
        if (this._poCenter.getY() + delta >= ZERO) {
            double yValue = this._poCenter.getY();
            this._poCenter.setY(yValue + delta);
        }
    }

    /**
     * Change the segment size by moving the right point by delta. Will be implemented only for a valid delta: only if the new right point remains the right point.
     *
     * @param delta the length change
     */
    public void changeSize(double delta) {
        double leftX = this.getPoLeft().getX();
        double rightX = this.getPoRight().getX();

        if (rightX + delta > leftX) {
            rightX += delta;

            this._length = getSegmentLength(leftX, rightX);
            this._poCenter.setX(rightX - (_length / POWER_OF_TWO));
        }
    }

    /**
     * Check if a point is located on the segment.
     *
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment(Point p) {
        if (p.getY() == this._poCenter.getY()) {
            double rightX = this.getPoRight().getX();
            double leftX = this.getPoLeft().getX();

            return p.getX() >= leftX && p.getX() <= rightX;
        }
        return false;
    }

    /**
     * Check if this segment is bigger than a reference segment.
     *
     * @param other the other segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment2 other) {
        return this._length > other._length;
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     *
     * @param other the other segment
     * @return The overlap size
     */
    public double overlap(Segment2 other) {
        if(this.getPoLeft().getX() > other.getPoRight().getX() && this.getPoLeft().getX() > other.getPoRight().getX()){
            return ZERO;
        } else if(this.getPoLeft().getX() < other.getPoLeft().getX() && this.getPoRight().getX()<other.getPoLeft().getX()){
            return ZERO;
        } else{
            if(this.getPoRight().getX() > other.getPoRight().getX()){
                return other.getPoRight().getX() - this.getPoLeft().getX();
            } else{
                return this.getPoRight().getX() - other.getPoLeft().getX();
            }
        }
    }

    /**
     * Compute the trapeze perimeter,  which constructed by this segment and a reference segment.
     *
     * @param other the other segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter(Segment2 other) {
        double segment1Length = getLength();
        double segment2Length = other.getLength();

        ///CALCULATING TRAPEZE RIGHT SIDE LENGTH
        double rightSide = Math.sqrt(Math.pow((other.getPoRight().getY() - this.getPoRight().getY()),POWER_OF_TWO) + Math.pow(other.getPoRight().getX() - this.getPoRight().getX(),POWER_OF_TWO));

        ///CALCULATING TRAPEZE LEFT SIDE LENGTH
        double leftSide = Math.sqrt(Math.pow((other.getPoLeft().getY() - this.getPoLeft().getY()),POWER_OF_TWO) + Math.pow(other.getPoLeft().getX() - this.getPoLeft().getX(),POWER_OF_TWO));

        ///RETURN SUM OF ALL SIDES///
        return segment1Length + segment2Length + rightSide + leftSide;
    }
    /////////////////////////////////////////////////////////////

    ///TO STRING METHOD/////////////////////
    public String toString() {
        return "(" + this.getPoLeft().getX() + "," + this.getPoLeft().getY() + ")---(" + this.getPoRight().getX() + "," + this.getPoRight().getY() + ")";
    }
    ////////////////////////////////////////
}
