
package day4task;
class MovieApp {
    private int total_seats = 10;

    public synchronized void bookSeats(int seats) {
        System.out.println(Thread.currentThread().getName() + " trying to book " + seats + " seats.");
        if (total_seats >= seats) {
            total_seats -= seats;
            System.out.println("Booked Successfully " + seats + " seats.");
            System.out.println("Seats left: " + total_seats);
        } else {
            System.out.println("Seats not booked. Only " + total_seats + " seats available.");
        }
    }

    public synchronized void cancelSeats(int seats) {
        total_seats += seats;
        System.out.println(Thread.currentThread().getName() + " cancelled " + seats + " seats.");
        System.out.println("Seats now available: " + total_seats);
    }

    public synchronized int getAvailableSeats() {
        return total_seats;
    }


    public synchronized void printStatus() {
        System.out.println("Current available seats: " + total_seats);
    }
}

public class BookMyShow extends Thread {
    static MovieApp movie;
    int seats;
    boolean cancelBooking;

    public BookMyShow(int seats, boolean cancelBooking) {
        this.seats = seats;
        this.cancelBooking = cancelBooking;
    }

    public void run() {
        if (cancelBooking) {
            movie.cancelSeats(seats);
        } else {
            movie.bookSeats(seats);
        }
    }

    public static void main(String[] args) {
        movie = new MovieApp();

        BookMyShow customer1 = new BookMyShow(8, false);
        customer1.setName("Customer1");
        customer1.start();

        BookMyShow customer2 = new BookMyShow(6, false);
        customer2.setName("Customer2");
        customer2.start();

    
        try {
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        movie.printStatus();
   
        BookMyShow customer3 = new BookMyShow(3, true);
        customer3.setName("Customer1 - Cancellation");
        customer3.start();

        BookMyShow customer4 = new BookMyShow(4, false);
        customer4.setName("Customer3");
        customer4.start();
    }
}
