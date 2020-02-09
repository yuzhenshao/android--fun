package com.syz.essay;

public class Game {
    private int itsScore=0;
    private int itsCurFrame=0;
    private boolean firstThrowFInFrame=true;

    public int getItsScore() {
        return itsScore;
    }

    public int getItsCurFrame() {
        return itsCurFrame;
    }

    public void add(int pins){
        itsScore+=pins;
        adjustCurFrame(pins);
    }

    private void adjustCurFrame(int pins) {
        if(firstThrowFInFrame){
            if(pins==10){
                itsCurFrame++;
            }else{
                firstThrowFInFrame=false;
            }
        }else{
            firstThrowFInFrame=true;
            itsCurFrame++;
        }
        itsCurFrame=Math.min(11,itsCurFrame);
    }

    public void scoreForFrame(int theFrame){

    }

}
