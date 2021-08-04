package com.example.computerapp.Models;

import com.google.gson.annotations.SerializedName;

public class Desktop {

    @SerializedName("desktopId")
    public int DesktopId;

    @SerializedName("cpu")
    public String Cpu;

    @SerializedName("gpu")
    public String Gpu;

    @SerializedName("ram")
    public String Ram;

    @SerializedName("ssdCap")
    public String SsdCap;

    @SerializedName("hddCap")
    public String HddCap;

    @SerializedName("psu")
    public String Psu;

    @SerializedName("case")
    public String Case;

    public int getDesktopId() {
        return DesktopId;
    }

    public void setDesktopId(int desktopId) {
        DesktopId = desktopId;
    }

    public String getCpu() {
        return Cpu;
    }

    public void setCpu(String cpu) {
        Cpu = cpu;
    }

    public String getGpu() {
        return Gpu;
    }

    public void setGpu(String gpu) {
        Gpu = gpu;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String ram) {
        Ram = ram;
    }

    public String getSsdCap() {
        return SsdCap;
    }

    public void setSsdCap(String ssdCap) {
        SsdCap = ssdCap;
    }

    public String getHddCap() {
        return HddCap;
    }

    public void setHddCap(String hddCap) {
        HddCap = hddCap;
    }

    public String getPsu() {
        return Psu;
    }

    public void setPsu(String psu) {
        Psu = psu;
    }

    public String getCase() {
        return Case;
    }

    public void setCase(String aCase) {
        Case = aCase;
    }
}
