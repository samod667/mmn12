public class Segment1 {
    private Point _poLeft;
    private Point _poRight;

    final int ZERO = 0;
    final double POWER_OF_TWO = 2;

    //CONSTRUCTORS///
    public Segment1(Point left, Point right) {
        if (right.getY() != left.getY()) {
            right.setY(left.getY());
        }
        this._poLeft = left;
        this._poRight = right;
    }

    public Segment1(double leftX, double leftY, double rightX, double rightY) {
        if (rightY != leftY) {
            //            System.out.println("Can't assign right Y value - value is changed to " + leftY);
            rightY = leftY;
        }

        this._poLeft = new Point(leftX, leftY);
        this._poRight = new Point(rightX, rightY);
    }

    public Segment1(Segment1 other) {
        if(other != null){
            this._poLeft = other._poLeft;
            this._poRight = other._poRight;
        }
    }

    //GETTERS//
    Point getPoLeft() {
        return this._poLeft;
    }

    Point getPoRight() {
        return this._poRight;
    }

    double getLength() {
        return Math.abs(this._poRight.getX() - this._poLeft.getX());
    }

    ///TO STRING METHOD///
    public String toString() {
        return "(" + this._poLeft.getX() + "," + this._poLeft.getY() + ")---(" + this._poRight.getX() + "," + this._poRight.getY() + ")";
    }

    ///METHODS///
    boolean equals(Segment1 other) {
        if (this._poLeft.getX() == other._poLeft.getX() && this._poLeft.getY() == other._poLeft.getY()) {
            return this._poRight.getX() == other._poRight.getX() && this._poRight.getY() == other._poRight.getY();
        }
        return false;
    }

    boolean isAbove(Segment1 other) {
        return this._poLeft.getY() > other._poLeft.getY();
    }

    boolean isUnder(Segment1 other) {
        return other.isAbove(this);
    }

    boolean isLeft(Segment1 other) {
        return this._poRight.getY() < other._poLeft.getX();
    }

    boolean isRight(Segment1 other) {
        return other.isLeft(this);
    }

    void moveHorizontal(double delta) {
        double leftX = this._poLeft.getX();
        double rightX = this._poRight.getX();

        if (leftX + delta >= ZERO) {
            this._poLeft.setX(leftX + delta);
            this._poRight.setX(rightX + delta);
        }
    }

    void moveVertical(double delta) {
        double leftY = this._poLeft.getY();
        double rightY = this._poRight.getY();

        if (leftY + delta >= ZERO)
            this._poLeft.setY(leftY + delta);
        this._poRight.setY(rightY + delta);
    }

    void changeSize(double delta) {
        double rightX = this._poRight.getX();

        if (rightX > this._poLeft.getX()) {
            this._poRight.setX(rightX + delta);
        }
    }

    boolean pointOnSegment(Point p) {
        double pointX = p.getX();
        double pointY = p.getY();

        if (pointX >= this._poLeft.getX() && pointX <= this._poRight.getX()) {
            return pointY == this._poLeft.getY();
        }
        return false;
    }

    public boolean isBigger(Segment1 other) {
        return this.getLength() > other.getLength();
    }

    public double overlap(Segment1 other) {
        if(other._poRight.getX() > this._poLeft.getX()){
            return other._poRight.getX() - this._poLeft.getX();
        } else {
            return this.getPoRight().getX() - other.getPoLeft().getX();
        }
    }

    public double trapezePerimeter(Segment1 other) {
        double segmentLength1 = this.getLength();
        double segmentLength2 = other.getLength();

        ///CALCULATING FIRST TRIANGLE INSIDE TRAPEZOID///
        double a1 = Math.abs(this._poLeft.getY() - other._poLeft.getY());
        double b1 = Math.abs(this._poLeft.getX() - other._poLeft.getX());
        ///TRAPEZOID SIDE
        double c1 = Math.sqrt(Math.pow(a1, POWER_OF_TWO) + Math.pow(b1, POWER_OF_TWO));

        ///CALCULATING SECOND TRIANGLE INSIDE TRAPEZOID///
        double b2 = Math.abs(this._poRight.getX() - other._poRight.getX());
        ///TRAPEZOID SIDE
        double c2 = Math.sqrt(Math.pow(a1, POWER_OF_TWO) + Math.pow(b2, POWER_OF_TWO));

        ///RETURN SUM OF ALL SIDES///
        return (segmentLength1 + segmentLength2 + c1 + c2);


    }
}
