package com.api.tareas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaControlador {

    @Autowired
    TareaRepositorio tareaRepositorio;

    @GetMapping
    public List<TareaDAO> listar(){
        return tareaRepositorio.findAll();
    }

    @GetMapping("/registrar")
    public TareaDAO registrar(
            @RequestParam String titulo,
            @RequestParam String estado,
            @RequestParam String fecha
    ) {
        // Crear una instancia de TareaDAO con los parámetros recibidos
        TareaDAO tarea = new TareaDAO();
        tarea.setTitulo(titulo);
        tarea.setEstado(estado);
        tarea.setFecha(fecha);

        // Guardar la tarea en el repositorio
        return tareaRepositorio.save(tarea);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        try {
            tareaRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
