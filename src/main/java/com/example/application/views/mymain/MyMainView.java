package com.example.application.views.mymain;

import com.example.application.views.MainLayout;
import com.example.application.views.game.GameView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Tic Tac Toe")
public class MyMainView extends VerticalLayout {

    private String simboloJugador1;
    private String simboloJugador2;
    private String nombreJugador1;
    private String nombreJugador2;

    public MyMainView() {
        iniciarLoginJugador(1);
    }

    private void iniciarLoginJugador(int jugador) {
        removeAll();

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H2 titulo = new H2("Jugador " + jugador + ": Iniciar sesión");
        titulo.getStyle().set("margin-bottom", "20px");

        VerticalLayout loginForm = createLoginForm(jugador);

        add(titulo, loginForm);
    }

    private VerticalLayout createLoginForm(int jugador) {
        VerticalLayout loginForm = new VerticalLayout();
        loginForm.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        loginForm.setSpacing(true);

        TextField username = new TextField("Usuario");
        PasswordField password = new PasswordField("Contraseña");

        Button loginButton = new Button("Iniciar sesión", e -> {
            if (jugador == 1) {
                nombreJugador1 = username.getValue();
                elegirSimbolo(jugador);
            } else {
                nombreJugador2 = username.getValue();
                simboloJugador2 = simboloJugador1.equals("X") ? "O" : "X";
                empezarJuego();
            }
        });
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        loginForm.add(username, password, loginButton);

        return loginForm;
    }

    private void elegirSimbolo(int jugador) {
        removeAll();

        H2 titulo = new H2("Jugador " + jugador + ": Elige tu símbolo");
        titulo.getStyle().set("margin-bottom", "20px");

        Button escogerX = new Button("Jugar con O", e -> asignarSimboloYContinuar("X", jugador));
        escogerX.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button escogerO = new Button("Jugar con X", e -> asignarSimboloYContinuar("O", jugador));
        escogerO.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout symbolChoiceLayout = new VerticalLayout(titulo, escogerX, escogerO);
        symbolChoiceLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        symbolChoiceLayout.setSpacing(true);

        add(symbolChoiceLayout);
    }

    private void asignarSimboloYContinuar(String simbolo, int jugador) {
        simboloJugador1 = simbolo;
        if (jugador == 1) {
            // Inicia el proceso de login para el jugador 2
            iniciarLoginJugador(2);
        } else {
            // El jugador 2 no elige un símbolo, se le asigna el que queda
            simboloJugador2 = simboloJugador1.equals("X") ? "O" : "X";
            // Empieza el juego con el símbolo del jugador 1
            empezarJuego();
        }
    }


    private void empezarJuego() {
        simboloJugador2 = simboloJugador1.equals("X") ? "O" : "X"; // Asegúrate de asignar el símbolo opuesto al jugador 2
        String queryParams = "?symbol=" + simboloJugador1 + "&player1=" + nombreJugador1 + "&player2=" + nombreJugador2;
        getUI().ifPresent(ui -> ui.navigate("game" + queryParams));
    }


}
