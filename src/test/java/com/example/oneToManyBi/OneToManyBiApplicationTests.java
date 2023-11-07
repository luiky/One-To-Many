package com.example.oneToManyBi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.oneToManyBi.entities.Direccion;
import com.example.oneToManyBi.entities.Usuario;
import com.example.oneToManyBi.repositories.DireccionRepository;
import com.example.oneToManyBi.repositories.UsuarioRepository;

@SpringBootTest
class OneToManyBiApplicationTests {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	DireccionRepository direccionRepository;
	
	
	@Test
	void mainTest() {		
		
		//Dos usuarios, luiky y lidia, luiky con dos direcciones, lidia con una.
		/** usuario LUIKY con dos direcciones **/
		Usuario uLuiky = new Usuario();
		uLuiky.setNombre("Luiky");
		//uLuiky=usuarioRepository.save(uLuiky);

				
		Direccion d = new Direccion ();
		d.setCalle("Plaza"); d.setCiudad("Caceres");d.setCodigoPostal(10002);
		d.setUsuario(uLuiky);
		//d=direccionRepository.save(d);
		
		Direccion d1 = new Direccion ();
		d1.setCalle("Calle"); d1.setCiudad("Coria");d1.setCodigoPostal(10800);		
		d1.setUsuario(uLuiky);	
		//d1=direccionRepository.save(d1);
		
		
		//usando list of para simplificar
		//uLuiky.setDirecciones(List.of(d,d1)); //pero mantengo los otros set de Usuario de direcciones.
		
		//usando ArrayList
		List<Direccion> direccionesLuiky= new ArrayList<Direccion>();
		direccionesLuiky.add(d);
		direccionesLuiky.add(d1);
		uLuiky.setDirecciones(direccionesLuiky);
			
		
		uLuiky=usuarioRepository.save(uLuiky);
		d=direccionRepository.save(d);
		d1=direccionRepository.save(d1);
				
		
		/** Usuuario LIDIA con una direccion **/
		Usuario uLidia = new Usuario();
		uLidia.setNombre("Lidia");		
		
		Direccion dirLidia = new Direccion ();
		dirLidia.setCalle("Carrer"); dirLidia.setCiudad("Sabadell"); dirLidia.setCodigoPostal(28756);
		dirLidia.setUsuario(uLidia);		
		
		//usando list of para simplificar
		uLidia.setDirecciones(List.of(dirLidia));
		uLidia= usuarioRepository.save(uLidia);
		dirLidia=direccionRepository.save(dirLidia);
		System.out.println("\tUsuario luiky con dos direcciones y lidia con una persistidos.\n");
		// RECORRIDO mostrando todos.
		
		//Recorrido por todos, sin modificar LAZY a EAGER en la relacion @OneToMany
		System.out.println(" --- Recorrido sin EAGER ---");
		List<Usuario> listUsuarios =(List<Usuario>) usuarioRepository.findAll();		
		for (Usuario u : listUsuarios) {						
			List <Direccion> dirUsuarios =direccionRepository.findByUsuario(u);
			System.out.println("Nombre: " + u.getNombre() + " . Cantidad de direcciones: "+ dirUsuarios.size());			
			for (Direccion dir : dirUsuarios) {
				System.out.println("\t"+dir.toString());
			}
			
		} 
		System.out.println(" --- FIN ---");
		
		
		/* OTRO RECORRIDO SIN EAGER
		//chatgpt e internet opcion con una query y un fetch
		//Recorrido por todos, sin modificar LAZY a EAGER en la relacion @OneToMany
		System.out.println(" --- Otro recorrido sin EAGER ---");
		List<Usuario> listUsuariosFetch =(List<Usuario>) usuarioRepository.findAllWithDirecciones();
		for (Usuario usuario : listUsuariosFetch) {
			System.out.println("---- ---- ");
			System.out.println(usuario.getNombre());
			for (Direccion dir : usuario.getDirecciones()) {
				System.out.println("\t" + dir.toString());				
			}
			System.out.println("---- ---- ");
		}*/	
		
		//borrado
//		direccionRepository.delete(d1);		
//		direccionRepository.delete(d);
//		d.setUsuario(null);
//		d1.setUsuario(null);
		
		//usuarioRepository.delete(uLuiky);

		
	}
	

}


