package com.libcommon.slidemenu;

/**
 * Created by yz on 2020/4/1.
 */
class MenuItem {
    final int id;
    final MenuItemClickListener listener;

    public MenuItem(int id, MenuItemClickListener listener) {
        this.id = id;
        this.listener = listener;
    }
}
