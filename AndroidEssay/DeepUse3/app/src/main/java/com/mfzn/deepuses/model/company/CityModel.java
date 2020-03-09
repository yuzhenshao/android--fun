package com.mfzn.deepuses.model.company;

/**
 * @author zhangbostay
 * @date 2019/4/10
 */
public class CityModel {


    /**
     * areaid : 320401
     * areaname : 市辖区
     * cityid : 320400
     * cityname : 常州市
     * provinceid : 320000
     * provincename : 江苏省
     */

    private String areaID;
    private String areaName;
    private String cityID;
    private String cityName;
    private String provinceID;
    private String provinceName;

    public String getAreaid() {
        return areaID;
    }

    public void setAreaid(String areaid) {
        this.areaID = areaid;
    }

    public String getAreaname() {
        return areaName;
    }

    public void setAreaname(String areaname) {
        this.areaName = areaname;
    }

    public String getCityid() {
        return cityID;
    }

    public void setCityid(String cityid) {
        this.cityID = cityid;
    }

    public String getCityname() {
        return cityName;
    }

    public void setCityname(String cityname) {
        this.cityName = cityname;
    }

    public String getProvinceid() {
        return provinceID;
    }

    public void setProvinceid(String provinceid) {
        this.provinceID = provinceid;
    }

    public String getProvincename() {
        return provinceName;
    }

    public void setProvincename(String provincename) {
        this.provinceName = provincename;
    }
}
