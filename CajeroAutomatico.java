class CuentaBancaria {
    private int saldo = 1000; 

    public synchronized void retirarDinero(String nombre, int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(nombre + " está retirando $" + cantidad);
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
            saldo -= cantidad;
            System.out.println(nombre + " retiró $" + cantidad + ". Saldo restante: $" + saldo);
        } else {
            System.out.println(nombre + " intentó retirar $" + cantidad + ", pero no hay suficiente saldo.");
        }
    }
}

class Usuario implements Runnable {
    private CuentaBancaria cuenta;
    private String nombre;
    private int cantidad;

    public Usuario(CuentaBancaria cuenta, String nombre, int cantidad) {
        this.cuenta = cuenta;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        cuenta.retirarDinero(nombre, cantidad);
    }
}

public class CajeroAutomatico {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();

        Thread usuario1 = new Thread(new Usuario(cuenta, "Carlos", 500));
        Thread usuario2 = new Thread(new Usuario(cuenta, "Ana", 700));
        Thread usuario3 = new Thread(new Usuario(cuenta, "Luis", 400));

        usuario1.start();
        usuario2.start();
        usuario3.start();
    }
}