package com.ToDo;

import java.util.List;

import org.apache.el.stream.Optional;
import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MyController 
{
	@Autowired
	UserRepo userRepo;
	@Autowired
	TaskRepo taskRepo;
	
	@RequestMapping("add{id}")
	public Task add(@PathVariable int id, @RequestBody String details)
	{
		try
		{
			Task task = new Task();
			task.setDetails(details);
			Task taskdb=taskRepo.save(task);
			User user=userRepo.findById(id).get();
			user.getTasks().add(taskdb);
			userRepo.save(user);
			
			return taskdb;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return  null;
		}
	}
	
	@RequestMapping("readAllTasks{id}")
	public List<Task> readAllTasks(@PathVariable int id)
	{
		try 
		{
			java.util.Optional<User> obj=userRepo.findById(id);
			User user=obj.get();
			return user.getTasks();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("login{username}")
	public int login(@RequestBody String password, @PathVariable String username)
	{
		try 
		{
			int cnt=userRepo.countByUsername(username);
			if(cnt==0)
			{
				return -2; // username Wrong
			}
			if (cnt>1)
			{
				return -3; // multiple accounts 
			}
			
			User user = userRepo.findByUsername(username);
			if (user.getPassword().equals(password))
			{
				return user.getId();
			}
			else
			{
				return -4; // Password wrong 
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return -1; // exception on server
		}
	}

	@RequestMapping("delete{uid}and{tid}")
	public int delete(@PathVariable int uid , @PathVariable int tid) 
	{	
		try 
		{
			User user=userRepo.findById(uid).get();
			Task dltask=null;
			for(Task task:user.getTasks()) 
			{
				if(task.getId()==tid) 
				{
					dltask=task;
					break;
				}
			}
			if(dltask!=null) 
			{
				user.getTasks().remove(dltask);
				userRepo.save(user);
				return 1;
			}
			else
				return -1;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}	
}