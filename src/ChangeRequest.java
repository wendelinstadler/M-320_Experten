public class ChangeRequest extends Ticket {

    private String system;

        public ChangeRequest(int number, String title, String reporter, String system) {
            super(number, title, reporter);
            this.system = system;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }
    @Override
    public int getResponseTime() {
        return 120;
    }
}



