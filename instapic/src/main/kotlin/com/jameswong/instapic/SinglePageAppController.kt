package com.jameswong.instapic

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SinglePageAppController {
    @GetMapping("/post/**")
    fun redirect(): String{
        return "forward:/index.html"
    }
}