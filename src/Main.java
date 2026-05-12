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

    Gimnasio gym = new Gimnasio("Gym Center", "Madrid", 39.90);
    gymDAO.insertarGimnasio(gym);
    System.out.println(gym);

    gym.setNombre("Gym Center Premium");
    gym.setCuotaMensual(49.90);
    gymDAO.actualizarGimnasio(gym.getId(), gym.getNombre(), gym.getCiudad(), gym.getCuotaMensual());

    gymDAO.borrarGimnasio(gym.getId());

    Socio socio = new Socio("Julian Chavez", 30, false);
    socioDAO.insertarSocio(socio);
    System.out.println(socio);

    socio.setEdad(31);
    socio.setVip(true);
    socioDAO.actualizarSocio(socio.getId(), socio.getNombreCompleto(), socio.getEdad(), socio.isVip());

    socioDAO.borrarSocio(socio.getId());

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