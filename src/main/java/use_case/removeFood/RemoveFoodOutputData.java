package use_case.removeFood;

public class RemoveFoodOutputData {

    private String resultMessage;
    private String username;
    private String viewingDate;

    public RemoveFoodOutputData(String resultMessage, String username, String viewingDate) {
        this.resultMessage = resultMessage;
        this.username = username;
        this.viewingDate = viewingDate;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getViewingDate() {
        return viewingDate;
    }
}
