package service;

import dao.EstadisticasDao;
import model.Configuracion;
import model.Estadisticas;

public class EstadisticasService {

    private final EstadisticasDao estadisticasDao;

    public EstadisticasService() {

        estadisticasDao = new EstadisticasDao();

    }

    /**
     * Devuelve las estadísticas del temario seleccionado.
     */
    public Estadisticas obtenerEstadisticas(Configuracion configuracion) {

        return estadisticasDao.obtenerEstadisticas(
                configuracion.getTemas());

    }

}