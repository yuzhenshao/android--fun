package com.libcommon.titlebar;

/**
 * @author yz @date 2020-03-23
 */
public class TitlebarPressedListener implements ElementPressedListener {


    @Override
    public void leftPressed() {

    }

    @Override
    public void rightPressed() {

    }
}

interface ElementPressedListener {

    void leftPressed();

    void rightPressed();
}

