

package cal;

public class Entry {

    final String hour;
    String description;

    public Entry(String hour) {
        this.hour = hour;
        this.description = "";

    }

    public String getHour() {
        return this.hour;
    }

    public String getColor() {
        if (description.equals("")) {
            return "lightblue";
        }
        return "red";
    }

    public String getDescription() {
        if (description.equals("")) {
            return "None";
        }
        return this.description;
    }

    public void setDescription(String descr) {
        description = descr;
    }

}
