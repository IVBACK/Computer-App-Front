package com.example.computerapp.Models;

import com.google.gson.annotations.SerializedName;

public class Notebook {

    @SerializedName("notebookId")
    public int NotebookId;

    @SerializedName("brand")
    public String Brand;

    @SerializedName("model")
    public String Model;

    @SerializedName("cpu")
    public String Cpu;

    @SerializedName("gpu")
    public String Gpu;

    @SerializedName("ram")
    public String Ram;

    @SerializedName("ssdCap")
    public String SsdCap;

    public int getNotebookId() {
        return NotebookId;
    }

    public void setNotebookId(int notebookId) {
        NotebookId = notebookId;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
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

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }

    @SerializedName("hddCap")
    public String HddCap;

    @SerializedName("battery")
    public String Battery;
}
