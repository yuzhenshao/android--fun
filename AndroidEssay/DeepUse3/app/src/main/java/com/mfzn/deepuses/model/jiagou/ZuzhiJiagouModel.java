package com.mfzn.deepuses.model.jiagou;

import java.io.Serializable;
import java.util.List;

public class ZuzhiJiagouModel implements Serializable {
    /**
     * departmentID : 0
     * departmentName : 公司
     * level : 0
     * sons : [{"departmentID":4,"departmentName":"市场部","level":1,"sons":[{"departmentID":238,"departmentName":"不急","level":2,"sons":[],"staff":[{"positionName":"","u_name":"骨灰盒","uid":28,"userno":"99999999","u_phone":"19941640525","u_head":"/public/images/u_head/28/20190510/0459410707c9973e638fadac8859d02d.jpg","roleID":1}]}],"staff":[{"positionName":"","u_name":"骨灰盒","uid":28,"userno":"99999999","u_phone":"19941640525","u_head":"/public/images/u_head/28/20190510/0459410707c9973e638fadac8859d02d.jpg","roleID":1}]}]
     * staff : [{"positionName":"","u_name":"零翼智能","uid":35,"userno":"84878438","u_phone":"13382862982","u_head":"/public/images/u_head/35/20190510/e8bd7bbbeb7f74d994dc6879b1cdaba7.jpg","roleID":4}]
     */

    private int departmentID;
    private String departmentName;
    private int level;
    private List<SonsBeanX> sons;
    private List<StaffBeanXX> staff;

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

    public List<SonsBeanX> getSons() {
        return sons;
    }

    public void setSons(List<SonsBeanX> sons) {
        this.sons = sons;
    }

    public List<StaffBeanXX> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffBeanXX> staff) {
        this.staff = staff;
    }

    public static class SonsBeanX implements Serializable {
        /**
         * departmentID : 4
         * departmentName : 市场部
         * level : 1
         * sons : [{"departmentID":238,"departmentName":"不急","level":2,"sons":[],"staff":[{"positionName":"","u_name":"骨灰盒","uid":28,"userno":"99999999","u_phone":"19941640525","u_head":"/public/images/u_head/28/20190510/0459410707c9973e638fadac8859d02d.jpg","roleID":1}]}]
         * staff : [{"positionName":"","u_name":"骨灰盒","uid":28,"userno":"99999999","u_phone":"19941640525","u_head":"/public/images/u_head/28/20190510/0459410707c9973e638fadac8859d02d.jpg","roleID":1}]
         */

        private int departmentID;
        private String departmentName;
        private int level;
        private List<SonsBeanX> sons;
        private List<StaffBeanXX> staff;
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

        public List<SonsBeanX> getSons() {
            return sons;
        }

        public void setSons(List<SonsBeanX> sons) {
            this.sons = sons;
        }

        public List<StaffBeanXX> getStaff() {
            return staff;
        }

        public void setStaff(List<StaffBeanXX> staff) {
            this.staff = staff;
        }

    }

    public static class StaffBeanXX implements Serializable {
        /**
         * positionName :
         * u_name : 零翼智能
         * uid : 35
         * userno : 84878438
         * u_phone : 13382862982
         * u_head : /public/images/u_head/35/20190510/e8bd7bbbeb7f74d994dc6879b1cdaba7.jpg
         * roleID : 4
         */

        private String positionName;
        private String u_name;
        private String uid;
        private String userno;
        private String u_phone;
        private String u_head;
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

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUserno() {
            return userno;
        }

        public void setUserno(String userno) {
            this.userno = userno;
        }

        public String getU_phone() {
            return u_phone;
        }

        public void setU_phone(String u_phone) {
            this.u_phone = u_phone;
        }

        public String getU_head() {
            return u_head;
        }

        public void setU_head(String u_head) {
            this.u_head = u_head;
        }

        public int getRoleID() {
            return roleID;
        }

        public void setRoleID(int roleID) {
            this.roleID = roleID;
        }
    }

//    public static class SonsBeanX implements Serializable {
//        /**
//         * departmentID : 238
//         * departmentName : 不急
//         * level : 2
//         * sons : []
//         * staff : [{"positionName":"","u_name":"骨灰盒","uid":28,"userno":"99999999","u_phone":"19941640525","u_head":"/public/images/u_head/28/20190510/0459410707c9973e638fadac8859d02d.jpg","roleID":1}]
//         */
//
//
//        private int departmentID;
//        private String departmentName;
//        private int level;
//        private List<StaffBeanXX> staff;
//        private boolean selectDe = false;
//
//        public boolean getMoren() {
//            return moren;
//        }
//
//        public void setMoren(boolean moren) {
//            this.moren = moren;
//        }
//
//        private boolean moren = false;
//
//        public boolean getSelectDe() {
//            return selectDe;
//        }
//
//        public void setSelectDe(boolean selectDe) {
//            this.selectDe = selectDe;
//        }
//
//        public int getDepartmentID() {
//            return departmentID;
//        }
//
//        public void setDepartmentID(int departmentID) {
//            this.departmentID = departmentID;
//        }
//
//        public String getDepartmentName() {
//            return departmentName;
//        }
//
//        public void setDepartmentName(String departmentName) {
//            this.departmentName = departmentName;
//        }
//
//        public int getLevel() {
//            return level;
//        }
//
//        public void setLevel(int level) {
//            this.level = level;
//        }
//
//        public List<StaffBeanXX> getStaff() {
//            return staff;
//        }
//
//        public void setStaff(List<StaffBeanXX> staff) {
//            this.staff = staff;
//        }
//
//    }

