package br.eti.giorgini.Tarefas.Controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.eti.giorgini.Tarefas.Models.Tarefa;
import br.eti.giorgini.Tarefas.Models.Usuario;
import br.eti.giorgini.Tarefas.Services.UsuarioService;
import br.eti.giorgini.Tarefas.repositories.TarefaRepository;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/listar")
	public ModelAndView listar(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/listar");
		String usuarioLogin = request.getUserPrincipal().getName();
		Usuario usuarioLogado = usuarioService.encontrarPorLogin(usuarioLogin);
		mv.addObject("tarefas", tarefaRepository.carregarTarefasPorUsuario(usuarioLogado.getId()));
		return mv;
	}

	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa", new Tarefa());
		return mv;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("tarefas/inserir");
			mv.addObject(tarefa);
		} else {
			String usuarioLogin = request.getUserPrincipal().getName();
			Usuario usuarioLogado = usuarioService.encontrarPorLogin(usuarioLogin);
			tarefa.setUsuario(usuarioLogado);
			tarefa.setData(new Date());
			tarefaRepository.save(tarefa);
			mv.setViewName("redirect:/tarefas/listar/");
		}
		return mv;
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Tarefa tarefa = tarefaRepository.getOne(id);
		mv.setViewName("tarefas/alterar");
		mv.addObject(tarefa);
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("tarefas/alterar");
			mv.addObject(tarefa);
		} else {
			tarefa.setData(new Date());
			tarefaRepository.save(tarefa);
			mv.setViewName("redirect:/tarefas/listar/");
		}
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id) {
		tarefaRepository.deleteById(id);
		return "redirect:/tarefas/listar/";
	}
	
	@GetMapping("/concluir/{id}")
	public String concluir(@PathVariable("id") Long id) {
		Tarefa tarefa = tarefaRepository.getOne(id);
		tarefa.setFechada(true);
		tarefaRepository.save(tarefa);
		return "redirect:/tarefas/listar/";
	}
}
