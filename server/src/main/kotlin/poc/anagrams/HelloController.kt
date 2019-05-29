package poc.anagrams

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun index(): String {
        return "Hello World!!!"
    }
}