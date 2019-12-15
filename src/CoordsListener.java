import java.awt.*;
import java.util.Observable;

public class CoordsListener extends Observable {
    private int ix, iy, ex, ey;

    public CoordsListener() {
        this.ix=0;
        this.iy=0;
        this.ex=0;
        this.ey=0;
    }

    public void setPosition(int x1, int y1,int  x2,int y2){
        this.ix = x1;
        this.iy=y1;
        this.ex=x2;
        this.ey=y2;

        setChanged();
        notifyObservers();
    }

    public int getIx() {
        return ix;
    }

    public int getIy() {
        return iy;
    }

    public int getEx() {
        return ex;
    }

    public int getEy() {
        return ey;
    }
}
