package demirciy.ygslyspuanhesaplama.model;

/**
 * Created by Yusuf on 25.03.2016.
 */
public class YgsScore {

    private Double ID;
    private String Date;
    private String ExamName;
    private Double Marks;
    private String ScoreType;
    private Double Score;

    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setExamName(String ExamName) {
        this.ExamName = ExamName;
    }

    public void setMarks(Double Marks) {
        this.Marks = Marks;
    }

    public void setScore(Double Score) {
        this.Score = Score;
    }

    public void setScoreType(String ScoreType) {
        this.ScoreType = ScoreType;
    }

    public String getDate() {
        return Date;
    }

    public String getExamName() {
        return ExamName;
    }

    public Double getMarks() {
        return Marks;
    }

    public Double getScore() {
        return Score;
    }

    public String getScoreType() {
        return ScoreType;
    }

    @Override
    public String toString() {
        return "YgsScore{" +
                "Date='" + Date + '\'' +
                ", ID=" + ID +
                ", ExamName='" + ExamName + '\'' +
                ", Marks=" + Marks +
                ", ScoreType='" + ScoreType + '\'' +
                ", Score=" + Score +
                '}';
    }
}
