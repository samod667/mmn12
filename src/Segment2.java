public class Segment2 {

    ///DECLARING VARIABLES///
    private Point _poCenter;
    private double _length;

    ///DECLARING FINALS
    final int ZERO = 0;
    final int POWER_OF_TWO = 2;

    ///CONSTRUCTORS///
    public Segment2(Point left, Point right) {
        if (left.getY() != right.getY()) {
            right.setY(left.getY());
        }

        this._length = getSegmentLength(left.getX(), right.getX());
        this._poCenter = new Point(right.getX() - (this._length / POWER_OF_TWO), left.getY());
    }

    public Segment2(double leftX, double leftY, double rightX, double rightY) {
        if (rightY != leftY) {
            rightY = leftY;
        }
        this._length = getSegmentLength(leftX, rightX);

        this._poCenter = new Point(rightX - (this._length / POWER_OF_TWO), rightY);
    }

    public Segment2(Segment2 other) {
        this._length = other._length;
        this._poCenter = new Point(other._poCenter);
    }

    public Segment2(Point point, double length) {
        this._poCenter = point;

        if (length > ZERO) {
            this._length = length;
        }
    }

    ///PRIVATE HELPER GETTING SEGMENT LENGTH METHOD///
    private double getSegmentLength(double leftX, double rightX) {
        return Math.abs(rightX - leftX);
    }
    //////////////////////////////////////////////////

    ///OTHER METHODS///
    Point getPoLeft() {
        return new Point(this._poCenter.getX() - (this._length / POWER_OF_TWO), this._poCenter.getY());
    }

    Point getPoRight() {
        return new Point(this._poCenter.getX() + (this._length / POWER_OF_TWO), this._poCenter.getY());
    }

    public double getLength() {
        return getSegmentLength(getPoLeft().getX(), getPoRight().getX());
    }

    public boolean equals(Segment2 other) {
        if (this._poCenter.getX() == other._poCenter.getX() && this._poCenter.getY() == other._poCenter.getY()) {
            return this._length == other._length;
        }
        return false;
    }

    public boolean isAbove(Segment2 other) {
        return this._poCenter.getY() > other._poCenter.getY();
    }

    public boolean isUnder(Segment2 other) {
        return other.isAbove(this);
    }

    public boolean isLeft(Segment2 other) {
        return this.getPoRight().getX() < other.getPoLeft().getX();
    }

    public boolean isRight(Segment2 other) {
        return other.isLeft(this);
    }

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

    public void moveVertical(double delta) {
        if (this._poCenter.getY() + delta >= ZERO) {
            double yValue = this._poCenter.getY();
            this._poCenter.setY(yValue + delta);
        }
    }

    public void changeSize(double delta) {
        double leftX = this.getPoLeft().getX();
        double rightX = this.getPoRight().getX();

        if (rightX + delta > leftX) {
            rightX += delta;

            this._length = getSegmentLength(leftX, rightX);
            this._poCenter.setX(rightX - (_length / POWER_OF_TWO));
        }
    }

    public boolean pointOnSegment(Point p) {
        if (p.getY() == this._poCenter.getY()) {
            double rightX = this.getPoRight().getX();
            double leftX = this.getPoLeft().getX();

            return p.getX() >= leftX && p.getX() <= rightX;
        }
        return false;
    }

    public boolean isBigger(Segment2 other) {
        return this._length > other._length;
    }

    public double overlap(Segment2 other) {
        if (other.getPoRight().getX() > this.getPoLeft().getX()) {
            return other.getPoRight().getX() - this.getPoLeft().getX();
        } else {
            return this.getPoRight().getX() - other.getPoLeft().getX();
        }
    }

    public double trapezePerimeter(Segment2 other) {
        double segment1Length = getLength();
        double segment2Length = other.getLength();

        ///CALCULATING FIRST TRIANGLE INSIDE TRAPEZOID///
        double a1 = getSegmentLength(this._poCenter.getY(), other._poCenter.getY());
        double b1 = getSegmentLength(this.getPoLeft().getX(), other.getPoLeft().getX());
        ///TRAPEZOID SIDE
        double c1 = Math.sqrt(Math.pow(a1, POWER_OF_TWO) + Math.pow(b1, POWER_OF_TWO));

        ///CALCULATING SECOND TRIANGLE INSIDE TRAPEZOID///
        double b2 = getSegmentLength(this.getPoRight().getX(), other.getPoRight().getX());
        double c2 = Math.sqrt(Math.pow(a1, POWER_OF_TWO) + Math.pow(b2, POWER_OF_TWO));

        ///RETURN SUM OF ALL SIDES///
        return (segment1Length + segment2Length + c1 + c2);
    }

    ///TO STRING METHOD///
    public String toString() {
        return "(" + this.getPoLeft().getX() + "," + this.getPoLeft().getY() + ")---(" + this.getPoRight().getX() + "," + this.getPoRight().getY() + ")";
    }

}
