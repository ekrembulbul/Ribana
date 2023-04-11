package com.bulbul.ribana.controller;

import com.bulbul.ribana.entity.Userr;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userr")
public interface UserrController extends BaseController<Userr, Long> {
}
