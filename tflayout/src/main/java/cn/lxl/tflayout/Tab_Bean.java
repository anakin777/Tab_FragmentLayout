package cn.lxl.tflayout;

import android.support.v4.app.Fragment;

public class Tab_Bean {

    /**
     * title : 名称
     * icon : 图标
     * msgcount : 未读消息
     */

    private String title;
    private int generalIcon;
    private int selectedIcon;
    private int msgcount;
    private boolean isFragmentAdded = false;
    private Fragment fragment;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Tab_Bean(String title, int generalIcon, int selectedIcon, int msgcount, Fragment fragment) {
        this.title = title;
        this.generalIcon = generalIcon;
        this.selectedIcon = selectedIcon;
        this.msgcount = msgcount;
        this.fragment = fragment;
    }

    public boolean isFragmentAdded() {
        return isFragmentAdded;
    }

    public void setFragmentAdded(boolean fragmentAdded) {
        isFragmentAdded = fragmentAdded;
    }

    public int getGeneralIcon() {
        return generalIcon;
    }

    public void setGeneralIcon(int generalIcon) {
        this.generalIcon = generalIcon;
    }

    public int getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(int selectedIcon) {
        this.selectedIcon = selectedIcon;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getMsgcount() {
        return msgcount;
    }

    public void setMsgcount(int msgcount) {
        this.msgcount = msgcount;
    }



}
