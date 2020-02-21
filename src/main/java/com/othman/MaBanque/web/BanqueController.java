package com.othman.MaBanque.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.othman.MaBanque.entities.Compte;
import com.othman.MaBanque.entities.Operation;
import com.othman.MaBanque.metier.IBanqueMetier;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;

	@RequestMapping("/operation")
	public String index() {
		return "comptes";
	}

	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCompte,
			@RequestParam(name = "page",defaultValue = "0")int page,
			@RequestParam(name = "size",defaultValue = "5")int size) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperation = banqueMetier.listOperation(codeCompte, page, size);

			model.addAttribute("compte", cp);
			model.addAttribute("listOperations", pageOperation);
			
			int[] pages = new int[pageOperation.getTotalPages()];
			model.addAttribute("pages", pages);
		} catch (Exception e) {
			model.addAttribute("exception", "Compte introvable");
		}
		return "comptes";
	}

	@RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant,
			String codeCompte2) {
		try {
			if (typeOperation.equals("Vers")) {
				banqueMetier.verser(codeCompte, montant);
			} else if (typeOperation.equals("Retr")) {
				banqueMetier.retirer(codeCompte, montant);
			} else if (typeOperation.equals("Vir")) {
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
}
