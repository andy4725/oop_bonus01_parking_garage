import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // The assignment describes a three-level garage.
        // This example gives each level 10 spaces.
        Parkolohaz parkolohaz = new Parkolohaz(3, 10, new RendszerOra());
        boolean fut = true;

        System.out.println("Marosvasarhelyi parkolohaz");

        while (fut) {
            kiirMenu();

            String valasztas = scanner.nextLine();

            if ("1".equals(valasztas)) {
                behajtasMenu(parkolohaz);
            } else if ("2".equals(valasztas)) {
                kihajtasMenu(parkolohaz);
            } else if ("3".equals(valasztas)) {
                foglaltsagMenu(parkolohaz);
            } else if ("4".equals(valasztas)) {
                System.out.printf(
                    "Bevetel: %.2f lej%n",
                    parkolohaz.getNapiBevetel()
                );
            } else if ("0".equals(valasztas)) {
                fut = false;
            } else {
                System.out.println("Ismeretlen menupont.");
            }

            System.out.println();
        }

        System.out.println("Viszontlatasra!");
        scanner.close();
    }

    private static void kiirMenu() {
        System.out.println("1 - Behajtas");
        System.out.println("2 - Kihajtas");
        System.out.println("3 - Foglaltsag szintenkent");
        System.out.println("4 - Napi bevetel");
        System.out.println("0 - Kilepes");
        System.out.print("Valasztas: ");
    }

    private static void behajtasMenu(Parkolohaz parkolohaz) {
        System.out.println("Jarmutipus:");
        System.out.println("1 - Auto");
        System.out.println("2 - Motor");
        System.out.println("3 - Kisbusz");
        System.out.println("4 - Elektromos auto");
        System.out.print("Valasztas: ");

        String tipus = scanner.nextLine();

        System.out.print("Rendszam: ");
        String rendszam = scanner.nextLine();

        try {
            Jarmu jarmu = letrehozJarmu(tipus, rendszam);
            int szint = parkolohaz.behajt(jarmu);
            System.out.println("Sikeres behajtas. Szint: " + szint);
        } catch (IllegalArgumentException e) {
            System.out.println("Hiba: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Hiba: " + e.getMessage());
        }
    }

    // This method selects the class based on the user's menu choice.
    // The parking house itself does not need to know these vehicle types.
    private static Jarmu letrehozJarmu(String tipus, String rendszam) {
        if ("1".equals(tipus)) {
            return new Auto(rendszam);
        } else if ("2".equals(tipus)) {
            return new Motor(rendszam);
        } else if ("3".equals(tipus)) {
            return new Kisbusz(rendszam);
        } else if ("4".equals(tipus)) {
            return new ElektromosAuto(rendszam);
        }

        throw new IllegalArgumentException("Ismeretlen jarmutipus.");
    }

    private static void kihajtasMenu(Parkolohaz parkolohaz) {
        System.out.print("Rendszam: ");
        String rendszam = scanner.nextLine();

        try {
            double dij = parkolohaz.kihajt(rendszam);
            System.out.printf("Fizetendo: %.2f lej%n", dij);
        } catch (IllegalArgumentException e) {
            System.out.println("Hiba: " + e.getMessage());
        }
    }

    private static void foglaltsagMenu(Parkolohaz parkolohaz) {
        int szint;

        for (szint = 1; szint <= parkolohaz.getSzintekSzama(); szint++) {
            System.out.println(
                szint + ". szint: "
                + parkolohaz.getFoglaltHelyek(szint) + " foglalt, "
                + parkolohaz.getSzabadHelyek(szint) + " szabad"
            );
        }
    }
}