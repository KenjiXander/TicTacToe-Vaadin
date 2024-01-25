package com.example.application.views.stats;

import com.example.application.models.EstadisticasJugador;
import com.example.application.services.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.services.EstadisticasService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "estadisticas", layout = MainLayout.class)
@PageTitle("Estadísticas")
public class EstadisticasView extends VerticalLayout {

    @Autowired
    private EstadisticasService estadisticasService;

    private Grid<EstadisticasJugador> grid = new Grid<>(EstadisticasJugador.class);

    public EstadisticasView() {
        setSizeFull();
        configurarGrid();
        add(grid);
        actualizarLista();

        // Botón para regresar al juego
        Button botonRegresarAlJuego = new Button("Regresar al Juego");
        botonRegresarAlJuego.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        botonRegresarAlJuego.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("game")));
        add(botonRegresarAlJuego);
    }

    private void configurarGrid() {
        grid.setSizeFull();
        grid.setColumns("nombreUsuario", "partidasJugadas", "partidasGanadas", "partidasPerdidas", "empates");
    }

    private void actualizarLista() {
        grid.setItems(estadisticasService.obtenerTodasLasEstadisticas());
    }
}
