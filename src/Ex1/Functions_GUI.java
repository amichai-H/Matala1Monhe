package Ex1;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
    public static boolean divByZero = false;
    ArrayList<function> arrayList = new ArrayList<>();
    public static Color[] Colors = {Color.blue, Color.cyan,
            Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
    @Override
    public void initFromFile(String file) throws IOException {
        
    }

    @Override
    public void saveToFile(String file) throws IOException {

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        StdDraw.setCanvasSize(width,height);
        StdDraw.setPenColor(StdDraw.BLACK);

        double rxMin = rx.get_min()-1;
        double rxMax = rx.get_max()+1;
        double ryMin = ry.get_min()-1;
        double ryMax = ry.get_max()+1;

        double rangeY = Math.abs(ryMin) + Math.abs(ryMax);
        double oneYmeasure = 1/rangeY;
        double rangeX = Math.abs(rxMin) + Math.abs(rxMax);
        double oneXmeasure = 1/rangeX;
        double zeroX = -1,zeroY = -1;
        if (ry.get_min()<=0){
            zeroX = -1*oneYmeasure*ryMin;
        }
        if (rx.get_min()<=0){
            zeroY = -1*oneXmeasure*rxMin;
        }
        StdDraw.line(zeroY,0,zeroY,1);//y
        StdDraw.line(0,zeroX,1,zeroX);//x
        double _X = rxMin;
        double _Y = ryMin;
        for (double i = 0,j=0;i<1||j<1;i=i+oneYmeasure,j=j+oneXmeasure){
            StdDraw.line(zeroY-0.01,i,zeroY+0.01,i);//y
            if (_Y!=0) {
                StdDraw.text(zeroY + 0.02, i, _Y + "");
            }
            if (_X!=0) {
                StdDraw.text(j, zeroX + 0.02, _X + "");
            }
            _Y++;
            _X++;
            StdDraw.line(j,zeroX-0.01,j,zeroX+0.01);//x

        }
        double epsilon = 0.05;
        System.out.println(epsilon);
        int c = 0;
        for(function function1: arrayList){
            StdDraw.setPenColor(Colors[c++]);
            for(double i = rxMin ;i<rxMax;i=i+epsilon){
                    double x0= zeroY + i*oneXmeasure;
                    double x1 = zeroY + (i + epsilon)*oneXmeasure;

                    //
                    double f1 = function1.f(i);
                    double f1e = function1.f(i+epsilon);
                    //
                    double y0 = zeroX + f1*oneYmeasure ;
                    double y1 = zeroX + f1e*oneYmeasure;
                    if (!divByZero) {
                        StdDraw.line(x0, y0, x1, y1);
                    }
                    else {
                        System.out.println(divByZero);
                        divByZero = false;
                    }

            }
        }

    }

    @Override
    public void drawFunctions(String json_file) {

    }

    public function copy() {
        return (function) arrayList.clone();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return arrayList.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return arrayList.iterator();
    }

    @Override
    public Object[] toArray() {
        return arrayList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return arrayList.toArray(a);
    }

    @Override
    public boolean add(function function) {
        return arrayList.add(function);
    }

    @Override
    public boolean remove(Object o) {
        return arrayList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return arrayList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return arrayList.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return arrayList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return arrayList.retainAll(c);
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    public void drawFunctions() {
    }
}
