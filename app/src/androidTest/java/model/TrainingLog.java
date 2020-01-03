package model;

import java.util.Date;

class TrainingLog {
    private Double weight;
    private Integer reps;
    private Integer sets;
    private RpeScale rpe;
    private LogType type;
    private RpeConverter rpeConverter = new RpeConverter();

    TrainingLog(Double weight, Integer reps, Integer sets, RpeScale rpe, LogType type) {
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.rpe = rpe;
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getReps() {
        return reps;
    }

    public Integer getSets() {
        return sets;
    }

    public LogType getType() {
        return type;
    }

    public double getPredicted1RM() {
        return rpeConverter.get1RM(rpe, weight, reps);
    }

    public RpeScale getRpe() {
        return rpe;
    }
}
