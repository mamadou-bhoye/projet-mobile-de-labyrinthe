package upec.projetandroid20182019;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Obstacle {
    List<Rect> listObstacle;

    public Obstacle() {
        this.listObstacle = new ArrayList<>();
    }

    public void addToList(android.graphics.Rect r){
        listObstacle.add(r);
    }

    public List<android.graphics.Rect> getListObstacle() {
        return listObstacle;
    }


}
