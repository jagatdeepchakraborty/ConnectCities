package com.mc.connectcities.logic;

import com.mc.connectcities.exception.ProcessingException;
import com.mc.connectcities.model.CityConnections;
import com.mc.connectcities.util.CommonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConnectCitiesLogic {
    String fileLocation;

    public ConnectCitiesLogic(@Value("${file.location}") String fileLocation) {
        this.fileLocation = fileLocation;
    }

    String result = null;

    public String connected(String origin, String destination) {
        result = "no";
        try {
            ArrayList<String> connectedCitiesList = CommonUtils.readFileToArraylist(fileLocation);
            Map<String, CityConnections> cityConnectionMap = prepareData(connectedCitiesList);
            CityConnections cityConnections = null;
            if (cityConnectionMap.size() > 0) {
                cityConnections = cityConnectionMap.get(origin.toLowerCase().trim());
            }else {
                throw new ProcessingException("No data found in file");
            }
            if (cityConnections != null) {
                findIfConnected(cityConnections, destination.toLowerCase().trim());
            }else {
                throw new ProcessingException("Origin is not part of data");
            }
        } catch (ProcessingException e) {
            return e.getMessage();
        }
        return result;
    }

    void findIfConnected(CityConnections cityConnections, String destination) {
        boolean returned;
        //If destination is connected to this city. Set the result to 'yes' if found.
        if (cityConnections.getConnectedCities().containsKey(destination)) {
            result = "yes";
        }
        //Fix for StackOverflowError. Never miss this
        if (!cityConnections.isChecked()) {
            cityConnections.setChecked(true);
        } else {
            return;
        }
        //Check recursively through all the connected cities until destination is found.
        for (CityConnections cityConnections1 : cityConnections.getConnectedCities().values()) {
            if (!result.equalsIgnoreCase("yes")) {
                findIfConnected(cityConnections1, destination);
            }
        }

    }

    private Map<String, CityConnections> prepareData(ArrayList<String> connectedCitiesList) {
        Map<String, CityConnections> cityConnectionMap = new HashMap<String, CityConnections>();
        ArrayList<CityConnections> cityConnectionsList = null;
        for (String connection : connectedCitiesList) {
            cityConnectionsList = new ArrayList<>();
            for (String city : connection.split(",")) {
                //Trim the spaces after split and make it all lowercase so that map operations work
                city = city.toLowerCase().trim();
                //If map doesn't contain the city add to it
                if (!cityConnectionMap.containsKey(city)) {
                    cityConnectionMap.put(city, new CityConnections(city));
                }
                //Get the existing object for the city
                cityConnectionsList.add(cityConnectionMap.get(city));
            }
            //Add to the connection list and map if the first city doesn't exist
            if (!cityConnectionsList.get(0).getConnectedCities().containsKey(cityConnectionsList.get(1).getCity())) {
                cityConnectionsList.get(0).getConnectedCities().put(cityConnectionsList.get(1).getCity(), cityConnectionsList.get(1));
                cityConnectionMap.get(cityConnectionsList.get(0).getCity()).getConnectedCities().put(cityConnectionsList.get(1).getCity(), cityConnectionsList.get(1));
            }
            //Add to the connection list and map if the second city doesn't exist
            if (!cityConnectionsList.get(1).getConnectedCities().containsKey(cityConnectionsList.get(0).getCity())) {
                cityConnectionsList.get(1).getConnectedCities().put(cityConnectionsList.get(0).getCity(), cityConnectionsList.get(0));
                cityConnectionMap.get(cityConnectionsList.get(1).getCity()).getConnectedCities().put(cityConnectionsList.get(0).getCity(), cityConnectionsList.get(0));
            }
        }
        return cityConnectionMap;
    }


}
