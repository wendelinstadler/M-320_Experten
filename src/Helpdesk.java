import java.util.ArrayList;

public class Helpdesk {
    private INotifier notifier;
    private ArrayList<Ticket> tickets;

    public Helpdesk(INotifier notifier) { // erstellt liste
        this.tickets = new ArrayList<>();
        this.notifier = notifier;
    }
    public void setNotifier(INotifier notifier) {
        this.notifier = notifier;
    }
    public void add(Ticket neuesTicket) {
        tickets.add(neuesTicket);
        String message = "Ticket #" + neuesTicket.getNumber() + " created. Response within "
                + neuesTicket.getResponseTime() + " h.";
        notifier.send(neuesTicket.getReporter(), message);
    }

    public void printOverview() {
        for (Ticket neuesTicket : tickets) {
            System.out.println(neuesTicket.getInfo() + " -> response within " + neuesTicket.getResponseTime() + " h");
        }
    }
    public void printOverview(int maxHours) {
        for (Ticket t : tickets) {
            if (t.getResponseTime() <= maxHours) {
                System.out.println(t.getInfo() + " -> response within " + t.getResponseTime() + " h");
            }
        }
    }
}