package com.example.application.views.game;

import com.example.application.TicTacToeGame;
import com.example.application.services.EstadisticasService;
import com.example.application.views.MainLayout;
import com.example.application.views.mymain.MyMainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PageTitle("Game")
@Route(value = "game", layout = MainLayout.class)
public class GameView extends VerticalLayout implements BeforeEnterObserver {

    private final TicTacToeGame ticTacToe = new TicTacToeGame();
    private Button[][] botonesTablero = new Button[3][3];
    private TextField textoGanador = new TextField("Ganador:");

    private int ganaX = 0;
    private int ganaO = 0;

    private TextField ganaXTexto = new TextField("");
    private TextField ganaOTexto = new TextField("");

    private String nombreJugadorX;
    private String nombreJugadorO;

    private String nombreJugador1;
    private String nombreJugador2;

    @Autowired
    private EstadisticasService estadisticasService;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> symbolParam = event.getLocation().getQueryParameters().getParameters().get("symbol").stream().findFirst();
        Optional<String> player1Param = event.getLocation().getQueryParameters().getParameters().get("player1").stream().findFirst();
        Optional<String> player2Param = event.getLocation().getQueryParameters().getParameters().get("player2").stream().findFirst();

        if (symbolParam.isPresent() && player1Param.isPresent() && player2Param.isPresent()) {
            String simboloJugadorPrincipio = symbolParam.get();
            nombreJugador1 = player1Param.get();
            nombreJugador2 = player2Param.get();

            if (simboloJugadorPrincipio.equals("X")) {
                nombreJugadorX = nombreJugador1;
                nombreJugadorO = nombreJugador2;
            } else {
                nombreJugadorX = nombreJugador2;
                nombreJugadorO = nombreJugador1;
            }

            ticTacToe.setSimboloJugadorPrincipio(simboloJugadorPrincipio);
        }

        mostrarTablero();
        estructuraJuego();
    }

    private void mostrarTablero() {
        VerticalLayout disenoVertical = new VerticalLayout();
        disenoVertical.setWidth("min-content");
        disenoVertical.setAlignItems(Alignment.CENTER);

        for (int fila = 0; fila < 3; fila++) {
            HorizontalLayout botonFila = new HorizontalLayout();
            botonFila.setWidth("min-content");
            for (int colum = 0; colum < 3; colum++) {
                Button botonTablero = crearBotonTablero(fila, colum);
                botonesTablero[fila][colum] = botonTablero;
                botonFila.add(botonTablero);
            }
            disenoVertical.add(botonFila);
        }

        add(crearBotonRegreso());
        add(disenoVertical);
        add(textoGanador);
        add(new Hr());
        add(ganaXTexto);
        add(ganaOTexto);
        add(reiniciarVictoriasBoton());
    }

    private Button crearBotonTablero(int fila, int colum) {
        Button boton = new Button();
        boton.setWidth("min-content");
        boton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton.addClickListener(e -> clicksBotonesTablero(fila, colum));
        return boton;
    }

    private void clicksBotonesTablero(int fila, int colum) {
        Button botonClick = botonesTablero[fila][colum];

        if (!botonClick.getText().isEmpty()) {
            return;
        }

        ticTacToe.hacerMovimiento(fila, colum);

        String simboloJugadorActual = ticTacToe.getsimboloJugadorActual();
        botonClick.setText(simboloJugadorActual);

        if (ticTacToe.ganaJuego()) {
            String simboloGanador = ticTacToe.getsimboloJugadorActual();
            mostrarGanador(simboloGanador);
            contadorVictorias(simboloGanador);
            return;
        }

        if (ticTacToe.empate()) {
            empate();
            return;
        }

        ticTacToe.cambiarJugador();
    }

    private void mostrarGanador(String simboloGanador) {
        String nombreGanador = simboloGanador.equals("X") ? nombreJugadorX : nombreJugadorO;
        textoGanador.setValue("Ganador: " + nombreGanador + " (" + simboloGanador + ")");
        estadisticasService.actualizarEstadisticas(nombreGanador, true, false);
        desactivarBotonesTablero();
    }

    private void contadorVictorias(String simboloGanador) {
        if (simboloGanador.equals("X")) {
            ganaX++;
            ganaXTexto.setValue("Victorias " + nombreJugadorX + ": " + ganaX);
        } else if (simboloGanador.equals("O")) {
            ganaO++;
            ganaOTexto.setValue("Victorias " + nombreJugadorO + ": " + ganaO);
        }
    }

    private void empate() {
        textoGanador.setValue("Empate");
        estadisticasService.actualizarEstadisticas(nombreJugadorX, false, true);
        estadisticasService.actualizarEstadisticas(nombreJugadorO, false, true);
        desactivarBotonesTablero();
    }

    private void desactivarBotonesTablero() {
        for (int fila = 0; fila < 3; fila++) {
            for (int colum = 0; colum < 3; colum++) {
                botonesTablero[fila][colum].setEnabled(false);
            }
        }
    }

    private void estructuraJuego() {
        setWidth("100%");
        setAlignItems(Alignment.CENTER);

        HorizontalLayout disenoHorizontal = new HorizontalLayout();
        disenoHorizontal.setWidthFull();
        disenoHorizontal.setAlignItems(Alignment.CENTER);

        VerticalLayout espacioIzquierda = new VerticalLayout();
        espacioIzquierda.setFlexGrow(1, espacioIzquierda);
        disenoHorizontal.add(espacioIzquierda);

        FormLayout formLayout3Colum = new FormLayout();
        formLayout3Colum.setWidth("100%");
        disenoHorizontal.add(formLayout3Colum);

        VerticalLayout espacioDerecha = new VerticalLayout();
        espacioDerecha.setFlexGrow(1, espacioDerecha);
        disenoHorizontal.add(espacioDerecha);

        Button botonReiniciar = new Button("Reiniciar");
        botonReiniciar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        botonReiniciar.addClickListener(e -> reiniciarJuego());
        disenoHorizontal.add(botonReiniciar);

        add(disenoHorizontal);
    }

    private void reiniciarJuego() {
        ticTacToe.reiniciarJuego();
        for (int fila = 0; fila < 3; fila++) {
            for (int colum = 0; colum < 3; colum++) {
                botonesTablero[fila][colum].setText("");
                botonesTablero[fila][colum].setEnabled(true);
            }
        }
        textoGanador.setValue("");
    }

    private Button reiniciarVictoriasBoton() {
        Button reiniciarVictoriasBoton = new Button("Reiniciar Victorias");
        reiniciarVictoriasBoton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        reiniciarVictoriasBoton.addClickListener(e -> reiniciarVictorias());
        return reiniciarVictoriasBoton;
    }

    private void reiniciarVictorias() {
        ganaX = 0;
        ganaO = 0;
        ganaXTexto.setValue("Victorias X: 0");
        ganaOTexto.setValue("Victorias O: 0");
    }


    private Button crearBotonRegreso() {
        Button botonRegreso = new Button("Regresar a escoger jugador");
        botonRegreso.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(MyMainView.class)));
        return botonRegreso;
    }
}
