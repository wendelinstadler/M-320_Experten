public class Test {

    public static void main(String[] args) {

        // =====================================================
        // TEIL 1: VERERBUNG
        // Incident und ServiceRequest erben von Ticket.
        // =====================================================
        System.out.println("===== Teil 1: Vererbung =====");

        // Störung mit Priorität 1 (hoch)
        Incident stoerung = new Incident(1001, "Drucker druckt nicht", "M. Muster", 1);
        System.out.println("Reaktionszeit Störung mit Priorität 1 (erwartet 1): " + stoerung.getResponseTime());

        // Priorität auf 3 (tief) ändern
        stoerung.setPriority(3);
        System.out.println("Reaktionszeit nach Wechsel auf Priorität 3 (erwartet 24): " + stoerung.getResponseTime());

        // Serviceanfrage
        ServiceRequest anfrage = new ServiceRequest(1002, "Neue Docking-Station", "L. Meier", "Docking-Station");
        System.out.println("Reaktionszeit Serviceanfrage (erwartet 72): " + anfrage.getResponseTime());

        // getInfo() ist in Ticket geschrieben und wird an beide vererbt
        System.out.println("Info Störung: " + stoerung.getInfo());
        System.out.println("Info Serviceanfrage: " + anfrage.getInfo());
        System.out.println();


        // =====================================================
        // TEIL 2: POLYMORPHIE
        // Statischer Typ = Ticket (links), dynamischer Typ = Unterklasse (rechts).
        // Java nimmt beim Ausführen die Methode des echten Objekts.
        // =====================================================
        System.out.println("===== Teil 2: Polymorphie =====");

        // Test 1: Variable vom Typ Ticket, Objekt ist ein Incident
        System.out.println("--- Test 1: Störung als Ticket ---");
        Ticket ticket1 = new Incident(1003, "Server reagiert nicht", "S. Keller", 2);
        System.out.println("Reaktionszeit Priorität 2 (erwartet 4): " + ticket1.getResponseTime());

        // Test 2: Variable vom Typ Ticket, Objekt ist ein ServiceRequest
        System.out.println("--- Test 2: Serviceanfrage als Ticket ---");
        Ticket ticket2 = new ServiceRequest(1004, "Neue Maus", "A. Huber", "Maus");
        System.out.println("Reaktionszeit Serviceanfrage (erwartet 72): " + ticket2.getResponseTime());

        // Test 3: Helpdesk mit drei gemischten Tickets
        System.out.println("--- Test 3: Helpdesk mit allen Tickets ---");
        Helpdesk helpdesk = new Helpdesk(new EmailNotifier());
        helpdesk.add(ticket1);
        helpdesk.add(ticket2);
        helpdesk.add(new Incident(1005, "Internet ausgefallen", "T. Frei", 1));
        System.out.println("Übersicht aller Tickets:");
        helpdesk.printOverview();

        // Test 4: beide Überladungen (statische Polymorphie)
        System.out.println("--- Test 4: Überladungen ---");

        // Überladung 1: Konstruktor ohne Priorität, setzt automatisch 2
        Incident ohnePrioritaet = new Incident(1006, "VPN ist langsam", "R. Weber");
        System.out.println("Priorität ohne Angabe (erwartet 2): " + ohnePrioritaet.getPriority());
        helpdesk.add(ohnePrioritaet);

        // Überladung 2: printOverview mit maxHours zeigt nur dringende Tickets
        System.out.println("Nur dringende Tickets (höchstens 4 Stunden):");
        helpdesk.printOverview(4);
        System.out.println();


        // =====================================================
        // TEIL 3: ABSTRAKTE KLASSEN
        // Ticket ist abstrakt: kein "new Ticket(...)" mehr möglich.
        // Jede Unterklasse muss getResponseTime() selbst schreiben.
        // =====================================================
        System.out.println("===== Teil 3: Abstrakte Klassen =====");

        // Neuer Tickettyp: Änderungsantrag
        Ticket aenderung = new ChangeRequest(1007, "Neue Firewall-Regel", "N. Graf", "Firewall");
        System.out.println("Reaktionszeit Änderungsantrag (erwartet 120): " + aenderung.getResponseTime());
        System.out.println("Info Änderungsantrag: " + aenderung.getInfo());

        // Der Helpdesk kann den neuen Typ ohne Anpassung verwalten
        helpdesk.add(aenderung);
        System.out.println("Übersicht mit Änderungsantrag:");
        helpdesk.printOverview();

        // Diese Zeile würde einen Fehler geben, weil Ticket abstrakt ist:
        // Ticket fehler = new Ticket(9999, "Test", "Ich");
        System.out.println();


        // =====================================================
        // TEIL 4: INTERFACES
        // Der Helpdesk kennt nur INotifier, nicht E-Mail oder Teams.
        // Mit setNotifier() wird der Versandkanal gewechselt.
        // =====================================================
        System.out.println("===== Teil 4: Interfaces =====");

        // Zwei Tickets per E-Mail bestätigen
        System.out.println("--- Versand per E-Mail ---");
        Helpdesk helpdesk2 = new Helpdesk(new EmailNotifier());
        helpdesk2.add(new Incident(2001, "Bildschirm flackert", "P. Brunner", 3));
        helpdesk2.add(new ServiceRequest(2002, "Neue Tastatur", "K. Schmid", "Tastatur"));

        // Auf Teams wechseln und ein weiteres Ticket bestätigen
        System.out.println("--- Wechsel auf Teams ---");
        helpdesk2.setNotifier(new TeamsNotifier());
        helpdesk2.add(new ChangeRequest(2003, "VPN-Client aktualisieren", "D. Vogel", "VPN"));
    }
}