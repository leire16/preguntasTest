package util;

import java.util.List;
import java.util.stream.IntStream;

public final class Constantes {

    private Constantes() {
        // Evita que se pueda instanciar la clase
    }

    // ------------------------
    // CONFIGURACIÓN GENERAL
    // ------------------------

    public static final int TOTAL_TEMAS = 39;
    public static final int ID_INICIO_TEMA_COMUN = 21;
    public static final int ID_FIN_TEMA_COMUN = 39;
    public static final int TODAS_LAS_PREGUNTAS = -1;
    public static final int TOTAL_PREGUNTAS_BD = 500;
    public static final int TOTAL_PREGUNTAS_BD_TODO = 700;
    public static final String TITULO_APP = "OPE Trainer";

    public static final int LIMITE_PREGUNTAS_MAS_FALLADAS = 20;

    public static final int ANCHO_VENTANA = 1000;
    public static final int ALTO_VENTANA = 750;
    public static final String TEXTO_TEST_TEMAS = "Test por temas";
    public static final String TEXTO_TEST_COMPLETO = "Test completo";
    public static final String TEXTO_TEST_FALLADAS = "Repaso de preguntas falladas";

    public static final String RUTA_BD = "jdbc:sqlite:src/sql/bd4.db";

    public static final int[] OPCIONES_NUMERO_PREGUNTAS = { 5, 10, 25, 50, 100 };

    // ------------------------
    // TEMAS DE LA OPE
    // ------------------------

    public static final String[] TEMAS = {

            "Modelo OSI y TCP/IP",

            "Redes de comunicaciones (Ethernet, LAN/WAN, WiFi, TCP/IP, IPv4/IPv6, routing, VLAN, NAT, ARP, ICMP...)",

            "Bases de datos relacionales y modelo E/R",

            "SAP HANA y SAP S/4HANA",

            "Scrum, Agile, Kanban y Waterfall",

            "Ingeniería del Software (UML, pruebas, mantenimiento, ciclo de vida...)",

            "Gestión de proyectos software (PERT, riesgos, planificación, métricas...)",

            "ITIL y gestión de servicios TI",

            "Business Intelligence, Data Warehouse y Big Data",

            "Firma electrónica, certificados y servicios de confianza ",

            "Protección de datos — RGPD y LOPDGDD",

            "ENS y seguridad de la información",

            "Criptografía y PKI",

            "Ciberseguridad y gestión de riesgos",

            "SOA, microservicios y arquitecturas",

            "Cloud computing y virtualización",

            "Ciberseguridad aplicada",

            "Big Data y ecosistemas de datos",

            "Caso Osanet / sistemas Osakidetza",

            "Ejercicios prácticos y miscelánea",

            "Preguntas Comunes",
    };

    public static List<Integer> idsTemaComun() {
        return IntStream.rangeClosed(ID_INICIO_TEMA_COMUN, ID_FIN_TEMA_COMUN)
                .boxed()
                .toList();
    }

}