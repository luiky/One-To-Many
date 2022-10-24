package com.example.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String email;
	
	@OneToMany (mappedBy ="usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)//, orphanRemoval = true)
	private Set<Direccion> direcciones = new HashSet<>();
	
	//If the collection is defined using generics to specify the element type, 
	// the associated target entity type need not be specified; 
	//otherwise the target entity class must be specified. 
	
	//If the relationship is bidirectional, the  mappedBy element must be used to specify the relationship field 
	//or property of the entity that is the owner of the relationship. 
	
	//Debo proporcionar el atributo:usuario de la entidad propietaria: Direccion.
	//fetch= FetchType.EAGER cargamos todas las direcciones que tenga el usuario al utilizar el usuario
	//CascadeType.ALL: Cuando una entidad es persistida, removida, refrescada o actualizada, 
	//su entidad relacionada debe ser persistida, removida, refrescada o actualizada también.
	
//	@OneToMany (mappedBy ="usuario",cascade= CascadeType.ALL, fetch= FetchType.EAGER)
//	private Set<Direccion> direcciones = new HashSet<>();	

	protected Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	public void addDireccion(Direccion direccion) {
		direcciones.add(direccion);
		direccion.setUsuario(this); //el usuario soy "yo"
	}
	
	public void deleteDireccion(Direccion direccion) {
		direcciones.remove(direccion);
		direccion.setUsuario(null); //Ningún Usuario asociado con esa direccion
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Direccion> getDirecciones() {
		return direcciones;
	}
		
	public void setDirecciones(Set<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	@Override
	public String toString() {
				
		return "Usuario [id=" + id + ", name=" + name + ", email=" + email + ", direcciones=" + direcciones + "]";
	}

}
