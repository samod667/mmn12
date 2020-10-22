public class Segment1 {
    private Point _poLeft;
    private Point _poRight;

    final int ZERO = 0;

    //CONSTRUCTORS///
    public Segment1(Point left, Point right){
        if(right.getY() != left.getY()){
            right.setY(left.getY());
        }
        this._poLeft = left;
        this._poRight = right;
    }
    public Segment1(double leftX, double leftY, double rightX, double rightY){
        if(rightY != leftY)
//            System.out.println("Can't assign right Y value - value is changed to " + leftY);
            rightY = leftY;


        this._poLeft = new Point(leftX, leftY);
        this._poRight = new Point(rightX, rightY);
    }

    public Segment1(Segment1 other){
        this._poLeft = other._poLeft;
        this._poRight = other._poRight;
    }

    //GETTERS//
    Point getPoLeft(){
        return this._poLeft;
    }
    Point getPoRight(){
        return this._poRight;
    }
    double getLength(){
        return this._poLeft.getX() + this._poRight.getX();
    }

    ///TO STRING METHOD///
    public String toString(){
        return "(" + this._poLeft.getX() + "," + this._poLeft.getY() + ")---(" + this._poRight.getX() + ","+this._poRight.getY()+")";
    }

    ///METHODS///
    boolean equals(Segment1 other){
        return this._poLeft.getX() == other._poRight.getX() && this._poLeft.getY() == other._poRight.getY();
    }

    boolean isAbove(Segment1 other){
        return this._poLeft.getY() > other._poLeft.getY();
    }

    boolean isUnder(Segment1 other){
        return !(isAbove(other));
    }

    boolean isLeft(Segment1 other){
        return this._poRight.getY() < other._poLeft.getX();
    }

    boolean isRight(Segment1 other){
        return this._poLeft.getX() > other._poRight.getY();
    }

    void moveHorizontal(double delta){
        double leftX = this._poLeft.getX();
        double rightX = this._poRight.getX();

        this._poLeft.setX(leftX + delta);
        this._poRight.setX(rightX + delta);
    }

    void moveVertical(double delta){
        double leftY = this._poLeft.getY();
        double rightY = this._poRight.getY();



        this._poLeft.setY(leftY + delta);
        this._poRight.setY(rightY + delta);
    }
}
