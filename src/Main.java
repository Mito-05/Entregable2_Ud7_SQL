import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import dao.GimnasioDAO;
import dao.SocioDAO;
import modelo.Gimnasio;
import modelo.Socio;

void main() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:fitness.odb");

    GimnasioDAO gymDAO = new GimnasioDAO(emf);
    SocioDAO socioDAO = new SocioDAO(emf);

    Gimnasio gymDemo = new Gimnasio("Gym Center", "Madrid", 39.90);
    gymDAO.insertarGimnasio(gymDemo);
    System.out.println(gymDemo);

    gymDemo.setNombre("Gym Center Premium");
    gymDemo.setCuotaMensual(49.90);
    gymDAO.actualizarGimnasio(gymDemo.getId(), gymDemo.getNombre(), gymDemo.getCiudad(), gymDemo.getCuotaMensual());

    gymDAO.borrarGimnasio(gymDemo.getId());

    Socio socioDemo = new Socio("Julian Chavez", 30, false);
    socioDAO.insertarSocio(socioDemo);
    System.out.println(socioDemo);

    socioDemo.setEdad(31);
    socioDemo.setVip(true);
    socioDAO.actualizarSocio(socioDemo.getId(), socioDemo.getNombreCompleto(), socioDemo.getEdad(), socioDemo.isVip());

    socioDAO.borrarSocio(socioDemo.getId());

    gymDAO.obtenerSociosDeGimnasio(1).forEach(System.out::println);

    gymDAO.obtenerNumeroSociosPorGimnasio().forEach((gimnasio, total) ->
            System.out.println(gimnasio + " -> " + total)
    );

    gymDAO.obtenerGimnasiosConMenosDe10Socios().forEach(System.out::println);

    gymDAO.obtenerTop5GimnasiosMasCaros().forEach(System.out::println);

    System.out.println(gymDAO.obtenerMasBaratoPorCiudad("Madrid"));

    socioDAO.asignarSocio(1, 2);
    socioDAO.borrarSocioDeGimnasio(1, 2);

    socioDAO.obtenerGimnasiosDeSocio(1).forEach(System.out::println);

    System.out.printf("%.2f\n", socioDAO.obtenerMediaEdadSocios());

    socioDAO.obtenerSociosSinGimnasio().forEach(System.out::println);

    emf.close();
}