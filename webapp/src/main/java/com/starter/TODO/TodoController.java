package com.starter.TODO;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.starter.exception.ExceptionController;

@Controller
@SessionAttributes("displayName")
public class TodoController {

	@Autowired
	TodoSevice todoservice;
	
	@RequestMapping(value="/listTodo", method=RequestMethod.GET)
	public String showList( ModelMap model){
		//model.addAttribute("whatsmyname",YourName);
		model.addAttribute("todos",todoservice.retrieveTodos(retrieveLoggedName()));
		return "ListTodo";
	}
	
	
	private String retrieveLoggedName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo",new Todo(0, retrieveLoggedName(), "", new Date(), false));
		return "addTodo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()){
			return "addTodo";
		}
		todoservice.addTodo(retrieveLoggedName(), todo.getDesc(), new Date(), false);
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:listTodo";
		
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model,@RequestParam int id) {
		Todo todo = todoservice.retrieveTodo(id);
		model.addAttribute("todo",todo);
		return "addTodo";
	}
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String saveTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors()){
			return "addTodo";
		}
		todo.setUser(retrieveLoggedName());
		todoservice.updateTodo(todo);
		return "redirect:listTodo";
	}
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model,@RequestParam int id) {
		todoservice.deleteTodo(id);
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:listTodo";
	}
	private Log logger = LogFactory.getLog(ExceptionController.class);

	@ExceptionHandler(value = Exception.class)
	public String handleError(HttpServletRequest req, Exception exception) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		return "error-todo";
	}
}
