//Class Point
public class Point {

    //Declare variables//
    private double _radius, _alpha;
    private final int POWER_OF_TWO = 2;
    private final int DEFAULT_VAL = 0;
    private final double ALPHA_DEFAULT_VALUE = 90;
    private final double RADIAN_FACTOR = 180;
    private final double ROUND_FACTOR = 10000.0;


    //CONSTRUCTORS//
    public Point(double x, double y) {
        if (x <= DEFAULT_VAL) {
            x = DEFAULT_VAL;
        }
        if (y <= DEFAULT_VAL) {
            y = DEFAULT_VAL;
        }
        //CALCULATE RADIUS AND ALPHA
        this._radius = this.getRadius(x, y);
        this._alpha = this.getAlpha(x, y);
        ////////////////////////////
    }


    public Point(Point other) {
        if(other != null){
            this._alpha = other._alpha;
            this._radius = other._radius;
        }
    }

    //CALCULATE RADIUS//
    private double getRadius(double x, double y) {
        return Math.sqrt(Math.pow(x, POWER_OF_TWO) + Math.pow(y, POWER_OF_TWO));
    }

    //CALCULATE ALPHA//
    private double getAlpha(double x, double y) {
        if (x == DEFAULT_VAL) {
            return ALPHA_DEFAULT_VALUE;
        }
        return Math.atan(y / x) * RADIAN_FACTOR / Math.PI;
    }

    //X TO RADIANS METHOD
    private double _xToRadian(double radius, double alpha) {
        return Math.round(radius * Math.cos(alpha * Math.PI / RADIAN_FACTOR) * ROUND_FACTOR) / ROUND_FACTOR;
    }

    //Y TO RADIANS METHOD
    private double _yToRadian(double radius, double alpha) {
        return Math.round(radius * Math.sin(alpha * Math.PI / RADIAN_FACTOR) * ROUND_FACTOR) / ROUND_FACTOR;
    }


    //GETTERS//
    public double getX() {
        return this._xToRadian(this._radius, this._alpha);
    }


    public double getY() {
        return this._yToRadian(this._radius, this._alpha);
    }
    //////////////////////////////////////////////////////


    //SETTERS//
    public void setX(double num) {
        double y = getY();
        if (num >= DEFAULT_VAL) {
            this._alpha = getAlpha(num, y);
            this._radius = getRadius(num, y);
        } else {
//            System.out.println("PLEASE ENTER A VALID POINT");
        }
    }

    public void setY(double num) {
        double x = getX();
        if (num >= DEFAULT_VAL) {
            this._alpha = getAlpha(x, num);
            this._radius = getRadius(x, num);
        } else {
//            System.out.println("PLEASE ENTER A VALID POINT");
        }
    }
    //////////////////////////////////////////////////////

    //TO STRING METHOD//
    public String toString() {
        return "(" + Math.round(_xToRadian(this._radius, this._alpha) * ROUND_FACTOR) / (double) ROUND_FACTOR + "," + Math.round(_yToRadian(this._radius, this._alpha) * ROUND_FACTOR) / (double) ROUND_FACTOR + ")";
    }
    /////////////////////

    public boolean equals(Point other) {
        return this._radius == other._radius && this._alpha == other._alpha;
    }

    public boolean isAbove(Point other) {
        return this._yToRadian(this._radius, this._alpha) > other._yToRadian(other._radius, other._alpha);
    }

    public boolean isUnder(Point other) {
        return other.isAbove(this);
    }

    public boolean isLeft(Point other) {
        return this._xToRadian(this._radius, this._alpha) < other._xToRadian(other._radius, other._alpha);
    }

    public boolean isRight(Point other) {
        return other.isLeft(this);
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow((p._yToRadian(p._radius, p._alpha) - this._yToRadian(this._radius, this._alpha)), POWER_OF_TWO) + Math.pow((p._xToRadian(p._radius, p._alpha) - this._xToRadian(this._radius, this._alpha)), POWER_OF_TWO));
    }

    public void move(double dx, double dy) {
        double x = _xToRadian(this._radius, this._alpha) + dx;
        double y = _yToRadian(this._radius, this._alpha) + dy;

        if (x >= DEFAULT_VAL && y >= DEFAULT_VAL) {
            this._alpha = this.getAlpha(x, y);
            this._radius = this.getRadius(x, y);
        } else {
//            System.out.println("Sorry, unable to move to points - please enter a valid choice");
        }
    }

}
