public class TeamsNotifier implements INotifier {

    @Override
    public void send(String recipient, String message) {
        System.out.println("Teams message to " + recipient + ": " + message);
    }
}