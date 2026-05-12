package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreCompleto;
    private int edad;
    private boolean vip;

    @ManyToMany
    private List<Gimnasio> gimnasios;

    public Socio() {
        this.gimnasios = new ArrayList<>();
    }

    public Socio(String nombreCompleto, int edad, boolean vip) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.vip = vip;
        this.gimnasios = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }
    public List<Gimnasio> getGimnasios() { return gimnasios; }
    public void setGimnasios(List<Gimnasio> gimnasios) { this.gimnasios = gimnasios; }

    @Override
    public String toString() {
        return "Socio [ID=" + id + ", Nombre=" + nombreCompleto + ", Edad=" + edad + ", VIP=" + vip + "]";
    }
}