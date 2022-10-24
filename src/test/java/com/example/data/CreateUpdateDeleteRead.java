package com.example.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.data.model.Direccion;
import com.example.data.model.Usuario;
import com.example.data.repository.DireccionRepository;
import com.example.data.repository.UsuarioRepository;

@SpringBootTest
class CreateUpdateDeleteRead {

	@Autowired
	private DireccionRepository direccionRepository; //solo para comprobaciones y ejemplificación orphanRemoval
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	void contextLoads() {
		
		//Create un par de usuarios y le añadimos 2 direcciones  luiky una a lidia.
		Usuario usuario01 = new Usuario("Luiky","luiky@unex.es");
		Usuario usuario02 = new Usuario("Lidia","lidia@gmail.com");
		
		usuario01.addDireccion(new Direccion("Plaza", "Caceres"));
		usuario01.addDireccion(new Direccion("Calle", "Coria"));
		
		usuario02.addDireccion(new Direccion("Carrer", "Sabadell"));
		
		usuario01 = usuarioRepository.save(usuario01);
		usuario02 = usuarioRepository.save(usuario02);		
					
		//Actualizar el email de lidia
		usuario02.setEmail("LIDIA@UNEX.ES");
		
		//Actualizar una direccion de Luiky. Coria a ACEBO. Forma basica directa
		for (Usuario u: usuarioRepository.findAll()) {
			if (u.getName()=="Luiky") {
				for (Direccion d: u.getDirecciones() ) {
					if (d.getCiudad()=="Coria") {
						d.setCiudad("ACEBO");
						usuarioRepository.save(u);
						
					}
				}
			}
		}
				
//		//Borrar una dirección de Luiky. la de  Caceres. 
		//con el orphanRemoval. Deberia borrar la fila
//		borrarConOrphanRemoval(); //acordarse de cambiarlo en Usuario

		//sin orphanRemoval. No deberia borrar la fial
		borrarSinOrphanRemoval();

		//Debug, consultar todas Direcciones
		consultarTodasDirecciones();
	
		//Mostrar el estado de la BD. Todos los usuario.
		consultarTodosUsuarios();

		
	}
	void consultarTodosUsuarios () {
		System.out.println("-------------- Consultar todos USUARIOS --------------");
		for (Usuario u: usuarioRepository.findAll()) {
			System.out.println(u.toString());
		}
	    System.out.println("-------------------------------");
		
	}
	
	void consultarTodasDirecciones () {
		System.out.println("-------------- Consultar todas DIRECCIONES --------------");
		for (Direccion d: direccionRepository.findAll()) {
			System.out.println(d.toString());
		}
	    System.out.println("-------------------------------");
		
	}
	
	void borrarSinOrphanRemoval () {
		
		//Borrar una dirección de Luiky. la de  Caceres. 
		for (Usuario u: usuarioRepository.findAll()) {
			if (u.getName()=="Luiky") {
				for (Direccion d: u.getDirecciones() ) {
					if (d.getCiudad()=="Caceres") {						
						d.setUsuario(null);						
						//System.out.println(u.toString());
						usuarioRepository.save(u); //primero guardo el usuario y luego borro la direccion en la tabla
						direccionRepository.delete(d);	
							
						
					}
				}
			}
		}
	}
	
	void borrarConOrphanRemoval () {
		//Borrar una dirección de Luiky. la de  Caceres.
		for (Usuario u: usuarioRepository.findAll()) {
		if (u.getName()=="Luiky") {
			for (Direccion d: u.getDirecciones() ) {
				if (d.getCiudad()=="Caceres") {
					u.deleteDireccion(d);				
					usuarioRepository.save(u);
				}
			}
		}
	}
		
	}
	

}