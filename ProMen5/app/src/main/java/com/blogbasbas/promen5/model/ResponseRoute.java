package com.blogbasbas.promen5.model;

import java.util.ArrayList;

/**
 * Created by FUADMASKA on 6/16/2017.
 */

public class ResponseRoute {

    ArrayList<Kedua> routes = new ArrayList<>();

    public ArrayList<Kedua> getRoutes() {
        return routes;
    }

    public class Kedua {
        ArrayList<Keempat> legs = new ArrayList<>();
        public ArrayList<Keempat> getLegs(){
            return legs;
        }

        public Ketiga getOverview_polyline() {
            return overview_polyline;
        }

        Ketiga overview_polyline;

        public class Ketiga {


            String points;

            public String getPoints() {
                return points;
            }
        }
    }
}
