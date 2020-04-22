package ua.in.sz.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class HelloWorldController extends AbstractController {

	@Autowired
	private TaskExecutor taskExecutor;

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("Get hello request");

		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");

		taskExecutor.execute(this::doTask);

		return model;
	}

	private void doTask() {
		try {
			logger.info("Start task");
			TimeUnit.SECONDS.sleep(20);
			logger.info("End task");
		} catch (Exception e) {
			logger.error("Can't do task", e);
		}

	}

}