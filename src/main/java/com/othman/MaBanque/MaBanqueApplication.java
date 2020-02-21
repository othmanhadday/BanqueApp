package com.othman.MaBanque;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.othman.MaBanque.Dao.ClientRepository;
import com.othman.MaBanque.Dao.CompteRepository;
import com.othman.MaBanque.Dao.OperationRepository;
import com.othman.MaBanque.entities.Client;
import com.othman.MaBanque.entities.Compte;
import com.othman.MaBanque.entities.CompteCourant;
import com.othman.MaBanque.entities.CompteEpargne;
import com.othman.MaBanque.entities.Operation;
import com.othman.MaBanque.entities.Retrait;
import com.othman.MaBanque.entities.Versement;
import com.othman.MaBanque.metier.IBanqueMetier;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner{
	@Autowired
	private ClientRepository cliRep;
	@Autowired
	private CompteRepository comRep;
	@Autowired
	private OperationRepository opRep;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*Client c1 = cliRep.save( new Client("othmane","othmane@gmail.com"));
		Client c2 = cliRep.save( new Client("Med","Med@gmail.com"));
		
		Compte cp1 = comRep.save(new CompteCourant("c1",new Date(),90000,c1,6000));
		Compte cp2 = comRep.save(new CompteEpargne("c2",new Date(),6000,c2,5.5));
		
		opRep.save(new Versement(new Date(),9000,cp1));
		opRep.save(new Versement(new Date(),6000,cp1));
		opRep.save(new Versement(new Date(),2300,cp1));
		opRep.save(new Retrait(new Date(),9000,cp1));

		opRep.save(new Versement(new Date(),2300,cp2));
		opRep.save(new Versement(new Date(),5789,cp2));
		opRep.save(new Versement(new Date(),2300,cp2));
		opRep.save(new Retrait(new Date(),9000,cp2));
		
		banqueMetier.verser("c1", 1111);
*/	
	}

}
