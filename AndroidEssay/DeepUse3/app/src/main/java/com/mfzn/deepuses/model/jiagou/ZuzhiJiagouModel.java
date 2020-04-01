package com.mfzn.deepuses.model.jiagou;

import java.io.Serializable;
import java.util.List;

public class ZuzhiJiagouModel implements Serializable {
    private int departmentID;
    private String departmentName;
    private int level;
    private List<SonsBean> sons;
    private List<StaffBean> staff;

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<SonsBean> getSons() {
        return sons;
    }

    public void setSons(List<SonsBean> sons) {
        this.sons = sons;
    }

    public List<StaffBean> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffBean> staff) {
        this.staff = staff;
    }

    public static class SonsBean implements Serializable {
        private int departmentID;
        private String departmentName;
        private int level;
        private List<SonsBean> sons;
        private List<StaffBean> staff;
        private boolean selectDe = false;

        public boolean getMoren() {
            return moren;
        }

        public void setMoren(boolean moren) {
            this.moren = moren;
        }

        private boolean moren = false;


        public boolean getSelectDe() {
            return selectDe;
        }

        public void setSelectDe(boolean selectDe) {
            this.selectDe = selectDe;
        }

        public int getDepartmentID() {
            return departmentID;
        }

        public void setDepartmentID(int departmentID) {
            this.departmentID = departmentID;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<SonsBean> getSons() {
            return sons;
        }

        public void setSons(List<SonsBean> sons) {
            this.sons = sons;
        }

        public List<StaffBean> getStaff() {
            return staff;
        }

        public void setStaff(List<StaffBean> staff) {
            this.staff = staff;
        }

    }

    public static class StaffBean implements Serializable {
        private String positionName;
        private String userID;
        private String userAvatar;
        private String userPhone;
        private String staffName;
        private int roleID;
        private boolean selectType = false;
        private boolean moren = false;

        public boolean getMoren() {
            return moren;
        }

        public void setMoren(boolean moren) {
            this.moren = moren;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public boolean getSelectType() {
            return selectType;
        }

        public void setSelectType(boolean selectType) {
            this.selectType = selectType;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public int getRoleID() {
            return roleID;
        }

        public void setRoleID(int roleID) {
            this.roleID = roleID;
        }

    }
}
