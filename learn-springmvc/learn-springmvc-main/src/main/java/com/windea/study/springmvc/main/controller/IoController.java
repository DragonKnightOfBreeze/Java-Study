package com.windea.study.springmvc.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.nio.file.Path;

@Controller
public class IoController {
	final ServletContext servletContext;

	@Autowired
	public IoController(ServletContext servletContext) {this.servletContext = servletContext;}

	@RequestMapping("/io/upload.action")
	public void upload(Model model, MultipartFile image) throws Exception {
		System.out.println(image.getName());
		System.out.println(image.getSize());

		//得到新的图片名
		String name = image.getOriginalFilename();
		String newName = "图片1" + "." + StringUtils.getFilenameExtension(name);
		System.out.println(newName);

		//写入图片
		image.transferTo(Path.of(servletContext.getRealPath("/temp"), newName));
	}
}
