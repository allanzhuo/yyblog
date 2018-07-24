package net.laoyeye.yyblog.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * created by laoyeye on 2018/2/8 at 16:41
 */
public class IpInfoBO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int code;
    private Info data;

    public static class Info {
        private String ip;
        private String country;
        private String area;
        private String region;
        private String city;
        private String county;
        private String isp;
        @JsonProperty(value = "country_id")
        private String countryId;
        @JsonProperty(value = "area_id")
        private String areaId;
        @JsonProperty(value = "region_id")
        private String regionId;
        @JsonProperty(value = "city_id")
        private String cityId;
        @JsonProperty(value = "county_id")
        private String countyId;
        @JsonProperty(value = "isp_id")
        private String ispId;
        public String getIp() {
            return ip;
        }
        public void setIp(String ip) {
            this.ip = ip;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public String getArea() {
            return area;
        }
        public void setArea(String area) {
            this.area = area;
        }
        public String getRegion() {
            return region;
        }
        public void setRegion(String region) {
            this.region = region;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getCounty() {
            return county;
        }
        public void setCounty(String county) {
            this.county = county;
        }
        public String getIsp() {
            return isp;
        }
        public void setIsp(String isp) {
            this.isp = isp;
        }
        public String getCountryId() {
            return countryId;
        }
        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }
        public String getAreaId() {
            return areaId;
        }
        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }
        public String getRegionId() {
            return regionId;
        }
        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }
        public String getCityId() {
            return cityId;
        }
        public void setCityId(String cityId) {
            this.cityId = cityId;
        }
        public String getCountyId() {
            return countyId;
        }
        public void setCountyId(String countyId) {
            this.countyId = countyId;
        }
        public String getIspId() {
            return ispId;
        }
        public void setIspId(String ispId) {
            this.ispId = ispId;
        }
        public Info() {
            super();
        }
        public Info(String ip, String country, String area, String region, String city, String county, String isp,
                String countryId, String areaId, String regionId, String cityId, String countyId, String ispId) {
            super();
            this.ip = ip;
            this.country = country;
            this.area = area;
            this.region = region;
            this.city = city;
            this.county = county;
            this.isp = isp;
            this.countryId = countryId;
            this.areaId = areaId;
            this.regionId = regionId;
            this.cityId = cityId;
            this.countyId = countyId;
            this.ispId = ispId;
        }

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Info getData() {
        return data;
    }

    public void setData(Info data) {
        this.data = data;
    }

    public IpInfoBO() {
        super();
    }

    public IpInfoBO(int code, Info data) {
        super();
        this.code = code;
        this.data = data;
    }
}