//    public static class StaffBeanXX implements Serializable {
//        /**
//         * positionName :
//         * u_name : 骨灰盒
//         * uid : 28
//         * userno : 99999999
//         * u_phone : 19941640525
//         * u_head : /public/images/u_head/28/20190510/0459410707c9973e638fadac8859d02d.jpg
//         * roleID : 1
//         */
//
//
//        private String positionName;
//        private String u_name;
//        private String uid;
//        private String userno;
//        private String u_phone;
//        private String u_head;
//        private int roleID;
//        private boolean selectType = false;
//
//        private String staffName;
//        private boolean moren = false;
//
//        public boolean getMoren() {
//            return moren;
//        }
//
//        public void setMoren(boolean moren) {
//            this.moren = moren;
//        }
//
//        public String getStaffName() {
//            return staffName;
//        }
//
//        public void setStaffName(String staffName) {
//            this.staffName = staffName;
//        }
//
//        public boolean getSelectType() {
//            return selectType;
//        }
//
//        public void setSelectType(boolean selectType) {
//            this.selectType = selectType;
//        }
//
//        public String getPositionName() {
//            return positionName;
//        }
//
//        public void setPositionName(String positionName) {
//            this.positionName = positionName;
//        }
//
//        public String getU_name() {
//            return u_name;
//        }
//
//        public void setU_name(String u_name) {
//            this.u_name = u_name;
//        }
//
//        public String getUid() {
//            return uid;
//        }
//
//        public void setUid(String uid) {
//            this.uid = uid;
//        }
//
//        public String getUserno() {
//            return userno;
//        }
//
//        public void setUserno(String userno) {
//            this.userno = userno;
//        }
//
//        public String getU_phone() {
//            return u_phone;
//        }
//
//        public void setU_phone(String u_phone) {
//            this.u_phone = u_phone;
//        }
//
//        public String getU_head() {
//            return u_head;
//        }
//
//        public void setU_head(String u_head) {
//            this.u_head = u_head;
//        }
//
//        public int getRoleID() {
//            return roleID;
//        }
//
//        public void setRoleID(int roleID) {
//            this.roleID = roleID;
//        }
//    }

//    public static class StaffBeanX implements Serializable {
//        /**
//         * positionName :
//         * u_name : 骨灰盒
//         * uid : 28
//         * userno : 99999999
//         * u_phone : 19941640525
//         * u_head : /public/images/u_head/28/20190510/0459410707c9973e638fadac8859d02d.jpg
//         * roleID : 1
//         */
//
//        private String positionName;
//        private String u_name;
//        private String uid;
//        private String userno;
//        private String u_phone;
//        private String u_head;
//        private int roleID;
//        private boolean selectType = false;
//        private String staffName;
//        private boolean moren = false;
//
//        public boolean getMoren() {
//            return moren;
//        }
//
//        public void setMoren(boolean moren) {
//            this.moren = moren;
//        }
//
//        public String getStaffName() {
//            return staffName;
//        }
//
//        public void setStaffName(String staffName) {
//            this.staffName = staffName;
//        }
//
//        public boolean getSelectType() {
//            return selectType;
//        }
//
//        public void setSelectType(boolean selectType) {
//            this.selectType = selectType;
//        }
//
//        public String getPositionName() {
//            return positionName;
//        }
//
//        public void setPositionName(String positionName) {
//            this.positionName = positionName;
//        }
//
//        public String getU_name() {
//            return u_name;
//        }
//
//        public void setU_name(String u_name) {
//            this.u_name = u_name;
//        }
//
//        public String getUid() {
//            return uid;
//        }
//
//        public void setUid(String uid) {
//            this.uid = uid;
//        }
//
//        public String getUserno() {
//            return userno;
//        }
//
//        public void setUserno(String userno) {
//            this.userno = userno;
//        }
//
//        public String getU_phone() {
//            return u_phone;
//        }
//
//        public void setU_phone(String u_phone) {
//            this.u_phone = u_phone;
//        }
//
//        public String getU_head() {
//            return u_head;
//        }
//
//        public void setU_head(String u_head) {
//            this.u_head = u_head;
//        }
//
//        public int getRoleID() {
//            return roleID;
//        }
//
//        public void setRoleID(int roleID) {
//            this.roleID = roleID;
//        }
//    }
}
