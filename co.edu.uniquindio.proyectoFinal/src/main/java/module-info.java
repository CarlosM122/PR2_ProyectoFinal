module co.edu.uniquindio.proyectofinal.fruteriaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.mapstruct;


    opens co.edu.uniquindio.proyectofinal.fruteriaapp to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.fruteriaapp;

    opens co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;
    exports co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;
    opens co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;
    exports co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;

    exports co.edu.uniquindio.proyectofinal.fruteriaapp.Service;
    requires java.sql;

}