/**
 * Segment1 represents a line (parallel to the x-axis) using two Points.
 *
 * @author Dor Samoha
 * @version Oct 29th 2020
 */

public class Segment1 {

    ///DECLARING VARIABLES///
    private Point _poLeft;
    private Point _poRight;

    final int ZERO = 0;
    final double POWER_OF_TWO = 2;
    ////////////////////////

    ///CONSTRUCTORS///////////////////////

    /**
     * Constructs a new segment using two Points. If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     *
     * @param left  the left point of the segment
     * @param right the right point of the segment
     */
    public Segment1(Point left, Point right) {
        if(left.getY() != right.getY()){
            right.setY(left.getY());
        }
        this._poLeft = new Point(left);
        this._poRight = new Point(right);
    }

    /**
     * Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point. If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     *
     * @param leftX  X value of the left point
     * @param leftY  Y value of the left point
     * @param rightX X value of the right point
     * @param rightY Y value of the right point
     */
    public Segment1(double leftX, double leftY, double rightX, double rightY) {
        if (rightY != leftY) {
            rightY = leftY;
        }
        this._poLeft = new Point(leftX, leftY);
        this._poRight = new Point(rightX, rightY);
    }

    /**
     * Copy Constructor. Construct a segment using a reference segment.
     *
     * @param other the reference segment
     */
    public Segment1(Segment1 other) {
        if (other != null) {
            this._poLeft = new Point(other._poLeft);
            this._poRight = new Point(other._poRight);
        }
    }
    ////////////////////////////////////////////////////////////////

    ///GETTERS/////////////////////////////////////////////////////

    /**
     * Returns the left point of the segment
     *
     * @return the left point
     */

    public Point getPoLeft() {
        return this._poLeft;
    }

    /**
     * Return the right point of the segment
     *
     * @return the right point
     */
    public Point getPoRight() {
        return this._poRight;
    }

    /////////////////////////////////////////////////////////////////

    ///TO STRING METHOD///////////////////////////
    public String toString() {
        return "(" + this._poLeft.getX() + "," + this._poLeft.getY() + ")---(" + this._poRight.getX() + "," + this._poRight.getY() + ")";
    }
    //////////////////////////////////////////////

    ///METHODS///////////////////////////////////

    /**
     * Check if the reference segment is equal to this segment.
     *
     * @param other the other Segment
     * @return true if both segments are equal
     */

    public boolean equals(Segment1 other) {
        return this._poLeft.equals(other._poLeft) && this._poRight.equals(other._poRight);

    }

    /**
     * Return the segment's length
     *
     * @return the length of segment
     */
    public double getLength() {
        return Math.abs(this._poRight.getX() - this._poLeft.getX());
    }

    /**
     * Check if this segment is above a reference segment.
     *
     * @param other the other segment
     * @return True if this segment is above the other segment
     */
    public boolean isAbove(Segment1 other) {
        return this._poLeft.getY() > other._poLeft.getY();
    }

    /**
     * Check if this segment is under a reference segment.
     *
     * @param other the other segment
     * @return True if this segment is under the other segment
     */
    public boolean isUnder(Segment1 other) {
        return other.isAbove(this);
    }

    /**
     * Check if the entire this segment is left of an other segment.
     *
     * @param other the other segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment1 other) {
        return this._poRight.getX() < other._poLeft.getX();
    }

    /**
     * Check if this segment is right of a received segment.
     *
     * @param other the other segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight(Segment1 other) {
        return other.isLeft(this);
    }

    /**
     * Move the segment horizontally by delta.
     *
     * @param delta the displacement size
     */
    public void moveHorizontal(double delta) {
        double leftX = this._poLeft.getX();
        double rightX = this._poRight.getX();

        if (leftX + delta >= ZERO) {
            this._poLeft.setX(leftX + delta);
            this._poRight.setX(rightX + delta);
        }
    }

    /**
     * Move the segment vertically by delta.
     *
     * @param delta the displacement size
     */
    void moveVertical(double delta) {
        double leftY = this._poLeft.getY();
        double rightY = this._poRight.getY();

        if (leftY + delta >= ZERO)
            this._poLeft.setY(leftY + delta);
        this._poRight.setY(rightY + delta);
    }

    /**
     * Change the segment size by moving the right point by delta. Will be implemented only for a valid delta: only if the new right point remains the right point.
     *
     * @param delta the length change
     */
    public void changeSize(double delta) {
        double rightX = this._poRight.getX();

        if (rightX + delta > this._poLeft.getX()) {
            this._poRight.setX(rightX + delta);
        }
    }

    /**
     * Check if a point is located on the segment.
     *
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment(Point p) {
        double pointX = p.getX();
        double pointY = p.getY();

        if (pointX >= this._poLeft.getX() && pointX <= this._poRight.getX()) {
            return pointY == this._poLeft.getY();
        }
        return false;
    }

    /**
     * Check if this segment is bigger than a reference segment.
     *
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment1 other) {
        return this.getLength() > other.getLength();
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     *
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap(Segment1 other) {
        if(this.getPoLeft().getX() > other._poRight.getX() && this._poLeft.getX() > other._poRight.getX()){
            return 0;
        } else if(this._poLeft.getX() < other._poLeft.getX() && this._poRight.getX()<other._poLeft.getX()){
            return 0;
        } else{
            if(this._poRight.getX() > other._poRight.getX()){
                return other._poRight.getX() - this._poLeft.getX();
            } else{
                return this._poRight.getX() - other._poLeft.getX();
            }
        }
    }

    /**
     * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
     *
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter(Segment1 other) {
        double segmentLength1 = this.getLength();
        double segmentLength2 = other.getLength();

        ///CALCULATE RIGHT SIDE OF TRAPEZE
        double rightSide = Math.sqrt(Math.pow((other._poRight.getY() - this._poRight.getY()),POWER_OF_TWO) + Math.pow(other._poRight.getX() - this._poRight.getX(),POWER_OF_TWO));

        ///CALCULATE LEFT SIDE OF TRAPEZE
        double leftSide = Math.sqrt(Math.pow((other._poLeft.getY() - this._poLeft.getY()),POWER_OF_TWO) + Math.pow(other._poLeft.getX() - this._poLeft.getX(),POWER_OF_TWO));

        ///RETURN SUM
        return segmentLength1 + segmentLength2 + rightSide + leftSide;
    }
    //////////////////////////////////////////////////////////////////
}
