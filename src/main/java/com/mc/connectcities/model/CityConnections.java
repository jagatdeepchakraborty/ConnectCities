package com.mc.connectcities.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CityConnections {
    private String city;
    private Map<String, CityConnections> connectedCities;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public CityConnections(String city) {
        this.city = city;
        connectedCities = new HashMap<String, CityConnections>();
    }

    public String getCity() {
        return city;
    }

    public Map<String, CityConnections> getConnectedCities() {
        return connectedCities;
    }

}
