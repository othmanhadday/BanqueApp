package com.othman.MaBanque.metier;

import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.othman.MaBanque.Dao.CompteRepository;
import com.othman.MaBanque.Dao.OperationRepository;
import com.othman.MaBanque.entities.Compte;
import com.othman.MaBanque.entities.CompteCourant;
import com.othman.MaBanque.entities.Operation;
import com.othman.MaBanque.entities.Retrait;
import com.othman.MaBanque.entities.Versement;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{
	@Autowired
	private CompteRepository compteRepostory;
	@Autowired
	private OperationRepository operationRepository;
	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp=compteRepostory.findById(codeCpte).get();
		if (cp==null) {
			throw new RuntimeException("Compte introvable");
		}
		
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(),montant,cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepostory.save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		double facilitesCaisse=0;
		
		if(cp instanceof CompteCourant)
			facilitesCaisse=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilitesCaisse<montant)
			throw new RuntimeException("Solde insuffisant");
		
		Retrait r = new Retrait(new Date(),montant,cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepostory.save(cp);		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if(codeCpte1.equals(codeCpte2)) {
			throw new RuntimeException("Impossible virement sur le meme compte");
		}
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		
		return operationRepository.listOperaton(codeCpte, PageRequest.of(page, size));
	}

}
