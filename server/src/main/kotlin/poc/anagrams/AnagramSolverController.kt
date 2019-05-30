package poc.anagrams

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/anagrams")
class AnagramSolverController(
        private val anagramSolverService: AnagramSolverService
) {

    @GetMapping("/{text}")
    fun solver(@PathVariable text: String): List<Anagrams> {
        return anagramSolverService.anagrams(text)
    }
}