package com.sb.onebrc.entity;

public class RawData {
        private String station;
        private Float temp;

        public RawData() {
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

        @Override
        public String toString() {
                return "RawData [station=" + station + ", temp=" + temp + "]";
        }

}
