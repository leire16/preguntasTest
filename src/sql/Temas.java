package sql;

import java.util.List;
import model.Tema;

public class Temas {

        public static final List<Tema> LISTA = List.of(

                        new Tema(1, "Modelo OSI y TCP/IP", 1, 24),

                        new Tema(2,
                                        "Redes de comunicaciones (Ethernet, LAN/WAN, WiFi, TCP/IP, IPv4/IPv6, Routing, VLAN, NAT, ARP, ICMP...)",
                                        25, 72),

                        new Tema(3,
                                        "Bases de datos relacionales y modelo E/R",
                                        73, 96),

                        new Tema(4,
                                        "SAP HANA y SAP S/4HANA",
                                        97, 120),

                        new Tema(5,
                                        "Scrum, Agile, Kanban y Waterfall",
                                        121, 144),

                        new Tema(6,
                                        "Ingeniería del Software (UML, pruebas, mantenimiento, ciclo de vida...)",
                                        145, 168),

                        new Tema(7,
                                        "Gestión de proyectos software (PERT, riesgos, planificación, métricas...)",
                                        169, 192),

                        new Tema(8,
                                        "Sistemas Operativos",
                                        193, 216),

                        new Tema(9,
                                        "Administración de Linux",
                                        217, 240),

                        new Tema(10,
                                        "Administración de Windows Server",
                                        241, 264),

                        new Tema(11,
                                        "Virtualización y Cloud Computing",
                                        265, 288),

                        new Tema(12,
                                        "Seguridad informática y criptografía",
                                        289, 312),

                        new Tema(13,
                                        "Protección de datos, ENS, RGPD y legislación TIC",
                                        313, 336),

                        new Tema(14,
                                        "ITIL y gestión de servicios TI",
                                        337, 360),

                        new Tema(15,
                                        "Business Intelligence, Data Warehouse y Big Data",
                                        361, 384),

                        new Tema(16,
                                        "Inteligencia Artificial y Machine Learning",
                                        385, 408),

                        new Tema(17,
                                        "Desarrollo Web (HTML, CSS, JavaScript, APIs...)",
                                        409, 432),

                        new Tema(18,
                                        "Programación (Java, C#, patrones, POO...)",
                                        433, 456),

                        new Tema(19,
                                        "Arquitectura de computadores",
                                        457, 480),

                        new Tema(20,
                                        "Repaso / Miscelánea de informática",
                                        481, 500),
                        new Tema(21,
                                        "Preguntas comunes",
                                        501, 700)

        );

        public static Tema obtenerTema(int numeroPregunta) {

                for (Tema tema : LISTA) {
                        if (tema.contienePregunta(numeroPregunta)) {
                                return tema;
                        }
                }

                return null;
        }

}