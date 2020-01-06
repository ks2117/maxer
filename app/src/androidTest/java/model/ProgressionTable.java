package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ProgressionTable {
    private Map<LocalDate, TrainingLog> originalTable = new HashMap<>();
    private Map<LocalDate, TrainingLog> adjustedTable = new HashMap<>();
    private RpeConverter rpeConverter = new RpeConverter();
    private String exercise;
    private double progression = 0;

    ProgressionTable(String exercise) {
        this.exercise = exercise;
    }

    void setProgression(double progression) {
        this.progression = progression;
    }

    void scheduleTrainingLog(LocalDate date, TrainingLog trainingLog) {
        originalTable.put(date, trainingLog);
        adjustedTable.put(date, trainingLog);
    }

    public TrainingLog getTrainingLog(LocalDate date) {
        return adjustedTable.get(date);
    }

    public void putTrainingLog(LocalDate date, TrainingLog log) {
        TrainingLog prevLog = adjustedTable.get(date);
        adjustedTable.replace(date, log);
        assert prevLog != null;
        double predicted1RM = (prevLog.getPredicted1RM() + rpeConverter.get1RM(log.getRpe(), log.getWeight(), log.getReps())) / 2;
        LocalDate currDate = date.plusDays(1);
        predicted1RM += progression;
        while (adjustedTable.containsKey(currDate)) {
            prevLog = adjustedTable.get(currDate);
            assert prevLog != null;
            prevLog.setWeight(rpeConverter.getWorkingWeight(prevLog.getRpe(), predicted1RM, prevLog.getReps()));
            currDate = currDate.plusDays(1);
            predicted1RM += progression;
        }
    }
}
