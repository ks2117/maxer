package model;

import java.util.HashMap;
import java.util.Map;

public class RpeConverter {
    private Map<RpeScale, HashMap<Integer, Double>> rpeChart = new HashMap<>();
    private double[][] rpeArray = {
            {1, 0.955, 0.922, 0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.68},
            {0.978, 0.939, 0.907, 0.878, 0.85, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694, 0.667},
            {0.955, 0.922, 0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.68, 0.653},
            {0.939, 0.907, 0.878, 0.85, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694, 0.667, 0.64},
            {0.922, 0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.68, 0.653, 0.626},
            {0.907, 0.878, 0.85, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694, 0.667, 0.64, 0.613},
            {0.892, 0.863, 0.837, 0.811, 0.786, 0.762, 0.739, 0.707, 0.68, 0.653, 0.626, 0.599},
            {0.878, 0.85, 0.824, 0.799, 0.774, 0.751, 0.723, 0.694, 0.667, 0.64, 0.613, 0.583},
    };

    RpeConverter() {
        for (int i = 0; i < rpeArray.length; i++) {
            HashMap<Integer, Double> row = new HashMap<>();
            for (double v : rpeArray[i]) {
                row.put(i + 1, v);
            }
            RpeScale rpe = RpeScale.getRpe(10 - (double) i / 2);
            rpeChart.put(rpe, row);
        }
    }

    public double get1RM(RpeScale rpe, double weight, int reps) {
        assert reps > 0 && reps <= 12;
        return weight / rpeChart.get(rpe).get(reps);
    }

    public double getWorkingWeight(RpeScale rpe, double oneRepMaxWeight, int reps) {
        assert reps > 0 && reps <= 12;
        return oneRepMaxWeight * rpeChart.get(rpe).get(reps);
    }
}
