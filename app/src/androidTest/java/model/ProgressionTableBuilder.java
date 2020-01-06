package model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProgressionTableBuilder {
    private ProgressionTable progressionTable;
    private RpeConverter rpeConverter = new RpeConverter();
    private double init1RM;
    private double target1RM;
    private double predicted1RM = init1RM;
    private double progression;
    private LocalDate startDate;
    private LocalDate currentDate;
    private LocalDate endDate;

    ProgressionTableBuilder(String exercise, double init1RM, double target1RM, LocalDate startDate, LocalDate endDate) {
        progressionTable = new ProgressionTable(exercise);
        this.init1RM = init1RM;
        this.target1RM = target1RM;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentDate = startDate;

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        progression = (target1RM - init1RM) / days;
    }

    public ProgressionTableBuilder with(long days, int reps, int sets, RpeScale rpe) {
        assert days <= ChronoUnit.DAYS.between(currentDate, endDate);
        for(int i = 0; i < days; i++) {
            double workingWeight = rpeConverter.getWorkingWeight(rpe, predicted1RM, reps);
            TrainingLog log = new TrainingLog(workingWeight, reps, sets, rpe, LogType.AUTOMATIC);
            progressionTable.scheduleTrainingLog(currentDate, log);
            currentDate = currentDate.plusDays(1);
            predicted1RM += progression;
        }
        return this;
    }

    public ProgressionTable build() {
        long days = ChronoUnit.DAYS.between(currentDate, endDate);
        if (days > 0) {
            this.with(days, 1, 1, RpeScale.TEN);
        }
        return progressionTable;
    }
}
