package com.alldata.jproject;

import com.alldata.jproject.model.Producto;
import com.alldata.jproject.model.Usuario;
import com.alldata.jproject.repository.ProductoRepository;
import com.alldata.jproject.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, ProductoRepository productoRepository){
        return args -> {

            Usuario usuario = new Usuario("Miguel Angel", "Majunia", "Miguel@miguel.com","Hyrule","12345","ADMIN","pass");
          log.info("Inserting into Users :" + usuarioRepository.save(usuario));
          log.info("Inserting into Users :" + usuarioRepository.save(new Usuario("Polancoas", "tnx", "Polancoas@graciaspor.com","Mexico","67891","USER","pass")));

          Producto producto = new Producto( "Paleta de chocolate", "Caja con 20 paletas sabor chocolate", "choco.jpg", 20.50, 50, 5);
          log.info("Inserting into Products :" + productoRepository.save(producto));
            log.info("Inserting into Products :" + productoRepository.save(new Producto( "Helado napolitano", "Tiene capas de sabores, a todos les gusta, no conozco naiden que no le guste el helado napolitano", "default.jpg", 15, 55, 5)));
        };
    }
}
