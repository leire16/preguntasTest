package service;

import java.util.List;

import dao.EstadisticasDao;
import model.Configuracion;
import model.Estadisticas;
import model.PreguntaDificil;

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

    public List<PreguntaDificil> obtenerPreguntasMasFalladas(
        Configuracion configuracion) {

        return estadisticasDao.obtenerPreguntasMasFalladas(
                configuracion.getTemas());

    }

}