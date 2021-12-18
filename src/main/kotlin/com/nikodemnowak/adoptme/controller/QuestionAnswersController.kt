package com.nikodemnowak.adoptme.controller

import com.nikodemnowak.adoptme.dto.PatchQuestionAnswersDTO
import com.nikodemnowak.adoptme.dto.PostQuestionAnswersDTO
import com.nikodemnowak.adoptme.dto.QuestionAnswersDTO
import com.nikodemnowak.adoptme.service.QuestionAnswersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/api/questionAnswers")
class QuestionAnswersController(
        private val questionAnswersService: QuestionAnswersService
) {
    @GetMapping
    fun getQuestionAnswers(): ResponseEntity<List<QuestionAnswersDTO>> {
        return ok(questionAnswersService.findAll())
    }

    @PostMapping
    fun postQuestionAnswers(@Valid @RequestBody postQuestionAnswersDTO: PostQuestionAnswersDTO): ResponseEntity<QuestionAnswersDTO> {
        return ResponseEntity(questionAnswersService.save(postQuestionAnswersDTO), HttpStatus.CREATED)
    }

    @PatchMapping
    fun patchQuestionAnswers(patchQuestionAnswersDTO: PatchQuestionAnswersDTO): ResponseEntity<QuestionAnswersDTO> {
        return ResponseEntity(questionAnswersService.update(patchQuestionAnswersDTO), HttpStatus.CREATED)
    }
}