module co.edu.uniquindio.proyectofinal.fruteriaapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyectofinal.fruteriaapp to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.fruteriaapp;
    opens co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;
    exports co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;


    opens co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;
    exports co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;
}