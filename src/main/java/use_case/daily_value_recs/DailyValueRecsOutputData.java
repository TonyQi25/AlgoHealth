package use_case.daily_value_recs;

public class DailyValueRecsOutputData {

    private double percent_cals;
    private double percent_prot;
    private double percent_carbs;
    private double percent_fat;

    public DailyValueRecsOutputData(double percent_cals, double percent_prot,
                                    double percent_carbs, double percent_fat) {

        this.percent_cals = percent_cals;
        this.percent_prot = percent_prot;
        this.percent_carbs = percent_carbs;
        this.percent_fat = percent_fat;
    }

    public double getPercent_cals() {
        return percent_cals;
    }

    public double getPercent_prot() {
        return percent_prot;
    }

    public double getPercent_carbs() {
        return percent_carbs;
    }

    public double getPercent_fat() {
        return percent_fat;
    }
}
