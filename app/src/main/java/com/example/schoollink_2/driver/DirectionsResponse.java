package com.example.schoollink_2.driver;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

public class DirectionsResponse {

    private List<Route> routes;

    public List<LatLng> getPoints() {
        List<LatLng> points = new ArrayList<>();
        if (routes != null && !routes.isEmpty()) {
            for (Leg leg : routes.get(0).legs) {
                for (Step step : leg.steps) {
                    points.addAll(PolyUtil.decode(step.polyline.points));
                }
            }
        }
        return points;
    }

    class Route {
        List<Leg> legs;
    }

    class Leg {
        List<Step> steps;
    }

    class Step {
        Polyline polyline;
    }

    class Polyline {
        String points;
    }
}
