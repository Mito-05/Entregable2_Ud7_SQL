package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Gimnasio;
import modelo.Socio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GimnasioDAO {
    EntityManagerFactory emf;

    public GimnasioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertarGimnasio(Gimnasio gimnasio) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(gimnasio);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarGimnasio(int id, String nombre, String ciudad, double cuotaMensual) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Gimnasio g = em.find(Gimnasio.class, id);
        if (g != null) {
            g.setNombre(nombre);
            g.setCiudad(ciudad);
            g.setCuotaMensual(cuotaMensual);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarGimnasio(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Gimnasio g = em.find(Gimnasio.class, id);
        if (g != null) {
            for (Socio s : g.getSocios()) {
                s.getGimnasios().remove(g);
            }
            em.remove(g);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Socio> obtenerSociosDeGimnasio(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Socio> query = em.createQuery("select s from Socio s join s.gimnasios g where g.id = :id", Socio.class);
        query.setParameter("id", id);
        List<Socio> socios = query.getResultList();
        em.close();
        return socios;
    }

    public Map<String, Long> obtenerNumeroSociosPorGimnasio() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("select g.nombre, count(s) from Gimnasio g left join g.socios s group by g.nombre", Object[].class);
        List<Object[]> grupos = query.getResultList();
        Map<String, Long> res = new HashMap<>();
        for (Object[] grupo : grupos) {
            String nombre = (String) grupo[0];
            Long num = (Long) grupo[1];
            res.put(nombre, num);
        }
        em.close();
        return res;
    }

    public List<Gimnasio> obtenerGimnasiosConMenosDe10Socios() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Integer> queryIds = em.createQuery("select g.id from Gimnasio g left join g.socios s group by g.id having count(s) < 10", Integer.class);
        List<Integer> ids = queryIds.getResultList();

        TypedQuery<Gimnasio> queryGyms = em.createQuery("select g from Gimnasio g where g.id in :ids", Gimnasio.class);
        queryGyms.setParameter("ids", ids);
        List<Gimnasio> gimnasios = queryGyms.getResultList();
        em.close();
        return gimnasios;
    }

    public List<Gimnasio> obtenerTop5GimnasiosMasCaros() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Gimnasio> query = em.createQuery("select g from Gimnasio g order by g.cuotaMensual desc", Gimnasio.class);
        query.setMaxResults(5);
        List<Gimnasio> gimnasios = query.getResultList();
        em.close();
        return gimnasios;
    }

    public Gimnasio obtenerMasBaratoPorCiudad(String ciudad) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Gimnasio> query = em.createQuery("select g from Gimnasio g where g.ciudad = :ciudad order by g.cuotaMensual asc", Gimnasio.class);
        query.setParameter("ciudad", ciudad);
        query.setMaxResults(1);
        List<Gimnasio> resultados = query.getResultList();
        Gimnasio g = resultados.isEmpty() ? null : resultados.get(0);
        em.close();
        return g;
    }
}