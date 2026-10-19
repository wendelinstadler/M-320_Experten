public class EmailNotifier implements INotifier {

    @Override
    public void send(String recipient, String message) {
        System.out.println("E-mail to " + recipient + ": " + message);
    }
}