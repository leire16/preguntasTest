package util;

public final class Constantes {

    private Constantes() {
        // Evita que se pueda instanciar la clase
    }

    // ------------------------
    // CONFIGURACIÓN GENERAL
    // ------------------------

    public static final int TOTAL_TEMAS = 21;
    public static final int ID_TEMA_COMUN = TOTAL_TEMAS; // 21
    public static final int TODAS_LAS_PREGUNTAS = -1;
    public static final int TOTAL_PREGUNTAS_BD = 500;
    public static final String TITULO_APP = "OPE Trainer";

    public static final int ANCHO_VENTANA = 900;
    public static final int ALTO_VENTANA = 600;
    public static final String TEXTO_TEST_TEMAS = "Test por temas";
    public static final String TEXTO_TEST_COMPLETO = "Test completo";
    public static final String TEXTO_TEST_FALLADAS = "Repaso de preguntas falladas";

    public static final String RUTA_BD = "jdbc:sqlite:src/sql/bd3.db";

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

            "Sistemas Operativos",

            "Administración de Linux",

            "Administración de Windows Server",

            "Virtualización y Cloud Computing",

            "Seguridad informática y criptografía",

            "Protección de datos, ENS, RGPD y legislación TIC",

            "ITIL y gestión de servicios TI",

            "Business Intelligence, Data Warehouse y Big Data",

            "Inteligencia Artificial y Machine Learning",

            "Desarrollo Web (HTML, CSS, JavaScript, APIs...)",

            "Programación (Java, C#, patrones, POO...)",

            "Arquitectura de computadores",

            "Repaso / Miscelánea de informática",

            "Preguntas Comunes",
    };

}