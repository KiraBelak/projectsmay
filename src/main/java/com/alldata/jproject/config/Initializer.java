package com.alldata.jproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alldata.jproject.models.Clasificacion;
import com.alldata.jproject.models.Instrumento;
import com.alldata.jproject.models.Marca;
import com.alldata.jproject.repositories.ClasificacionRepository;
import com.alldata.jproject.repositories.InstrumentoRepository;
import com.alldata.jproject.repositories.MarcaRepository;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }

    private void initializeData() {
        // Lógica para inicializar los datos
        if (clasificacionRepository.count() == 0) {
            // Crear y guardar clasificaciones
            Clasificacion clasificacion1 = new Clasificacion("Cuerdas", "Cuerdas");
            clasificacionRepository.save(clasificacion1);

            Clasificacion clasificacion2 = new Clasificacion("Viento", "Viento");
            clasificacionRepository.save(clasificacion2);

            Clasificacion clasificacion3 = new Clasificacion("Percusión", "Percusión");
            clasificacionRepository.save(clasificacion3);

            Marca marca1 = new Marca("Yamaha", "Instrumentos musicales de cuerdas", clasificacion1);
            marcaRepository.save(marca1);

            Marca marca2 = new Marca("Pearl", "Instrumentos musicales de percusión", clasificacion3);
            marcaRepository.save(marca2);

            Marca marca3 = new Marca("Selenium", "Instrumentos musicales de viento", clasificacion2);
            marcaRepository.save(marca3);

            Instrumento instrumento1 = new Instrumento("Guitarra", "Instrumento de cuerda",100,10, "imagen1.jpg", marca1);
            instrumentoRepository.save(instrumento1);

            Instrumento instrumento2 = new Instrumento("Batería", "Instrumento de percusión",200,5, "imagen2.jpg", marca2);
            instrumentoRepository.save(instrumento2);

            Instrumento instrumento3 = new Instrumento("Trompeta", "Instrumento de viento",150,8, "imagen3.jpg", marca3);
            instrumentoRepository.save(instrumento3);
        }
    }
}


/* Endpoint 1 8080*/
/* Endpoint 2 8081*/
/* Endpoint 3 8082*/

/* EP principal 1,2 o 3 */