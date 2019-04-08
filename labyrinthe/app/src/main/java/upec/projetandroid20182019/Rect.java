package upec.projetandroid20182019;

final class Rect extends Object {
    float left;
    float top;
    float right;
    float bottom;

    public Rect(float left,float top, float right, float bottom){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

    }

    public float getRectXCenter(){
        return (left + (right - left));
    }

    public float getRectYCenter(){
        return (top +(bottom - top));
    }


}
