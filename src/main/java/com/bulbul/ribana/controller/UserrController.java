package com.bulbul.ribana.controller;

import com.bulbul.ribana.entity.Userr;
import com.bulbul.ribana.entity.custom.CustomUserr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RequestMapping("/userr")
public interface UserrController extends BaseController<Userr, Long> {

    @GetMapping("/findByParams")
    ResponseEntity<List<CustomUserr>> findByParams(@RequestParam Map<String, String> params) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, NoSuchFieldException;

}
