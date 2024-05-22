package com.white.assignmentjava5.util;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidateUtils {
    public static Map<String,String> validate(BindingResult bindingResult){
      return bindingResult.getFieldErrors().stream().map(objectError -> {
            Map<String,String> map = new HashMap<>();
            map.put(objectError.getField(),objectError.getDefaultMessage());
            return map;
        }).collect(HashMap::new, HashMap::putAll, HashMap::putAll);
    }
}
