package interface_adapter.display_food_options;

import java.util.HashMap;

public class DisplayOptionsViewState {

    private String currSelection;
    private HashMap<String, Integer> selectionMap;
    private String[] selectionList;
    private String errorMessage;

    public String getCurrSelection() {
        return currSelection;
    }

    public void setCurrSelection(String currSelection) {
        this.currSelection = currSelection;
    }

    public HashMap<String, Integer> getSelectionMap() {
        return selectionMap;
    }

    public void setSelectionMap(HashMap<String, Integer> selectionMap) {
        this.selectionMap = selectionMap;
    }

    public String[] getSelectionList() {
        return selectionList;
    }

    public void setSelectionListItem(int place, String item) {
        selectionList[place] = item;
    }

    public void setSelectionList(String[] selectionList) {
        this.selectionList = selectionList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
