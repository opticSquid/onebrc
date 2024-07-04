package com.sb.onebrc.entity;

public class RawData {
        private String station;
        private Float temp;

        public RawData() {
        }

        public RawData(String station, Float temp) {
                this.station = station;
                this.temp = temp;
        }

        public String getStation() {
                return station;
        }

        public void setStation(String station) {
                this.station = station;
        }

        public Float getTemp() {
                return temp;
        }

        public void setTemp(Float temp) {
                this.temp = temp;
        }

}
