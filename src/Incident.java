public class Incident extends Ticket {

    private int priority;

    public Incident(int number, String title, String reporter, int priority) {
        super(number, title, reporter);
        this.priority = priority;
    }

    public Incident(int number, String title, String reporter) { // kostrukor dafür das falls man nichts bei priority eingiebt ist es 2
        super(number, title, reporter);
        this.priority = 2;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    @Override
    public int getResponseTime() {
        if (priority == 1) {
            return 1;
        } else if (priority == 2) {
            return 4;
        } else {
            return 24;
        }
    }
}
