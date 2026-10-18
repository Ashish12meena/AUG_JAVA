class HostelRoom {
    String number;

    HostelRoom(String number) {
        this.number = number;
    }
}

class Hostel {
    String name;
    HostelRoom[] rooms = new HostelRoom[3];

    Hostel(String name) {
        this.name = name;
        rooms[0] = new HostelRoom("Room 1");    // rooms made inside the hostel
        rooms[1] = new HostelRoom("Room 2");
        rooms[2] = new HostelRoom("Room 3");
    }

    void display() {
        System.out.println(name);
        for (int i = 0; i < rooms.length; i++) {
            System.out.println("  " + rooms[i].number);
        }
    }
}

public class Composition {
    public static void main(String[] args) {
        Hostel h1 = new Hostel("VGU Girls Hostel");
        h1.display();

        h1 = null;   // hostel gone, so its rooms are gone too
    }
}
